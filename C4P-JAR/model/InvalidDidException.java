package com.infowave.c4p.model;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class InvalidDidException extends ModelException
 {
  public InvalidDidException(String msg) {super(msg);}
  public InvalidDidException(String msg, Throwable t) {super(msg,t);}
  public InvalidDidException(Throwable t) {super(t);} 
}
