package com.infowave.c4p.web.actions;

import com.infowave.c4p.web.util.*;
import com.infowave.c4p.service.dto.UserRegistrationDTO;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.model.Desktop;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * This action is responsible for creating new accounts and sending an email
 * notification to the user once the account has been created.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class CreateAccount extends BaseAction
 {

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(CreateAccount.class);

  private static LLOnyx onyx = new LLOnyx();
  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param actionForm The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs
   * @return a forward
   * @throws Exception

   */
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    ActionForward forward = null;
    CreateAccountForm form = (CreateAccountForm) actionForm;
    HttpSession session = request.getSession();
    SessionState state = WebUtil.getSessionState(request);
    boolean haveDid = (form.getDid() != null) && !form.getDid().equals("");

    // Validate the input parameters
    List inputErrors = form.validateInput(mapping,request);

    if (!inputErrors.isEmpty()) {
      errors.addAll(inputErrors);
      return null;
    }

    // Create the account
    UserRegistrationDTO info = new UserRegistrationDTO();
    info.setUsername(form.getUsername());
    info.setFirstname(form.getFirstName());
    info.setLastname(form.getLastName());
    info.setPassword(form.getPassword());
    info.setEmail(form.getEmail());
    info.setResellerId(state.getReseller().getName());
    info.setSecurityAnswer(form.getSecurityAnswer());
    info.setSecurityQuestion(form.getSecurityQuestion());
    info.setCountryCode(form.getCountryCode());

    // It it possible to create an account with or without a did
    if (haveDid) {
      info.setDid(form.getDid());
      info.setPcName(form.getPcName());
    } else {
      info.setDid(null);
      info.setPcName(null);
    }

    Locale userLocale = null;
    String locale = form.getLocale();
    if( locale == null || locale.equals("") ) {
	userLocale = state.getReseller().getDefaultLocale();
    } else {
    	userLocale = new Locale( locale );
    }
    info.setLocale( userLocale );

    try {
      DelegateFactory.getUserManager().RegisterUser(info);
      // Email the user about his/her new account
      Notifier.instance().notifyAccountCreation(state.getReseller().getName(),
                                                form.getEmail(), userLocale);
                                               // state.getReseller().getDefaultLocale());
      // remove the form from the session
      session.removeAttribute(mapping.getAttribute());
    }
    catch(ServiceException e) {
      // Add the error to our list so that the user gets a message.
      errors.add(e.getError());
      // If this error is not a user error, we log it
      ErrorCode code = e.getError().getCode();
      if (!code.equals(ErrorCode.USERNAME_ALREADY_EXISTS)) {
        m_log.error("Could not create account",e);
      }
    }

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

      if( !(((rccode != null && rccode.length() > 0 && desktop != null) ||
           (code != null && code.length() > 0)) && userDTO != null && licenseMgr != null)  ) {
		// update onyx with the account information only if license info isn't getting updated
	    // If the onyx update fails, allow the user to continue
	    try {
		  onyx.UpdateUserInfo( form.getUsername() );
	    } catch( Exception ignored ) {
			m_log.error("Unable to update ONYX for " +form.getUsername() );
		}
	  }

      if( code != null && code.length() > 0 && userDTO != null && licenseMgr != null && desktop != null ) {

       	// update onyx with the license information
	    // If the onyx update fails, allow the user to continue
	    try {
      	  onyx.UpdateLicenseInfo( form.getUsername(), code );
	    } catch( Exception ignored ) {
			m_log.error("Unable to update ONYX for " +form.getUsername()+ ", code = " +code );
		}

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

       	// update onyx with the license information
	    // If the onyx update fails, allow the user to continue
	    try {
      	  onyx.UpdateLicenseInfo( form.getUsername(), rccode );
	    } catch( Exception ignored ) {
			m_log.error("Unable to update ONYX for " +form.getUsername()+ ", rccode = " +rccode );
		}

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

    if (errors.isEmpty()) {
       if (haveDid) {
         forward = super.findForward(request,mapping,"successWithDesktop");
       } else {
         forward = super.findForward(request,mapping,"success");
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
                                 ActionErrors errors) {
    return true;
  }

}
