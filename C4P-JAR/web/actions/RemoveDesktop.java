package com.infowave.c4p.web.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;

/**
 * Removes a desktop. The desktop will be deleted from the DB.  
 */

public class RemoveDesktop extends UserAction
 {

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(EditDesktop.class);

  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception java.io.IOException if an input/output error occurs
   * @exception javax.servlet.ServletException if a servlet exception occurs
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
    SessionState state = WebUtil.getSessionState(request);
    String did = request.getParameter(WebConstants.DID);

    try {
      if (did != null) {
        DesktopDTO desktop = state.getDesktop(did);
        if (desktop != null) {
          DelegateFactory.getDesktopManager().removeDesktop(did);
          // remove the desktop from out internal list.
          state.refreshDesktopList();
      } else {
          // The url was probably hacked.
          m_log.info("Invalid did passed in the request: " + did + " - ignoring");
        }
      } else {
        m_log.debug("Missing did parameter - ignoring");
      }
    }
    catch(ServiceException e){
      m_log.error("cannot save desktop",e);
    }

    // The desktop is edited in a popup window. The forward for this action is
    // the popupCloser page. We set in the request the page that should be
    // displayed in the main window (the popup's opener) when the popup is
    // closed.
    ActionForward main = mapping.findForward("showAccountManagementPage");
    request.setAttribute(WebConstants.PAGE,main.getPath());

    return null;

  }
}
