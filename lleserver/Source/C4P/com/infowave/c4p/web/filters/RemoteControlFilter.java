package com.infowave.c4p.web.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpRecoverableException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.Header;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Map;

import com.infowave.c4p.web.util.RequestRedirector;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.DesktopUtils;
import com.infowave.c4p.util.ApplicationParameters;
import com.infowave.c4p.service.dto.UserDTO;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * This filters substitutes the incoming URI with the content of the
 * application's remoteControlUrl parameter.
 * Example: if the RC url is https://rc.lapalink.com and the incoming url
 *          is:
 *              http://www.lapalink.com/x_rcicm/a/b/c.htm?didi=qwqwqw
 *          We generate:
 *               https://rc.lapalink.com/wy/a/b/c.htm?didi=qwqwqw
 */
public class RemoteControlFilter implements Filter
 {
 /** The class's logger */
  private static Log m_log = LogFactory.getLog(RemoteControlFilter.class);

  private ServletContext ctx;
  private FilterConfig config = null;

  private static final String RC_PREFIX = "x-rcicm";

  public void init(FilterConfig filterConfig) throws ServletException
  {
    this.config = filterConfig;
  }
  public void destroy()
  {
  }

  public void doFilter(ServletRequest servletRequest,
                        ServletResponse servletResponse,
                        FilterChain filterChain)
          throws IOException,ServletException
  {
    boolean stopFilterChain = false;

    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      HttpServletResponse response = (HttpServletResponse) servletResponse;

     if (isRemoteControlRequest(request)) {
       stopFilterChain = true;

       HttpMethod method = null;
       InputStream is = null;
       OutputStream os = null;
       try {
         // Build the redirected url
         StringBuffer  downloadUrl = new StringBuffer();
         downloadUrl.append(ApplicationParameters.instance().getRemoteControlUrl());
         downloadUrl.append(getRemoteControlUri(request));

         RequestRedirector redirector;
         Map headers = null;
         // If we have a user logged in, we send the usual headers
         SessionState state = WebUtil.getSessionState(request);
         if (state.getUserDTO() != null) {
           headers = DesktopUtils.getApplicationRequestHeader(request,null);
         }
         redirector = new RequestRedirector(request,downloadUrl.toString(),headers);
         method = redirector.getMethod();
         m_log.debug("method: " + method.getClass() + " - " + method.getURI()
                     + " - prefix: " + getPrefix());


         Header  contentType;
         HttpClient client = new HttpClient();
         int statusCode;
         // Fetch the url
         statusCode = client.executeMethod(method);
         m_log.debug("status code: " + statusCode  + " - prefix: " + getPrefix());
         // We send the content to the browser, we also copy the headers we
         // received from the ICM.
         Header[] responseHeaders = method.getResponseHeaders();
         if (responseHeaders != null){
           for (int i=0;i<responseHeaders.length;i++){
             response.setHeader(responseHeaders[i].getName(),
                                responseHeaders[i].getValue());
           }
         }
         is = method.getResponseBodyAsStream();
         byte[] buff = new byte[1024];
         int bytesdRead;
         os = response.getOutputStream();
         while ((bytesdRead = is.read(buff)) != -1) {
           os.write(buff,0,bytesdRead);
         }
         os.close();os = null;
         is.close();is = null;
         response.flushBuffer();
       }
       catch (HttpRecoverableException e) {
         m_log.debug("A recoverable exception occurred. " + e.getMessage()  +
                     " - prefix: " + getPrefix());
       } catch (Exception e) {
         m_log.debug("Failed to fetch url."  + " - prefix: " + getPrefix(), e);
       } finally {
         // Release the connection, close the streams
         if (method != null) {method.releaseConnection();}
         if (os != null) {try {os.close();} catch(Exception e){}}
         if (is != null) {try {is.close();} catch(Exception e){}}
       }
     }
    }
    if (!stopFilterChain){
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  /**
   * This method returns the prefix this filter uses.
   * @return
   */
  protected String getPrefix()
  {
    return RC_PREFIX;
  }
// -------------------------------------------------------------- Helper methods
  /** Determaine whether this request is for a static resoource.
   *
   * @param request
   * @return
   */
  public boolean isRemoteControlRequest(HttpServletRequest request)
  {
    boolean isRemoteControlRequest = false;
    String uri = request.getRequestURI();
    if (uri.indexOf(getPrefix()) != -1){
      isRemoteControlRequest = true;
    }
    return isRemoteControlRequest;
  }

  /**
   * Extracts the requested file uri from a request.
   *
   * @param request
   * @return
   */
  private  String getRemoteControlUri(HttpServletRequest request)
  throws Exception
  {
    URI uri = new URI (request.getRequestURI());
    String escapedURI = uri.getEscapedURI();

    // Take everything after the prefix
    try {
      escapedURI = escapedURI.substring(escapedURI.indexOf(getPrefix()) +
                                        getPrefix().length());
    }
    catch(Exception e) {
      throw new IllegalArgumentException("Not a rc  request uri: " + uri);
    }
    return escapedURI;
  }

}
