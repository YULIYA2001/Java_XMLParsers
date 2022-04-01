package com.golubovich.xml.bean;

import static com.golubovich.xml.utils.Constants.*;

import com.golubovich.xml.bean.enums.Multiplying;
import com.golubovich.xml.bean.enums.Soil;
import com.golubovich.xml.parsers.jaxb.adapters.DateAdapter;
import com.golubovich.xml.parsers.jaxb.adapters.IdAdapter;
import com.golubovich.xml.parsers.jaxb.adapters.MultiplyingAdapter;
import com.golubovich.xml.parsers.jaxb.adapters.SoilAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = TAG_FLOWER)
@XmlAccessorType(XmlAccessType.FIELD)
public class Flower {

  @XmlAttribute(name = ATTR_ID)
  @XmlJavaTypeAdapter(IdAdapter.class)
  private Integer id;

  @XmlElement(name = TAG_NAME)
  private String name;

  @XmlElement(name = TAG_SOIL)
  @XmlJavaTypeAdapter(SoilAdapter.class)
  private Soil soil;

  @XmlElement(name = TAG_COUNTRY)
  private String country;

  @XmlElement(name = TAG_IMPORT_DATE)
  @XmlJavaTypeAdapter(DateAdapter.class)
  private LocalDate importDate;

  @XmlElements({
      @XmlElement(name = "visualParameters", type = VisualParameters.class),
      @XmlElement(name = "extendedVisualParameters", type = ExtendedVisualParameters.class)
  })
  private VisualParameters visualParameters;

  @XmlElement(name = TAG_GROWING_TIPS)
  private GrowingTips growingTips;

  @XmlJavaTypeAdapter(MultiplyingAdapter.class)
  private ArrayList<Multiplying> multiplying;

  public Flower() {
  }

  public Flower(int id, String name, Soil soil, String country, LocalDate importDate,
      VisualParameters visualParameters, GrowingTips growingTips,
      ArrayList<Multiplying> multiplying) {
    this.id = id;
    this.name = name;
    this.soil = soil;
    this.country = country;
    this.importDate = importDate;
    this.visualParameters = visualParameters;
    this.growingTips = growingTips;
    this.multiplying = multiplying;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSoil(Soil soil) {
    this.soil = soil;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setImportDate(LocalDate importDate) {
    this.importDate = importDate;
  }

  public void setVisualParameters(VisualParameters visualParameters) {
    this.visualParameters = visualParameters;
  }

  public void setGrowingTips(GrowingTips growingTips) {
    this.growingTips = growingTips;
  }

  public void setMultiplying(ArrayList<Multiplying> multiplying) {
    this.multiplying = multiplying;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Soil getSoil() {
    return soil;
  }

  public String getCountry() {
    return country;
  }

  public LocalDate getImportDate() {
    return importDate;
  }

  public VisualParameters getVisualParameters() {
    return visualParameters;
  }

  public GrowingTips getGrowingTips() {
    return growingTips;
  }

  public ArrayList<Multiplying> getMultiplying() {
    return multiplying;
  }

  @Override
  public String toString() {

    ArrayList<String> str_multiplying = new ArrayList<>();
    for (Multiplying _mlt : multiplying) {
      str_multiplying.add(_mlt.getName());
    }

    return "Flower{ " +
        "id=" + id +
        ", name='" + name + '\'' +
        ", soil=" + soil.getName() +
        ", country='" + country + '\'' +
        ", importDate=" + importDate +
        ", visualParameters=" + visualParameters +
        ", growingTips=" + growingTips +
        ", multiplying=" + str_multiplying +
        " }";
  }
}