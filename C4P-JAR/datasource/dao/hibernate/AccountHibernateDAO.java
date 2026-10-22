package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Authentication;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.Reseller;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;



/**
 * Concrete Account DAO.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class AccountHibernateDAO extends AbstractDAO implements AccountDAO {

  private static final String GET_BY_USERNAME_QUERY = "Account.by.username";
  private static final String GET_BY_ID_QUERY = "Account.by.id";
  private static final String GET_COUNT_LIKE_USERNAME_QUERY = "Count.accounts.like.username";
  private static final String GET_COUNT_LIKE_FIRSTNAME_QUERY = "Count.accounts.like.firstname";
  private static final String GET_COUNT_LIKE_LASTNAME_QUERY = "Count.accounts.like.lastname";
  private static final String GET_COUNT_LIKE_EMAIL_QUERY = "Count.accounts.like.email";
  private static final String GET_LIKE_USERNAME_QUERY = "Account.like.username";
  private static final String GET_LIKE_FIRSTNAME_QUERY = "Account.like.firstname";
  private static final String GET_LIKE_LASTNAME_QUERY = "Account.like.lastname";
  private static final String GET_LIKE_EMAIL_QUERY = "Account.like.email";
  private static final String USERNAME_ATTRIBUTE = "username";
  private static final String FIRSTNAME_ATTRIBUTE = "firstName";
  private static final String LASTNAME_ATTRIBUTE = "lastName";
  private static final String EMAIL_ATTRIBUTE = "email";
  private static final String ID_ATTRIBUTE = "id";


  /**
   *
   * Gets an account given a user name.
   *
   * @param username - the username
   * @return The account for that username or nul if this username does not
   *         exist.
   * @throws DataException
   */
  public Account getByUsername(String username)
          throws DataException
  {
   Account account = null;
    account = (Account) super.getSingleInstance(GET_BY_USERNAME_QUERY,
                                                USERNAME_ATTRIBUTE,
                                                username);
    return account;
  }
  /**
   * Gets an account by its id
   *
   * @param id - the account's id
   * @return The account for that id or nul if this username does not
   *         exist.
   * @throws DataException
   */
  public Account getById(String id)
          throws DataException
  {
   Account account = null;
    account = (Account) super.getSingleInstance(GET_BY_ID_QUERY,ID_ATTRIBUTE,id);
    return account;
  }


  /**
   * Deletes an account given a user name.
   *
   * @param username - the username
   * @throws DataException
   */
  public void deleteByUsername(String username)
          throws DataException
  {
    super.deleteSingleInstance(GET_BY_USERNAME_QUERY,
                               USERNAME_ATTRIBUTE,
                               username);

  }

  /**
   * Creates the object it is not already persistent; otherwise, update it.
   *
   * @param account - the object to persist/update.
   * @throws DataException
   */
  public void update(Account account) throws DataException
  {
    super.save(account);
  }


  /**
   * Creates an account by persisting the Account, Authentication, and Desktop
   * objects.
   *
   * @param account
   * @param authentication
   * @param desktop - if null, we're creating an ccount without an initial
   * desktop.
   * @throws DataException
   */
  public void createAcount (Account account,
                            Authentication authentication,
                            Desktop desktop)
  throws DataException
  {
    Transaction tx = null;
    try {
      Session session = HibernateSession.currentSession();
      tx = session.beginTransaction();
      session.save(account);
      session.save(authentication);
      if (desktop != null) {
        session.save(desktop);
      }
      tx.commit();
    }
    catch (HibernateException e) {
      if (tx != null) {
        try{tx.rollback();} catch(Exception ex){}
      }
      throw new DataException ("Cannot create account",e);
    }
    finally {
     try {HibernateSession.closeSession();}catch (Exception e){}
    }
  }

  /**
   * Attachess a desktop to an existing account.
   * objects.
   *
   * @param account
   * @param desktop
   * @throws DataException
   */
  public void addDesktopToAcount (Account account,
                                  Desktop desktop)
            throws DataException
  {
    Transaction tx = null;
    try {
      Session session = HibernateSession.currentSession();
      tx = session.beginTransaction();
      //session.save(account);
      session.save(desktop);
      tx.commit();
    }
    catch (HibernateException e) {
      if (tx != null) {
        try{tx.rollback();} catch(Exception ex){}
      }
      throw new DataException ("Cannot attach the desktop",e);
    }
    finally {
     try {HibernateSession.closeSession();}catch (Exception e){}
    }
  }
  /**
   * Saves a list of accounts
   *
   * @param accounts
   * @throws DataException
   */
  public void update (List accounts)  throws DataException
  {
    Transaction tx = null;
    try {
      Session session = HibernateSession.currentSession();
      tx = session.beginTransaction();
      for (Iterator it = accounts.iterator();it.hasNext();) {
        Account account = (Account) it.next();
        session.update(account);
      }
      tx.commit();
    }
    catch (HibernateException e) {
      if (tx != null) {
        try{tx.rollback();} catch(Exception ex){}
      }
      throw new DataException ("Cannot save accounts",e);
    }
    finally {
     try {HibernateSession.closeSession();}catch (Exception e){}
    }

  }

  public List getUsersByUsername( String partialName ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_USERNAME_QUERY,
                                                USERNAME_ATTRIBUTE,
                                                partialName );
    return list ;
  }

  public List getUsersByFirstname( String partialName ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_FIRSTNAME_QUERY,
                                                FIRSTNAME_ATTRIBUTE,
                                                partialName );
    return list ;
  }

  public List getUsersByLastname( String partialName ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_LASTNAME_QUERY,
                                                LASTNAME_ATTRIBUTE,
                                                partialName );
    return list ;
  }

  public List getUsersByEmail( String partialName ) throws DataException
  {
    List list = (List)super.getMultipleInstance (GET_LIKE_EMAIL_QUERY,
                                                EMAIL_ATTRIBUTE,
                                                partialName );
    return list ;
  }


  public int countUsersByUsername( String partialName ) throws DataException
  {
   
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_USERNAME_QUERY,
                                                USERNAME_ATTRIBUTE,
                                                partialName );
    return i.intValue();
 }

  public int countUsersByFirstname( String partialName ) throws DataException
  {
   
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_FIRSTNAME_QUERY,
                                               FIRSTNAME_ATTRIBUTE,
                                                partialName );
    return i.intValue();
 }

  public int countUsersByLastname( String partialName ) throws DataException
  {
   
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_LASTNAME_QUERY,
                                                LASTNAME_ATTRIBUTE,
                                                partialName );
    return i.intValue();
 }

  public int countUsersByEmail( String partialName ) throws DataException
  {
   
    Integer i = (Integer)super.getSingleInstance(GET_COUNT_LIKE_EMAIL_QUERY,
                                                EMAIL_ATTRIBUTE,
                                                partialName );
    return i.intValue();
 }

  // --------------------------------------------------------- Test

  public static void testGetByUsername() throws Exception
  {
    AccountDAO dao = DAOFactory.getAccountDAO();
    Account account = dao.getByUsername("c4test");
    System.out.println(account);
  }

  public static void testDeleteByUsername() throws Exception
  {
    String username = "acc_0";
    AccountDAO dao = DAOFactory.getAccountDAO();
    dao.deleteByUsername(username);
  }


  public static void testCreate() throws Exception
  {
    String resellerCode = "res_0";
    ResellerDAO resellerDAO =DAOFactory.getResellerDAO();
    Reseller reseller = resellerDAO.getByCode(resellerCode);
    if (reseller == null){
      System.out.println("Cannot find reseller: " + resellerCode);
      return;
    }
    AccountDAO dao = DAOFactory.getAccountDAO();
    Account account = new Account();
    account.setUsername("acc_" + 10);
    account.setBillingId("1");
    account.setCreationDate(new Date());
    account.setEmail("acc1@jkjkj.com");
    account.setFirstName("acc1_fn" + 10);
    account.setLastName("acc1_ln" + 10);
    account.setLocale(new Locale("en"));
    //account.setRegionalSettings("settings");
    account.setSecurityAnswer("foo");
    account.setSecurityQuestion(0);
    account.setReseller(reseller);
    dao.update(account);
  }


  public static void main (String[] args)
  {
    try {
      testGetByUsername();
      //testDeleteByUsername();
      //testCreate();
      //testDeleteByUsername();

    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}