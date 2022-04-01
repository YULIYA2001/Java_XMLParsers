package com.golubovich.xml.bean;


import static com.golubovich.xml.utils.Constants.TAG_AVERAGE_SIZE;
import static com.golubovich.xml.utils.Constants.TAG_LEAF_COLOR;
import static com.golubovich.xml.utils.Constants.TAG_STEM_COLOR;
import static com.golubovich.xml.utils.Constants.TAG_VISUAL_PARAMS;

import com.golubovich.xml.bean.ExtendedVisualParameters;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlSeeAlso({ExtendedVisualParameters.class})
@XmlRootElement(name = TAG_VISUAL_PARAMS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {TAG_STEM_COLOR, TAG_LEAF_COLOR, TAG_AVERAGE_SIZE})
public class VisualParameters {

  @XmlElement(name = TAG_STEM_COLOR)
  private String stemColor;

  @XmlElement(name = TAG_LEAF_COLOR)
  private String leafColor;

  @XmlElement(name = TAG_AVERAGE_SIZE)
  private int averageSize;

  public VisualParameters() {
  }

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
    return "VisualParameters1{" +
        "stemColor='" + stemColor + '\'' +
        ", leafColor='" + leafColor + '\'' +
        ", averageSize=" + averageSize +
        '}';
  }
}
