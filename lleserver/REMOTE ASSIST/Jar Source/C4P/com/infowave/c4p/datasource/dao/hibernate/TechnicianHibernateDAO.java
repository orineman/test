package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.dao.TechnicianDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.ObjectNotFoundException;
import com.infowave.c4p.model.Technician;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.*;

/**
 * Concrete Technician DAO.
 *
 */
public class TechnicianHibernateDAO extends AbstractDAO implements TechnicianDAO {

  private static final String GET_BY_COMPANYID_QUERY = "Technician.by.companyid";
  private static final String GET_BY_DOUBLEID_QUERY = "Technician.by.doubleid";
  private static final String GET_BY_TECHCID_QUERY = "Technician.by.techcid";
  private static final String GET_BY_COMPANYID_TECHCID_QUERY = "Technician.by.companyid.techcid";
  private static final String GET_ACTIVE_BY_DOUBLEID_QUERY = "Technician.active.by.doubleid";
  private static final String ACTIVATE_BY_COMPANYID_QUERY = "Technician.activate.by.companyid";
  private static final String INACTIVATE_BY_COMPANYID_QUERY = "Technician.inactivate.by.companyid";
  private static final String SETACCESSLEVEL_BY_COMPANYID_QUERY = "Technician.setaccesslevel.by.companyid";
  private static final String DELETE_BY_COMPANYID_QUERY = "Technician.delete.by.companyid";
  private static final String GET_LIKE_TECHCID = "Technicians.like.techcid";
  private static final String GET_LIKE_COMPANYID_EMAIL = "Technicians.like.companyid.email";
  private static final String GET_LIKE_COMPANYID_TECHCID = "Technicians.like.companyid.techcid";
  private static final String GET_LIKE_TECHID = "Technicians.like.techid";
  private static final String GET_LIKE_NAME = "Technicians.like.name";
  private static final String GET_LIKE_EMAIL = "Technicians.like.email";
  private static final String COUNT_LIKE_TECHID = "Technician.Count.like.techid";
  private static final String COUNT_LIKE_NAME = "Technician.Count.like.name";
  private static final String COUNT_LIKE_EMAIL = "Technician.Count.like.email";
  private static final String COUNT_LIKE_TECHCID = "Technician.Count.like.techcid";
  private static final String COUNT_LIKE_COMPANYID_TECHCID = "Technician.Count.like.companyid.techcid";
  private static final String COUNT_LIKE_COMPANYID_EMAIL = "Technician.Count.like.companyid.email";
  private static final String COUNT_ACTIVE_BY_COMPANYID = "Technician.Count.active.by.companyid";
  

  private static final String COMPANYID_ATTRIBUTE = "companyid";
  private static final String TECHCID_ATTRIBUTE = "techcid";
  private static final String TECHID_ATTRIBUTE = "techid";
  private static final String NAME_ATTRIBUTE = "name";
  private static final String EMAIL_ATTRIBUTE = "email";
  private static final String ACCESSLEVEL_ATTRIBUTE = "accesslevel";

