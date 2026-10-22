package com.infowave.c4p.web.actions.service;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.util.ErrorCode;

import java.util.List;

/**
 * This action is responsible for setting a desktop's software version.
 *
 * This action is not meant to be called by a browser.
 *
 */
public class SetSoftwareVersion extends BaseAction
{
    /** The class's logger */
  private static Log m_log = LogFactory.getLog(SetSoftwareVersion.class);

  private static final String MSG_SUCCESS            = "0 success";
  private static final String MSG_INVALID_DID        = "1 invalid did";
  private static final String MSG_INVALID_PARAMETERS = "2 invalid parameters";
  private static final String MSG_GENERAL_ERROR      = "255 general error";
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
    String did = request.getParameter(WebConstants.DID);
    String version = request.getParameter(WebConstants.VERSION);
    String message = null;
    DesktopManager desktopMgr = DelegateFactory.getDesktopManager();
    try {
      if (did == null || version == null) {
        message = MSG_INVALID_PARAMETERS;
      } else {
        desktopMgr.setSoftwareVersion(did,version);
        message = MSG_SUCCESS;
      }
    }
    catch (ServiceException e){
      if (e.getError().getCode().equals(ErrorCode.UNKNOWN_DID_DEFINED)) {
        message = MSG_INVALID_DID;
      } else {
        m_log.error("Cannot set the sw version (" + version + ") for desktop: " + did);
        message = MSG_GENERAL_ERROR;
      }
    }
    response.setHeader("content-type", "text/html; charset=utf-8");
    response.setHeader("content-length", Integer.toString(message.length()));
    response.setStatus(200);
    response.getWriter().write(message);
    response.flushBuffer();
    return null;
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





