package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.License;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.datasource.DataException;

import java.util.List;

/**
 * This interface defines the methods supported by License DAO implementions.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface LicenseDAO
{

  /**
   * Returns the licenses with a specific code.
   *
   * @param code - the serial number we want to retrieve
   * @return The Licenses with that code belonging to that user
   * @throws com.infowave.c4p.datasource.DataException
   */

  public List getByCode(String code, String resellerId)
          throws DataException;


  /**
   * Save - or update- a License bean.
   * @param license - the bean to be saved.
   * @throws com.infowave.c4p.datasource.DataException
   */
  public void update(License license) throws DataException;
    /**
   * Returns the licenses with a specific code and owned by a specific user.
   *
   * @param code - the serial number we want to retrieve
   * @param userId - a user's id
   * @return The Licenses with that code belonging to that user
   * @throws com.infowave.c4p.datasource.DataException
   */
  public List getByCodeAndUserId(String code, String resellerId, String userId)

          throws DataException;

    /**
   * Returns the licenses with a specific code that have not been used yet,
   *
   * @param code - the serial number we want to retrieve
   * @return The Licenses with that code that have not been used
   * @throws com.infowave.c4p.datasource.DataException
   */
  public List getUnusedByCode(String code, String resellerId)
          throws DataException;

    /**
   * Returns the licenses with a specific code that have not been used yet.
   * The code is the first 11 digits of a 12 digit value. Used specifically for
   *  LapLink Gold 11.5 compatibility.
   *
   * @param code - the serial number we want to retrieve
   * @return The Licenses with that code that have not been used
   * @throws com.infowave.c4p.datasource.DataException
   */
    public abstract List getUnusedLLGByCode(String code, String resellerId)
        throws DataException;


  /**
   * Save a license, the desktop that receives this license, and teh desktop's
   * previous license.
   * @param license
   * @param desktop
   * @throws DataException
   */
  public void licenseDesktop(License license, Desktop desktop,
                             List licensesMadeAvailable,List expiredLicenses,
                             Account acount)
          throws DataException;

  /**
   * Saves the license and the desktop.
   *
   * @param licenses
   * @param desktop
   * @throws DataException
   */
  public void unlicenseDesktop(List licenses, Desktop desktop)
          throws DataException;

  /**
   * Count the number of licenses having a particular code.
   * @param code
   * @param resellerId
   * @return
   * @throws DataException
   */
  public int countLicenses(String code, String resellerId)
          throws DataException;

  /**
   * Creates a batch of licenses.
   * @param licenses
   * @throws DataException
   */
  public void createLicenses(List licenses) throws DataException;
}
