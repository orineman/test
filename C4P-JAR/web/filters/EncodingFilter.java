package com.infowave.c4p.web.filters;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 06.02.2008
 * Time: 15:02:07
 * Paccage : com.infowave.c4p.web.filters
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
 /** The class's logger */
  private static Log m_log = LogFactory.getLog(DownloadFilter.class);
  private String encoding = "UTF-8";

  public void doFilter(ServletRequest request,
      ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    if (request.getCharacterEncoding()!=null && !request.getCharacterEncoding().equals("UTF-8"))   {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=UTF-8");
    }
    filterChain.doFilter(request, response);
  }

  public void init(FilterConfig filterConfig)
                   throws ServletException {
/*    String encodingParam = filterConfig
              .getInitParameter("encoding");
    if (encodingParam != null) {
      encoding = encodingParam;
    }*/
  }

  public void destroy() {
    // nothing todo
  }
}
