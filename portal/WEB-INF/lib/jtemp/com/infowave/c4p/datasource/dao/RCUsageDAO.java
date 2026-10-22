package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.RCUsage;
import com.infowave.c4p.datasource.DataException;

import java.util.List;
/**
 * This interface defines the methods supported by Desktop DAO implementions.
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface RCUsageDAO
{
  public RCUsage getByDid(String did) throws DataException;

  public List getByCode(String code) throws DataException;

  public RCUsage getByCodeAndDid(String code, String did) throws DataException;

  public void update(RCUsage rcusage) throws DataException;

  public int countRCUsagesLikeLicense( String partialData ) throws DataException;

  public List getRCUsagesLikeLicense( String partialData ) throws DataException;
}
