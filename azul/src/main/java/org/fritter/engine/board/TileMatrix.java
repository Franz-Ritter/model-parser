package org.fritter.engine.board;

import static org.fritter.engine.model.StoneColour.BLUE;
import static org.fritter.engine.model.StoneColour.RED;
import static org.fritter.engine.model.StoneColour.TURQUOISE;
import static org.fritter.engine.model.StoneColour.WHITE;
import static org.fritter.engine.model.StoneColour.YELLOW;

import java.security.InvalidParameterException;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.fritter.engine.model.StoneColour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TileMatrix {

  private TileRow[] rows = new TileRow[5];

  @Autowired
  public TileMatrix() {
    initMatrix();
  }

  private void initMatrix() {
    rows[0] = new TileRow(BLUE, YELLOW, RED, WHITE, TURQUOISE);
    rows[1] = new TileRow(TURQUOISE, BLUE, YELLOW, RED, WHITE);
    rows[2] = new TileRow(WHITE, TURQUOISE, BLUE, YELLOW, RED);
    rows[3] = new TileRow(RED, WHITE, TURQUOISE, BLUE, YELLOW);
    rows[4] = new TileRow(YELLOW, RED, WHITE, TURQUOISE, BLUE);
  }


  public Integer placeStone(int rowNumber, StoneColour stoneColour) {
    if (getRow(rowNumber).hasColourPlacedInRow(stoneColour)) {
      throw new InvalidParameterException(String.format("Stone %s in row %s already exists", stoneColour, rowNumber));
    }
    TileRow currentRow = getRow(rowNumber);
    Integer columnNumber = currentRow.placeStone(stoneColour);
    return scorePoints(rowNumber, columnNumber);
  }

  private Integer scorePoints(Integer rowNumber, Integer columnNumber) {
    TileRow currentRow = getRow(rowNumber);
    boolean rowHasNeighbours = currentRow.isPlaced(columnNumber - 1) || currentRow.isPlaced(columnNumber + 1);
    boolean columnHasNeighbours = hasColumnFiledNeighbours(rowNumber, columnNumber);

    if(!rowHasNeighbours && !columnHasNeighbours) {
      return 1;
    }
    if (!rowHasNeighbours){
      return scoreColumn(rowNumber, columnNumber);
    }
    if (!columnHasNeighbours){
      return scoreRow(rowNumber, columnNumber);
    }
    return scoreRow(rowNumber, columnNumber) + scoreColumn(rowNumber, columnNumber) ;
  }

  private boolean hasColumnFiledNeighbours(Integer rowNumber, Integer columnNumber) {
    if (isOutOfRange(rowNumber + 1) && isOutOfRange(rowNumber - 1)) {
      return false;
    }
    boolean topNeighbour = !isOutOfRange(rowNumber + 1) && getRow(rowNumber + 1).isPlaced(columnNumber);
    boolean bottomNeighbour = !isOutOfRange(rowNumber - 1) && getRow(rowNumber - 1).isPlaced(columnNumber);
    return topNeighbour || bottomNeighbour;
  }

  private Integer scoreRow(int rowNumber, Integer columnNumber) {
    return 1 +
        scoreRowDirection(rowNumber, columnNumber + 1, () -> 1) +
        scoreRowDirection(rowNumber, columnNumber - 1, () -> -1);
  }

  private Integer scoreRowDirection(int rowNumber, Integer columnNumber, Supplier<Integer> direction) {
    if (isOutOfRange(columnNumber)) {
      return 0;
    }
    TileRow currentRow = getRow(rowNumber);
    if (currentRow.isPlaced(columnNumber)) {
      return 1 + scoreRowDirection(rowNumber, columnNumber + direction.get(), direction);
    } else {
      return 0;
    }
  }

  private Integer scoreColumn(int rowNumber, Integer columnNumber) {
    return 1 +
        scoreColumnDirection(rowNumber + 1, columnNumber, () -> 1) +
        scoreColumnDirection(rowNumber - 1, columnNumber, () -> -1);
  }

  private Integer scoreColumnDirection(int rowNumber, Integer columnNumber, Supplier<Integer> direction) {
    if (isOutOfRange(rowNumber)) {
      return 0;
    }
    if (getRow(rowNumber).isPlaced(columnNumber)) {
      return 1 + scoreColumnDirection(rowNumber + direction.get(), columnNumber, direction);
    } else {
      return 0;
    }
  }

  public void assertPlaceStone(Integer rowNumber, StoneColour colour) {
    if(getRow(rowNumber).hasColourPlacedInRow(colour)) {
      throw new IllegalStateException(String.format("%s is already given in row %s", colour, rowNumber));
    }
  }

  public static boolean isOutOfRange(int number) {
    return number < 0 || number > 4;
  }

  public void printMatrix() {
    Stream.of(rows).map(TileRow::toString).forEach(System.out::println);
  }

  private TileRow getRow(Integer row) {
    return rows[row];
  }
}
