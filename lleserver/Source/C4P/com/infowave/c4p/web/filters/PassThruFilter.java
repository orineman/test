package com.infowave.c4p.web.filters;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.httpclient.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.codec.net.QCodec;
import org.apache.commons.codec.EncoderException;


import com.infowave.c4p.web.util.*;
import com.infowave.c4p.service.dto.DesktopDTO;

/**
 * This filter is a passthru:
 * It intercepts requests that start with a certain prefix, removes the prefix,
 * submits the request to the ICM, and sends the result back to the client.
 *
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */

public class PassThruFilter implements Filter

 {
 /** The class's logger */
  private static Log m_log = LogFactory.getLog(PassThruFilter.class);

  private ServletContext ctx;
  private FilterConfig config = null;

  private static final String PASSTHRU_PREFIX = "x-passthru";

  public void init(FilterConfig filterConfig) throws ServletException
  {
    this.config = filterConfig;
  }
  public void destroy()
  {
  }

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
  {
        QCodec qc = new QCodec();
    boolean stopFilterChain = false;

    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      HttpServletResponse response = (HttpServletResponse) servletResponse;

     if (isFileRequest(request)) {
       stopFilterChain = true;

       SessionState state = WebUtil.getSessionState(request);

       // get the  desktop
       // Make sure it's the active desktop.
       String did = request.getParameter(WebConstants.DID);
       String referrer = request.getHeader("referer");
       String useragent = request.getHeader("User-Agent");
       m_log.debug("JAE user-agent = " +useragent );
       if (did == null) {
         m_log.warn("Not did passed in request" + " - prefix: " + getPrefix());
         if (ctx == null) { ctx = config.getServletContext();}
         RequestDispatcher ds =
                 ctx.getRequestDispatcher("showGeneralErrorPage.do");
         ds.forward(servletRequest,
                    servletResponse);
         return;
       }
       DesktopDTO desktop;
       desktop = state.getCurrentDesktop();
       if (!desktop.getDid().equals(did)) {
         m_log.warn("The did passed in the request (" + did + ") does not" +
                    "correspond to the current did (" + desktop.getDid() + ")"
                     + " - prefix: " + getPrefix());
         if (ctx == null) { ctx = config.getServletContext();}
         RequestDispatcher ds =
                 ctx.getRequestDispatcher("showGeneralErrorPage.do");
         ds.forward(servletRequest,
                    servletResponse);
         return;

       }
       HttpMethod method = null;
       InputStream is = null;
       OutputStream os = null;
       try {
         // Build the download url
         StringBuffer  downloadUrl = new StringBuffer();
         downloadUrl.append(desktop.getIcmURL());
         //if (!desktop.getIcmURL().endsWith("/")) {
         //  downloadUrl.append("/");
         // }

         downloadUrl.append(getFileUri(request));

         RequestRedirector redirector;
         Map headers = DesktopUtils.getDownlofFileRequestHeader(request,desktop);
         String softwareVersion = null;
         if( desktop != null ) softwareVersion = desktop.getSoftwareVersion();
         redirector = new RequestRedirector(request,downloadUrl.toString(),headers,softwareVersion);
         method = redirector.getMethod();
		 if( referrer == null || referrer.length() == 0 ) {
			referrer = request.getScheme();
			referrer += "://";
			referrer += request.getServerName();
			referrer += "/index.jsp";
			referrer += request.getQueryString();
		 }
         m_log.debug("referer: " + referrer);
         method.setRequestHeader("Referer", referrer);
         method.setRequestHeader("User-Agent", useragent);
         m_log.debug("method: " + method.getClass() + " - " + method.getURI()
                     + " - prefix: " + getPrefix());


         Header  contentType;
         HttpClient client = new HttpClient();
         int statusCode;
         // Fetch the url from the ICM
         statusCode = client.executeMethod(method);
         m_log.debug("status code: " + statusCode  + " - prefix: " + getPrefix());
         // We send the content to the browser, we also copy the headers we
         // received from the ICM.
         Header[] responseHeaders = method.getResponseHeaders();
         if (responseHeaders != null){
           for (int i=0;i<responseHeaders.length;i++){
             String jtemp = responseHeaders[i].getName().toLowerCase();
             if( jtemp.indexOf("content-disposition" ) != -1 ) {
		String jtempv = responseHeaders[i].getValue();
		String jtempv2;
		int idx = jtempv.indexOf( "filename=" );
		if( idx != -1 && desktop.getSoftwareVersion() != null ) {
		 	jtempv2 = jtempv.substring( 0, idx + 10 );
			jtempv = jtempv.substring( idx + 10, jtempv.length() - 1 );
			jtempv = jtempv2 + qc.decode(jtempv);
                        }
             	response.setHeader(responseHeaders[i].getName(),
                                jtempv);
	 } else {
             	response.setHeader(responseHeaders[i].getName(),
                                responseHeaders[i].getValue());
             }
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
    return PASSTHRU_PREFIX;
  }
// -------------------------------------------------------------- Helper methods
  /** Determaine whether this request is for a static resoource.
   *
   * @param request
   * @return
   */
  public boolean isFileRequest(HttpServletRequest request)
  {
    boolean isFileRequest = false;
    String uri = request.getRequestURI();
    if (uri.indexOf(getPrefix()) != -1){
      isFileRequest = true;
    }
    return isFileRequest;
  }

  /**
   * Extracts the requested file uri from a request.
   *
   * @param request
   * @return
   */
  private  String getFileUri(HttpServletRequest request)
  throws Exception
  {
    URI uri = new URI (request.getRequestURI());
    String escapedURI = uri.getEscapedURI();

    try {
      escapedURI = escapedURI.substring(escapedURI.indexOf(getPrefix()) +
                          getPrefix().length());
    }
    catch(Exception e) {
      throw new IllegalArgumentException("Not a file request uri: " + uri);
    }
    return escapedURI;
  }





}
