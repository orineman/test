package com.infowave.c4p.web.security;

import com.infowave.c4p.commons.DisplayMode;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated
 */

public class C4PDisplayModeValve extends ValveBase
    implements Lifecycle
{
  /** This valve's debug level */
  int debug = 0;

  /** The request attribute in which we store the client's capabilities */
  private static String CAPABILITIES_ATTR = "infowave.client.capablities";

  /** The request attribute in which we store the client's initial display mode */
  private static String INITIAL_DISPLAY_ATTR = "infowave.client.initial.display";


  /** if the use agent contains one of these strings, we add PC to the
   * capability list and set the initial display to PC */
  private static String[] USER_AGENT_PC = {"Windows NT 51"};

  /** if the use agent contains one of these strings, we add PDA to the
   * capability list and set the initial display to PDA */
  private static String[] USER_AGENT_PDA = {"Windows CE",
                                           "PPC",
                                           "PalmSource",
                                           "SymbianOS",
                                           "MIDP"};

  /** if the use agent contains one of these strings, we add PHONE to the
   * capability list and set the initial display to PHONE */
  private static String[] USER_AGENT_PHONE = {"WAPPER"};

  public int getDebug() {return debug;}
  public void setDebug(int debug) {this.debug = debug;}

  /**
   * Analyze the request and store the client's capabilities and initial display
   * a requestparametes.
   *
   * @param req
   * @param response
   * @throws IOException
   * @throws ServletException
   */
  public void invoke(Request req, Response response) throws IOException, ServletException
  {
    if (req instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest)req;
      // no need to redo it if
      if (request.getAttribute(CAPABILITIES_ATTR) != null) {
        DisplayMode initialDisplay = null;
        List capabilities = new ArrayList(3);

        // Look at the accept header
        String accept = request.getHeader("accept");
        if (accept != null) {
          if  (accept.indexOf("vnd.wap") != -1) {
            capabilities.add(DisplayMode.PHONE);
            initialDisplay = DisplayMode.PHONE;
          }
          if (accept.indexOf("*/*") != -1) {
            capabilities.add(DisplayMode.PC);
            capabilities.add(DisplayMode.PDA);
            initialDisplay = DisplayMode.PDA;
          }
        }
        // Look at the User-agent header
        String userAgent = request.getHeader("user-agent");
        if (userAgent != null) {
          // PC user agent
          for (int i=0;i<USER_AGENT_PC.length;i++){
            if (userAgent.indexOf(USER_AGENT_PC[i]) != -1) {
              capabilities.add(DisplayMode.PC);
              initialDisplay = DisplayMode.PC;
              break;
            }
          }
          // PDA user agent
          for (int i=0;i<USER_AGENT_PDA.length;i++){
            if (userAgent.indexOf(USER_AGENT_PDA[i]) != -1) {
              capabilities.add(DisplayMode.PDA);
              initialDisplay = DisplayMode.PDA;
              break;
            }
          }
          // PHONE user agent
          for (int i=0;i<USER_AGENT_PHONE.length;i++){
            if (userAgent.indexOf(USER_AGENT_PHONE[i]) != -1) {
              capabilities.add(DisplayMode.PHONE);
              initialDisplay = DisplayMode.PHONE;
              break;
            }
          }
        }

        // Store this information in the request

        request.setAttribute(CAPABILITIES_ATTR,capabilities);
        request.setAttribute(INITIAL_DISPLAY_ATTR,initialDisplay);
      }

      }
    // Pass this request on to the next valve in our pipeline
    getNext().invoke(req, response);
  }

  public void addLifecycleListener(LifecycleListener lifecycleListener)
  {
  }

  public LifecycleListener[] findLifecycleListeners()
  {
    return new LifecycleListener[0];
  }

  public void removeLifecycleListener(LifecycleListener lifecycleListener)
  {
  }

  public void start() throws LifecycleException
  {
  }

  public void stop() throws LifecycleException
  {
  }
 // ------------------------------------------------------------- Helper methods
 /**
  * Logs a message
  * todo implement a real logger
  * @param msg
  */
  private void log(String msg)
 {
   System.out.println(msg);
 }
}

