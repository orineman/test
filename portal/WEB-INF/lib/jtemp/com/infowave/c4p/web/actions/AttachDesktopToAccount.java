package com.infowave.c4p.web.actions;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.delegate.LicenseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.model.Desktop;


/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Nov 16, 2003
 * Time: 10:42:18 PM
 * To change this template use Options | File Templates.
 */
public class AttachDesktopToAccount extends BaseAction
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(AttachDesktopToAccount.class);
  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param actionForm The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception java.io.IOException if an input/output error occurs
   * @exception javax.servlet.ServletException if a servlet exception occurs
   * @return a forward
   * @throws java.lang.Exception

   */
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
        ActionForward forward = null;
    AttachDesktopToAccountForm form = (AttachDesktopToAccountForm) actionForm;
    SessionState state = WebUtil.getSessionState(request);

    // Validate the input parameters
    List inputErrors = form.validateInput(mapping,request);

    if (!inputErrors.isEmpty()) {
      m_log.debug("errors");
      errors.addAll(inputErrors);
      return null;
    }
    m_log.debug("no errors");

    // Add the desktop
    try {
      DelegateFactory.getUserManager().addDesktopToAccount(form.getUsername(),
                                                           form.getPassword(),
                                                           form.getDid(),
                                                           form.getPcName());

      // if there is a normal license -- set that into the database
      String code =  form.getLicenseCode();
      String rccode =  form.getLicenseCodeRC();
      UserDTO userDTO = null;
      LicenseManager licenseMgr = null;
      if( (code != null && code.length() > 0) || (rccode != null && rccode.length() > 0) ) {
        	UserManager userManager = DelegateFactory.getUserManager();
        	userDTO = userManager.getUserDTO(form.getUsername());
    	licenseMgr = DelegateFactory.getLicenseManager();

      }

      DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
      Desktop desktop = desktopDAO.getByDid(form.getDid()); 

      if( code != null && code.length() > 0 && userDTO != null && licenseMgr != null && desktop != null ) {

      	try {
       		List freedUpLicenses =  licenseMgr.setLicenseCode(userDTO.getId(),
                        	desktop.getId(), code, false);
     	} catch (ServiceException e) {
      		// Add the error to our list so that the user gets a message.
       		errors.add(e.getError());
       		// If this error is not a user error, we log it
       		ErrorCode ecode = e.getError().getCode();
       		if (!ecode.equals(ErrorCode.INVALID_LICENSE_NUMBER)) {
          			m_log.error("Could not set license",e);
       		}
        		forward = super.findForward(request,mapping,"success.licenseexception");
    	}

      }

       // if there is an rc license -- set that into the database
      if( rccode != null && rccode.length() > 0 && userDTO != null && licenseMgr != null ) {

      	try {
       		 List freedUpLicenses =  licenseMgr.setRCLicenseCode(userDTO.getId(),
                         	form.getDid(), rccode, false);
     	} catch (ServiceException e) {
      		// Add the error to our list so that the user gets a message.
       		errors.add(e.getError());
       		// If this error is not a user error, we log it
       		ErrorCode ecode = e.getError().getCode();
       		if (!ecode.equals(ErrorCode.INVALID_RC_LICENSE_NUMBER)) {
          			m_log.error("Could not set rc license",e);
       		}
        		forward = super.findForward(request,mapping,"success.licenseexception");
      	 }
        }

    } catch(ServiceException e) {
      // Add the error to our list so that the user gets a message.
      errors.add(e.getError());
      // If this error is not a user error, we log it
      ErrorCode ecode = e.getError().getCode();
      if (!ecode.equals(ErrorCode.PC_NAME_ALREADY_EXISTS) &&
              !ecode.equals(ErrorCode.INVALID_USERNAME) &&
              !ecode.equals(ErrorCode.INVALID_PASSWORD)) {
        m_log.error("Could not add desktop = " +
                    " username = " + form.getUsername() +
                    " did = " + form.getDid() ,e);
      }
    }

    return forward;

  }

  /**
   * Checks that this action is authorized.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return true if this action can be executed.
   */
  protected boolean isAuthorized(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 ActionErrors errors)
  {
    return true;
  }
}
