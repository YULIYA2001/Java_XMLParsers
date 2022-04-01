package com.golubovich.xml.parsers.jaxb.adapters;

import static com.golubovich.xml.utils.Constants.YES;

import com.golubovich.xml.bean.LightingJAXB;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LightingAdapter extends XmlAdapter<LightingJAXB, Boolean> {

  @Override
  public Boolean unmarshal(LightingJAXB lighting) throws Exception {
    return YES.equals(lighting.getPhotophilous());
  }

  @Override
  public LightingJAXB marshal(Boolean aBoolean) throws Exception {
    //TODO if need
    return null;
  }
}
