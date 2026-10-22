package com.infowave.c4p.web.actions;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.UrlFetcher;
import com.infowave.c4p.web.util.WebConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Oct 19, 2003
 * Time: 9:37:09 PM
 * To change this template use Options | File Templates.
 */
public class Process404Error extends BaseAction
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(Process404Error.class);
  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs
   * @return a forward
   * @throws Exception

   */
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    // let the framework pick the forward.
    m_log.debug(request.getRequestURI());
    m_log.debug(request.getRequestURL());

   // If there is no desktop currently selected but this url contains a did,
      // it means that the user has clicked on a desktop link after the session
      // has expired.
      // Note: this assumes that all request that should be protected contains
      // a did. If this is not thje case, we should ask the desktop to make all
      // the urls it generates start with '/protected' (as defined in web.xml).
      // Note that we might have problems with multipart post requests as
      //  won't work.
      if (request.getParameter(WebConstants.DID) != null){
        m_log.debug("This request contains a did - sending to login page");
        response.sendRedirect(WebConstants.ACTION_LOGIN_PAGE);
        response.flushBuffer();
      }
    return null;

  }
  /**
   * Checks that this action is authorized.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return true if this action can be executed.
   */
  protected boolean isAuthorized(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 ActionErrors errors)
  {
    return true;
  }
}
