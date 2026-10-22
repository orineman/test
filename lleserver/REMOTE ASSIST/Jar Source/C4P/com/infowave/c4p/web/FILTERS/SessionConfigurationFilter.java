package com.infowave.c4p.web.filters;

//import com.infowave.c4p.web.config.Configurator;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.SessionTerminationNotifier;
import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.service.ServiceException;
//import com.infowave.c4p.commons.DisplayMode;

import javax.servlet.*;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/* This filter is responsible for  identifying to which company the session
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

      // If we have a principal in the request but no technicianDTO in the session, it
      // means that it's the first request we execute after the authentication
      // phase. We must initialize the session's state.
      if ((request.getUserPrincipal() != null) && (state.getTechnicianDTO() == null)){
        try {
          state.refresh(request);
        }
        catch(ServiceException e){
          m_log.error("Couldn't get the session's state",e);
          throw new ServletException("Couldn't get the session's state",e);
        }
      }

      // Locale - if we don't have a locale yet, find a temporary one.
      if (state.getLocale() == null){
		state.setTemporaryLocale(request);
      }


    }

	m_log.debug("before filterChain.doFilter");
    // Contine with the pipeline
    filterChain.doFilter(servletRequest,servletResponse);
  }

}
