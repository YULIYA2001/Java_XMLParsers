package com.golubovich.xml.parsers.jaxb.adapters;

import com.golubovich.xml.bean.enums.Multiplying;
import com.golubovich.xml.parsers.ParseEnums;
import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MultiplyingAdapter extends XmlAdapter<String, ArrayList<Multiplying>> {

  @Override
  public ArrayList<Multiplying> unmarshal(String s) throws Exception {
    ParseEnums parseEnums = new ParseEnums();
    return parseEnums.parseMultiplying(s);
  }

  @Override
  public String marshal(ArrayList<Multiplying> multiplying) throws Exception {
    // TODO если надо
    return null;
  }
}
