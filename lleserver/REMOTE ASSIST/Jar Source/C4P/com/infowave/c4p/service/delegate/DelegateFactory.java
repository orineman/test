package com.infowave.c4p.service.delegate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is part of the Business Delegate Pattern. It is responsible for
 * creating (and caching) concrete business delegates.
 *
 */
public class DelegateFactory
 {

  private static SessionManager m_sessionManager = null;
  private static CompanyManager m_companyManager = null;
  private static TechnicianManager m_technicianManager = null;
  private static TechloginManager m_techloginManager = null;
  private static final String SESSION_MANAGER_CLASS = "com.infowave.c4p.model.facade.SessionManagerImpl";
  private static final String COMPANY_MANAGER_CLASS = "com.infowave.c4p.model.facade.CompanyManagerImpl";
  private static final String TECHNICIAN_MANAGER_CLASS = "com.infowave.c4p.model.facade.TechnicianManagerImpl";
  private static final String TECHLOGIN_MANAGER_CLASS = "com.infowave.c4p.model.facade.TechloginManagerImpl";

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(DelegateFactory.class);

  /**
   * Returns an instance of a SessionManager.
   *
   *
   * @return an instance of an SessionManager.
   */
  public static SessionManager getSessionManager() {
    if (m_sessionManager == null){
      String classname = SESSION_MANAGER_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_sessionManager = (SessionManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate SessionManager from : "+ classname);
      }
    }
    return m_sessionManager;
  }

  /**
   * Returns an instance of a CompanyManager.
   *
   *
   * @return an instance of an CompanyManager.
   */
  public static CompanyManager getCompanyManager() {
    if (m_companyManager == null){
      String classname = COMPANY_MANAGER_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_companyManager = (CompanyManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate CompanyManager from : "+ classname);
      }
    }
    return m_companyManager;
  }

  /**
   * Returns an instance of a TechnicianManager.
   *
   *
   * @return an instance of an TechnicianManager.
   */
  public static TechnicianManager getTechnicianManager() {
    if (m_technicianManager == null){
      String classname = TECHNICIAN_MANAGER_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_technicianManager = (TechnicianManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate a TechnicianManager from : "+ classname);
      }
    }
    return m_technicianManager;
  }
  /**
   * Returns an instance of a TechloginManager.
   *
   *
   * @return an instance of an TechloginManager.
   */
  public static TechloginManager getTechloginManager() {
    if (m_techloginManager == null){
      String classname = TECHLOGIN_MANAGER_CLASS;
      try {
        Class theClass = Class.forName(classname);
        m_techloginManager = (TechloginManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate a TechloginManager from : "+ classname);
      }
    }
    return m_techloginManager;
  }

}
