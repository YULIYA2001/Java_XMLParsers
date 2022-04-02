package com.golubovich.xml.builder;

import com.golubovich.xml.parsers.sax.SAXMyParser;

public class SAXParserBuilder implements Builder {

//  private SomeType something;
//
//  @Override
//  public void setSomething(SomeType something) {
//    this.something = something;
//  }

  public SAXMyParser getResult() {
    return new SAXMyParser();
  }
}
