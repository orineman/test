package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.infowave.c4p.web.util.BaseAction;

public class ShowMainPage extends UserAction
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(ShowMainPage.class);

  public  ActionForward executeLogic(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     List errors)
          throws Exception
  {
    // We're going to leave this in here as a place holder in case we need to refresh
	// or set things before bringing up the welcome page for the user.
    return null;
  }

}
