package com.infowave.c4p.datasource.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This class is responsible for providing sessions to the application.
 * Normally, the DB connections are provided by a JNDI datasource. However,
 * there are two cases where we need to use the Hibernate-provider connection
 * pool:
 *  - for the admin tools, when we run the application from the command-line
 *    outside of Tomcat.
 *  - For unit-testing.
 *
 * When we detect that we have a JNDI context, and when a datasource is
 * bounded in that context, we use it. To that end, we define the
 * hibernate.connection.datasource property in the Hibernate Configuration
 * object. If not, we assume that the Hibernate.properties file found in the
 * class-path contains alll the required properties, or that they are passed
 * via the command line.
 *
 *
 */
public class HibernateSession
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(HibernateSession.class);

  private static final String DATASOURCE_JNDI_NAME = "java:comp/env/jdbc/connectionPool";
  /** The factory we use to create sessions */
  private static SessionFactory m_sessionFactory;
  /** The session associated with that thread */
  public static final ThreadLocal m_session = new ThreadLocal();

  private static boolean m_useDatasourceFromJNDI = true;

  static {
    // Initialize Hibernate
    Configuration cfg = null;


    // Load the mapping files
    try {
//      com.mysql.jdbc.Driver
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      cfg = new Configuration();
      
      cfg.addClass(com.infowave.c4p.datasource.dao.hibernate.AccountHibernateDAO.class);
      cfg.addClass(com.infowave.c4p.datasource.dao.hibernate.ResellerHibernateDAO.class);
      cfg.addClass(com.infowave.c4p.datasource.dao.hibernate.DesktopHibernateDAO.class);
      cfg.addClass(com.infowave.c4p.datasource.dao.hibernate.LicenseHibernateDAO.class);
      cfg.addClass(com.infowave.c4p.datasource.dao.hibernate.ICMHibernateDAO.class);
      cfg.addClass(com.infowave.c4p.datasource.dao.hibernate.AuthenticationHibernateDAO.class);
      cfg.addClass(com.infowave.c4p.datasource.dao.hibernate.RCUsageHibernateDAO.class);
      m_log.debug("The Hibernate have been loaded");
    }
    catch (Exception e){
      m_log.error("Cannot Configure Hibernate",e);
      cfg = null;
    }

    // If everything is fine, we must decide if we're using a datasource
    // we retrieve from JNDI or the Hibernate-provided connection manager
    if (cfg != null) {
      try {
        Context ctx = null;
        ctx = new InitialContext();
        ctx.lookup(DATASOURCE_JNDI_NAME);
        m_useDatasourceFromJNDI = true;
        m_log.info("The datasource: " + DATASOURCE_JNDI_NAME + " is bound in " +
                   "JNDI");
      }
      catch(javax.naming.NoInitialContextException e) {
        m_log.warn("No Initial context - Using Hibernate's connection manager");
        m_useDatasourceFromJNDI = false;

      }
      catch (NamingException e){
        m_log.info("The datasource: " + DATASOURCE_JNDI_NAME + " is not bound in"+
                   " JNDI - Using Hibernate's connection manager");
        m_useDatasourceFromJNDI = false;
      }
      if (m_useDatasourceFromJNDI) {
        cfg.setProperty("hibernate.connection.datasource",DATASOURCE_JNDI_NAME);
      }
      // Now build the session factory
      try {
        m_sessionFactory = cfg.buildSessionFactory();
      }
      catch (HibernateException e) {
        m_log.error("Cannot create the session factory",e);
      }
    }
  }

  public static Session currentSession()
          throws HibernateException {
    Session s = (Session) m_session.get();
    if (s == null) {
      s = m_sessionFactory.openSession();
      m_session.set(s);
    }
    return s;
  }


  /**
   * Clsoes the DB session.
   *
   * @throws HibernateException
   */
  public static void closeSession()
          throws HibernateException
  {
    Session s = (Session) m_session.get();
    m_session.set(null);

    if (s != null){
      // If we got our connection from the datasource, close it
      /*
      if (m_useDatasourceFromJNDI) {
        if (s.connection() != null ) {
          try {s.connection().close();} catch (Exception e){};
        } else{

        }
      }
      */
      // close the session
      s.close();
    }
  }
}
