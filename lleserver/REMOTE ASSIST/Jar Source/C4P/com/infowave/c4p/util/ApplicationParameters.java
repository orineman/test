package com.infowave.c4p.util;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.io.File;

/**
 * The parameters used by the web layer.
 */
public class ApplicationParameters
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ApplicationParameters.class);

  /** The server we use to send mails */
  private String smtpServer;

  /** The maximum count for web page display */
  private int maxWebCount;

  /** The class's unique instance */
  private static ApplicationParameters m_instance;

  /**
   * This constructor should be called once by doing a JNDI lookup
   */
  public ApplicationParameters()
  {
    if (m_instance == null) {
      m_instance = this;
    } else {
      throw new IllegalStateException("The no-parameters constructor should be called only once");
    }
  }

// -------------------------------------------------------------- public methods
  public static ApplicationParameters instance(){
    if (m_instance == null){
      throw new IllegalStateException("The class's unique instance has not been created");
    }
    return m_instance;
  }

  public String getSmtpServer()
  {
    return smtpServer;
  }

  public void setMaxWebCount(int count)
  {
    maxWebCount = count;
  }

  public int getMaxWebCount()
  {
    return maxWebCount;
  }

  public void setSmtpServer(String smtpServer)
  {
    this.smtpServer = smtpServer;
  }

  /**
   * Logs the application parameters.
   */
  public void log()
  {
    m_log.info("SMTP server: " + smtpServer);
  }

}
