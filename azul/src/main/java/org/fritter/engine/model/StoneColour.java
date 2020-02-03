package org.fritter.engine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StoneColour {

  RED("R"), BLUE("B"), YELLOW("Y"), WHITE("W"), TURQUOISE("T"), EMPTY("x");

  private String shortName;

}

