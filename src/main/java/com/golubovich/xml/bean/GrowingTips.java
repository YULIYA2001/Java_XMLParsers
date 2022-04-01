package com.golubovich.xml.bean;

import static com.golubovich.xml.utils.Constants.TAG_GROWING_TIPS;
import static com.golubovich.xml.utils.Constants.TAG_LIGHTNING;
import static com.golubovich.xml.utils.Constants.TAG_TEMPERATURE;
import static com.golubovich.xml.utils.Constants.TAG_WATERING;

import com.golubovich.xml.parsers.jaxb.adapters.LightingAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = TAG_GROWING_TIPS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {TAG_TEMPERATURE, TAG_LIGHTNING, TAG_WATERING})
public class GrowingTips {

  @XmlElement(name = TAG_TEMPERATURE)
  private int temperature;

  @XmlElement(name = TAG_LIGHTNING)
  @XmlJavaTypeAdapter(LightingAdapter.class)
  private Boolean lighting;

  @XmlElement(name = TAG_WATERING)
  private int watering;

  public GrowingTips() {
  }

  public GrowingTips(int temperature, boolean lighting, int watering) {
    this.temperature = temperature;
    this.lighting = lighting;
    this.watering = watering;
  }

  public int getTemperature() {
    return temperature;
  }

  public boolean isLighting() {
    return lighting;
  }

  public int getWatering() {
    return watering;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public void setLighting(boolean lighting) {
    this.lighting = lighting;
  }

  public void setWatering(int watering) {
    this.watering = watering;
  }

  @Override
  public String toString() {
    return "GrowingTips1{" +
        "temperature=" + temperature +
        ", lighting=" + lighting +
        ", watering=" + watering +
        '}';
  }
}
