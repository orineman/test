package com.infowave.c4p.model;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import com.infowave.c4p.datasource.dao.ICMDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.web.util.Base64Encoder;

/**
 * This class represents an ICM.
 * 
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ICM
 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ICM.class);

  /** The name of the did variable in the url. e.g. www.foo.com/?did={did} */
  private static final String DID_VARIABLE_NAME = "did";

  /** The name of the mode variable in the url */
  private static final String MODE_VARIABLE_NAME = "mode";

  /** This regexp describes a substitution variable. e.g. {did} */
  private static final String VARIABLE_REGEXP = "(\\{([^\\s\\}]*)\\})";

  /** The pattern to match the regexp */
  private static Pattern VARIABLE_PATTERN = Pattern.compile(VARIABLE_REGEXP);

  private String id = null;
  private String name;
  private String protocol = null;
  private String hostname = null;
  private int port = -1;
  private String statusURL = null;
  private String appListURL = null;
  private String logoutURL = null;
  private String appPattern = null;
  private String username;
  private String password;

  // ---------------------------------------------------------------- Properties
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatusURL() {
    return statusURL;
  }

  public void setStatusURL(String statusURL) {
    this.statusURL = statusURL;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getAppListURL() {
    return appListURL;
  }

  public void setAppListURL(String appListURL) {
    this.appListURL = appListURL;
  }

  public String getAppPattern() {
    return appPattern;
  }

  public void setAppPattern(String appPattern) {
    this.appPattern = appPattern;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getLogoutURL()
  {
    return logoutURL;
  }

  public void setLogoutURL(String logoutURL)
  {
    this.logoutURL = logoutURL;
  }
  // ------------------------------------------------------------ Public methods

  public String toString()
  {

    return "ICM: " + name + " " + protocol + "://" + hostname + ":" + port +
            " status url: " + statusURL + " appUrl: " + appListURL +
            " appPattern: " + appPattern;
  }

  
  /**
   * Determines whether a desktop (identified by its ICM url) is connected.
   * @param icmUrl - the url that identifies this desktop.
   * e.g. icm1.bar.com/?did=123
   * @param username -- the username to use for authentication
   *  Only used when the authorization parameter is null
   * @param password -- the password to use for authentication
   *  Only used when the authorization parameter is null
   * @param authorization - the string to put in the Basic authorization header.
   * @return true if the desktop is connected.
   * @throws CannotConnectToICMException
   */
   public static boolean isDesktopConnected(String icmUrl, String username, String password )
        throws CannotConnectToICMException
    {
        boolean connected = false;
        GetMethod httpget = null;
        try
        {
            HttpClient httpclient = new HttpClient();
            httpget = new GetMethod(icmUrl);
            String authorization = "Basic " + Base64Encoder.encode(username + ":" + password);
            httpget.setRequestHeader("Authorization", authorization);
            httpclient.executeMethod(httpget);
            int code = httpget.getStatusLine().getStatusCode();
            connected = (code == 200);
        }
        catch(IOException e)
        {
            m_log.warn("Could not contact the ICM (" + icmUrl + ")", e);
            throw new CannotConnectToICMException(e);
        }
        finally
        {
            if(httpget != null)
                httpget.releaseConnection();
        }
        return connected;
    }


  /**
   * Given a did, finds the icm that manages this desktop.
   * Note that we can find the ICM only if the desktop is connected.
   * @param did - the desktop's did
   * @return the ICM that manageds this desktop if the desktop is connected;
   * false otherwise.
   */
  public static ICM findICM(String did)
  {
    ICM icm = null;
    try {
      ICMDAO dao = DAOFactory.getICMDAO();
      ICM[] icms = null;

      icms = dao.getAll();
      if (icms != null) {
        String icmName;
        for (int i=0;i<icms.length;i++) {
          // ignore the LapLink Gold ICM
          icmName = icms[i].getName();
          if( !icmName.equals( "LLG" ) && !icmName.equals("RDP") ) {
              if (icms[i].isDesktopRegisteredAndConnected(did)) {
                icm = icms[i];
                break;
              }
          }
        }
      }
    } catch (DataException e) {
      m_log.error("Cannot retrieve all ICMs",e);
    }
    catch(CannotConnectToICMException e){
      m_log.error("Cannot connect to an ICM",e);
    }
    return icm;
  }

  // Get the info about the RC icm
  public static ICM findRCICM()
  {
    ICM icm = null;
    try {
      ICMDAO dao = DAOFactory.getICMDAO();
      ICM[] icms = null;

      icms = dao.getAll();
      if (icms != null) {
        String icmName;
        for (int i=0;i<icms.length;i++) {
          // ignore the LapLink Gold ICM
          icmName = icms[i].getName();
          if( icmName.equals("RDP") ) {
             icm = icms[i];
             break;
          }
        }
      }
    } catch (DataException e) {
      m_log.error("Cannot retrieve all ICMs",e);
    }
    return icm;
  }

  // Get the info about the RC icm
  public static ICM findLLGICM()
  {
    ICM icm = null;
    try {
      ICMDAO dao = DAOFactory.getICMDAO();
      ICM[] icms = null;

      icms = dao.getAll();
      if (icms != null) {
        String icmName;
        for (int i=0;i<icms.length;i++) {
          // ignore the LapLink Gold ICM
          icmName = icms[i].getName();
          if( icmName.equals("LLG") ) {
             icm = icms[i];
             break;
          }
        }
      }
    } catch (DataException e) {
      m_log.error("Cannot retrieve all ICMs",e);
    }
    return icm;
  }

  // Get the info about the RC icm
  public static ICM findFilesICM()
  {
    ICM icm = null;
    try {
      ICMDAO dao = DAOFactory.getICMDAO();
      ICM[] icms = null;

      icms = dao.getAll();
      if (icms != null) {
        String icmName;
        for (int i=0;i<icms.length;i++) {
          // ignore the LapLink Gold ICM
          icmName = icms[i].getName();
          if( icmName.equals("ICM1") ) {
             icm = icms[i];
             break;
          }
        }
      }
    } catch (DataException e) {
      m_log.error("Cannot retrieve all ICMs",e);
    }
    return icm;
  }

  /**
   * Returns the url that is used to check the status fo a desktop.
   *
   * @param did - the desktop to check.
   * @return the URL we use to check that desktop.
   * @exception MalformedURLException - if we cannot build the url.
   */
  public URL getDesktopStatusURL(String did)
  throws MalformedURLException
  {
    URL url;
    String expandedReq = substituteVariableInString(statusURL,DID_VARIABLE_NAME,did);
    // convenient during development
    if (!expandedReq.startsWith("http")){
      url = new URL(protocol,hostname,port,expandedReq);
    } else {
      url = new URL(expandedReq);
    }
    return url;
  }

  /**
   * Returns the url that is used to notifies that the user has logged out.
   *
   * @param did - the desktop to logout.
   * @return the URL we use to nothify that tehe user has logged out.
   * @exception MalformedURLException - if we cannot build the url.
   */
  public URL getDesktopLogoutURL(String did)
  throws MalformedURLException
  {
    URL url;
    String expandedReq = substituteVariableInString(logoutURL,DID_VARIABLE_NAME,did);
    // convenient during development
    if (!expandedReq.startsWith("http")){
      url = new URL(protocol,hostname,port,expandedReq);
    } else {
      url = new URL(expandedReq);
    }
    return url;
  }


  /**
   * Returns the url that is used to display a desktop's applications list.
   *
   * @param desktop - the desktop to redirect to.
   * @param mode - the format (S,M,or L)
   * @return the URL.
   * @exception MalformedURLException - if we cannot build the url.
   */
  public URL getDesktopAppListURL(DisplayMode mode,Desktop desktop)
  throws MalformedURLException
  {
    String expandedReq = substituteVariableInString(appListURL,
                                                    DID_VARIABLE_NAME,
                                                    desktop.getDid());
    expandedReq = substituteVariableInString(expandedReq,MODE_VARIABLE_NAME,
                                             mode.getCode());
    URL url = new URL(protocol,hostname,port,expandedReq);
    return url;
  }
  /**
   * Returns the ICM's top url (e.g. https://www.laplink.com
   *
   * @return the URL.
   * @exception MalformedURLException - if we cannot build the url.
   */
  public URL getTopURL()
  throws MalformedURLException
  {
    URL url = new URL(protocol,hostname,port,"");
    return url;
  }

  // ------------------------------------------------------------ Helper methods
  /**
   * Determines whether a given desktop is registred with this ICM <strong>and
   * </strong> is connected.
   *
   * @param did - the desktop's did.
   * @return true if the desktop is registered with that ICM and is connected;
   * false if the desktop is either not registered with this ICM, or is not
   * connected, or if the ICM could not be contacted.
   */
  private boolean isDesktopRegisteredAndConnected(String did)
  throws CannotConnectToICMException
  {
      URL url = null;
    try {
      url = getDesktopStatusURL(did);
    } catch (MalformedURLException e) {
      throw new CannotConnectToICMException(this.toString(),e);
    }
    return isDesktopConnected(url.toExternalForm(),getUsername(),getPassword());
  }


  /**
   * Performs variable substitution in a string.
   *
   * If the input string contains the {name} substring, we replace all
   * occurences of the substring with 'name'
   * e.g. input = http://www.foo.com:8080/doit?toto=titi&did={did}
   *      name = did
   *      value = 1234
   * The method returns http://www.foo.com:8080/doit?toto=titi&did=1234
   * @param input - a string
   * @param name - The name of a variable
   * @param value - the value for that variable
   * @return The original string with the substitutions; if the variable {name}
   * is not present in the input string, we return it with no modifications.
   */
  public static String substituteVariableInString(String input,String name,
                                                  String value)
   {
     StringBuffer output = new StringBuffer();
     Matcher matcher = VARIABLE_PATTERN.matcher(input);
     boolean found = false;
     try {
       while(matcher.find()){
         found = true;
         String currentVariable = matcher.group(2);
         if (currentVariable.equals(name)) {
           matcher.appendReplacement (output, value);
         }
       }
     }
     catch (IllegalStateException e) {
       String msg = "No match has yet been attempted " +
               " or the previous match operation failed - variable: " + name +
               " = " + value + " - input: " + input;
       m_log.error(msg,e);
       found=false;
     }
     catch (IndexOutOfBoundsException e) {
       String msg = "capturing group does not exist in the pattern - variable: " +
               name + " = " + value + " - input: " + input;
       m_log.error(msg,e);
       found=false;
     }
     matcher.appendTail (output);
     if (found) {
       return output.toString();
     } else {
       return input;
     }
   }


  // -------------------------------------------------------------- Test Methods

  private static void testGetDesktopStatusURL() throws Exception
  {
    ICM icm = new ICM();
    icm.setStatusURL("http://www.com.com/?did={did}");
    System.out.println(icm.getDesktopStatusURL("1234"));
  }
  private static void testIsDesktopRegisteredAndConnected() throws Exception
 {
    ICM icm = new ICM();
    String did = "LTqceHV2";
    //did = "LTqceHV3";

    icm.setStatusURL("https://files.laplink.com/?LLListServers&did={did}");
    icm.setUsername("icmadmin");
    icm.setPassword("imapinhead");
    //icm.setStatusURL("http://localhost:8080/html/simulateICM.do?type=odd&did={did}");

    System.out.println(icm.isDesktopRegisteredAndConnected(did));
  }

  private static void testFindICM() throws Exception
  {
    String did = "LTqceHV3";
    ICM icm = ICM.findICM(did);
    System.out.println(icm);



  }

  private static void testPattern() throws Exception
  {
    String url = "http://rome.infowave.com/html/f/apps/files.laplink.com/L/IWLOGS?did=BXyrXJqG";
    String pattern = "/html/[tp]/apps/files.laplink.com/(.*)";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(url);
   System.out.println("found: " + m.find());

  }
  private static void testSubstituteVariableInString() throws Exception
  {
    String url = "http://www.foo.bar:8080/doit?toto=titi&did={did}&w=p";
    System.out.println(substituteVariableInString(url,"did","1234"));
  }


  private static void testGetDesktopAppListURL() throws Exception
  {
    Desktop d = new Desktop();
    d.setDid("asdf");
    ICM icm = new ICM();
    icm.setProtocol("https");
    icm.setHostname("files.laplink.com");
    icm.setPort(443);
    icm.setAppListURL("/{mode}/?did={did}");

    System.out.println(icm.getDesktopAppListURL(DisplayMode.PC,d));

  }
  public static void main(String[] args) {
    try {
      //testGetDesktopAppListURL();
      //testIsDesktopRegisteredAndConnected();
      //testFindICM();
      testPattern();
      //testSubstituteVariableInString();
      //testGetDesktopStatusURL();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
