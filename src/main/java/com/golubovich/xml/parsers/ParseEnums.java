package com.golubovich.xml.parsers;

import com.golubovich.xml.bean.enums.Multiplying;
import com.golubovich.xml.bean.enums.Soil;
import java.util.ArrayList;
import java.util.Arrays;

public class ParseEnums {

  public Soil parseSoil(String soil) {
    for (Soil el : Soil.values()) {
      if (el.getName().equals(soil)) {
        return el;
      }
    }
    return null;
  }

  public ArrayList<Multiplying> parseMultiplying(String multiplyingStr) {
    ArrayList<String> mltStrList = new ArrayList(Arrays.asList(
        (multiplyingStr).split("\\s")
    ));

    ArrayList<Multiplying> mltList = new ArrayList<>();

    for (String mltEl : mltStrList) {
      for (Multiplying el : Multiplying.values()) {
        if (el.getName().equals(mltEl)) {
          mltList.add(el);
        }
      }
    }

    return mltList;
  }
}
