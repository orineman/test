package com.infowave.c4p.web.actions;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Sep 26, 2003
 * Time: 3:02:18 PM
 * To change this template use Options | File Templates.
 */
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public class ShowApplicationPage  extends UserAction{

  public  ActionForward executeLogic(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     List errors)
          throws Exception
  {
    // Extract attributes  we will need
    Locale locale = getLocale(request);
    MessageResources messages = getResources(request);
    HttpSession session = request.getSession();
    return (mapping.findForward("success"));
  }

}
