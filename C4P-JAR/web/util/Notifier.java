package com.infowave.c4p.web.util;

import com.infowave.c4p.web.config.Configurator;
import com.infowave.c4p.util.StringManager;
import com.infowave.c4p.util.ApplicationParameters;

import java.util.Properties;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.*;

import javax.activation.*;

public class Notifier
{
  private static final String PASSWORD_RECOVERY_SUBJECT_KEY = "mail.passwordRecoverySubject";
  private static final String PASSWORD_RECOVERY_FROM_KEY = "mail.passwordRecoveryFrom";
  private static final String NEW_ACCOUNT_SUBJECT_KEY = "mail.newAccountSubject";
  private static final String NEW_ACCOUNT_BODY_KEY = "mail.newAccountBody";
  private static final String NEW_ACCOUNT_FROM_KEY = "mail.newAccountFrom";
  private static final String LICENSE_SUBJECT_KEY = "mail.newLicensesSubject";
  private static final String LICENSE_BODY_KEY = "mail.newLicensesBody";
  private static final String LICENSE_FROM_KEY = "mail.newLicensesFrom";
  private static final String LICENSE_CC_KEY = "mail.newLicensesCC";
  private static final String LICENSE_TO_KEY = "mail.newLicensesTo";
  private static final String ONYXERR_SUBJECT_KEY = "onyxerr.subject";
  private static final String ONYXERR_BODY_KEY = "onyxerr.body";
  private static final String ONYXERR_FROM_KEY = "onyxerr.from";
  private static final String ONYXERR_TO_KEY = "onyxerr.to";
  private static final String SETTING_UP_INSTRUCTIONS_SUBJECT = "mail.settingUpSubject";
  private static final String SETTING_UP_INSTRUCTIONS_BODY = "mail.settingUpBody";

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(Notifier.class);

  /** The class' unique instance */
  private static Notifier m_instance = null;

    // ----------------------------------------------------------- Public methods

  public void notifyPasswordRecovery(String reseller,String to,String newpassword,Locale locale)
  {
    StringManager stringMgr;
    if (locale != null) {
      stringMgr = StringManager.getManager(reseller,locale);
    } else {
      stringMgr = StringManager.getManager(reseller);
    }
    if (stringMgr == null){
      m_log.error("Cannot send mail- no String manager for reseller: " + reseller);
      return;
    }

    String subject = stringMgr.getString(PASSWORD_RECOVERY_SUBJECT_KEY);
    String from = stringMgr.getString(PASSWORD_RECOVERY_FROM_KEY);

    String body = "Your new password is:\n"+newpassword;

    send(to,from,subject,body);
  }

  public boolean notifySettingUp(String reseller, String to, Locale locale) {
      StringManager stringMgr;
      if (locale != null) {
        stringMgr = StringManager.getManager(reseller,locale);
      } else {
        stringMgr = StringManager.getManager(reseller);
      }
      if (stringMgr == null){
        m_log.error("Cannot send mail- no String manager for reseller: " + reseller);
        return false;
      }
      String subject = stringMgr.getString(SETTING_UP_INSTRUCTIONS_SUBJECT);
      String from = stringMgr.getString(NEW_ACCOUNT_FROM_KEY);
      String body = stringMgr.getString(SETTING_UP_INSTRUCTIONS_BODY);
      return send(to,from,subject,body);
  }

  public void notifyAccountCreation(String reseller, String to,Locale locale)
  {
    StringManager stringMgr;
    if (locale != null) {
      stringMgr = StringManager.getManager(reseller,locale);
    } else {
      stringMgr = StringManager.getManager(reseller);
    }
    if (stringMgr == null){
      m_log.error("Cannot send mail- no String manager for reseller: " + reseller);
      return;
    }
    String subject = stringMgr.getString(NEW_ACCOUNT_SUBJECT_KEY);
    String body = stringMgr.getString(NEW_ACCOUNT_BODY_KEY);
    String from = stringMgr.getString(NEW_ACCOUNT_FROM_KEY);

    send(to,from,subject,body);
  }

  public void notifyLicenseCreation(String reseller, String recip, String msgText, Locale locale, String filename)
  {
    StringManager stringMgr;
    if (locale != null) {
      stringMgr = StringManager.getManager(reseller);
    } else {
      stringMgr = StringManager.getManager("reseller",locale);
    }
    if (stringMgr == null){
      m_log.error("Cannot send mail -- no String manager for reseller: " + reseller);
      return;
    }
    String subject = stringMgr.getString(LICENSE_SUBJECT_KEY);
    String body = stringMgr.getString(LICENSE_BODY_KEY) + msgText;
    String from = stringMgr.getString(LICENSE_FROM_KEY);
    String cc = stringMgr.getString(LICENSE_CC_KEY);
    String to = stringMgr.getString(LICENSE_TO_KEY);
    if( recip != null && recip.length() > 0 ) {
	if( cc != null && cc.length() > 0 ) {
		cc += ", ";
	}
	cc += recip;
    }


    send(to,from,cc,subject,body,filename);
  }

