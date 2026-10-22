package com.infowave.c4p.web.security;

import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.authenticator.FormAuthenticator;
import org.apache.catalina.*;
import org.apache.catalina.deploy.LoginConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.infowave.c4p.commons.ClientCapabilities;
/**
 * Performs Form based authentication if teh client supports it
 */
public class C4PFormAuthenticator extends FormAuthenticator
{
  /**
   * Descriptive information about this implementation.
   */
  protected static final String info =
          "com.infowave.c4p.web.security.C4PFormAuthenticator/1.0";


  // ------------------------------------------------------------- Properties


  /**
   * Return descriptive information about this Valve implementation.
   */
  public String getInfo() {

    return (this.info);

  }

  public void invoke(Request request, Response response,
                     ValveContext context)
          throws IOException, ServletException
  {
    // If this is not an HTTP request, do nothing
    if (!(request instanceof HttpRequest) ||
            !(response instanceof HttpResponse)) {
      context.invokeNext(request, response);
      return;
    }
    if (!(request.getRequest() instanceof HttpServletRequest) ||
            !(response.getResponse() instanceof HttpServletResponse)) {
      context.invokeNext(request, response);
      return;
    }

    HttpServletRequest hrequest = (HttpServletRequest) request;
    HttpSession session = hrequest.getSession(true);
    ClientCapabilities capabilities = new ClientCapabilities(hrequest);
    boolean doFormAuth = false;

    if (capabilities.canDoFormAuthentication() &&
            (session.getAttribute("C4P_FORCE_BASIC_AUTH") == null)) {
      doFormAuth = true;
    }

    if (doFormAuth){
      //System.out.println("must do form");
      super.invoke(request,response,context);
   } else {
      context.invokeNext(request, response);
   }
  }


}
