package com.golubovich.xml.bean.enums;

public enum Soil {
  TOPSOIL("Грунтовая"),
  PODZOLIC_SOIL("Подзолистая"),
  SOD_PODZOLIC_SOIL("Дерново-подзолистая");

  private final String name;

  public String getName() {
    return this.name;
  }

  Soil(String name) {
    this.name = name;
  }
}
