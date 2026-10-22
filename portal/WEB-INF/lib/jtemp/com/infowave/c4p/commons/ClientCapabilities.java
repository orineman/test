package com.infowave.c4p.commons;



import org.apache.commons.logging.LogFactory;

import org.apache.commons.logging.Log;



import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import java.util.List;

import java.util.Collections;

import java.util.Iterator;

import java.io.Serializable;



/**

 * Created by: Vincent Aumont

 * Copyright 2003 Infowave Software, Inc.

 */

/**
 * This clas is responsible for determining the client's capabilities in terms
 * of authentication mode and display mode.
 */

public class ClientCapabilities implements Serializable

 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ClientCapabilities.class);

  /** The display mode when the user first login */
  DisplayMode initialDisplay = null;

  /** The client's display capabilities, sorted (higher dispay first). */
  private List capabilities = new ArrayList(3);

  /** if the use agent contains one of these strings, we add PC to the
   * capability list and set the initial display to PC */
  private static String[] USER_AGENT_PC = {"Windows NT 51",
											"Macintosh",
											"Mac_PowerPC"};

  /** if the use agent contains one of these strings, we add PDA to the
   * capability list and set the initial display to PDA */
  private static String[] USER_AGENT_PDA = {"Windows CE",
                                            "PalmSource",
                                            "SymbianOS",
                                            "MIDP"};

  /** if the use agent contains one of these strings, we add PHONE to the
   * capability list and set the initial display to PHONE */
  private static String[] USER_AGENT_PHONE = {"WAPPER",
  																						"UP.Browser",
																						"UP.Link"
  																					 };

  /**
   * Determines the client's capapbilities by looking at the request.
   */
  public ClientCapabilities(HttpServletRequest request)
  {
    // Look at the accept header
    String accept = request.getHeader("accept");
    boolean bPC=false;
    boolean bPDA=false;
    boolean bPhone=false;
    boolean bXML=false;


    if (accept != null) {
      if  (accept.indexOf("vnd.wap") != -1) {
      	bPhone=true;
        initialDisplay = DisplayMode.PHONE;
      }

      if  (accept.indexOf("application/xml") != -1) {
      	bXML=true;
        initialDisplay = DisplayMode.XML;
      }

      if (accept.indexOf("*/*") != -1) {
      	bPC=true;
      	bPDA=true;
        if (initialDisplay == DisplayMode.PHONE)
        {
        	initialDisplay = DisplayMode.PDA;
        } else{
        	initialDisplay = DisplayMode.PC;
        }
      }
    }

    // Look at the User-agent header

    String userAgent = request.getHeader("user-agent");
    if (userAgent != null) {
      // PC user agent
      for (int i=0;i<USER_AGENT_PC.length;i++){
        if (userAgent.indexOf(USER_AGENT_PC[i]) != -1) {
          bPC=true;
          initialDisplay = DisplayMode.PC;
          break;
        }
      }
      // PDA user agent
      for (int i=0;i<USER_AGENT_PDA.length;i++){
        if (userAgent.indexOf(USER_AGENT_PDA[i]) != -1) {
          bPDA=true;
          initialDisplay = DisplayMode.PDA;
          break;
        }
      }
      // PHONE user agent
      for (int i=0;i<USER_AGENT_PHONE.length;i++){
        if (userAgent.indexOf(USER_AGENT_PHONE[i]) != -1) {
          bPhone=true;
          initialDisplay = DisplayMode.PHONE;
          break;
        }
      }
    }



    // If we don't have an initial mode at this point, we assume that the
    // client is a PC.
    if (initialDisplay == null){
     //  m_log.warn ("Cannot determine the client's capabilities - Assuming PC" +
      //            " Accept= " + accept + " User-Agent = " + userAgent);
      bPC=true;
      initialDisplay = DisplayMode.PC;
    }

	m_log.debug("ClientCapabilities: bPC = " +bPC+ ", initialDisplay = " +initialDisplay );

    if (bPhone) capabilities.add(DisplayMode.PHONE);
    if (bPDA) capabilities.add(DisplayMode.PC);
    if (bPC) capabilities.add(DisplayMode.PDA);
    if (bXML) capabilities.add(DisplayMode.XML);

    Collections.sort(capabilities);
  }



  /**
   * Does this client support this mode?
   */

  public boolean supportsMode(DisplayMode mode)
  {
    for (Iterator it = capabilities.iterator();it.hasNext();) {
      DisplayMode dm =(DisplayMode) it.next();
      if (dm.equals(mode)) {
        return true;
      }
    }
    return false;
  }



  /**
   * Whether this client can do foram-based authentication.
   */
  public boolean canDoFormAuthentication()
  {
    boolean retVal = !(DisplayMode.PHONE.equals(initialDisplay) || DisplayMode.XML.equals(initialDisplay));
    return retVal;
  }



  /**
   * Returns the initial display mode
   * @return
   */

  public DisplayMode getInitialDisplay() {
    return initialDisplay;
  }



  /**
   * Returns the supported display modes, sorted.
   */
  public List getCapabilities() {
    return capabilities;
  }

  /**
   *  Formats this object as a string.
   * @return
   */
  public String toString()
  {
    StringBuffer buff = new StringBuffer();
    buff.append("Initial Display: ").append(initialDisplay);
    buff.append(" Capabilities: ");
    for (int i=0;i<capabilities.size();i++){
      buff.append(capabilities.get(i).toString()).append(" ");
    }
    return buff.toString();
  }
}

