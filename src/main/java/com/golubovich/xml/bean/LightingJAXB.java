package com.golubovich.xml.bean;

import static com.golubovich.xml.utils.Constants.ATTR_PHOTOPHILOUS;
import static com.golubovich.xml.utils.Constants.TAG_LIGHTNING;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = TAG_LIGHTNING)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {ATTR_PHOTOPHILOUS})
public class LightingJAXB {

  @XmlAttribute(name = ATTR_PHOTOPHILOUS)
  private String photophilous;

  public String getPhotophilous() {
    return photophilous;
  }

  public void setPhotophilous(String photophilous) {
    this.photophilous = photophilous;
  }
}
