package model.parser.test.parser.parser;

import com.github.reinert.jjschema.v1.JsonSchemaFactory;
import com.github.reinert.jjschema.v1.JsonSchemaV4Factory;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.parser.test.parser.client.ClassInformationDto;
import model.parser.test.parser.client.FieldInformationDto;
import org.springframework.stereotype.Service;

@Service
public class ModelParser {

  private final JsonSchemaFactory schemaFactory;

  ModelParser() {
    JsonSchemaFactory schemaFactory = new JsonSchemaV4Factory();
    schemaFactory.setAutoPutDollarSchema(true);
    this.schemaFactory = schemaFactory;
  }


  public List<ClassInformationDto> parseBatteries() {
    return parseModel("battery");
  }

  public List<ClassInformationDto> parseSolarPanels() {
    return parseModel("solarpanel");
  }

  private List<ClassInformationDto> parseModel(final String subPackage) {
    try {
      final ClassPath classPath = ClassPath.from(getClass().getClassLoader());
      Set<ClassInfo> info = classPath.getTopLevelClassesRecursive(String.format("model.parser.test.model.%s.impl", subPackage));
      List<ClassInformationDto> schemas = info.stream()
          .map(ClassInfo::getName)
          .map(this::loadClass)
          .map(this::createClassInformation)
          .collect(Collectors.toList());
      return schemas;

    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

  }

  Class loadClass(String name) {
    try {
      return getClass().getClassLoader().loadClass(name);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private ClassInformationDto createClassInformation(Class clazz) {

    final List<FieldInformationDto> fieldInformationDto = getFields(clazz).stream()
        .map(field -> new FieldInformationDto(field.getName(), field.getType().getName()))
        .collect(Collectors.toList());
    return new ClassInformationDto(clazz.getName(), clazz.getSimpleName(), fieldInformationDto);

  }

  private List<Field> getFields(Class clazz) {
    if (clazz.equals(Object.class)) {
      return Collections.emptyList();
    } else {
      List<Field> superFields = getFields(clazz.getSuperclass());
      return Stream.of(Arrays.asList(clazz.getDeclaredFields()), superFields)
          .flatMap(List::stream)
          .collect(Collectors.toList());
    }
  }

}
