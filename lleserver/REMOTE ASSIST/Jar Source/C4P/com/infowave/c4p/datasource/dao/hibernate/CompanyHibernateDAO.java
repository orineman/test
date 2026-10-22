package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.dao.CompanyDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.ObjectNotFoundException;
import com.infowave.c4p.model.Company;
//import com.infowave.c4p.model.Authentication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.*;



/**
 * Concrete Company DAO.
 *
 */
public class CompanyHibernateDAO extends AbstractDAO implements CompanyDAO {

  private static final String GET_BY_COMPANYID_QUERY = "Company.by.companyid";
  private static final String GET_COUNT_LIKE_COMPANYID_QUERY = "Company.Count.like.companyid";
  private static final String GET_COUNT_LIKE_XREF_QUERY = "Company.Count.like.xref";
  private static final String GET_COUNT_LIKE_NAME_QUERY = "Company.Count.like.name";
  private static final String CANCEL_BY_XREF = "Company.Cancel.by.xref";
  private static final String GET_LIKE_COMPANYID_QUERY = "Companies.like.companyid";
  private static final String GET_LIKE_NAME_QUERY = "Companies.like.name";
  private static final String GET_LIKE_XREF_QUERY = "Companies.like.xref";
  private static final String GET_ACTIVE_RECENT_QUERY = "Company.active.recent";
  
  private static final String COMPANYID_ATTRIBUTE = "companyid";
  private static final String NAME_ATTRIBUTE = "name";
  private static final String XREF_ATTRIBUTE = "xref";
  private static final String DAYS_ATTRIBUTE = "days";


  /**
   *
   * Gets a company given a companyid.
   *
   * @param id - the companyid
   * @return The company for that id or null if this companyid does not
   *         exist.
   * @throws DataException
   */
  public Company getByCompanyid(String id)
          throws DataException
  {
	Company company = null;
    company = (Company) super.getSingleInstance(GET_BY_COMPANYID_QUERY,
                                                COMPANYID_ATTRIBUTE,
                                                id);
    return company;
  }

  public void deleteByCompanyid(String id) throws DataException
  {
    super.deleteSingleInstance(GET_BY_COMPANYID_QUERY,
                               COMPANYID_ATTRIBUTE,
                               id);
  }

  public void addCompany(Company company) throws DataException
  {
    super.save(company);
  }

  public void update(Company company)
          throws DataException
  {
    super.save(company);
  }

  public int countLikeCompanyid(String partialData)
          throws DataException
  {
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_COMPANYID_QUERY,
                                                COMPANYID_ATTRIBUTE,
                                                partialData );
    return i.intValue();
  }

  public int countLikeName(String partialData)
          throws DataException
  {
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_NAME_QUERY,
                                                NAME_ATTRIBUTE,
                                                partialData );
    return i.intValue();
  }

  public int countLikeXref(String partialData)
          throws DataException
  {
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_XREF_QUERY,
                                                XREF_ATTRIBUTE,
                                                partialData );
    return i.intValue();
  }

  public List getCompaniesLikeCompanyid(String partialData)
          throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_COMPANYID_QUERY,
                                                COMPANYID_ATTRIBUTE,
                                                partialData );
    return list ;
  }

  public List getCompaniesLikeName(String partialData)
          throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_NAME_QUERY,
                                                NAME_ATTRIBUTE,
                                                partialData );
    return list ;
  }

  public List getCompaniesLikeXref(String partialData)
          throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_XREF_QUERY,
                                                XREF_ATTRIBUTE,
                                                partialData );
    return list ;
  }

  public List getCompaniesActiveRecent(String days)
          throws DataException
  {
	  List list = (List)super.getMultipleInstance(GET_ACTIVE_RECENT_QUERY,
												  DAYS_ATTRIBUTE,
												  days);
	  return list;
  }

  public void cancelByXref(String xref) throws DataException
  {
		Map parameters = new HashMap();
		parameters.put(XREF_ATTRIBUTE, xref);
		groupUpdate(CANCEL_BY_XREF, parameters);
  }



}
