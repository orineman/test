package com.infowave.c4p.datasource;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class DataException extends Exception
{
  public DataException (String msg) {super(msg);}
  public DataException (String msg, Throwable t) {super(msg,t);}
  public DataException (Throwable t) {super(t);}

}
