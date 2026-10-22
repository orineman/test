package com.infowave.c4p.web.actions;

import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.web.util.*;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */

/**
 * This action is responsible for displaying a desktop page.
 */
public class ShowDesktop extends UserAction
{
/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(ShowDesktop.class);

public ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception

{
	ActionForward actionForward = null;
	String did = null;
	String desktopURL = null;
	SessionState state;

	did = request.getParameter(WebConstants.DID);

	// if we don't find a did it's probably because the user tinkered with the
	// url. We simply log a debug message and redisplay then man page.
	if(did == null)
	{
		m_log.debug("no did in the request");
		return mapping.findForward("input");
	}
	// get the sessionSate
	state = WebUtil.getSessionState(request);
	state.refresh(request);

	// Get the desktop. If we can't find it in our list, it's probably because
	// the user tinkered with the url or used the Back button.
	DesktopDTO desktop = state.getDesktop(did);
	if(desktop == null)
	{
		m_log.debug("the user " + state.getUserDTO().getUsername() + "does not have access desktop " + did);
		return mapping.findForward("input");
	}

	// We change the state to reflect the new current desktop
	state.setCurrentDesktop(desktop);
	m_log.debug("set the current desktop to :" + desktop.getDid());

	// Build a httpMethod for the url to retrieve and store it in the request
	// We store the url to retrieve in the session
	HttpMethod method;
	String appListUrl;
	Map headers;

	appListUrl = desktop.getAppListUrl(state.getDisplayMode());
	m_log.debug("appListURL:" + appListUrl);
	method = new GetMethod(appListUrl);
	m_log.debug("getApplicationRequestHeader. Begin");
	headers = DesktopUtils.getApplicationRequestHeader(request, state.getCurrentDesktop());
	for(Iterator it = headers.keySet().iterator(); it.hasNext();)
	{
		String headerName = (String) it.next();
		m_log.debug("getApplicationRequestHeader. Header:" + headerName);
		method.addRequestHeader(headerName, (String) headers.get(headerName));
	}

	RequestRedirector redirector = new RequestRedirector(appListUrl, headers);
	request.setAttribute(WebConstants.METHOD, redirector);
	m_log.debug("End ShowDesktop");
	// let the framework pick the forward
	return null;
}


}
