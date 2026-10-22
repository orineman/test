package com.infowave.c4p.web.actions.lle5.feedback;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alexogar
 * Date: Jul 30, 2008
 * Time: 7:03:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubmitHostBugReport extends EmailBugReport {

    protected List<MimeBodyPart> parts = new ArrayList<MimeBodyPart>();
    private static final String HOST_BUG_REPORT_XML = "hostBugReportXML";

    /**
     * @see SubmitBugReport#doPost(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {// Create a factory for disk-based file items
        
        if (ServletFileUpload.isMultipartContent(request)) {
            // Create a new file upload handler
            FileItemFactory factory = new DiskFileItemFactory();

            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List files = upload.parseRequest(request);
                MimeBodyPart messageBodyPart;
                for (Object o : files) {
                    FileItem f = (FileItem) o;

                    if (f.isFormField()) {
                        if (f.getFieldName().equalsIgnoreCase(HOST_BUG_REPORT_XML)) {
                            getDesktopInfo(f.get());
                        }
                    } else {
                        messageBodyPart = new MimeBodyPart();
                        DataSource source =
                                new ByteArrayDataSource(f.getFieldName(), f.getContentType(), f.get());
                        messageBodyPart.setFileName(f.getName());
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        parts.add(messageBodyPart);
                    }
                }
            } catch (FileUploadException e) {

            } catch (MessagingException m) {

            }

            sendBugReport();
        }
    }

    @Override
    protected void addEmailAttachments(Multipart multipart) throws IOException, MessagingException {
        for (MimeBodyPart m : parts) {
            multipart.addBodyPart(m);
        }
    }
}
