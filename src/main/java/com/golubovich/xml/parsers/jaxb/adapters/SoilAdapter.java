package com.golubovich.xml.parsers.jaxb.adapters;

import com.golubovich.xml.bean.enums.Soil;
import com.golubovich.xml.parsers.ParseEnums;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SoilAdapter extends XmlAdapter<String, Soil> {

  @Override
  public Soil unmarshal(String s) throws Exception {
    ParseEnums parseEnums = new ParseEnums();
    return parseEnums.parseSoil(s);
  }

  @Override
  public String marshal(Soil soil) throws Exception {
    return soil.getName();
  }
}
