package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.DesktopUtils;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.service.dto.DesktopDTO;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ShowDesktopApplication extends UserAction
 {
    /** The class's logger */
  private static Log m_log = LogFactory.getLog(ShowDesktopApplication.class);

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
    HttpSession session = request.getSession();

    // The ApplicationFilter filter built the HttpMethod object and stored it in
    // the request. So, we just need to forward the request to the appropriate
    // page

    return null;
  }



  public ActionForward executeLogic_____(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    HttpSession session = request.getSession();
    String did = request.getParameter(WebConstants.DID);

    if (did == null) {
      m_log.debug("no did in the request");
      return  mapping.findForward("input");
    }

    /*
    byte[] html = (byte[])session.getAttribute(WebConstants.HTML);
    if (html == null){
      m_log.error("html is null"); // todo remove
    }
    session.removeAttribute(WebConstants.HTML);
    request.setAttribute(WebConstants.HTML,html);
    */

    //  We must make sure the this url is for the
    // cuurently selected desktop and that this desktop is not disconnected.
    m_log.debug("The did: " + did);

    SessionState state = WebUtil.getSessionState(request);
    state.refresh(request);
    DesktopDTO desktop = state.getCurrentDesktop();
    if (desktop == null) {
      m_log.info("No active desktop - ignoring ");
      return  mapping.findForward("input");
    }

    if (!did.equals(desktop.getDid())) {
      m_log.info("The did in the app url (" + did + ") does not correpond " +
                 "to the current desktop's did (" + desktop.getDid() + ")" +
                 " - ignoring");
      return  mapping.findForward("input");
    }

    if (!desktop.isConnected()) {
      m_log.info("This desktop (" + desktop.getDid() + ") is not connected " +
                 " - ignoring");
      return  mapping.findForward("input");

    }

    // Everything is fine


    return null;
  }



  public ActionForward executeLogic_______________(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    String desktopUrl = null;
    desktopUrl=request.getParameter(WebConstants.URL);

    // if we don't find a urk it's probably because the user tinkered with the
    // url. We simply log a debug message and redisplay then man page.
    if (desktopUrl == null) {
      m_log.debug("no url in the request");
      return  mapping.findForward("input");
    }

    // We have an application url. We must make sure the this url is for the
    // cuurently selected desktop and that this desktop is not disconnected.
    String did = null;
    did = DesktopUtils.getDidParameter(desktopUrl);
    m_log.debug("The did: " + did);
    if (did == null) {
      m_log.info("The application url does not contain a did parameter "
                 + "- ignoring: " + desktopUrl);
      return  mapping.findForward("input");
    }

    SessionState state = WebUtil.getSessionState(request);
    state.refresh(request);
    DesktopDTO desktop = state.getCurrentDesktop();
    if (desktop == null) {
      m_log.info("No active desktop "
                 + "- ignoring: " + desktopUrl);
      return  mapping.findForward("input");
    }

    if (!did.equals(desktop.getDid())) {
      m_log.info("The did in this url does not correpond to the " +
                 "current desktop's did (" + desktop.getDid() + ")" +
                 " ignoring: " + desktopUrl);
      return  mapping.findForward("input");
    }

    if (!desktop.isConnected()) {
      m_log.info("This desktop (" + desktop.getDid() + ") is not connected " +
                 " ignoring: " + desktopUrl);
      return  mapping.findForward("input");

    }

    // Everything is fine, we store the url to retrieve in the session
    WebUtil.setDesktopUrl(request,desktopUrl);

    return null;
  }

}
