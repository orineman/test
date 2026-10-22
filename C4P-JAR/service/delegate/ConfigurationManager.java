package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.ServiceException;

import java.util.List;

/**
 * This interface contains the methods the web application requires for its
 * configuration.
 * 
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface ConfigurationManager

{
  /**
   * Returns the list of all known resellers.
   *
   * @return the list of all known resellers.
   * @throws ServiceException
   */
  public List getAllResellers() throws ServiceException;
}
