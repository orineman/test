package com.infowave.c4p.web.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSession;

import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.dto.DesktopDTO;

import java.util.Iterator;

/**
 * This class is responsible for notifying teh ICMs when a session terminates
 * (either because of teh user logs out or because the session expires).
 */
public class SessionTerminationNotifier implements HttpSessionBindingListener
{
  private static final int TIMEOUT = 60 * 1000;

  /** The class's logger */
  private static Log m_log
    = LogFactory.getLog(SessionTerminationNotifier.class);

  private SessionState m_state;

  public SessionTerminationNotifier(HttpServletRequest request,
                                    SessionState state){
    m_log.debug("Creating nofifier for session: " + request.getSession().getId());
    m_state = state;
  }

  public void valueBound(HttpSessionBindingEvent event){}

  public void valueUnbound(HttpSessionBindingEvent event)
  {
    HttpSession session = event.getSession();
    m_log.debug("session terminated: " + session.getId());
    UserDTO user = m_state.getUserDTO();
    if ((m_state != null) && (m_state.getUserDesktops() != null)){
      for (Iterator it = m_state.getUserDesktops().iterator();it.hasNext();){
        DesktopDTO desktop = (DesktopDTO) it.next();
        String url = null;
        url = desktop.getLogoutURL();
        if (url != null) {
          String password = "ignored";
          NotifierWorkerThread worker = new NotifierWorkerThread(url,
                                                                 user.getUsername(),
                                                                 password);
          worker.start();
        }
      }
    } else {
      //m_log.debug("no desktops for session: " + session.getId());
    }
  }

  // ---------------------------------------------- NotifierWorkerThread
   public class NotifierWorkerThread extends Thread {
    private String  m_url = null;
    private String m_username;
    private String m_password;
    private boolean m_done = false;
    private HttpMethod m_method = null;

    NotifierWorkerThread(String url,String username,String password)
    {
      m_url = url;
      m_username = username;
      m_password = password;
    }

    public boolean isDone() {
      return m_done;
    }
    public void terminate()
    {
      try {m_method.releaseConnection();} catch(Exception e){}
    }

    public synchronized void run() {
      try {
        String authorization;
        authorization = "Basic " + Base64Encoder.encode(m_username + ":" + m_password);
        m_method = new GetMethod(m_url);
        m_method.setRequestHeader("Authorization",authorization);
        HttpClient client = new HttpClient();
        client .setTimeout(TIMEOUT);
        m_log.debug("calling " + m_url);
        int res = client.executeMethod(m_method);
        m_log.debug("called " + m_url + " with return code: " + res);
      }
      catch(Exception e) {
        m_log.info("Cannot call logout url: " + m_url + " - " + e.getMessage());
      }
      finally{
        try {m_method.releaseConnection();} catch(Exception e){}
      }
    }
  }
}
