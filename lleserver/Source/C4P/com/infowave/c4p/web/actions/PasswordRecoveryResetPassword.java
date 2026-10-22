package com.infowave.c4p.web.actions;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;

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
 * Time: 9:47:03 PM
 * To change this template use Options | File Templates.
 */
public class PasswordRecoveryResetPassword extends BaseAction
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(PasswordRecoveryResetPassword.class);

  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param actionForm The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs
   * @return a forward
   * @throws Exception

   */
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    HttpSession session = request.getSession();
    AccountForm form = (AccountForm) actionForm;
    boolean validAnswer = false;
    // Validate the input parameters
    validateInputParameters(mapping,actionForm,request,errors);

    if (!errors.isEmpty()) {
      return null;
    }

    try {
      String username = (String) session.getAttribute(WebConstants.PASSWORD_RECOVERY_USERNAME);
      if (username != null){
        DelegateFactory.getUserManager().resetPassword(username,
                                                       form.getNewPassword());
      }
    }
    catch(ServiceException e) {
      // Add the error to our list so that the user gets a message.
      errors.add(e.getError());
      // If this error is not a user error, we log it
      ErrorCode code = e.getError().getCode();
      if (!code.equals(ErrorCode.INVALID_USERNAME)){
        m_log.error("Could not reset the password",e);
      }
    }
    return null;
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

  private void validateInputParameters(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        List errors)
  {
    AccountForm form = (AccountForm) actionForm;
    form.validateNewPassword(mapping,request,errors);
    form.validateNewPasswordConfirmation(mapping,request,errors);
  }
}
