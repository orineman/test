package com.infowave.c4p.web.actions.test;

import com.infowave.c4p.web.util.BaseAction;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    // Returns the contents of the file in a byte array.
    public byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

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
//      File f = null;
//      if (request.getParameter("file") != null && request.getParameter("file").length() > 0) {
//          f = new File(request.getParameter("file"));
//      }
//
//      if (request.getParameter("form") != null || f == null || ! f.exists()) {
//          PrintWriter out = response.getWriter();
//          out.println("<html><body>");
//          if (f != null && ! f.exists()) {
//              out.println("<h2>File \"" + f.getAbsolutePath() + "\" does not found!</h2>");
//          }
//          out.println("<form action='" + mapping.getPath() + ".do' method='POST' target='content'>");
//          out.println("File: <input name='file'><br>");
//          out.println("Action: <input type='radio' name='action' value='show' checked>show <input type='radio' name='action' value='load'>load<br>");
//          out.println("<input type='submit' value='Submit'><br>");
//          out.println("</form>");
//          out.println("<iframe name='content' src='' width='100%' height='500' hspace='10' vspace='10' scrolling=true>");
//          out.println("</body></html>");
//      }
//      else if (f != null && f.exists()) {
//          response.setHeader("Content-Type", new javax.activation.MimetypesFileTypeMap().getContentType( f ));
//          response.setHeader("Content-Length", String.valueOf( f.length() ));
//          if (request.getParameter("action").equals("load")) {
//              response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
//          }
//
//          ServletOutputStream out = response.getOutputStream();
//          out.write(this.getBytesFromFile( f ));
//      }

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
