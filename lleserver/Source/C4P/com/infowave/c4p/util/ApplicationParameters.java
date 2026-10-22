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

  /** Full path of the upload directory */
  private String uploadDirectoryPath = null;

  /** The server we use to send mails */
  private String smtpServer;

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
  public String getUploadDirectoryPath()
  {
    return uploadDirectoryPath;
  }

/**
 * Sets the directory we use to temporily store the files the users upload
 * to their deskops. We make sure the directory is writable/readable.
 * @param uploadDirectoryPath
 */
  public void setUploadDirectoryPath(String uploadDirectoryPath)
  {
    String errMessage = null;
    this.uploadDirectoryPath = uploadDirectoryPath;
    File uploadDir = new File(uploadDirectoryPath);
    if (uploadDir.exists()){
      if (!uploadDir.isDirectory()) {
        errMessage = "Invalid upload directory path: " + uploadDirectoryPath +
                    " exists and is not a directory";
      } else if (!uploadDir.canRead()) {
        errMessage = "Invalid upload directory path: " + uploadDirectoryPath +
                    " is not readdable";
      } else if(!uploadDir.canWrite()) {
        errMessage = "Invalid upload directory path: " + uploadDirectoryPath +
                    " is not writable";
      }
    } else if (!uploadDir.mkdir()) {
      errMessage = "Cannot create upload directory: " + uploadDirectoryPath;
    }
    if (errMessage != null) {
      m_log.error(errMessage);
      throw new IllegalArgumentException(errMessage);
    }
  }

  public String getSmtpServer()
  {
    return smtpServer;
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
    m_log.info("Upload directory: " + uploadDirectoryPath);
  }

}
