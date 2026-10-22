package com.infowave.c4p.web.actions;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Nov 21, 2003
 * Time: 7:46:39 PM
 * To change this template use Options | File Templates.
 */
public class EditDesktop extends UserAction
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

    if (request.getParameter("save") != null) {
      return save(mapping,form,request,response,errors);
    } else if (request.getParameter("remove") != null) {
      return remove(mapping,form,request,response,errors);
    } else {
      m_log.info("No action specified");
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      errors.add(error);
    }
    return null;
  }



  public ActionForward save(ActionMapping mapping,
                            ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            List errors)
          throws Exception
  {
    SessionState state = WebUtil.getSessionState(request);

    String did = request.getParameter(WebConstants.DID);
    String name = request.getParameter("pcName");
    String visible = request.getParameter("visible");

    try {
    if ((did != null) && (name != null) ) {
      boolean isVisible = (visible != null) && visible.equalsIgnoreCase("on");
      DesktopDTO desktop = state.getDesktop(did);
      if (desktop != null) {
        DesktopDTO newDTO = new DesktopDTO();
        newDTO.setName(name);
        newDTO.setVisible(isVisible);
        DesktopDTO updated = DelegateFactory.getDesktopManager().update(did,newDTO);
        desktop.setName(updated.getName());
        desktop.setVisible(updated.isVisible());
      } else {
        m_log.debug("save: Invalid did passed in the request - ignoring");
      }
    } else {
      m_log.debug("save: missing parameter - ignoring");
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

    public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors)
        throws Exception
    {
        ActionForward forward = super.findForward(request, mapping, "confirmRemoveDesktop");
        if(forward == null)
            m_log.error("no forward");
        else
            m_log.debug("remove - forward: " + forward.getPath());
        return forward;
    }



}
