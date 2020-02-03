package org.fritter.engine.board;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.fritter.engine.model.StoneColour;

public class PlayerBoard {

  private Integer score;
  private TileMatrix tileMatrix;
  private TileFillerBoard tileFillerBoard;
  private Integer negativeStones;

  PlayerBoard() {
    tileMatrix = new TileMatrix();
  }

  public void placeStones(Integer rowNumber, StoneColour colour, final Integer number) {
    tileMatrix.assertPlaceStone(rowNumber, colour);
    Integer leftOvers = this.tileFillerBoard.placeStones(rowNumber, colour, number);
    if (leftOvers > 0) {
      negativeStones += leftOvers;
    }
  }

  public Integer scoreRound() {
    Integer oldScore = score;
    for (int i = 0; i < 5; i++) {
      StoneColour colourToPlace = tileFillerBoard.emptyRow(i);
      if (!colourToPlace.equals(StoneColour.EMPTY)) {
        score += tileMatrix.placeStone(i, colourToPlace);
      }
    }
    score += PlayerBoardUtil.getNegativePoints(negativeStones);
    negativeStones = 0;
    return score;
  }


}
