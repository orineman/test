package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.HDSession;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.service.dto.SessionDTO;

import java.util.List;

/**
 * This interface defines the methods supported by Session DAO
 * implementions.
 */
public interface SessionDAO
{
  /**
   * Gets an company given a companyid.
   *
   * @param id - the companyid
   * @return The list of sessions for that id or null if this companyid does not
   *         exist.
   * @throws com.infowave.c4p.datasource.DataException
   */
  public List getByCompanyid(String id)
          throws DataException;

  /*
   * Gets a session given the id combo of companyid, techcid, and did.
   *
   * @param companyid - the companyid
   * @param techcid - the techcid
   * @param did - the did
   * @return The session for this id combo or null if this combo does not
   *         exist.
   * @throws com.infowave.c4p.datasource.DataException
   */
  public HDSession getByTripleid(String companyid, String techcid, String did)
          throws DataException;

  /*
   * Gets an available session given the id combo of companyid, techcid, and did. An available session
   * is one where the enddatetime has not been set.
   *
   * @param companyid - the companyid
   * @param techcid - the techcid
   * @param did - the did
   * @return The session for this id combo or null if this combo does not
   *         exist.
   * @throws com.infowave.c4p.datasource.DataException
   */
  public HDSession getAvailableByTripleid(String companyid, String techcid, String did)
          throws DataException;

  /**
   * Update a session.
   * @param session - the session to update.
   * @throws DataException
   */
  public void update(HDSession session) throws DataException;

  /**
   * Count the number of sessions with this did.
   * @param did 
   * @return the number of sessions with this did (1 or 0)
   * @throws DataException
   */
  public int countSessionsByDid(String did)
          throws DataException;

  /*
   * Gets a session given the did.
   *
   * @param did - the did
   * @return The session for this did or null if this did does not
   *         exist.
   * @throws com.infowave.c4p.datasource.DataException
   */
  public HDSession getByDid(String did)
          throws DataException;

}
