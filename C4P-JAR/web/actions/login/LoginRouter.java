package com.infowave.c4p.web.actions.login;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.WebUtil;
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
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class LoginRouter extends BaseAction

{
/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(LoginRouter.class);

/**
 * Executes the business logic.
 *
 * @param mapping  The ActionMapping used to select this instance
 * @param form	 The optional ActionForm bean for this request (if any)
 * @param request  The HTTP request we are processing
 * @param response The HTTP response we are creating
 * @return a forward
 * @throws IOException	  if an input/output error occurs
 * @throws ServletException if a servlet exception occurs
 * @throws Exception
 */
public ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception
{
	ActionForward forward = null;
	HttpSession session = request.getSession();
	SessionState state = WebUtil.getSessionState(request);

	// If we hava a user already logged in, we must log him/her first before
	// processing another login request. Not ideal because the new user will be
	// sent to the login page and prompted for his credentials again, but
	// it's the only option we have as the Web container maintains a reference
	// to the principal.
//	Map Pars = request.getParameterMap();
//	int iSize = Pars.size();
//	m_log.debug("++++++LoginRouter.Parameters:" + Integer.toString(iSize));

	if(request.getUserPrincipal() != null)
	{
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
	session.setAttribute("j_security_check", request.getParameter("j_security_check"));
	String sVersion = request.getParameter("j_version");
//	session.setAttribute("j_version", sVersion);
	//m_log.debug("Username = " + request.getParameter("j_username"));
	m_log.debug("LoginRouter. Setting version:" + sVersion);
	if(!sVersion.equals(""))
	{
		state.setVersion(Double.valueOf(sVersion));
	}

	String forwardDestinee = null;
	String url = null;

//	if(sVersion.indexOf("5") >= 0)
	if(state.getVersion() >= 5)
	{
		forwardDestinee = WebConstants.FWD_MAIN_PAGE5;
		url = WebConstants.ACTION_LOGIN_PROXY5;

		m_log.debug("LoginRouter. Redirecting to " + url + ". Destinee:" + forwardDestinee);
		session.setAttribute(WebConstants.FORWARD_DESTINEE, forwardDestinee);

		response.sendRedirect(response.encodeRedirectURL(url));
//		response.sendRedirect(response.encodeRedirectURL(forwardDestinee));
	}
	else
	{
		forwardDestinee = WebConstants.FWD_MAIN_PAGE;
		url = WebConstants.ACTION_LOGIN_PROXY;
		m_log.debug("LoginRouter. Redirecting to " + url + ". Destinee:" + forwardDestinee);
		session.setAttribute(WebConstants.FORWARD_DESTINEE, forwardDestinee);
		response.sendRedirect(response.encodeRedirectURL(url));
	}

/*
	String forwardDestinee = null;
	m_log.debug("LoginRouter to Version. Vers:" + Integer.toString(sVersion.indexOf("5")));
	if(sVersion.indexOf("5") >=0 )
	{
		forwardDestinee = WebConstants.FWD_MAIN_PAGE5;
		m_log.debug("LoginRouter to Version 5");
	}
	else
	{
		forwardDestinee = WebConstants.FWD_MAIN_PAGE;
		m_log.debug("LoginRouter to Version 4");
	}
	session.setAttribute(WebConstants.FORWARD_DESTINEE,forwardDestinee);
	// todo this should be in the constants file.
	String url ="/protected/loginProxy.do";
	url = WebConstants.FWD_LOGIN_PROXY;
	
//	  String url = "loginProxy";
	m_log.debug("LoginRouter. Redirecting to "+ url);
	response.sendRedirect(response.encodeRedirectURL(url));
 */
	m_log.debug("LoginRouter. End");
	forward = mapping.findForward(WebConstants.FWD_DUMMY_PAGE);
	return forward;
}

/**
 * Checks that this action is authorized.
 *
 * @param mapping  The ActionMapping used to select this instance
 * @param form	 The optional ActionForm bean for this request (if any)
 * @param request  The HTTP request we are processing
 * @param response The HTTP response we are creating
 * @return true if this action can be executed.
 */
protected boolean isAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionErrors errors)
{
	return true;
}

}
