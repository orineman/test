package com.infowave.c4p.model;

import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.service.ServiceException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.Date;


/**
 * This class represents a RCUsage for a machine.
 *
 */
public class RCUsage
{
  private static final long MILLIS_PER_DAY = 86400000;
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(RCUsage.class);
  /** The row's unique id */
  private String id = null;
  /** the license */
  private String licenseSerialNumber = null;
  /** the device identifier */
  private String did = null;
  /** When it is time to reset the row values */
  private Date windowExpiryDate = null;
  /** When it is time for the user to renew their license  */
  // Is null if machine is on limited rcusage
  private Date expiryDate = null;
  /** number of rc bytes transferred by this client in this window */
  private double bytes = 0;
  /** number of rc seconds consumed by the client in this window */
  private int serverSeconds = 0;
  /** number of rc seconds consumed by the client in this window */
  private  int clientSeconds = 0 ;
  /** number of rc minutes allowed per window (0 == default, 0x7fffffff == unlimited) */
  private int windowLimit = 0;
  /** number of days per window */
  private int windowSize = 0;


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
    return 0;
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

  private void setWindowSize( int size ) 
  {
    windowSize  = size;
  }
// ------------------------------------------------------------- Helper methods

  private boolean isWindowExpired()
  {
    boolean hasExpired = true;
    Date now = new Date();
    if( windowExpiryDate != null && now.before( windowExpiryDate) ) {	
	// expiry is in future
	hasExpired = false;
    }
    return hasExpired;
  }

  public boolean isLicenseExpired()
  {
    boolean hasExpired = true;
    Date now = new Date();
    if( expiryDate != null && now.before( expiryDate) ) {	
	// expiry is in future
	hasExpired = false;
    }
    return hasExpired;
  }
// ------------------------------------------------------------- Public methods

  /**
    * Validates & resets rcusage information for a given machine if necessary
    */
  public boolean validate( int windowSize ) throws ServiceException
  {
	boolean needsUpdate = false;

	setWindowSize( windowSize );

	// Check if unlimited license has expired
	if( getWindowLimit() > 0 && isLicenseExpired() ) {
		// license has expired
		needsUpdate = true;
		setDefaultEntry();
	}

	// Check if window has expired
	if( isWindowExpired() ) {
		// window has expired
		needsUpdate = true;
		resetEntry();
	}

	return needsUpdate;
  }

  /** Sets the values to a default (limited) rc account */
  public void setDefaultEntry( int windowSize ) throws ServiceException
  {
	setWindowSize( windowSize );
	setDefaultEntry();
  }

  public void clear() 
  {
  	id = null;
  	licenseSerialNumber = null;
  	did = null;
 	windowExpiryDate = null;
  	expiryDate = null;
  	bytes = 0;
  	serverSeconds = 0;
  	clientSeconds = 0 ;
 	windowLimit = 0;
  	windowSize = 0;
  }
  
  /** Sets the values to a default (limited) rc account */
  public void setDefaultEntry() throws ServiceException
  {
	// for now, leave licenseSerialNumber and expirydate alone
	setWindowLimit(0);
	resetEntry();
  }

  /** Resets the window related values */
  private void resetEntry() throws ServiceException
  {
	// Get the current date and add the window to it to get the next expiry
  	//licenseSerialNumber = null;
	long milliseconds = System.currentTimeMillis();
	milliseconds += windowSize * MILLIS_PER_DAY;
	Date nextWindowExpiry = new Date( milliseconds );
	setBytes(0);
	setServerSeconds(0);
	setClientSeconds(0);
	setWindowExpiryDate( nextWindowExpiry );
  }








}