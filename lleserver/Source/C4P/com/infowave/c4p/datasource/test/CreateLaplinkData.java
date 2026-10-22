package com.infowave.c4p.datasource.test;

import com.infowave.c4p.model.*;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.LicenseDAO;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.util.security.PasswordEncoderFactory;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.LicenseManager;

import java.util.*;

import net.sf.hibernate.HibernateException;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */public class CreateLaplinkData
 {

  Reseller m_reseller = null;
  Reseller m_IWReseller = null;
  //Reseller m_romeReseller = null;

  Desktop m_desktop1 = null;
  Desktop m_desktop2 = null; // agha
  Desktop m_desktop3 = null; // broitblat
  Account m_account1 = null;
  Account m_account2 = null; // agha
  Account m_account3 = null; // broitblat
  Authentication m_auth1 = null;
  Authentication m_auth2 = null;  // agha
  Authentication m_auth3 = null;  // broitblat
  ICM m_icm1 = null;


  Account m_accVictor;
  Desktop m_deskVictor;
  Authentication m_authVictor;

  Account m_accFrank;
  Desktop m_deskFrank;
  Authentication m_authFrank;

  private static final String ENCODER =  "com.infowave.c4p.laplink.security.PasswordEncoderImpl";
  protected String getResellerHostname() {return "rome.infowave.com";}
  protected String getICMHostname() {return "rome.infowave.com";}



  private void createReseller() throws Exception
  {
    System.out.println("Creating resellers");
    m_reseller = new Reseller();
    m_reseller.setName("laplink");
    m_reseller.setHostname(getResellerHostname() + "," + "216.123.146.137" +
                           "," + "10.1.10.137");
    //m_reseller.setHostname("10.1.10.137");
    m_reseller.setCode("laplink");
    m_reseller.setExpiryUrl("http://www.google.com");
    m_reseller.setDefaultLocale(new Locale("en"));
    m_reseller.setNbSecurityQuestions(4);
    m_reseller.setPasswordEncoder(ENCODER);
    //m_reseller.setLicenseCodeFormat("4,4,4");
    //m_reseller.setLicenseCodeFormat("2");
    m_reseller.setLicenseCodeFormat("1,1");
    m_reseller.setDemoDuration(3);
    DAOFactory.getResellerDAO().update(m_reseller);



    m_IWReseller = new Reseller();
    m_IWReseller.setName("infowave");
    m_IWReseller.setHostname("symmetrylive.com");
    m_IWReseller.setCode("infowave");
    m_IWReseller.setExpiryUrl("http://www.infowave.com");
    m_IWReseller.setDefaultLocale(new Locale("en"));
    m_IWReseller.setNbSecurityQuestions(4);
    m_IWReseller.setPasswordEncoder(ENCODER);
    m_IWReseller.setLicenseCodeFormat("4,4,4");
    m_IWReseller.setDemoDuration(3);

    DAOFactory.getResellerDAO().update(m_IWReseller);

    /**
    m_romeReseller = new Reseller();
    m_romeReseller.setName("rome");
    m_romeReseller.setHostname("216.123.146.137");
    m_romeReseller.setCode("rome");
    m_romeReseller.setExpiryUrl("http://www.infowave.com");
    m_romeReseller.setDefaultLocale(new Locale("en"));
    m_romeReseller.setNbSecurityQuestions(4);
    m_romeReseller.setPasswordEncoder(ENCODER);
    m_romeReseller.setLicenseCodeFormat("4,4,4");
    m_romeReseller.setNbDevicesPerLicense(3);
    DAOFactory.getResellerDAO().update(m_romeReseller);
  */

  }

  private void createAccounts() throws Exception
  {
    System.out.println("Creating accounts");


    m_account1 = new Account();
    m_account1.setUsername("c4test");
    m_account1.setBillingId("1");
    m_account1.setCreationDate(new Date());
    m_account1.setEmail("acc1@jkjkj.com");
    m_account1.setFirstName("Joe");
    m_account1.setLastName("Test");
    m_account1.setLocale(new Locale("fr"));
    m_account1.setSecurityAnswer("foo");
    m_account1.setSecurityQuestion(0);
    m_account1.setReseller(m_reseller);
    DAOFactory.getAccountDAO().update(m_account1);



    m_account2 = new Account();
    m_account2.setUsername("Agha");
    m_account2.setBillingId("1");
    m_account2.setCreationDate(new Date());
    m_account2.setEmail("acc1@jkjkj.com");
    m_account2.setFirstName("Agha");
    m_account2.setLastName("Agha");
    m_account2.setLocale(new Locale("en"));
    m_account2.setSecurityAnswer("foo");
    m_account2.setSecurityQuestion(0);
    m_account2.setReseller(m_reseller);
    DAOFactory.getAccountDAO().update(m_account2);

    m_account3 = new Account();
    m_account3.setUsername("broitblat");
    m_account3.setBillingId("1");
    m_account3.setCreationDate(new Date());
    m_account3.setEmail("BarryR@sproqit.com]");
    m_account3.setFirstName("Barry");
    m_account3.setLastName("Roitblat");
    m_account3.setLocale(new Locale("en"));
    m_account3.setSecurityAnswer("foo");
    m_account3.setSecurityQuestion(0);
    m_account3.setReseller(m_reseller);
    DAOFactory.getAccountDAO().update(m_account3);

    m_accVictor = new Account();
    m_accVictor.setUsername("vtang");
    m_accVictor.setBillingId("1");
    m_accVictor.setCreationDate(new Date());
    m_accVictor.setEmail("acc1@jkjkj.com");
    m_accVictor.setFirstName("Victor");
    m_accVictor.setLastName("Tang");
    m_accVictor.setLocale(new Locale("en"));
    m_accVictor.setSecurityAnswer("foo");
    m_accVictor.setSecurityQuestion(0);
    m_accVictor.setReseller(m_reseller);
    DAOFactory.getAccountDAO().update(m_accVictor);

    m_accFrank = new Account();
    m_accFrank.setUsername("frank");
    m_accFrank.setBillingId("1");
    m_accFrank.setCreationDate(new Date());
    m_accFrank.setEmail("acc1@jkjkj.com");
    m_accFrank.setFirstName("Frank");
    m_accFrank.setLastName("--");
    m_accFrank.setLocale(new Locale("en"));
    m_accFrank.setSecurityAnswer("foo");
    m_accFrank.setSecurityQuestion(0);
    m_accFrank.setReseller(m_reseller);
    DAOFactory.getAccountDAO().update(m_accFrank);


  }

  private void createAuthentications() throws Exception
  {

    PasswordEncoder enc = PasswordEncoderFactory.instance().getEncoder(ENCODER);
    m_auth1 = new Authentication();
    m_auth1.setUsername(m_account1.getUsername());
    m_auth1.setPassword(enc.encode("infowave"));
    m_auth1.setAuthType(ENCODER);
    m_auth1.setRole(Role.USER);
    m_auth1.setAccount(m_account1);
    DAOFactory.getAuthenticationDAO().update(m_auth1);

    m_auth2 = new Authentication();
    m_auth2.setUsername(m_account2.getUsername());
    m_auth2.setPassword(enc.encode("shujaa"));
    m_auth2.setAuthType(ENCODER);
    m_auth2.setRole(Role.USER);
    m_auth2.setAccount(m_account2);
    DAOFactory.getAuthenticationDAO().update(m_auth2);

    m_auth3 = new Authentication();
    m_auth3.setUsername(m_account3.getUsername());
    m_auth3.setPassword(enc.encode("broitblat"));
    m_auth3.setAuthType(ENCODER);
    m_auth3.setRole(Role.USER);
    m_auth3.setAccount(m_account3);
    DAOFactory.getAuthenticationDAO().update(m_auth3);

    m_authVictor = new Authentication();
    m_authVictor.setUsername(m_accVictor.getUsername());
    m_authVictor.setPassword(enc.encode("vtang"));
    m_authVictor.setAuthType(ENCODER);
    m_authVictor.setRole(Role.USER);
    m_authVictor.setAccount(m_accVictor);
    DAOFactory.getAuthenticationDAO().update(m_authVictor);

    m_authFrank = new Authentication();
    m_authFrank.setUsername(m_accFrank.getUsername());
    m_authFrank.setPassword(enc.encode("frank"));
    m_authFrank.setAuthType(ENCODER);
    m_authFrank.setRole(Role.USER);
    m_authFrank.setAccount(m_accFrank);
    DAOFactory.getAuthenticationDAO().update(m_authFrank);
  }

  private void createDesktops() throws Exception
  {
    m_desktop1 = new Desktop();
    m_desktop1.setDid("LTqceHV2");
    //m_desktop1.setDid("LTqceHV3");
    m_desktop1.setName("My PC1 ");
    m_desktop1.setCreationDate(new Date());
    DAOFactory.getDesktopDAO().update(m_desktop1);

    m_desktop2 = new Desktop();
    m_desktop2.setDid("fQbL2iqx");
    m_desktop2.setName("Agha's PC");
    m_desktop2.setCreationDate(new Date());
    DAOFactory.getDesktopDAO().update(m_desktop2);

    m_desktop3 = new Desktop();
    m_desktop3.setDid("22WBpkIi");
    m_desktop3.setName("Barry's PC");
    m_desktop3.setCreationDate(new Date());
    DAOFactory.getDesktopDAO().update(m_desktop3);

    m_deskVictor = new Desktop();
    m_deskVictor.setDid("5Ai4KasZ");
    m_deskVictor.setName("Victor's PC");
    m_deskVictor.setCreationDate(new Date());
    DAOFactory.getDesktopDAO().update(m_deskVictor);

    m_deskFrank = new Desktop();
    m_deskFrank.setDid("qwqmd3vX");
    m_deskFrank.setName("Frank's PC");
    m_deskFrank.setCreationDate(new Date());
    DAOFactory.getDesktopDAO().update(m_deskFrank);
  }
  private void bindDesktopsToAccounts() throws Exception
  {
    System.out.println("Binding desktops to accounts");
    m_desktop1.setOwner(m_account1.getUsername());
    m_account1.addDesktop(m_desktop1);
    m_account1.addForeignDesktop(m_desktop2);
    m_account1.addForeignDesktop(m_deskVictor);
    m_account1.addForeignDesktop(m_deskFrank);

    m_desktop2.setOwner(m_account2.getUsername());
    m_account2.addDesktop(m_desktop2);

    m_desktop3.setOwner(m_account3.getUsername());
    m_account3.addDesktop(m_desktop3);

    m_deskVictor.setOwner(m_accVictor.getUsername());
    m_accVictor.addDesktop(m_deskVictor);

    m_deskFrank.setOwner(m_accFrank.getUsername());
    m_accFrank.addDesktop(m_deskFrank);

    DAOFactory.getDesktopDAO().update(m_desktop1);
    DAOFactory.getDesktopDAO().update(m_desktop2);
    DAOFactory.getDesktopDAO().update(m_desktop3);
    DAOFactory.getDesktopDAO().update(m_deskVictor);
    DAOFactory.getDesktopDAO().update(m_deskFrank);
    DAOFactory.getAccountDAO().update(m_account1);
    DAOFactory.getAccountDAO().update(m_account2);
    DAOFactory.getAccountDAO().update(m_account3);
    DAOFactory.getAccountDAO().update(m_accVictor);
    DAOFactory.getAccountDAO().update(m_accFrank);

  }
protected void createLicenses() throws Exception
{
  System.out.println("Creating licenses");
  // create 10 subscriptions (s1..s10), 10- fixed terms (f1...f10)
  // and 10 reg codes (r1..r10).
  List licenses = new ArrayList(30);
  LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();

  for (int i=0;i<10;i++) {
    License sub = new License();
    sub.setRegCode(false);
    sub.setDuration(0);
    sub.setMaxUses(3);

    License fixed = new License();
    fixed.setRegCode(false);
    fixed.setDuration(30);
    fixed.setMaxUses(1);

    License regCode1 = new License();
    regCode1.setRegCode(true);
    regCode1.setDuration(30);
    regCode1.setMaxUses(3);
    License regCode2 = new License();
    regCode2.setRegCode(true);
    regCode2.setDuration(30);
    regCode2.setMaxUses(3);
    License regCode3 = new License();
    regCode3.setRegCode(true);
    regCode3.setDuration(30);
    regCode3.setMaxUses(3);

    sub.setCode("s" + i);
    fixed.setCode("f" + i);
    regCode1.setCode("r" + i);
    regCode2.setCode("r" + i);
    regCode3.setCode("r" + i);

    licenses.add(sub);
    licenses.add(fixed);
    licenses.add(regCode1);
    licenses.add(regCode2);
    licenses.add(regCode3);
  }

  for (Iterator it = licenses.iterator();it.hasNext();){
    License license = (License) it.next();
    license.setNumUses(0);
    license.setReseller(m_reseller);
    licenseDAO.update(license);
  }

}
  /**
   * Create 10 fixed terms: t0-t9
   * @throws Exception
   */
protected void createMoreLicenses() throws Exception
{
  System.out.println("Creating more licenses");

  List licenses = new ArrayList(30);
  LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
  ResellerDAO resellerDAO = DAOFactory.getResellerDAO();

  Reseller reseller =resellerDAO.getByCode("laplink");
  for (int i=0;i<10;i++) {
    License fixed = new License();
    fixed.setRegCode(false);
    fixed.setDuration(365);
    fixed.setMaxUses(1);
    fixed.setCode("t" + i);
    licenses.add(fixed);
  }

  for (Iterator it = licenses.iterator();it.hasNext();){
    License license = (License) it.next();
    license.setNumUses(0);
    license.setReseller(reseller);
    licenseDAO.update(license);
  }

}

  private void addAnotherDesktopToAccount1() throws Exception
  {
    System.out.println("Adding a desktop to account 1");
    Desktop desktop = new Desktop();
    desktop.setDid("whatever2");
    //m_desktop1.setDid("LTqceHV3");
    desktop.setName("My PC3");
    desktop.setCreationDate(new Date());
    DAOFactory.getDesktopDAO().update(desktop);

    Account account1 = DAOFactory.getAccountDAO().getByUsername("c4test");
    desktop.setOwner(account1.getUsername());
    account1.addDesktop(desktop);
    DAOFactory.getDesktopDAO().update(desktop);
    DAOFactory.getAccountDAO().update(account1);

  }
  private void createICMs() throws Exception
{
  System.out.println("Creating ICMs");

  // first ICM
  m_icm1 = new ICM();
  m_icm1.setName("ICM1");
  m_icm1.setProtocol("https");
  m_icm1.setHostname("files.laplink.com");
  m_icm1.setPort(443);
  //m_icm1.setStatusURL("http://" + getICMHostname() +"/simulateICM.do?type=all&did={did}");
  m_icm1.setStatusURL("https://files.laplink.com/?LLListServers&did={did}");
  m_icm1.setLogoutURL("https://files.laplink.com/L/logout.asp?did={did}");
  m_icm1.setAppListURL("/L/home.asp?did={did}");
  //m_icm1.setAppPattern("/html/[tp]/apps/files.laplink.com/(.*)");
  m_icm1.setAppPattern("/html/[tp]/apps/(.*)");
  m_icm1.setUsername("icmadmin");
  m_icm1.setPassword("imapinhead");
  DAOFactory.getICMDAO().update(m_icm1);
}


  private void licenseDesktops() throws Exception
  {
    System.out.println("License desktops");
    LicenseManager licenseMgr = DelegateFactory.getLicenseManager();
    licenseMgr.setLicenseCode(m_account1.getId(),m_desktop1.getId(),"s7",false);
    licenseMgr.setLicenseCode(m_accVictor.getId(),m_deskVictor.getId(),"s8",false);
    licenseMgr.setLicenseCode(m_account2.getId(),m_desktop2.getId(),"s9",false);
    licenseMgr.setLicenseCode(m_account3.getId(),m_desktop3.getId(),"s6",false);
  }

  public  void createData () throws HibernateException, Exception
  {
    createReseller();
    createDesktops();
    createAccounts();
    createAuthentications();
    bindDesktopsToAccounts();
    createLicenses();
    //addForeignDesktops();
    createICMs();
    licenseDesktops();
  }


  public static void main (String[] args)
  {
    CreateLaplinkData ta = new CreateLaplinkData();
    try {
      //ta.createData();
      //ta.createMoreLicenses();
      ta.addAnotherDesktopToAccount1();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
