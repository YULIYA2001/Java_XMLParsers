package com.golubovich.xml.bean.enums;

public enum Multiplying {
  seed("Семена"),
  leaf("Листья"),
  petiole("Черенки");

  private final String name;

  public String getName() {
    return this.name;
  }

  Multiplying(String name) {
    this.name = name;
  }
}
