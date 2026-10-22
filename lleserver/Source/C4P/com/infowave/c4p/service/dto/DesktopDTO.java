package com.infowave.c4p.service.dto;

import com.infowave.c4p.util.StringManager;
import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.model.ICM;
import com.infowave.c4p.model.License;
import com.infowave.c4p.datasource.dao.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.regex.Pattern;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;


/**
 * The Data Transfer Object for a Desktop.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * This class represents a desktop
 */
public class DesktopDTO implements Serializable
{
  /** The device's unique id */
  private String id = null;

  /** the device identifier */
  private String did = null;

  /** The PC's name */
  private String name = null;

  /** the url we use to retrieve this desktop's status */
  /** The desktop's owner */
  private String owner = null;

  /** The ICM url we use to retrieve this desktop's status (connected/disconnected)*/
  private String statusURL = null;

  /** The ICM url we call when the user logs out */
  private String logoutURL = null;

  /** The number of days before this desktop expires. Zero indicates the desktop
   *  does not expire. A positive value indicates the number of days before the
   *  expiry date, and a negative value indicates the number of days since the
   *  expiry date. */
  private int daysToExpiry = 0;

  /** whether the license for this desktop has expired */
  boolean hasExpired = false;
  boolean hasRCExpired = false;

  /** Whether this desktop has a fixed term license */
  private boolean isFixedTerm = false;

  /** whether this desktop is connected */
  boolean isConnected = false;

  /** The urls we use to diplay the desktop's application list */
  private Map appListUrls = new HashMap();

  /** The regular expression for application urls */
  private String appUrlRegexp = null;

  /** The pattern for the appUrlRegexp regexp */
  private Pattern appUrlPattern = null;

  /** The top url for this desktop's ICM e.g. https://www.laplink.com */
  private String icmURL = null;

 /** last used license code */
  private String licenseSerialNumber = null;
  private String rclicenseSerialNumber=null;

  /** Whether this desktop should be displayed in the desktop list */
  private boolean visible = false;

  /** When this desktop expires */
  Date expiryDate = null;
  Date rcexpiryDate = null;

  /** Whether this desktop is licensed */
  boolean isLicensed = false;
  boolean isRCLicensed = false;

  /** The software version this desktop uses */
  private String softwareVersion = null;

  /** icm for llg*/
  private static String llgStatusUrl = "https://llold.ll2go.com/?LLListServers&did={did}"; // LLE
  //private static String llgStatusUrl = "https://ll.ll2go.com/?LLListServers&did={did}";  // LLI
  private static String llgusername = "";
  private static String llgpassword = "";

  /** icm for rdp*/
  private boolean rdpEnabled = false;
  private String rdpURL = null;

  /** license types */
  private int licenseType = 0;
  private int rcLicenseType = 0;
  private String licenseTypeStr = "";
  private String rclicenseTypeStr = "";

  private static Log m_log = LogFactory.getLog(DesktopDTO.class);

  // ------------------------------------------------------- setters and getters
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isHasExpired() {
    return hasExpired;
  }

