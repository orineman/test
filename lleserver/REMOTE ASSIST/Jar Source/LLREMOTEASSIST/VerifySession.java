
package llremoteassist;

import java.util.List;

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


public class VerifySession
    extends HDHttpServlet
{

    public VerifySession()
    {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
		int respCode = HttpServletResponse.SC_NO_CONTENT; // 204
		String query = req.getQueryString();
		m_log.debug(query);
		// Now get the various parameters
		try {
			//String companyid = super.getQueryParam( query, LLRAConstants.CO_TOKEN, 
			//		LLRAConstants.CO_TOKEN_LEN, LLRAConstants.ERRMSG_CO);
			String techcid = super.getQueryParam( query, LLRAConstants.TCID_TOKEN, 
					LLRAConstants.TCID_TOKEN_LEN, LLRAConstants.ERRMSG_TCID);
			String did = super.getQueryParam( query, LLRAConstants.DID_TOKEN, 
					LLRAConstants.DID_TOKEN_LEN, LLRAConstants.ERRMSG_DID);
			String name = super.getQueryParam( query, LLRAConstants.NAME_TOKEN, 
					LLRAConstants.NAME_TOKEN_LEN, LLRAConstants.ERRMSG_NAME);

			// Make sure params are correct length
			//super.validateCompanyid( companyid );
			super.validateTechcid( techcid );
			super.validateDid( did );
			super.validateName( name );

			// See if we're going to allow this session
			TechloginDTO activeloginDto = null;
			SessionManager sm = DelegateFactory.getSessionManager(); 
			SessionDTO sessionDto = null;
			TechloginManager tlm = DelegateFactory.getTechloginManager(); 
			if( sm != null && tlm != null ) {
				// See if the technician is actively logged in
			    List activeloginDtoList = tlm.getActiveByTechcid(techcid);
				if( activeloginDtoList.size() <= 0 ) {
					throw new BusyException( "technician not logged in" );
				}
				if(activeloginDtoList.size() > 1) {
					throw new BusyException( "technician has too many active logins" );
				}
				activeloginDto = (TechloginDTO)activeloginDtoList.get(0);
				//
				sessionDto = sm.getAvailableByTripleid(activeloginDto.getCompanyid(), techcid, did );
				if( sessionDto == null ) {
					throw new NoCanFindException( "available session tripleid" );
				}
				// See if this technician has this did assigned to them and
				// update the record if they do.
				sessionDto = sm.update(activeloginDto.getCompanyid(), techcid, did, name );
				if( sessionDto == null ) {
					throw new NoCanFindException( "session tripleid" );
				}
			} else {
				throw new WayBadException( "SessionManager/TechloginManager MIA" );
			}

		// Error Responses:
		// 400 -- format of request was bad
		// 403 -- tech not logged in or data is bad (e.g., did was mistyped)
		// 500 -- internal server, misc error
		//
		} catch( IckyRequestException icke ) {
			m_log.debug("IckyRequestException: " +icke.toString() );
			respCode = HttpServletResponse.SC_BAD_REQUEST;		// 400
		} catch( NoCanFindException ncfe ) {
			m_log.debug("NoCanFindException: " +ncfe.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN;		// 403
		} catch( ServiceException se ) {
			m_log.debug("ServiceException: " +se.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN;		// 403
		} catch( WayBadException wbe ) {
			m_log.debug("WayBadException: " +wbe.toString() );
			respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;	// 500 
		} catch( BusyException wbe ) {
			m_log.debug("BusyException: " +wbe.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN;		// 403 
		} catch( Exception eek ) {
			m_log.debug("Exception: " +eek.toString() );
			respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;		// 500 
		}
		// Check if this is a valid session
		m_log.debug("respCode = " +respCode );
		super.setResponseCode( respCode, resp );
    }


    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llremoteassist.VerifySession.class);
    }



}