package com.infowave.c4p.web.actions;

import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Sep 27, 2003
 * Time: 5:55:43 PM
 * To change this template use Options | File Templates.
 */
public class ShowMainPage extends UserAction
{
/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(ShowMainPage.class);

public ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception
{
	m_log.debug("ShowDewsktopPage. Start");
	// Extract attributes  we will need
	MessageResources messages = getResources(request);
	HttpSession session = request.getSession();
	DesktopManager desktopManager = DelegateFactory.getDesktopManager();

	// Get the session State
	SessionState state = WebUtil.getSessionState(request);
	// We refresh the session state only if it's not the first time we visit
	// this page. If it;s teh first time, the state has beeb refreshed when
	// it was created by the filter.
	if(session.getAttribute("c4.firstTimeInMainPage") == null)
	{
		m_log.debug("ShowDewsktopPage. First time here");
		session.setAttribute("c4.firstTimeInMainPage", "whatever");
	}
	else
	{
		m_log.debug("ShowDewsktopPage. State refresh");
		state.refresh(request);
	}
	m_log.debug("ShowDewsktopPage. enterDesktopSection");
	state.enterDesktopSection();

	// Set the reseller's welcome page in the request
	Locale locale = state.getLocale();

	return null;
}

}
