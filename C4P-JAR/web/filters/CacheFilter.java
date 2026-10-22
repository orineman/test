package com.infowave.c4p.web.filters;

import java.io.IOException;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Calendar;
import com.infowave.c4p.web.util.CacheResponseWrapper;
import com.infowave.c4p.web.util.BrowserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Alexey Ogarkov
 */
public class CacheFilter implements Filter {
    /** The class's logger */
    private static Log m_log = LogFactory.getLog(CacheFilter.class);

    ServletContext sc;
    FilterConfig fc;

    String matchString = "/ui/.*?\\.(gif|jpe?g|png)$";

    File tempDirectory;

    long cacheTimeout = Long.MAX_VALUE;

    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain)
                            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // check if was a resource that shouldn't be cached.
        String r = sc.getRealPath("");
        String path = fc.getInitParameter(request.getRequestURI());
        /* Here if where is init parameter with url -name and nocache -value
         * we path throw request
         * If in parameter matchString reqeustURI match rege string of param value
         * we path throw request too
         * Also we path throw request if there is nocache request headers.
         */
        if ((path!= null && path.equals("nocache")) ||
                        isNotCashableContent(request) ||
                        !request.getRequestURI().matches(matchString)) {
          chain.doFilter(request, response);
          return;
        }
/*      path = r+path;
        m_log.debug("Cache started for path" + path);
        String id = request.getRequestURI() + request.getQueryString();

        // get possible cache
        String temp = tempDirectory.getAbsolutePath();
        File file = new File(temp+id);

        // get current resource
        if (path == null) {
          path = sc.getRealPath(request.getRequestURI());
        }

        File current = new File(path);

        try {
          long now = Calendar.getInstance().getTimeInMillis();
          //set timestamp check
          if (!file.exists() || (file.exists() &&
                 current.lastModified() > file.lastModified()) ||
              cacheTimeout < now - file.lastModified()) {

            String name = file.getAbsolutePath();
            name = name.substring(0,name.lastIndexOf("/"));
            new File(name).mkdirs();
            ByteArrayOutputStream baos =
                new ByteArrayOutputStream();
            CacheResponseWrapper wrappedResponse =
              new CacheResponseWrapper(response, baos);
            chain.doFilter(req, wrappedResponse);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            m_log.debug("Cache ended file "+ name + "cached");
          }
        } catch (ServletException e) {
          if (!file.exists()) {
            m_log.error("Some error in caching",e);
            throw new ServletException(e);
          }
        } catch (IOException e) {
          if (!file.exists()) {
            m_log.error("Some error in caching",e);
            throw e;
          }
        }

        FileInputStream fis = new FileInputStream(file);
        String mt = sc.getMimeType(request.getRequestURI());
        response.setContentType(mt);
        ServletOutputStream sos = res.getOutputStream();
        for (int i = fis.read(); i!= -1; i = fis.read()) {
          sos.write((byte)i);
        }
        // get possible cache
        String temp = tempDirectory.getAbsolutePath();
        File file = new File(temp+id);

        // get current resource
        if (path == null) {
          path = sc.getRealPath(request.getRequestURI());
        }

        File current = new File(path);

        try {
          long now = Calendar.getInstance().getTimeInMillis();
          //set timestamp check
          if (!file.exists() || (file.exists() &&
                 current.lastModified() > file.lastModified()) ||
              cacheTimeout < now - file.lastModified()) {

            String name = file.getAbsolutePath();
            name = name.substring(0,name.lastIndexOf("/"));
            new File(name).mkdirs();
            ByteArrayOutputStream baos =
                new ByteArrayOutputStream();
            CacheResponseWrapper wrappedResponse =
              new CacheResponseWrapper(response, baos);
            chain.doFilter(req, wrappedResponse);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            m_log.debug("Cache ended file "+ name + "cached");
          }
        } catch (ServletException e) {
          if (!file.exists()) {
            m_log.error("Some error in caching",e);
            throw new ServletException(e);
          }
        } catch (IOException e) {
          if (!file.exists()) {
            m_log.error("Some error in caching",e);
            throw e;
          }
        }

        FileInputStream fis = new FileInputStream(file);
        String mt = sc.getMimeType(request.getRequestURI());
        response.setContentType(mt);
        ServletOutputStream sos = res.getOutputStream();
        for (int i = fis.read(); i!= -1; i = fis.read()) {
          sos.write((byte)i);
        }*/
        long now = System.currentTimeMillis();
        now += cacheTimeout;
        response.setHeader("Expires" , new Date(now).toString());
        response.setHeader("Cache-Control" , "must-revalidate");
        if (request.getSession()!=null) {
            request.getSession();
            BrowserInfo info = new BrowserInfo(request,request.getSession());
            if (info.isIE()) {
                response.setHeader("Cache-Control","post-check=604800 env=browser_MSIE");
                response.setHeader("Cache-Control","pre-check=604800 env=browser_MSIE");
            }
        }
        chain.doFilter(request, response);
        return;
    }

    public void init(FilterConfig filterConfig) {
        this.fc = filterConfig;
        this.sc = filterConfig.getServletContext();
        //cacheTimeout init-param
        String ct = fc.getInitParameter("cacheTimeout");
        if (ct != null) {
          cacheTimeout = 60*1000*Long.parseLong(ct);
          m_log.debug("Set cacheTimeout to " +cacheTimeout);
        }
        //regex for matching requestURI
        String m = fc.getInitParameter("regex");
        if ( m != null ) {
            matchString = m;
            m_log.debug("Set matching regexp to " +matchString);
        }
        //tempDir
        String tempDirPath = fc.getInitParameter("tempDir");
        if ( tempDirPath != null ) {
            tempDirectory = new File(tempDirPath);
        } else {
            tempDirectory = (File)sc.getAttribute("javax.servlet.context.tempdir");
        }
        m_log.debug("Set temp DIR regexp to " +tempDirectory.getPath());
    }

    public void destroy() {
        this.sc = null;
        this.fc = null;
    }

    private boolean isNotCashableContent(HttpServletRequest request) {
        String cache_control = request.getHeader("Cache-Control");
        String pragma = request.getHeader("Pragma");
        return (cache_control != null  && cache_control.equals("no-cache")) ||
               (pragma != null  && pragma.equals("no-cache"));
    }
}
