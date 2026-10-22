package com.infowave.c4p.web.actions;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Sep 26, 2003
 * Time: 3:02:18 PM
 * To change this template use Options | File Templates.
 */
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import com.infowave.c4p.web.util.BaseAction;

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
