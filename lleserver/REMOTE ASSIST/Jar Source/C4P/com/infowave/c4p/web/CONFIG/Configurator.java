package com.infowave.c4p.web.config;

import org.apache.struts.action.PlugIn;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.ServletException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

//import com.infowave.c4p.service.delegate.ConfigurationManager;
//import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.util.PortalException;
import com.infowave.c4p.util.ApplicationParameters;

/**
 * This class is a Struts plugin. It is rsponsible for initializig the
 *  application:
 * - retrieve the application parameters from JNDI
 * - retrieve the list of resellers from the database.
 *
 */
/**
 * This clas is responsible for configuring the web application
 */
public class Configurator implements PlugIn{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(Configurator.class);


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
   * Retrieves the class' unique instance.
   * @return
   */
  public static Configurator instance()
  {
    if (m_instance == null){
      m_log.error("The singleton has not neen created yet", new Throwable());
    }
    return m_instance;
  }
  
  // ----------------------------------------------------------- Helper methods


  /**
   * performs the initialization:
   * - retrieve the application parameters from JNDI
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
  }


}
