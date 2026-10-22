package com.infowave.c4p.web.actions.test;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.RequestRedirector;
import com.infowave.c4p.web.filters.ApplicationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.httpclient.NameValuePair;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Dec 18, 2003
 * Time: 7:05:23 PM
 * To change this template use Options | File Templates.
 */
public class TestAction extends BaseAction
{
   /** The class's logger */
  private static Log m_log = LogFactory.getLog(TestAction.class);

  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs
   * @return a forward
   * @throws Exception

   */
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    //printMultiPartRequestParameters(request,out);
    RequestRedirector redirector = new RequestRedirector(request,"fff",null);
    redirector.getMethod();
    out.println("here: " + request.getHeader("Content-type"));
    out.println("</body></html>");
    response.flushBuffer();
    return null;
  }

  /**
   * Checks that this action is authorized.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return true if this action can be executed.
   */
  protected boolean isAuthorized(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 ActionErrors errors)
  {
    return true;
  }


    private void printMultiPartRequestParameters(HttpServletRequest request,
                                                    PrintWriter out)
  {
    try {
      if (FileUpload.isMultipartContent(request)){
        out.println("multipart");
      } else {
        out.println("not a mp");
      }
      String dir = "/home/vincent/dev/portal/tmp"; // todo
      DiskFileUpload upload = new DiskFileUpload();
      upload.setRepositoryPath(dir);
      List  items = upload.parseRequest(request);
      List params = new ArrayList (12);

      Iterator iter = items.iterator();
      int counter = 0;
      while (iter.hasNext()) {
        FileItem item = (FileItem) iter.next();
        if (item.isFormField()) {
          out.println("Param: " + item.getFieldName() + "=" +item.getString());
        } else {
          String sessionId = request.getSession().getId();
          String fieldName = item.getFieldName();
          String fileName = item.getName();
          String contentType = item.getContentType();
          boolean isInMemory = item.isInMemory();
          long sizeInBytes = item.getSize();
          File uploadedFile = new File(dir + "/" + sessionId + "_" + counter++);
          //item.write(uploadedFile);
          out.println("fieldName: " + fieldName + " - fileName: " + fileName);
        }
      }
    }
    catch (Exception e) {
      m_log.error("Cannot process multipart request",e);
    }
  }


  private void dumpMp(HttpServletRequest request)
  {
    try {
      String BOUNDARY_FIELD = "boundary=";
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
      InputStream is = request.getInputStream();
      OutputStream os = new FileOutputStream("/poub/tmp.poub");
      try {
        MultipartStream multipartStream;
        multipartStream = new MultipartStream(is,boundary.getBytes(), 512);


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

      } catch(MultipartStream.MalformedStreamException e) {
        // the stream failed to follow required syntax
      } catch(IOException e) {
        // a read or write error occurred
      }
    }
    catch (Exception e) {
      m_log.debug("Exception in dump",e);
    }
  }


}
