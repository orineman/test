package com.infowave.c4p.web.util;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Oct 1, 2003
 * Time: 11:24:10 AM
 * To change this template use Options | File Templates.
 */
public interface WebConstants {

  // Request/Session attributes
  public static final String COMPANY_BUNDLE = "c4p_companyBundle";
  public static final String LOCALE = "locale";
  public static final String MESSAGE = "c4p_msg";
  public static final String ERRORS = "c4p_errors";
  public static final String SESSION_STATE = "laplink_sessionState";
  public static final String FORWARD_DESTINEE = "c4p_forwardDestinee";
  public static final String DEFAULT_LOCALE_STR = "en";
  public static final String DEFAULT_MESSAGES_FILE	= "messages";

  // Request paramaters
  public static final String ACTION = "action";
  public static final String PAGE = "page";

 // Forwards
  public static final String RASSIST = "/RAssist";
  public static final String LOGIN_ERR = "/showLoginPage.do?action=error";
  public static final String LOGIN_PROXY = "/protected/loginProxy.do";
  public static final String FWD_LOGIN_PAGE ="login";
  public static final String FWD_DUMMY_PAGE ="dummy";
  public static final String FWD_SYSTEM_ERROR ="to_be_defined";
  public static final String FWD_MAIN_PAGE ="mainPage";

  // Company Database fields
  public static final int DEDICATED_LICENSE = 4;			// concurrent tech logins // aka dedicated // has no company admin
  public static final int CONCURRENT_LICENSE = 2;		// concurrent tech logins // aka concurrent // has company admin
  public static final double COMPANY_MISCFLAGS_DEFAULT = 0;

  // Technician Database fields
  public static final int	PWD_MIN									= 6;	// minimum length of passwords
  public static final int	TECHNICIAN_MAXCONNS_INACTIVE			= 0;	// technician is inactive
  public static final int	TECHNICIAN_MAXCONNS_ACTIVE				= 1;	// technician is active
  public static final int	TECHNICIAN_ACCESSLEVEL_INACTIVE			= 0x00; // company is inactive
  public static final int	TECHNICIAN_ACCESSLEVEL_ACTIVE			= 0x01; // company is active
  public static final int	TECHNICIAN_ACCESSLEVEL_TECH				= 0x02;	// non-admin
  public static final int	TECHNICIAN_ACCESSLEVEL_COMPANY_ADMIN	= 0x04;	// per company
  public static final int	TECHNICIAN_ACCESSLEVEL_SITE_ADMIN		= 0x40;	// per remote assist site
  public static final String TECHNICIAN_ACCESSNAME_ACTIVE			= "active";
  public static final String TECHNICIAN_ACCESSNAME_TECH				= "technician";
  public static final String TECHNICIAN_ACCESSNAME_COMPANY_ADMIN	= "company";
  public static final String TECHNICIAN_ACCESSNAME_SITE_ADMIN		= "site";

  // Password reset
  public static final int PWD_MIN_SIZE = 6;			// minimum size of password		
  public static final int PWD_MAX_SIZE = 8;			// maximum size of password
  public static final String PWD_ENCODER = "com.infowave.c4p.laplink.security.PasswordEncoderImpl";

  public static final int CID_SIZE = 4;				// size of techcid

}
