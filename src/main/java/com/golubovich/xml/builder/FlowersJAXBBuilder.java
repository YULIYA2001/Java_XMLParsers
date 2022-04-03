package com.golubovich.xml.builder;

import com.golubovich.xml.parsers.jaxb.JAXBParser;

public class JAXBParserBuilder implements Builder {

//  private SomeType something;
//
//  @Override
//  public void setSomething(SomeType something) {
//    this.something = something;
//  }

  public JAXBParser getResult() {
    return new JAXBParser();
  }
}
