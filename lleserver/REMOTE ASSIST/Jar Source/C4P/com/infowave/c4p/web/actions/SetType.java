package com.infowave.c4p.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.List;

public class SetType extends BaseAction
{
  /** The class's logger */
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.SetType.class);
    }

	private static final String LOGINERROR = "loginerror";

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
        ActionForward next = null;

        if( isAuthorized(request) ) {
			String forwardName = request.getQueryString();
			if( forwardName != null ) {
				int idx = forwardName.indexOf("type=");
				if( idx != -1 ) {
					forwardName = forwardName.substring( idx+5 );
				}
				idx = forwardName.indexOf( "&" );
				if( idx != -1 ) {
					forwardName = forwardName.substring(0, idx);
				}
				if( LOGINERROR.equals(forwardName) ) {
					response.sendRedirect(response.encodeRedirectURL(WebConstants.RASSIST + WebConstants.LOGIN_ERR));
					next = mapping.findForward(WebConstants.FWD_DUMMY_PAGE);
				} else {
    	     		next = mapping.findForward(forwardName);
				}
			}
		} else {
         	response.sendError(response.SC_NOT_FOUND);
		}
        return next;
  }

 
  private boolean isAuthorized( HttpServletRequest request ) 
  {
       return true;
  }

  protected boolean isAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse, ActionErrors actionerrors)
  {
	return isAuthorized( request );
  }



}
