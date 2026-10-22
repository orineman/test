package com.infowave.c4p.web.util;

import org.apache.struts.action.*;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;

public abstract class BaseAction extends Action
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(BaseAction.class);

// ----------------------------------------------------------- Hot-spot methods
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
  public abstract ActionForward executeLogic(ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response,
                                             List errors)
          throws Exception;

  /**
   * Checks that this action is authorized.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return true if this action can be executed.
   */
  protected abstract boolean isAuthorized(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response,
                                          ActionErrors errors);

  /**
   * This will be executed before executing the business logic.
   *
   * @throws Exception
   */
  protected void preProcessing(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               List errors) throws Exception {}

  // ------------------------------------------------------------ Action methods
  /**
   * Skeleton perform that calls the other "hotspot" methods in
   * this class in turn. Typically, subclasses can override the other
   * methods and leave this one as is.
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs
   */
  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
    throws IOException, ServletException
  {
    ActionForward actionForward = null;
    List errors = new ArrayList(4);
    String actionType =  mapping.getType();
    HttpSession session = request.getSession(true);

    //if (!mapping.getType().endsWith("ErrorPage")) throw new RuntimeException();

    // Make sure this action is authorized
    //if (!isAuthorized(mapping,form,request,response,errors)){
    //}
    m_log.debug(mapping.getType());
    
    // Execute the business logic
    try {
      preProcessing(mapping,form,request,response,errors);
      actionForward = executeLogic(mapping,form,request,response,errors);
    }
    catch (Exception e) {
      m_log.error(e);
      catchException(mapping,form,request,response,errors,e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return null;
    }
    //m_log.debug("is commiited: " + response.isCommitted());
    if (response.isCommitted()) {
      return null;
    }
    // If there are errors, find the appropriate page.
    if (!errors.isEmpty()) {
      return findError(mapping,form,request,response,errors,actionForward);
    }

    // If we don't have a forward, find one
    if (actionForward == null){
      actionForward = findNominal(mapping,form,request,response,errors);
    }

    // If we don't have a mapping at this point, it's an application bug. Log it
    // and forward to the error page.
    if (actionForward == null) {
      m_log.error("Could not find a forward for mapping: "+ mapping.getName() +
                  " action = " + actionType);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    // Log a message
    if (actionForward != null) {
      m_log.debug("Forwarding " + mapping.getType() + " to " + actionForward.getPath());
    }
    return actionForward;
  }

  // ------------------------------------------------------------ Helper methods
  /**
   * Process an exception raised by a sublcass
   */
  protected void catchException(ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response,
				List errors,
				Exception exception)
          throws IOException
  {
    String msg = "Cannot perform action " + mapping.getType();
    m_log.error(msg,exception);
    errors.add(new PortalError(ErrorCode.SYSTEM_ERROR));
    //response.setStatus();
    response.sendError(500,"portal error");
  }
  /**
   * Return the appropriate ActionForward for an error condition.
   * The default method returns a forward to input, when there is one, or
   * "error" when not.
   * The application must provide an "error" forward.
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request(if any)
   * @param request The HTTP request we are processing
   * @param response The resonse we are creating
   */
  protected ActionForward findError(ActionMapping mapping,
				    ActionForm form,
				    HttpServletRequest request,
				    HttpServletResponse response,
				    List errors,
				    ActionForward actionForward)
  {
    ActionForward forward = null;

    if (errors.isEmpty())
      return null;

    WebUtil.setErrors(request,errors);
    //saveErrors(request, errors);

    // If we already have a forward, we use it. Otherwise:
    // If the mapping defines an input page, use it.
    // Because the input page will most likely rely on  some request parameters
    // to display its data, we add to the forward all the parameters we found
    // in the request, but only if we do a redirect (for a strauigth forward,
    // they are in the request already).
    forward = actionForward;

    if (forward == null) {
      String input = mapping.getInput();

      if (input != null) {
        if (input.startsWith("/")) {
          forward = new ActionForward(input);
        } else {
          forward =  mapping.findForward(input);
        }
        if (forward == null) {
          m_log.warn("findError - no forward found for input: " + input +
                     " action: " + mapping.getType());
        }
      } else {
        m_log.warn("findError - No input for action: " + mapping.getType());
      }

    }
    // add the request parameters if needed
    if ( (forward != null) && forward.getRedirect()){
      forward = ReqUtils.putBackAllParametersInRequest(forward,request);
    }
    return forward;
  }

   /**
   * Return the appropriate ActionForward for the nominal,
   * non-error state.
   * The default returns mapping.findForward("continue");
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The response we are creating
   * @param errors Our ActionErrors collection
   */
  protected ActionForward findNominal(ActionMapping mapping,
				      ActionForm form,
				      HttpServletRequest request,
				      HttpServletResponse response,
				      List errors
				      )
  {
    ActionForward actionForward = null;
    SessionState state = WebUtil.getSessionState(request);
    //DisplayMode displayMode;

    if (state == null) {
      m_log.debug ("No state");
      return null;
    }
    //displayMode = state.getDisplayMode();
    // Try to find a 'success' forward
    if (actionForward == null) {
      actionForward = findForward(request,mapping,"success");
    }
    if (actionForward == null) {
      m_log.error("No nominal forward for mapping: " + mapping.getName());
    }

    return actionForward;
  }

  /**
   * Finds right the forward for the current display type.
   * @param request
   * @param mapping
   * @param name
   * @return
   */
  protected ActionForward findForward(HttpServletRequest request,
                                   ActionMapping mapping, String name)
  {
    SessionState state = WebUtil.getSessionState(request);
    //DisplayMode displayMode = state.getDisplayMode();
    //String forwardName = name + "." + displayMode.getName();
    String forwardName = name;
    return mapping.findForward(forwardName);
  }
}