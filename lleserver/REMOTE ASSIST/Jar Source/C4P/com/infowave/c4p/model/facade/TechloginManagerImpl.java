package com.infowave.c4p.model.facade;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.DataException;

import com.infowave.c4p.service.dto.TechloginDTO;
import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.model.Techlogin;
import com.infowave.c4p.model.assembler.TechloginAssembler;
import com.infowave.c4p.datasource.dao.TechloginDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.delegate.TechloginManager;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

/**
 * This class contains the business logic of Techlogin-related operations.
 *
 */
public class TechloginManagerImpl implements TechloginManager
{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(TechloginManagerImpl.class);

  public TechloginManagerImpl()
  {
  }

  /**
   * Retrieves the list of Techlogins associated with a company. 
   * 
   * @param companyid - a companyid
   * @return a list of Techlogins associated with this company.
   * @throws ServiceException
   */
  public List getTechloginByCompanyid(String companyid)
          throws ServiceException
  {
    List techlogins = null;
    List techloginDTOs = new ArrayList();

    TechloginDAO techloginDAO = DAOFactory.getTechloginDAO();
    try {
		techlogins = techloginDAO.getByCompanyid(companyid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( techlogins == null ) {
		PortalError error = new PortalError(ErrorCode.INVALID_COMPANYID_ERROR, companyid);
		throw new ServiceException(error);
	}

	for (Iterator it = techlogins.iterator(); it.hasNext();){
		Techlogin techlogin = (Techlogin) it.next();
		TechloginDTO dto = TechloginAssembler.createTechloginDTO(techlogin);
		techloginDTOs.add(dto);
    }

	return techloginDTOs;

  }


  /**
   * Returns the active techlogin list corresponding the a techid/companyid combo.
   * @param companyid
   * @param techcid
   * @return a List of TechloginDTOs
   * @throws ServiceException
   */
  public List getActiveByDoubleid(String companyid, String techcid)
          throws ServiceException
  {
    List		techloginDTOs = new ArrayList();
    List		techlogins = null;

    TechloginDAO	techloginDAO = DAOFactory.getTechloginDAO();
    try {
		techlogins = techloginDAO.getActiveByDoubleid(companyid, techcid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( techlogins != null ) {
		TechloginDTO dto;
		for(Iterator it = techlogins.iterator(); it.hasNext(); techloginDTOs.add(dto))
		{
			Techlogin techlogin = (Techlogin)it.next();
			dto = TechloginAssembler.createTechloginDTO(techlogin);
		}
	}

    return techloginDTOs ;
  }

  public List getActiveByTechcid(String techcid)
          throws ServiceException
  {
    List		techloginDTOs = new ArrayList();
    List		techlogins = null;

    TechloginDAO	techloginDAO = DAOFactory.getTechloginDAO();
    try {
		techlogins = techloginDAO.getActiveByTechcid(techcid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( techlogins != null ) {
		TechloginDTO dto;
		for(Iterator it = techlogins.iterator(); it.hasNext(); techloginDTOs.add(dto))
		{
			Techlogin techlogin = (Techlogin)it.next();
			dto = TechloginAssembler.createTechloginDTO(techlogin);
		}
	}

    return techloginDTOs ;
  }

  /**
   * Returns the active techlogin corresponding the loginid.
   * @param loginid -- String
   *
   * @return a techlogindto that is associated with this login id
   * @throws ServiceException
   */
  public TechloginDTO getActiveTechloginByLoginid(double loginid)
          throws ServiceException
  {
    TechloginDTO	dto = null;
    Techlogin		techlogin = null;

    TechloginDAO	techloginDAO = DAOFactory.getTechloginDAO();
    try {
		techlogin = techloginDAO.getActiveByLoginid(loginid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( techlogin != null ) {
		dto = TechloginAssembler.createTechloginDTO(techlogin);
	}

	return dto;
  }

  /**
   * Logouts this technician and sets the flag in the database to the specified value.
   * @param loginid
   * @param flags
   *
   * @return a TechloginDTO
   * @throws ServiceException
   */
  public TechloginDTO logoutByLoginid(double loginid, double flags)
          throws ServiceException
  {
    TechloginDTO	dto = null;
    Techlogin		techlogin = null;

    TechloginDAO	techloginDAO = DAOFactory.getTechloginDAO();
    try {
		// get the techlogin
		techlogin = techloginDAO.getByLoginid(loginid);
		// Don't reset the date if already logged out
		if( techlogin != null && techlogin.isActive() ) {
			Date dt = new Date();
			techlogin.setEnddatetime( dt );
			techlogin.setFlags( flags );
			techlogin = techloginDAO.logout(techlogin);
		}
    } catch (NumberFormatException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( techlogin != null ) {
		dto = TechloginAssembler.createTechloginDTO(techlogin);
	}

	return dto;
  }

  /**
   * Logouts this technician.
   * @param loginid
   *
   * @return a TechloginDTO
   * @throws ServiceException
   */
  public TechloginDTO logoutByLoginid(double loginid)
          throws ServiceException
  {
    TechloginDTO	dto = null;
    Techlogin		techlogin = null;

    TechloginDAO	techloginDAO = DAOFactory.getTechloginDAO();
    try {
		// get the techlogin
		techlogin = techloginDAO.getByLoginid(loginid);
		// Don't reset the date if already logged out
		if( techlogin != null && techlogin.isActive() ) {
			Date dt = new Date();
			techlogin.setEnddatetime( dt );
			techlogin = techloginDAO.logout(techlogin);
		}
    } catch (NumberFormatException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( techlogin != null ) {
		dto = TechloginAssembler.createTechloginDTO(techlogin);
	}

	return dto;
  }

  /**
   * Logs in this technician.
   * @param technician -- TechnicianDTO
   *
   * @return a TechloginDTO
   * @throws ServiceException
   */
  public TechloginDTO login(TechnicianDTO technician, double loginid)
          throws ServiceException
  {
    TechloginDTO	dto = null;
    Techlogin		techlogin = new Techlogin();

    TechloginDAO	techloginDAO = DAOFactory.getTechloginDAO();
    try {
		// load up the techlogin
		techlogin.setLoginid( loginid );
		techlogin.setCompanyid( technician.getCompanyid() );
		techlogin.setTechcid( technician.getTechcid() );
		// Use a default that hibernate will tolerate 01-02-1970 00:00:00
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 1, 1, 0, 0, 0);
		Date dt = cal.getTime();
		techlogin.setEnddatetime( dt );
		// put it into the table
		techlogin = techloginDAO.login(techlogin);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( techlogin != null ) {
		dto = TechloginAssembler.createTechloginDTO(techlogin);
	}

	return dto;
  }

  /**
   * Returns number of active techlogins for this company.
   * @param companyid
   *
   * @return an integer count of the number of active techlogins associated with this company
   * @throws ServiceException
   */
  public int countActiveByCompanyid(String companyid)
          throws ServiceException
  {
	int count = 0;
	try {
		TechloginDAO techloginDAO = DAOFactory.getTechloginDAO();
		count = techloginDAO.countActiveTechloginsByCompanyid( companyid );
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}

	return count;
  }

  /**
   * Returns number of techlogins for this company.
   * @param companyid
   *
   * @return an integer count of the number of techlogins associated with this company
   * @throws ServiceException
   */
  public int countByCompanyid(String companyid)
          throws ServiceException
  {
	int count = 0;
	try {
		TechloginDAO techloginDAO = DAOFactory.getTechloginDAO();
		count = techloginDAO.countTechloginsByCompanyid( companyid );
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}

	return count;
  }

  /**
   * Returns the number of techlogins using this loginid.
   * @param loginid -- String
   *
   * @return a count of the number of logins that are associated with this login id (0 or 1)
   * @throws ServiceException
   */
  public int countTechloginsByLoginid(double loginid)
          throws ServiceException
  {
	int count = 0;
	try {
		TechloginDAO techloginDAO = DAOFactory.getTechloginDAO();
		count = techloginDAO.countTechloginsByLoginid( loginid );
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}

	return count;
  }


  /**
   * Returns the number of active techlogins using this loginid.
   * @param loginid -- String
   *
   * @return a count of the number of logins that are associated with this login id (0 or 1)
   * @throws ServiceException
   */
  public int countActiveTechloginsByLoginid(double loginid)
          throws ServiceException
  {
	int count = 0;
	try {
		TechloginDAO techloginDAO = DAOFactory.getTechloginDAO();
		count = techloginDAO.countActiveTechloginsByLoginid( loginid );
    } catch (NumberFormatException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}

	return count;
  }

}


