package model.parser.test.parser.controller;


import java.util.List;
import model.parser.test.parser.client.ClassInformationDto;
import model.parser.test.parser.parser.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ParserController {

  private final ModelParser modelParser;

  @Autowired
  public ParserController(ModelParser modelParser) {
    this.modelParser = modelParser;
  }

  @GetMapping("/batteries")
  List<ClassInformationDto> getBatteries() {
    return modelParser.parseBatteries();
  }

  @GetMapping("/solarPanels")
  List<ClassInformationDto> getSolarPanels() {
    return modelParser.parseSolarPanels();
  }
}
