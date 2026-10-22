
package llremoteassist;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.List;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infowave.c4p.service.delegate.TechnicianManager;
import com.infowave.c4p.service.delegate.TechloginManager;
import com.infowave.c4p.service.delegate.SessionManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.SessionDTO;
import com.infowave.c4p.service.dto.TechloginDTO;
import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.util.IdGenerator;

public class HDDid
    extends HDHttpServlet
{

    public HDDid() 
    {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
		int respCode = HttpServletResponse.SC_OK; // 200
		String did = "";
		String query = req.getQueryString();
		m_log.debug(query);
		String hdrLoginid = req.getHeader( LLRAConstants.LOGINID );
		m_log.debug("hdrLoginid = " +hdrLoginid);
		String uri = req.getRequestURL().toString();
		m_log.debug("request = " +uri);
		// Now get the various parameters
		// See what type of poll this is
		if( uri.indexOf( LLRAConstants.GET_DID ) != -1 ) {
			try {
				String companyid = super.getQueryParam( query, LLRAConstants.CO_TOKEN, 
					LLRAConstants.CO_TOKEN_LEN, LLRAConstants.ERRMSG_CO);
				String techid = super.getQueryParam( query, LLRAConstants.TECHID_TOKEN, 
					LLRAConstants.TECHID_TOKEN_LEN, LLRAConstants.ERRMSG_TECHID);

				// verify the lengths of the input data
				validateCompanyid(companyid);
				validateTechid(techid);

				// validate the companyid, techid combo & get back the techcid
				TechnicianDTO technicianDto = null;
				TechnicianManager tm = DelegateFactory.getTechnicianManager(); 
				if( tm != null ) {
					technicianDto = tm.getByDoubleid(companyid, techid);
					if( technicianDto == null ) {
						throw new NoCanFindException( "technician not found" );
					}
				} else {
					throw new WayBadException( "TechnicianManager MIA" );
				}

				String techcid = technicianDto.getTechcid();

				// Validate that this technician has an active login
				TechloginManager tlm = DelegateFactory.getTechloginManager(); 
				if( tlm != null ) {

					List techloginDTOList = tlm.getActiveByDoubleid( companyid, techcid );
					if( techloginDTOList.size() <= 0 ) {
						throw new BusyException( "technician not logged in" );
					}
					// If this technician has multiple active logins, we're logging all of them out.
					TechloginDTO activeloginDto = null;
					if( techloginDTOList.size() > 1 ) {
						for(Iterator it = techloginDTOList.iterator(); it.hasNext(); )
						{
							activeloginDto = (TechloginDTO)it.next();
							activeloginDto = tlm.logoutByLoginid(activeloginDto.getLoginid(), TechloginDTO.FORCED_LOGOUT_FLAG);
						}
						throw new BusyException( "technician has multiple active logins" );
					}

					// There is one, active login for this technician so we're going to
					// check the loginid to make sure it's okay
					for(Iterator it = techloginDTOList.iterator(); it.hasNext(); )
					{
						activeloginDto = (TechloginDTO)it.next();
						if( !activeloginDto.doLoginidsMatch( hdrLoginid ) ) {
							throw new BusyException( "technician's login is stale" );
						}
					}
				} else {
					throw new WayBadException( "TechloginManager MIA" );
				}

				// Get us a device id & enter it into the database
				SessionDTO sessionDto = null;
				SessionManager sm = DelegateFactory.getSessionManager(); 
				if( sm != null ) {
					did = generateUniqueDid(sm);
					sessionDto = sm.insert(companyid, techcid, did);
					if( sessionDto == null ) {
						throw new NoCanFindException( "session insert" );
					}
				} else {
					throw new WayBadException( "SessionManager MIA" );
				}

			// Error Responses:
			// 400 -- format of request was bad
			// 403 -- tech not logged in
			// 404 -- data is bad (e.g., can't find techid or companyid)
			// 500 -- internal server, misc error
			//
			} catch( IckyRequestException icke ) {
				m_log.debug("IckyRequestException: " +icke.toString() );
				respCode = HttpServletResponse.SC_BAD_REQUEST;   //400
			} catch( BusyException be ) {
				m_log.debug("BusyException: " +be.toString() );
				respCode = HttpServletResponse.SC_FORBIDDEN;		// 403
			} catch( NoCanFindException ncfe ) {
				m_log.debug("NoCanFindException: " +ncfe.toString() );
				respCode = HttpServletResponse.SC_NOT_FOUND;		// 404 
			} catch( ServiceException se ) {
				m_log.debug("ServiceException: " +se.toString() );
				respCode = HttpServletResponse.SC_NOT_FOUND;		// 404 
			} catch( WayBadException wbe ) {
				m_log.debug("WayBadException: " +wbe.toString() );
				respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;		// 500 
			} catch( Exception eek ) {
				m_log.debug("Exception: " +eek.toString() );
				respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;		// 500 
			}
		} else if(uri.indexOf( LLRAConstants.RELEASE_DID ) != -1) {
			respCode = HttpServletResponse.SC_NO_CONTENT;		// 204 default for this type of message
			try {
				// Get the did
				int idx, idx2;
				idx = query.indexOf( LLRAConstants.DID_TOKEN );
				if( idx != -1 ) {
					idx += LLRAConstants.DID_TOKEN_LEN;
					idx2 = query.indexOf( "&", idx );
					if( idx2 == -1 ) {
						did = query.substring( idx );
					} else {
						throw new IckyRequestException( "too many query parameters" );
					}
				} else {
					throw new IckyRequestException( "no did" );
				}

				// validate the data
				validateDid(did);

				// Get the session manager
				SessionDTO sessionDto = null;
				SessionManager sm = DelegateFactory.getSessionManager(); 
				if( sm == null ) {
					throw new WayBadException( "SessionManager MIA" );
				}
				
				/*
				// Make sure this techlogin is active
				TechloginManager tlm = DelegateFactory.getTechloginManager(); 
				if( tlm == null ) {
					throw new WayBadException( "TechloginManager MIA" );
				}
				int logincount = tlm.countActiveTechloginsByLoginid( Double.parseDouble(hdrLoginid) );
				if( logincount == 0 ) {
					throw new NoCanFindException( "stale loginid" );
				}
				*/
				// Update the database
				sessionDto = sm.close( did );
				if( sessionDto == null ) {
					throw new NoCanFindException( "did not found" );
				}
			// Error Responses:
			// 400 -- format of request was bad
			// 404 -- data is bad (can't find did)
			// 500 -- internal server, misc error
			//
			} catch( IckyRequestException icke ) {
				m_log.debug("IckyRequestException: " +icke.toString() );
				respCode = HttpServletResponse.SC_BAD_REQUEST; //400
			} catch( NoCanFindException ncfe ) {
				m_log.debug("NoCanFindException: " +ncfe.toString() );
				respCode = HttpServletResponse.SC_NOT_FOUND;		// 404 
			} catch( ServiceException se ) {
				m_log.debug("ServiceException: " +se.toString() );
				respCode = HttpServletResponse.SC_NOT_FOUND;		// 404 
			} catch( WayBadException wbe ) {
				m_log.debug("WayBadException: " +wbe.toString() );
				respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;		//  500 
			} catch( Exception eek ) {
				m_log.debug("Exception: " +eek.toString() );
				respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;		// 500 
			}

		} else {
			// We don't recognize this one
			respCode = HttpServletResponse.SC_BAD_REQUEST; // 400
		}
		// Check if this is a valid session
		m_log.debug("respCode = " +respCode );
		if( respCode == HttpServletResponse.SC_OK ) { // 200
			try {
				resp.setContentType("text/plain; charset=utf-8");
				ServletOutputStream sos = resp.getOutputStream();
				if( sos != null ) {
					sos.print(did);
					sos.close();
				} else {
					throw new IOException( "no outputtream" );
				}
			} catch( IOException ioe ) {
				respCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; // 500
			}
		}
		super.setResponseCode( respCode, resp );
	}
 

    private String generateUniqueDid(SessionManager sm) throws WayBadException
    {
		boolean found = false;
		String did = null;
		int nbTries = 0;
		while (!found){
			  did = IdGenerator.getId();
			  nbTries ++;
			  while (did.length() < LLRAConstants.DID_SIZE){
					did += IdGenerator.getId();
			  }
			  did = did.substring(0, LLRAConstants.DID_SIZE);
			  try {
					if (sm.countSessionsByDid(did) == 0){
						  found = true;
					}
			  }
			  catch(ServiceException e){
					throw new WayBadException(e);
			  }
		}
		m_log.debug("Number of tried to find a unique did: " + nbTries);
		return did;
    }

    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llremoteassist.HDDid.class);
    }




}