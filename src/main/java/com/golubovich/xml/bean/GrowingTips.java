package com.golubovich.xml.bean;

public class GrowingTips {
  private int temperature;
  private boolean photophilous;
  private int watering;

  public GrowingTips() {}

  public GrowingTips(int temperature, boolean photophilous, int watering) {
    this.temperature = temperature;
    this.photophilous = photophilous;
    this.watering = watering;
  }

  public int getTemperature() {
    return temperature;
  }

  public boolean isPhotophilous() {
    return photophilous;
  }

  public int getWatering() {
    return watering;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public void setPhotophilous(boolean photophilous) {
    this.photophilous = photophilous;
  }

  public void setWatering(int watering) {
    this.watering = watering;
  }

  @Override
  public String toString() {
    return "GrowingTips{" +
        "temperature=" + temperature +
        ", photophilous=" + photophilous +
        ", watering=" + watering +
        '}';
  }
}
