package com.golubovich.xml.parsers.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IdAdapter extends XmlAdapter<String, Integer> {

  @Override
  public Integer unmarshal(String s) throws Exception {
    return Integer.parseInt(s.substring(1));
  }

  @Override
  public String marshal(Integer i) throws Exception {
    return ("_" + i);
  }
}
