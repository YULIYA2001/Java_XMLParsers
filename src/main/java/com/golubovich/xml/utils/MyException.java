package com.golubovich.xml.utils;

public class MyException extends Exception {

  public MyException() {
    super();
  }

  public MyException(String message) {
    super(message);
  }

  public MyException(MyException e) {
    super(e);
  }

  public MyException(String message, MyException e) {
    super(message, e);
  }
}
