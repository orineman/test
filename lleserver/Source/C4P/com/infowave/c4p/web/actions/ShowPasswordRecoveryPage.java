package com.infowave.c4p.web.actions;

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

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Nov 20, 2003
 * Time: 3:30:31 PM
 * To change this template use Options | File Templates.
 */
public class ShowPasswordRecoveryPage extends BaseAction
{
  private static final String USERNAME = "username";
  private static final String SECURITY = "security";
  private static final String PASSWORD = "password";

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ShowPasswordRecoveryPage.class);


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
    String step = request.getParameter(WebConstants.PASSWORD_RECOVERY_STEP);
    HttpSession session = request.getSession();

    // Make sure we have a valid name. If none is provided or if it is
    // unknown, default to the first step.
    m_log.debug("step: " + step);
    if (step == null) {
      step = USERNAME;
    } else if (!USERNAME.equals(step) && !SECURITY.equals(step) &&
            !PASSWORD.equals(step)) {
      step = USERNAME;
    }

    // Make sure the requested step is consistent with the state of the
    // transaction
    if (SECURITY.equals(step)) {
      if (session.getAttribute(WebConstants.PASSWORD_RECOVERY_USERNAME)== null){
        step = USERNAME;
      }
    } else if (PASSWORD.equals(step)) {
      if ((session.getAttribute(WebConstants.PASSWORD_RECOVERY_USERNAME)==null)||
      (session.getAttribute(WebConstants.PASSWORD_RECOVERY_QUESTION)==null)) {
        step = USERNAME;
      }
    }
    String forwardName = "success." + step;
    m_log.debug("forward: " + forwardName);
    ActionForward forward =super.findForward(request,mapping,forwardName);
    if (forward != null) {
    m_log.debug("getRedirect: " + forward.getRedirect());
    }
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
                                 ActionErrors errors)
  {
    return true;
  }
}
