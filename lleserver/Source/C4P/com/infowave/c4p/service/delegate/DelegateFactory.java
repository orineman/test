package com.infowave.c4p.service.delegate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is part of the Business Delegate Pattern. It is responsible for
 * creating (and caching) concrete business delegates.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class DelegateFactory
 {

  private static UserManager m_userManager = null;
  private static DesktopManager m_desktopManager = null;
  private static ConfigurationManager m_configurationManager = null;
  private static LicenseManager m_licenseManager = null;
  private static RCUsageManager m_rcusageManager = null;
  private static final String USER_MANAGER_CLASS = "com.infowave.c4p.model.facade.UserManagerImpl";
  private static final String DESKTOP_MANAGER_CLASS = "com.infowave.c4p.model.facade.DesktopManagerImpl";
  private static final String AUTH_SERVICE_CLASS = "com.infowave.c4p.model.facade.AuthenticationServiceImpl";
  private static final String CONFIG_MANAGER_CLASS = "com.infowave.c4p.model.facade.ConfigurationManagerImpl";
  private static final String LICENSE_MANAGER_CLASS = "com.infowave.c4p.model.facade.LicenseManagerImpl";
  private static final String RCUSAGE_MANAGER_CLASS = "com.infowave.c4p.model.facade.RCUsageManagerImpl";

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(DelegateFactory.class);

  /**
   * Returns an instance of a UserManager.
   *
   *
   * @return an instance of an UserManger.
   */
  public static UserManager getUserManager() {
    if (m_userManager == null){
      String classname = USER_MANAGER_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_userManager = (UserManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate UserManager from : "+ classname);
      }
    }
    return m_userManager;
  }

  /**
   * Returns an instance of a UserManager.
   *
   *
   * @return an instance of an UserManger.
   */
  public static DesktopManager getDesktopManager() {
    if (m_desktopManager == null){
      String classname = DESKTOP_MANAGER_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_desktopManager = (DesktopManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate DesktopManager from : "+ classname);
      }
    }
    return m_desktopManager;
  }

  /**
   * Returns an instance of a ConfigurationManager.
   *
   *
   * @return an instance of an ConfigurationManger.
   */
  public static ConfigurationManager getConfigurationManager() {
    if (m_configurationManager == null){
      String classname = CONFIG_MANAGER_CLASS;
      try {

        Class theClass = Class.forName(classname);
        m_configurationManager = (ConfigurationManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate a ConfigurationManager from : "+ classname);
      }
    }
    return m_configurationManager;
  }
  /**
   * Returns an instance of a LicenseManager.
   *
   *
   * @return an instance of an ConfigurationManger.
   */
  public static LicenseManager getLicenseManager() {
    if (m_licenseManager == null){
      String classname = LICENSE_MANAGER_CLASS;
      try {
        Class theClass = Class.forName(classname);
        m_licenseManager = (LicenseManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate a LicenseManager from : "+ classname);
      }
    }
    return m_licenseManager;
  }
  /**
   * Returns an instance of a RCUsageManager.
   *
   *
   * @return an instance of an RCUsageManager.
   */
  public static RCUsageManager getRCUsageManager() {
    if (m_rcusageManager == null){
      String classname = RCUSAGE_MANAGER_CLASS;
      try {
        Class theClass = Class.forName(classname);
        m_rcusageManager = (RCUsageManager) theClass.newInstance();
      }
      catch (Exception e) {
        m_log.error("Cannot instanciate a RCUsageManager from : "+ classname);
      }
    }
    return m_rcusageManager;
  }

}
