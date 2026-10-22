package com.infowave.c4p.web.util;

import com.infowave.c4p.util.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Oct 18, 2003
 * Time: 10:37:59 PM
 * To change this template use Options | File Templates.
 */
public class WebUtil
{
  /**
   * Stores the session state objectn the session.
   * @param request
   * @param state
   */
  public static void setSessionState(HttpServletRequest request,
                                      SessionState state)
  {
    HttpSession session = request.getSession();
    session.setAttribute(WebConstants.SESSION_STATE,state);
  }
  /**
   * Retrieves the session state object from the session.
   * @param request
   * @return session state object
   */
  public static SessionState getSessionState(HttpServletRequest request)
  {
    HttpSession session = request.getSession();
    return (SessionState) session.getAttribute(WebConstants.SESSION_STATE);
  }

  /**
   * Stores the errors in the session.
   *
   * @param request
   * @param errors
   */
  public static void setErrors(HttpServletRequest request,
                               List errors)
  {
    //HttpSession session = request.getSession();
    request.setAttribute(WebConstants.ERRORS,errors);
  }

    /**
   * Stores the error in the session.
   *
   * @param request
   * @param error
   */
  public static void setError(HttpServletRequest request,
                               ErrorCode error)
  {
    //HttpSession session = request.getSession();
    request.setAttribute(WebConstants.ERROR,error);
  }
 
  /**
   * Stores in the session the url that should be displayed in the page's
   * desktop section.
   * @param request
   * @param url
   */

  public static void setDesktopUrl(HttpServletRequest request,
                                    String url)
  {
    HttpSession session = request.getSession();
    session.setAttribute(WebConstants.DESKTOP_URL,url);
  }

  /**
   * Retrieves from the session the url that  should be displayed in the page's
   * desktop section.
   * @param request
   * @return session state object
   */

  public static String getDesktopUrl(HttpServletRequest request)
  {
    HttpSession session = request.getSession();
    return (String) session.getAttribute(WebConstants.DESKTOP_URL);
  }


}
