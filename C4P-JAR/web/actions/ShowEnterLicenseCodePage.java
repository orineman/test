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

import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.dto.DesktopDTO;

/**
 */
public class ShowEnterLicenseCodePage extends UserAction
{
     /** The class's logger */
  private static Log m_log = LogFactory.getLog(ShowEnterLicenseCodePage.class);

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
    String did = request.getParameter(WebConstants.DID);

    if (did == null) {
      m_log.warn("not did in request");
      PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
      errors.add(error);
      return null;
    }

    // Find the desktop
    SessionState state = WebUtil.getSessionState(request);
    DesktopDTO desktop = state.getUserDesktop(did);
    if (desktop == null) {
      m_log.warn("The desktop " + did + " does not belong to user " + state.getUserDTO().getUsername());
      PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
      errors.add(error);
      return null;
    }
    // Check whether this desktop has expired
    if (desktop.isHasExpired()) {
      request.setAttribute("expired","true");
    }
    // Protect next action with a token
    super.saveToken(request);
    return null;
  }

}
