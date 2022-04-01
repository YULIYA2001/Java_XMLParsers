package com.golubovich.xml.bean;


import static com.golubovich.xml.utils.Constants.TAG_BUD_COLOR;
import static com.golubovich.xml.utils.Constants.TAG_EXTENDED_VISUAL_PARAMS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = TAG_EXTENDED_VISUAL_PARAMS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {TAG_BUD_COLOR})
public class ExtendedVisualParameters extends VisualParameters {

  @XmlElement(name = TAG_BUD_COLOR)
  private String budColor;

  public ExtendedVisualParameters() {
  }

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
    return "ExtendedVisualParameters1{" +
        "budColor='" + budColor + '\'' +
        "} " + super.toString();
  }
}
