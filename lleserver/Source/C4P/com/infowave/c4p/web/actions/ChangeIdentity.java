package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ChangeIdentity extends UserAction
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ChangeIdentity.class);

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
    SessionState state = WebUtil.getSessionState(request);
    ChangeIdentityForm form = (ChangeIdentityForm) actionForm;

    // Validate the input parameters
    List inputErrors = form.validateInput(mapping,request);

    if (!inputErrors.isEmpty()) {
      errors.addAll(inputErrors);
      return null;
    }

    try {
      UserDTO updatedUser = null;
      Locale userLocale = null;
      String locale = form.getLocale();
      if( locale != null && !locale.equals("") ) {
	userLocale = new Locale(locale, "");
      } 

      updatedUser = DelegateFactory.getUserManager().changeIdentity(state.getUserDTO().getUsername(),
                                                      form.getFirstName(),
                                                      form.getLastName(),
                                                      form.getEmail(),
				      userLocale);
      state.setUserDTO(updatedUser);
      state.setLocale(request.getSession(),updatedUser.getLocale());
    }
    catch(ServiceException e) {
      // Add the error to our list so that the user gets a message.
      errors.add(e.getError());
      // If this error is not a user error, we log it
      ErrorCode code = e.getError().getCode();
      m_log.error("Could not change identity",e);
    }

    return null;
  }

}
