package com.golubovich.xml;

import com.golubovich.xml.bean.ExtendedVisualParameters;
import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.bean.GrowingTips;
import com.golubovich.xml.bean.VisualParameters;
import com.golubovich.xml.bean.enums.Multiplying;
import com.golubovich.xml.bean.enums.Soil;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.lang.model.util.Elements.Origin;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLMain {

  public static void main(String[] args) {

    ArrayList<Flower> flowers = new ArrayList();

    File file = new File("src/main/resources/plants.xml");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Document doc = null;
    try {
      doc = dbf.newDocumentBuilder().parse(file);
    } catch (SAXException | IOException | ParserConfigurationException e) {
      System.out.println("Open parsing error " + e.getMessage());
      return;
    }

    Node root = doc.getFirstChild();
    NodeList rootChilds = root.getChildNodes();
    NodeList flowerNode= null;


    for (int i = 0; i < rootChilds.getLength(); i++) {
      if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }

      Flower flower = new Flower();
      flower.setId(Integer.parseInt(
          rootChilds.item(i).getAttributes().item(0).getTextContent().substring(1)
      ));
//      System.out.println("\nid: " + flower.getId());

      flowerNode = rootChilds.item(i).getChildNodes();
      for (int j = 0; j < flowerNode.getLength(); j++) {
        if (flowerNode.item(j).getNodeType() != Node.ELEMENT_NODE) {
          continue;
        }

        if (flowerNode.item(j).getNodeName() == null) {
          System.out.println("error smth wrong");
          return;
        }

        switch (flowerNode.item(j).getNodeName()) {
          case "name":
            flower.setName(flowerNode.item(j).getTextContent());
//            System.out.println("name: " + flower.getName());
            break;

          case "soil":
            String _soil = flowerNode.item(j).getTextContent();
            for (Soil el: Soil.values()) {
              if (el.getName().equals(_soil)) {
                flower.setSoil(el);
              }
            }
//            System.out.println("soil: " + flower.getSoil().getName());
            break;

          case "multiplying":
            ArrayList<String> _mltStrList = new ArrayList(
                Arrays.asList(flowerNode.item(j).getTextContent().split("\\s")));
            ArrayList<Multiplying> mltList = new ArrayList<>();
            for (String _multiplyingEl: _mltStrList) {
              for (Multiplying el : Multiplying.values()) {
                if (el.getName().equals(_multiplyingEl)) {
                  mltList.add(el);
                }
              }
            }
            flower.setMultiplying(mltList);
//            for (Multiplying mlt: flower.getMultiplying())
//              System.out.println("multiplying: " + mlt.getName());
            break;

          case "origin":
            NodeList origin = flowerNode.item(j).getChildNodes();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            flower.setCountry(origin.item(1).getTextContent());
            flower.setImportDate(LocalDate.parse(origin.item(3).getTextContent(), formatter));
//            System.out.println("country: " + flower.getCountry());
//            System.out.println("importDate: " + flower.getImportDate());
            break;

          case "visualParameters":
            flower.setVisualParameters(
                parseVisualParameters(flowerNode.item(j).getChildNodes())
            );
//            System.out.println("visualParameters: " + flower.getVisualParameters().toString());
            break;

          case "extendedVisualParameters":
            flower.setVisualParameters(
              parseExtendedVisualParameters(flowerNode.item(j).getChildNodes())
            );
//            System.out.println("visualParameters: " +
//                flower.getVisualParameters().toString());
            break;

          case "growingTips":
            flower.setGrowingTips(
              parseGrowingTips(flowerNode.item(j).getChildNodes())
            );
//            System.out.println("growingTips: " + flower.getGrowingTips().toString());
            break;

          default:
            System.out.println("ERROR ----------------- Wrong Tag ---------------------");
        }


      }
      flowers.add(flower);
    }

    for (Flower fl: flowers) {
      System.out.println("\n\n" + fl.toString());
    }

  }

  private static VisualParameters parseVisualParameters(NodeList _vParams) {
    return new VisualParameters(
        _vParams.item(1).getTextContent(),
        _vParams.item(3).getTextContent(),
        Integer.parseInt(_vParams.item(5).getTextContent()));
  }

  private static ExtendedVisualParameters parseExtendedVisualParameters(NodeList _vParams) {
    ExtendedVisualParameters vParams = new ExtendedVisualParameters(parseVisualParameters(_vParams));
    vParams.setBudColor(_vParams.item(7).getTextContent());
    return vParams;
  }

  private static GrowingTips parseGrowingTips(NodeList _grTips) {
    boolean photophilous;
    photophilous = "Да".equals(_grTips.item(3).getAttributes().item(0).getTextContent());

    return new GrowingTips(
        Integer.parseInt(_grTips.item(1).getTextContent()),
        photophilous,
        Integer.parseInt(_grTips.item(5).getTextContent()));
  }
}

//public class XMLMain {
//  private static ArrayList<Flower> flowers = new ArrayList<>();
//
//  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//    // Создание фабрики и образца парсера
//    SAXParserFactory factory = SAXParserFactory.newInstance();
//    SAXParser parser = factory.newSAXParser();
//
//    XMLHandler handler = new XMLHandler();
//    parser.parse(new File("src/main/resources/plants.xml"), handler);
//  }
//
//  private static class XMLHandler extends DefaultHandler {
//    private String name, lastElementName;
//
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//      // Тут будет логика реакции на начало элемента
//      if (qName.equals("flower")) {
//        int id = Integer.parseInt(attributes.getValue("id").substring(1));
//        System.out.println("id - " + id);
//      }
//
//      lastElementName = qName;
//    }
//
//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//      // Тут будет логика реакции на конец элемента
//      if (name != null && !name.isEmpty()) {
//        System.out.println("name - " + name);
//        name = null;
//      }
//    }
//
//    @Override
//    public void characters(char[] ch, int start, int length) throws SAXException {
//      // Тут будет логика реакции на текст между элементами
//      String information = new String(ch, start, length);
//
//      information = information.replace("\n", "").trim();
//
//      if (!information.isEmpty()) {
//        if (lastElementName.equals("name"))
//          name = information;
//      }
//    }
//
//    @Override
//    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
//      // Тут будет логика реакции на пустое пространство внутри элементов (пробелы, переносы строчек и так далее).
//    }
//  }
//}
