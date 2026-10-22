package com.infowave.c4p.model;

/**
 * When we cannot contact the ICM.
 */
public class CannotConnectToICMException extends ModelException
 {
  public CannotConnectToICMException(String msg) {super(msg);}
  public CannotConnectToICMException(String msg, Throwable t) {super(msg,t);}
  public CannotConnectToICMException(Throwable t) {super(t);}

}
