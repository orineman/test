package com.infowave.c4p.web.util;

import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.dto.ResellerDTO;
import com.infowave.c4p.commons.DisplayMode;

import com.infowave.c4p.service.dto.RCUsageDTO;
import com.infowave.c4p.model.facade.RCUsageManagerImpl;


import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.net.URLEncoder;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * This class contains the little business logic we would put in the DesktopDTO
 * class if putting business logic in dtos was not considered as bad pratcise.
 *
 */
public class DesktopUtils
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(DesktopUtils.class);

  /** The regular expression to extract the did from a request
   *  e.g. www.foo.com/?did=1234 */
  private static final String DID_PARAM_REGEXP = "(did=([^/&]*))";
  /** The pattern to match the DID_VARIABLE_NAME regexp */
  private static Pattern DID_PARAM_PATTERN = Pattern.compile(DID_PARAM_REGEXP);
  /** The regular expression that describes a Files url */
  private static String FILES_URL_REGEXP = "(/files.laplink.com/(.*))";
  /** The pattern for a Files url */
  private static Pattern FILES_URL_PATTERN = Pattern.compile(FILES_URL_REGEXP);

  /** The header we send to the desktop, that contains the user's desktopslist **/
  private static String HEADER_DESKTOP_LIST = "DesktopList";

  /** The header we send to the desktop, that contains th url to retrieve the
   * status (connected/disconnected) of a device **/
  private static String HEADER_CONN_STATUS = "ConnectionStatus";

  /** The header we send to the desktop, that contains the user's credentials */
  private static String HEADER_AUTHORIZATION = "Authorization";

  /** The header we send to the desktop, that contains the suported modes (L.M.S) */
  private static String HEADER_SUPPORTED_MODES = "SupportedMode";

  /** The header  that contains the expiry url */
  private static String HEADER_EXPIRY_URL = "ExpiryURL";

  /** The header  that contains the get desktop icon  url */
  private static String HEADER_DEVICE_ICON_URL = "ConnectionStatus";

  /** The header  that the current display mode (L, M, or S) */
  private static String HEADER_CURRENT_MODE = "CurrentMode";

  /** The user agent */
  private static String HEADER_USER_AGENT = "User-Agent";

  private static String HEADER_REFERER = "Referer";

  private static String HEADER_CONTENT_TYPE = "Content-Type";
  private static String HEADER_LLLOCALE = "LLLocale";
  private static String HEADER_LLRCWINDOW = "LLRCWindow";

  private static String RC_OPTIONS_REQUEST = "/llrc/index.jsp";
  private static int RC_DEFAULT_LIMIT = 3600;		// seconds
  private static int RC_WINDOW_LIMIT = 30;		// days

  /**
   * Given a desktop and a url, determine whether this url should be rewriten
   * to access one of the desktop's application.
   * E.g. if the url is
   * http://www.portal/com/html/files.laplink.com/S/do_something?did=1234
   * and the regular expression for this desktop's applications is:
   * /www.laplink.com/(.*)
   * and, the ICM's root url for this desktop is:
   * https://files.laplink.com:8080
   * Then this method will generate the following url:
   * https://files.laplink.com:8080/S/do_something?did=1234
   *
   * todo we rely on the fact that the uri to send to the icm
   * (/S/do_something?did=1234 in our example) is given by the regular
   * expression's first group ( (.*) in our example). This informtion ( the
   * group number) should be defined in the database but because the specs
   * change daily these days, I'll keep the hardcoded value for now.
   *
   * @param desktop - the desktop
   * @param url - the original url.
   * @return the application url or null if the original url does not follow
   * the pattern for an application url;
   *
   */
  public static String getAppUrl(DesktopDTO desktop,String url)
  {
    String appUrl = null;
    Pattern pattern = desktop.getAppUrlPattern();
    Matcher matcher = pattern.matcher(url);
    if (matcher.find()) {
      if (matcher.groupCount() == 1) {
        StringBuffer sb = new StringBuffer();
        sb.append(desktop.getIcmURL());
        if (!desktop.getIcmURL().endsWith("/") &&
            !matcher.group(1).startsWith("/")){
          sb.append("/");
        }
        
        sb.append(matcher.group(1));
        appUrl = sb.toString();
      } else {
        m_log.error("the app url regexp should have one group - found: " +
                    matcher.groupCount() + " groups - pattern: " +
                    pattern.toString());
        appUrl = null;
      }
    }
    // remove trailing slashes
    /*
    if (appUrl != null) {
      while (appUrl.endsWith("/")) {
        appUrl = appUrl.substring(0,appUrl.length()-2);
      }
    }
    */
    return appUrl;
  }

    /**
   * Given a desktop and a url, determine whether this url should be rewriten
   * to access Files application for that desktop.
   * E.g. if the url is:
   * http://www.portal/com/html/showDesktop.do/html/p/apps/files.laplink.com/L/C%3A?did=khPdprqn
   * and the regular expression for the Files applications is:
   * (/files.laplink.com/(.*))
   * Then this method will generate the following url:
   * files.laplink.com/L/C%3A?did=khPdprqn
   *
   * Note that we don't use teh desktop's ICM (as it only serve the dektop's
   * application (mail,calendar, contacts, etc.). We count on the fact that the
   * original url contains the Files ICM's full url.
     *
   * @param desktop - the desktop
   * @param url - the original url.
   * @return the Filees url or null if the original url does not follow
   * the pattern for Files url;
   * @deprecated we only have one icm for Files and APPS application.
   */
  public static String getFilesUrl(DesktopDTO desktop,String url)
  {
    String filesUrl = null;
    Matcher matcher = FILES_URL_PATTERN.matcher(url);
    if (matcher.find()) {
      filesUrl =  matcher.group();
      // remove the first /
      filesUrl = filesUrl.substring(1);

    }
    return filesUrl;
  }

  /**
   * Extract the did from a request.
   * @param url
   * @return the value of the did request parameter
   */
  public static String getDidParameter(String url)
  {
    String did = null;
    Matcher matcher = DID_PARAM_PATTERN.matcher(url);
    if (matcher.find()) {
      if (matcher.group(2) != null) {
        did = matcher.group(2);
      }
    }
    return did;
  }

    /**
   * Returns a list of the headers that have to be sent with a request for a
   * desktop application.
   * @param request
   * @param desktop
   * @return
   */
  public static Map getDownlofFileRequestHeader(HttpServletRequest request,
                                                DesktopDTO desktop)
  {
    Map headers = new HashMap();
    SessionState state = WebUtil.getSessionState(request);
    UserDTO user = state.getUserDTO();

    // Authorization header
    String password = "ignored";
    String authorization = "Basic "
            + Base64Encoder.encode(user.getUsername() + ":" + password);
    headers.put(HEADER_AUTHORIZATION,authorization);

    return headers;
  }

  /**
   * Returns a list of the headers that have to be sent with a request for a
   * desktop application.
   * @param request
   * @param desktop
   * @return
   */
  public static Map getApplicationRequestHeader(HttpServletRequest request,
                                                DesktopDTO desktop)
  {
    Map headers = new HashMap();
    SessionState state = WebUtil.getSessionState(request);
    UserDTO user = state.getUserDTO();

    // Authorization header
    String password = "ignored";
    String authorization = "Basic "
            + Base64Encoder.encode(user.getUsername() + ":" + password);
    headers.put(HEADER_AUTHORIZATION,authorization);

    // Supported Display Modes
    String modes = state.getSupportedDisplayModesAsString();
    headers.put(HEADER_SUPPORTED_MODES,modes);

    // Current display mode
    headers.put(HEADER_CURRENT_MODE,state.getDisplayMode().getCode());

    // DesktopList
    boolean firstDesktop = true;
    StringBuffer buff = new StringBuffer();
    for (Iterator it = state.getOrderedDesktops().iterator();it.hasNext();){
      DesktopDTO itDesktop =(DesktopDTO)it.next();
      // We only include visible desktops
      if (!itDesktop.isVisible()) {
        continue;
      }
      if (firstDesktop) {
        firstDesktop = false;
      } else {
        buff.append("; ");
      }
      // did
      buff.append(itDesktop.getDid()).append("/");
      // Desktop name
      buff.append(encodeForDeviceListHeader(itDesktop.getName())).append("/");
      // Owner
      buff.append(encodeForDeviceListHeader(itDesktop.getOwner())).append("/");
      // ICM
      buff.append(itDesktop.getIcmURL()).append("/");
      // days to expiry
      buff.append(itDesktop.getDaysToExpiry()).append("/");
      // RDP ICM
      buff.append(itDesktop.getRdpURL()).append("/");
    }
    headers.put(HEADER_DESKTOP_LIST,buff.toString());

    // Expiry url
    headers.put(HEADER_EXPIRY_URL,state.getReseller().getExpiryUrl());
    
    // device icon header
    headers.put(HEADER_DEVICE_ICON_URL,ResourceManager.DEVICON_PREFIX);

    // User agent. We add the header because the ICM needs to know what is
    // the client's user-agent is (if we don't overwrite it, it'll be
    // something like "jakarta[...]"
    headers.put(HEADER_USER_AGENT,request.getHeader("User-Agent"));
    if( desktop == null || (desktop != null && desktop.getSoftwareVersion() != null) ) {
		String referer = request.getHeader("Referer");
		if( referer == null || referer.length() == 0 ) {
			referer = request.getScheme();
			referer += "://";
			referer += request.getServerName();
			referer += "/index.jsp";
			referer += request.getQueryString();
		}
        headers.put(HEADER_REFERER, referer);
        m_log.debug("referer: " + referer);
   }
    headers.put(HEADER_CONTENT_TYPE,request.getHeader("Content-Type"));

	// pass back the locale stored in the database for additional info to the host
    headers.put(HEADER_LLLOCALE,state.getLocale().toString());

    // Check if this is a request for the rc options page and if it is, send back info
   // about the remaining time in the rc window
   String url = request.getRequestURI();
   if( url.indexOf( RC_OPTIONS_REQUEST ) != -1 ) {
	// Get the did out of the request
	String did = null;
	String query = request.getQueryString();
	int idx = query.indexOf( "did=" );
	if( idx != -1 ) {
		idx += 4;
		int idx2 = query.indexOf( "&", idx );
		if( idx2 == -1 ) {
			did = query.substring( idx );
		} else {
			did = query.substring( idx, idx2 );
		}
	}
            if( did != null && !did.equals("") ) {
		RCUsageManagerImpl rcStats = new RCUsageManagerImpl( did );
		RCUsageDTO rcInfo = null;
		try {
			rcInfo = rcStats.verifyInfo( RC_WINDOW_LIMIT );
			int maxSecs = Math.max( RC_DEFAULT_LIMIT , rcInfo.getWindowLimit() );
			int clientSecondsLeft = maxSecs;
			if( maxSecs < 0x7fffffff ) {
				clientSecondsLeft = Math.max( -2, maxSecs - rcInfo.getClientSeconds() );
			}
       			// Check on the time remaining
    		 	headers.put(HEADER_LLRCWINDOW, Integer.toString( clientSecondsLeft ));
		} catch( Exception e ) {
		}
           }
  }

    return headers;
  }

  /**
   * The device list we sent to the desktop in the HEADER_DESKTOP_LIST request
   * header uses spaces, slashes, and semi colons as field separators.
   * Therefore, we must make sure that if a value to be put in the header
   * contains one of these characters, it is properley encoded.
   * We encode these characters as follow: %<hex value of the character>.
   *
   * todo A HTTP header can only accept US-ASCII character. Because the desktop
   * list contains the owner's name, it is possible that the string contains
   * non ascii character. Therefore, this value should also be uuencoded.
   * We've decided to deal with this in a later release.
   *
   * @param input
   * @return the encoded string or an empty string if the input string contains
   * no-ascii characters.
   */
  public static String encodeForDeviceListHeader(String input)
  {
    String encoded = null;

    try {
      // make sure that this String does not contain non ascii characters
      encoded = URLEncoder.encode(input, "8859_1");
    }
    catch (Exception e){
      m_log.error ("This value cannot be sent in a http header as it " +
                   "contains non US-ASCII characters: " + input);
      encoded = "";
    }
    return encoded;
  }

