package com.infowave.c4p.web.security;

import com.infowave.c4p.commons.ClientCapabilities;
import org.apache.catalina.authenticator.FormAuthenticator;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;

/**
 * Performs Form based authentication if the client supports it
 */
public class C4PFormAuthenticator extends FormAuthenticator {
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

    public void invoke(Request request, Response response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        if (!(request.getRequest() instanceof HttpServletRequest) ||
                !(response.getResponse() instanceof HttpServletResponse)) {
            getNext().invoke(request, response);
            return;
        }
        RememberMe rem = new RememberMe();
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpSession session = hrequest.getSession(true);
        if (hrequest.getParameter("rememberMe") != null && hrequest.getParameter("rememberMe").equalsIgnoreCase("on")) {

            if (hrequest.getParameter("j_username") != null) {
                if (hrequest.getParameter("j_password") != null) {
                    try {
                        rem.rememberMe(hrequest, response, hrequest.getParameter("j_username"), (hrequest.getParameter("j_password")));
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (BadPaddingException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        } else {
            if (hrequest.getParameter("j_username") != null) {
                if (hrequest.getParameter("j_password") != null) {
                    Cookie[] cookies = request.getCookies();
                    for (Cookie c: cookies) {
                        if (c.getName().equals(RememberMe.USERNAME_COOKIE)||c.getName().equals(RememberMe.PASSWORD_COOKIE)) {
                            c.setMaxAge(0);
                            response.addCookie(c);
                        }
                    }
                }
            }
        }

        if (hrequest.getParameter("j_version")!=null) {
            session.setAttribute("j_version",hrequest.getParameter("j_version"));
        }

        ClientCapabilities capabilities = new ClientCapabilities(hrequest);
        boolean doFormAuth = false;

        if (capabilities.canDoFormAuthentication() &&
                (session.getAttribute("C4P_FORCE_BASIC_AUTH") == null)) {
            doFormAuth = true;
        }

        if (doFormAuth) {
            //System.out.println("must do form");
            super.invoke(request, response);
        } else {
            getNext().invoke(request, response);
        }
    }

}
