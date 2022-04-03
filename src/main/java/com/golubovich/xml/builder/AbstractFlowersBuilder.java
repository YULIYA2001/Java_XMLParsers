package com.golubovich.xml.builder;

import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.utils.MyException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractFlowersBuilder {

  protected List<Flower> flowers;

  protected AbstractFlowersBuilder() {
    flowers = new ArrayList<>();
  }

  public List<Flower> getFlowers() {
    return flowers;
  }

  public abstract void buildListFlowers(String filePath) throws MyException;
}
