package com.infowave.c4p.datasource.test;

import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.model.facade.LicenseManagerImpl;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.dto.UserRegistrationDTO;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Dec 4, 2003
 * Time: 6:14:51 PM
 * To change this template use Options | File Templates.
 */
public class LicenseTest
{
  private LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
  private AccountDAO accountDAO = DAOFactory.getAccountDAO();
  private DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
  private ResellerDAO resellerDAO = DAOFactory.getResellerDAO();

  private LicenseManager licenseMgr = DelegateFactory.getLicenseManager();
  private UserManager userManager = DelegateFactory.getUserManager();

  private static Log m_log = LogFactory.getLog(LicenseTest.class);

 void createAccount() throws Exception
 {
   UserRegistrationDTO regInfo = new UserRegistrationDTO();

   Reseller reseller = resellerDAO.getByCode("laplink");

   regInfo.setUsername("toto");
   regInfo.setPassword("titi");
   regInfo.setEmail("toto@ooo.com");
   regInfo.setSecurityQuestion(0);
   regInfo.setSecurityAnswer("foo");
   regInfo.setDid("aaaDID");
   regInfo.setPcName("the pc");
   regInfo.setResellerId("laplink");

   userManager.RegisterUser(regInfo);
 }

  void attachFirstLicense() throws Exception
  {
    createAccount();

    String did = "aaaDID";
    String code = "f4";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();

    m_log.debug("--------------- start test");
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),code,false);

  }

  void addFirstRegCode() throws Exception
  {
    createAccount();

    String did = "aaaDID";
    String code = "r0";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();

    m_log.debug("--------------- start test");
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),code,false);

  }



  void transferFromSubscriptionToFixed() throws Exception
  {
    createAccount();
    String did = "aaaDID";
    String subscriptionCode = "s1";
    String fixedTermCode = "f4";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();

    // create the subs
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),subscriptionCode,false);
    // switch to fix
    m_log.debug("--------------- start test");
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixedTermCode,false);
  }


    void transferFromFixedToSubscription() throws Exception
  {
    createAccount();
    String did = "aaaDID";
    String subscriptionCode = "s1";
    String fixedTermCode = "f4";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();

    // create the subs
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixedTermCode,false);
    // switch to fix
    m_log.debug("--------------- start test");
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),subscriptionCode,false);
  }

  void extendFixedTerm() throws Exception
  {
    createAccount();
    String did = "aaaDID";
    String fixed1 = "f4";
    String fixed2 = "f5";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();

    // first fixed
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixed1,false);
    // second fixed
    m_log.debug("--------------- start test");
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixed2,true);
  }

   void transferFixedTermFreeUpSeveralLicenses() throws Exception
  {
    createAccount();
    String did = "aaaDID";
    String fixed1 = "f4";
    String fixed2 = "f5";
    String fixed3 = "f6";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();

    // first fixed
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixed1,false);
    // second fixed
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixed2,true);
    // thirs fixed
    m_log.debug("--------------- start test");
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixed3,false);
  }

public void unlicense() throws Exception
{
    createAccount();
    String did = "aaaDID";
    String fixed1 = "f4";
    String fixed2 = "f5";
    String fixed3 = "f6";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();

    // first fixed
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixed1,false);
    // second fixed
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixed2,true);
    // third fixed
    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),fixed3,true);
    m_log.debug("--------------- start test");
   licenseMgr.unlicense(desktop.getId());
}

private void useADifferentRegCodeForAnotherDesktop() throws Exception
{
  createAccount();
  userManager.addDesktopToAccount("toto","titi","bbbDID","the econd pc");

  String did1 = "aaaDID";
  String did2 = "bbbDID";
  String regCode1 = "r0";
  String regCode2 = "r1";

  Desktop desktop1 = desktopDAO.getByDid(did1);
  Desktop desktop2 = desktopDAO.getByDid(did2);
  Account account = desktop1.getAccount();

  licenseMgr.setLicenseCode(account.getId(),desktop1.getId(),regCode1,false);
  m_log.debug("--------------- start test");
  licenseMgr.setLicenseCode(account.getId(),desktop2.getId(),regCode2,false);


}


/**
 * User has 2 desktops.
 * - set license L1 to desktop1
 * - unlicense desktop1
 * - set LicenseL2 to desktop2

  * @throws Exception
 */
public void scenario1() throws Exception
{
  createAccount();
  userManager.addDesktopToAccount("toto","titi","bbbDID","the econd pc");

  String did1 = "aaaDID";
  String did2 = "bbbDID";
  String licenseCode = "f4";

  Desktop desktop1 = desktopDAO.getByDid(did1);
  Desktop desktop2 = desktopDAO.getByDid(did2);
  Account account = desktop1.getAccount();

  licenseMgr.setLicenseCode(account.getId(),desktop1.getId(),licenseCode,false);
  licenseMgr.unlicense(desktop1.getId());
  m_log.debug("--------------- start test");
  licenseMgr.setLicenseCode(account.getId(),desktop2.getId(),licenseCode,false);

}

  /**
   * Add the same reg code twice
   * @throws Exception
   */

  public void scenario2() throws Exception
{
  String did = "LTqceHV2";
  String subscriptionCode = "r0";

  Desktop desktop = desktopDAO.getByDid(did);
  Account account = desktop.getAccount();

  licenseMgr.setLicenseCode(account.getId(),desktop.getId(),subscriptionCode,false);
  licenseMgr.setLicenseCode(account.getId(),desktop.getId(),subscriptionCode,true);
}


  public static void main (String[] args)
  {
    try {
      CreateHomeData data = new CreateHomeData();
      data.createData();
      LicenseTest lt = new LicenseTest();
      //lt.attachFirstLicense();
      //lt.addFirstRegCode();
      //lt.transferFromSubscriptionToFixed() ;
      //lt.extendFixedTerm();
      lt.transferFixedTermFreeUpSeveralLicenses();
      //lt.unlicense();
      //lt.scenario1();
      //lt.useADifferentRegCodeForAnotherDesktop();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }






}
