package com.infowave.c4p.web.actions;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Oct 19, 2003
 * Time: 9:37:09 PM
 * To change this template use Options | File Templates.
 */
public class Process404Error extends BaseAction {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(Process404Error.class);

    /**
     * Executes the business logic.
     *
     * @param mapping  The ActionMapping used to select this instance
     * @param form     The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return a forward
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet exception occurs
     * @throws Exception
     */
    public ActionForward executeLogic(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      List errors)
            throws Exception {
        // let the framework pick the forward.
        m_log.debug(request.getRequestURI());
        m_log.debug(request.getRequestURL());

        // If there is no desktop currently selected but this url contains a did,
        // it means that the user has clicked on a desktop link after the session
        // has expired.
        // Note: this assumes that all request that should be protected contains
        // a did. If this is not thje case, we should ask the desktop to make all
        // the urls it generates start with '/protected' (as defined in web.xml).
        // Note that we might have problems with multipart post requests as
        //  won't work.
        if (request.getParameter(WebConstants.DID) != null) {
            m_log.debug("This request contains a did - sending to login page");
            response.sendRedirect(WebConstants.ACTION_LOGIN_PAGE);
            response.flushBuffer();
        }
        HttpSession session = request.getSession();
        if (session != null) {
            if (session.getAttribute("j_version") != null) {
                if (((String) session.getAttribute("j_version")).equalsIgnoreCase("5.0")) {
                    return super.findForward(request, mapping, "success.5");
                }
            }
        }
        return null;

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
}
