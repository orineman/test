package com.infowave.c4p.web.filters;

import com.infowave.c4p.web.config.Configurator;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.SessionTerminationNotifier;
import com.infowave.c4p.service.dto.ResellerDTO;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.commons.DisplayMode;

import javax.servlet.*;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * This filter is responsible for  identifying to which reseller the session
 * belongs.
 */
public class SessionConfigurationFilter implements Filter
 {
  private ServletContext ctx;
  private FilterConfig config = null;

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(SessionConfigurationFilter.class);

  public void init(FilterConfig filterConfig) throws ServletException
  {
    this.config = filterConfig;
  }

  public void destroy() {
  }

/**
 * Implements the filter.
 *
 * @param servletRequest
 * @param servletResponse
 * @param filterChain
 * @throws IOException
 * @throws ServletException
 */
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain)
          throws IOException, ServletException

  {
    if (servletRequest instanceof HttpServletRequest) { 
      HttpServletRequest request = (HttpServletRequest)servletRequest;
      HttpServletResponse response = (HttpServletResponse)servletResponse;
      HttpSession session = request.getSession();
      response.setHeader("sessionId",session.getId());

      // log some debugging information about the request
      m_log.debug("requested id: " + request.getRequestedSessionId() +
                  " - session id: " + (request.getSession()== null? "null":request.getSession().getId()) +
                  " - Authorization header: " + request.getHeader("Authorization") +
                  " - User Principal: " + request.getUserPrincipal() +
                  " - Cookie: " + request.getHeader("Cookie"));

      if ( session != null && session.isNew()) {
        m_log.debug("New  session: " + session.getId());
      }

      SessionState state = WebUtil.getSessionState(request);

      if (request.getSession() != null)
        m_log.debug(request.getSession().getId() + " " + request.getRequestURL() +
                    " " + Config.get(request.getSession(),Config.FMT_LOCALE) + " "
        + request.getHeader("user-agent"));
      // Create the session's state if if does not exist already
      if (state == null) {
        m_log.debug("creating the state "  + request.getSession().getId() + " " + request.getRequestURL());
        state = new SessionState(request);
        WebUtil.setSessionState(request,state);
      }

      // If not done yet, find the reseller
      if (state.getReseller() == null) {
        if (!state.setReseller(request)) {
          m_log.error("Cannot set the reseller");
          throw new ServletException("Cannot find a reseller for " +
                                     request.getRequestURL());
        } else {
          // We set the passwrod encoder's class name in the sesion. The
          // scurity realm needs this information to encode the password
          request.getSession().setAttribute("c4p_passwordEncoder",
                                            state.getReseller().getPasswordEncoder());
          m_log.debug("setting reseller: " + state.getReseller().getName());
        }
      } else {
        //m_log.debug("res: " + state.getReseller().getName());
      }

      // If we have a principal in the request but no userDTO in the session, it
      // means that it's the first request we execute after the authentication
      // phase. We must initialize the session's state.
      if ((request.getUserPrincipal() != null) && (state.getUserDTO() == null)){
        try {
          state.refresh(request);
        }
        catch(ServiceException e){
          m_log.error("Couldn't get the session's state",e);
          throw new ServletException("Couldn't get the session's state",e);
        }
      }

      // We allow two resellers to have accounts with the same username. We
      // can't enforce this at login time because we're using container-managed
      // authentication and the Tomcat realms don't have access to the request.
      // So, we make sure here that the user's reseller matches the reseller
      // corresponding to this hostname
      if ((state.getUserDTO() != null)){
        String userReseller = state.getUserDTO().getResellerName();
        String resellerName = state.getReseller().getName();
       if (!userReseller.equals(resellerName)) {
         m_log.debug("Wrong reseller (" + userReseller + ") - should be: " +
                     resellerName + " logging out");
         session.invalidate();
         response.sendRedirect("/showLoginPage.do"+ "?" +
                          WebConstants.ACTION + "=" + "error" + "&" +
                          WebConstants.LOCALE + "=" + state.getLocale());
         response.flushBuffer();
         return;
       }
      }

      // If not done yet, determine the display modes
      if (state.getClientCapabilities() == null) {
        state.setClientCapabilities(request);
      }

      // Locale - if we don't have a locale yet, find a temporary one.
      if (state.getLocale() == null){
      state.setTemporaryLocale(request);
      }


    }

    // Contine with the pipeline
    filterChain.doFilter(servletRequest,servletResponse);
  }

}
