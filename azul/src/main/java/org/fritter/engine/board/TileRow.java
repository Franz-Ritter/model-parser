package org.fritter.engine.board;

import static org.fritter.engine.board.TileMatrix.isOutOfRange;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.fritter.engine.model.StoneColour;

public class TileRow {

  private TileField[] fields = new TileField[5];

  public TileRow(StoneColour first, StoneColour second, StoneColour third, StoneColour fourth, StoneColour fifth) {
    fields[0] = new TileField(first);
    fields[1] = new TileField(second);
    fields[2] = new TileField(third);
    fields[3] = new TileField(fourth);
    fields[4] = new TileField(fifth);
  }

  /**
   * places a stone in a row.
   *
   * @param stoneColour The colour of the stone which will be set.
   * @return The column number of the stone which is set.
   */
  Integer placeStone(StoneColour stoneColour) {
    for(int i = 0; i<fields.length; i++) {
      if (fields[i].getColour().equals(stoneColour)) {
        fields[i].setPlaced(true);
        return i;
      }
    }
    throw new RuntimeException(String.format("Stone colour %s not set in this row"));
  }

  boolean hasColourPlacedInRow(StoneColour stoneColour) {
    return Stream.of(fields).anyMatch(field -> field.getColour().equals(stoneColour) && field.isPlaced());
  }

  @Override
  public String toString() {
    return Stream.of(fields)
        .map(field -> field.isPlaced() ? field.getColour().getShortName() : "x")
        .collect(Collectors.joining(" - ", "|", "|"));
  }


  public boolean isPlaced(int column) {
    if (isOutOfRange(column)) {
      return false;
    }
    return fields[column].isPlaced();
  }
}
