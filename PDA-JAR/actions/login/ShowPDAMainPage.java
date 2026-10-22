/*
 * ShowPDAMainPage.java
 *
 * Created on 20 �������� 2007 �., 16:35
 */

package com.infowave.pda.actions.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.commons.DisplayMode;

/**
 * @author Alexey.Ogarkov
 */

public class ShowPDAMainPage extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    public final static String PHONE_SUCCESS = "phone.success";
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(ShowPDAMainPage.class);

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
        // Extract attributes  we will need
        m_log.debug("PDAMainPage5. Start");
        MessageResources messages = getResources(request);
        HttpSession session = request.getSession();
        DesktopManager desktopManager = DelegateFactory.getDesktopManager();

        // Get the session State
        SessionState state = WebUtil.getSessionState(request);
        // We refresh the session state only if it's not the first time we visit
        // this page. If it;s teh first time, the state has beeb refreshed when
        // it was created by the filter.
        if (session.getAttribute("c4.firstTimeInMainPage") == null) {
            m_log.debug("PDAMainPage5. First time here");
            session.setAttribute("c4.firstTimeInMainPage", "whatever");
        } else {
            m_log.debug("PDAMainPage. State refresh");
            state.refresh(request);
        }
        if (!state.getClientCapabilities().getInitialDisplay().equals(DisplayMode.PHONE)) {
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(PHONE_SUCCESS);
        }
    }
}
