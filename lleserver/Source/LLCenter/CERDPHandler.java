
package llcenter;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.model.Authentication;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.util.security.PasswordEncoderFactory;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.model.facade.DesktopManagerImpl;
import com.infowave.c4p.model.facade.RCUsageManagerImpl;
import com.infowave.c4p.service.ServiceException;

public class CERDPHandler
    extends HttpServlet
{

    public CERDPHandler()
    {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
	// handles desktop lits request -- /xhtml/t/system/small_device_list.jsp
 	HttpSession session = req.getSession();
	String userId = (String)session.getValue("LLG");
	String s = req.getRequestURL().toString();
	m_log.debug(s +":" +userId);
	if( s.indexOf("/xhtml/t/system/small_device_list.jsp") != -1 && userId != null && userId.length() > 0 )
	{
		resp.setHeader( CACHE_HDR, CACHE_HDR_VAL );
		// Set up to write the response out
		java.io.PrintWriter out = new java.io.PrintWriter(resp.getOutputStream());
		// Get a list of desktops
		DesktopManagerImpl dtoManager = new DesktopManagerImpl();
		String responseBody = "  <PCList>  \r\n";
		try {
			java.util.List desktops = dtoManager.getDesktops( userId );
			// build the return list
			java.util.Iterator desktopList = desktops.iterator();
			DesktopDTO desktop = null;
			String status = "";
			while( desktopList.hasNext() ) {
				desktop = (DesktopDTO)desktopList.next();
				responseBody += "    <PC>";
				responseBody += desktop.getName();
				responseBody += getStatus(desktop);
				responseBody += "</PC>\r\n";
				responseBody += "    <did>";
				responseBody += desktop.getDid();
				responseBody += "</did>\r\n";
			}
		} catch (Exception e) { }
		responseBody += "  </PCList>  \r\n\r\n";
		resp.setHeader("Content-Length", Integer.toString(responseBody.length()) );
		out.print(responseBody);
		out.flush();
		out.close();
		resp.setStatus(200);
	} else if(s.indexOf("/xhtml/s/public/logout.jsp") != -1) {
		//session.invalidate();
		resp.setHeader( CACHE_HDR, CACHE_HDR_VAL );
		session.removeValue("LLG");
		resp.setStatus(200);
	} else {
		// return an error
		resp.setStatus(400);
	}
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
	// handles authentication request -- /xhtml/index.jsp
 	HttpSession session = req.getSession();
	String s = req.getRequestURL().toString();
	String contentLen = req.getHeader("Content-Length");
	m_log.debug(s);
	if( s.indexOf("/xhtml/index.jsp") != -1 )
	{
		boolean isValid = false;
		String userID = "";
		String password = "";
		// pull out the userid and password
             	String info = getRequestData(req.getInputStream(), contentLen ); 
		if( info.length() > 0 ) {
		    userID = getAuthInfo( info , userToken );
            	    password = getAuthInfo( info , passwordToken );
		    // check if info is valid
		    isValid = validate( userID, password );
			if( isValid ) {
				// valid -- so put info into session for later
 				//HttpSession session = req.getSession();
				session.putValue("LLG", userID);
				// send back 200 response
				resp.setHeader( CACHE_HDR, CACHE_HDR_VAL );
				resp.setStatus(200);
			} else {
				// not valid -- send back an error
				resp.setStatus(400);
			}
		} else {
			// return an error
			resp.setStatus(500);
		}
	} else 	{
		// return an error
		resp.setStatus(400);
	}
    }

   public String getStatus( DesktopDTO desktop )
   {
	String status = "";
	boolean isExpired = false, isWindowExpired = false, isConnected = false, isAvailable = false;

	if( desktop.isHasExpired() ) {
		isExpired = true;
	} else {
		if( desktop.isConnected() ) {
			isConnected = true;
		}
		if( desktop.getRdpEnabled() ) {
			isAvailable = true;
		}
		RCUsageManagerImpl rcStats = new RCUsageManagerImpl( desktop.getDid() );
		if( rcStats != null ) {
			try { isWindowExpired = rcStats.isExpired();
			} catch( ServiceException se ) {}
		}
	}

	if( isExpired ) {
		status = " (expired) ";
	} else if( (!isConnected && !isAvailable) || isWindowExpired ) {
		status = " (offline/unavailable) ";
	} else if( !isConnected ) {
		status = " (offline) ";
	} else if( !isAvailable ) {
		status = " (unavailable) ";
	}

	return status;
   }

    private String getRequestData( ServletInputStream sis, String contentLen   )
    {
       int mlen = Integer.parseInt(contentLen);
       BufferedReader br = new BufferedReader(new InputStreamReader(sis));
       //InputStreamReader isr = new InputStreamReader(sis);
      char buffer[] = new char[BUF_LEN];
      //byte buffer[] = new byte[BUF_LEN];
       int len = 0;

        try {
	 if( br != null ) {
		len = br.read( buffer, 0, mlen   );
		if( len == -1 ) len = 0;
		//len = isr.read( buffer, 0, mlen  );
	 }
            br.close();
            sis.close();
      } catch( Exception e) {
	len = 0;
       }

       return new String( buffer, 0, len );
    }

   private String getAuthInfo( String s, String token )  throws ServletException
   {
        int i = s.indexOf(token);
        if(i == -1)
            throw new ServletException("invalid request format1");
        i += token.length();

        int j = s.indexOf("&", i);
        if(j == -1)
            throw new ServletException("invalid request format2");

        return s.substring(i, j);
   }

   private boolean validate( String username, String password ) 
   {
      boolean isValid = false;
      try {
            AuthenticationDAO authenticationDAO = DAOFactory.getAuthenticationDAO();
            Authentication authentication = authenticationDAO.getByUsername(username);
            if(authentication != null)
            {
               isValid = validatePassword(authentication, password);
            }
       } catch( Exception e ) {     
       }
       return isValid;

   } 

    private boolean validatePassword(Authentication authentication, String password)
        throws ServletException
    {
        boolean valid = false;
        try
        {
            PasswordEncoder encoder = null;
            Class theClass = Class.forName(authentication.getAuthType());
            encoder = (PasswordEncoder)theClass.newInstance();
            valid = encoder.match(password, authentication.getPassword());
        }
        catch(Exception e)
        {
            throw new ServletException("oops", e);
        }
        return valid;
    }

    private static final String userToken = "&userID=";
    private static final String passwordToken = "&password=";
    private static final int BUF_LEN = 1024;
    private static final String CACHE_HDR = "Cache-Control";
    private static final String CACHE_HDR_VAL = "no-cache, no-store, must-revalidate, max-age=1";

    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llcenter.CERDPHandler.class);
    }



}