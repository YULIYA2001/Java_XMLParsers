package com.golubovich.xml.parsers.saxparser;

import static com.golubovich.xml.utils.Constants.*;

import com.golubovich.xml.bean.ExtendedVisualParameters;
import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.bean.GrowingTips;
import com.golubovich.xml.bean.VisualParameters;
import com.golubovich.xml.parsers.ParseEnums;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserHandler extends DefaultHandler {

  private ParseEnums parseEnums;

  private String currentTagName;

  private List<Flower> flowersList;
  private Flower flower;
  private GrowingTips growingTips;
  private VisualParameters visualParams;

  private boolean isFlower = false;
  private boolean isOrigin = false;
  private boolean isVisualParams = false;
  private boolean isExtendedVisualParams = false;
  private boolean isGrowingTips = false;


  public List<Flower> getFlowersList() {
    return flowersList;
  }

  @Override
  public void startDocument() {
    flowersList = new ArrayList<>();
    parseEnums = new ParseEnums();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    currentTagName = qName;

    if (currentTagName != null) {
      switch (currentTagName) {
        case TAG_FLOWER -> {
          isFlower = true;
          flower = new Flower();
          flower.setId(Integer.parseInt(
              attributes.getValue(0).substring(1)      // in xml id="_number"
          ));
        }

        case TAG_ORIGIN -> isOrigin = true;

        case TAG_VISUAL_PARAMS -> {
          isVisualParams = true;
          visualParams = new VisualParameters();
        }

        case TAG_EXTENDED_VISUAL_PARAMS -> {
          isVisualParams = true;
          isExtendedVisualParams = true;
          visualParams = new ExtendedVisualParameters();
        }

        case TAG_GROWING_TIPS -> {
          isGrowingTips = true;
          growingTips = new GrowingTips();
        }

        case TAG_LIGHTNING -> growingTips.setPhotophilous(
            YES.equals(attributes.getValue(0))
        );
      }
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    switch (qName) {

      case TAG_FLOWER -> {
        flowersList.add(flower);
        isFlower = false;
      }

      case TAG_ORIGIN -> isOrigin = false;

      case TAG_VISUAL_PARAMS -> {
        isVisualParams = false;
        flower.setVisualParameters(visualParams);
      }

      case TAG_EXTENDED_VISUAL_PARAMS -> {
        isVisualParams = false;
        isExtendedVisualParams = false;
        flower.setVisualParameters(visualParams);
      }

      case TAG_GROWING_TIPS -> {
        isGrowingTips = false;
        flower.setGrowingTips(growingTips);
      }
    }

    currentTagName = null;
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (currentTagName == null) {
      return;
    }

    if (isFlower) {
      switch (currentTagName) {
        case TAG_NAME -> flower.setName(new String(ch, start, length));
        case TAG_SOIL -> flower.setSoil(
            parseEnums.parseSoil(new String(ch, start, length))
        );
        case TAG_MULTIPLYING -> flower.setMultiplying(
            parseEnums.parseMultiplying(new String(ch, start, length))
        );
      }

      if (isOrigin) {
        switch (currentTagName) {
          case TAG_COUNTRY -> flower.setCountry(new String(ch, start, length));
          case TAG_IMPORT_DATE -> flower.setImportDate(
              LocalDate.parse(
                  new String(ch, start, length),
                  DateTimeFormatter.ofPattern("yyyy-MM-dd")
              )
          );
        }
      } else if (isGrowingTips) {
        switch (currentTagName) {
          case TAG_TEMPERATURE -> growingTips.setTemperature(
              Integer.parseInt(new String(ch, start, length))
          );
          case TAG_WATERING -> growingTips.setWatering(
              Integer.parseInt(new String(ch, start, length))
          );
        }
      } else if (isVisualParams) {
        switch (currentTagName) {
          case TAG_STEM_COLOR -> visualParams.setStemColor(new String(ch, start, length));
          case TAG_LEAF_COLOR -> visualParams.setLeafColor(new String(ch, start, length));
          case TAG_AVERAGE_SIZE -> visualParams.setAverageSize(
              Integer.parseInt(new String(ch, start, length))
          );
        }

        if (isExtendedVisualParams && TAG_BUD_COLOR.equals(currentTagName)) {
          parseExtendedVisualParams(new String(ch, start, length));
        }

      }
    }
  }

  private void parseExtendedVisualParams(String budColor) {
    ExtendedVisualParameters ev = (ExtendedVisualParameters) visualParams;
    ev.setBudColor(budColor);
    visualParams = ev;
  }
}
