package com.infowave.c4p.datasource.test;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.model.*;
import org.hibernate.HibernateException;

import java.util.*;
/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class CreateTestData {

  //SessionFactory sf;
  //Configuration cfg;
  //Transaction transaction;
  //Session session;
  //Connection conn;

  Reseller[] m_resellers;
  ICM[] m_icms;

  // key: reseller's code - values: lists of reg codes
  Hashtable  m_accounts = new Hashtable();
  Hashtable m_desktops = new Hashtable();
  Hashtable m_registrationCodes = new Hashtable();
  Hashtable m_licenses = new Hashtable();

  int m_nbResellers = 3;
  //int m_nbICMs = 2;
  // constraints:
  //             - m_nbRegistrationCodes > m_nbDesktops
  //             - m_nbLicenses > m_nbDesktops
  // Rem: these numbers are per resellers.
  int m_nbDesktops = 10;
  int m_nbAccounts = 4;
  int m_nbRegistrationCodes = m_nbDesktops + 10;
  int m_nbLicenses = m_nbDesktops + 10;


  private void createRegCodes() throws Exception
  {
    System.out.println("Creating reg codes");
    for (int iReseller = 0;iReseller<m_nbResellers;iReseller++){
      List codes = new ArrayList();
      for (int i=0;i<m_nbRegistrationCodes;i++) {
        RegistrationCode regCode = new RegistrationCode();
        regCode.setCode("regCode_" + iReseller + "." + i);
        regCode.setDescription("a description");
        regCode.setReseller(m_resellers[iReseller]);
        DAOFactory.getRegistrationCodeDAO().update(regCode);
        codes.add(regCode);
      }
      m_registrationCodes.put(m_resellers[iReseller].getCode(),
                              codes);
    }
  }

  private void createLicenses() throws Exception
  {
    System.out.println("Creating licenses");
    for (int iReseller = 0;iReseller<m_nbResellers;iReseller++){
      List licenses = new ArrayList();
      for (int i=0;i<m_nbLicenses;i++) {
        License license = new License();
        //license.setSerialNumber("serial_"+iReseller+"."+i);
        //license.setCreationDate(new Date());
        //license.setAllocatedDate(new Date());
        license.setReseller(m_resellers[iReseller]);
        DAOFactory.getLicenseDAO().update(license);
        licenses.add(license);
      }
      m_licenses.put(m_resellers[iReseller].getCode(),licenses);
    }
  }



  private void createDesktops() throws Exception
  {
    System.out.println("Creating desktops");
    for (int iReseller = 0;iReseller<m_nbResellers;iReseller++){
      Reseller reseller = m_resellers[iReseller];
      List desktops = new ArrayList();
      List regCodes = (List) m_registrationCodes.get(reseller.getCode());
      List licenses = (List) m_licenses.get(reseller.getCode());
      if (regCodes.size() < m_nbDesktops) {
        throw new Exception ("not enough registration codes");
      }
      if (licenses.size() < m_nbDesktops) {
        throw new Exception ("not enough licenses");
      }
      for (int iDesktop=0;iDesktop<m_nbDesktops;iDesktop++){
        Desktop desktop = new Desktop();
        desktop.setDid("did_" + iReseller + "." + iDesktop);
        desktop.setName("My PC " + iDesktop);
        //desktop.setRegistrationCode((RegistrationCode) regCodes.get(iDesktop));
        desktop.setLicense((License) licenses.get(iDesktop));
        DAOFactory.getDesktopDAO().update(desktop);
        desktops.add(desktop);
      }
      m_desktops.put(reseller.getCode(),desktops);
    }
  }

  private void createResellers() throws Exception
  {
    System.out.println("Creating resellers");
    m_resellers = new Reseller[m_nbResellers];

    for (int i=0;i<m_resellers.length;i++){
      m_resellers[i]= new Reseller();
      m_resellers[i].setName("reseller_" + i);
            m_resellers[i].setHostname("hotname_" + i);
      m_resellers[i].setCode("res_" + i);
      m_resellers[i].setExpiryUrl("http://www.google.com");
      DAOFactory.getResellerDAO().update(m_resellers[i]);
    }
  }

  private void createAccounts() throws Exception
  {
    System.out.println("Creating accounts");
    for (int iReseller = 0;iReseller<m_nbResellers;iReseller++){
      Reseller reseller = m_resellers[iReseller];
      List accounts = new ArrayList();
      for (int iAccount=0;iAccount<m_nbAccounts;iAccount++){
        Account account = new Account();
        String code =  iReseller + "." + iAccount;
        account = new Account();
        if ((iReseller == 0) && (iAccount == 0)) {
          account.setUsername("p");
        } else {
          account.setUsername("acc_"+ code);
        }
        account.setBillingId("1");
        account.setCreationDate(new Date());
        account.setEmail("acc1@jkjkj.com");
        account.setFirstName("acc1_fn" + code);
        account.setLastName("acc1_ln" + code);
        account.setLocale(new Locale("en"));
        //account.setRegionalSettings("settings");
        account.setSecurityAnswer("foo");
        account.setSecurityQuestion(0);
        account.setReseller(m_resellers[iReseller]);
        DAOFactory.getAccountDAO().update(account);
        accounts.add(account);
      }
      m_accounts.put(reseller.getCode(),accounts);
    }
  }

  private void createAuthentications() throws Exception
  {
    System.out.println("Creating authentications");
    for (int iReseller = 0;iReseller<m_nbResellers;iReseller++){
      Reseller reseller = m_resellers[iReseller];
      List accounts = (List) m_accounts.get(reseller.getCode());
      for (Iterator it = accounts.iterator();it.hasNext();){
        Account account = (Account) it.next();
        Authentication auth = new Authentication();
        auth.setUsername(account.getUsername());
        auth.setPassword("p");
        auth.setAuthType("laplink");
        auth.setRole(Role.USER);
        auth.setAccount(account);
        DAOFactory.getAuthenticationDAO().update(auth);
      }
    }
  }

  private void bindDesktopsToAccounts() throws Exception
  {
    System.out.println("Binding desktops to accounts");
    for (int iReseller = 0;iReseller<m_nbResellers;iReseller++){
      Reseller reseller = m_resellers[iReseller];
      List desktops = (List) m_desktops.get(reseller.getCode());
      List accounts = (List) m_accounts.get(reseller.getCode());

      for (int iDesktop=0;iDesktop<desktops.size();iDesktop++) {
        Desktop desktop = (Desktop)desktops.get(iDesktop);
        int iAccount = iDesktop%accounts.size();
        Account account = (Account)accounts.get(iAccount);
        desktop.setOwner(account.getFullName());
        account.addDesktop(desktop);
      }
    }
  }

  private void addForeignDesktops() throws Exception
  {
    System.out.println("Adding foreign desktops");
    int nbForeignDesktops = 2;
    for (int iReseller = 0;iReseller<m_nbResellers;iReseller++){
      Reseller reseller = m_resellers[iReseller];
      List desktops = (List) m_desktops.get(reseller.getCode());
      List accounts = (List) m_accounts.get(reseller.getCode());
      for(Iterator it=accounts.iterator();it.hasNext();){
        Account account = (Account)it.next();

        for (int i=0;i<nbForeignDesktops;i++ ){
          while (true){
            Desktop foreign = null;
            int seed = (int) (Math.random() * 100);
            int iDesktop = (seed)%desktops.size();
            foreign = (Desktop)desktops.get(iDesktop);
            if ((account.getForeignDesktop(foreign.getDid()) == null) &&
                    (account.getDesktop(foreign.getDid()) == null)) {
              account.addForeignDesktop(foreign);
              break;
            }
          }
        }
      }
    }
  }
private void createICMs() throws Exception
{
  System.out.println("Creating ICMs");


  // first ICM
  ICM icm1 = new ICM();
  icm1.setName("ICM1");
  icm1.setProtocol("https");
  icm1.setHostname("files.laplink.com");
  icm1.setPort(443);
  icm1.setStatusURL("http://rome.infowave.com/simulateICM.do?type=odd&did={did}");
  //icm1.setAppListURL("/?did={did}");
  icm1.setAppListURL("/L/?did={did}");
  icm1.setAppPattern("/html/(.*)");
  DAOFactory.getICMDAO().update(icm1);

  // second ICM
  ICM icm2 = new ICM();
  icm2.setName("ICM2");
  icm2.setProtocol("https");
  icm2.setHostname("files.laplink.com");
  icm2.setPort(443);
  icm2.setStatusURL("http://rome.infowave.com/simulateICM.do?type=even&did={did}");
  icm2.setAppListURL("/L/?did={did}");
  icm2.setAppPattern("(/[LMS]/(.*))");
  DAOFactory.getICMDAO().update(icm2);

}


  private  void createMyData () throws HibernateException, Exception
  {
    AccountDAO accountDAO = DAOFactory.getAccountDAO();
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
    Account account = accountDAO.getByUsername("p");
    Reseller reseller = account.getReseller();
    reseller.setHostname("rome.infowave.com");
    reseller.setName("infowave");
    Desktop desktop = (Desktop) account.getDesktops().iterator().next();
    //desktop.setDid("khPdprqn");
    desktop.setDid("LTqceHV2");
    desktopDAO.update(desktop);
    resellerDAO.update(reseller);
  }


  private  void createData () throws HibernateException, Exception
  {
    createResellers();
    createRegCodes();
    createLicenses();
    createDesktops();
    createAccounts();
    createAuthentications();
    bindDesktopsToAccounts();
    addForeignDesktops();
    createICMs();
    createMyData ();
  }


  public static void main (String[] args)
{
    CreateTestData ta = new CreateTestData();
  try {
    ta.createData();
  } catch (Exception e) {
    e.printStackTrace();
  }
}
}
