package com.infowave.c4p.datasource.test;
/*
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.daos.HibernateSession;
*/
public class CreateTestData2 {
/*
  SessionFactory sf;
  Configuration cfg;
  Transaction transaction;
  Session session;
  Connection conn;

  Reseller[] m_resellers;
  Account[]  m_accounts;

  private void init() throws HibernateException, Exception
  {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection("jdbc:mysql://localhost/proto?user=vincent&password=vincent");
    cfg = new Configuration();
    cfg.addFile("/home/vincent/dev/portal/WEB-INF/classes/Account.hbm.xml");
    cfg.addFile("/home/vincent/dev/portal/WEB-INF/classes/Reseller.hbm.xml");
    cfg.addFile("/home/vincent/dev/portal/WEB-INF/classes/Desktop.hbm.xml");
    sf = cfg.buildSessionFactory();
  }

  private void beginTransaction()
      throws HibernateException {

      //session = sf.openSession(conn);
    session = HibernateSession.currentSession();
    transaction = session.beginTransaction();
  }

  private void endTransaction(boolean commit)
      throws HibernateException {

      if (commit) {
          transaction.commit();
      } else {
          // Don't commit the transaction, can be faster for read-only operations
          transaction.rollback();
      }
      //session.close();
    HibernateSession.closeSession();
  }



  private Reseller[] createResellers() throws HibernateException, Exception
  {
    Reseller [] resellers = new Reseller[3];

    for (int i=0;i<resellers.length;i++){
      resellers[i]= new Reseller();
      resellers[i].setName("reseller_" + i);
      resellers[i].setCode("res_" + i);
    }
    beginTransaction();
    for (int i=0;i<resellers.length;i++){
      session.save(resellers[i]);
    }
    endTransaction(true);

    return resellers;
  }

  private Account[] createAccounts() throws HibernateException, Exception
  {
    Account[] accounts = new Account[3];
    beginTransaction();

    for (int i=0;i<accounts.length;i++){
      accounts[i] = new Account();
      accounts[i].setUsername("acc_" + i);
      accounts[i].setBillingId("1");
      accounts[i].setCreationDate(new Date());
      accounts[i].setEmail("acc1@jkjkj.com");
      accounts[i].setFirstName("acc1_fn" + i);
      accounts[i].setLastName("acc1_ln" + i);
      accounts[i].setLocale("en");
      accounts[i].setRegionalSettings("settings");
      accounts[i].setSecurityAnswer("foo");
      accounts[i].setSecurityQuestion("the question");
      accounts[i].setReseller(m_resellers[0]);
      // add 2 desktop to each account
      Set desktops = new HashSet();
      accounts[i].setDesktops(desktops);
      session.save(accounts[i]);
      for (int iDesktop=0;iDesktop<2;iDesktop++){
        Desktop desktop = new Desktop();
        desktop.setDid("did_" + i + "_" + iDesktop);
        desktops.add(desktop);
        desktop.setAccount(accounts[i]);
        session.save(desktop);
      }
    }
    //for (int i=0;i<accounts.length;i++){
    //}
    endTransaction(true);

    return accounts;
  }

    private  void createData () throws HibernateException, Exception
    {
      m_resellers = createResellers();
      m_accounts = createAccounts();
    }


  public static void main (String[] args)
{
    CreateTestData2 ta = new CreateTestData2();
  try {
    ta.init();
    ta.createData();
  } catch (Exception e) {
    e.printStackTrace();
  }
}
*/
}
