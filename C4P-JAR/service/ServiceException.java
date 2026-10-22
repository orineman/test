package com.infowave.c4p.service;

import com.infowave.c4p.util.PortalException;
import com.infowave.c4p.util.PortalError;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ServiceException extends PortalException
 {
  
  private PortalError m_error;

  public ServiceException (PortalError error)
  {
    super();
    m_error = error;
  }

  public ServiceException (PortalError error,String msg)
  {
    super(msg);
    m_error = error;
  }

  public ServiceException (PortalError error, String msg, Throwable t)
  {
    super(msg,t);
    m_error = error;
  }

  public ServiceException (PortalError error,Throwable t)
  {
    super(t);
    m_error = error;
  }

  public PortalError getError()
  {
    return m_error;
  }

   public String getMessage()
   {
     String superMsg = super.getMessage();
     if (superMsg == null)
       return m_error.toString();
     else
       return m_error.toString() + superMsg;
   }
}
