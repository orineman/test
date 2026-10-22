package com.infowave.c4p.web.actions.login;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
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

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class Proxy extends BaseAction
{
/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(Proxy.class);

/*
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs
   * @return a forward
   * @throws Exception

   */
public ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception
{
	m_log.debug("++++++LoginProxy. Start");
	ActionForward forward = null;
	String originator = null;
	HttpSession session = request.getSession();

	//m_log.debug("Principal: " + request.getUserPrincipal());

	String forwardDestinee = null;
	forwardDestinee = (String) session.getAttribute(WebConstants.FORWARD_DESTINEE);

	forward = mapping.findForward(forwardDestinee);
	m_log.debug("++++++LoginProxy. Forward:"+ forward.getPath());

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
