package com.infowave.c4p.model;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class DesktopAlreadyAddedException extends ModelException
 {
  public DesktopAlreadyAddedException (String msg) {super(msg);}
  public DesktopAlreadyAddedException (String msg, Throwable t) {super(msg,t);}
  public DesktopAlreadyAddedException (Throwable t) {super(t);}
}
