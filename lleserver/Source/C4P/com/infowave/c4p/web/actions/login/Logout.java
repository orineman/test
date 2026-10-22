package com.infowave.c4p.web.actions.login;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.ServletException;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.List;

import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.actions.UserAction;


/**
 * Logs out the user.
 * 
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */

/**
 * This action logs the user out.
 */
public class Logout extends BaseAction{

    /** The class's logger */
  private static Log m_log = LogFactory.getLog(Logout.class);

  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception java.io.IOException if an input/output error occurs
   * @exception javax.servlet.ServletException if a servlet exception occurs
   * @return a forward
   * @throws java.lang.Exception

   */
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    ActionForward actionForward = null;
    HttpSession session = request.getSession();
    SessionState state = WebUtil.getSessionState(request);
    Locale locale = state.getLocale();
    // We invalidate the session and send the user to the login page.
    // Because we invalidate the session, the locale will be lost. To avoid
    // the default locale, we pass the user's locale as a request parameter.
    // No that we need to do a redirect so that the request hits the State
    // filter again to determine the reseller, etc.
    actionForward = mapping.findForward(WebConstants.FWD_LOGIN_PAGE);
    
    session.invalidate();
    response.sendRedirect(actionForward.getPath()+ "?" +
                          WebConstants.ACTION + "=" + "logout" + "&" +
                          WebConstants.LOCALE + "=" + locale);
    response.flushBuffer();
    return mapping.findForward(WebConstants.FWD_DUMMY_PAGE);
  }

  /**
   * Checks that this action is authorized.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return true if this action can be executed.
   */
  protected boolean isAuthorized(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 ActionErrors errors) {
    return true;
  }

}
