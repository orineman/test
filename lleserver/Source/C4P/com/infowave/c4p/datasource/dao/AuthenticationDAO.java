package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.Authentication;
import com.infowave.c4p.datasource.DataException;

/**
 * This interface defines the methods supported by Authorization DAO
 * implementions.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface AuthenticationDAO
{
  /**
   * Gets an authentication given a user name.
   *
   * @param username - the username
   * @return The authentication for that username or nul if this username does not
   *         exist.
   * @throws com.infowave.c4p.datasource.DataException
   */
  public Authentication getByUsername(String username)
          throws DataException;

  /**
   * Deletes an authorization given a user name.
   *
   * @param username - the username
   * @throws DataException
   */
  public void deleteByUsername(String username)
          throws DataException;

  /**
   * Creates the object it is not already persistent; otherwise, update it.
   *
   * @param auth - the object to persist/update.
   * @throws DataException
   */
  public void update(Authentication auth) throws DataException;

}
