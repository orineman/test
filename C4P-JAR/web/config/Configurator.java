package com.infowave.c4p.web.config;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.ConfigurationManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.dto.ResellerDTO;
import com.infowave.c4p.util.ApplicationParameters;
import com.infowave.c4p.util.PortalException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import java.util.*;

/**
 * This class is a Struts plugin. It is rsponsible for initializig the
 *  application:
 * - retrieve the application parameters from JNDI
 * - retrieve the list of resellers from teh database.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * This clas is responsible for configuring the web application
 */
public class Configurator implements PlugIn{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(Configurator.class);

  /** List of all know resellers */
  private List m_resellers;

  /** List af resellers in a hash map keyed on the reseller's hostname */
  private Map m_resellersByHostname = new HashMap();

  /** The module's configuration */
  ModuleConfig m_moduleConfig;

  /** the class' unique instance */
  private static Configurator m_instance = null;

  // ------------------------------------------------------------ PlugIn methods
  public void destroy()
  {
  }

  public void init(ActionServlet actionServlet,
                   ModuleConfig moduleConfig) throws ServletException

  {
    m_moduleConfig = moduleConfig;
    try {
      init(actionServlet);
    }
    catch (PortalException e)
    {
      throw new ServletException(e);
    }

    m_instance = this;
  }

  // ------------------------------------------------------------ Public methods


  /**
   * Retrieves teh class' unique instance.
   * @return
   */
  public static Configurator instance()
  {
    if (m_instance == null){
      m_log.error("The singleton has not neen created yet", new Throwable());
    }
    return m_instance;
  }
  /**
   * Retrieves a reseller given its hostname.
   *
   * @param hostname - a hostname
   * @return the reseller using this hostname or null.
   */
  public ResellerDTO getResellerByHostname(String hostname)
  {
    return (ResellerDTO) m_resellersByHostname.get(hostname);
  }

  
  // ----------------------------------------------------------- Helper methods


  /**
   * performs the initialization:
   * - retrieve the application parameters from JNDI
   * - retrieve the list of resellers from teh database.
   *
   * @param actionServlet
   * @throws PortalException
   */
  private void init(ActionServlet actionServlet) throws PortalException
  {

    // Get the application's parameters fron JNDI
    try {
      // Get the server parameters
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      envCtx.lookup("bean/ApplicationParametersFactory");
      ApplicationParameters.instance().log();
    }
    catch(NamingException e){
      m_log.error("Cannot retriev the application parameters from JNDI",e);
    }
    // Get the resellers list from the DB
    getResellerList();
  }

  /**
   * Retrieves the list of resellers.
   *
   * @throws PortalException
   */
  private void getResellerList() throws PortalException
  {
    ConfigurationManager configMgr = DelegateFactory.getConfigurationManager();
    try {
      m_resellers = configMgr.getAllResellers();
    }
    catch (ServiceException e){
      m_log.error("Cannot retrieve the list of resellers");
      throw new PortalException("Cannot retrieve the list of resellers",e);
    }

    for (Iterator it  = m_resellers.iterator();it.hasNext();) {
      ResellerDTO reseller = (ResellerDTO)it.next();
      StringTokenizer tok = new StringTokenizer(reseller.getHostname(),",");
      while(tok.hasMoreTokens()) {
        String host = tok.nextToken();
        m_resellersByHostname.put(host,reseller);
        m_log.info("Added host: " + host + " for reseller: " + reseller.getName());
      }

    }
  }
}
