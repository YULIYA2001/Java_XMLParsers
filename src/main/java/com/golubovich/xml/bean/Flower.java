package com.golubovich.xml.bean;

import com.golubovich.xml.bean.enums.Multiplying;
import com.golubovich.xml.bean.enums.Soil;
import java.time.LocalDate;
import java.util.ArrayList;

public class Flower {
  private int id;
  private String name;
  private Soil soil;
  private String country;
  private LocalDate importDate;
  private VisualParameters visualParameters;
  private GrowingTips growingTips;
  private ArrayList<Multiplying> multiplying;

  public Flower() {}

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

    ArrayList<String> _multiplying = new ArrayList<>();
    for (Multiplying _mlt: multiplying)
      _multiplying.add(_mlt.getName());

    return "Flower{\n" +
        "id=" + id +
        "\nname='" + name + '\'' +
        "\nsoil=" + soil.getName() +
        "\ncountry='" + country + '\'' +
        "\nimportDate=" + importDate +
        "\nvisualParameters=" + visualParameters +
        "\ngrowingTips=" + growingTips +
        "\nmultiplying=" + _multiplying +
        "\n}";
  }
}