// --------------------------------------------------------------- test methods
  private static void testGetAppUrl() throws Exception
  {
    String regexp = "/[LMS]/.*";
    String icmUrl = "https://www.infowave.com";
    String url = "http:/foo.bar.com/html/doit.do/S/toto?did=123";

    regexp = "/files.laplink.com/(.*)";

    url = "http:/www.portal.com/html/files.laplink.com/S/toto?did=123";
    url = "http://rome.infowave.com/html/t/apps/files.laplink.com/L/c:/INFOWAVE?did=5Ai4KasZ";
    url = "http://rome.infowave.com/html/t/apps//lsfiles/D:/Music/goback.gif?did=LTqceHV2&GetActions";
    url = "http://rome.infowave.com/html/p/apps//lsfiles/F:/Music/60's?did=5Ai4KasZ&LLMethod=GetNavigatePage";

    regexp = "/html/t/apps/(.*)";
    regexp ="/html/[tp]/apps/(.*)";

    DesktopDTO dto = new DesktopDTO();
    dto.setAppUrlRegexp(regexp);
    dto.setIcmURL(icmUrl);
    System.out.println("apps url: " + getAppUrl(dto,url));
  }

  private static void testGeFilesUrl() throws Exception
  {
    String url = "http://www.portal/com/html/showDesktop.do/html/p/apps/files.laplink.com/L/C%3A?did=khPdprqn";
    url ="/html/p/apps/files.laplink.com/L/C:";
    DesktopDTO dto = new DesktopDTO();
    System.out.println("Files url: " + getFilesUrl(dto,url));
  }

  private static void testGetDidParameter() throws Exception
  {
    String url= "wewe.wewe.wewe/popo.popo?popo=popo&did=1234/&lklk=popo";
    System.out.println("did:" + getDidParameter(url));
  }

  private static void test() throws Exception
  {

    String s = " %/;";
    StringBuffer buff = new StringBuffer();
    char[] chars = s.toCharArray();
    for (int i=0;i<chars.length;i++){
      char c = chars[i];
      if (c == ' ') {
        System.out.println("space");
      } else if(c == '%'){
        System.out.println("percent");
      } else if(c == '/'){
        System.out.println("slash");
      } else if(c == ';'){
        System.out.println("semicolon");
      } else {
        System.out.println("char:" + c);
      }
            //System.out.println(b + " -> " + toHex(b));
    }
    //System.out.println (buff);

  }
  public static String toHex(int i) {
    StringBuffer buf = new StringBuffer(2);
    //don't forget the second hex digit
    if (((int) i & 0xff) < 0x10) {
      buf.append("0");
    }
    buf.append(Long.toString((int) i & 0xff, 16).toUpperCase());
    return buf.toString().toString();
  }//toHex


  static private void testEncodeForDeviceListHeader() throws Exception
  {
    DesktopDTO dto = new DesktopDTO();
    String s = "a a%a/a;";
    System.out.println(encodeForDeviceListHeader(s));

  }
    public static void main(String[] args) {
    try {
      //test();
      //testEncodeForDeviceListHeader();

      testGetAppUrl();
      //testGeFilesUrl();
      //testGetDidParameter();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
