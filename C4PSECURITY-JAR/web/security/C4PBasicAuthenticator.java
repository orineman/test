package com.infowave.c4p.web.security;
import com.infowave.c4p.commons.ClientCapabilities;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * This authenticator does nothing but call its base class if the client can
 * only accept Basic Authentication.
 */

public class C4PBasicAuthenticator extends BasicAuthenticator
{
  /**
   * Descriptive information about this implementation.
   */
  protected static final String info =
          "com.infowave.c4p.web.security.C4PBasicAuthenticator/1.0";


  // ------------------------------------------------------------- Properties


  /**
   * Return descriptive information about this Valve implementation.
   */
  public String getInfo() {

    return (this.info);

  }
  /**
   * Calls BasicAuthenticator.invoke if the client cannot use Form authenticaion.
   *
   * @param request
   * @param response
   * @throws IOException
   * @throws ServletException
   */
  public void invoke(Request request, Response response)
          throws IOException, ServletException
  {
    request.setCharacterEncoding("UTF-8");
    // If this is not an HTTP request, do nothing
    if (!(request.getRequest() instanceof HttpServletRequest) ||
            !(response.getResponse() instanceof HttpServletResponse)) {
      getNext().invoke(request, response);
      return;
    }
    HttpServletRequest hrequest = (HttpServletRequest) request;

    // If this client must do basic auth, we call this valves; otherwise, we
    // execute the next valve.
    boolean doBasicAuth = false;
    HttpSession session = hrequest.getSession();
    ClientCapabilities capabilities = new ClientCapabilities(hrequest);
    if (!capabilities.canDoFormAuthentication() ||
            (session.getAttribute("C4P_FORCE_BASIC_AUTH") != null)){
      doBasicAuth = true;
    }

    if (doBasicAuth){
      //System.out.println("must do basic");
      //log("Invoking BasicAuthenticator.invoke");
      super.invoke(request,response);
    } else {
      getNext().invoke(request, response);
    }
  }


}
