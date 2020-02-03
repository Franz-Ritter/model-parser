package org.fritter.engine.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.fritter.engine.model.StoneColour;
import org.junit.jupiter.api.Test;

public class TileFillerBoardTest {

  @Test
  void printFiller() {
    TileFillerBoard tileFillerBoard = new TileFillerBoard();
    tileFillerBoard.printFiller();
  }


  @Test
  void placeStones_emptyRow_twoLeft() {
    TileFillerBoard tileFillerBoard = new TileFillerBoard();

    Integer stonesToMuch = tileFillerBoard.placeStones(2, StoneColour.BLUE, 1);
    tileFillerBoard.printFiller();

    assertThat(stonesToMuch).isEqualTo(-2);
  }

  @Test
  void placeStones_twoTimes_oneLeft() {
    TileFillerBoard tileFillerBoard = new TileFillerBoard();

    tileFillerBoard.placeStones(2, StoneColour.BLUE, 1);
    Integer stonesToMuch = tileFillerBoard.placeStones(2, StoneColour.BLUE, 1);

    tileFillerBoard.printFiller();

    assertThat(stonesToMuch).isEqualTo(-1);
  }

  @Test
  void placeStones_twoTimes_oneTooMuch() {
    TileFillerBoard tileFillerBoard = new TileFillerBoard();

    tileFillerBoard.placeStones(2, StoneColour.BLUE, 2);
    Integer stonesTooMuch = tileFillerBoard.placeStones(2, StoneColour.BLUE, 2);

    tileFillerBoard.printFiller();

    assertThat(stonesTooMuch).isEqualTo(1);
  }


}
