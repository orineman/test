package com.infowave.c4p.web.filters;

import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.commons.DisplayMode;

import javax.servlet.*;
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
public class SearchFilter implements Filter
 {
  private ServletContext ctx;
  private FilterConfig config = null;

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(SearchFilter.class);

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
      String uri = request.getRequestURI();
      String query = request.getQueryString();
      if( uri.indexOf("/llsearch/index.jsp") != -1 && query .indexOf("btnl_web_search=") != -1 ) {
         String searchParams = "";
         int idx = query .indexOf("q=");
         if( idx != -1 ) {
             int idx2 = query.indexOf( "&", idx );
             if( idx2 != -1 ) {
                 searchParams = query.substring( idx, idx2 );
             } else {
                 searchParams = query.substring( idx );
             }
         }
         response.sendRedirect("http://www.google.com/search"+ "?" +searchParams );
         response.flushBuffer();
         return;

      } else {

        // Contine with the pipeline
        filterChain.doFilter(servletRequest,servletResponse);
      }
  }
}

}
