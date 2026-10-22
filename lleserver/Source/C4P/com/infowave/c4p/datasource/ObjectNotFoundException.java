package com.infowave.c4p.datasource;
/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ObjectNotFoundException extends DataException{
  public ObjectNotFoundException(String msg) {super(msg);}
  public ObjectNotFoundException(String msg, Throwable t) {super(msg,t);}
  public ObjectNotFoundException(Throwable t) {super(t);}

}
