package com.golubovich.xml.builder;

import static com.golubovich.xml.utils.Constants.*;

import com.golubovich.xml.bean.ExtendedVisualParameters;
import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.bean.GrowingTips;
import com.golubovich.xml.bean.VisualParameters;
import com.golubovich.xml.parsers.ParseEnums;
import com.golubovich.xml.utils.MyException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class FlowersStAXBuilder extends AbstractFlowersBuilder {

  ParseEnums parseEnums = new ParseEnums();
  List<Flower> flowersList;
  Flower flower;
  GrowingTips growingTips;
  VisualParameters visualParams;

  public FlowersStAXBuilder() {
  }

  @Override
  public void buildListFlowers(String filePath) throws MyException {
    flowers = parse(filePath);
  }

  private List<Flower> parse(String filePath) throws MyException {
    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    XMLEventReader reader;
    try {
      reader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));
    } catch (XMLStreamException | FileNotFoundException e) {
      throw new MyException(e.getMessage());
    }

    flowersList = new ArrayList<>();
    return parseFlowersList(reader);
  }

  private List<Flower> parseFlowersList(XMLEventReader reader) throws MyException {

    while (reader.hasNext()) {
      XMLEvent xmlEvent;
      try {
        xmlEvent = reader.nextEvent();
      } catch (XMLStreamException e) {
        throw new MyException(e.getMessage());
      }

      if (xmlEvent.isStartElement()) {
        startElement(xmlEvent, reader);
      } else if (xmlEvent.isEndElement()) {
        endElement(xmlEvent);
      }
    }

    return flowersList;
  }

  private void endElement(XMLEvent xmlEvent) throws MyException {
    EndElement endElement = xmlEvent.asEndElement();

    if (endElement.getName().getLocalPart() == null
        || endElement.getName().getLocalPart().isEmpty()) {
      throw new MyException("Error smth wrong. Tag without name?");
    }

    if (TAG_FLOWER.equals(endElement.getName().getLocalPart())) {
      flower.setVisualParameters(visualParams);
      flower.setGrowingTips(growingTips);
      flowersList.add(flower);
    }
  }

  private void startElement(XMLEvent xmlEvent, XMLEventReader reader) throws MyException {
    StartElement startElement = xmlEvent.asStartElement();

    try {
      switch (startElement.getName().getLocalPart()) {
        case TAG_FLOWER:
          startFlowerElement(startElement);
          break;

        case TAG_NAME:
          xmlEvent = reader.nextEvent();
          flower.setName(xmlEvent.asCharacters().getData());
          break;

        case TAG_SOIL:
          xmlEvent = reader.nextEvent();
          flower.setSoil(
              parseEnums.parseSoil(xmlEvent.asCharacters().getData())
          );
          break;

        case TAG_MULTIPLYING:
          xmlEvent = reader.nextEvent();
          flower.setMultiplying(
              parseEnums.parseMultiplying(xmlEvent.asCharacters().getData())
          );
          break;

        case TAG_COUNTRY:
          xmlEvent = reader.nextEvent();
          flower.setCountry(xmlEvent.asCharacters().getData());
          break;

        case TAG_IMPORT_DATE:
          xmlEvent = reader.nextEvent();
          flower.setImportDate(
              LocalDate.parse(
                  xmlEvent.asCharacters().getData(),
                  DateTimeFormatter.ofPattern("yyyy-MM-dd")
              )
          );
          break;

        case TAG_GROWING_TIPS:
          growingTips = new GrowingTips();
          break;

        case TAG_LIGHTNING:
          startLightningTag(startElement);
          break;

        case TAG_TEMPERATURE:
          xmlEvent = reader.nextEvent();
          growingTips.setTemperature(Integer.parseInt(xmlEvent.asCharacters().getData()));
          break;

        case TAG_WATERING:
          xmlEvent = reader.nextEvent();
          growingTips.setWatering(Integer.parseInt(xmlEvent.asCharacters().getData()));
          break;

        case TAG_VISUAL_PARAMS:
          visualParams = new VisualParameters();
          break;

        case TAG_EXTENDED_VISUAL_PARAMS:
          visualParams = new ExtendedVisualParameters();
          break;

        case TAG_STEM_COLOR:
          xmlEvent = reader.nextEvent();
          visualParams.setStemColor(xmlEvent.asCharacters().getData());
          break;

        case TAG_LEAF_COLOR:
          xmlEvent = reader.nextEvent();
          visualParams.setLeafColor(xmlEvent.asCharacters().getData());
          break;

        case TAG_AVERAGE_SIZE:
          xmlEvent = reader.nextEvent();
          visualParams.setAverageSize(Integer.parseInt(
              xmlEvent.asCharacters().getData()
          ));
          break;

        case TAG_BUD_COLOR:
          xmlEvent = reader.nextEvent();
          ((ExtendedVisualParameters) visualParams).setBudColor(
              xmlEvent.asCharacters().getData()
          );
          break;
      }
    } catch (XMLStreamException e) {
      throw new MyException(e.getMessage());
    }
  }

  private void startLightningTag(StartElement startElement) {
    Attribute photophilousAttr = startElement.getAttributeByName(new QName(ATTR_PHOTOPHILOUS));
    if (photophilousAttr != null) {
      growingTips.setLighting(YES.equals(photophilousAttr.getValue()));
    }
  }

  private void startFlowerElement(StartElement startElement) {
    flower = new Flower();
    Attribute idAttr = startElement.getAttributeByName(new QName(ATTR_ID));
    if (idAttr != null) {
      flower.setId(Integer.parseInt(idAttr.getValue().substring(1)));
    }
  }
}
