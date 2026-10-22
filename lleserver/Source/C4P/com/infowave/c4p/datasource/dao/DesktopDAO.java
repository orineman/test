package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.datasource.DataException;

import java.util.List;
/**
 * This interface defines the methods supported by Desktop DAO implementions.
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface DesktopDAO
{
  public Desktop getByDid(String did) throws DataException;

  /**
   * Retrieve a desktop given it's unique id.
   * @param id
   * @return
   * @throws DataException
   */
  public Desktop getById(String id) throws DataException;

  /**
   * Retrieve a desktop given it's unique id. Also initialize the license
   * reference.
   * @param id
   * @return
   * @throws DataException
   */
  public Desktop getByIdWithLicense(String id) throws DataException;


  public void update(Desktop desktop) throws DataException;
/**
   * Reset the foreign access to the desktop:
   *  - deny access to any account that currently has access to this desktop
   *  - grant access to the account in the list.
   * @param desktop
   * @param accounts
   * @throws DataException
   */
  public void resetForeignAccess(Desktop desktop, List accounts)
  throws DataException;
  /**
   * Retrieves a desktop with its list of foreign accounts initialized
   * @param did
   * @return
   * @throws DataException
   */
  public Desktop getByDidWithForeignAccounts(String did)
  throws DataException;

    public abstract void delete(String s)
        throws DataException;

  public int countDesktopsByAccountname( String partialName ) throws DataException;
  public int countDesktopsByDesktopname( String partialName ) throws DataException;
  public int countDesktopsByLicense( String partialName ) throws DataException;
  public int countDesktopsByExpirydate( String partialName ) throws DataException;

  public List getDesktopsByAccountname( String partialName ) throws DataException;
  public List getDesktopsByDesktopname( String partialName ) throws DataException;
  public List getDesktopsByLicense( String partialName ) throws DataException;
  public List getDesktopsByExpirydate( String partialName ) throws DataException;


}
