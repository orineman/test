package com.infowave.c4p.web.actions.service;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.util.IdGenerator;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This action generates and returns a unique DID.
 * This action is not meant to be called by a browser.
 *
 */
public class GetDid extends BaseAction
{
     /** The class's logger */
  private static Log m_log = LogFactory.getLog(GetDid.class);

  private static final String MSG_SUCCESS            = "0 success";
  private static final String MSG_INVALID_DID        = "2 invalid did";
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
    StringBuffer message = new StringBuffer();

    String did;
    try {
      DesktopManager desktopMgr = DelegateFactory.getDesktopManager();
    // todo teh did size is hardcoded for now, It shuld be passed as a req param
    did = desktopMgr.generateUniqueDid(8);
    message.append("return: 0 success\n");
    message.append("result: ").append(did).append("\n");
    } catch(ServiceException e){
      message.append("return: 1 error\n");
    }
    response.setHeader("content-type", "text/html; charset=utf-8");
    response.setHeader("content-length", Integer.toString(message.length()));
    response.setStatus(200);
    response.getWriter().write(message.toString());
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
