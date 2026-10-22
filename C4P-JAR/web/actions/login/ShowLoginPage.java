// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 9/28/2004 9:03:18 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   ShowLoginPage.java

package com.infowave.c4p.web.actions.login;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowLoginPage extends BaseAction
{

    public ShowLoginPage()
    {
    }

    public ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors)
        throws Exception
    {
        HttpSession session = request.getSession();
        ActionForward actionForward = null;
        String action = request.getParameter("action");
        SessionState state = WebUtil.getSessionState(request);
        if(state != null)
		{
			state.notInAnySection();
			Object oVers = session.getAttribute("j_version");
			if(oVers != null)
			{
				String sVers = oVers.toString();
				m_log.debug("Login. Setting version:" + sVers);
				if(!sVers.equals(""))
					state.setVersion(Double.valueOf(sVers));
			}
		}
        if(request.getUserPrincipal() == null && session.getAttribute("j_username") != null && session.getAttribute("j_security_check") != null)
        {
            String url = "" + session.getAttribute("j_security_check") + "?j_username=" + session.getAttribute("j_username") + "&j_password=" + session.getAttribute("j_password") + "&j_version=" + session.getAttribute("j_version");
            session.removeAttribute("j_security_check");
            session.removeAttribute("j_username");
            session.removeAttribute("j_password");
			session.removeAttribute("j_version");
            m_log.debug("redirecting to " + url);
//            WebUtil.setNoCacheHeaders(response);
            response.sendRedirect(response.encodeRedirectURL(url));
            actionForward = mapping.findForward("dummy");
            return actionForward;
        }
        if(action == null)
            action = "protected";
        String forwardName = "success";
        if(action.equalsIgnoreCase("error") && !request.getRequestURL().toString().toLowerCase().startsWith("https"))
            forwardName = forwardName + ".http";
        forwardName = forwardName + "." + action;
//        WebUtil.setNoCacheHeaders(response);
        actionForward = super.findForward(request, mapping, forwardName);
        m_log.debug("forward name = " + forwardName);
        return actionForward;
    }

    protected boolean isAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse, ActionErrors actionerrors)
    {
        return true;
    }


    private static Log m_log;

    static
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.login.ShowLoginPage.class);
    }
}