package com.infowave.c4p.web.filters;

import org.apache.commons.lang.time.DateUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.TimeZone;

/**
 * @author Alexey Ogarkov
 */
public class SetTimezoneOffsetFilter implements Filter {

    public static final String PROPERTY_JAVASCRIPT_ENABLED = "JS_ENABLED";

    public static final String PROPERTY_TIMEZONE_OFFEST = "c4p_timezoneOffset";

    public static final String COOKIE_NAME = "timezoneOffset";

    public static final String JAVASCRIPT_COOKIE_NAME = "javascript";

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getSession().getAttribute(PROPERTY_TIMEZONE_OFFEST) == null) {
            req.getSession().setAttribute(
                    PROPERTY_JAVASCRIPT_ENABLED, false);
            if (req.getCookies() != null) {
                for (Cookie cookie : req.getCookies()) {
                    if (COOKIE_NAME.equals(cookie.getName())) {
                        int timezoneOffsetMinutes = Integer.parseInt(cookie.getValue());
                        TimeZone timeZone = TimeZone.getTimeZone("GMT");
                        timeZone.setRawOffset(
                                (int) (timezoneOffsetMinutes * DateUtils.MILLIS_PER_MINUTE));
                        //Config.set(req.getSession(), Config.FMT_TIME_ZONE, timeZone);
                        req.getSession().setAttribute(
                                PROPERTY_TIMEZONE_OFFEST, new Integer(timezoneOffsetMinutes));
                        // removing cookie, Sun sucks as usual in their API
                        cookie.setMaxAge(0);
                        res.addCookie(cookie);
                    } else if (JAVASCRIPT_COOKIE_NAME.equals(cookie.getName())) {
                        req.getSession().setAttribute(
                                PROPERTY_JAVASCRIPT_ENABLED, true);
                    }
                }
            }
        }
        if (req.getSession().getAttribute(PROPERTY_JAVASCRIPT_ENABLED) == null) {
            req.getSession().setAttribute(PROPERTY_JAVASCRIPT_ENABLED, true);
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}