package com.golubovich.xml.bean;

public class ExtendedVisualParameters extends VisualParameters{

  private String budColor;

  public ExtendedVisualParameters(String stemColor, String leafColor, int averageSize,
      String budColor) {
    super(stemColor, leafColor, averageSize);
    this.budColor = budColor;
  }

  public ExtendedVisualParameters(VisualParameters vParams) {
    super(vParams.getStemColor(), vParams.getLeafColor(), vParams.getAverageSize());
  }

  public String getBudColor() {
    return budColor;
  }

  public void setBudColor(String budColor) {
    this.budColor = budColor;
  }

  @Override
  public String toString() {
    return "ExtendedVisualParameters{" +
        "budColor='" + budColor + '\'' +
        "} " + super.toString();
  }
}
