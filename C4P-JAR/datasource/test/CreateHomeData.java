package com.infowave.c4p.datasource.test;

import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.LicenseManager;
import org.hibernate.HibernateException;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Nov 7, 2003
 * Time: 9:48:11 AM
 * To change this template use Options | File Templates.
 */
public class CreateHomeData extends CreateLaplinkData
{
  protected String getResellerHostname() {return "localhost";}
  protected String getICMHostname() {return "localhost:8080";}

/*
  private void createLicenses() throws Exception
  {
    System.out.println("Creating licenses");
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();

    License license1 = new License();
    license1.setCode("1234567890123456");
    license1.setDuration(30);
    license1.setNumUses(3);
    license1.setAccount(m_account1);
    license1.setReseller(m_reseller);
    licenseDAO.update(license1);

    License license2 = new License();
    license2.setCode("1111117890123456");
    license2.setDuration(30);
    license2.setNumUses(3);
    license2.setReseller(m_reseller);
    licenseDAO.update(license2);

    License license3 = new License();
    license3.setCode("1111117890123456");
    license3.setDuration(30);
    license3.setNumUses(3);
    license3.setReseller(m_reseller);
    licenseDAO.update(license3);

    m_desktop1.setLicense(license1);
    desktopDAO.update(m_desktop1);
  }
*/

  private void setlicenses() throws Exception
  {
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    LicenseManager licenseMgr = DelegateFactory.getLicenseManager();
        String did = "LTqceHV2";
    String code = "f1";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();

    licenseMgr.setLicenseCode(account.getId(),desktop.getId(),code,false);
  }

  private void changeResellerInfo() throws Exception
  {
    ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
    Reseller reseller = resellerDAO.getByCode("laplink");
    reseller.setLicenseCodeFormat("1,1");
    resellerDAO.update(reseller);
  }


  private void changeAccounts() throws Exception
  {
     Desktop  desktop = new Desktop();
    desktop.setDid("foobar");
    desktop.setName("My PC2");
    desktop.setOwner(m_account1.getUsername());
    m_account1.addDesktop(desktop);

    DAOFactory.getDesktopDAO().update(desktop);
    DAOFactory.getAccountDAO().update(m_account1);

  }
  public  void createData () throws HibernateException, Exception
  {
    super.createData();
    changeResellerInfo();
    setlicenses();
    changeAccounts();

  }
    public static void main (String[] args)
  {
    CreateHomeData h = new CreateHomeData();
    try {
      h.createData();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
