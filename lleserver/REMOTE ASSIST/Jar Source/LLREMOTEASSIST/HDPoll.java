
package llremoteassist;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infowave.c4p.service.delegate.TechloginManager;
import com.infowave.c4p.service.delegate.SessionManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.SessionDTO;
import com.infowave.c4p.service.dto.TechloginDTO;

public class HDPoll
    extends HDHttpServlet
{

    public HDPoll() 
    {
    }
  
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llremoteassist.HDPoll.class);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
		int respCode = HttpServletResponse.SC_FORBIDDEN; // 403
		String hdrLoginid = req.getHeader( LLRAConstants.LOGINID );
		m_log.debug("hdrLoginid = " +hdrLoginid);
		String uri = req.getRequestURL().toString();
		m_log.debug("request = " +uri);
		// Now get the various parameters
		try {
			// See what type of poll this is
			if( uri.indexOf( LLRAConstants.LOGIN_STATUS ) != -1 ) {
				String query = req.getQueryString();
				m_log.debug(query);
				String did = super.getQueryParam( query, LLRAConstants.DID_TOKEN, 
					LLRAConstants.DID_TOKEN_LEN, LLRAConstants.ERRMSG_DID) ;

				// Make sure did is correct length
				super.validateDid( did );

				// Get the data managers
				TechloginManager tlm = DelegateFactory.getTechloginManager(); 
				SessionManager sm = DelegateFactory.getSessionManager(); 
				SessionDTO sessionDto = null;
				TechloginDTO techloginDto = null;
				if( sm == null ) {
					throw new WayBadException( "SessionManager MIA" );
				}
				if( tlm == null ) {
					throw new WayBadException( "TechloginManager MIA" );
				}

				// See if we have an active techlogin
				techloginDto = tlm.getActiveTechloginByLoginid( Double.parseDouble(hdrLoginid) );
				if( techloginDto == null ) {
					throw new NoCanFindException( "stale loginid" );
				}

				// Get data from session table
				sessionDto = sm.getByTripleid( techloginDto.getCompanyid(), techloginDto.getTechcid(), did );
				if( sessionDto == null ) {
					throw new IckyRequestException( "can't find did" );
				}

				// Got the session info
				if( !sessionDto.isLoggedIn() ) {
					respCode = HttpServletResponse.SC_NO_CONTENT; // 204
				} else {
					// Need to return login info back to HelpDesk Client
					try {
						resp.setContentType("text/plain; charset=utf-8");
						ServletOutputStream sos = resp.getOutputStream();
						if( sos != null ) {
							sos.print(sessionDto.getName());
							sos.close();
							respCode = HttpServletResponse.SC_OK;  // 200
						} else {
							throw new IOException( "no outputstream" );
						}
					} catch( IOException ioe ) {
						respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; // 500
					}
				}


			} else if( uri.indexOf( LLRAConstants.STATUS ) != -1 ) {
				// See if we have an active techlogin
				TechloginManager tlm = DelegateFactory.getTechloginManager(); 
				if( tlm == null ) {
					throw new WayBadException( "TechloginManager MIA" );
				}
				int logincount = tlm.countActiveTechloginsByLoginid( Double.parseDouble(hdrLoginid) );
				if( logincount == 0 ) {
					throw new NoCanFindException( "stale loginid" );
				}
				respCode = HttpServletResponse.SC_NO_CONTENT; // 204
			}



		// Error Responses:
		// 400 -- format of request was bad
		// 403 -- tech not logged in or data is bad (e.g., did can't be found)
		// 500 -- internal server, misc error
		//
		} catch( NoCanFindException ncfe ) {
			m_log.debug("NoCanFindException: " +ncfe.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN;		// 403
		} catch( IckyRequestException ire ) {
			m_log.debug("IckyRequestException: " +ire.toString() );
			respCode = HttpServletResponse.SC_BAD_REQUEST;		// 400
		} catch( ServiceException se ) {
			m_log.debug("ServiceException: " +se.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN;		// 403
		} catch( WayBadException wbe ) {
			m_log.debug("WayBadException: " +wbe.toString() );
			respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;		// 500 
		} catch( Exception eek ) {
			m_log.debug("Exception: " +eek.toString() );
			respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;		// 500 
		}
		super.setResponseCode( respCode, resp );
	}


}


