package model.parser.test.model.battery;

import lombok.Data;
import model.parser.test.model.IModel;

@Data
public abstract class AbstractBattery implements IBattery, IModel {

  private float voltageIn;
  private float voltageOut;


  public void execute(Object... parameter) {
    System.out.println("battery is running");
  }
}
