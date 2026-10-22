package com.infowave.c4p.web.actions.lle5;

import com.infowave.c4p.web.util.BaseAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UploadPage extends BaseAction
{
	public UploadPage()
	{
	}

	public ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors)
		throws Exception
	{
//		m_log.debug("UploadPage. Welcome");
//		HttpSession session = request.getSession();
		ActionForward actionForward = null;
//		String path = request.getParameter("path");
//		String did = request.getParameter("did");
//		SessionState state = WebUtil.getSessionState(request);
/*
		if(request.getUserPrincipal() == null && session.getAttribute("j_username") != null && session.getAttribute("j_security_check") != null)
		{
//			String url = "" + session.getAttribute("j_security_check") + "?j_username=" + session.getAttribute("j_username") + "&j_password=" + session.getAttribute("j_password") + "&j_version=" + session.getAttribute("j_version");
			String url = "" + session.getAttribute("j_security_check") + "?j_username=" + session.getAttribute("j_username") + "&j_password=" + session.getAttribute("j_password");
			session.removeAttribute("j_security_check");
			session.removeAttribute("j_username");
			session.removeAttribute("j_password");
//			session.removeAttribute("j_version");
			m_log.debug("redirecting5 to:" + url);
//            WebUtil.setNoCacheHeaders(response);
			response.sendRedirect(response.encodeRedirectURL(url));
			actionForward = mapping.findForward("dummy");
			m_log.debug("forwarding5 to:" + actionForward);
			return actionForward;
		}
		if(action == null)
			action = "protected";
*/
		String forwardName = "success";
		forwardName = forwardName + "." + "pc";
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
		m_log = LogFactory.getLog(com.infowave.c4p.web.actions.lle5.UploadPage.class);
	}
}
