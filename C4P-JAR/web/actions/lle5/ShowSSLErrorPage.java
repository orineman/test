/*
 * ShowSSLErrorPage.java
 *
 * Created on 2 ќкт€брь 2007 г., 17:48
 */

package com.infowave.c4p.web.actions.lle5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Alexey.Ogarkov
 */

public class ShowSSLErrorPage extends Action {

    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(ShowSSLErrorPage.class);
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param form     The optional ActionForm bean for this request.
     * @param request  The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        m_log.debug("SSLHandshakeExceotion");
        return mapping.findForward(SUCCESS);

    }
}
