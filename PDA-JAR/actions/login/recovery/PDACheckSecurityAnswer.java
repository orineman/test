package com.infowave.pda.actions.login.recovery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import com.infowave.c4p.web.actions.lle5.recovery.GetSecurityQuestion;
import com.infowave.c4p.web.actions.AccountForm;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 01.02.2008
 * Time: 4:34:51
 * Paccage : com.infowave.pda.actions.login.recovery
 */
public class PDACheckSecurityAnswer extends BaseAction {
    /**
         * The class's logger
         */
        private static Log m_log = LogFactory.getLog(PDACheckSecurityAnswer.class);

        public ActionForward executeLogic(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response,
                                          List errors)
                throws Exception {
            HttpSession session = request.getSession();
            AccountForm form = (AccountForm) actionForm;
            boolean validAnswer = false;
            // Validate the input parameters
            validateInputParameters(mapping, actionForm, request, errors);

            if (!errors.isEmpty()) {
                m_log.debug("Error validating params");
                return mapping.findForward("error");
            }

            try {
                String username = (String) session.getAttribute(WebConstants.PASSWORD_RECOVERY_USERNAME);
                m_log.debug("username: " + username);
                if (username != null) {
                    if (DelegateFactory.getUserManager().validateSecurityAnswer(username,
                            form.getSecurityAnswer())) {
                        validAnswer = true;
                        m_log.debug("valid: " + validAnswer);
                        session.setAttribute(WebConstants.PASSWORD_RECOVERY_STEP, 2);
                    }
                }
                if (!validAnswer) {
                    PortalError error = new PortalError(ErrorCode.INVALID_SECURITY_ANSWER);
                    errors.add(error);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return mapping.findForward("error");
                }
            }
            catch (ServiceException e) {
                // Add the error to our list so that the user gets a message.
                errors.add(e.getError());
                // If this error is not a user error, we log it
                ErrorCode code = e.getError().getCode();
                m_log.error("Could not validate security question", e);
                if (!code.equals(ErrorCode.INVALID_USERNAME)) {
                    m_log.error("Could not validate security question", e);
                }
                return mapping.findForward("error");
            }

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
            form.validateSecurityAnswer(mapping, request, errors);

            Integer step = (Integer) request.getSession().getAttribute(WebConstants.PASSWORD_RECOVERY_STEP);
            if (step != null && step==1) {
                //request.getSession().setAttribute(WebConstants.PASSWORD_RECOVERY_STEP, 2);
            } else {
                errors.add(new PortalError(ErrorCode.DATA_ENTRY_SEC_ANSWER_MISSING_ERROR));
            }
        }

}
