
package llcenter;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.service.dto.RCUsageDTO;
import com.infowave.c4p.model.facade.RCUsageManagerImpl;


public class LLRCStats
    extends HttpServlet
{

    public LLRCStats() 
    {
    }
  
    /* Updates the database with new information from the ICM.
     *
     * The format of this message allows for multiple machines (dids) to be updated within the same message.
     *  	<data>\r\n
     *		<stats did="aaaaaaaa" b="12345" ss="12345" cs="12345" />\r\n
     *				. . .
     *		<stats did="zzzzzzzz" b="12345" ss="12345" cs="12345" />\r\n
     *	</data>\r\n
     *
     *	b == bytes, ss == server seconds, cs == client seconds
     *   Note that the values that are passed are the amount to update (i.e., the delta) each database item.
     *
     * As part of this processing, for each did found, the database record will be updated with the information for that did. No checking
     * is done to see that the table entry has gone passed the window as that is handled via the GET processing.
    */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
	int respCode = 204;
	int step = 2;	// ICM doesn't make outgoing SLL connections
	boolean done = false;
	String s = req.getRequestURL().toString();

	while( respCode == 204 && !done ) {
		switch( step ) {
		case 1: 
			// Ensure SSL request
			if( s.indexOf("https://") == -1 )
			{
				respCode = 400;
			}
			break;

		case 2: 
			// Check the URL
			if( s.indexOf("/LLRCStats/postinfo") == -1 )
			{
				respCode = 400;
			}
			break;

		case 3:
			// parse the message body and try each item
			respCode = handleMessage( req );
			break;

		default:
			done = true;
		}
		step++;
	}

	resp.setStatus(respCode);
   }

    /* Gets current RC Usage for a particular machine (did).
     *
     * The format of this message allows for information about a single machine to be queried. The did in question is specified
     * in the header LLRCID. Information is also returned in the following HTTP headers:
     *	LLRCLB == low bytes
     *	LLRCHB == high bytes
     *	LLRCSS == serverseconds
     *	LLRCCS == client seconds
     *
     * The ICM only uses the server seconds to determine if the user has exceeded their limits. So the information passed back is the number
     * of seconds that the user may continue to run the RC. All other values are currently passed back as 0. If the user has exceeded their limit,
     * the server seconds is returned as 0. If the user has unlimited access then the value 0x7fffffff is returned in the server seconds header.
     *
     * Additional processing for this message includes making sure that a row exists for this did in the RCUsage table. It also checks, and if necessary resets,
     * the information if the window has expired. Note that any licensed user will already have an entry in the RCUsage table by virtue of having entered
     * their license serial number for the RC product.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
	RCUsageManagerImpl rcStats = null;
	RCUsageDTO rcInfo = null;
	boolean done = false;
	int step = 2;	// ICM doesn't make outgoing SSL connections
	String did = null;
	int respCode = 204;
	String s = req.getRequestURL().toString();


	while( respCode == 204 && !done ) {
		switch( step ) {
		case 1: 
			// Ensure SSL request
			if( s.indexOf("https://") == -1 )
			{
				respCode = 400;
			}
			break;

		case 2: 
			// Check the URL
			if( s.indexOf("/LLRCStats/getinfo") == -1 )
			{
				respCode = 400;
			}
			break;

		case 3:
			// Get the did from the HTTP header LLRCID
			did = req.getHeader(LLRCID);
			if( did == null || did.length() != 8 ) {
				respCode = 400;
			} else {
				rcStats = new RCUsageManagerImpl( did );
			}
			break;

		case 4:
			// Verify the database information & get the values in the table
			try {
				rcInfo = rcStats.verifyInfo( windowSize );
			} catch( Exception e ) {
				respCode = 500;
			}
			break;

		default:
			done = true;
		}
		step++;
	}

	// populate the specialized HTTP headers if everything went ok
	if( respCode == 204 ) {
		int maxSecs = Math.max( defaultLimit, rcInfo.getWindowLimit() );
		int clientSecondsLeft = maxSecs;
		if( maxSecs < 0x7fffffff ) {
			clientSecondsLeft = Math.max( -2, maxSecs - rcInfo.getClientSeconds() );
		}
		resp.setHeader(LLRCHB,  Integer.toString( 0xffffffff ));
		resp.setHeader(LLRCLB,  Integer.toString( 0xffffffff ));
		resp.setHeader(LLRCSS,  Integer.toString( 0x7fffffff ));
		resp.setHeader(LLRCCS,  Integer.toString( clientSecondsLeft ));
	}

	resp.setStatus(respCode);
    }

    private int handleMessage( HttpServletRequest req )
    {
       int respCode = 204;
       BufferedReader br = null;
       RCUsageManagerImpl rcStats = null;
       RCUsageDTO rcInfo = null;
       try {
       	br = new BufferedReader(new InputStreamReader(req.getInputStream()));
	// check the start of the message
	String str = br.readLine( );
	if( str.indexOf( STARTMSG_TOKEN ) == -1 ) {
		throw new IOException("Bad message");
	}

	while( str != null ) {
	     str = br.readLine( );
	     if( str.indexOf( ENDMSG_TOKEN ) != -1 ) {
		str = null;
		break;
	     }
	     // parse the message and then update the database
	    String did = getToken( str, DID_TOKEN, DID_TOKEN_LEN );
	    if( did != null ) {
		try {
	    	    rcStats = new RCUsageManagerImpl( did );
	    	    rcInfo = rcStats.verifyInfo( windowSize );
		} catch( Exception e) {
		    rcInfo = null;
		}
		if( rcInfo != null ) {
		    rcInfo.setClientSeconds( rcInfo.getClientSeconds() + Integer.parseInt(getToken( str, CSECS_TOKEN, CSECS_TOKEN_LEN )) );
		    rcInfo.setServerSeconds( rcInfo.getServerSeconds() + Integer.parseInt(getToken( str, SSECS_TOKEN, SSECS_TOKEN_LEN )) );
		    //rcInfo.setBytesSeconds( rcInfo.getBytes() + Integer.valueOf(getToken( str, BYTES_TOKEN, BYTES_TOKEN_LEN )) );
		    rcStats.update(did, rcInfo, rcStats.getRCUsage());
		}
	    }

            }

       } catch( Exception e) {
	respCode = 400;
       } finally {
	if(br != null) try { br.close(); } catch( IOException ioe ) {}
      }
 
      return respCode;
    }

    private String getToken(String info, String token, int len)
    {
	String retVal = null;
	int idx1, idx2;
	idx1 = info.indexOf( token );
	if( idx1 != -1 ) {
	    idx1 += len;
	    idx2 = info.indexOf( END_TOKEN, idx1 );
	    retVal = info.substring( idx1, idx2 );
	}
	return retVal;
    }


    private static final int DEFAULT_LIMIT_DEFAULT = 3600;		// seconds
    private static final int WINDOW_SIZE_DEFAULT = 30;		// days

    private static int windowSize;						// days
    private static int defaultLimit;						// seconds

    private static final String LLRCID = "LLRCID";
    private static final String LLRCHB = "LLRCHB";
    private static final String LLRCLB = "LLRCLB";
    private static final String LLRCSS = "LLRCSS";
    private static final String LLRCCS = "LLRCCS";

    private static final String DID_TOKEN = "did=\"";
    private static final String BYTES_TOKEN = "b=\"";
    private static final String CSECS_TOKEN = "cs=\"";
    private static final String SSECS_TOKEN = "ss=\"";
    private static final String END_TOKEN = "\"";
    private static final String STARTMSG_TOKEN = "<data>";
    private static final String ENDMSG_TOKEN = "</data>";
    private static int DID_TOKEN_LEN = DID_TOKEN.length();
    private static int BYTES_TOKEN_LEN = BYTES_TOKEN.length();
    private static int CSECS_TOKEN_LEN = CSECS_TOKEN.length();
    private static int SSECS_TOKEN_LEN = SSECS_TOKEN.length();

    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llcenter.LLRCStats.class);
    }

    /* Get the values for the default window (in days) and default limit (in seconds). These values should
     * be found in the web.xml (where the servlets are defined) listed as init-params.
     */
    public void init() throws ServletException
    {
	try {
		windowSize = Integer.parseInt(getServletConfig().getInitParameter("window size"));
	} catch( Exception e1) {
		windowSize = WINDOW_SIZE_DEFAULT;
	}

	try {
		defaultLimit = Integer.parseInt(getServletConfig().getInitParameter("default limit"));
	} catch( Exception e1) {
		defaultLimit = DEFAULT_LIMIT_DEFAULT;
	}

	m_log.debug("windowSize = " +windowSize+ " & defaultLimit = " +defaultLimit );

    }



}