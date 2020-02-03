package org.fritter.engine.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.fritter.engine.board.TileMatrix;
import org.fritter.engine.model.StoneColour;
import org.junit.jupiter.api.Test;

class TileMatrixTest {

  @Test
  void printMatrix() {
    TileMatrix tileMatrix = new TileMatrix();
    tileMatrix.printMatrix();
  }


  @Test
  void placeStone_emptyBoard_onePoint() {
    TileMatrix tileMatrix = new TileMatrix();
    tileMatrix.printMatrix();

    Integer points = tileMatrix.placeStone(0, StoneColour.YELLOW);
    System.out.println("Points: " + points);
    tileMatrix.printMatrix();

    assertThat(points).isEqualTo(1);
  }

  @Test
  void placeStone_fourStonesSet_6Point() {
    TileMatrix tileMatrix = new TileMatrix();

    tileMatrix.placeStone(0, StoneColour.RED);
    tileMatrix.placeStone(0, StoneColour.WHITE);
    tileMatrix.placeStone(1, StoneColour.BLUE);
    tileMatrix.placeStone(2, StoneColour.TURQUOISE);
    System.out.println("Before test");
    tileMatrix.printMatrix();

    Integer points = tileMatrix.placeStone(0, StoneColour.YELLOW);
    System.out.println("After test");
    System.out.println("Points: " + points);
    tileMatrix.printMatrix();

    assertThat(points).isEqualTo(6);
  }

  @Test
  void placeStone_rowBegin_2Point() {
    TileMatrix tileMatrix = new TileMatrix();

    tileMatrix.placeStone(1, StoneColour.BLUE);
    System.out.println("Before test");
    tileMatrix.printMatrix();

    Integer points = tileMatrix.placeStone(0, StoneColour.YELLOW);
    System.out.println("After test");
    System.out.println("Points: " + points);
    tileMatrix.printMatrix();

    assertThat(points).isEqualTo(2);
  }

  @Test
  void placeStone_rowMid_3Point() {
    TileMatrix tileMatrix = new TileMatrix();

    tileMatrix.placeStone(1, StoneColour.BLUE);
    tileMatrix.placeStone(3, StoneColour.WHITE);
    System.out.println("Before test");
    tileMatrix.printMatrix();

    Integer points = tileMatrix.placeStone(2, StoneColour.TURQUOISE);
    System.out.println("After test");
    System.out.println("Points: " + points);
    tileMatrix.printMatrix();

    assertThat(points).isEqualTo(3);
  }

  @Test
  void placeStone_rowMid_5Point() {
    TileMatrix tileMatrix = new TileMatrix();

    tileMatrix.placeStone(0, StoneColour.YELLOW);
    tileMatrix.placeStone(1, StoneColour.BLUE);
    tileMatrix.placeStone(3, StoneColour.WHITE);
    tileMatrix.placeStone(4, StoneColour.RED);
    System.out.println("Before test");
    tileMatrix.printMatrix();

    Integer points = tileMatrix.placeStone(2, StoneColour.TURQUOISE);
    System.out.println("After test");
    System.out.println("Points: " + points);
    tileMatrix.printMatrix();

    assertThat(points).isEqualTo(5);
  }

  @Test
  void placeStone_columnBegin_2Point() {
    TileMatrix tileMatrix = new TileMatrix();

    tileMatrix.placeStone(0, StoneColour.RED);
    System.out.println("Before test");
    tileMatrix.printMatrix();

    Integer points = tileMatrix.placeStone(0, StoneColour.YELLOW);
    System.out.println("After test");
    System.out.println("Points: " + points);
    tileMatrix.printMatrix();

    assertThat(points).isEqualTo(2);
  }

  @Test
  void placeStone_columnMid_3Point() {
    TileMatrix tileMatrix = new TileMatrix();

    tileMatrix.placeStone(0, StoneColour.RED);
    System.out.println("Before test");
    tileMatrix.printMatrix();

    Integer points = tileMatrix.placeStone(0, StoneColour.YELLOW);
    System.out.println("After test");
    System.out.println("Points: " + points);
    tileMatrix.printMatrix();

    assertThat(points).isEqualTo(2);
  }

  @Test
  void placeStone_columnMid_5Point() {
    TileMatrix tileMatrix = new TileMatrix();

    tileMatrix.placeStone(0, StoneColour.BLUE);
    tileMatrix.placeStone(0, StoneColour.YELLOW);
    tileMatrix.placeStone(0, StoneColour.WHITE);
    tileMatrix.placeStone(0, StoneColour.TURQUOISE);
    System.out.println("Before test");
    tileMatrix.printMatrix();

    Integer points = tileMatrix.placeStone(0, StoneColour.RED);
    System.out.println("After test");
    System.out.println("Points: " + points);
    tileMatrix.printMatrix();

    assertThat(points).isEqualTo(5);
  }


}
