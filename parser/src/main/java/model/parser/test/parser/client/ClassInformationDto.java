package model.parser.test.parser.client;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassInformationDto {

  private String className;
  private String simpleName;

  private List<FieldInformationDto> fields;
}
