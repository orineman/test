package com.infowave.c4p.web.util;

import com.infowave.c4p.web.util.WebConstants;
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

//import javax.activation.*;

public class Notifier
{
  private static final String FORGOT_PWD_SUBJECT_KEY = "mail.forgotPwdSubject";
  private static final String FORGOT_PWD_BODY_KEY = "mail.forgotPwdBody";
  private static final String FORGOT_PWD_FROM_KEY = "mail.forgotPwdFrom";

  private static final String NEW_ACCOUNT_SUBJECT_KEY = "mail.newAccountSubject";
  private static final String NEW_ACCOUNT_BODY_KEY = "mail.newAccountBody";
  private static final String NEW_ACCOUNT_FROM_KEY = "mail.newAccountFrom";
  private static final String NEW_ACCOUNT_BCC_KEY = "mail.newAccountBcc";
  private static final String NEW_ACCOUNT_TECHNICIANID_LABEL_KEY = "mail.newAccountTechnicianidLabel";
  private static final String NEW_ACCOUNT_COMPANYID_LABEL_KEY = "mail.newAccountCompanyidLabel";
  private static final String NEW_ACCOUNT_PASSWORD_LABEL_KEY = "mail.newAccountPasswordLabel";
  private static final String NEW_ACCOUNT_FOOTER_KEY = "mail.newAccountFooter";

  private static final String NEWUNUSED_ACCOUNT_SUBJECT_KEY = "mail.newUnusedAccountSubject";
  private static final String NEWUNUSED_ACCOUNT_FROM_KEY = "mail.newUnusedAccountFrom";
  private static final String NEWUNUSED_ACCOUNT_TO_KEY = "mail.newUnusedAccountTo";


  /** The class's logger */
  private static Log m_log = LogFactory.getLog(Notifier.class);

  /** The class' unique instance */
  private static Notifier m_instance = null;

  // ----------------------------------------------------------- Public methods
  public void notifyNewUnusedAccounts(String body, Locale locale)
  {
	  StringManager stringMgrBasic;
	  // Use this block to pull this info from the messages.properties file
	  if (locale != null)
	  {
		  stringMgrBasic = StringManager.getManager(WebConstants.DEFAULT_MESSAGES_FILE, locale);
	  }
	  else
	  {
		  stringMgrBasic = StringManager.getManager(WebConstants.DEFAULT_MESSAGES_FILE);
	  }
	  if (stringMgrBasic == null)
	  {
		  m_log.error("notifyNewUnusedAccounts() Cannot send mail- no String manager.");
		  return;
	  }

	  String subject = stringMgrBasic.getString(NEWUNUSED_ACCOUNT_SUBJECT_KEY);
	  String from = stringMgrBasic.getString(NEWUNUSED_ACCOUNT_FROM_KEY);
	  String to = stringMgrBasic.getString(NEWUNUSED_ACCOUNT_TO_KEY);

	  send(to, from, null, subject, body);

  }

