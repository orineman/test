package com.infowave.c4p.web.actions;


import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.infowave.c4p.web.util.*;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.DesktopUtils;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.service.dto.DesktopDTO;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ShowRDPApplication extends UserAction
 {
    /** The class's logger */
  private static Log m_log = LogFactory.getLog(ShowRDPApplication.class);

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
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    ActionForward forward = null;
    HttpSession session = request.getSession();
    RDPForm form  = (RDPForm)actionForm;
    SessionState state;

    List inputErrors = form.validateDid(mapping,request);
    if (!inputErrors.isEmpty()){
      errors.addAll(inputErrors);
      super.saveToken(request);
      forward = mapping.findForward("error.pc");
    } else {
      state = WebUtil.getSessionState(request);
      state.refresh(request);

      // Get the desktop. If we can't find it in our list, it's probably because
      // the user tinkered with the url or used the Back button.
      DesktopDTO desktop = state.getDesktop(form.getDid());
      if (desktop == null) {
         m_log.debug("the user " + state.getUserDTO().getUsername() +
                  "does not have access desktop " + form.getDid());
         return mapping.findForward("input");
      }

      // We change the state to reflect the new current desktop
      state.setCurrentDesktop(desktop);
      m_log.debug("set the current desktop to: " + desktop.getDid());

   HttpMethod  method;
   String appListUrl;
   Map headers;

   appListUrl = desktop.getAppListUrl(state.getDisplayMode());
   method = new GetMethod(appListUrl);
   headers = DesktopUtils.getApplicationRequestHeader(request,
                                                      state.getCurrentDesktop());
   for (Iterator it = headers.keySet().iterator();it.hasNext();){
     String headerName = (String) it.next();
     method.addRequestHeader(headerName,(String)headers.get(headerName));
   }

   RequestRedirector redirector = new RequestRedirector(appListUrl,headers);
   request.setAttribute(WebConstants.METHOD,redirector);


      request.setAttribute("desktopinfo", actionForm);
      forward = mapping.findForward("success.pc");
   }

    return forward;
  }


}
