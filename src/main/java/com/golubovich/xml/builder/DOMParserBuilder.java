package com.golubovich.xml.builder;

import com.golubovich.xml.parsers.DOMParser;

public class DOMParserBuilder implements Builder {

//  private SomeType something;
//
//  @Override
//  public void setSomething(SomeType something) {
//    this.something = something;
//  }

  public DOMParser getResult() {
    return new DOMParser();
  }
}
