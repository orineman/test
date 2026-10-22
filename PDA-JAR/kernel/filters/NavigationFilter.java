/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 17.10.2007
 * Time: 15:12:44
 * To change this template use File | Settings | File Templates.
 */
package com.infowave.pda.kernel.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.ListIterator;

public class NavigationFilter implements javax.servlet.Filter {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(NavigationFilter.class);

    private HttpSession session;

    public NavigationFilter() {
    }

    class NavigationStack extends LinkedList<String> {
        public String removeFirst() {
            if (super.peek().indexOf("?") == -1) {
                return super.removeFirst() + "?go=exit";
            } else {
                return super.removeFirst() + "&go=exit";
            }
        }

        public void addFirst(String s) {
            if (s.indexOf("?") == -1) {
                super.addFirst(s + "?go=exit");
            } else {
                super.addFirst(s + "&go=exit");
            }

        }

        public int getLength() {
            ListIterator it = this.listIterator();
            int ret = 0;
            while (it.hasNext()) {
                ret = it.nextIndex();
            }
            return ret;
        }
    }

    private NavigationStack navigationStack;

    /*
     * Constants
     */
    public static final String NAVIGATION = "navigationStack";

    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, java.io.IOException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            String method = servletRequest.getParameter("go");
            session = servletRequest.getSession();
            navigationStack = (NavigationStack) session.getAttribute(NAVIGATION);

            if (navigationStack == null) {
                navigationStack = new NavigationStack();
                session.setAttribute(NAVIGATION,navigationStack);
            }

            if (method != null) {
/*
                navigationStack = (NavigationStack) session.getAttribute(NAVIGATION);

                if (navigationStack == null) {
                    navigationStack = new NavigationStack();
                }

*/              String queryStr = "";
                if (((HttpServletRequest)request).getMethod().equalsIgnoreCase("get")) {
                    queryStr = servletRequest.getQueryString();
                } else {
                    queryStr = "go=enter";                    
                }
                if (method.equals("enter")) {
                    int qin = queryStr.indexOf("go=enter");
                    if (qin != -1) {
                        if (qin == 0) {
                            queryStr = queryStr.substring(8);
                        } else {
                            queryStr = queryStr.substring(0, qin - 1) + queryStr.substring(qin + 8);
                        }
                    }
                    String referer = servletRequest.getHeader("referer");
                    int index = referer.indexOf("go=enter");
                    if (navigationStack.size() > 0) {
                        if (!navigationStack.peek().equals(referer)) {

                            if (index != -1) {
                                referer = referer.substring(0, index) + referer.indexOf(index + 8);
                            }
                            navigationStack.addFirst(referer);
                        }
                    } else {
                        if (index != -1) {
                            referer = referer.substring(0, index) + referer.indexOf(index + 8);
                        }
                        navigationStack.addFirst(referer);
                    }
                    session.setAttribute(NAVIGATION, navigationStack);
                    servletResponse.sendRedirect(servletRequest.getRequestURI() + "?" + queryStr);
                    response.flushBuffer();
                    return;
                } else if (method.equals("exit")) {
                    int qin = -2;
                    while (qin != -1) {
                        qin = queryStr.indexOf("go=exit");
                        if (qin == 0) {

                                queryStr = queryStr.substring(7);

                        } else {
                            if (qin != -1) {
                                queryStr = queryStr.substring(0, qin - 1) + queryStr.substring(qin + 7);
                            }
                        }
                    }
                    navigationStack.removeFirst();
                    session.setAttribute(NAVIGATION, navigationStack);
                    servletResponse.sendRedirect(servletRequest.getRequestURI() + (!queryStr.equals("")?"?":"") + queryStr);
                    response.flushBuffer();
                    return;
                }
            } /*else {
                session = servletRequest.getSession();

                navigationStack = (NavigationStack) session.getAttribute(NAVIGATION);

                if (navigationStack == null) {
                    navigationStack = new NavigationStack();
                }
                                                                                                                              
                if (navigationStack.getLength() != 0) {
                    String referer = servletRequest.getHeader("referer");
                    int index = referer.indexOf("go=enter");
                    if (navigationStack.size() > 0) {
                        if (!navigationStack.peek().equals(referer)) {

                            if (index != -1) {
                                referer = referer.substring(0, index) + referer.indexOf(index + 8);
                            }
                            navigationStack.addFirst(referer);
                        }
                    } else {
                        if (index != -1) {
                            referer = referer.substring(0, index) + referer.indexOf(index + 8);
                        }
                        navigationStack.addFirst(referer);
                    }
                    session.setAttribute(NAVIGATION, navigationStack);
                }
            }*/
        }
        chain.doFilter(request, response);
    }

    public static String getBackReference(HttpSession session) {
        NavigationStack navigationStack = (NavigationStack) session.getAttribute(NAVIGATION);
        return navigationStack.peek();
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }
}
