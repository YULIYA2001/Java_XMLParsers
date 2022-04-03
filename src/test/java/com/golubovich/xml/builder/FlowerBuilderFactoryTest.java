package com.golubovich.xml.builder;

import com.golubovich.xml.utils.MyException;
import junit.framework.TestCase;

public class FlowerBuilderFactoryTest extends TestCase {

  public void testCreateFlowerBuilder() {
    try {
      AbstractFlowersBuilder builder = FlowerBuilderFactory.createFlowerBuilder("bla-bla");
    } catch (MyException e) {
      assertEquals("No such enum constant", e.getMessage());
    }
  }
}