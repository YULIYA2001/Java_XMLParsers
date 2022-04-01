package com.golubovich.xml;


import com.golubovich.xml.bean.Flower;
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
}