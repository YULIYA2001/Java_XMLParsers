package com.golubovich.xml.builder;

import com.golubovich.xml.parsers.StAXParser;

public class StAXParserBuilder implements Builder {

//  private SomeType something;
//
//  @Override
//  public void setSomething(SomeType something) {
//    this.something = something;
//  }

  public StAXParser getResult() {
    return new StAXParser();
  }
}
