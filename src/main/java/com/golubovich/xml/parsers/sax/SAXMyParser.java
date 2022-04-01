package com.golubovich.xml.parsers.sax;

import static com.golubovich.xml.utils.Constants.*;

import com.golubovich.xml.bean.Flower;
import com.golubovich.xml.utils.MyException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class SAXMyParser {

  public List<Flower> parse() throws MyException {

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SaxParserHandler handler = new SaxParserHandler();
    SAXParser parser;
    try {
      parser = factory.newSAXParser();
    } catch (ParserConfigurationException | SAXException e) {
      throw new MyException(e.getMessage());
    }

    File file = new File(FILE_PATH);
    try {
      parser.parse(file, handler);
    } catch (SAXException | IOException e) {
      throw new MyException(e.getMessage());
    }

    return handler.getFlowersList();
  }
}