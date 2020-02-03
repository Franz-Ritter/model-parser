package org.fritter.engine.board;

import java.security.InvalidParameterException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import org.fritter.engine.model.StoneColour;
import org.springframework.jmx.access.InvalidInvocationException;


public class TileFillerRow {

  @Getter
  private StoneColour currentColour;
  private Integer currentPlacedStones;
  private final Integer size;

  TileFillerRow(Integer size) {
    this.size = size;
    reset();
  }

  StoneColour emptyRow() {
    if (!isFull()) {
      throw new InvalidInvocationException(String
          .format("You can not empty a started row. Size: %d, placed: %d, colour: %s",
              size, currentPlacedStones, currentColour));
    }
    StoneColour colour = currentColour;
    reset();
    return currentColour;
  }

  boolean isFull() {
    return size.equals(currentPlacedStones);
  }

  Integer spaceLeft() {
    return size - currentPlacedStones;
  }

  Integer placeStones(StoneColour stoneColour, Integer numberOfStones) {
    if (!currentColour.equals(StoneColour.EMPTY) && !stoneColour.equals(currentColour)) {
      throw new InvalidParameterException(
          String.format("Invalid colour %s. Filler %d has already colour %s. ", stoneColour, size, currentColour));
    }
    currentColour = stoneColour;
    Integer totalStones = currentPlacedStones + numberOfStones;
    currentPlacedStones = totalStones >= size ? size : currentPlacedStones + numberOfStones;
    return (totalStones - size);
  }

  void reset() {
    currentPlacedStones = 0;
    currentColour = StoneColour.EMPTY;
  }

  @Override
  public String toString() {
    String placed = IntStream.range(0, currentPlacedStones)
        .mapToObj(i -> currentColour.getShortName())
        .collect(Collectors.joining(" - "));
    String notPlaced = IntStream.rangeClosed(currentPlacedStones + 1, size)
        .mapToObj(i -> StoneColour.EMPTY.getShortName())
        .collect(Collectors.joining(" - "));
    return notPlaced.isEmpty() || placed.isEmpty() ?
        "|" + placed + notPlaced + "|" :
        "|" + notPlaced + " - " + placed + "|";
  }


}
