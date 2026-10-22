/*
 * PDALoginPage.java
 *
 * Created on 20 �������� 2007 �., 12:23
 */

package com.infowave.pda.actions.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.infowave.c4p.web.util.WebConstants;

/**
 * @author Alexey.Ogarkov
 */

public class PDALoginPage extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(PDALoginPage.class);

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param form     The optional ActionForm bean for this request.
     * @param request  The HTTP HostConnector we are processing.
     * @param response The HTTP Response we are processing.
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //PDALoginForm loginForm = (PDALoginForm) form;
        ActionForward forward;
        m_log.debug("hear");
        HttpSession session = request.getSession();
        if (request.getUserPrincipal() != null) {
            String username = request.getUserPrincipal().getName();
            m_log.debug("User " + request.getParameter("j_username") + " attempting to log in while user " + request.getUserPrincipal() + " is still logged in - login out");
            forward = mapping.findForward(WebConstants.FWD_LOGIN_PAGE);
            session.invalidate();
            response.sendRedirect(forward.getPath() + "?" + WebConstants.ACTION + "=" + "logout" + "&username=" + username);
            response.flushBuffer();
            return null;
            //return mapping.findForward(WebConstants.FWD_DUMMY_PAGE);
        }

        session.setAttribute("j_username", request.getParameter("j_username"));
	    session.setAttribute("j_password", request.getParameter("j_password"));
	    session.setAttribute("j_security_check", "/j_security_check");

        if ( request.getParameter("jsenabled")!=null) {
            session.setAttribute("JSENABLED",request.getParameter("jsenabled").equalsIgnoreCase("true"));            
        }

        String forwardDestinee = WebConstants.FWD_MAIN_PDAPAGE;
		String url = WebConstants.ACTION_LOGIN_PROXY5;

        m_log.debug("LoginRouter. Redirecting to " + url + ". Destinee:" + forwardDestinee);
		session.setAttribute(WebConstants.FORWARD_DESTINEE, forwardDestinee);

		response.sendRedirect(response.encodeRedirectURL(url));

        return mapping.findForward(SUCCESS);

    }
}
