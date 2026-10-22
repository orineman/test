package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.dao.SessionDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.ObjectNotFoundException;
import com.infowave.c4p.model.HDSession;
//import com.infowave.c4p.model.Authentication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * Concrete Session DAO.
 *
 */
public class SessionHibernateDAO extends AbstractDAO implements SessionDAO {

  private static Log m_log = LogFactory.getLog(SessionHibernateDAO.class);

  private static final String GET_BY_COMPANYID_QUERY	= "Session.by.companyid";
  private static final String GET_BY_DID_QUERY	= "Session.by.did";
  private static final String GET_AVAILABLE_BY_TRIPLEID_QUERY		= "Session.available.by.tripleid";
  private static final String GET_ACTIVE_BY_DOUBLEID_QUERY		= "Session.active.by.doubleid";
  private static final String GET_BY_TRIPLEID_QUERY		= "Session.by.tripleid";
  private static final String COUNT_BY_DID_QUERY		= "Count.sessions.by.did";

  private static final String COMPANYID_ATTRIBUTE		= "companyid";
  private static final String TECHCID_ATTRIBUTE			= "techcid";
  private static final String LOGINID_ATTRIBUTE			= "loginid";
  private static final String DID_ATTRIBUTE				= "did";
  private static final String NAME_ATTRIBUTE			= "name";
  private static final String NUMUSES_ATTRIBUTE			= "numuses";


  /**
   *
   * Gets a list of sessions given a companyid.
   *
   * @param id - the companyid
   * @return The List of sessions for that id or null if this companyid does not
   *         exist.
   * @throws DataException
   */
  public List getByCompanyid(String id)
          throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_BY_COMPANYID_QUERY,
                                                COMPANYID_ATTRIBUTE,
                                                id );
    return list;
  }

  public HDSession getByDid(String did)
          throws DataException
  {
    return (HDSession)super.getSingleInstance (GET_BY_DID_QUERY,
                                                DID_ATTRIBUTE,
                                                did );
  }
  

  public List getActiveByDoubleid(String companyid, String techcid)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(COMPANYID_ATTRIBUTE, companyid);
    parameters.put(TECHCID_ATTRIBUTE, techcid);
    return execute(GET_ACTIVE_BY_DOUBLEID_QUERY,parameters,null);
  }


  public HDSession getByTripleid(String companyid, String techcid, String did)
          throws DataException
  {
		HDSession session = null;
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		parameters.put(TECHCID_ATTRIBUTE, techcid);
		parameters.put(DID_ATTRIBUTE, did);
		List sessions = execute(GET_BY_TRIPLEID_QUERY, parameters, null);
		if( sessions != null && sessions.size() > 0 ) {
			session = (HDSession) sessions.get(0);
		}

		return session;
  }

  public HDSession getAvailableByTripleid(String companyid, String techcid, String did)
          throws DataException
  {
		HDSession session = null;
		Map parameters = new HashMap();
		parameters.put(COMPANYID_ATTRIBUTE, companyid);
		parameters.put(TECHCID_ATTRIBUTE, techcid);
		parameters.put(DID_ATTRIBUTE, did);
		List sessions = execute(GET_AVAILABLE_BY_TRIPLEID_QUERY, parameters, null);
		if( sessions != null && sessions.size() > 0 ) {
			session = (HDSession) sessions.get(0);
		}

		return session;
  }

  public void update(HDSession session) throws DataException
  {
    super.save(session);
  }

  public int countSessionsByDid(String did)
          throws DataException
  {
    Integer i = (Integer)super.getSingleInstance(COUNT_BY_DID_QUERY,
                                                DID_ATTRIBUTE,
                                                did );
    return i.intValue();
  }


}
