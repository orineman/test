package com.infowave.c4p.web.filters;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.MultipartStream;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.infowave.c4p.web.util.RequestRedirector;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.web.util.*;


/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class UploadFilter implements Filter
{

  private static final String BOUNDARY_FIELD = "boundary=";
  private static final int TRANSFER_BUFFER_SIZE = 1024;

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(UploadFilter.class);

  public void init(FilterConfig filterConfig) throws ServletException {
  }

  public void destroy() {
  }


    public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain)
          throws IOException, ServletException
  {
    boolean stopHere = false;
    String softwareVersion = null;
    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest)servletRequest;
      HttpServletResponse response = (HttpServletResponse)servletResponse;
      if (FileUpload.isMultipartContent(request)) {
        DesktopDTO desktop = null;
        SessionState state = WebUtil.getSessionState(request);
        if (state != null ) {
            desktop = state.getCurrentDesktop();
            if( desktop != null ) softwareVersion = desktop.getSoftwareVersion();
        }
        RequestRedirector rq = new RequestRedirector(request,"popo",null,softwareVersion );
        rq.getMethod();
      } else {
        filterChain.doFilter(servletRequest, servletResponse);
      }
    }
  }
  public void doFilter_____(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain)
          throws IOException, ServletException
  {
    boolean stopHere = false;
    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest)servletRequest;
      HttpServletResponse response = (HttpServletResponse)servletResponse;
      if (FileUpload.isMultipartContent(request)) {
        // Get the boundary
        String boundary = null;
        String contentType = request.getHeader("Content-type");
        if (contentType != null) {
          int idxBoundary = contentType.indexOf(BOUNDARY_FIELD);
          if (idxBoundary != -1){
             boundary = contentType.substring(idxBoundary + BOUNDARY_FIELD.length(),
                                              contentType.length());
            m_log.debug("CT: " + contentType + " B: " + boundary);
          } else {
            m_log.warn("The content-type for this multipart reqyest does not contains the boundary: " + contentType);
          }
        } else {
          m_log.warn("this multipart request does not contain a Content-type header");
        }
      if (boundary == null) {
        return;
      }
        stopHere = true;
        InputStream is = request.getInputStream();
        OutputStream os = response.getOutputStream();
        try {
          MultipartStream multipartStream;
          multipartStream = new MultipartStream(is,boundary.getBytes(),
                                                TRANSFER_BUFFER_SIZE);

          boolean nextPart = multipartStream.skipPreamble();
          while(nextPart) {
            m_log.debug("got a part");
            String headers = multipartStream.readHeaders();
            m_log.debug("headers: " + headers);
            // todo header = chunks.readHeader();
            // process headers
            // create some output stream
            multipartStream.readBodyData(os);
            nextPart = multipartStream.readBoundary();
          }
          servletResponse.flushBuffer();

        } catch(MultipartStream.MalformedStreamException e) {
          // the stream failed to follow required syntax
        } catch(IOException e) {
          // a read or write error occurred
        }

      }


    }

    if(!stopHere) {
      filterChain.doFilter(servletRequest,servletResponse);
    }
  }

}
