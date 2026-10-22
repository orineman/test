package com.infowave.c4p.web.actions.teksupport;

import com.infowave.c4p.admin.LicenseGenerator;
import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class LicenseGenerate extends BaseAction
{
    private static final int MAX_ARGS = 18;
  /** The class's logger */
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.teksupport.LicenseGenerate.class);
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
        ActionForward forward = null;
	LicenseGeneratorForm form = (LicenseGeneratorForm) actionForm;

        if( !isAuthorized(request) ) {
         	response.sendError(response.SC_NOT_FOUND);
        } else if( !form.iscleared() ) {
	SessionState state = WebUtil.getSessionState(request);  
	// Generate the licenses
	String filename = "/lle/licenses/list_" + new Date().getTime();
	LicenseGenerator licGen = new LicenseGenerator();
	int idx = 0;
	String [] args = new String[MAX_ARGS];
	args[idx++] = "-" +licGen.FIXED_OPT;			// -fixed	
	args[idx++] = "-" +licGen.MAX_USES_OPT;		// -maxuses 100
	args[idx++] = form.getMaxUses();				// -maxuses 100
	if( !form.getMaxUses().equals( "1") ) {
		// We have a regcode request
		args[idx++] = "-" +licGen.REG_CODE_OPT;
          }
	args[idx++] = "-" +licGen.NB_LICENSES_OPT;		// -nb 10
	args[idx++] = form.getTotalCount();			// -nb 10
	args[idx++] = "-" +licGen.DURATION_OPT;		// -duration 365
	args[idx++] = form.getDays();				// -duration 365
	args[idx++] = "-" +licGen.OUTPUT_OPT;		// -output filename
	args[idx++] = filename;					// -output filename
	args[idx++] = "-" +licGen.EMAIL_OPT;			// -sendto name@co.com
	args[idx++] = form.getEmailAddress();			// -sendto name@co.com
	args[idx++] = "-" +licGen.RESELLER_OPT;		// -reseller laplink
	args[idx++] = "laplink";					// -reseller laplink
	args[idx++] = "-" +licGen.MSG_OPT;			// -msg email text
	args[idx++] = form.getEmailText();			// -msg email text



	if( form.isRC() ) {
		args[idx++] = "-" +licGen.RC_OPT;		// -rc	
          }
	if( !form.isSubscription() ) {
 		args[idx++] = "-" +licGen.BOX_OPT;		// -box	
         }
	while( idx != MAX_ARGS ) {
		args[idx++] = "";
	}
	licGen.doit( args );
	// email the results
      	//Notifier.instance().notifyAccountCreation(state.getReseller().getName(),
         //                                       form.getEmailAddress(), state.getDefaultLocale());
        }
        //forward = mapping.findForward("success.pc");
        forward  = super.findForward(request,mapping,"success.pc");

		form.clear();
        return forward;
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

       return isAuthed;
   }

    protected boolean isAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse, ActionErrors actionerrors)
    {
	return isAuthorized( request );
    }



}