  public boolean isHasRCExpired() {
    return hasRCExpired;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public int getDaysToExpiry() {
    return daysToExpiry;
  }

  public void setDaysToExpiry(int daysToExpiry) {
    this.daysToExpiry = daysToExpiry;
  }

  public void setHasExpired(boolean hasExpired) {
    this.hasExpired = hasExpired;
  }

  public void setHasRCExpired(boolean hasExpired) {
    this.hasRCExpired = hasExpired;
  }


  public boolean isConnected() {
    return isConnected;
  }

  public void setConnected(boolean connected) {
    isConnected = connected;
  }

  public String getStatusURL() {
    return statusURL;
  }

  public void setStatusURL(String statusURL) {
    this.statusURL = statusURL;
  }

  public void setAppListUrls(Map appListUrls) {
    this.appListUrls = appListUrls;
  }

  public String getAppListUrl(DisplayMode mode){
    return (String) appListUrls.get(mode);
  }

  public String getAppUrlRegexp() {
    return appUrlRegexp;
  }

  public void setAppUrlRegexp(String appUrlRegexp) {
    this.appUrlRegexp = appUrlRegexp;
  }

  public String getIcmURL() {
    return icmURL;
  }

  public void setIcmURL(String icmURL) {
    this.icmURL = icmURL;
  }

  public String getRdpURL() {
    return rdpURL;
  }

  public void setRdpURL(String rdpURL) {
    this.rdpURL = rdpURL;
  }


  public Pattern getAppUrlPattern() {
    if ((appUrlPattern == null) && (appUrlRegexp != null)) {
      appUrlPattern = Pattern.compile(appUrlRegexp);
    }
    return appUrlPattern;
  }

  public String getLogoutURL()
  {
    return logoutURL;
  }

  public void setLogoutURL(String logoutURL)
  {
    this.logoutURL = logoutURL;
  }

  public int getLicenseType()
  {
    return licenseType;
  }

  public void setLicenseType(int licenseType)
  {
    this.licenseType = licenseType;
    if( licenseType == License.BOXED_TYPE ) setLicenseTypeStr( License.BOXED_TYPE_STRING );
    else setLicenseTypeStr( License.SUBSCRIPTION_STRING );
  }

  public int getRcLicenseType()
  {
    return rcLicenseType;
  }

  public void setRcLicenseType(int rcLicenseType)
  {
    this.rcLicenseType = rcLicenseType;
    if( rcLicenseType == License.BOXED_TYPE ) setRclicenseTypeStr( License.BOXED_TYPE_STRING );
    else setRclicenseTypeStr( License.SUBSCRIPTION_STRING );
  }

  public boolean isVisible()
  {
    return visible;
  }

  public void setVisible(boolean visible)
  {
    this.visible = visible;
  }

  public String getRclicenseSerialNumber()
  {
    return rclicenseSerialNumber;
  }

  public void setRclicenseSerialNumber(String licenseSerialNumber)
  {
    this.rclicenseSerialNumber = licenseSerialNumber;
  }

  public String getLicenseSerialNumber()
  {
    return licenseSerialNumber;
  }

  public void setLicenseSerialNumber(String licenseSerialNumber)
  {
    this.licenseSerialNumber = licenseSerialNumber;
  }

  public boolean isFixedTerm()
  {
    return isFixedTerm;
  }

  public void setFixedTerm(boolean fixedTerm)
  {
    isFixedTerm = fixedTerm;
  }

  public Date getExpiryDate()
  {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate)
  {
    this.expiryDate = expiryDate;
  }

  public Date getRcexpiryDate()
  {
    return rcexpiryDate;
  }

  public void setRcexpiryDate(Date expiryDate)
  {
    this.rcexpiryDate = expiryDate;
  }

  public boolean isLicensed()
  {
    return isLicensed;
  }

  public void setLicensed(boolean licensed)
  {
    isLicensed = licensed;
  }

  public boolean isRclicensed()
  {
    return isRCLicensed;
  }

  public void setRclicensed(boolean licensed)
  {
    isRCLicensed = licensed;
  }

  public String getSoftwareVersion()
  {
    return softwareVersion;
  }

  public void setSoftwareVersion(String softwareVersion)
  {
    this.softwareVersion = softwareVersion;
  }

  public void setRdpEnabled( boolean enabled)
  {
     rdpEnabled = enabled;
  }

  public boolean getRdpEnabled() { 
     return rdpEnabled; 
  }

  public String getLicenseTypeStr()
  {
    return licenseTypeStr;
  }

  public void setLicenseTypeStr(String licenseTypeStr)
  {
    this.licenseTypeStr= licenseTypeStr;
  }

  public String getRclicenseTypeStr()
  {
    return rclicenseTypeStr;
  }

  public void setRclicenseTypeStr(String rclicenseTypeStr)
  {
    this.rclicenseTypeStr= rclicenseTypeStr;
  }

  public String getHtmlencodedOwner()
  {
	return StringManager.htmlencode( (owner==null?"":owner) );
  }

  public String getHtmlencodedName()
  {
	return StringManager.htmlencode( (name==null?"":name) );
  }

  public String getLinkencodedOwner()
  {
	return StringManager.linkencode( (owner==null?"":owner) );
  }

  public String getLinkencodedName()
  {
	return StringManager.linkencode( (name==null?"":name) );
  }


  public void setHtmlencodedName( String name )
  {
  }

  public void setHtmlencodedOwner( String name )
  {
  }

  public void setLinkencodedName( String name )
  {
  }

  public void setLinkencodedOwner( String name )
  {
  }

  public void addDaysToExpiry( int days )
  {
      daysToExpiry += days;
      long expiryTime = expiryDate.getTime();
      long delta = days & 0xFFFFFFFF;
      delta = delta * (1000*3600*24); // milliseconds
      expiryTime += delta;
      expiryDate.setTime( expiryTime );
  }

  public boolean isLLGEnabled( )
  {
	getLLGHost();

	boolean llgEnabled = false;
	try {
                String url = ICM.substituteVariableInString(llgStatusUrl , "did", did);
	    llgEnabled = ICM.isDesktopConnected(url, llgusername, llgpassword );
 	} catch( Exception e) {
	    llgEnabled = false;
	}
	return llgEnabled;
  }

  private static void getLLGHost()
  {
       try {
	ICMDAO icmDAO = DAOFactory.getICMDAO();
    	ICM icm = icmDAO.getByUsername( "LLG" ); // actually the ICM name
      	if( icm != null ) {
               llgStatusUrl = icm.getStatusURL();
               llgusername = icm.getUsername();
               llgpassword = icm.getPassword();
      	}
       } catch( Exception e ) {
       }
  }

}

