package com.golubovich.xml;


import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.builder.Builder;
import com.golubovich.xml.builder.Director;
import com.golubovich.xml.builder.DOMParserBuilder;
import com.golubovich.xml.builder.JAXBParserBuilder;
import com.golubovich.xml.builder.SAXParserBuilder;
import com.golubovich.xml.builder.StAXParserBuilder;
import com.golubovich.xml.parsers.DOMParser;
import com.golubovich.xml.parsers.StAXParser;
import com.golubovich.xml.parsers.jaxb.JAXBParser;
import com.golubovich.xml.parsers.sax.SAXMyParser;
import com.golubovich.xml.utils.MyException;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class XMLMain {

  private static final Logger logger = LogManager.getLogger(XMLMain.class);

  public static void main(String[] args) {
    final String DOM = "1";
    final String SAX = "2";
    final String StAX = "3";
    final String JAXB = "4";
    final String EXIT = "0";


    // wrong builder
    do {
      Director director = new Director();
      Builder builder;
      List<Flower> flowers;

      try {

        builder = new DOMParserBuilder();
        director.constructDOMParser(builder);
        DOMParser domParser = ((DOMParserBuilder) builder).getResult();
        flowers = domParser.parse();
        print(flowers);

        builder = new SAXParserBuilder();
        director.constructSAXParser(builder);
        SAXMyParser saxParser = ((SAXParserBuilder) builder).getResult();
        flowers = saxParser.parse();
        print(flowers);

        builder = new StAXParserBuilder();
        director.constructStAXParser(builder);
        StAXParser staxParser = ((StAXParserBuilder) builder).getResult();
        flowers = staxParser.parse();
        print(flowers);

        builder = new JAXBParserBuilder();
        director.constructJAXBParser(builder);
        JAXBParser jaxbParser = ((JAXBParserBuilder) builder).getResult();
        flowers = jaxbParser.parse();
        print(flowers);

      } catch (MyException e) {
        logger.error(e.getMessage());
      }

    } while (false);




    // Standard Menu
    Scanner in = new Scanner(System.in);
    System.out.println("\t Выберите вид парсера\n-------------------------");

    while (true) {
      List<Flower> flowersList = null;
      System.out.println("\n1. DOM \t 2. SAX \t 3. StAX \t 4. JAXB \t 0. Выход");

      switch (in.nextLine()) {
        case DOM:
          DOMParser domParser = new DOMParser();
          try {
            flowersList = domParser.parse();
          } catch (MyException e) {
            logger.error(e.getMessage());
            return;
          }
          break;

        case SAX:
          SAXMyParser saxParser = new SAXMyParser();
          try {
            flowersList = saxParser.parse();
          } catch (MyException e) {
            logger.error(e.getMessage());
            return;
          }
          break;

        case StAX:
          StAXParser staxParser = new StAXParser();
          try {
            flowersList = staxParser.parse();
          } catch (MyException e) {
            logger.error(e.getMessage());
            return;
          }
          break;

        case JAXB:
          JAXBParser jaxbParser = new JAXBParser();
          try {
            flowersList = jaxbParser.parse();
          } catch (MyException e) {
            logger.error(e.getMessage());
            return;
          }
          break;

        case EXIT:
          return;
        default:
          System.out.println("Неверный ввод. Попробуйте снова");
      }

      if (flowersList != null) {
        for (Flower flower : flowersList) {
          logger.info(flower);
        }
      }
    }
  }

  private static void print(List<Flower> flowersList) {
    if (flowersList != null) {
      for (Flower flower : flowersList) {
        System.out.println(flower);
      }
      System.out.println();
    }
  }
}