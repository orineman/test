package com.infowave.c4p.datasource;
/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class InconsistentDataException extends DataException {

  public InconsistentDataException(String msg) {super(msg);}
  public InconsistentDataException(String msg, Throwable t) {super(msg,t);}
  public InconsistentDataException(Throwable t) {super(t);}
}
