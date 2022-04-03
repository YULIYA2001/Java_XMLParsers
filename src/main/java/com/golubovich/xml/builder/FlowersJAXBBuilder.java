package com.golubovich.xml.builder;

import com.golubovich.xml.bean.ExtendedVisualParameters;
import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.bean.FlowersJAXB;
import com.golubovich.xml.bean.GrowingTips;
import com.golubovich.xml.bean.LightingJAXB;
import com.golubovich.xml.bean.VisualParameters;
import com.golubovich.xml.utils.MyException;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class FlowersJAXBBuilder extends AbstractFlowersBuilder {

  public FlowersJAXBBuilder() {
  }

  @Override
  public void buildListFlowers(String filePath) throws MyException {
    flowers = parse(filePath);
  }

  private List<Flower> parse(String filePath) throws MyException {

    FlowersJAXB flowers;
    try {
      JAXBContext context = JAXBContext.newInstance(FlowersJAXB.class, Flower.class,
          GrowingTips.class, LightingJAXB.class, VisualParameters.class,
          ExtendedVisualParameters.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      flowers = (FlowersJAXB) unmarshaller.unmarshal(new File(filePath));
    } catch (JAXBException e) {
      throw new MyException(e.getMessage());
    }

    return flowers.getFlowersList();
  }
}
