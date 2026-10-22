package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.SessionDTO;
import com.infowave.c4p.model.HDSession;

import java.util.List;

/**
 * This is the Business Delegate for Session-related operation.
 *
 */
public interface SessionManager
{
  /**
   * Retrieves the list of sessions associated with a company. 
   * 
   * @param companyid - a companyid
   * @return a list of sessions associated with this company.
   * @throws ServiceException
   */
  public List getSessionByCompanyid(String companyid)
		throws ServiceException;

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
          throws ServiceException;

  /*
   * Gets an available session given the id combo of companyid, techcid, and did. 
   *
   * @param companyid - the companyid
   * @param techcid - the techcid
   * @param did - the did
   * @return The sessiondto for this id combo or null if this combo does not
   *         exist.
   * @throws com.infowave.c4p.datasource.ServiceException
   */
  public SessionDTO getAvailableByTripleid(String companyid, String techcid, String did)
          throws ServiceException;

  /**
   * Updates the session.
   * @param companyid - the companyid
   * @param techcid - the techcid
   * @param did - the did
   * @param name - the username
   * @return the updated SessionDTO
   * @throws ServiceException
   */
  public SessionDTO update(String companyid, String techcid, String did, String name) 
		throws ServiceException;

  /**
   * Count the number of sessions with this did.
   * @param did 
   * @return the number of sessions with this did (1 or 0)
   * @throws DataException
   */
  public int countSessionsByDid(String did)
          throws ServiceException;

  /**
   * Inserts a new session.
   * @param companyid - the companyid
   * @param techcid - the techcid
   * @param did - the did
   * @return the inserted SessionDTO
   * @throws ServiceException
   */
  public SessionDTO insert(String companyid, String techcid, String did) 
		throws ServiceException;

  /**
   * Closes an existing session.
   * @param did
   * @return the updated SessionDTO
   * @throws ServiceException
   */
  public SessionDTO close(String did) 
		throws ServiceException;

}
