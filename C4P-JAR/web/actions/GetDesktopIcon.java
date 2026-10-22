package com.infowave.c4p.web.actions;

import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.web.util.ResourceManager;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class GetDesktopIcon extends UserAction
{
/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(GetDesktopIcon.class);

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
	String did = request.getParameter(WebConstants.DID);
	byte[] icon = null;

	if(did == null)
	{
		m_log.warn("no did specified");
	}
	else
	{
		SessionState state = WebUtil.getSessionState(request);
		DesktopDTO desktop = state.getDesktop(did);
		if(desktop == null)
		{
			m_log.warn("This did in not in the user's did list: " + did);
		}
		else
		{
			m_log.warn("====getDesktopIconAsByteArray for desktop " + did);
			icon = ResourceManager.getDesktopIconAsByteArray(request, desktop.isConnected(), desktop.getRdpEnabled());
		}
	}
	if(icon != null)
	{
		m_log.warn("====getOutputStream for desktop " + did);
		response.getOutputStream().write(icon);
	}
	else
	{
		m_log.warn("could not get icon for desktop " + did);
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	response.flushBuffer();

	// keep Struts happy
	ActionForward actionForward = mapping.findForward("dummyPage");
	return actionForward;
}
}
