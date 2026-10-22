package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.RCUsage;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.RCUsageDAO;
import com.infowave.c4p.datasource.DataException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import net.sf.hibernate.Session;
import net.sf.hibernate.Query;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Hibernate;


/**
 * Concrete Desktop DAO.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class DesktopHibernateDAO extends AbstractDAO implements DesktopDAO
{
  private static final String GET_BY_DID_QUERY = "Desktop.by.did";
  private static final String GET_BY_ID_QUERY = "Desktop.by.id";
  private static final String ACCOUNTS_WITH_FOREIGN_ACCESS_QUERY = "accounts.with.foreign.access";
  private static final String GET_COUNT_LIKE_ACCOUNTNAME_QUERY = "Count.desktops.like.accountname";
  private static final String GET_COUNT_LIKE_DESKTOPNAME_QUERY = "Count.desktops.like.desktopname";
  private static final String GET_COUNT_LIKE_LICENSE_QUERY = "Count.desktops.like.license";
  private static final String GET_COUNT_LIKE_EXPIRYDATE_QUERY = "Count.desktops.like.expirydate";
  private static final String GET_LIKE_ACCOUNTNAME_QUERY = "Desktop.like.accountname";
  private static final String GET_LIKE_DESKTOPNAME_QUERY = "Desktop.like.desktopname";
  private static final String GET_LIKE_LICENSE_QUERY = "Desktop.like.license";
  private static final String GET_LIKE_EXPIRYDATE_QUERY = "Desktop.like.expirydate";
  private static final String DID_ATTRIBUTE = "did";
  private static final String ID_ATTRIBUTE = "id";
  private static final String DESKTOPNAME_ATTRIBUTE = "name";
  private static final String ACCOUNTNAME_ATTRIBUTE = "owner";
  private static final String LICENSE_ATTRIBUTE = "licenseSerialNumber";
  private static final String EXPIRYDATE_ATTRIBUTE = "expiryDate";


 /**
   * Retrieve a desktop given it's unique id.
   * @param id
   * @return
   * @throws DataException
   */
  public Desktop getById(String id) throws DataException
 {
    Desktop object = (Desktop)super.getSingleInstance(GET_BY_ID_QUERY,
                                                      ID_ATTRIBUTE,id);

    if( object != null ) getRcInfo(object);
    return object;

 }
 /**
   * Retrieve a desktop given it's unique id. Also initialize the license
   * reference.
   * @param id
   * @return
   * @throws DataException
   */
  public Desktop getByIdWithLicense(String id) throws DataException
 {
   Desktop desktop;
   List collectionsOrProxiesToInitialize = new ArrayList(1);
   collectionsOrProxiesToInitialize.add("license");
   desktop = (Desktop)super.getSingleInstance(GET_BY_ID_QUERY,
                                              ID_ATTRIBUTE,id,
                                              collectionsOrProxiesToInitialize);

   if( desktop!= null ) getRcInfo(desktop);
   return desktop;
 }

    public void delete(String did)
        throws DataException
    {
        super.deleteSingleInstance("Desktop.by.did", "did", did);
    }



  public Desktop getByDid(String did)
          throws DataException
  {
    Desktop object = (Desktop)super.getSingleInstance(GET_BY_DID_QUERY,
                                                      DID_ATTRIBUTE,did);
     if( object!= null ) getRcInfo(object);
    return object;
  }

  /**
   * Retrieves a desktop with its list of foreign accounts initialized
   * @param did
   * @return
   * @throws DataException
   */
  public Desktop getByDidWithForeignAccounts(String did)
  throws DataException
  {
    Desktop desktop;
    List collectionsToInitialize = new ArrayList(1);
    collectionsToInitialize.add("foreignAccounts");
    desktop = (Desktop) getSingleInstance(GET_BY_DID_QUERY,DID_ATTRIBUTE,did,
                                          collectionsToInitialize);
     if( desktop!= null ) getRcInfo(desktop);
    return desktop;
  }

  public void update(Desktop desktop) throws DataException
  {
    super.save(desktop);
  }


  /**
   * Reset the foreign access to the desktop:
   *  - deny access to any account that currently has access to this desktop
   *  - grant access to the account in teh list.
   * @param desktop
   * @param accounts
   * @throws DataException
   */
  public void resetForeignAccess(Desktop desktop, List accounts)
  throws DataException
  {

    Object object = null;
    try {
      List objects = null;
      Session session = HibernateSession.currentSession();
      desktop.removeAllForeignAccess();

      for (Iterator it=accounts.iterator();it.hasNext();) {
        desktop.addForeignAccess((Account) it.next());
      }
      session.save(desktop);
    }
    catch (HibernateException e) {
      throw new DataException ("Cannot reset access for desktop " +
                               desktop.getDid(),e);
    }
    finally {
      try {HibernateSession.closeSession();}catch (Exception e){}
    }
  }

  public List getDesktopsByAccountname( String partialData ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_ACCOUNTNAME_QUERY,
                                                ACCOUNTNAME_ATTRIBUTE,
                                                partialData );
    if( list.size() > 0 ) getRcInfo( list );
    return list ;
  }

  public List getDesktopsByDesktopname( String partialData ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_DESKTOPNAME_QUERY,
                                                DESKTOPNAME_ATTRIBUTE,
                                                partialData );
   if( list.size() > 0 ) getRcInfo( list );
    return list ;
  }

  public List getDesktopsByLicense( String partialData ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_LICENSE_QUERY,
                                                LICENSE_ATTRIBUTE,
                                                partialData );
    if( list.size() > 0 ) getRcInfo( list );
    return list ;
  }

  public List getDesktopsByExpirydate( String partialData ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_EXPIRYDATE_QUERY,
                                                EXPIRYDATE_ATTRIBUTE,
                                                partialData );
    if( list.size() > 0 ) getRcInfo( list );
    return list ;
  }


  public int countDesktopsByDesktopname( String partialData ) throws DataException
  {
   
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_DESKTOPNAME_QUERY,
                                                DESKTOPNAME_ATTRIBUTE,
                                                partialData );
    return i.intValue();
 }

  public int countDesktopsByAccountname( String partialData ) throws DataException
  {
   
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_ACCOUNTNAME_QUERY,
                                               ACCOUNTNAME_ATTRIBUTE,
                                                partialData );
    return i.intValue();
 }

  public int countDesktopsByLicense( String partialData ) throws DataException
  {
   
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_LICENSE_QUERY,
                                                LICENSE_ATTRIBUTE,
                                                partialData );
    return i.intValue();
 }

  public int countDesktopsByExpirydate( String partialData ) throws DataException
  {
   
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_EXPIRYDATE_QUERY,
                                                EXPIRYDATE_ATTRIBUTE,
                                                partialData );
    return i.intValue();
 }


  private void getRcInfo( Desktop desktop )
          throws DataException
  {
    // Get the rc info assoicated with this desktop
    RCUsage rcusage = null;
    RCUsageDAO rcUsageDAO = DAOFactory.getRCUsageDAO();
    rcusage = rcUsageDAO.getByDid(desktop.getDid());
    if (rcusage != null) {
      desktop.setRCLicenseSerialNumber( rcusage.getLicenseSerialNumber() );
      desktop.setRCExpiryDate( rcusage.getExpiryDate() );
    }
	
  }


  private void getRcInfo( List list )
          throws DataException
  {
     if( list != null ) {
       Iterator iter = list.iterator();
       if( iter != null ) {
            Desktop desktop;
	while( iter.hasNext() ) {
	   desktop = (Desktop)iter.next();
	   getRcInfo( desktop ); 
            }
       }
    }
  
	
  }



