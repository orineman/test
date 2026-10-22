package com.infowave.c4p.web;

public class WebException extends Exception
{
  public WebException (String msg) {super(msg);}
  public WebException (String msg, Throwable t) {super(msg,t);}
  public WebException (Throwable t) {super(t);}

}