  public void notifyNewAccount(String companyid, String technicianid, String pwd, String to, Locale locale)
  {
    StringManager stringMgr;
    StringManager stringMgrBasic;
	// Use this block for per company configuration
    if (locale != null) {
      stringMgr = StringManager.getManager(companyid,locale);
    } else {
      stringMgr = StringManager.getManager(companyid);
    }
	// Use this block to pull this info from the messages.properties file
	if (locale != null) {
	  stringMgrBasic = StringManager.getManager(WebConstants.DEFAULT_MESSAGES_FILE,locale);
	} else {
	  stringMgrBasic = StringManager.getManager(WebConstants.DEFAULT_MESSAGES_FILE);
	}
    if (stringMgr == null && stringMgrBasic == null){
      m_log.error("Cannot send mail- no String manager for companyid: " + companyid);
      return;
    }

    String subject = stringMgr.getString(NEW_ACCOUNT_SUBJECT_KEY);
	if( subject.equals(NEW_ACCOUNT_SUBJECT_KEY) ) {
		subject = stringMgrBasic.getString(NEW_ACCOUNT_SUBJECT_KEY);
	}

    String body = stringMgr.getString(NEW_ACCOUNT_BODY_KEY);
	if( body.equals(NEW_ACCOUNT_BODY_KEY) ) {
		body = stringMgrBasic.getString(NEW_ACCOUNT_BODY_KEY);
	}

    String label = stringMgr.getString(NEW_ACCOUNT_COMPANYID_LABEL_KEY);
	if( label.equals(NEW_ACCOUNT_COMPANYID_LABEL_KEY) ) {
		label = stringMgrBasic.getString(NEW_ACCOUNT_COMPANYID_LABEL_KEY);
	}
	body += label +" "+ companyid;

    label = stringMgr.getString(NEW_ACCOUNT_TECHNICIANID_LABEL_KEY);
	if( label.equals(NEW_ACCOUNT_TECHNICIANID_LABEL_KEY) ) {
		label = stringMgrBasic.getString(NEW_ACCOUNT_TECHNICIANID_LABEL_KEY);
	}
	body += label +" "+ technicianid;

    label = stringMgr.getString(NEW_ACCOUNT_PASSWORD_LABEL_KEY);
	if( label.equals(NEW_ACCOUNT_PASSWORD_LABEL_KEY) ) {
		label = stringMgrBasic.getString(NEW_ACCOUNT_PASSWORD_LABEL_KEY);
	}
	body += label +" "+ pwd;

    String footer = stringMgr.getString(NEW_ACCOUNT_FOOTER_KEY);
	if( footer.equals(NEW_ACCOUNT_FOOTER_KEY) ) {
		footer = stringMgrBasic.getString(NEW_ACCOUNT_FOOTER_KEY);
	}
	body += footer;

    String from = stringMgr.getString(NEW_ACCOUNT_FROM_KEY);
	if( from.equals(NEW_ACCOUNT_FROM_KEY) ) {
		from = stringMgrBasic.getString(NEW_ACCOUNT_FROM_KEY);
	}

    String bcc = stringMgr.getString(NEW_ACCOUNT_BCC_KEY);
	if( bcc.equals(NEW_ACCOUNT_BCC_KEY) ) {
		bcc = stringMgrBasic.getString(NEW_ACCOUNT_BCC_KEY);
	}

	if( to == null || to.length() == 0 ) {
		to = bcc;
	}

    send(to,from,bcc,subject,body);
  }


  public void notifyPasswordReset(String companyid, String to, String newpwd, Locale locale)
  {
    StringManager stringMgr;
    StringManager stringMgrBasic;
	// Use this block for per company configuration
    if (locale != null) {
      stringMgr = StringManager.getManager(companyid,locale);
    } else {
      stringMgr = StringManager.getManager(companyid);
    }
	// Use this block to pull this info from the messages.properties file
	if (locale != null) {
	  stringMgrBasic = StringManager.getManager(WebConstants.DEFAULT_MESSAGES_FILE,locale);
	} else {
	  stringMgrBasic = StringManager.getManager(WebConstants.DEFAULT_MESSAGES_FILE);
	}
    if (stringMgr == null && stringMgrBasic == null){
      m_log.error("Cannot send mail- no String manager for companyid: " + companyid);
      return;
    }

    String subject = stringMgr.getString(FORGOT_PWD_SUBJECT_KEY);
	if( subject.equals(FORGOT_PWD_SUBJECT_KEY) ) {
		subject = stringMgrBasic.getString(FORGOT_PWD_SUBJECT_KEY);
	}
    String body = stringMgr.getString(FORGOT_PWD_BODY_KEY);
	if( body.equals(FORGOT_PWD_BODY_KEY) ) {
		body = stringMgrBasic.getString(FORGOT_PWD_BODY_KEY);
	}
	body += newpwd;
    String from = stringMgr.getString(FORGOT_PWD_FROM_KEY);
	if( from.equals(FORGOT_PWD_FROM_KEY) ) {
		from = stringMgrBasic.getString(FORGOT_PWD_FROM_KEY);
	}

    send(to,from,null,subject,body);
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
  public void send (String to, String from, String bcc, String subject, String content)
  {
    try {
      Properties props = System.getProperties();
      Session session = Session.getDefaultInstance(props, null);
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to, false));
	  if( bcc != null ) {
		  msg.setRecipients(Message.RecipientType.BCC,
							InternetAddress.parse(bcc, false));
	  }
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
