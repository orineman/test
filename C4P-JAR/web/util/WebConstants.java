package com.infowave.c4p.web.util;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Oct 1, 2003
 * Time: 11:24:10 AM
 * To change this template use Options | File Templates.
 */
public interface WebConstants
{
// Request/Session attributes
public static final String RESELLER_BUNDLE = "c4p_resellerBundle";
public static final String DISPLAY_MODE = "mode"; // nedeed???
//public static final String ERROR_CODE = "c4p_errorCode";
public static final String ERRORS = "c4p_errors";
public static final String ERROR = "c4p_error";    
public static final String FORWARD_DESTINEE = "c4p_forwardDestinee";
public static final String MESSAGE = "c4p_msg";

public static final String PASSWORD_RECOVERY_USERNAME = "c4p_pr_uname";
public static final String PASSWORD_RECOVERY_QUESTION = "c4p_pr_question";
public static final String METHOD = "c4p_method";
public static final String LICENSE_CODE = "c4p_license_code";
public static final String FREEDUP_LICENSES = "c4p_freedup_licenses";
public static final String DESKTOP_URL = "infowave_desktopURL";
public static final String SESSION_STATE = "infowave_sessionState";

// Request paramaters
public static final String DID = "did";
public static final String URL = "url";
public static final String LOCALE = "locale";
public static final String ACTION = "action";
public static final String SECTION = "section";
public static final String PASSWORD_RECOVERY_STEP = "step";
public static final String PAGE = "page";
public static final String OWNER = "owner";
public static final String USERS = "users";
public static final String VERSION = "version";

// Forwards
public static final String FWD_SYSTEM_ERROR = "to_be_defined";
public static final String FWD_INDEX="index";    
public static final String FWD_MAIN_PAGE5 = "mainPage5";
public static final String FWD_MAIN_PAGE = "mainPage";
public static final String FWD_LOGIN_PAGE5 = "login5";
public static final String FWD_LOGIN_PAGE = "login5";
public static final String FWD_DUMMY_PAGE = "dummy";
public static final String FWD_MAIN_PDAPAGE = "mainPDAPage";
public static final String FWD_MAIN_PHONEPAGE = "mainPhonePage";


// Actions
public static final String ACTION_SHOW_ERROR_PAGE = "/showGeneralErrorPage.do";
//  public static final String ACTION_LOGIN_PAGE ="/showLoginPage.do";
public static final String ACTION_LOGIN_PAGE ="/loginPage5.do";
public static final String ACTION_SHOW_DESKTOP_APP = "/protected/showDesktopApplication.do";

public static final String ACTION_LOGIN_PROXY5 = "/protected/lle5.do";
public static final String ACTION_LOGIN_PROXY = "/protected/loginProxy.do";

public static final String CLIENT_URL = "ui/source/index.html";
}