// ------------------------------------------------------ Test
  public static void testGetByDid() throws Exception
  {
    DesktopDAO dao = DAOFactory.getDesktopDAO();
    String did = "LTqceHV2";
    Desktop desktop = dao.getByDid(did);
    System.out.println(desktop);
    System.out.println(desktop.getLicense());
  }

    public static void testGetByIdWithlicense() throws Exception
  {
    DesktopDAO dao = DAOFactory.getDesktopDAO();
    String did = "LTqceHV2";
    Desktop desktop = dao.getByDid(did);

    Desktop desktop2 = dao.getByIdWithLicense(desktop.getId());
    System.out.println(desktop2);
    System.out.println(desktop2.getLicense());
  }



  private static void showForeignAccess(String did) throws Exception
  {
    Desktop desktop = null;
    Session session = HibernateSession.currentSession();
    Query q = session.getNamedQuery(GET_BY_DID_QUERY);
    q.setString(DID_ATTRIBUTE,did);
    List objects = q.list();
    if (!objects.isEmpty()) {
      desktop = (Desktop)objects.get(0);
    }
    if (desktop == null) {
      System.out.println("no desktop with did: " + did);
      return;
    }
    for (Iterator it = desktop.getForeignAccounts().iterator();it.hasNext();){
      Account account = (Account) it.next();
      System.out.println(account);
    }
    session.close();
  }


  private static void testGetByDidWithForeignAccounts() throws Exception
  {
    String did = "123";
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    Desktop desktop;
    desktop = desktopDAO.getByDidWithForeignAccounts(did);
    for (Iterator it = desktop.getForeignAccounts().iterator();it.hasNext();){
      Account account = (Account) it.next();
      System.out.println(account);
    }
  }
  public static void main(String[] args)
  {
   try{
     //testGetByDid();
     testGetByIdWithlicense();
     //testResetForeignAccess();
     //testGetByDidWithForeignAccounts();
  }
    catch(Exception e) {
     e.printStackTrace();
   }
}

    private static Log m_log  = LogFactory.getLog(DesktopHibernateDAO.class);

}
