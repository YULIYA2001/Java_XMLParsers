package com.golubovich.xml.bean.enums;

public enum Soil{
  topsoil("Грунтовая"),
  podzolicSoil("Подзолистая"),
  sodPodzolicSoil("Дерново-подзолистая");

  private final String name;

  public String getName() {
    return this.name;
  }

  Soil(String name) {
    this.name = name;
  }
}
