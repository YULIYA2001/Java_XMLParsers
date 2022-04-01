package com.golubovich.xml.parsers.jaxb.adapters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

  @Override
  public LocalDate unmarshal(String s) throws Exception {
    return LocalDate.parse(
        s, DateTimeFormatter.ofPattern("yyyy-MM-dd")
    );
  }

  @Override
  public String marshal(LocalDate localDate) throws Exception {
    //TODO if need
    return null;
  }
}