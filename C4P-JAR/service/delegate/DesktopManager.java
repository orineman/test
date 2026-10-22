package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.model.Desktop;

import java.util.List;

/**
 * This is the Business Delegate for Desktop-related operation.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface DesktopManager
{

  /**
  * Retrieve the default user desktop, return null if there is no default desktops is set.
  *
  * @param username - a username
  * @return a default desktop or null
  * @throws ServiceException
  */
  public DesktopDTO getDefaultDesktop(String username) throws ServiceException;
  
  /**
   * Retrieves the list of desktops a user has access to. Optionally, this
   * method can also check whether the dsktops are connected.
   * *
   * @param username - a username
   * @return a list of desktop this user has access to.
   * @throws ServiceException
   */
  public List getDesktops(String username)
          throws ServiceException;

  /**
   * Returns teh desktop corresponding the a given did.
   * @param did
   * @return
   * @throws ServiceException
   */
  public DesktopDTO getByDid(String did) throws ServiceException;

  /**
   * Determines whether a desktop (identified by its ICM usl) is connected.
   *
   * @param icmUrl - teh url idetifying a desktop
   * @return true is the desktop is connected.
   * @throws ServiceException
   */
  /*
  public boolean isConnected (String icmUrl)
          throws ServiceException;
*/
    /**
   * Retrieves a desktop by did and finds its managing ICM.
   *
   * @param did - the desktop's did.
   * @return the desktop bean..
   * @throws ServiceException
   */
  public DesktopDTO getDesktop(String did)
          throws ServiceException;

  /**
   * Updates a desktop
   * @param did
   * @param desktopDTO
   * @return
   * @throws ServiceException
   */
  public DesktopDTO update(String did, DesktopDTO desktopDTO)
          throws ServiceException;

 /**
  * Remove the access to this desktop to all accounts that currently have access
  * to it; grant access to the accounts that are passed in parameter.
  * @param did - the desktop
  * @param ownerUsername - teh desktop's owner
  * @param users - teh users toattache the desktop to.
  * @throws ServiceException
  */
 public void resetForeignAccounts(String did,
                                  String ownerUsername,
                                  List users)
         throws ServiceException;

    /**
   * generates a unique did
   * @param size - number of character for the did.
   * @return a unique did.
   * @throws ServiceException
   */
  public String generateUniqueDid(int size) throws ServiceException;

    public void removeDesktop(String did)
        throws ServiceException;

/**
 * Sets the default desktop for the user.
 *
 * @param username - the owner name
 * @param did	 - teh desktop's did.
 * @throws ServiceException
 */
public void setDefaultDesktop(String username,String did) throws ServiceException;

    /**
   * Sets the software for a desktop.
   * @param did - teh desktop's did.
   * @param version - the software version
   * @throws ServiceException
   */
  public void setSoftwareVersion(String did, String version) throws ServiceException;

  public int getDesktopcountLikeAccountname( String partialData ) throws ServiceException;
  public int getDesktopcountLikeDesktopname( String partialData ) throws ServiceException;  
  public int getDesktopcountLikeLicense( String partialData ) throws ServiceException;
  public int getDesktopcountLikeExpirydate( String partialData ) throws ServiceException;

  public List getDesktopsLikeAccountname( String partialData ) throws ServiceException;
  public List getDesktopsLikeDesktopname( String partialData ) throws ServiceException;
  public List getDesktopsLikeLicense( String partialData ) throws ServiceException;
  public List getDesktopsLikeExpirydate( String partialData ) throws ServiceException;

  public DesktopDTO updateExpiryDate(String did, DesktopDTO desktopDTO)
          throws ServiceException;

}
