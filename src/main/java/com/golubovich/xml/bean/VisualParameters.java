package com.golubovich.xml.bean;

public class VisualParameters {
  private String stemColor;
  private String leafColor;
  private int averageSize;

  public VisualParameters(String stemColor, String leafColor, int averageSize) {
    this.stemColor = stemColor;
    this.leafColor = leafColor;
    this.averageSize = averageSize;
  }

  public String getStemColor() {
    return stemColor;
  }

  public String getLeafColor() {
    return leafColor;
  }

  public int getAverageSize() {
    return averageSize;
  }

  public void setStemColor(String stemColor) {
    this.stemColor = stemColor;
  }

  public void setLeafColor(String leafColor) {
    this.leafColor = leafColor;
  }

  public void setAverageSize(int averageSize) {
    this.averageSize = averageSize;
  }

  @Override
  public String toString() {
    return "VisualParameters{" +
        "stemColor='" + stemColor + '\'' +
        ", leafColor='" + leafColor + '\'' +
        ", averageSize=" + averageSize +
        '}';
  }
}
