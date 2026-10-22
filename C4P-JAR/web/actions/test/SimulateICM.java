package com.infowave.c4p.web.actions.test;

import com.infowave.c4p.web.util.BaseAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class SimulateICM extends BaseAction {

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(SimulateICM.class);

  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {

    String type = request.getParameter("type");
    String did = request.getParameter("did");

    // the did is add if the first digit we find in the did is odd
    boolean oddDid = true;
    char[] chars = did.toCharArray();
    for (int i=0;i<chars.length;i++){
      if (Character.isDigit(chars[i])) {
        if ((Character.getNumericValue(chars[i]) % 2) == 0){
          oddDid = false;
        } else {
          oddDid = true;
        }
      }
    }
    
    // Find the code to return
    int returnCode;
    if ("all".equals(type)) {
      returnCode = 200;
    } else if ("odd".equals(type) && oddDid) {
      returnCode = 200;
    } else if  ("even".equals(type) && !oddDid) {
      returnCode = 200;
          }
    else {
      returnCode = 404;
    }

    if (returnCode == 200) {
      m_log.debug("The desktop " + did + " is managed by icm " + type);
    } else {
      m_log.debug("The desktop " + did + " is not managed by icm " + type);
    }
    response.sendError(returnCode);

    return null;
  }

  protected boolean isAuthorized(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 ActionErrors errors) {
    return true;
  }

}
