package com.golubovich.xml.bean;

import static com.golubovich.xml.utils.Constants.TAG_FLOWER;
import static com.golubovich.xml.utils.Constants.TAG_FLOWERS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = TAG_FLOWERS)
@XmlAccessorType(XmlAccessType.FIELD)
public class FlowersJAXB {

  @XmlElement(name = TAG_FLOWER)
  private List<Flower> flowersList = new ArrayList<>();

  public List<Flower> getFlowersList() {
    return Collections.unmodifiableList(flowersList);
  }

  public void setFlowersList(List<Flower> flowersList) {
    this.flowersList = flowersList;
  }
}
