package com.golubovich.xml.parsers;

import static com.golubovich.xml.utils.Constants.*;

import com.golubovich.xml.utils.MyException;
import com.golubovich.xml.bean.ExtendedVisualParameters;
import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.bean.GrowingTips;
import com.golubovich.xml.bean.VisualParameters;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {

  private ParseEnums parseEnums;

  public List<Flower> parse() throws MyException {
    parseEnums = new ParseEnums();

    Document doc;
    try {
      doc = buildDocument();
    } catch (ParserConfigurationException | IOException | SAXException e) {
      throw new MyException("Open parsing error " + e.getMessage());
    }

    NodeList flowersNodes = doc.getFirstChild().getChildNodes();
    List<Flower> flowersList;
    try {
      flowersList = parseFlowersList(flowersNodes);
    } catch (MyException e) {
      throw new MyException(e.getMessage());
    }

    return flowersList;
  }

  private Document buildDocument()
      throws ParserConfigurationException, IOException, SAXException {
    File file = new File(FILE_PATH);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    return dbf.newDocumentBuilder().parse(file);
  }

  private List<Flower> parseFlowersList(NodeList flowersNodes) throws MyException {

    ArrayList<Flower> flowersList = new ArrayList();

    for (int i = 0; i < flowersNodes.getLength(); i++) {
      if (flowersNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }

      int flowerId;
      try {
        flowerId = Integer.parseInt(
            flowersNodes.item(i).getAttributes().item(0).getTextContent().substring(1)
        );
      } catch (NumberFormatException e) {
        throw new MyException("Fail parse flower id. " + e.getMessage());
      }

      NodeList flowerNode = flowersNodes.item(i).getChildNodes();
      Flower flower = parseFlower(flowerNode, flowerId);
      flowersList.add(flower);
    }
    return flowersList;
  }

  private Flower parseFlower(NodeList flowerNode, int flowerId) throws MyException {

    Flower flower = new Flower();
    flower.setId(flowerId);

    for (int j = 0; j < flowerNode.getLength(); j++) {
      if (flowerNode.item(j).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }

      if (flowerNode.item(j).getNodeName() == null) {
        throw new MyException("error smth wrong: childNode is null");
      }

      switch (flowerNode.item(j).getNodeName()) {

        case TAG_NAME:
          flower.setName(flowerNode.item(j).getTextContent());
          break;

        case TAG_SOIL:
          flower.setSoil(
              parseEnums.parseSoil(flowerNode.item(j).getTextContent())
          );
          break;

        case TAG_MULTIPLYING:
          flower.setMultiplying(
              parseEnums.parseMultiplying(flowerNode.item(j).getTextContent())
          );
          break;

//        case TAG_ORIGIN:
//          NodeList origin = flowerNode.item(j).getChildNodes();
//          flower.setCountry(origin.item(1).getTextContent());
//          flower.setImportDate(LocalDate.parse(
//              origin.item(3).getTextContent(),
//              DateTimeFormatter.ofPattern("yyyy-MM-dd")
//          ));
//          break;
        case TAG_COUNTRY:
          flower.setCountry(flowerNode.item(j).getTextContent());
          break;

        case TAG_IMPORT_DATE:
          flower.setImportDate(LocalDate.parse(
              flowerNode.item(j).getTextContent(),
              DateTimeFormatter.ofPattern("yyyy-MM-dd")));
          break;

        case TAG_VISUAL_PARAMS:
          flower.setVisualParameters(
              parseVisualParameters(flowerNode.item(j).getChildNodes())
          );
          break;

        case TAG_EXTENDED_VISUAL_PARAMS:
          flower.setVisualParameters(
              parseExtendedVisualParameters(flowerNode.item(j).getChildNodes())
          );
          break;

        case TAG_GROWING_TIPS:
          flower.setGrowingTips(
              parseGrowingTips(flowerNode.item(j).getChildNodes())
          );
          break;

        default:
          throw new MyException("Unknown wrong tag");
      }
    }
    return flower;
  }

  private VisualParameters parseVisualParameters(NodeList vParams) {
    return new VisualParameters(
        vParams.item(1).getTextContent(),
        vParams.item(3).getTextContent(),
        Integer.parseInt(vParams.item(5).getTextContent()));
  }

  private ExtendedVisualParameters parseExtendedVisualParameters(NodeList vParams) {
    ExtendedVisualParameters visualParams = new ExtendedVisualParameters(
        parseVisualParameters(vParams));
    visualParams.setBudColor(vParams.item(7).getTextContent());
    return visualParams;
  }

  private GrowingTips parseGrowingTips(NodeList grTips) {
    boolean photophilous = YES.equals(grTips.item(3).getAttributes().item(0).getTextContent());

    return new GrowingTips(
        Integer.parseInt(grTips.item(1).getTextContent()),
        photophilous,
        Integer.parseInt(grTips.item(5).getTextContent()));
  }
}
