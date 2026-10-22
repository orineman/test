package com.infowave.c4p.web.actions.onyx;

public class OnyxException extends Exception
{
  public OnyxException (String msg) {super(msg);}
  public OnyxException (String msg, Throwable t) {super(msg,t);}
  public OnyxException (Throwable t) {super(t);}

}
