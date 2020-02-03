package org.fritter.engine.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.fritter.engine.model.StoneColour;

@Getter
@AllArgsConstructor
public class TileField {

  private StoneColour colour;

  @Setter
  private boolean placed;

  TileField(StoneColour colour) {
    this.colour = colour;
    this.placed = false;
  }


}
