package com.infowave.c4p.web.actions;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class SearchForTechnician extends BaseAction
{
  /** The class's logger */
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.SearchForTechnician.class);
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
    String forwardName = null;
    if( !isAuthorized(request) ) {
        response.sendError(response.SC_NOT_FOUND);
    } else {
		TechnicianForm form = (TechnicianForm) actionForm;

		// Get the accesslevel of the logged in user to double check
		// rights 
		SessionState state = WebUtil.getSessionState(request);
		form.setLoggedinUser(state.getTechnicianDTO());

   		// Validate the input parameters
   		List validateErrors = form.validateData(mapping,request);
   		if (validateErrors.isEmpty()) {
			// good to go ahead 
			form.findTechnician(errors);
			request.setAttribute("techniciandata", form);
			forwardName = form.getNextaction();
		}
    }
    return mapping.findForward(forwardName);
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
