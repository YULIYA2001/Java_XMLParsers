package com.golubovich.xml.builder;

import static com.golubovich.xml.utils.Constants.FILE_PATH;

import com.golubovich.xml.bean.ExtendedVisualParameters;
import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.bean.enums.Multiplying;
import com.golubovich.xml.bean.enums.Soil;
import com.golubovich.xml.utils.MyException;
import java.util.List;
import junit.framework.TestCase;

public class FlowersSAXBuilderTest extends TestCase {

  public void testBuildListFlowers() throws MyException {
    List<Flower> flowers;

    try {
      AbstractFlowersBuilder builder = FlowerBuilderFactory.createFlowerBuilder("sax");
      builder.buildListFlowers("bla-bla.xml");
    } catch (MyException e) {
      assertNotSame("", e.getMessage());
    }

    AbstractFlowersBuilder builder = FlowerBuilderFactory.createFlowerBuilder("sax");
    builder.buildListFlowers(FILE_PATH);
    flowers = builder.getFlowers();

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