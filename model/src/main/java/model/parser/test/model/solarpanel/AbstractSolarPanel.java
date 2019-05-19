package model.parser.test.model.solarpanel;

import lombok.Getter;
import lombok.Setter;
import model.parser.test.model.IModel;

@Getter
@Setter
public abstract class AbstractSolarPanel implements ISolarPanel, IModel {

  int panelNumber;

  public void execute(Object... parameter) {
    System.out.println("solar panel is running");
  }
}
