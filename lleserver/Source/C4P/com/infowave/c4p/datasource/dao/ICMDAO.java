package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.ICM;
import com.infowave.c4p.datasource.DataException;

/**
 * This interface defines the methods supported by ICM DAO implementions.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface ICMDAO
{
  /**
   * Gets an ICM given its name.
   *
   * @param name - the ICM's name
   * @return The ICM with that name or null if this name does not
   *         exist.
   * @throws com.infowave.c4p.datasource.DataException
   */
  public ICM getByUsername(String name) throws DataException;

  /**
   * Returns all ICMs.
   * @return all the ICMs presents in the system.
   * @throws DataException
   */
  public ICM[] getAll() throws DataException;

  /**
   * Update -or create- an ICM.
   * @param icm - the ICM to persist or update.
   * @throws DataException
   */
  public void update(ICM icm) throws DataException;
  
}
