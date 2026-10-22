package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.ServiceException;

import java.util.List;

/**
 * This is the Business Delegate for License-related operation.
 *
 */
public interface LicenseManager
{

  /**
   * Determine whether a license code corresponds to a fixed term license.
   * @param licenseCode
   * @param resellerId
   * @return
   * @throws ServiceException
   */
  public boolean isFixedTerm(String licenseCode, String resellerId)
  throws ServiceException;
  /**
   * Sets a new license for a destiop.
   * @param desktopId
   * @param licenseCode
   * @param extend
   * @return the list of licenses that have been freed up
   * @throws ServiceException
   */
  public List setLicenseCode(String userId,String desktopId,String licenseCode,
                             boolean extend)
  throws ServiceException;

    /**
   * remove the desktop's license if it exsits.
   * @param desktopId
   * @throws ServiceException
   */
  public List unlicense(String desktopId)
  throws ServiceException;

    /**
   * sets the license infromation for LapLink Gold.
   * @param desktopId
   * @param sn -- first 11 digits of a 12 digit serial number
   * @throws ServiceException
   */
   public abstract int setLLGLicense(String desktopId, String sn)
   throws ServiceException;


}
