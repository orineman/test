package com.infowave.c4p.web.actions.lle5.feedback;

import com.infowave.c4p.util.ApplicationParameters;
import com.infowave.c4p.service.dto.DesktopDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import java.util.Date;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 * User: alexogar
 * Date: Jul 29, 2008
 * Time: 4:36:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailBugReport extends SubmitBugReport {

    private String to;
    private String from;
    private String subject;
    private String attachFileName;

    private static final String BUGREPORT_FROM_KEY = "";

    public void init(ServletConfig c) throws ServletException {
        super.init(c);

        to = c.getInitParameter("to");
        from = c.getInitParameter("from");
        subject = c.getInitParameter("subject");
        attachFileName = c.getInitParameter("logname");
        if (subject == null) {
            subject = generateSubject();
        }

        if (from == null) {
            //String
        }

        Properties props = System.getProperties();
        String smtpHost = "localhost";
        try {
        	smtpHost = ApplicationParameters.instance().getSmtpServer();
        } catch( IllegalStateException ise ) {
        }
        m_log.debug("SMTP Host: " + smtpHost);

        if (smtpHost != null) {
            props.put("mail.smtp.host", smtpHost);
        }

    }

    protected boolean sendBugReport() {
        try {
            Properties props = System.getProperties();
            Session session = Session.getDefaultInstance(props, null);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            msg.setSubject(subject);
            MimeBodyPart messageBodyPart =
                  new MimeBodyPart();
            messageBodyPart.setContent(generateMessageBody(),"text/html;charset=UTF-8");
            Multipart multipart = new MimeMultipart();
               multipart.addBodyPart(messageBodyPart);
            addEmailAttachments(multipart);

            // Put parts in message
               msg.setContent(multipart);
           
            msg.setSentDate(new Date());
            Transport.send(msg);
        }
        catch (Exception e) {
            String msg = "Cannot send message from: " + from + " to: " + to;
            m_log.warn(msg, e);
            return false;
        }
        return true;
    }

    protected void addEmailAttachments(Multipart multipart) throws IOException, MessagingException {
        MimeBodyPart messageBodyPart;// Part two is attachment
        if (bugReport.getHostLogFile()!=null) {
           messageBodyPart = new MimeBodyPart();
           DataSource source =
             new ByteArrayDataSource(attachFileName,hostLogContentType,bugReport.getHostLogFile());
           messageBodyPart.setFileName(attachFileName);
           messageBodyPart.setDataHandler(
             new DataHandler(source));
           multipart.addBodyPart(messageBodyPart);
        }
    }

    protected String generateMessageBody() {
        StringBuffer sb = new StringBuffer();

        sb.append("<center><h1>");
        sb.append("Laplink EveryWhere User Feedback System");
        sb.append("</h1></center>");

        if (hostReport!=null) {
            Field[] fields = HostReport.class.getFields();
            sb.append("<h2>Host Report Properties:</h2><p>");
            sb.append("<ul>");
            for (Field f : fields) {
                Object value;
                try {
                     value = f.get(hostReport);
                     if (value instanceof List) {
                        sb.append(listElement(f.getName(), (List) value));
                     } else {
                        sb.append("<li>"+f.getName() + " : " + f.get(hostReport));
                     }
                } catch (IllegalAccessException e) {
                    break;
                }
            }
            sb.append("</ul>");
        }


        sb.append("<h2>Server Report Properties:</h2><p>");

        if (bugReport.getBrowserInfo()!=null) {
            sb.append("<h3>Browser Properties</h3><p>");
            sb.append("<h4>User-Agent :" +bugReport.getBrowserInfo().getUserAgent() +"</h4>" );
            sb.append("<ul>");
            sb.append(listPropertyString("Company",bugReport.getBrowserInfo().getCompany()));
            sb.append(listPropertyString("MinorVersion",bugReport.getBrowserInfo().getMinorVersion()));
            sb.append(listPropertyString("MainVersion",bugReport.getBrowserInfo().getMainVersion()));
            sb.append(listPropertyString("Name",bugReport.getBrowserInfo().getName()));
            sb.append(listPropertyString("OS",bugReport.getBrowserInfo().getOs()));
            sb.append(listPropertyString("Version",bugReport.getBrowserInfo().getVersion()));
            sb.append("<ul>");
            sb.append(listPropertyString("Screen Height", String.valueOf(bugReport.getBrowserInfo().getHeight())));
            sb.append(listPropertyString("Screen Width", String.valueOf(bugReport.getBrowserInfo().getWidth())));
            sb.append("</ul>");
            sb.append("</ul>");
        }

        if (bugReport.getUser()!=null) {
            sb.append("<h3>User Properties</h3><p>");
            sb.append("<h4>Username :" +bugReport.getUser().getUsername() );
            sb.append("<ul>");
            sb.append(listPropertyString("Current Desktop",state.getCurrentDesktop().getName()));
            sb.append(listPropertyString("Email",bugReport.getUser().getEmail()));
            sb.append("<li>"+listElement("Desktops",(List<DesktopDTO>) state.getUserDesktops())+"</li>");
            sb.append("<ul>");
        }

        if (bugReport.getUserComment()!=null) {
            sb.append("<h2>User Comment:</h2><p>");
            sb.append("<b><i>"+bugReport.getUserComment()+"</i></b>");
        }
        return sb.toString();
    }

    private <E> StringBuffer listElement(String name, List<E> list) {
        StringBuffer sb = new StringBuffer();
        sb.append("<h4>"+name+"</h3><p><ul>");
        for (E e : list) {
            sb.append("<li>"+e.toString()+"</li>");
        }
        sb.append("</ul>");

        return sb;
    }

    private String listPropertyString(String name, String value) {
        return "<li>"+name+" : " + value+ "</li>";
    }

    protected String generateSubject() {
        return "LLE5 BugReport";
    }

    class ByteArrayDataSource implements DataSource {

        private String name;
        private String contentType;
        private byte[] baos;

        ByteArrayDataSource(String name, String contentType, byte[] input) throws IOException {
            this.name = name;
            this.contentType = contentType;
            this.baos = input;
        }

        public String getContentType() {
            return contentType;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(baos);
        }

        public String getName() {
            return name;
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Cannot write to this read-only resource");
        }
    }
}
