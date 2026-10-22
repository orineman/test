
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
import com.infowave.c4p.service.dto.TechloginDTO;
import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.util.security.PasswordEncoderFactory;

import java.util.List;
import java.util.Iterator;

// This servlet handles the review & setting of the of the Help Desk Technician data
public class HDTechnician
    extends HDHttpServlet
{

	private String m_email = null;
	private String m_phone = null;
	private String m_name = null;
	private String m_misc = null;
	private String m_password = null;

    public HDTechnician() 
    {
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
			if( uri.indexOf( LLRAConstants.TECHINFO ) != -1 ) {
				TechloginManager tlm = DelegateFactory.getTechloginManager(); 
				if( tlm == null ) {
					throw new WayBadException( "TechloginManager MIA" );
				}

				// Get this technician's information
				TechnicianManager tm = DelegateFactory.getTechnicianManager(); 
				if( tm == null ) {
					throw new WayBadException( "TechnicianManager MIA" );
				}

				TechnicianDTO technicianDto = getTechnician(hdrLoginid, tlm, tm);

				// Put the technician info into the response
				StringBuffer sb = new StringBuffer("");
				sb.append(LLRAConstants.TECHNAME_TOKEN).append((technicianDto.getName()==null?"":technicianDto.getName())).append(LLRAConstants.TECH_DELIM);
				sb.append(LLRAConstants.EMAIL_TOKEN).append((technicianDto.getEmail()==null?"":technicianDto.getEmail())).append(LLRAConstants.TECH_DELIM);
				sb.append(LLRAConstants.PHONE_TOKEN).append((technicianDto.getPhone()==null?"":technicianDto.getPhone())).append(LLRAConstants.TECH_DELIM);
				sb.append(LLRAConstants.MISC_TOKEN).append((technicianDto.getMisc()==null?"":technicianDto.getMisc())).append(LLRAConstants.TECH_DELIM);

				resp.setContentType("text/plain; charset=utf-8");
				ServletOutputStream sos = resp.getOutputStream();
				if( sos == null ) {
					throw new WayBadException( "Can't get output stream" );
				}
				sos.print( sb.toString() );
				sos.close();
				respCode = HttpServletResponse.SC_OK; // 200
			}



		// Error Responses:
		// 400 -- format of request was bad
		// 403 -- tech not logged in or data is bad (e.g., did can't be found)
		// 500 -- internal server, misc error
		//
		} catch( NoCanFindException ncfe ) {
			m_log.debug("NoCanFindException: " +ncfe.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN;		// 403
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
	  
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
		int respCode = HttpServletResponse.SC_FORBIDDEN; // 403
		String hdrLoginid = req.getHeader( LLRAConstants.LOGINID );
		m_log.debug("hdrLoginid = " +hdrLoginid);
		String uri = req.getRequestURL().toString();
		m_log.debug("request = " +uri);
		try {
			// Check if this is a login or logout. Note that something unidentified
			// gets a 204 returned. 
			if( uri.indexOf( LLRAConstants.TECHINFO ) != -1 ) {
				// The Help Desk application is sending an update to the technician table
				TechloginManager tlm = DelegateFactory.getTechloginManager(); 
				if( tlm == null ) {
					throw new WayBadException( "TechloginManager MIA" );
				}

				// Get this technician's information
				TechnicianManager tm = DelegateFactory.getTechnicianManager(); 
				if( tm == null ) {
					throw new WayBadException( "TechnicianManager MIA" );
				}

				TechnicianDTO technicianDto = getTechnician(hdrLoginid, tlm, tm);


				// the data to update is in the message body
				readMessageBody(req);

				// Update the technician info & save it
				// if the values are null, they weren't in the message
				if( m_password != null ) {
					validatePassword(m_password);
				    PasswordEncoder enc = PasswordEncoderFactory.instance().getEncoder("com.infowave.c4p.laplink.security.PasswordEncoderImpl");
					if( enc == null ) {
						throw new WayBadException( "PasswordEncoder MIA" );
					}
					technicianDto.setPassword( enc.encode(m_password) );
				}

				if( m_email != null ) {
					validateEmail(m_email);
					technicianDto.setEmail( m_email );
				}

				if( m_phone != null ) {
					validatePhone(m_phone);
					technicianDto.setPhone( m_phone );
				}

				if( m_name != null ) {
					validateName(m_name);
					technicianDto.setName( m_name );
				}

				if( m_misc != null ) {
					validateMisc(m_misc);
					technicianDto.setMisc( m_misc );
				}

				tm.update( technicianDto );


				respCode = HttpServletResponse.SC_NO_CONTENT; // 204
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
		} catch( ServiceException se ) {
			m_log.debug("ServiceException: " +se.toString() );
			respCode = HttpServletResponse.SC_FORBIDDEN; //403
		} catch( Exception eek ) {
			m_log.debug("Exception: " +eek.toString() );
			respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; // 500
		}
		super.setResponseCode( respCode, resp );
	}

	private TechnicianDTO getTechnician(String hdrLoginid, TechloginManager tlm, TechnicianManager tm) 
		throws NoCanFindException, ServiceException
	{
		// See if we have an active techlogin & get info about it
		TechloginDTO techloginDto = tlm.getActiveTechloginByLoginid( Double.parseDouble(hdrLoginid) );
		if( techloginDto == null ) {
			throw new NoCanFindException( "stale loginid" );
		}

		// Get this technician's information
		TechnicianDTO technicianDto = tm.getByTechcid( techloginDto.getTechcid() );
		if( technicianDto == null ) {
			throw new NoCanFindException( "bad technician (bad techcid)" );
		}

		return technicianDto;
	}


    private void readMessageBody(HttpServletRequest req) throws IckyRequestException
	{
		String messageBody = "";
		ServletInputStream sis = null;
		try {
			sis = req.getInputStream();
			if( sis == null ) {
				throw new IckyRequestException( "unable to read message body" );
			}
			int len = req.getContentLength();
			byte[] buf = new byte[2048];
			int idx = 0;
			int noRead = 0;
			while( noRead != -1 ) {
				idx += noRead;
				noRead = sis.readLine( buf, idx, 2048-idx );
			}
			if( idx != 0 ) {
				messageBody = new String( buf, 0, idx );
				m_name = getItem(messageBody, LLRAConstants.TECHNAME_TOKEN, LLRAConstants.TECHNAME_TOKEN_LEN);
				m_email = getItem(messageBody, LLRAConstants.EMAIL_TOKEN, LLRAConstants.EMAIL_TOKEN_LEN);
				m_phone = getItem(messageBody, LLRAConstants.PHONE_TOKEN, LLRAConstants.PHONE_TOKEN_LEN);
				m_misc = getItem(messageBody, LLRAConstants.MISC_TOKEN, LLRAConstants.MISC_TOKEN_LEN);
				m_password = getItem(messageBody, LLRAConstants.PWD_TOKEN, LLRAConstants.PWD_TOKEN_LEN);
				if( m_password != null ) {
					m_password = xlatPassword( m_password );
				}
			}
		} catch( IOException ioe ) {
			throw new IckyRequestException( "unable to read message body" );
		} finally {
			try{ if( sis != null ) sis.close(); } catch( Exception ignored ) {}
		}

	}

	private String getItem(String messageBody, String token, int tokenLen) throws IckyRequestException
	{
		String retstr = null;				
		int idx, idx2;
		idx = messageBody.indexOf(token);
		if( idx != -1 ) {
			idx += tokenLen;
			idx2 = messageBody.indexOf( LLRAConstants.TECH_DELIM, idx );
			if( idx2 != -1 ) {
				retstr = messageBody.substring( idx, idx2 );
			} else {
				throw new IckyRequestException( "improper format in message body" );
			}
		}
		return retstr;
	}

	// however we decide to obfuscate the password for transfer we undo it here
	private String xlatPassword(String pwd)
	{
		return pwd;
	}

    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llremoteassist.HDTechnician.class);
    }


}