/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 24.10.2007
 * Time: 18:42:43
 * To change this template use File | Settings | File Templates.
 */
package com.infowave.c4p.web.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements javax.servlet.Filter {

    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(LoginFilter.class);

    private ServletContext ctx;
    private FilterConfig config = null;

    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, java.io.IOException {
        boolean stopFilterChain = false;
        if (req instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest) req;
            if (servletRequest.getUserPrincipal() == null || servletRequest.getRequestURI().equals("/j_security_check")) {
                if (isRedirectAvailable(servletRequest)) {
                    RequestDispatcher dispatcher = null;
                    if (servletRequest.getSession() != null) {
                        HttpSession session = servletRequest.getSession();
                        if (session.getAttribute("toLogin") != null && servletRequest.getUserPrincipal() == null) {
                            // session.removeAttribute("toLogin");
                        } else {
                            m_log.debug("redirect not loginned user to index.jsp");
                            session.setAttribute("toLogin", "whatever");
                            if (servletRequest.getParameter("action") != null) {
                                dispatcher = servletRequest.getRequestDispatcher("/index.jsp?action=" + servletRequest.getParameter("action"));
                            } else {
                                dispatcher = servletRequest.getRequestDispatcher("/index.jsp");
                            }
                            stopFilterChain = true;
                            dispatcher.forward(servletRequest, resp);
                        }
                    }

                    if (servletRequest.getParameter("rememberMe")!=null) {

                    }
                }
            }
        }

        if (!stopFilterChain) {
            chain.doFilter(req, resp);
        }
    }

    private boolean isRedirectAvailable(HttpServletRequest servletRequest) {
        return (!servletRequest.getHeader("User-Agent").equalsIgnoreCase("lle"))
             &&(!servletRequest.getHeader("User-Agent").equalsIgnoreCase("llhttpclient"))
             &&(!servletRequest.getRequestURI().equalsIgnoreCase("/setversion.do"))
             &&(!isNotForbidenURL(servletRequest.getRequestURI()));
    }

    private boolean isNotForbidenURL(String url) {
        for(String s : notForbiddenUrls) {
            if (s.trim().equalsIgnoreCase(url)) {
                return true;
            }
        }
        return false;
    }

    private String[] notForbiddenUrls;

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        String value = config.getInitParameter("notForbiddenUrls");

        if (value!=null) {
            notForbiddenUrls = value.split(";");
        }
    }

}
