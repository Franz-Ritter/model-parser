package org.fritter.engine.board;

import java.util.HashMap;
import java.util.Map;

public final class PlayerBoardUtil {

  private static Map<Integer, Integer> scoringMap = iniMap();

  private PlayerBoardUtil() {

  }
  private static Map<Integer, Integer> iniMap() {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(1,-1);
    map.put(2 ,-2);
    map.put(3 ,- 4);
    map.put(4,-6);
    map.put(5,-8);
    map.put(6,-11);
    map.put(7,-14);
    map.put(8,-17);
    map.put(9,-20);
    map.put(10,-23);
    return iniMap();
  }

  public static Integer getNegativePoints(Integer numberOfStones) {
    return scoringMap.get(numberOfStones);
  }

}
