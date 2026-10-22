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


}
