package com.infowave.c4p.web.actions;

import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.RCUsageDAO;
import com.infowave.c4p.model.RCUsage;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This classes aggregates the actions for the whole license-setting process:
 */
public class SetRCLicenseCode extends UserAction
{
/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(SetRCLicenseCode.class);

/**
 * Executes the business logic.
 *
 * @param mapping	The ActionMapping used to select this instance
 * @param actionForm The optional ActionForm bean for this request (if any)
 * @param request	The HTTP request we are processing
 * @param response   The HTTP response we are creating
 * @return a forward
 * @throws IOException	  if an input/output error occurs
 * @throws ServletException if a servlet exception occurs
 * @throws Exception
 */
public ActionForward executeLogic(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception
{
	HttpSession session = request.getSession();
	SessionState state = WebUtil.getSessionState(request);
	RCLicenseForm form = (RCLicenseForm) actionForm;
	LicenseManager licenseManager = DelegateFactory.getLicenseManager();
	ActionForward forward = null;

	// The parameters we want to pas to the next action, no matter what
	request.setAttribute("did", form.getDid());
	request.setAttribute("code", form.getCode());
	request.setAttribute("extensionAnswer", form.getExtensionAnswer());

	// License pages are protected by a token
	if(!super.isTokenValid(request, true))
	{
		System.out.println("token not valid");
		PortalError error = new PortalError(ErrorCode.FORM_ALREADY_SUBMITTED);
		errors.add(error);
		return null;
	}
	else
	{
		System.out.println("token valid");
	}
	// Clear the token
	super.resetToken(request);

	// Regardless of the operation we're performing, we need a valid did and a
	// valid license code in the request
	List inputErrors = validateLicenseCodeAndDid(mapping, actionForm, request);
	if(!inputErrors.isEmpty())
	{
		errors.addAll(inputErrors);
		super.saveToken(request);
		return null;
	}

	// Process the action
	if(form.getEnterRCLicenseCodeButton() != null)
	{
		// The user has just entered a license code
		forward = processLicenseCodeEntered(mapping, actionForm, request, response, errors);
	}
	else if(form.getExtensionQuestionButton() != null)
	{
		// The user has answered the extension question. We store this information
		//in the request and go to the confirmation page.
		String answer = form.getExtensionAnswer();
		if(answer == null)
		{
			m_log.warn("no answer in the request");
			PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
			errors.add(error);
		}
		else
		{
			forward = super.findForward(request, mapping, "success.confirmation");
		}
	}
	else if(form.getConfirmButton() != null)
	{
		// The user has pressed the confirmation button
		forward = processConfirmation(mapping, actionForm, request, response, errors);
	}
	else
	{
		// Invalid action - the user must be tinkering with the form
		m_log.warn("Unknown action");
		PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
		errors.add(error);
	}
	// Add a new token
	super.saveToken(request);
	return forward;
}


/**
 * Process the license code the user just entered
 *
 * @param mapping	The ActionMapping used to select this instance
 * @param actionForm The optional ActionForm bean for this request (if any)
 * @param request	The HTTP request we are processing
 * @param response   The HTTP response we are creating
 * @return a forward
 * @throws IOException	  if an input/output error occurs
 * @throws ServletException if a servlet exception occurs
 * @throws Exception
 */
public ActionForward processLicenseCodeEntered(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception
{
	ActionForward actionForward = null;
	HttpSession session = request.getSession();
	SessionState state = WebUtil.getSessionState(request);
	RCLicenseForm form = (RCLicenseForm) actionForm;
	LicenseManager licenseManager = DelegateFactory.getLicenseManager();

	// If the user already has a fixed term license, we check whether the new
	// license is a fixed term too. In that case, we must ask the user she s/he
	// wants to extend the existing license.
	boolean mustAskAboutExtension = false;

	RCUsage rcusage = null;
	RCUsageDAO rcUsageDAO = DAOFactory.getRCUsageDAO();
	rcusage = rcUsageDAO.getByDid(form.getDid());
	if(rcusage != null)
	{
		if(rcusage.getLicenseSerialNumber() != null && !rcusage.isLicenseExpired())
		{
			mustAskAboutExtension = true;
		}
	}

	if(mustAskAboutExtension)
	{
		actionForward = super.findForward(request, mapping, "success.askAboutExtension");
	}
	else
	{
		actionForward = super.findForward(request, mapping, "success.confirmation");
	}
	return actionForward;
}


private List validateLicenseCodeAndDid(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request)
{
	List inputErrors;
	RCLicenseForm form = (RCLicenseForm) actionForm;
	inputErrors = form.validateLicenseCode(mapping, request);
	inputErrors.addAll(form.validateDid(mapping, request));
	return inputErrors;
}

/**
 * Proces the license code the user just entered
 *
 * @param mapping	The ActionMapping used to select this instance
 * @param actionForm The optional ActionForm bean for this request (if any)
 * @param request	The HTTP request we are processing
 * @param response   The HTTP response we are creating
 * @return a forward
 * @throws IOException	  if an input/output error occurs
 * @throws ServletException if a servlet exception occurs
 * @throws Exception
 */
public ActionForward processConfirmation(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception
{
	ActionForward actionForward = null;
	HttpSession session = request.getSession();
	SessionState state = WebUtil.getSessionState(request);
	RCLicenseForm form = (RCLicenseForm) actionForm;
	LicenseManager licenseManager = DelegateFactory.getLicenseManager();

	boolean extendLicense = false;

	if((form.getExtensionAnswer() != null) && (form.getExtensionAnswer().equalsIgnoreCase("extend")))
	{
		extendLicense = true;
	}
	DesktopDTO desktop = state.getDesktop(form.getDid());
	try
	{
		List freedUpLicenses;
		freedUpLicenses = licenseManager.setRCLicenseCode(state.getUserDTO().getId(), desktop.getDid(), form.getLicenseCode(), extendLicense);
		state.refreshDesktop(desktop.getDid());
		// The confirmation page displays the licenses that have been freed up.
		if(!freedUpLicenses.isEmpty())
		{
			request.setAttribute(WebConstants.FREEDUP_LICENSES, freedUpLicenses);
		}
		// When we close the confirmation window, a piece of javascript will cause
		// this page to be refreshed.
		ActionForward main = mapping.findForward("showAccountManagementPage");
		request.setAttribute(WebConstants.PAGE, main.getPath());
	}
	catch(ServiceException e)
	{
		// Add the error to our list so that the user gets a message.
		errors.add(e.getError());
		// If this error is not a user error, we log it
		ErrorCode code = e.getError().getCode();
		if(!code.equals(ErrorCode.INVALID_RC_LICENSE_NUMBER))
		{
			m_log.error("Could not set license", e);
		}
	}
	return actionForward;
}


}