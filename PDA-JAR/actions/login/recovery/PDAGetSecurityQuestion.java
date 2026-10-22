package com.infowave.pda.actions.login.recovery;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.actions.AccountForm;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.util.ErrorCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 01.02.2008
 * Time: 4:03:17
 * Paccage : com.infowave.pda.actions.login.recovery
 */
public class PDAGetSecurityQuestion extends BaseAction {
        /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(PDAGetSecurityQuestion.class);

  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    m_log.debug("sceurity question Action start.");
    AccountForm form = (AccountForm) actionForm;
    HttpSession session = request.getSession();

    // Validate the input parameters
    validateInputParameters(mapping,actionForm,request,errors);

    if (!errors.isEmpty()) {
        return mapping.findForward("error");
    }
    try {
      int question;
      question = DelegateFactory.getUserManager().getSecurityQuestion(form.getUsername());
      m_log.debug("sceurity question: " + question);
      session.setAttribute(WebConstants.PASSWORD_RECOVERY_QUESTION,new Integer(question));
      session.setAttribute(WebConstants.PASSWORD_RECOVERY_USERNAME,form.getUsername());
      session.setAttribute(WebConstants.PASSWORD_RECOVERY_STEP,1);
    }
    catch(ServiceException e) {
      // Add the error to our list so that the user gets a message.
      //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      errors.add(e.getError());
      // If this error is not a user error, we log it
      ErrorCode code = e.getError().getCode();
      if (!code.equals(ErrorCode.INVALID_USERNAME)){
        m_log.error("Could not retrieve the security question",e);
      }
      return mapping.findForward("error");
    }
   return mapping.findForward("success");
  }

  private void validateInputParameters(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        List errors)
  {
    AccountForm form = (AccountForm) actionForm;

    form.validateUsername(mapping,request,errors);
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

