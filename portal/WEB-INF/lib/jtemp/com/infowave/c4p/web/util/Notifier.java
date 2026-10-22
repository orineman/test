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
  private static final String NEW_ACCOUNT_SUBJECT_KEY = "mail.newAccountSubject";
  private static final String NEW_ACCOUNT_BODY_KEY = "mail.newAccountBody";
  private static final String NEW_ACCOUNT_FROM_KEY = "mail.newAccountFrom";
  private static final String LICENSE_SUBJECT_KEY = "mail.newLicensesSubject";
  private static final String LICENSE_BODY_KEY = "mail.newLicensesBody";
  private static final String LICENSE_FROM_KEY = "mail.newLicensesFrom";
  private static final String LICENSE_CC_KEY = "mail.newLicensesCC";
  private static final String LICENSE_TO_KEY = "mail.newLicensesTo";

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(Notifier.class);

  /** The class' unique instance */
  private static Notifier m_instance = null;

  // ----------------------------------------------------------- Public methods

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
   * Sends a message.
   */
  public void send (String to, String from, String subject, String content)
  {
    try {
      Properties props = System.getProperties();
      Session session = Session.getDefaultInstance(props, null);
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to, false));
      msg.setSubject(subject);
      msg.setText(content);
      msg.setHeader("X-Mailer", "MsgSend");
      msg.setSentDate(new Date());
      Transport.send(msg);
    }
    catch (Exception e) {
      String msg = "Cannot send message from: " + from + " to: " + to;
      m_log.warn(msg,e);
    }
  }

  /* Send with an attachment
   */
  public void send (String to, String from, String cc, String subject, String content, String filename)
  {
    try {
      Properties props = System.getProperties();
      Session session = Session.getDefaultInstance(props, null);
      Message msg = new MimeMessage(session);
      MimeMultipart mmp = new MimeMultipart();
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setContent(content, "text/plain");
      mmp.addBodyPart(mbp1);

      MimeBodyPart mbp2 = new MimeBodyPart();

      FileDataSource fds = new FileDataSource( filename );
      DataHandler dh = new DataHandler( fds );

      mbp2.setDataHandler(dh);
      mbp2.setFileName( filename );
      mmp.addBodyPart( mbp2 );
      msg.setContent( mmp );

      msg.setFrom(new InternetAddress(from));
      // list must be comma separated if more than one recipient specified
      msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to, false));
      if( cc != null && cc.length() > 0 )
              msg.setRecipients(Message.RecipientType.CC,
                        InternetAddress.parse(cc, false));
      msg.setSubject(subject);
      msg.setHeader("X-Mailer", "MsgSend");
      msg.setSentDate(new Date());
      msg.saveChanges();
      Transport.send(msg);
    }
    catch (Exception e) {
      String msg = "Cannot send message from: " + from + " to: " + to;
      m_log.warn(msg,e);
    }
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
