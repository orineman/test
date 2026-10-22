package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Authentication;
import com.infowave.c4p.model.Desktop;

import java.util.List;

/**
 * This interface defines the methods supported by DAO Account implementions.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface AccountDAO
{
  /**
   * Gets an account given a user name.
   *
   * @param username - the username
   * @return The account for that username or nul if this username does not
   *         exist.
   * @throws DataException
   */
  public Account getByUsername(String username)
          throws DataException;
  /**
   * Gets an account by its id
   *
   * @param id - the account's id
   * @return The account for that id or nul if this username does not
   *         exist.
   * @throws DataException
   */
  public Account getById(String id)
          throws DataException;


  /**
   * Deletes an account given a user name.
   *
   * @param username - the username
   * @throws DataException
   */
  public void deleteByUsername(String username)
          throws DataException;

  /**
   * Creates the object it is not already persistent; otherwise, update it.
   *
   * @param account - the object to persist/update.
   * @throws DataException
   */
  public void update(Account account) throws DataException;
 /**
   * Creates an account by persisting the Account, Authentication, and Desktop
   * objects.
   *
   * @param account
   * @param authentication
   * @param desktop
   * @throws DataException
   */
  public void createAcount (Account account,
                            Authentication authentication,
                            Desktop desktop)
  throws DataException;
 /**
   * Attachess a desktop to an existing account.
   * objects.
   *
   * @param account
   * @param desktop
   * @throws DataException
   */
  public void addDesktopToAcount (Account account,
                                  Desktop desktop)
            throws DataException;

  /**
   * Saves a list of accounts
   *
   * @param accounts
   * @throws DataException
   */
  public void update (List accounts)  throws DataException;

  public int countUsersByUsername( String partialName ) throws DataException;
  public int countUsersByFirstname( String partialName ) throws DataException;
  public int countUsersByLastname( String partialName ) throws DataException;
  public int countUsersByEmail( String partialName ) throws DataException;

  public List getUsersByUsername( String partialName ) throws DataException;
  public List getUsersByFirstname( String partialName ) throws DataException;
  public List getUsersByLastname( String partialName ) throws DataException;
  public List getUsersByEmail( String partialName ) throws DataException;



}