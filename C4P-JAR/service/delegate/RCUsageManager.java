package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.RCUsageDTO;
import com.infowave.c4p.model.RCUsage;

import java.util.Date;
import java.util.List;


/**
 * This is the Business Delegate for RCUsage-related operation.
 *
 */
public interface RCUsageManager
{

  /**
   * Returns the rcusage corresponding the a given did.
   * @param did
   * @return
   * @throws ServiceException
   */
  public RCUsageDTO getByDid(String did) throws ServiceException;


  /**
   * Updates a desktop
   * @param did
   * @param desktopDTO
   * @return
   * @throws ServiceException
   */
  public RCUsageDTO update(String did, RCUsageDTO rcUsageDTO, RCUsage rcusage )
          throws ServiceException;

/*
    public void removeRCUsage(String did)
        throws ServiceException;
*/

  public RCUsageDTO updateExpiryDate(String did, RCUsageDTO rcusageDTO)
          throws ServiceException;

  public int countRCUsagesLikeLicense( String partialData )
		  throws ServiceException;

  public List getRCUsagesLikeLicense( String partialData ) 
		throws ServiceException;

}
