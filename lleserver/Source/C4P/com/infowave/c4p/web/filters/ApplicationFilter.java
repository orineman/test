package com.infowave.c4p.web.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Enumeration;

import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.web.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Nov 30, 2003
 * Time: 9:15:09 PM
 * To change this template use Options | File Templates.
 */
public class ApplicationFilter implements Filter

{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ApplicationFilter.class);

  private ServletContext ctx;
  private FilterConfig config = null;

  private static final String FILE_PREFIX = "x-file";

  public void init(FilterConfig filterConfig) throws ServletException
  {
    this.config = filterConfig;
  }
  public void destroy()
  {
  }

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
  {
      boolean stopFilterChain = false;
      String softwareVersion = null;

    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      HttpSession session = request.getSession();

      String desktopUrl = null;
      DesktopDTO desktop = null;
      SessionState state = WebUtil.getSessionState(request);
      // If we don't have a current desktop, it's certainly not a desktop url
      String requestUri = request.getRequestURI().toLowerCase();
      // If this is an rdp request we do not want any desktop
      if( isAccountPageLink(requestUri) || isLogoutLink(requestUri) ) {
            DesktopDTO dto = null;
	if( state!= null ) state.setCurrentDesktop(dto );
      }
      if ( isNewDesktopLink(requestUri ) ) {

      	String requestQuery = request.getQueryString();
            int idx = -1;
            if(requestQuery != null ) {
		idx = requestQuery.indexOf("did=");
	}
      	if( idx != -1 ) {
            	String requestDid = null;
           		 idx += 4;
           		 int idx2 = requestQuery.indexOf( "&", idx);
            	if( idx2 == -1 ) {
                		requestDid = requestQuery.substring( idx );
		} else {
               		 requestDid = requestQuery.substring( idx, idx2 );
		}
           		 if( state != null ) {
                		desktop = state.getDesktop(requestDid );
           		 }
     	 } 
     }else {
          	if (state != null && !isRdpLink(requestUri ) ) {
             	desktop = state.getCurrentDesktop();
          	}
      }
      if (desktop != null) {
        // We have a desktop selected. We now check whether the url we're
        // trying to access is a Files url for that desktop.
        softwareVersion = desktop.getSoftwareVersion();

        m_log.debug("query string: " + request.getQueryString());
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()){
          String param = (String) e.nextElement();
          m_log.debug(param + "=" + request.getParameter(param));
        }
        URI uri = new URI(request.getRequestURI());
        String escapedURI = uri.getEscapedURI();

        m_log.debug("got URI: " + request.getRequestURI() + " - escaped: " + escapedURI + " - translated: " + request.getPathTranslated());
        desktopUrl = DesktopUtils.getAppUrl(desktop,escapedURI);
        //desktopUrl = DesktopUtils.getAppUrl(desktop,request.getRequestURI());
      }
      if (desktopUrl != null) {
        stopFilterChain = true;

        String did = request.getParameter(WebConstants.DID);

       if (did == null){
         m_log.warn("no did in the request: ");
         RequestDispatcher ds =
                 ctx.getRequestDispatcher(WebConstants.ACTION_SHOW_ERROR_PAGE);
         ds.forward(servletRequest,
                    servletResponse);
         return;
       }
        // Build the http method to retrieve the content of this page and
        //store it in the request.
        Map headers = DesktopUtils.getApplicationRequestHeader(request,desktop);
        RequestRedirector redirector;
        redirector = new RequestRedirector(request,desktopUrl,headers,softwareVersion);
        //HttpMethod method = redirector.getMethod();
        //request.setAttribute(WebConstants.METHOD,method);
        request.setAttribute(WebConstants.METHOD,redirector);

        // Forward to the showApp action
        String appUrl = WebConstants.ACTION_SHOW_DESKTOP_APP + "?"
                + WebConstants.DID + "=" + did;
        m_log.debug("dispatching to: " + appUrl);
        if (ctx == null) {ctx = config.getServletContext();}
        RequestDispatcher ds =
                ctx.getRequestDispatcher(appUrl);
        ds.forward(servletRequest,
                   servletResponse);
      }
    }
    // Continue with the filter
   if (!stopFilterChain) {
      filterChain.doFilter(servletRequest,servletResponse);
    }
  }


  private boolean isLogoutLink( String requestUriLowercase ) {
	boolean isLink = false;
	if(requestUriLowercase.equals( "/logout.do")   ) {
		isLink = true;
 	}

	return isLink;
  }

  private boolean isAccountPageLink( String requestUriLowercase ) {
	boolean isLink = false;
	if(requestUriLowercase.equals( "/protected/showaccountmanagementpage.do")   ) {
		isLink = true;
 	}

	return isLink;
  }

  private boolean isRdpLink( String requestUriLowercase ) {
	boolean isLink = false;
	if(requestUriLowercase.equals( "/protected/showrdpservicepage.do")  ||
               requestUriLowercase.equals( "/protected/vncservicepage.do")  ||
               requestUriLowercase.equals( "/protected/winshadowservicepage.do")  ||
               requestUriLowercase.equals( "/protected/rdpservicepage.do") 
	 ) {
		isLink = true;
 	}

	return isLink;
  }


  private boolean isNewDesktopLink( String requestUriLowercase ) {
	boolean isLink = false;
	if(requestUriLowercase.equals( "/protected/showdesktoppage.do")   ) {
		isLink = true;
 	}

	return isLink;
  }


}
