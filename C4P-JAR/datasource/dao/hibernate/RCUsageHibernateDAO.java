package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.datasource.dao.RCUsageDAO;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.RCUsage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete RCUsage DAO.
 *
 */
public class RCUsageHibernateDAO extends AbstractDAO implements RCUsageDAO
{
  private static final String GET_BY_DID_QUERY = "RCUsage.by.did";
  private static final String GET_BY_CODE_QUERY = "RCUsage.by.code";
  private static final String GET_LIKE_CODE_QUERY = "RCUsage.like.code";
  private static final String COUNT_LIKE_CODE_QUERY = "RCUsage.count.like.code";
  private static final String GET_BY_CODE_DID_QUERY = "RCUsage.by.codeanddid";
  private static final String DELETE_BY_DID = "RCUsage.by.did";

  private static final String DID_ATTRIBUTE = "did";
  private static final String CODE_ATTRIBUTE = "licenseSerialNumber";

  private static Log m_log = LogFactory.getLog(RCUsageHibernateDAO.class);


    public void delete(String did)
        throws DataException
    {
        super.deleteSingleInstance(DELETE_BY_DID, DID_ATTRIBUTE, did);
    }




  public RCUsage getByDid(String did)
          throws DataException
  {
    RCUsage object = (RCUsage)super.getSingleInstance(GET_BY_DID_QUERY,
                                                      DID_ATTRIBUTE,did);
    return object;
  }

  public List getByCode(String code) // List of RCUsage objects
          throws DataException
  {
    //RCUsage object = (RCUsage)super.getSingleInstance(GET_BY_CODE_QUERY,
   //                                                   CODE_ATTRIBUTE,code);
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);

    return execute(GET_BY_CODE_QUERY,parameters,null);
  }

  public RCUsage getByCodeAndDid(String code, String did)
          throws DataException
  {
    Map parameters = new HashMap();
    parameters.put(CODE_ATTRIBUTE,code);
    parameters.put(DID_ATTRIBUTE,did);

    List list = execute(GET_BY_CODE_DID_QUERY,parameters,null);
    RCUsage object = null;
    if( list != null ) {
    	if( list.size() > 1 ) {
		throw new DataException("Improper amount of rcusage data returned");
    	} else if( list.size() == 1) {
		object = (RCUsage)list.get(0);
	}
    }

    return object;
  }

  public int countRCUsagesLikeLicense( String partialData ) throws DataException
  {
    Integer i = (Integer)super.getSingleInstance(COUNT_LIKE_CODE_QUERY,
                                                CODE_ATTRIBUTE,
                                                partialData );
    return i.intValue();
  }

  public List getRCUsagesLikeLicense( String partialData ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_CODE_QUERY,
                                                CODE_ATTRIBUTE,
                                                partialData );
    return list ;
  }


  public void update(RCUsage rcusage) throws DataException
  {
    // Make sure the did exists in the desktop table
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    Desktop desktop = desktopDAO.getByDid(rcusage.getDid());
    if( desktop != null ) {
    	super.save(rcusage);
    } else {
	throw new DataException("Did [" +rcusage.getDid()+ "] does not exist.");
    }
  }


}
