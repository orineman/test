package com.infowave.c4p.datasource.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is responsible for creating (and caching) DAO.
 *
 */
public class DAOFactory
{
  private static SessionDAO m_sessionDAO = null;
  private static TechnicianDAO m_technicianDAO = null;
  private static TechloginDAO m_techloginDAO = null;
  private static CompanyDAO m_companyDAO = null;

  private static final String SESSION_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.SessionHibernateDAO";
  private static final String TECHNICIAN_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.TechnicianHibernateDAO";
  private static final String TECHLOGIN_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.TechloginHibernateDAO";
  private static final String COMPANY_DAO_CLASS = "com.infowave.c4p.datasource.dao.hibernate.CompanyHibernateDAO";

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(DAOFactory.class);

  /**
   * Returns an instance of a Session DAO.
   *
   *
   * @return an instance of an Session DAO.
   */
  public static SessionDAO getSessionDAO() {
    if (m_sessionDAO == null){
      String classname = SESSION_DAO_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_sessionDAO = (SessionDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate SessionDAO from : "+ classname);
      }
    }
    return m_sessionDAO;
  }

  /**
   * Returns an instance of a Technician DAO.
   *
   *
   * @return an instance of a Technician DAO.
   */
  public static TechnicianDAO getTechnicianDAO() {
    if (m_technicianDAO == null){
      String classname = TECHNICIAN_DAO_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_technicianDAO = (TechnicianDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate TechnicianDAO from : "+ classname);
      }
    }
    return m_technicianDAO;
  }

  /**
   * Returns an instance of a Techlogin DAO.
   *
   *
   * @return an instance of a Techlogin DAO.
   */
  public static TechloginDAO getTechloginDAO() {
    if (m_techloginDAO == null){
      String classname = TECHLOGIN_DAO_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_techloginDAO = (TechloginDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate TechloginDAO from : "+ classname);
      }
    }
    return m_techloginDAO;
  }

  /**
   * Returns an instance of a Company DAO.
   *
   *
   * @return an instance of a Company DAO.
   */
  public static CompanyDAO getCompanyDAO() {
    if (m_companyDAO == null){
      String classname = COMPANY_DAO_CLASS;
      try {
        Class theClass = Class.forName(classname);
        m_companyDAO = (CompanyDAO) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instantiate CompanyDAO from : "+ classname);
      }
    }
    return m_companyDAO;
  }
}
