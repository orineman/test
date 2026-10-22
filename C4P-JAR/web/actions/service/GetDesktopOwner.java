package com.infowave.c4p.web.actions.service;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This action returns the owner of a desktop given the desktop ID.
 * This action is not meant to be called by a browser.
 *
 */
public class GetDesktopOwner extends BaseAction
{
     /** The class's logger */
  private static Log m_log = LogFactory.getLog(GetDesktopOwner.class);

  private static final String MSG_SUCCESS            = "0 success:";
  private static final String MSG_INVALID_DID        = "2 invalid did:";
  private static final String MSG_GENERAL_ERROR      = "255 general error:";

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
    String message = null;
    String did = request.getParameter(WebConstants.DID);

    message = getOwner (mapping,form,request,response,errors);

    response.setHeader("content-type", "text/html; charset=utf-8");
    response.setHeader("content-length", Integer.toString(message.length()));
    response.setStatus(200);
    response.getWriter().write(message);
    response.flushBuffer();
    return null;


  }


  public String getOwner(ActionMapping mapping,
                         ActionForm form,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         List errors)
          throws Exception
  {
    String message = null;
    String did = request.getParameter(WebConstants.DID);
    if (did == null) {
      return MSG_INVALID_DID;
    }
    try {
      DesktopDTO dto = DelegateFactory.getDesktopManager().getByDid(did);
      if (dto == null) {
        message = MSG_INVALID_DID;
      } else {
        message = MSG_SUCCESS + "\n" + dto.getOwner();
      }
    }
    catch (ServiceException e){
      PortalError error = e.getError();
      if (error.getCode().equals(ErrorCode.UNKNOWN_DID_DEFINED)) {
        m_log.debug("Invalid did " + did);
        message =  MSG_INVALID_DID;
      } else  {
          m_log.debug("could not complete the operation",e);
        message = MSG_GENERAL_ERROR;
      }
    }
    return message;
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
