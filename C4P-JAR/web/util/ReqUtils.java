package com.infowave.c4p.web.util;

import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.*;
import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Oct 18, 2003
 * Time: 9:25:42 PM
 * To change this template use Options | File Templates.
 */
public class ReqUtils
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ReqUtils.class);


  /**
   * Add the specified request parameters to a forward.
   * The parameters must exeist in the request.
   */
  public static ActionForward putBackParametersInRequest(ActionForward forward,
                                                         HttpServletRequest request,
                                                         String[] paramNames)
  {
    boolean first = true;
    StringBuffer buffer = new StringBuffer(forward.getPath());
    for (int i=0;i<paramNames.length;i++) {
      if (first) {
        buffer.append("?");
        first = false;
      } else {
        buffer.append("&");
      }
      String param = (String) request.getParameter(paramNames[i]);
      if (param != null) {
        buffer.append(paramNames[i]).append("=");
        try { buffer.append(URLEncoder.encode(param, "UTF-8")); } catch( UnsupportedEncodingException e) { buffer.append(URLEncoder.encode(param)); }
      } else {
        m_log.error("putBackParametersInRequest: could not find parameter:"+
                  paramNames[i]);
      }
    }
    return  new ActionForward(buffer.toString(),forward.getRedirect());
  }

  /**
   * Add the specified request parameter to a forward.
   * The parameters must exeist in the request.
   */
  public static ActionForward putBackParameterInRequest
          (ActionForward forward,
           HttpServletRequest request,
           String paramName)
  {
    String[] paramNames = new String[1];
    paramNames[0] = paramName;
    return putBackParametersInRequest(forward,request,paramNames);
  }

  /**
   * If the request contains pairs of parameters of the
   * form (paramName<i>, paramValue<i>), append them to the forward. The
   * resulting forward as the form:
   * <forward>&?<paramName1>=<paramValue1>&<paramName2>=<paramValue2>...etc.
   *
   */
  public static ActionForward putBackParameterNVPsInRequest
          (ActionForward forward,
           HttpServletRequest request)
  {
    // Find the parameters to put back
    Map params = new HashMap(3);
    for (int i=0;i<10;i++) {
      String paramName = request.getParameter("paramName" + i);
      String paramValue = request.getParameter("paramValue" + i);
      if ((paramName != null) && (paramValue != null)) {
        params.put(paramName,paramValue);
      }
    }
    // Put them back
    boolean first = true;
    String forwardPath = forward.getPath();
    StringBuffer buffer = new StringBuffer(forwardPath);
    // check whether the forward already contains parameters
    boolean forwardHasParameters = false;
    try {
    forwardHasParameters = (new URI(forwardPath).getQuery() != null);
    }
    catch(URIException e) {
      m_log.error ("Cannot create URI for forward: " + forwardPath);
    }

    for (Iterator it=params.keySet().iterator();it.hasNext();) {
      String paramName = (String) it.next();
      String paramValue = (String) params.get(paramName);
      if (first) {
        first = false;
        if (!forwardHasParameters){
          buffer.append("?");
        } else {
          buffer.append("&");
        }
      } else {
        buffer.append("&");
      }
      buffer.append(paramName).append("=");
      try { buffer.append(URLEncoder.encode(paramValue, "UTF-8")); } catch( UnsupportedEncodingException e) { buffer.append(URLEncoder.encode(paramValue)); }
    }
    return  new ActionForward(buffer.toString(),forward.getRedirect());


  }
  /**
   * Takes all the requests paramters and append them to the forward.
   */
  public static ActionForward putBackAllParametersInRequest
          (ActionForward forward,
           HttpServletRequest request)
  {
    List paramList = new ArrayList();
    for (Enumeration en = request.getParameterNames();en.hasMoreElements();) {
      paramList.add(en.nextElement());
    }
    String[] params = new String[paramList.size()] ;
    return putBackParametersInRequest(forward,request,
                                      (String[])paramList.toArray(params));
  }

  /**
   * Retrieves the URI from the request and appends the requests parameters.
   *
   * @param request
   * @return the URI with its parameters.
   */
  //todo I think we can deprecate this one
  public static String getURIWithParameters(HttpServletRequest request)
  {
    String uri = request.getRequestURI();
    Enumeration params = request.getParameterNames();
    boolean first = true;
    StringBuffer sb = new StringBuffer();
    sb.append(uri);
    while (params.hasMoreElements()){
      String param = (String) params.nextElement();
      String value = request.getParameter(param);
      if (first){
        sb.append("?");
        first = false;
      } else {
        sb.append("&");
      }
      sb.append(param).append("=").append(value);
    }
    return sb.toString();
  }

  public static String dumpRequest(HttpServletRequest request)
  {
    StringBuffer sb = new StringBuffer();
    // http://www.geo.unizh.ch:8080/wirz/servlet_source/SnoopServlet.java.html
    sb.append("Request attributes :\n");
    java.util.Enumeration e = request.getAttributeNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      Object value = request.getAttribute(key);
      sb.append("   " + key + " = " + value+ "\n");
    }
    sb.append("\n");
    sb.append("Request headers :");
    e = request.getHeaderNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String value = request.getHeader(key);
      sb.append("   " + key + ": " + value + "\n");
    }
    sb.append("\n");
    sb.append("Parameter names in this request:\n");
    e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] values = request.getParameterValues(key);
      sb.append("   " + key + " = ");
      for(int i = 0; i < values.length; i++) {
        sb.append(values[i] + " ");
      }
      sb.append("\n");
    }
    sb.append("\n");

    sb.append("Protocol: " + request.getProtocol().trim()+ "\n");
    sb.append("Scheme: " + request.getScheme()+ "\n");
    sb.append("Server Name: " + request.getServerName()+ "\n");
    sb.append("Server Port: " + request.getServerPort()+ "\n");

    sb.append("Remote Addr: " + request.getRemoteAddr()+ "\n");
    sb.append("Remote Host: " + request.getRemoteHost()+ "\n");
    sb.append("Character Encoding: " + request.getCharacterEncoding()+ "\n");
    sb.append("Content Length: " + request.getContentLength()+ "\n");
    sb.append("Content Type: "+ request.getContentType()+ "\n");
    sb.append("Locale: "+ request.getLocale()+ "\n");
    //sb.append("Default Response Buffer: "+ response.getBufferSize()+ "\n");
    sb.append("Request Is Secure: " + request.isSecure()+ "\n");
    sb.append("Auth Type: " + request.getAuthType()+ "\n");
    sb.append("HTTP Method: " + request.getMethod()+ "\n");
    sb.append("Remote User: " + request.getRemoteUser()+ "\n");
    sb.append("Request URI: " + request.getRequestURI()+ "\n");
    sb.append("Context Path: " + request.getContextPath()+ "\n");
    sb.append("Servlet Path: " + request.getServletPath()+ "\n");
    sb.append("Path Info: " + request.getPathInfo()+ "\n");
    sb.append("Path Trans: " + request.getPathTranslated()+ "\n");
    sb.append("Query String: " + request.getQueryString()+ "\n");

    return sb.toString();
  }
  public static String dumpSessionAttributes(HttpServletRequest request)
  {
    StringBuffer sb = new StringBuffer();
    HttpSession session = request.getSession();
    Enumeration attrs = session.getAttributeNames();
    while (attrs.hasMoreElements()){
      String attr = (String) attrs.nextElement();
      sb.append(attr).append(": ").append(session.getAttribute(attr)).append("\n");
    }
    return sb.toString();
  }

  // --------------------------------------------------------- Helper methods

  public static void main (String[] args)
  {
    try {
      String path= "/boo/bar?ppopo=popo";
       path= "/boo/bar.do";
      URI uri = new URI(path);
      System.out.println(uri.getQuery());
    }
    catch (Exception e) {e.printStackTrace();}
  }
}
