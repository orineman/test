package com.infowave.c4p.service.dto;

import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.datasource.dao.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.io.Serializable;

/*
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.io.Serializable;
*/

/**
 * The Data Transfer Object for a RCUsage.
 *
 */
/**
 * This class represents a desktop
 */
public class RCUsageDTO implements Serializable
{
  /** The row's unique id */
  private String id;
  /** the device identifier */
  private String did;
    /** the license */
  private String licenseSerialNumber;
/** When it is time to reset the row values */
  private Date windowExpiryDate;
  /** When it is time for the user to renew their license  */
  // Is null if machine is on limited rcusage
  private Date expiryDate;
  /** number of rc bytes transferred by this client in this window */
  private double bytes;
  /** number of rc seconds consumed by the client in this window */
  private int serverSeconds;
  /** number of rc seconds consumed by the client in this window */
  private  int clientSeconds;
  /** number of rc minutes allowed per window (0 == default, 0x7fffffff == unlimited) */
  private int windowLimit;



  private static Log m_log = LogFactory.getLog(RCUsageDTO.class);


  // ------------------------------------------------------- setters and getters
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getLicenseSerialNumber()
  {
    return licenseSerialNumber;
  }

  public void setLicenseSerialNumber(String licenseSerialNumber)
  {
    this.licenseSerialNumber = licenseSerialNumber;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public Date getExpiryDate()
  {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate)
  {
    this.expiryDate = expiryDate;
  }

  public Date getWindowExpiryDate()
  {
    return windowExpiryDate;
  }

  public void setWindowExpiryDate(Date windowExpiryDate)
  {
    this.windowExpiryDate = windowExpiryDate;
  }

  public double getBytes()
  {
    return bytes;
  }

  public int getLowBytes()
  {
    return 0 ;
  }

  public int getHighBytes()
  {
    return 0;
  }

  public void setBytes(double bytes)
  {
    this.bytes = bytes;
  }

  public int getClientSeconds()
  {
    return clientSeconds;
  }

  public void setClientSeconds(int clientSeconds)
  {
    this.clientSeconds= clientSeconds;
  }

  public int getServerSeconds()
  {
    return serverSeconds;
  }

  public void setServerSeconds(int serverSeconds)
  {
    this.serverSeconds = serverSeconds;
  }

  public int getWindowLimit()
  {
    return windowLimit;
  }

  public void setWindowLimit(int windowLimit)
  {
    this.windowLimit= windowLimit;
  }


  public void addDaysToExpiry( int days )
  {
      //daysToExpiry += days;
      long expiryTime = expiryDate.getTime();
      long delta = days & 0xFFFFFFFF;
      delta = delta * (1000*3600*24); // milliseconds
      expiryTime += delta;
      expiryDate.setTime( expiryTime );
  }

}

