package com.infowave.c4p.web.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.infowave.c4p.web.util.GZIPResponseWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GZIPFilter implements Filter {
    ServletContext sc;
    FilterConfig fc;

    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(GZIPFilter.class);

    String matchString = "/ui/.*?\\.(gif|jpe?g|png|js|html)$";
    String ignoreMatchString = "*.do";

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    if (req instanceof HttpServletRequest) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String ae = request.getHeader("accept-encoding");
        // This is for pathing throw requests that are not for protocol
        String pp = request.getHeader("protocolPath");
        if (ae!=null && ae.indexOf("gzip")!=-1 &&
        (pp==null || request.getRequestURI().matches(matchString)) && !request.getRequestURI().matches(ignoreMatchString)) {
        GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
        try {
            chain.doFilter(req, wrappedResponse);
        }
        catch (ServletException ex) {throw ex;}
        catch (IOException ex) {throw ex;}
        catch (Exception ex) {throw new ServletException(ex);}
        finally {
            wrappedResponse.finishResponse();
        }
        }
        else chain.doFilter(req, res);
    }
    }

  public void init(FilterConfig filterConfig) {
    this.fc = filterConfig;
    this.sc = filterConfig.getServletContext();
    //regex for matching requestURI
    String m = fc.getInitParameter("regex");
    if ( m != null ) {
        matchString = m;
    }
    String im = fc.getInitParameter("ignoreRegex");
    if ( im != null ) {
        ignoreMatchString = im;
    }
  }

  public void destroy() {
  }
}
