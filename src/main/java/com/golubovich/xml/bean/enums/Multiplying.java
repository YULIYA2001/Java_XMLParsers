package com.golubovich.xml.bean.enums;

public enum Multiplying {
  SEED("Семена"),
  LEAF("Листья"),
  PETIOLE("Черенки");

  private final String name;

  public String getName() {
    return this.name;
  }

  Multiplying(String name) {
    this.name = name;
  }
}
