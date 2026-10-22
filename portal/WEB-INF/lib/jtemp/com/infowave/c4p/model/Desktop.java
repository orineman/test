package com.infowave.c4p.model;

import com.infowave.c4p.model.Account;
import com.infowave.c4p.commons.DisplayMode;

import java.io.*;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.*;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * This class represents a DEsktop.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class Desktop
{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(Desktop.class);

  /** How many license code we keep in the history.
   * Remark: could be defined in the reseller table */
  private static final int MAX_NB_LICENSES_IN_HISTORY = 10;

  private String id = null;
  private String did = null;
  /** the PC's name, chosen by the owner */
  private String name = null;
  /** The owner's name */
  private String owner = null;
  /** license code for that desktop. Note that this is a denormalization */
  private String licenseSerialNumber;
   private String rclicenseSerialNumber= null;
 /** When this desktop expires, null = never */
  private Date expiryDate = null;
  private Date rcexpiryDate = null;
 /** Whether this desktop is displayed in the UI */
  private boolean visible = true;
  /** When this desktop was created */
  private Date creationDate;
  private Account account = null;
  private String registrationCode= null;
  private String rcregistrationCode=null;
  private License license = null;
  private License rclicense = null;
  private String statusURL;
  /** The urls we use to diplay the desktop's application list */
  private Map m_appListUrls = new HashMap();
  /** The desktop's ICM's top url (e.g. https://www.infowave.com */
  private String icmURL = null;
  private String rdpURL = null;
  /** The regular expression that desktop applications' urls follow.
   * e.g. /([LMS])/(.*) */
   private String appUrlRegexp = null;
  /** The url to notify the ICM that a user having access to that desktop has
   * logged out */
  private String logoutURL;
  /** The account that have access to this desktop, without being their owner */
  private Set foreignAccounts;
  /** a CSV list of the last 10 license codes that have been used for this desktop */
  private String licenseHistory;
  /** The software version this desktop uses */
  private String softwareVersion;

  /** The licenses contained in m_licenseHistory (which is a csv list) */
  private List m_pastLicenseCodes = null;

  private static final String protocol = "http";
  private static final int port = 80;
  private static final String relUrl = "/?LLListServers&did=";
  private String host = "ll.ll2go.com";
  private boolean checkedLLG = false;
  private boolean isLLGAvailable = false;
  private boolean isRdpEnabled = false;

  public Set getForeignAccounts(){return foreignAccounts;}
  public void setForeignAccounts(Set foreignAccounts){ this.foreignAccounts = foreignAccounts;}

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getLicenseSerialNumber() {
    return licenseSerialNumber;
  }

  public void setLicenseSerialNumber(String licenseSerialNumber) {
    this.licenseSerialNumber = licenseSerialNumber;
  }

  public String getRCLicenseSerialNumber() {
    return rclicenseSerialNumber;
  }

  public void setRCLicenseSerialNumber(String licenseSerialNumber) {
    this.rclicenseSerialNumber = licenseSerialNumber;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Date getRCExpiryDate() {
    return rcexpiryDate;
  }

  public void setRCExpiryDate(Date expiryDate) {
    this.rcexpiryDate = expiryDate;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }


  public Account getAccount() {
    return account;
  }


  public void setAccount(Account account) {
    this.account = account;
  }

  public String getRegistrationCode() {
    return registrationCode;
  }

  public void setRegistrationCode(String registrationCode) {
    this.registrationCode = registrationCode;
  }

  public String getRCRegistrationCode() {
    return rcregistrationCode;
  }

  public void setRCRegistrationCode(String registrationCode) {
    this.rcregistrationCode = registrationCode;
  }

  public License getLicense() {
    return license;
  }

  public void setLicense(License license) {
    this.license = license;
  }

  public License getRCLicense() {
    return rclicense;
  }

  public void setRCLicense(License license) {
    this.rclicense = license;
  }



  public String getStatusURL() {
    return statusURL;
  }

  public void setStatusURL(String statusURL) {
    this.statusURL = statusURL;
  }

  public Map getAppListUrls() {
    return m_appListUrls;
  }

  public String getAppUrlRegexp() {
    return appUrlRegexp;
  }

  public void setAppUrlRegexp(String appUrlRegexp) {
    this.appUrlRegexp = appUrlRegexp;
  }

  public boolean isVisible()
  {
    return visible;
  }

  public void setVisible(boolean visible)
  {
    this.visible = visible;
  }

  public String getLicenseHistory() {
    return licenseHistory;
  }

  public void setLicenseHistory(String licenseHistory) {
    this.licenseHistory = licenseHistory;
    this.m_pastLicenseCodes = null;
  }

  public String getLogoutURL()
  {
    return logoutURL;
  }

  public void setLogoutURL(String logoutURL)
  {
    this.logoutURL = logoutURL;
  }

  public String getSoftwareVersion()
  {
    return softwareVersion;
  }

  public void setSoftwareVersion(String softwareVersion)
  {
    this.softwareVersion = softwareVersion;
  }

  public void setRdpEnabled( boolean isRdpEnabled )
  {
     this.isRdpEnabled = isRdpEnabled;
  }

  public boolean getRdpEnabled() { return isRdpEnabled; }

// ------------------------------------------------------------- Public methods

  /**
   * Returns all the license codes that are in the history
   * @return
   */
  public String[] getLicenseCodesInHistory()
  {
    String[] codes;
    // m_pastLicenseCodes is lazily initialized
    if (m_pastLicenseCodes == null){
      parseLicenseHistory();
    }
    codes = new String[m_pastLicenseCodes.size()];
    for (int i=0;i<m_pastLicenseCodes.size();i++){
      codes[i] = (String) m_pastLicenseCodes.get(i);
    }
    return codes;
  }
  /**
   * Removes all foreign access to this desktop.
   *
   */
  public void removeAllForeignAccess()
  {
    if (foreignAccounts != null) {
      foreignAccounts.clear();
    }
  }
  /**
   * Gives an account access to this desktop.
   *
   * @param account
   */
  public void addForeignAccess(Account account)
  {
    foreignAccounts.add(account);
  }
  
  /** Adds an url for a particular display mode
   *
    * @param mode
   * @param url
   */
  public void addAppListUrl(DisplayMode mode, String url){
    this.m_appListUrls.put(mode,url);
  }


  /**
   * Gets the url the url to display the deskop's applications list in a
   * particular display mode.
   * @return
   */
  public String getAppListUrl(DisplayMode mode)
  {
    return (String) m_appListUrls.get (mode);
  }

  // only handle pc more (0)
  public String getRdpURL()
  {
    return rdpURL;
  }

  public void setRdpURL(String rdpURL) {
    this.rdpURL = rdpURL;
  }


  public String getIcmURL() {
    return icmURL;
  }

  public void setIcmURL(String icmURL) {
    this.icmURL = icmURL;
  }

  /**
   * For debugging purposes.
   * @return
   */
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("Desktop: ").append("did: ").append(did).append(" ");
    sb.append("owner: ").append(owner);
    sb.append(" name: ").append(name);
    return sb.toString();
  }

  /**
   * Has this desktop used this license before?
   */
  public boolean hasUsedLicenseCode(String licenseCode)
  {
    boolean hasUsed = false;

    // m_pastLicenseCodes is lazily initialized
    if (m_pastLicenseCodes == null){
      parseLicenseHistory();
    }
    for (int i=0;i<m_pastLicenseCodes.size();i++){
      String code = (String)m_pastLicenseCodes.get(i);
      if (code.equals(licenseCode)){
        hasUsed = true;
        break;
      }
    }
    return hasUsed;
  }

  /**
   * Remove the license from this desktop.
   * @deprecated
   */
  public void unlicense()
  {
    if (license != null) {
    removeLicenseFromHistory(license);
      setLicense(null);
      setLicenseSerialNumber(null);
    setExpiryDate(null);
    }
  }
  /**
   * adds a license to the license history
   * @return true if we could add the license. false if the license history is
   * full.
   */
  public boolean addLicenseToHistory(License license)
  {
    // m_pastLicenseCodes is lazily initialized
    if (m_pastLicenseCodes == null){
      parseLicenseHistory();
    }

    if (licenseHistoryIsFull()) {
      return false;
    }
    // Add this license code to our list.
    // Remove the first license in the history if we've reached the max.
    if (m_pastLicenseCodes.size() >= MAX_NB_LICENSES_IN_HISTORY) {
        m_pastLicenseCodes.remove(0);
    }
    m_pastLicenseCodes.add(license.getCode());

    // Update the CSV
    StringBuffer buff = new StringBuffer();
    boolean first = true;
    for (int i=0;i<m_pastLicenseCodes.size();i++){
      if (first == true) {
        first = false;
      } else {
        buff.append(",");
      }
      buff.append(m_pastLicenseCodes.get(i));
    }
    licenseHistory = buff.toString();
    return true;
  }

  /**
   * Determine whether we have reached the maximum # of licenses allowed in the
   * history.
   * @return
   */
  public boolean licenseHistoryIsFull()
  {
    // m_pastLicenseCodes is lazily initialized
    if (m_pastLicenseCodes == null){
      parseLicenseHistory();
    }
    return (m_pastLicenseCodes.size() >= MAX_NB_LICENSES_IN_HISTORY);
  }
   /**
   * adds a license to the license history
   */
  public void removeLicenseFromHistory(License license)
  {
    // m_pastLicenseCodes is lazily initialized
    if (m_pastLicenseCodes == null){
      parseLicenseHistory();
    }

    for (Iterator it=m_pastLicenseCodes.iterator();it.hasNext();) {
      String code = (String) it.next();
      if (code.equals(license.getCode())) {
        it.remove();
      }
    }

    // Update the CSV
    StringBuffer buff = new StringBuffer();
    boolean first = true;
    for (int i=0;i<m_pastLicenseCodes.size();i++){
      if (first == true) {
        first = false;
      } else {
        buff.append(",");
      }
      buff.append(m_pastLicenseCodes.get(i));
    }
    licenseHistory = buff.toString();
  }



  /**
   * Converts the licenseHistory fields into a list of Strings
   */
  private void parseLicenseHistory()
  {
    m_pastLicenseCodes = new ArrayList(MAX_NB_LICENSES_IN_HISTORY);
    if (licenseHistory != null) {
      StringTokenizer tok = new StringTokenizer(licenseHistory,",");
      while (tok.hasMoreTokens()) {
        m_pastLicenseCodes.add(tok.nextElement());
      }
    }
  }

  /**
   * Determine whether the desktop is still a demo. A desktop is a demo is it
   * has no license but an expiry date.
   * @return
   */
  public boolean isDemo()
  {
    if ((getLicenseSerialNumber() == null) &&
            (getExpiryDate() != null)) {
      return true;
    }
  return false;
  }

  public boolean hasExpired()
  {
    return (getDaysToExpiry() < 0);
  }

  /**
   * returns the number of remaining days until this desktop expires.
   * If teh desktop never expires (i.e. if its license is a subscription, the
   * method returns 0.
   * If the desktop has expired, the method returns -1.
   * If we have a desktop with no expiry date and no license (this should never
   * happen), we consider it as expired.
   * @return
   */
  public int getDaysToExpiry()
  {
    int daysToExpiry;

    if (expiryDate == null ) {
      // this should never happen as a desktop with no licenses is a demo and
      // shoud have an expiry date.
      if (getLicenseSerialNumber() == null) {
        daysToExpiry = -1;
      } else {
        // the desktop does not expire
        daysToExpiry = 0;
      }
    } else {
      long now = (new Date()).getTime();
      long expiryTime = expiryDate.getTime();
      daysToExpiry = (int)((expiryTime - now) /(1000*3600*24));
      if (daysToExpiry == 0) {
        if (now < expiryTime) {
          daysToExpiry = 1;
        } else {
        daysToExpiry = -1;
        }
      } else {
        // we had one day so that we're sure we count partial days. E.g. if the
        // desktop expired on Wednesday and we check on tuesday at 1:00PM, we
        // still consider we have 2 days before it expires.
        daysToExpiry++;
      }
    }
    return daysToExpiry;
  }

  public int getDaysToRCExpiry()
  {
    int daysToExpiry;

    if (rcexpiryDate == null ) {
      // this should never happen as a desktop with no licenses is a demo and
      // shoud have an expiry date.
      if (getRCLicenseSerialNumber() == null) {
        daysToExpiry = -1;
      } else {
        // the desktop does not expire
        daysToExpiry = 0;
      }
    } else {
      long now = (new Date()).getTime();
      long expiryTime = rcexpiryDate.getTime();
      daysToExpiry = (int)((expiryTime - now) /(1000*3600*24));
      if (daysToExpiry == 0) {
        if (now < expiryTime) {
          daysToExpiry = 1;
        } else {
        daysToExpiry = -1;
        }
      } else {
        // we had one day so that we're sure we count partial days. E.g. if the
        // desktop expired on Wednesday and we check on tuesday at 1:00PM, we
        // still consider we have 2 days before it expires.
        daysToExpiry++;
      }
    }
    return daysToExpiry;
  }

    public boolean isLLGEnabled(String s)
    {
        if(s != null && s != "")
            host = s;
        return isLLGEnabled();
    }

    public boolean isLLGEnabled()
    {
        boolean flag = false;
        if(!checkedLLG)
        {
            int i = 404;
            try
            {
                String s = "http://" + host + "/?LLListServers&did=" + did;
                Socket socket = new Socket(host, 80);
                BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(socket.getOutputStream());
                bufferedoutputstream.write("GET ".getBytes());
                bufferedoutputstream.write(s.getBytes());
                bufferedoutputstream.write(" HTTP/1.1\r\n".getBytes());
                bufferedoutputstream.write("Authorization: Basic aWNaYlRlay4haa1scfllaeVaZss==\r\n".getBytes());
                bufferedoutputstream.write("host: www.ll2go.com\r\n\r\n".getBytes());
                bufferedoutputstream.flush();
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s1 = bufferedreader.readLine();
                if(s1 != null)
                {
                    int j = s1.indexOf(' ', 9);
                    if(j != -1)
                        i = Integer.parseInt(s1.substring(9, j));
                }
                for(; s1 != null; s1 = bufferedreader.readLine());
            }
            catch(IOException ioexception) { }
            if(i == 200)
                flag = true;
            isLLGAvailable = flag;
        } else
        {
            flag = isLLGAvailable;
        }
        return flag;
    }


}