  public void notifyOnyxError(String msgText, Locale locale)
  {
    String reseller = "laplink";	// this is an admin message!
    StringManager stringMgr;
    if (locale != null) {
      stringMgr = StringManager.getManager(reseller);
    } else {
      stringMgr = StringManager.getManager("reseller",locale);
    }
    if (stringMgr == null){
      m_log.error("Cannot send mail -- no String manager for reseller: " + reseller);
      return;
    }
    String subject = stringMgr.getString(ONYXERR_SUBJECT_KEY);
    String body = stringMgr.getString(ONYXERR_BODY_KEY) + msgText;
    String from = stringMgr.getString(ONYXERR_FROM_KEY);
    String to = stringMgr.getString(ONYXERR_TO_KEY);

    send(to,from,subject,body);
  }

  // ------------------------------------------------------------ Helper methods
    /**
   * Returns the class' unique instance
   */
  public static Notifier instance()
  {
    if (m_instance == null) {
      m_instance = new Notifier();
      m_instance.initialize();
    }
    return m_instance;
  }

    /**
     * Sends a mail.
     *
     * @param to
     *      Address of mail recipient.
     * @param from
     *      Address of mail sender.
     * @param cc
     *      Value for "CC" field.
     * @param bcc
     *      Value for "BCC" field.
     * @param subject
     *      Mail subject.
     * @param content
     *      Mail message.
     * @param contentType
     *      Type of mail content/message (e.g. text/html, text/xml).
     *      If the type is not set then:
     *      <ul>
     *      <li>if message starts from &lt;html&gt; then text/html will be used
     *      <li>otherwise message will be treated as plain text (text/plain)
     *      </ul>
     * @param filename
     *      List of of mail attachments.
     *      Each item is file name of attachment.
     * @return
     *      <code>true</code> if mail was sent.
     */
    public boolean sendMail(String to, String from, String cc, String bcc,
                             String subject, String content, String contentType,
                             String[] filename) {
        if (contentType == null)
            contentType = "";
        try {
            Properties props = System.getProperties();
            Session session = Session.getDefaultInstance(props, null);
            Message msg = new MimeMessage(session);

            // Determine content type
            if (contentType.length() == 0) {
				String contentLC = content.trim().toLowerCase();
                if (contentLC.startsWith("<!doctype") || contentLC.startsWith("<html") )
                    contentType = "text/html";
                else
                    contentType = "text/plain";
            }

            // Set message fields
            msg.setFrom(new InternetAddress(from));
            // list must be comma separated if more than one recipient specified
            msg.setRecipients(Message.RecipientType.TO,
                              InternetAddress.parse(to, false));
            if ( cc != null && cc.length() > 0 )
                  msg.setRecipients(Message.RecipientType.CC,
                                    InternetAddress.parse(cc, false));
            if ( bcc != null && bcc.length() > 0 )
                  msg.setRecipients(Message.RecipientType.BCC,
                                    InternetAddress.parse(bcc, false));
            msg.setSubject(subject);
            msg.setHeader("X-Mailer", "MsgSend");
            msg.setSentDate(new Date());

            // Form message
            if (filename == null) {
                msg.setContent(content, contentType);
            }
            else {
                MimeMultipart mmp = new MimeMultipart();
                MimeBodyPart mbp = new MimeBodyPart();
                mbp.setContent(content, contentType);
                mmp.addBodyPart(mbp);
                FileDataSource fds;
                for (String file : filename) {
                    mbp = new MimeBodyPart();
                    fds = new FileDataSource(file);
                    mbp.setDataHandler( new DataHandler(fds) );
                    mbp.setFileName( fds.getFile().getName() );
                    mmp.addBodyPart(mbp);
                }
                msg.setContent(mmp);
                msg.saveChanges();
            }
            Transport.send(msg);
        }
        catch (Exception e) {
            String msg = "Cannot send message from: " + from + " to: " + to;
            m_log.warn(msg,e);
            return false;
        }
        return true;
    }

  /**
   * Sends a message.
   */
  public boolean send (String to, String from, String subject, String content)
  {
      return this.sendMail(to, from, null, null, subject, content, null, null);
  }

  /* Send with an attachment
   */
  public boolean send (String to, String from, String cc, String subject, String content, String filename)
  {
      String[] attachment = {filename};
      return this.sendMail(to, from, cc, null, subject, content, null, attachment);
  }


  /**
   * Initialize the class.
   */
  private void initialize()
  {
    // Get the SMTP host
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



}
