package com.infowave.c4p.web.actions.lle5.recovery;

import static com.infowave.c4p.web.util.XmlUtil.Error2XML;
import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.actions.AccountForm;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
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
 * Date: 19.11.2007
 * Time: 17:13:56
 * Paccage : com.infowave.c4p.web.actions.lle5.recovery
 */
public class ResetPassword5 extends BaseAction {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(GetSecurityQuestion.class);

    public ActionForward executeLogic(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      List errors)
            throws Exception {
        HttpSession session = request.getSession();
        response.setStatus(403);
        AccountForm form = (AccountForm) actionForm;
        boolean validAnswer = false;
        // Validate the input parameters
        validateInputParameters(mapping, actionForm, request, errors);

        if (!errors.isEmpty()) {
            errors.clear();            
            return mapping.findForward("error");
        }

        try {
            String username = (String) session.getAttribute(WebConstants.PASSWORD_RECOVERY_USERNAME);
            if (username != null) {
                DelegateFactory.getUserManager().resetPassword(username,
                        form.getNewPassword());
                response.setStatus(200);
               
            }
        }
        catch (ServiceException e) {
            // Add the error to our list so that the user gets a message.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
            errors.add(e.getError());
            // If this error is not a user error, we log it
            ErrorCode code = e.getError().getCode();
            if (!code.equals(ErrorCode.INVALID_USERNAME)) {
                m_log.error("Could not reset the password", e);
            }
            String sResponse = Error2XML(code.toInt(),code.toString());
            byte[] utf8Bytes = sResponse.getBytes("UTF-8");
            String utf8Response = new String(utf8Bytes, "UTF-8");
            m_log.debug(utf8Response);
            response.setHeader("content-type", "text/xml; charset=utf-8");
            response.getWriter().print(utf8Response);
        }
        response.flushBuffer();
        return null;
        //request.getSession().setAttribute(WebConstants.PASSWORD_RECOVERY_STEP, 3);
        //return mapping.findForward("success");
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
        if (step != null && step == 2) {
            //request.getSession().setAttribute(WebConstants.PASSWORD_RECOVERY_STEP, 3);
        } else {
            errors.add(new PortalError(ErrorCode.DATA_ENTRY_SEC_ANSWER_MISSING_ERROR));
        }
    }

}

