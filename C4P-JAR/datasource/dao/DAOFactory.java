package com.infowave.c4p.datasource.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Vincent AUmont
 */

/**
 * This class is responsible for creating (and caching) DAO.
 *
 * Right now the concrete DAO classes are hard-coded. We will change this when we
 * need different types of DAOs.
 *
 *
 */
public class DAOFactory
{
  private static AccountDAO m_accountDAO = null;
  private static DesktopDAO m_desktopDAO = null;
  private static ResellerDAO m_resellerDAO = null;
  private static RegistrationCodeDAO m_registrationCodeDAO = null;
  private static LicenseDAO m_licenseDAO = null;
  private static AuthenticationDAO m_authenticationDAO = null;
  private static ICMDAO m_icmDAO = null;
  private static RCUsageDAO m_rcUsageDAO = null;

  private static final String ACCOUNT_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.AccountHibernateDAO";
  private static final String DESKTOP_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.DesktopHibernateDAO";
  private static final String RESELLER_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.ResellerHibernateDAO";
  private static final String REGCODE_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.RegistrationCodeHibernateDAO";
  private static final String LICENSE_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.LicenseHibernateDAO";
  private static final String AUTHENTICATION_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.AuthenticationHibernateDAO";
  private static final String ICM_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.ICMHibernateDAO";
  private static final String RCUSAGE_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.RCUsageHibernateDAO";

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(DAOFactory.class);

  /**
   * Returns an instance of an Account DAO.
   *
   *
   * @return an instance of an Account DAO.
   */
  public static AccountDAO getAccountDAO() {
    if (m_accountDAO == null){
      String classname = ACCOUNT_DAO_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_accountDAO = (AccountDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate AccountDAO from : "+ classname);
      }
    }
    return m_accountDAO;
  }

  /**
   * Returns an instance of a Desktop DAO.
   *
   *
   * @return an instance of a Desktop DAO.
   */
  public static DesktopDAO getDesktopDAO() {
    if (m_desktopDAO == null){
      String classname = DESKTOP_DAO_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_desktopDAO = (DesktopDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate DesktopDAO from : "+ classname);
      }
    }
    return m_desktopDAO;
  }

  /**
   * Returns an instance of a Reseller DAO.
   *
   *
   * @return an instance of a Reseller DAO.
   */
  public static ResellerDAO getResellerDAO() {
    if (m_resellerDAO == null){
      String classname = RESELLER_DAO_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_resellerDAO = (ResellerDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate ResellerDAO from : "+ classname);
      }
    }
    return m_resellerDAO;
  }

  /**
   * Returns an instance of a RegistrationCode DAO.
   *
   *
   * @return an instance of a RegistrationCode DAO.
   */
  public static RegistrationCodeDAO getRegistrationCodeDAO() {
    if (m_registrationCodeDAO == null){
      String classname = REGCODE_DAO_CLASS;
      try {
        Class theClass = Class.forName(classname);
        m_registrationCodeDAO = (RegistrationCodeDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate RegistrationCode from : "+ classname);
      }
    }
    return m_registrationCodeDAO;
  }
  /**
   * Returns an instance of a License DAO.
   *
   *
   * @return an instance of a License DAO.
   */
  public static LicenseDAO getLicenseDAO() {
    if (m_licenseDAO == null){
      String classname = LICENSE_DAO_CLASS;
      try {
        Class theClass = Class.forName(classname);
        m_licenseDAO = (LicenseDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate LicenseDAO from : "+ classname);
      }
    }
    return m_licenseDAO;
  }
  /**
   * Returns an instance of a License DAO.
   *
   *
   * @return an instance of a License DAO.
   */
  public static AuthenticationDAO getAuthenticationDAO() {
    if (m_authenticationDAO == null){
      String classname = AUTHENTICATION_DAO_CLASS;
      try {
        Class theClass = Class.forName(classname);
        m_authenticationDAO = (AuthenticationDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate AuthenticationDAO from : "+ classname);
      }
    }
    return m_authenticationDAO;
  }

  /**
   * Returns an instance of an ICM DAO.
   *
   *
   * @return an instance of an ICM DAO.
   */
  public static ICMDAO getICMDAO() {
    if (m_icmDAO == null){
      String classname = ICM_DAO_CLASS;
      try {
        Class theClass = Class.forName(classname);
        m_icmDAO = (ICMDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate ICMDAO from : "+ classname);
      }
    }
    return m_icmDAO;
  }

  /**
   * Returns an instance of an RCUsage DAO.
   *
   *
   * @return an instance of an RCUsage DAO.
   */
  public static RCUsageDAO getRCUsageDAO() {
    if (m_rcUsageDAO == null){
      String classname = RCUSAGE_DAO_CLASS;
     try {
        Class theClass = Class.forName(classname);
        m_rcUsageDAO  = (RCUsageDAO ) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate RCUSAGEDAO from : "+ classname);
      }
    }
    return m_rcUsageDAO;
  }


}
