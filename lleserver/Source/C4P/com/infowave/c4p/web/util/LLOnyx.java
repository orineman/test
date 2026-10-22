
package com.infowave.c4p.web.util;


import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;
import java.security.Principal;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infowave.c4p.web.util.Notifier;
import com.infowave.c4p.web.util.Base64Encoder;
import com.infowave.c4p.util.ApplicationParameters;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.DataException;

public class LLOnyx
{

    public LLOnyx() 
    {
    }

    public void UpdateUserInfo( String username ) throws ServiceException
    {
	try {
		NameValuePair[] nvps = new NameValuePair[23];
		setUserInfo( nvps, getAccount( username ) );
		send( ONYX_URL, nvps );
	} catch( java.io.IOException ioe ) {
		Notifier.instance().notifyOnyxError( "UpdateUserInfo: " +ioe.toString(), new Locale("en") );
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, ioe);
	}
    }

    // Onyx knows the details about each license so we don't have to specifiy type
    public void UpdateLicenseInfo( String username, String serialNumber ) throws ServiceException
    {
	try {
		NameValuePair[] nvps = new NameValuePair[25];
		setLicenseInfo( nvps, getAccount( username ), serialNumber );
		send( ONYX_URL, nvps );
	} catch( java.io.IOException ioe ) {
		Notifier.instance().notifyOnyxError( "UpdateLicenseInfo: " +ioe.toString(), new Locale("en") );
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, ioe);
	}
    }

    // Onyx knows the details about each license so we don't have to specifiy type
    public void UpdateLicenseInfo( Account account, String serialNumber ) throws ServiceException
    {
	if( account == null ) {
		PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
		throw new ServiceException(error, "No account information.");
	}
	try {
		NameValuePair[] nvps = new NameValuePair[25];
		setLicenseInfo( nvps, account, serialNumber );
		send( ONYX_URL, nvps );
	} catch( java.io.IOException ioe ) {
		Notifier.instance().notifyOnyxError( "UpdateLicenseInfo: " +ioe.toString(), new Locale("en") );
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, ioe);
	}
    }
  
    private Account getAccount( String username ) throws ServiceException
    {
	m_log.debug( "username = " +username );

	// now get account from username
            AccountDAO accountDAO = DAOFactory.getAccountDAO();
	Account account = null;
            try {
		account = accountDAO.getByUsername(username);
	} catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
		throw new ServiceException(error, "Unable to get account information for user " +username);
	}
	m_log.debug( "account = " +account );

	if( account == null ) {
		PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
		throw new ServiceException(error, "Unable to get account information for user " +username);
	}

	return account;
    }


    private void setUserInfo( NameValuePair[] nvps, Account account  )
    {
	setBasicInfo( nvps, account );
            nvps[3] = new NameValuePair(PRODUCT_ID, "PAFGLLE104000P0EVDEN" );    // LLE
            //nvps[3] = new NameValuePair(PRODUCT_ID, "PAFGLPLK0B0BMS0EVDML" );    // LLI
            nvps[22] = new NameValuePair(VENDORCOMMAND, VC_PURCHASE);
    }

    private void setLicenseInfo( NameValuePair[] nvps, Account account, String serialNumber )
    {
	setBasicInfo( nvps, account );
            nvps[3] = new NameValuePair(PRODUCT_ID, "" ); 				  // LLE
            //nvps[3] = new NameValuePair(PRODUCT_ID, "PAFGLPLK0B0BMS0RTDEN" );   // LLI
            nvps[22] = new NameValuePair(VENDORCOMMAND, VC_REGISTER);
            nvps[23] = new NameValuePair(VENDORUSERID,  account.getEmail() ); // email
            nvps[24] = new NameValuePair(VENDORSERIALNUMBER, serialNumber  ); // license number
    }

    private void setBasicInfo( NameValuePair[] nvps, Account account )
    {
	String temp;
	Date date = new Date();
            StringBuffer str = new StringBuffer(20);
	str.append(r.nextDouble());
	String id = str.toString();
	if( id.length() > 10 ) {
		id = id.substring(10);
	}
            nvps[0] = new NameValuePair(PURCHASE_ID, id );
            nvps[1] = new NameValuePair(RUNNING_NO,  "0001" ); 
            nvps[2] = new NameValuePair(PURCHASE_DATE, df1.format( date ) ); 
            nvps[4] = new NameValuePair(QUANTITY,  "1" ); 
	temp = account.getUsername();
            nvps[5] = new NameValuePair(REG_NAME,  temp == null?"":temp ); 
            nvps[6] = new NameValuePair(ADDITIONAL1, "" );
            nvps[7] = new NameValuePair(ADDITIONAL2, "" ); 
            nvps[8] = new NameValuePair(RESELLER, "LapLink" ); 
	temp = account.getLastName();
            nvps[9] = new NameValuePair(LASTNAME, temp == null?"":temp);
	temp = account.getFirstName();
            nvps[10] = new NameValuePair(FIRSTNAME, temp == null?"":temp );
            nvps[11] = new NameValuePair(COMPANY,  "" ); 
	temp = account.getEmail();
            nvps[12] = new NameValuePair(EMAIL, temp == null?"":temp); 
            nvps[13] = new NameValuePair(PHONE, "" );
            nvps[14] = new NameValuePair(FAX,  "" ); 
            nvps[15] = new NameValuePair(STREET, "" ); 
            nvps[16] = new NameValuePair(CITY, "" );
            nvps[17] = new NameValuePair(ZIP,  "" ); 
            nvps[18] = new NameValuePair(STATE,  "" ); 
            nvps[19] = new NameValuePair(COUNTRY,  account.getCountryCode() ); 
            nvps[20] = new NameValuePair(LANGUAGE_ID, mapLanguageID(account.getLocale()) ); 	
            nvps[21] = new NameValuePair(VENDORID, "LLE" ); // LLE
            //nvps[21] = new NameValuePair(VENDORID, "Laplink" );  // LLI
    }

    private String mapLanguageID( Locale locale )
    {
	String langId = "1";	// default to english

	// Map the locale into an Onyx supported value
	String localeName = locale!=null?locale.getLanguage():"en";
	m_log.debug("localeName = " +localeName );

	// handle language code changes with the compare
	if( localeName.equals( germanLocale.getLanguage() )) {
		langId = "2";
	} else if( localeName.equals( frenchLocale.getLanguage()  )) {
		langId = "6";
	} else if( localeName.equals( italianLocale.getLanguage()  )) {
		langId = "5";
	} else if( localeName.equals( spanishLocale.getLanguage()  )) {
		langId = "4";
	//} else if( localeName.equals( portugueseLocale.getLanguage()  )) {
	//	langId = "4";
	}

	return langId;

    }
  
    public void send( String url, NameValuePair[] nvps ) throws java.io.IOException
    {
        //String url = "http://my-dev.laplink.com/LLRCStats/postinfo";
        HttpMethod method = new PostMethod(url);
        //String username = "vtang";
        //String password = "ignored";
        //String authorization = "Basic " + Base64Encoder.encode(username + ":" + password);
        //method.addRequestHeader("Authorization", authorization);
        if(method instanceof PostMethod)
        {
            PostMethod post = (PostMethod)method;
            //NameValuePair nvps[] = new NameValuePair[2];
            //nvps[0] = new NameValuePair("did", "5Ai4KasZ");
            //nvps[1] = new NameValuePair("LLMethod", "GetNavigatePage");
            post.setRequestBody(nvps);
            NameValuePair params[] = post.getParameters();
	String str = "method: " + method.getClass().getName() + " " + method.getPath();
	str += "The parameters:";
            for(int i = 0; i < params.length; i++)
                str += " param: " + params[i].getName() + " = " + params[i].getValue() +"; ";
            m_log.debug( str );

        }
        HttpClient client = new HttpClient();
		client.setConnectionTimeout( 5000 ); // milliseconds
        int res = client.executeMethod(method);
        m_log.debug("return: " + res);
        java.io.InputStream is = method.getResponseBodyAsStream();
        byte buff[] = new byte[256];
        System.out.println(method.getResponseBodyAsString());
        method.releaseConnection();
		if( (res < 200 || res > 206) && res != 400) {	// ignore 400 responses
			throw new IOException("Response = " +res);
		}
    }


    private static final String ONYX_URL = "http://www.laplink.com/mysupport/register/holdere5.asp";

    private static final String VENDORID = "VENDORID";
    private static final String LANGUAGE_ID = "LANGUAGE_ID";
    private static final String COUNTRY = "COUNTRYCODE";
    private static final String STATE = "STATE";
    private static final String ZIP = "ZIP";
    private static final String CITY = "CITY";
    private static final String STREET = "STREET";
    private static final String VENDORCOMMAND = "VENDORCOMMAND";
    private static final String VENDORUSERID = "VENDORUSERID";
    private static final String VENDORSERIALNUMBER = "VENDORSERIALNUMBER";

    private static final String FAX = "FAX";
    private static final String PHONE = "PHONE";
    private static final String EMAIL = "EMAIL";
    private static final String COMPANY = "COMPANY";
    private static final String FIRSTNAME = "FIRSTNAME";
    private static final String LASTNAME = "LASTNAME";
    private static final String RESELLER = "RESELLER";

    private static final String ADDITIONAL1 = "ADDITIONAL1";
    private static final String ADDITIONAL2 = "ADDITIONAL2";
    private static final String REG_NAME = "REG_NAME";
    private static final String QUANTITY = "QUANTITY";
    private static final String PRODUCT_ID = "PRODUCT_ID";
    private static final String PURCHASE_DATE = "PURCHASE_DATE";
    private static final String RUNNING_NO = "RUNNING_NO";
    private static final String PURCHASE_ID = "PURCHASE_ID";

    private static final String VC_PURCHASE = "PURCHASE";
    private static final String VC_REGISTER = "REGISTERSERIALNUMBER";

    private static SimpleDateFormat df1 = new SimpleDateFormat( "MM/dd/yyyy" );
    private static Random r = new Random(System.currentTimeMillis());
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.util.LLOnyx.class);
    }

    private static Locale germanLocale = new Locale("de", "", "");
    private static Locale frenchLocale= new Locale("fr", "", "");
    private static Locale italianLocale= new Locale("it", "", "");
    private static Locale spanishLocale= new Locale("es", "", "");
    private static Locale portugueseLocale= new Locale("pt", "", "");


}