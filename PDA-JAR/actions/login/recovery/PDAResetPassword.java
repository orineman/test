package com.infowave.pda.actions.login.recovery;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.web.actions.AccountForm;
import com.infowave.c4p.web.actions.lle5.recovery.GetSecurityQuestion;
import com.infowave.c4p.web.util.WebConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 01.02.2008
 * Time: 0:59:12
 * Paccage : com.infowave.pda.actions.login.recovery
 */
public class PDAResetPassword extends Action {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(PDAResetPassword.class);

    public ActionForward executeLogic(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      List errors)
            throws Exception {
        HttpSession session = request.getSession();
        AccountForm form = (AccountForm) actionForm;
        boolean validAnswer = false;
        m_log.debug("reseting user password");
        // Validate the input parameters
        validateInputParameters(mapping, actionForm, request, errors);

        if (!errors.isEmpty()) {
            errors.clear();
            m_log.error("Could not reset the password forwarding to error");
            return mapping.findForward("error");
        }

        try {
            String username = (String) session.getAttribute(WebConstants.PASSWORD_RECOVERY_USERNAME);
            if (username != null) {
                DelegateFactory.getUserManager().resetPassword(username,
                        form.getNewPassword());
            }
        }
        catch (ServiceException e) {
            request.getSession().setAttribute(WebConstants.PASSWORD_RECOVERY_STEP, 2);
            // Add the error to our list so that the user gets a message.
            errors.add(e.getError());
            // If this error is not a user error, we log it
            ErrorCode code = e.getError().getCode();
            if (!code.equals(ErrorCode.INVALID_USERNAME)) {
                m_log.error("Could not reset the password", e);
            }
        }

        request.getSession().setAttribute(WebConstants.PASSWORD_RECOVERY_STEP, 3);
        return mapping.findForward("success");
    }


    /**
     * Checks that this action is authorized.
     *
     * @param mapping  The ActionMapping used to select this instance
     * @param form     The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
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

    private void validateInputParameters(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         List errors) {
        AccountForm form = (AccountForm) actionForm;
        form.validateNewPassword(mapping, request, errors);
        form.validateNewPasswordConfirmation(mapping, request, errors);

        Integer step = (Integer) request.getSession().getAttribute(WebConstants.PASSWORD_RECOVERY_STEP);
        if (step != null && step == 2 ) {
            //request.getSession().setAttribute(WebConstants.PASSWORD_RECOVERY_STEP, 3);
        } else {
            errors.add(new PortalError(ErrorCode.DATA_ENTRY_SEC_ANSWER_MISSING_ERROR));
        }
    }

}

