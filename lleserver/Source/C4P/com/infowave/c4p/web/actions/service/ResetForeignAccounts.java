package com.infowave.c4p.web.actions.service;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.ServiceException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
/**
 * This action is responsible for resetting the list of users that have access
 * to a desktop.
 *
 * This action is not meant to be called by a browser.
 *
 */
public class ResetForeignAccounts extends BaseAction
{
    /** The class's logger */
  private static Log m_log = LogFactory.getLog(ResetForeignAccounts.class);


  private static final String MSG_SUCCESS            = "0 success";
  private static final String MSG_INVALID_OWNER      = "1 invalid owner";
  private static final String MSG_INVALID_DID        = "2 invalid did";
  private static final String MSG_INVALID_PARAMETERS = "3 invalid parameters";
  private static final String MSG_INVALID_USERNAME =   "4 invalid username";
  private static final String MSG_GENERAL_ERROR      = "255 general error";
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

    message = attachDesktopToAccounts (mapping,form,request,response,errors);

    response.setHeader("content-type", "text/html; charset=utf-8");
    response.setHeader("content-length", Integer.toString(message.length()));
    response.setStatus(200);
    response.getWriter().write(message);
    response.flushBuffer();
    return null;
  }

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
  public String attachDesktopToAccounts (ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response,
                                         List errors)
          throws Exception
  {
    String message;
    String did = request.getParameter(WebConstants.DID);
    String owner = request.getParameter(WebConstants.OWNER);
    String users = request.getParameter(WebConstants.USERS);


    if ((did == null) || (owner == null) ){
      m_log.debug("one of the parameters is missing");
      return MSG_INVALID_PARAMETERS;
    }
    // Get the list of users
    List userList = new ArrayList(3);
    if (users!= null) {
      StringTokenizer st = new StringTokenizer(users,",");
      while (st.hasMoreTokens()) {
        userList.add(st.nextToken());
      }
    }
    // Performs the operation
    try {
      DesktopManager desktopManager = DelegateFactory.getDesktopManager();
      desktopManager.resetForeignAccounts(did,owner,userList);
      message = MSG_SUCCESS;
      }
      catch(ServiceException e) {
        PortalError error = e.getError();
        if (error.getCode().equals(ErrorCode.NOT_OWNER_OF_DESKTOP)) {
          m_log.debug(owner + " does not own desktop " + did);
          message =  MSG_INVALID_OWNER;
        } else if (error.getCode().equals(ErrorCode.UNKNOWN_DID_DEFINED)) {
          m_log.debug("unknow did " + did);
          message = MSG_INVALID_DID;
        } else if (error.getCode().equals(ErrorCode.INVALID_USERNAME)) {
          m_log.debug(owner + " at least one username is invalid: " + users);
          message = MSG_INVALID_USERNAME;
        }  else if (error.getCode().equals(ErrorCode.INVALID_PARAMETERS)) {
          m_log.debug("Invalid parameters " + did + " " + owner + " " + users);
          message = MSG_INVALID_PARAMETERS;
        } else {
          m_log.debug("could not complete the operation",e);
          message = MSG_GENERAL_ERROR;
        }
      }
  return message;
  }
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
