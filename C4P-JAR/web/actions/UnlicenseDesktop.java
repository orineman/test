package com.infowave.c4p.web.actions;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
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
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Dec 12, 2003
 * Time: 6:05:29 PM
 * To change this template use Options | File Templates.
 */
public class UnlicenseDesktop extends UserAction
{

/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(UnlicenseDesktop.class);

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
	SessionState state = WebUtil.getSessionState(request);
	// Find the desktop
	DesktopDTO desktop = null;
	AccountDAO accountDAO = DAOFactory.getAccountDAO();

	String did = request.getParameter(WebConstants.DID);
	if(did == null)
	{
		m_log.warn("no did in request");
		PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
		errors.add(error);
		return null;
	}
	desktop = state.getUserDesktop(did);
	if(desktop == null)
	{
		m_log.warn("The user " + state.getUserDTO().getUsername() + " does not own desktop " + did);
		PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
		errors.add(error);
		return null;
	}
	// Unlicense the desktop
	LicenseManager licenseManager = DelegateFactory.getLicenseManager();

	try
	{
		Account acc = accountDAO.getById(state.getUserDTO().getId());
		List freedUpLicenses = licenseManager.unlicense(desktop.getId(), acc.getReseller(), true, null);
		state.refreshDesktop(desktop.getDid());
		// The confirmation page displays the licenses that have been freed up.
		if(!freedUpLicenses.isEmpty())
		{
			request.setAttribute(WebConstants.FREEDUP_LICENSES, freedUpLicenses);
		}
	}
	catch(ServiceException e)
	{
		errors.add(e.getError());
		m_log.error("Could not unlicense the desktop", e);
	}

	// The desktop is edited in a popup window. The forward for this action is
	// the popupCloser page. We set in the request the page that should be
	// displayed in the main window (the popup's opener) when the popup is
	// closed.
	ActionForward main = mapping.findForward("showAccountManagementPage");
	request.setAttribute(WebConstants.PAGE, main.getPath());

	return null;
}
}
