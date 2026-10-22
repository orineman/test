package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.Techlogin;
import com.infowave.c4p.datasource.DataException;

import java.util.List;
/**
 * This interface defines the methods supported by Techlogin DAO implementions.
 */
public interface TechloginDAO
{

  /**
   * Retrieve a list of techlogins given a companyid.
   * @param id = companyid
   * @return list of techlogins, active and inactive, for this company
   * @throws DataException
   */
  public List getByCompanyid(String id) 
	throws DataException;

  /**
   * Get the active login list for this specific technician
   * @param companyid
   * @param techid
   * @return techlogin list of active logins found for this particular technician (should be 0 or 1)
   * @throws DataException
   */
  public List getActiveByDoubleid(String companyid, String techcid)
          throws DataException;

  public List getActiveByTechcid(String techcid)
          throws DataException;

	
  /**
   * Returns the active techlogin corresponding the loginid.
   * @param loginid -- String
   *
   * @return a techlogin that is associated with this login id
   * @throws DataException
   */
  public Techlogin getActiveByLoginid(double loginid)
          throws DataException;

  /**
   * Logouts this technician.
   * @param loginid
   *
   * @return a Techlogin
   * @throws ServiceException
   */
  public Techlogin logout(Techlogin login)
          throws DataException;

  /**
   * Logs in this technician.
   *
   * @return a TechloginDTO
   * @throws ServiceException
   */
  public Techlogin login(Techlogin login)
          throws DataException;

  /**
   * Returns number of active techlogins for this company.
   * @param companyid
   *
   * @return an integer count of the number of active techlogins associated with this company
   * @throws ServiceException
   */
  public int countActiveTechloginsByCompanyid(String companyid)
          throws DataException;

  public int countTechloginsByCompanyid(String companyid)
          throws DataException;

  /**
   * Returns the number of techlogins using this loginid.
   * @param loginid -- String
   *
   * @return a count of the number of logins that are associated with this login id (0 or 1)
   * @throws ServiceException
   */
  public int countTechloginsByLoginid(double loginid)
          throws DataException;

  /**
   * Returns the number of active techlogins using this loginid.
   * @param loginid -- String
   *
   * @return a count of the number of logins that are associated with this login id (0 or 1)
   * @throws ServiceException
   */
  public int countActiveTechloginsByLoginid(double loginid)
          throws DataException;

  /**
   * Retrieve a techlogin given a loginid.
   * @param id = loginid (String)
   * @return a techlogin, active or inactive, for this loginid
   * @throws DataException
   */
  public Techlogin getByLoginid(double loginid) 
	throws DataException;

}
