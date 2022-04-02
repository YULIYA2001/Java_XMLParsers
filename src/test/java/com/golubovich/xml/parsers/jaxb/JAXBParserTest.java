package com.golubovich.xml.parsers.jaxb;

import com.golubovich.xml.bean.ExtendedVisualParameters;
import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.bean.enums.Multiplying;
import com.golubovich.xml.bean.enums.Soil;
import com.golubovich.xml.utils.MyException;
import java.util.List;
import junit.framework.TestCase;

public class JAXBParserTest extends TestCase {

  public void testParse() {
    JAXBParser jaxbParser = new JAXBParser();
    List<Flower> flowers = null;

    try {
      flowers = jaxbParser.parse();
    } catch (MyException e) {
      e.printStackTrace();
    }

    assertNotNull(flowers);
    Flower flower = flowers.get(0);
    assertEquals(flowers.size(), 8);

    assertNotNull(flower);
    assertEquals(flower.getName(), "Хризантема кустовая Зембла белая");
    assertEquals(flower.getSoil(), Soil.PODZOLIC_SOIL);
    assertEquals(flower.getCountry(), "Нидерланды");
    assertEquals(flower.getImportDate().toString(), "2022-03-29");

    assertTrue(flower.getVisualParameters() instanceof ExtendedVisualParameters);
    assertNotNull(flower.getVisualParameters());
    assertEquals(flower.getVisualParameters().getStemColor(), "Темно-зеленый");
    assertEquals(flower.getVisualParameters().getLeafColor(), "Темно-зеленый");
    assertEquals(flower.getVisualParameters().getAverageSize(), 50);
    assertEquals(((ExtendedVisualParameters) flower.getVisualParameters()).getBudColor(), "Белый");

    assertNotNull(flower.getGrowingTips());
    assertEquals(flower.getGrowingTips().getTemperature(), 17);
    assertTrue(flower.getGrowingTips().isLighting());
    assertEquals(flower.getGrowingTips().getWatering(), 500);

    assertEquals(flower.getMultiplying().size(), 2);
    assertEquals(flower.getMultiplying().get(0), Multiplying.PETIOLE);
    assertEquals(flower.getMultiplying().get(1), Multiplying.SEED);
  }
}