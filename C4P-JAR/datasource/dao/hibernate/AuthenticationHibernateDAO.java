package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.model.Authentication;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.AuthenticationDAO;

/**
 * Concrete Authentication DAO.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class AuthenticationHibernateDAO extends AbstractDAO implements AuthenticationDAO
{
  private static final String GET_BY_USERNAME_QUERY = "Authentication.by.username";
  private static final String USERNAME_ATTRIBUTE = "username";

  /**
   * Gets an authentication given a user name.
   *
   * @param username - the username
   * @return The authentication for that username or nul if this username does not
   *         exist.
   * @throws com.infowave.c4p.datasource.DataException
   */
  public Authentication getByUsername(String username)
          throws DataException
  {
    Authentication authentication  = null;
    authentication = (Authentication) super.getSingleInstance(GET_BY_USERNAME_QUERY,
                                                              USERNAME_ATTRIBUTE,
                                                              username);
    return authentication;
  }

  /**
   * Deletes an authorization given a user name.
   *
   * @param username - the username
   * @throws DataException
   */
  public void deleteByUsername(String username)
          throws DataException
  {
    super.deleteSingleInstance(GET_BY_USERNAME_QUERY,
                               USERNAME_ATTRIBUTE,
                               username);
  }

  /**
   * Creates the object it is not already persistent; otherwise, update it.
   *
   * @param auth - the object to persist/update.
   * @throws DataException
   */
  public void update(Authentication auth) throws DataException
  {
    super.save(auth);
  }

// ---------------------------------------------------------------- Test methods
private static void testGetByUsername() throws Exception
{
  String username = "acc_0.0";
  AuthenticationDAO dao = new AuthenticationHibernateDAO();
  Authentication auth = dao.getByUsername(username);
  System.out.println(auth);
}

  public static void main (String[] args)
  {
    try {
      testGetByUsername();
    }
    catch(Exception e) {e.printStackTrace();}
  }
}
