package com.infowave.c4p.model;

import com.infowave.c4p.util.PortalException;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ModelException extends PortalException
 {
  public ModelException (String msg) {super(msg);}
  public ModelException (String msg, Throwable t) {super(msg,t);}
  public ModelException (Throwable t) {super(t);} 
}
