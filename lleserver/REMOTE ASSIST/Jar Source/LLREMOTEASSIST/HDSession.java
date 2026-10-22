
package llremoteassist;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infowave.c4p.service.delegate.TechnicianManager;
import com.infowave.c4p.service.delegate.TechloginManager;
import com.infowave.c4p.service.delegate.CompanyManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.service.dto.TechloginDTO;
import com.infowave.c4p.service.dto.TechnicianDTO;

import java.util.List;
import java.util.Iterator;

// This servlet handles the login & logout of the Help Desk Technicians
public class HDSession
    extends HDHttpServlet
{

    public HDSession() 
    {
    }
  
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
		int respCode = HttpServletResponse.SC_NO_CONTENT; // 204
		String uri = req.getRequestURL().toString();
		m_log.debug("request = " +uri);
		try {
			// Check if this is a login or logout. Note that something unidentified
			// gets a 204 returned. 
			if( uri.indexOf( LLRAConstants.LOGOUT ) != -1 ) {
				// logout, get the id of the techlogin
				String hdrLoginid = req.getHeader( LLRAConstants.LOGINID );
				m_log.debug("hdrLoginid = " +hdrLoginid);
				// the only thing we return a bad status on is
				// a 500 server error (such as can't access the db)
				if( hdrLoginid != null ) {
					// Update the techlogin table
					TechloginManager tlm = DelegateFactory.getTechloginManager(); 
					if( tlm == null ) {
						throw new WayBadException( "TechloginManager MIA" );
					}
					// Logout this technician
					TechloginDTO loginDto = tlm.logoutByLoginid(Double.parseDouble(hdrLoginid));
				}
			} else if( uri.indexOf( LLRAConstants.LOGIN ) != -1 ) {
				// a login, we need to get the query parameters & password
				String query = req.getQueryString();
				m_log.debug(query);
				String companyid = super.getQueryParam( query, LLRAConstants.CO_TOKEN, 
					LLRAConstants.CO_TOKEN_LEN, LLRAConstants.ERRMSG_CO);
				String techid = super.getQueryParam( query, LLRAConstants.TECHID_TOKEN, 
					LLRAConstants.TECHID_TOKEN_LEN, LLRAConstants.ERRMSG_TECHID);

				// the password is the message body
				String password = readPassword(req);
				try {
					byte[] item_b = companyid.getBytes( "8859_1" );
					companyid = new String(item_b);
					byte[] item_b1 = techid.getBytes( "8859_1" );
					techid = new String(item_b1);
				} catch( Exception eek ) { }

				// verify the lengths of the input data
				validateCompanyid(companyid);
				validateTechid(techid);
				validatePassword(password);

				// Get the data managers
				TechnicianManager tm = DelegateFactory.getTechnicianManager(); 
				TechloginManager tlm = DelegateFactory.getTechloginManager(); 
				CompanyManager cm = DelegateFactory.getCompanyManager(); 
				if( tm == null || tlm == null || cm == null ) {
					String msg = "";
					if( tm == null ) msg += "TechnicianManager ";
					if( tlm == null ) msg += "TechloginManager ";
					if( cm == null ) msg += "CompanyManager ";
					throw new WayBadException( msg+ "MIA" );
				}

				// Get the company info
				CompanyDTO companyDto = cm.getByCompanyid( companyid );
				if( companyDto == null ) {
					throw new NoCanFindException( "bad companyid" );
				}

				// Get the technician info -- make sure they are active
				TechnicianDTO technicianDto = tm.getActiveByDoubleid( companyid, techid );
				if( technicianDto == null ) {
					throw new NoCanFindException( "bad technician (bad techid or not active)" );
				}

				// Get the current number of active technician logins for this company
				int numActiveLogins = tlm.countActiveByCompanyid( companyid );

				// Check if this technician can go ahead
				TechloginDTO techloginDto = null;

				// password matches?
				if( !technicianDto.doPasswordsMatch( password ) ) {
					throw new NoCanFindException( "passwords mismatch" );
				}

				// number of techlogins is under limit?
				int coMaxConns = companyDto.getMaxconns();
				if( coMaxConns <= 0 ) {
					// denied! company not active
					throw new NoCanFindException( "Company has no login priviledges" );
				}

				// Check if this technician is already logged in
				// Should only be at most a single active login per tech
				// We log them out here as we're going to give them a new login
				List techloginDTOList = tlm.getActiveByDoubleid( companyid, technicianDto.getTechcid() );
				for(Iterator it = techloginDTOList.iterator(); it.hasNext();)
				{
					techloginDto = (TechloginDTO)it.next();
					techloginDto = tlm.logoutByLoginid(techloginDto.getLoginid(), TechloginDTO.FORCED_LOGOUT_FLAG);
				}

				// We deny this login if the company is over the maximum number of active logins
				// and this user did not hold one of those logins at the time this request came 
				// into the server
				if( numActiveLogins >= coMaxConns ) {
					if( techloginDTOList.size() == 0 ) {
						// denied! company at techlogin limit and technician was not actively logged in
						throw new BusyException( "maximum login count" );
					}
				}

				// Login is viable -- get the loginid
				double loginid = generateUniqueLoginid(tlm);

				techloginDto = tlm.login( technicianDto, loginid );
				resp.setContentType("text/plain; charset=utf-8");
				ServletOutputStream sos = resp.getOutputStream();
				if( sos == null ) {
					throw new WayBadException( "Can't get output stream" );
				}
				sos.print( techloginDto.getLoginidString() );
				sos.print( ";" );
				sos.print( techloginDto.getTechcid() );
				sos.close();
				respCode = HttpServletResponse.SC_OK; // 200
			}
			// Error Responses:
			// 400 -- format of request was bad
			// 403 -- company inactive
			// 500 -- internal server, misc error
			// 503 -- over maximum login count
			//
		} catch( NoCanFindException ncfe ) {
			m_log.debug("NoCanFindException: " +ncfe.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN; //403
		} catch( IckyRequestException icke ) {
			m_log.debug("IckyRequestException: " +icke.toString() );
			respCode = HttpServletResponse.SC_BAD_REQUEST;  // 400
		} catch( BusyException be ) {
			m_log.debug("BusyException: " +be.toString() );
			respCode = HttpServletResponse.SC_SERVICE_UNAVAILABLE; // 503
		} catch( ServiceException se ) {
			m_log.debug("ServiceException: " +se.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN; //403
		} catch( Exception eek ) {
			m_log.debug("Exception: " +eek.toString() );
			respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; // 500
		}
		super.setResponseCode( respCode, resp );
	}

    private String readPassword(HttpServletRequest req) throws IckyRequestException
	{
		String password = "";
		ServletInputStream sis = null;
		try {
			sis = req.getInputStream();
			if( sis == null ) {
				throw new IckyRequestException( "unable to read password" );
			}
			int len = req.getContentLength();
			byte[] buf = new byte[1024];	
			int noRead = sis.readLine( buf, 0, 1024 );
			if( noRead != -1 ) {
				password = xlatPassword(buf, noRead );
			}
		} catch( IOException ioe ) {
			throw new IckyRequestException( "unable to read password" );
		} finally {
			try { if(sis != null) sis.close(); } catch( Exception ignored) {}
		}

		return password;
	}

	// however we decide to obfuscate the password for transfer we undo it here
	private String xlatPassword(byte[] buf, int noRead)
	{
		String password = new String(buf, 0, noRead);
		return password;
	}

    private double generateUniqueLoginid(TechloginManager tlm) throws WayBadException
    {
		boolean found = false;
		double loginid = 0;
		int nbTries = 0;
		while( !found ) {
			  String dblStr = Double.toString( Math.random() );
			  dblStr = dblStr.substring( 0, Math.min(16, dblStr.length()) );
			  loginid = Double.parseDouble(dblStr);
			  nbTries ++;
			  try {
					
					//if( tlm.countTechloginsByLoginid(TechloginDTO.loginidToString( loginid )) == 0 ){
					if( tlm.countTechloginsByLoginid( loginid ) == 0 ){
						  found = true;
					}
			  }
			  catch( ServiceException e ){
					throw new WayBadException(e);
			  }
		}
		m_log.debug("Number of tried to find a unique loginid: " + nbTries);
		return loginid;
    }


    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llremoteassist.HDSession.class);
    }


}