package com.infowave.c4p.web.actions.login;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ShowLoginPage extends BaseAction
 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ShowLoginPage.class);
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
    HttpSession session = request.getSession();
    ActionForward actionForward = null;
    String action = request.getParameter(WebConstants.ACTION);

    SessionState state = WebUtil.getSessionState(request);
    if (state != null) {
      state.notInAnySection();
    }
    
    //m_log.debug("session = " + session.getId());
    // If we have a j_username password in the session, perform authentication
    if ((request.getUserPrincipal() == null)
            && (session.getAttribute("j_username") != null)
            && (session.getAttribute("j_security_check") != null)) {
      String url = "" + session.getAttribute("j_security_check")
      +"?j_username=" + session.getAttribute("j_username") +
      "&j_password=" + session.getAttribute("j_password");
      session.removeAttribute("j_security_check");
      session.removeAttribute("j_username");
      session.removeAttribute("j_password");
      m_log.debug("redirecting to " + url);

      response.sendRedirect(response.encodeRedirectURL(url));
      actionForward = mapping.findForward(WebConstants.FWD_DUMMY_PAGE);
      return actionForward;
    }

    if (action == null) {
      action="protected";
    }

    String forwardName = "success";

    // If we arrived here after a form authentication login error, we look at
    // teh requested protocol to decide what login page we must display.
    if (action.equalsIgnoreCase("error")){
      if (!request.getRequestURL().toString().toLowerCase().startsWith("https")) {
        forwardName += ".http";
      }
    }
    forwardName += "." + action;

    actionForward = super.findForward(request,mapping,forwardName );
    m_log.debug("forward name = " + forwardName);
    return actionForward;
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
