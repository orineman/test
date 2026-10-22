package com.infowave.c4p.web.filters;

import com.infowave.c4p.web.util.*;
import com.infowave.c4p.service.dto.DesktopDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Enumeration;
import java.net.URLEncoder;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */

/**
 * This class is responsible for redirecting urls to the ICM. The url generated
 * by the desktop are relative the this web application.
 *
 */
public class ICMRedirector_bak implements Filter
 {

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ICMRedirector_bak.class);

  private FilterConfig config = null;
  private ServletContext ctx;
 // --------------------------------------------------------- Life cycle methods
  public void init(FilterConfig filterConfig) throws ServletException
  {
    this.config = filterConfig;
    //ctx = config.getServletContext();
  }
  public void destroy()
  {
    config = null;
  }

  /**
   * The actual filter.
   *
   * @param servletRequest
   * @param servletResponse
   * @param filterChain
   * @throws IOException
   * @throws ServletException
   */
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException
  {
    String url = null;
    String desktopUrl = null;

    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      //System.out.println(ReqUtils.dumpSessionAttributes(request));
      m_log.debug("--------> " + request.getMethod());
      m_log.debug("url = " + request.getRequestURL() +
                  " uri = " + request.getRequestURI());
      DesktopDTO desktop = null;
      SessionState state = WebUtil.getSessionState(request);
      if (state != null) {
        desktop = state.getCurrentDesktop();
      }
      //if (desktop == null) {m_log.debug("no desktop " + request.getRequestURI());}
      if (desktop != null) {
        // We have a desktop selected. We now check whether the url we're
        // trying to access is a Files url for that desktop.
        url = ReqUtils.getURIWithParameters(request);
        desktopUrl = DesktopUtils.getAppUrl(desktop,url);
        }
      }
    // If we found a valid application url, we redirect to user; otherwise, we
    // continue the filters chain.
    if (desktopUrl != null) {

      m_log.debug("URL: " + url + " is a desktop application url: " + desktopUrl);
      StringBuffer redirectTo = new StringBuffer();
      redirectTo.append("/protected/showDesktopApplication.do");
      redirectTo.append("?").append(WebConstants.URL).append("=");
      //redirectTo.append(desktopUrl);
      // todo use the new encode method
      redirectTo.append(URLEncoder.encode(desktopUrl));
      if (ctx == null) {
        ctx = config.getServletContext();
      }
      RequestDispatcher ds =
              ctx.getRequestDispatcher(redirectTo.toString());
      ds.forward(servletRequest,
                 servletResponse);
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }
}
