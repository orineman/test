package com.infowave.c4p.web.actions.onyx;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.Base64;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.TechnicianDAO;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.model.Technician;
import com.infowave.c4p.util.security.PasswordEncoderFactory;

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

public class Login extends BaseAction
{
  /** The class's logger */
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.onyx.Login.class);
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
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    boolean isAuthed = authenticate( request );
    if( !isAuthed ) {
        response.setHeader("WWW-Authenticate", "Basic realm=LapLink Onyx");
        response.sendError(response.SC_UNAUTHORIZED);
	}
    return null;
  }

  public static boolean authenticate(HttpServletRequest request)
  {
        boolean isAuthed = false;
		String scheme = request.getScheme();
		if( scheme.equals("https") ) {

			String auth = request.getHeader("Authorization");
			HttpSession session = request.getSession();
			session.removeAttribute("ts_username");
			if( auth != null && auth != "" ) {
				auth = auth.substring(6);
				// check if the username password is correct
				byte[] authBytes = Base64.decode(auth);
				if( authBytes != null && authBytes.length > 0 ) {
					try {
						// break into components
						auth = new String( authBytes );
						int idx = auth.indexOf(':');
						String username = auth.substring(0,idx);
						String password = auth.substring(idx+1);
						// now check against the database
						TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
						Technician technician = technicianDAO.getByDoubleid( "siteadmin", username );
						if( technician != null ) {
		     				// user exists, now check password
 							if (validatePassword(technician,password)) {
								// set authorization status into session
								if( username.equals("ra!admin") ) {
									isAuthed = true;
									session.setAttribute("ts_username", "tsadmin");
								}
							}
						}

					} catch( Exception ignored ) {}
				}
			}
		}


       return isAuthed;

  }


  public static boolean validatePassword(Technician technician, String password)
  {
    PasswordEncoderFactory factory = PasswordEncoderFactory.instance();
    return factory.getEncoder(WebConstants.PWD_ENCODER).match(password,technician.getPassword());

  }


    protected boolean isAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse, ActionErrors actionerrors)
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



}