  /**
   *
   * Gets a list of technicians given a companyid.
   *
   * @param id - the companyid
   * @return The technician list for that id or null if this companyid does not
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

  public Technician getByTechcid(String techcid) throws DataException
  {
    return (Technician)super.getSingleInstance (GET_BY_TECHCID_QUERY,
                                                TECHCID_ATTRIBUTE,
                                                techcid );
  }

  public Technician getByDoubleid(String companyid, String techid)
          throws DataException
  {
		Technician technician = null;
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		parameters.put(TECHID_ATTRIBUTE, techid);
		List technicians = execute(GET_BY_DOUBLEID_QUERY, parameters, null);
		if( technicians != null && technicians.size() > 0 ) {
			technician = (Technician) technicians.get(0);
		}

		return technician;
  }

  public Technician getByTechcid(String companyid, String techcid)
          throws DataException
  {
		Technician technician = null;
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		parameters.put(TECHCID_ATTRIBUTE, techcid);
		List technicians = execute(GET_BY_COMPANYID_TECHCID_QUERY, parameters, null);
		if( technicians != null && technicians.size() > 0 ) {
			technician = (Technician) technicians.get(0);
		}

		return technician;
  }

  public Technician getActiveByDoubleid(String companyid, String techid) throws DataException
  {
		Technician technician = null;
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		parameters.put(TECHID_ATTRIBUTE, techid);
		List technicians = execute(GET_ACTIVE_BY_DOUBLEID_QUERY, parameters, null);
		if( technicians != null && technicians.size() > 0 ) {
			technician = (Technician) technicians.get(0);
		}

		return technician;
  }

  public void addTechnician(Technician technician) throws DataException
  {
    super.save(technician);
  }

  public void deleteByTechcid(String companyid, String techcid) throws DataException
  {
	Map parameters = new HashMap();
	parameters.put(COMPANYID_ATTRIBUTE, companyid);
	parameters.put(TECHCID_ATTRIBUTE, techcid);
    super.deleteSingleInstance(GET_BY_COMPANYID_TECHCID_QUERY, parameters );
  }

  public void activateByCompanyid(String companyid) throws DataException
  {
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		groupUpdate(ACTIVATE_BY_COMPANYID_QUERY, parameters);
  }

  public void inactivateByCompanyid(String companyid) throws DataException
  {
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		groupUpdate(INACTIVATE_BY_COMPANYID_QUERY, parameters);
  }

  public void setAccesslevelByCompanyid(String companyid, String accesslevel) throws DataException
  {
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		parameters.put(ACCESSLEVEL_ATTRIBUTE, accesslevel);
		groupUpdate(SETACCESSLEVEL_BY_COMPANYID_QUERY, parameters);
  }


  public int countLikeTechid(String companyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,companyid);
    parameters.put(TECHID_ATTRIBUTE,partialData);

    return super.countObjects(COUNT_LIKE_TECHID,parameters);
  }

  public int countLikeName(String companyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,companyid);
    parameters.put(NAME_ATTRIBUTE,partialData);

    return super.countObjects(COUNT_LIKE_NAME,parameters);
  }

  public int countLikeEmail(String companyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,companyid);
    parameters.put(EMAIL_ATTRIBUTE,partialData);

    return super.countObjects(COUNT_LIKE_EMAIL,parameters);
  }

  public int countLikeTechcid(String companyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,companyid);
    parameters.put(TECHCID_ATTRIBUTE,partialData);

    return super.countObjects(COUNT_LIKE_COMPANYID_TECHCID,parameters);
  }

  public int countLikeCompanyidEmail(String partialCompanyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,partialCompanyid);
    parameters.put(EMAIL_ATTRIBUTE,partialData);

    return super.countObjects(COUNT_LIKE_COMPANYID_EMAIL,parameters);
  }

  public int countLikeTechcid(String partialData)
          throws DataException
  {
    Integer i = (Integer)super.getSingleInstance(COUNT_LIKE_TECHCID,
                                                TECHCID_ATTRIBUTE,
                                                partialData );
    return i.intValue();
  }

  public int countActiveByCompanyid(String companyid)
          throws DataException
  {
    Integer i = (Integer)super.getSingleInstance(COUNT_ACTIVE_BY_COMPANYID,
                                                COMPANYID_ATTRIBUTE,
                                                companyid );
    return i.intValue();
  }


  public List getTechniciansLikeTechcid(String companyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,companyid);
    parameters.put(TECHCID_ATTRIBUTE,partialData);

    return execute(GET_LIKE_COMPANYID_TECHCID,parameters,null);
  }

  public List getTechniciansLikeTechid(String companyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,companyid);
    parameters.put(TECHID_ATTRIBUTE,partialData);

    return execute(GET_LIKE_TECHID,parameters,null);
  }

  public List getTechniciansLikeName(String companyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,companyid);
    parameters.put(NAME_ATTRIBUTE,partialData);

    return execute(GET_LIKE_NAME,parameters,null);
  }

  public List getTechniciansLikeEmail(String companyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,companyid);
    parameters.put(EMAIL_ATTRIBUTE,partialData);

    return execute(GET_LIKE_EMAIL,parameters,null);
  }

  public List getTechniciansLikeCompanyidEmail(String partialCompanyid, String partialData)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE,partialCompanyid);
    parameters.put(EMAIL_ATTRIBUTE,partialData);

    return execute(GET_LIKE_COMPANYID_EMAIL,parameters,null);
  }

  public List getTechniciansLikeTechcid(String partialData)
          throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_TECHCID,
                                                TECHCID_ATTRIBUTE,
                                                partialData );
    return list ;
  }

  public void update(Technician technician)
          throws DataException
  {
    super.save(technician);
  }

  public void deleteAllByCompanyid(String companyid) throws DataException
  {
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		groupUpdate(DELETE_BY_COMPANYID_QUERY, parameters);
  }



}


