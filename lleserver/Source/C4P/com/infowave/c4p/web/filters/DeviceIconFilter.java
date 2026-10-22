package com.infowave.c4p.web.filters;

import com.infowave.c4p.web.util.ResourceManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class DeviceIconFilter implements Filter
 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(DeviceIconFilter.class);

  private FilterConfig m_config = null;

  public void init(FilterConfig config) throws ServletException
  {
    m_config = config;
  }



  public void destroy()
  {
    m_config = null;
  }


  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain)
            throws IOException, ServletException
    {

    boolean stopHere = false;
    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      //m_log.debug(" req: " + request.getRequestURI());
      if (ResourceManager.isDeviceIconRequest(request)){
        stopHere = true;
        String uri = ResourceManager.getDeviceIconURI(request);
        ServletContext ctx = request.getSession().getServletContext();
        ctx.getRequestDispatcher(uri).forward(request,response);
      }
    }
    if (!stopHere) {
      filterChain.doFilter(servletRequest,servletResponse);
    }
  }

}
