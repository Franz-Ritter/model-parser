package org.fritter.engine.board;

import java.util.stream.Stream;
import org.fritter.engine.model.StoneColour;

public class TileFillerBoard {

  TileFillerRow[] tileFillerRows = new TileFillerRow[5];

  TileFillerBoard() {
    for (int i = 0; i < tileFillerRows.length; i++) {
      tileFillerRows[i] = new TileFillerRow(i+1);
    }
  }

  public Integer placeStones(Integer rowNumber, StoneColour colour, final Integer number) {
    return tileFillerRows[rowNumber].placeStones(colour, number);
  }

  public void printFiller(){
    Stream.of(tileFillerRows).map(TileFillerRow::toString).forEach(System.out::println);
  }

  StoneColour emptyRow(Integer rowNumber){
    TileFillerRow currentRow = tileFillerRows[rowNumber];
    return currentRow.isFull() ? currentRow.emptyRow() : StoneColour.EMPTY;
  }


}
