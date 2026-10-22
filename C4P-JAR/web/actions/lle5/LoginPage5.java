package com.infowave.c4p.web.actions.lle5;

import com.infowave.c4p.commons.ClientCapabilities;
import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.WebUtil;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;

public class LoginPage5 extends BaseAction {
    public LoginPage5() {
    }

    public ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors)
            throws Exception {
        m_log.debug("LoginPage5. Welcome");
/*        <meta http-equiv="Cache-Control" content="no-store,no-cache,must-revalidate"/>
          <meta http-equiv="Pragma" content="no-cache"/>
          <meta http-equiv="Expires" content="-1"/>*/
        
        HttpSession session = request.getSession();

        ActionForward actionForward;
        String action = request.getParameter("action");
        if (action == null) {
            action = (String) session.getAttribute("action");
            session.removeAttribute("action");
        }
        SessionState state = WebUtil.getSessionState(request);
        ClientCapabilities clientCapabilities = null;
        
        if (state!=null) {
            clientCapabilities = state.getClientCapabilities();
            state.notInAnySection();
        }
        if (request.getUserPrincipal() == null && session.getAttribute("j_username") != null && session.getAttribute("j_security_check") != null) {

            HttpClient client = new HttpClient();
            client.getHostConfiguration().setHost(
                    request.getServerName(),
                    request.getServerPort(),
                    request.getScheme()
            );

            client.getState().setCookiePolicy(CookiePolicy.COMPATIBILITY);

            GetMethod httpget = new GetMethod(request.getContextPath() + "/protected/lle5.do");
            int result;
            client.executeMethod(httpget);
            Cookie[] cookies = client.getState().getCookies();
            m_log.debug("Present cookies: ");
            for (Cookie cooky1 : cookies) {
                m_log.debug(" - " + cooky1.toExternalForm());
            }
            httpget.releaseConnection();
            PostMethod authPost = new PostMethod(request.getContextPath() + session.getAttribute("j_security_check"));
            NameValuePair[] data = {
                    new NameValuePair("j_username", (String) session.getAttribute("j_username")),
                    new NameValuePair("j_password", (String) session.getAttribute("j_password"))
            };

            for (Cookie cooky : cookies) {
                authPost.setRequestHeader("Cookie",
                        cooky.toExternalForm());
            }
            authPost.setRequestBody(data);
            result = client.executeMethod(authPost);
            authPost.releaseConnection();

            CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
            Cookie[] logoncookies = cookiespec.match(request.getServerName(), request.getServerPort(), "self-login",
                    false, client.getState().getCookies());
            m_log.debug("Logon cookies:");
            if (logoncookies.length == 0) {
                m_log.debug("None");
            } else {
                for (Cookie logoncooky : logoncookies) {
                    m_log.debug("- " + logoncooky.toString());
                }
            }

            if ((result == HttpStatus.SC_MOVED_TEMPORARILY) ||
                    (result == HttpStatus.SC_MOVED_PERMANENTLY) ||
                    (result == HttpStatus.SC_SEE_OTHER) ||
                    (result == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header header = authPost.getResponseHeader("location");
                String newuri;
                if (header != null) {
                    newuri = header.getValue();
                    if ((newuri == null) || (newuri.equals(""))) {
                        newuri = "/protected/lle5.do";
                    }
                    GetMethod redirect = new GetMethod(newuri);
                    redirect.addRequestHeader(WebConstants.FORWARD_DESTINEE, (String) session.getAttribute(WebConstants.FORWARD_DESTINEE));
                    //client.executeMethod(redirect);
                    response.addHeader("Set-Cookie",
                            "JSESSIONID=" + redirect.getRequestHeader("Cookie") +
                                    ";Path=" + request.getContextPath());
                    if (newuri.indexOf("jsessionid=") > -1) {
                        String jsessionid =
                                newuri.substring(newuri.indexOf("jsessionid=") +
                                        11, newuri.length());

                        response.addHeader("Set-Cookie",
                                "JSESSIONID=" + jsessionid +
                                        ";Path=" + request.getContextPath());
                    }
                    //RequestDispatcher dispatcher = request.getRequestDispatcher("/protected/lle5.do");

                    //dispatcher.forward(request,response);
                    sendRedirect("/j_security_check",request,response,clientCapabilities,"j_security_check");
                    actionForward = mapping.findForward("dummyPage");
                    m_log.debug("forwarding5 to:" + actionForward);
                    return actionForward;
                    /*PrintWriter out = response.getWriter();
                    out.write(new String(redirect.getResponseBody()));
                    out.close();
                    response.flushBuffer();
                    return null;*/
                }


            }
            
            session.removeAttribute("j_security_check");
            session.removeAttribute("j_username");
            session.removeAttribute("j_password");
            session.removeAttribute("c4p.toLogin");
            sendRedirect("/loginPage5.do?action=error",request,response,clientCapabilities,"error");
            actionForward = mapping.findForward("dummyPage");
            m_log.debug("forwarding5 to:" + actionForward);
            return actionForward;
        }
       if (action!=null&&action.equals("error")) {
            if (request.getParameter("toolbar")!=null) {
                return super.findForward(request, mapping, "error.toolbar");
            }
        }

        String forwardName = "";
        if (state != null) {
            if(state.getReseller()==null)
                state.setTemporaryLocale(request);
            if (action == null) {
                sendRedirect("/loginPage5.do?action=unauthorized",request,response,clientCapabilities,"unauthorized");
                actionForward = mapping.findForward("dummyPage");
                m_log.debug("forwarding5 to:" + actionForward);
                return actionForward;
            }
            forwardName = "success";
            if (action.equalsIgnoreCase("error") && !request.getRequestURL().toString().toLowerCase().startsWith("https"))
                forwardName = forwardName + ".http";
            forwardName = forwardName + "." + action;
            //        WebUtil.setNoCacheHeaders(response);

        } else {
            if (request.getMethod().equals("POST")) {
                //TODO make it for phone too
                if (request.getHeader("referer").indexOf("/pda/") == -1) {
                    request.setAttribute("statusCode", 500);
                    request.setAttribute("code", -1);
                    request.setAttribute("description", "Session time is expired. Please, log in again.");
                    forwardName = "error.session";
                } else {
                    response.sendRedirect("/loginPage5.do?action=expiry");
                    actionForward = mapping.findForward("dummyPage");
                    m_log.debug("forwarding5 to:" + actionForward);
                    return actionForward;
                }
            } else if (request.getMethod().equals("GET")) {
                if (session != null) {
                    sendRedirect("/loginPage5.do?action="+(action==null?"unauthorized":action),request,response,clientCapabilities,action);
                    actionForward = mapping.findForward("dummyPage");
                    m_log.debug("forwarding5 to:" + actionForward);
                    return actionForward;
                } else {
                    sendRedirect("/loginPage5.do?action=expiry",request,response,clientCapabilities,"expiry");
                    actionForward = mapping.findForward("dummyPage");
                    m_log.debug("forwarding5 to:" + actionForward);
                    return actionForward;
                }
            }
/*
            if (request.getRequestURI().equals("/loginPage5.do")) {
                response.sendRedirect("/loginPage5.do?action=unauthorized");
                actionForward = mapping.findForward("dummyPage");
                m_log.debug("forwarding5 to:" + actionForward);
                return actionForward;
            }*/
        }
        actionForward = super.findForward(request, mapping, forwardName);
        m_log.debug("forward name = " + forwardName);
        return actionForward;
    }

    protected void sendRedirect(String url,HttpServletRequest request, HttpServletResponse response, ClientCapabilities client,String action) throws IOException {
        if (client!= null && client.getInitialDisplay().equals(DisplayMode.PHONE)) {
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader("Content-type", "text/vnd.wap.wml");
            response.setHeader("Location",url);
        } else {
            if (request.getSession().getAttribute("secure") == null && (action==null || !action.equalsIgnoreCase("nothing"))) {
                response.sendRedirect("https://"+request.getServerName()+url);
                request.getSession().setAttribute("secure","true");
            } else {
                response.sendRedirect(url);                
            }
        }
    }

    protected boolean isAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse, ActionErrors actionerrors) {
        return true;
    }

    private static Log m_log;

    static {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.lle5.LoginPage5.class);
    }
}
