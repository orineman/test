package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.util.ErrorCode;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Nov 20, 2003
 * Time: 9:38:49 AM
 * To change this template use Options | File Templates.
 */
public class ChangePassword extends UserAction
{
    /** The class's logger */
  private static Log m_log = LogFactory.getLog(ChangePassword.class);

   /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param actionForm The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception java.io.IOException if an input/output error occurs
   * @exception javax.servlet.ServletException if a servlet exception occurs
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
    SessionState state = WebUtil.getSessionState(request);
    AccountForm form = (AccountForm) actionForm;

    // Validate the input parameters
    validateInputParameters(mapping,actionForm,request,errors);

    if (!errors.isEmpty()) {
      return null;
    }

    try {
      DelegateFactory.getUserManager().changePassword(state.getUserDTO().getUsername(),
                                                      form.getPassword(),
                                                      form.getNewPassword());
      // Store the confirmation message's key in the request
      request.setAttribute(WebConstants.MESSAGE,"msg.confirmationPasswordChange");
    }
    catch(ServiceException e) {
      // Add the error to our list so that the user gets a message.
      errors.add(e.getError());
      // If this error is not a user error, we log it
      ErrorCode code = e.getError().getCode();
      if (code.equals(ErrorCode.INVALID_PASSWORD)){
        m_log.error("Could not change identity",e);
      }
    }
    return null;
  }

  private void validateInputParameters(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        List errors)
  {
    AccountForm form = (AccountForm) actionForm;
    //form.validatePassword(mapping,request,errors);
    form.validateNewPassword(mapping,request,errors);
    form.validateNewPasswordConfirmation(mapping,request,errors);
  }
}
