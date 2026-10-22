package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.commons.DisplayMode;

/**
 * This action is responsible for switching teh display mode (OC,PDA,PHONE).
 */
public class ChangeDisplayMode extends UserAction
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ChangeDisplayMode.class);

  /**
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
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    SessionState state =  WebUtil.getSessionState(request);
    String mode = request.getParameter(WebConstants.DISPLAY_MODE);
    ActionForward forward = null;
    if (mode != null) {
      DisplayMode displayMode = null;
      displayMode = DisplayMode.getFromCode(mode);
      if (!state.getClientCapabilities().supportsMode(displayMode)) {
        m_log.debug("This client does not support the display mode " + mode +
                    " - ignoring");
      } else {
        state.setDisplayMode(displayMode);
      }

      // This is for teh case where a client supports teh 3 authentication mode
      // and the user switches tp PHONE mode from the login page (if a device
      // supporst all 3 modes teh initoal mode is PDA).
      HttpSession session = request.getSession();
      if (displayMode.equals(DisplayMode.PHONE) || displayMode.equals(DisplayMode.XML)) {
        session.setAttribute("C4P_FORCE_BASIC_AUTH", "true");
      } else {
        session.removeAttribute("C4P_FORCE_BASIC_AUTH");
      }
    }


    // If we have a logged in user, we go to the main page, otherwise, we go
    // to the login page
    if (state.getUserDTO() != null) {
      forward = super.findForward(request,mapping,"success.user");
    } else {
      forward = super.findForward(request,mapping,"success.noUser");
    }

    return forward;
  }
}
