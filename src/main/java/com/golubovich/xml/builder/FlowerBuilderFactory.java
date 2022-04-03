package com.golubovich.xml.builder;

import com.golubovich.xml.utils.MyException;

public class FlowerBuilderFactory {

  private enum TypeParser {
    DOM, SAX, STAX, JAXB
  }

  private FlowerBuilderFactory() {
  }

  public static AbstractFlowersBuilder createFlowerBuilder(String typeParser) throws MyException {

    TypeParser type;

    try {
      type = TypeParser.valueOf(typeParser.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new MyException("No such enum constant");
    }

    switch (type) {
      case DOM:
        return new FlowersDOMBuilder();
      case SAX:
        return new FlowersSAXBuilder();
      case STAX:
        return new FlowersStAXBuilder();
      case JAXB:
        return new FlowersJAXBBuilder();
      default:
        throw new MyException("No such enum constant");
    }
  }
}
