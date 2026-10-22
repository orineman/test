package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.dao.TechloginDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.ObjectNotFoundException;
import com.infowave.c4p.model.Techlogin;
//import com.infowave.c4p.model.Authentication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.*;



/**
 * Concrete Techlogin DAO.
 *
 */
public class TechloginHibernateDAO extends AbstractDAO implements TechloginDAO {

  private static final String GET_BY_COMPANYID_QUERY = "Techlogin.by.companyid";
  private static final String GET_ACTIVE_BY_TECHCID_QUERY = "TechLogin.active.by.techcid";
  private static final String GET_ACTIVE_BY_DOUBLEID_QUERY = "TechLogin.active.by.doubleid";
  private static final String GET_ACTIVE_BY_LOGINID_QUERY = "TechLogin.active.by.loginid";
  private static final String GET_BY_LOGINID_QUERY = "TechLogin.by.loginid";
  private static final String COUNT_ACTIVE_BY_COMPANYID_QUERY = "TechLogin.count.active.by.companyid";
  private static final String COUNT_BY_LOGINID_QUERY = "TechLogin.count.by.loginid";
  private static final String COUNT_ACTIVE_BY_LOGINID_QUERY = "TechLogin.count.active.by.loginid";
  private static final String COUNT_ALL_BY_COMPANYID_QUERY = "TechLogin.count.all.by.companyid";
	  
  private static final String COMPANYID_ATTRIBUTE = "companyid";
  private static final String TECHCID_ATTRIBUTE = "techcid";
  private static final String LOGINID_ATTRIBUTE = "loginid";


  /**
   *
   * Gets a list of techlogins given a companyid.
   *
   * @param id - the companyid
   * @return The techlogin list for that companyid or null if this companyid does not
   *         exist.
   * @throws DataException
   */
  public List getByCompanyid(String id)
          throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_BY_COMPANYID_QUERY,
                                                COMPANYID_ATTRIBUTE,
                                                id );
    return list ;
  }

  public List getActiveByDoubleid(String companyid, String techcid)
          throws DataException
  {
	Map parameters = new HashMap();
	parameters.put(COMPANYID_ATTRIBUTE, companyid);
	parameters.put(TECHCID_ATTRIBUTE, techcid);
	return execute(GET_ACTIVE_BY_DOUBLEID_QUERY, parameters, null);
  }

  public List getActiveByTechcid(String techcid)
          throws DataException
  {
	Map parameters = new HashMap();
	parameters.put(TECHCID_ATTRIBUTE, techcid);
	return execute(GET_ACTIVE_BY_TECHCID_QUERY, parameters, null);
  }

  public Techlogin getActiveByLoginid(double loginid)
          throws DataException
  {
	Techlogin login = null;
	login = (Techlogin) super.getSingleInstance(GET_ACTIVE_BY_LOGINID_QUERY, LOGINID_ATTRIBUTE, loginid);
	return login;
  }

  public void update(Techlogin login) throws DataException
  {
    super.save(login);
  }

  public Techlogin getByLoginid(double loginid)
          throws DataException
  {
	Techlogin login = null;
	login = (Techlogin) super.getSingleInstance(GET_BY_LOGINID_QUERY, LOGINID_ATTRIBUTE, loginid);
	return login;
  }

  /**
   * Logouts this technician.
   * @param loginid
   *
   * @return a Techlogin
   * @throws ServiceException
   */
  public Techlogin logout(Techlogin login)
          throws DataException
  {
    super.save(login);
	return login;
  }

  /**
   * Logs in this technician.
   *
   * @return a TechloginDTO
   * @throws ServiceException
   */
  public Techlogin login(Techlogin login)
          throws DataException
  {
    super.save(login);
	return login;
  }

  /**
   * Returns number of active techlogins for this company.
   * @param companyid
   *
   * @return an integer count of the number of active techlogins associated with this company
   * @throws ServiceException
   */
  public int countActiveTechloginsByCompanyid(String companyid)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE, companyid);

    return super.countObjects(COUNT_ACTIVE_BY_COMPANYID_QUERY, parameters);
  }

  /**
   * Returns number of techlogins for this company.
   * @param companyid
   *
   * @return an integer count of the number of techlogins associated with this company
   * @throws ServiceException
   */
  public int countTechloginsByCompanyid(String companyid)
          throws DataException
  {
	  Map parameters = new HashMap();
	  parameters.put(COMPANYID_ATTRIBUTE, companyid);

	  return super.countObjects(COUNT_ALL_BY_COMPANYID_QUERY, parameters);
  }

  /**
   * Returns the number of techlogins using this loginid.
   * @param loginid -- String
   *
   * @return a count of the number of logins that are associated with this login id (0 or 1)
   * @throws ServiceException
   */
  public int countTechloginsByLoginid(double loginid)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(LOGINID_ATTRIBUTE, new Double(loginid));

    return super.countObjects(COUNT_BY_LOGINID_QUERY, parameters);
  }


  /**
   * Returns the number of active techlogins using this loginid.
   * @param loginid -- String
   *
   * @return a count of the number of logins that are associated with this login id (0 or 1)
   * @throws DataException
   */
  public int countActiveTechloginsByLoginid(double loginid)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(LOGINID_ATTRIBUTE, new Double(loginid));

    return super.countObjects(COUNT_ACTIVE_BY_LOGINID_QUERY, parameters);
  }

}
