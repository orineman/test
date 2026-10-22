package com.infowave.c4p.web.actions.onyx;

import com.infowave.c4p.web.util.BaseAction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.apache.struts.action.*;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class CustomerInfo extends BaseAction
{
  /** The class's logger */
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.onyx.CustomerInfo.class);
    }

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
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
       ActionForward next = null;

       if( !isAuthorized(request) ) {
		m_log.error("unauthorized access attempt");
        response.sendError(response.SC_NOT_FOUND);
       } else {
     	OnyxForm form = (OnyxForm) actionForm;

		try {
   			// Validate the input parameters
   			form.validateData(mapping,request);

			// good to go ahead 
			form.performAction();
			response.sendError(response.SC_OK);

		} catch( OnyxException oe ) {
			m_log.debug("OnyxException = " +oe.toString() );
         	response.sendError(response.SC_NOT_FOUND);
		}

       }

       return next;
  }

 
  private boolean isAuthorized( HttpServletRequest request ) 
  {
    // check session for authorization status
    boolean isAuthed = false;
    HttpSession session = request.getSession();

    String attr = (String)session.getAttribute("ts_username");
    if( attr != null && attr != "" ) {
		isAuthed = attr.equals("tsadmin");
    }

    if( !isAuthed ) {
		isAuthed = Login.authenticate( request );
    }

    return isAuthed;
   }

   protected boolean isAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse, ActionErrors actionerrors)
   {
	   return isAuthorized( request );
   }


}
