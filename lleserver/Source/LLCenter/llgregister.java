
package llcenter;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.dto.LicenseDTO;
import com.infowave.c4p.model.facade.DesktopManagerImpl;
import com.infowave.c4p.model.facade.LicenseManagerImpl;


public class LLGRegister
    extends HttpServlet
{

    public LLGRegister()
    {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException
    {
	// handles llg register request -- /lf-xhtml-loudpc/_all/public/lle/reg.jsp
	int respCode = 200;
	String s = req.getRequestURL().toString();
	m_log.debug(s);
	if( s.indexOf("/lf-xhtml-loudpc/_all/public/lle/reg.jsp") != -1 )
	{
		try {
			String requestBody = getRequestData(req.getInputStream());
			// Get did from request
			String did = getInfo( requestBody, didToken );
			// Get serial number from request
			String sn = getInfo( requestBody, snToken );

			// Update the tables, if necessary with this info from LLG
			LicenseManagerImpl licenseManager = new LicenseManagerImpl();
			respCode =  licenseManager.setLLGLicense(did, sn);

			resp.setStatus( respCode );

		} catch( Exception e ) {
			respCode = 500;
			resp.setStatus( 500 );
		}
	} else {
		// return an error
		respCode = 400;
		resp.setStatus(400);
	}
	// Override the status -- the host doesn't check and it cannot handle
	// chunked encoding. A 200 response will not send back data
	m_log.debug("LLGRegister status = " +respCode );
	resp.setStatus(200);
    }

   
    private String getRequestData( ServletInputStream is )
    {
       BufferedReader br = new BufferedReader(new InputStreamReader(is));
       char buffer[] = new char[BUF_LEN];
       int len = 0;

        try {
	 if( br != null ) {
		len = br.read( buffer, 0, BUF_LEN);
	 }
       } catch( Exception e) {
	len = 0;
       }

       return new String( buffer, 0, len );
    }

   private String getInfo( String s, String token )  throws ServletException
   {
        int i = s.indexOf(token);
        if(i == -1)
            throw new ServletException("invalid request format1");
        i += token.length();

        int j = s.indexOf("</", i);
        if(j == -1)
            throw new ServletException("invalid request format2");

        return s.substring(i, j);
   }

  

    private static final String didToken = "<did>";
    private static final String snToken = "<alt>";
    private static final int BUF_LEN = 1024;

    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llcenter.LLGRegister.class);
    }



}