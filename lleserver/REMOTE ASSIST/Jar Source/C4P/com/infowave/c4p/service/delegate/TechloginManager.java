package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.TechloginDTO;
import com.infowave.c4p.model.Techlogin;
import com.infowave.c4p.service.dto.TechnicianDTO;

import java.util.List;

/**
 * This is the Business Delegate for Techlogin-related operation.
 *
 */
public interface TechloginManager
{
  /**
   * Retrieves the list of techlogin associated with a company. 
   * 
   * @param companyid - a companyid
   * @return a list of techlogin associated with this company.
   * @throws ServiceException
   */
  public List getTechloginByCompanyid(String companyid)
          throws ServiceException;

  /**
   * Returns a List of active techlogins corresponding the a techid/companyid combo.
   * @param companyid
   * @param techcid
   * @return a List of TechloginDTOs (there should only be zero or one in the list)
   * @throws ServiceException
   */
  public List getActiveByDoubleid(String companyid, String techcid)
          throws ServiceException;

  /**
   * Returns the active techlogin corresponding the loginid.
   * @param loginid -- String
   *
   * @return a techlogindto that is associated with this login id
   * @throws ServiceException
   */
  public TechloginDTO getActiveTechloginByLoginid(double loginid)
          throws ServiceException;

  /**
   * Logouts this technician and sets the flag in the database to the specified value.
   * @param loginid
   * @param flags
   *
   * @return a TechloginDTO
   * @throws ServiceException
   */
  public TechloginDTO logoutByLoginid(double loginid, double flags)
          throws ServiceException;

  /**
   * Logouts this technician.
   * @param loginid
   *
   * @return a TechloginDTO
   * @throws ServiceException
   */
  public TechloginDTO logoutByLoginid(double loginid)
          throws ServiceException;

  /**
   * Logs in this technician.
   * @param technician -- TechnicianDTO
   *
   * @return a TechloginDTO
   * @throws ServiceException
   */
  public TechloginDTO login(TechnicianDTO technician, double loginid)
          throws ServiceException;

  /**
   * Returns number of active techlogins for this company.
   * @param companyid
   *
   * @return an integer count of the number of active techlogins associated with this company
   * @throws ServiceException
   */
  public int countActiveByCompanyid(String companyid)
          throws ServiceException;
  public int countByCompanyid(String companyid)
          throws ServiceException;

  /**
   * Logouts this technician.
   * @param loginid -- String
   *
   * @return a count of the number of logins that are associated with this login id
   * @throws ServiceException
   */
  public int countTechloginsByLoginid(double loginid)
          throws ServiceException;

  /**
   * Returns the number of active techlogins using this loginid.
   * @param loginid -- String
   *
   * @return a count of the number of logins that are associated with this login id (0 or 1)
   * @throws ServiceException
   */
  public int countActiveTechloginsByLoginid(double loginid)
          throws ServiceException;

  public List getActiveByTechcid(String techcid)
          throws ServiceException;

}
