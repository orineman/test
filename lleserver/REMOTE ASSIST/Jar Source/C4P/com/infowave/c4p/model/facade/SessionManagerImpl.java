package com.infowave.c4p.model.facade;


import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.DataException;

import com.infowave.c4p.service.dto.SessionDTO;
import com.infowave.c4p.model.HDSession;
import com.infowave.c4p.model.assembler.SessionAssembler;
import com.infowave.c4p.datasource.dao.SessionDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.delegate.SessionManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class contains the business logic of Session-related operations.
 *
 */
public class SessionManagerImpl implements SessionManager
{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(SessionManagerImpl.class);

  public SessionManagerImpl()
  {
  }

  /**
   * Retrieves the list of sessions associated with a company. 
   * 
   * @param companyid - a companyid
   * @return a list of sessions associated with this company.
   * @throws ServiceException
   */
  public List getSessionByCompanyid(String companyid)
          throws ServiceException
  {
    List sessions = null;
    List sessionDTOs = new ArrayList();

    SessionDAO sessionDAO = DAOFactory.getSessionDAO();
    try {
		sessions  = sessionDAO.getByCompanyid(companyid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( sessions == null ) {
		PortalError error = new PortalError(ErrorCode.INVALID_COMPANYID_ERROR, companyid);
		throw new ServiceException(error);
	}

	for (Iterator it = sessions.iterator();it.hasNext();){
		HDSession session = (HDSession) it.next();
		SessionDTO dto = SessionAssembler.createSessionDTO(session);
		sessionDTOs.add(dto);
    }

	return sessionDTOs;

  }

  /*
   * Gets a session given the id combo of companyid, techcid, and did.
   *
   * @param companyid - the companyid
   * @param techcid - the techcid
   * @param did - the did
   * @return The sessiondto for this id combo or null if this combo does not
   *         exist.
   * @throws com.infowave.c4p.datasource.ServiceException
   */
  public SessionDTO getByTripleid(String companyid, String techcid, String did)
          throws ServiceException
  {
    SessionDTO dto = null;
    HDSession session = null;

    SessionDAO sessionDAO = DAOFactory.getSessionDAO();
    try {
		session = sessionDAO.getByTripleid(companyid, techcid, did);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( session != null ) {
		dto = SessionAssembler.createSessionDTO(session);
	}

	return dto;
  }

  /*
   * Gets a session given the id combo of companyid, techcid, and did.
   *
   * @param companyid - the companyid
   * @param techcid - the techcid
   * @param did - the did
   * @return The sessiondto for this id combo or null if this combo does not
   *         exist.
   * @throws com.infowave.c4p.datasource.ServiceException
   */
  public SessionDTO getAvailableByTripleid(String companyid, String techcid, String did)
          throws ServiceException
  {
    SessionDTO dto = null;
    HDSession session = null;

    SessionDAO sessionDAO = DAOFactory.getSessionDAO();
    try {
		session = sessionDAO.getAvailableByTripleid(companyid, techcid, did);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( session != null ) {
		dto = SessionAssembler.createSessionDTO(session);
	}

	return dto;
  }

  /**
   * Updates an existing session.
   * @param username
   * @param companyid
   * @param techid
   * @param did
   * @return the updated SessionDTO
   * @throws ServiceException
   */
  public SessionDTO update(String companyid, String techcid, String did, String name) 
		throws ServiceException
  {
    SessionDTO dto = null;
    SessionDAO dao = DAOFactory.getSessionDAO();
    try {
      HDSession session = null;
      session = dao.getByTripleid(companyid, techcid, did);
	  if( session != null ) {
	    // Set the StartDate
		session.setStartdatetime( new Date() );
	    // set the name
		session.setName(name);
		// increment the number of uses
		int numUses = session.getNumuses();
		numUses += 1;
		session.setNumuses( numUses );
		// update the database
		dao.update(session);
		dto = SessionAssembler.createSessionDTO(session);
	  }
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return dto;
  }

  /**
   * Closes an existing session.
   * @param did
   * @return the updated SessionDTO
   * @throws ServiceException
   */
  public SessionDTO close(String did) 
		throws ServiceException
  {
    SessionDTO dto = null;
    SessionDAO dao = DAOFactory.getSessionDAO();
    try {
      HDSession session = null;
      session = dao.getByDid(did);
	  if( session != null ) {
	    // set the ending timestamp
		session.setEnddatetime( new Date() );
		// update the database
		dao.update(session);
		dto = SessionAssembler.createSessionDTO(session);
	  }
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return dto;
  }


  /**
   * Count the number of sessions with this did.
   * @param did 
   * @return the number of sessions with this did (1 or 0)
   * @throws DataException
   */
  public int countSessionsByDid(String did)
          throws ServiceException
  {
	int count = 0;
	try {
		SessionDAO sessionDAO = DAOFactory.getSessionDAO();
		count = sessionDAO.countSessionsByDid( did );
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}

	return count;
  }


  /**
   * Inserts a new session.
   * @param companyid - the companyid
   * @param techcid - the techcid
   * @param did - the did
   * @return the inserted SessionDTO
   * @throws ServiceException
   */
  public SessionDTO insert(String companyid, String techcid, String did) 
		throws ServiceException
  {
    SessionDTO dto = null;
    SessionDAO dao = DAOFactory.getSessionDAO();
    try {
      HDSession session = new HDSession();
	  if( session != null ) {
	    // set the companyid, techid, did
		session.setCompanyid(companyid);
		session.setTechcid(techcid);
		session.setDid(did);
		// Use a default that hibernate will tolerate 01-02-1970 00:00:00
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 1, 1, 0, 0, 0);
		Date dt = cal.getTime();
		session.setEnddatetime(dt);
		session.setStartdatetime(dt);
		// update the database
		dao.update(session);
		dto = SessionAssembler.createSessionDTO(session);
	  }
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return dto;
  }


}


