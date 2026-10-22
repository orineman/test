package com.infowave.c4p.web.actions.login;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class LoginRouter extends BaseAction

 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(LoginRouter.class);
  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs
   * @return a forward
   * @throws Exception

   */
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    ActionForward forward = null;
    HttpSession session = request.getSession();

    // If we hava a user already logged in, we must log him/her first before
    // processing another login request. Not ideal because the new user will be
    // sent to the login page and prompted for his credentials again, but
    // it's the only option we have as the Web container maintains a reference
    // to the principal.

    if (request.getUserPrincipal() != null){
      String username=request.getUserPrincipal().getName();
	  int idx = username.indexOf( ";" );
	  if( idx != -1 ) username = username.substring(idx+1);
      m_log.debug("User " + request.getParameter("j_username") +
                  " attempting to log in while user " +
                  request.getUserPrincipal() + " is still logged in - login out");
      forward = mapping.findForward(WebConstants.FWD_LOGIN_PAGE);

      session.invalidate();
      response.sendRedirect(WebConstants.RASSIST +forward.getPath()+ "?" +
                            WebConstants.ACTION + "=" + "logout" +
                            "&username=" + username);
      response.flushBuffer();
      return null;
      //return mapping.findForward(WebConstants.FWD_DUMMY_PAGE);
    }

    session.setAttribute("j_companyid",request.getParameter("j_companyid"));
    session.setAttribute("j_username",request.getParameter("j_username"));
    session.setAttribute("j_password",request.getParameter("j_password"));
    session.setAttribute("j_security_check",request.getParameter("j_security_check"));
    //m_log.debug("Username = " + request.getParameter("j_username"));
    String forwardDestinee = null;
    forwardDestinee = WebConstants.FWD_MAIN_PAGE;
    session.setAttribute(WebConstants.FORWARD_DESTINEE,forwardDestinee);
    // todo this should be in the constants file.
    //String url ="/RAssist/protected/loginProxy.do";
	String url = WebConstants.RASSIST + WebConstants.LOGIN_PROXY;
    response.sendRedirect(response.encodeRedirectURL(url));
    forward = mapping.findForward(WebConstants.FWD_DUMMY_PAGE);
    //m_log.debug("forward = " + forward);
    return forward;
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
