package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.datasource.DataException;


/**
 * This interface defines the methods supported by Reseller DAO implementions.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface ResellerDAO
{
    public Reseller getByCode(String code) throws DataException;
  public void update(Reseller reseller) throws DataException;
  public Reseller[] getAll() throws DataException;


}
