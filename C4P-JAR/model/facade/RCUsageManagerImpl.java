package com.infowave.c4p.model.facade;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.RCUsageDAO;
import com.infowave.c4p.model.RCUsage;
import com.infowave.c4p.model.assembler.RCUsageAssembler;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.RCUsageManager;
import com.infowave.c4p.service.dto.RCUsageDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * This class contains the business logic of rcusage-related operations.
 *
 */
public class RCUsageManagerImpl implements RCUsageManager
{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(RCUsageManagerImpl.class);
  private String did = "";
  private RCUsage rcusage= null;
  public static final int WINDOW_SIZE = 30;
  public static final int DEFAULT_LIMIT = 3600;


  public RCUsageManagerImpl( ) 
  {
	this.did = did;
  }

  public RCUsageManagerImpl( String did ) 
  {
	this.did = did;
  }

  public RCUsage getRCUsage() {
	return rcusage;
  }

  /* Ensures consistency with in the RCUsage table
   *
   * If the did does not exist in the RCUsage table, it is added.
   * If the did does exist, the window is checked for expiration. If it has expired,
   * the information is reset in the database.
   *
   */
  public RCUsageDTO verifyInfo( int windowSize) throws ServiceException
  {
    	rcusage= null;
    	RCUsageDAO rcUsageDAO = DAOFactory.getRCUsageDAO();

	// see if the did already exists in the rcusage database table
    	try {
		rcusage= rcUsageDAO.getByDid( did );
		if( rcusage == null ) {
			throw new DataException("Did not found");
		}
		// did is already in the table so ensure the window expiry & values are ok
		if( rcusage.validate( windowSize) ) {
			// update the database
			rcUsageDAO.update( rcusage );
		}

    	} catch ( DataException de ){
		// did isn't present so add it
		try {
			rcusage = new RCUsage();
			rcusage.setDid( did );
			rcusage.setDefaultEntry( windowSize );
			rcUsageDAO.update( rcusage);
		} catch( DataException dee ) {
    			PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      			throw new ServiceException(error,dee);
		}
    	}

      	RCUsageDTO rcUsageInfo= RCUsageAssembler.createRCUsageDTO(rcusage);
	return rcUsageInfo;
  }

   public boolean isExpired() throws ServiceException
   {
	boolean isExpired = false;
	int clientSecondsLeft = getClientSecondsLeft();
	if( clientSecondsLeft <= 0 ) {
		isExpired = true;
	}
	   m_log.debug("RCUsageManagerImpl IsExpired:" + isExpired);
	return isExpired;
   }

   public int getClientSecondsLeft() throws ServiceException
   {

	RCUsageDTO rcInfo = null;
	int clientSecondsLeft = 0;

	rcInfo = verifyInfo( WINDOW_SIZE );
	if( rcInfo != null ) {
        		int maxSecs = Math.max( DEFAULT_LIMIT, rcInfo.getWindowLimit() );
        		clientSecondsLeft = maxSecs;
        		if( maxSecs < 0x7fffffff ) {
			// Orin's defaults
                		clientSecondsLeft = Math.max( -2, maxSecs - rcInfo.getClientSeconds() );
		}
        	}

	   m_log.debug("RCUsageManagerImpl seconds left:" + Integer.toString(clientSecondsLeft));
	return clientSecondsLeft;
   }

   public RCUsageDTO updateExpiryDate(String did, RCUsageDTO rcusageDTO)
          throws ServiceException
  {
    RCUsageDTO dto = null;
    RCUsageDAO dao = DAOFactory.getRCUsageDAO();
    try {
      RCUsage rcusage = null;
      rcusage = dao.getByDid(did);
      rcusage.setExpiryDate(rcusageDTO.getExpiryDate());
      dao.update(rcusage);
      dto = RCUsageAssembler.createRCUsageDTO(rcusage);
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return dto;
  }

  public RCUsageDTO update(String did, RCUsageDTO rcUsageDTO, RCUsage rcusage)
          throws ServiceException
  {
    RCUsageDTO dto = null;
    RCUsageDAO dao = DAOFactory.getRCUsageDAO();
    try {
      //RCUsage rcusage = null;
      //rcusage = dao.getByDid(did);
      if( rcusage != null ) {
	rcusage.setClientSeconds( rcUsageDTO.getClientSeconds() );
	rcusage.setServerSeconds( rcUsageDTO.getServerSeconds() );
	rcusage.setBytes( rcUsageDTO.getBytes() );
	rcusage.setWindowLimit( rcUsageDTO.getWindowLimit() );
	rcusage.setExpiryDate( rcUsageDTO.getExpiryDate() );
	rcusage.setWindowExpiryDate( rcUsageDTO.getWindowExpiryDate() );
	rcusage.setLicenseSerialNumber( rcUsageDTO.getLicenseSerialNumber() );
    	dao.update( rcusage );
     	dto = RCUsageAssembler.createRCUsageDTO(rcusage);
      }
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return dto ;
  }

  public RCUsageDTO getByDid(String did) throws ServiceException
  {
    RCUsageDTO dto = null;
    RCUsage rcusage = null;
    RCUsageDAO rcUsageDAO = DAOFactory.getRCUsageDAO();
    try {
      rcusage = rcUsageDAO.getByDid(did);
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    if (rcusage != null){
      dto = RCUsageAssembler.createRCUsageDTO(rcusage);
    }

    return dto;
  }

  public int countRCUsagesLikeLicense( String partialData ) throws ServiceException
  {
      try {
     	RCUsageDAO rcusageDAO = DAOFactory.getRCUsageDAO();
		int count = rcusageDAO.countRCUsagesLikeLicense( partialData );
      	return count;
     } catch (DataException e) {
      	PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      	throw new ServiceException(error,e);
     }
  }

  public List getRCUsagesLikeLicense( String partialData ) throws ServiceException
  {
        List rcusages = null;
        List rcusageDTOs = new ArrayList();

        try
        {
      	RCUsageDAO rcusageDAO = DAOFactory.getRCUsageDAO();
            rcusages = rcusageDAO.getRCUsagesLikeLicense( partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        RCUsageDTO dto;
        for(Iterator it = rcusages.iterator(); it.hasNext(); rcusageDTOs.add(dto))
        {
            RCUsage rcusage = (RCUsage)it.next();
            dto = RCUsageAssembler.createRCUsageDTO(rcusage);
        }

        return rcusageDTOs ;

  }


}


