package com.infowave.c4p.web.actions.lle5;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.CannotConnectToICMException;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.StringManager;
import com.infowave.c4p.web.actions.UserAction;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.sun.org.apache.xml.internal.serialize.DOMSerializer;
import com.sun.org.apache.xml.internal.serialize.Method;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.SerializerFactory;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import static com.infowave.c4p.web.util.XmlUtil.*;
import org.xml.sax.SAXException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;


import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This classes aggregates the actions for the requesting informations by client from host::
 */
public class clienttohostCommands extends UserAction
{
private static final int BUF_LEN = 1024;
private byte[] responseBodyByte;
private String responseBody;
private long responseLength;
private Header[] responseHeaders;
private int iState;
/**
 * The class's logger
 */

private static Log m_log = LogFactory.getLog(clienttohostCommands.class);

/**
 * Allowed Commands
  */

public static ArrayList<String> allowCommands = new ArrayList<String>();
static {
    allowCommands.add("48");
    allowCommands.add("49");
//    allowCommands.add("50");
    allowCommands.add("4");
    allowCommands.add("47");
    allowCommands.add("9");
    allowCommands.add("16");
}

/**
 * Executes the business logic.
 *
 * @param mapping	The ActionMapping used to select this instance
 * @param actionForm The optional ActionForm bean for this request (if any)
 * @param request	The HTTP request we are processing
 * @param response   The HTTP response we are creating
 * @return a forward
 * @throws IOException	  if an input/output error occurs
 * @throws ServletException if a servlet exception occurs
 * @throws Exception
 */
public ActionForward executeLogic(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception
{
	HttpSession session = request.getSession();
	SessionState state = WebUtil.getSessionState(request);
    state.refresh(request);
    boolean isRedirect = false;
	ActionForward actionForward = null;
	String sDID;
	String sBody = "";
	String sResponse = "";
	try
	{
//		m_log.debug("ClientToHostCommands. 0");
		String sContentType = request.getHeader("Content-Type");
		m_log.debug("ClientToHostCommands. content type:" + sContentType);
		sBody = getRequestData(request.getInputStream());
		sDID = request.getHeader("DID");

		DesktopDTO desktop = null;
//		m_log.debug("ClientToHostCommands. 10");
		if(sDID == null)
		{
			m_log.debug("ClientToHostCommands. DID header is absent");
			desktop = state.getCurrentDesktop();
			if(desktop != null)
			{
				sDID = desktop.getDid();
			}
			m_log.debug("ClientToHostCommands. Current Desktop DID:" + sDID);
		}
		else
		{
			m_log.debug("ClientToHostCommands. Begin. DID:" + sDID);
			desktop = state.getDesktop(sDID);
		}
		if(desktop == null)
		{
			m_log.debug("ClientToHostCommands. Current desktop: null. Error");
			sResponse = Error2XML(ErrorCode.UNKNOWN_DID_DEFINED.toInt(), ErrorCode.UNKNOWN_DID_DEFINED.toString());
			response.setHeader("content-type", "text/xml; charset=utf-8");
			response.setHeader("content-length", Integer.toString(sResponse.length()));
			response.getWriter().write(sResponse);
			response.flushBuffer();
			return null;
		}
		else if (!desktop.isConnected() && !desktop.getRdpEnabled())
		{
			m_log.debug("ClientToHostCommands. Current desktop not connected");
			sResponse = Error2XML(ErrorCode.DESKTOP_DISCONNECTED.toInt(), ErrorCode.DESKTOP_DISCONNECTED.toString());
			response.setHeader("content-type", "text/xml; charset=utf-8");
			response.setStatus(403);
			response.setHeader("content-length", Integer.toString(sResponse.length()));
			response.getWriter().write(sResponse);
			m_log.debug(state.getDesktop(sDID).isConnected());
			response.flushBuffer();
			return null;
		}
		state.setCurrentDesktop(desktop);

		if(null != request.getHeader("XMLProcessing")) 	// create new XML request to host
			sBody = XMLProcessing(sBody, request, desktop); 		// added by lexx. 28.09.07

		String sURL = "";
//		m_log.debug("XMLProcessing. Connected:" + desktop.isConnected());
//		m_log.debug("XMLProcessing. RDP Enabled:" + desktop.getRdpEnabled());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(sBody)));

		NodeList nodes = null;
		nodes = document.getElementsByTagName("Command");
		String sID = getXMLNodeValue(nodes, "ID");
        if(!desktop.isConnected() && desktop.getRdpEnabled())
		{// add tag 'OnlyRC'
            if (sID.equals("2")) {
                returnToClientWithRDP(state.getUserDTO().getUsername(),response);
                return null;
            }
            //sBody = XMLProcessing(sBody, request, desktop); 		// added by lexx. 07.11.07
            //if onlyRc substitute Host response

        }
		else if(desktop.isHasExpired() && !allowCommands.contains(sID))
		{// add tag 'LicenseExpired'
			sBody = XMLProcessing(sBody, request, desktop); 		// added by lexx. 07.11.07
		}

		sURL = desktop.getIcmURL() + "/LLE5?did=" + sDID;
		m_log.debug("ClientToHostCommands. Translate to:" + sURL);

		TranslateRequestToICM(sURL, sDID, request, sBody, state);
//		response.setHeader("content-type", "text/xml; charset=utf-8");
		response.setStatus(iState);

		for(int i1 = 0; i1 < responseHeaders.length; i1++)
		{
			m_log.debug("Response header: (" + responseHeaders[i1].getName() + ") =" + responseHeaders[i1].getValue());
			response.setHeader(responseHeaders[i1].getName(), responseHeaders[i1].getValue());
		}
		response.setHeader("Content-Length", Long.toString(responseLength));
		m_log.debug("Response header: (Content-Length) =" + Long.toString(responseLength));
        //TODO remove it!!! This code only for pretty printing of response XML
        try {
            Document XMLDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                    new InputSource(
                            new StringReader(responseBody)));
            Element element = XMLDoc.getDocumentElement();
            OutputFormat of = new OutputFormat();
            of.setIndenting(true);
            of.setOmitXMLDeclaration(true);

            StringWriter w = new StringWriter();
            DOMSerializer ser = SerializerFactory.getSerializerFactory(Method.XML).
                    makeSerializer(w, of).asDOMSerializer();
            ser.serialize(element);
            m_log.debug("\n" + w.getBuffer());
        } catch (SAXException e) {
            m_log.debug(responseBody.replaceAll("><", ">\n<"));
        } catch (ParserConfigurationException e) {
            m_log.debug(responseBody.replaceAll("><", ">\n<"));
        } catch( Exception ignored ) {
        }
		PrintWriter writer = response.getWriter();
		if(null != responseBody)
		{
			writer.write(responseBody);
		}
		else
		{
			for(int i = 0; i < responseLength; i++)
			{
				writer.write(responseBodyByte[i]);
			}
		}
		response.flushBuffer();
//		}
		return actionForward;
	}
	catch(ServiceException ex)
	{
		m_log.error("ClientToHostCommands. ServiceException catch:" + ex);
		sResponse = Error2XML(ex.hashCode(), ex.getLocalizedMessage());
	}
	catch(CannotConnectToICMException ex)
	{
		m_log.error("ClientToHostCommands. CannotConnectToICMException catch:" + ex);
		sResponse = Error2XML(ex.hashCode(), ex.getLocalizedMessage());
	}
	catch(Exception ex)
	{
		m_log.error("ClientToHostCommands. catch:" + ex);
		sResponse = Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
	}
	response.setStatus(iState);

	response.setHeader("content-type", "text/xml; charset=utf-8");
	response.setHeader("content-length", Integer.toString(sResponse.length()));
	response.getWriter().write(sResponse);
	response.flushBuffer();
	return null;
}

    private void returnToClientWithRDP(String username, HttpServletResponse response) throws ServiceException, IOException {
        String sResponse = "";
        DocumentBuilder builder;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ServiceException(new PortalError(ErrorCode.SYSTEM_ERROR));
        }

        Document document = builder.newDocument();
        Element elResponse = document.createElement("LLEResponse");

        Element elUserInfo = document.createElement("UserInfo");

        Element elUserName = document.createElement("ID");
        elUserName.setTextContent(username);
        elUserInfo.appendChild(elUserName);

        Element elServices = document.createElement("Services");

        Element elRemoteDesktop = document.createElement("Service");

        Element elRemoteDesktopID = document.createElement("ID");
        elRemoteDesktopID.setTextContent("1");
        elRemoteDesktop.appendChild(elRemoteDesktopID);

        Element elRemoteDesktopGroup = document.createElement("Group");
        elRemoteDesktopGroup.setTextContent("Remote Desktop Control");
        elRemoteDesktop.appendChild(elRemoteDesktopGroup);

        Element elRemoteDesktopName = document.createElement("Name");
        elRemoteDesktopName.setTextContent("Remote Desktop");
        elRemoteDesktop.appendChild(elRemoteDesktopName);

        elServices.appendChild(elRemoteDesktop);

        Element elScreenSharing = document.createElement("Service");

        Element elScreenSharingID = document.createElement("ID");
        elScreenSharingID.setTextContent("2");
        elScreenSharing.appendChild(elScreenSharingID);

        Element elScreenSharingGroup = document.createElement("Group");
        elScreenSharingGroup.setTextContent("Remote Desktop Control");
        elScreenSharing.appendChild(elScreenSharingGroup);

        Element elScreenSharingName = document.createElement("Name");
        elScreenSharingName.setTextContent("Screen Sharing");
        elScreenSharing.appendChild(elScreenSharingName);

        elServices.appendChild(elScreenSharing);

        elUserInfo.appendChild(elServices);
        elResponse.appendChild(elUserInfo);
        
        document.appendChild(elResponse);       
        document.normalizeDocument();
        
        sResponse = XML2String(document);

        response.setStatus(200);
        byte[] utf8Bytes = sResponse.getBytes("UTF-8");
        String utf8Response = new String(utf8Bytes, "UTF-8");

        m_log.debug(utf8Response);

        response.setHeader("content-type", "text/xml; charset=utf-8");
        response.getWriter().print(utf8Response);
        response.flushBuffer();
    }


    private String getRequestData(ServletInputStream is)
{
	char buffer[] = new char[BUF_LEN];
	String sBuffer = "";
	int len = 0, iOffset = 0;
	try
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		if(br != null)
		{
			do
			{
				len = br.read(buffer, 0, BUF_LEN);
				if(len < 0)
					break;
				sBuffer += new String(buffer, 0, len);
			}
//			while(len == BUF_LEN);
			while(true);
		}
	}
	catch(IOException e)
	{
		m_log.debug("Read Request. IOExcept Catch:" + e);
	}
	catch(Exception e)
	{
		m_log.debug("Read Request. Catch:" + e);
//		sBuffer = "";
	}
	return sBuffer;
}

private String XMLProcessing(String sBody, HttpServletRequest request, DesktopDTO desktop) throws ServiceException
{
	String sBuffer = "";
	SessionState state = WebUtil.getSessionState(request);
	try
	{
		m_log.error("XMLProcessing. Input xml:" + sBody);

		AccountDAO accountDAO = DAOFactory.getAccountDAO();
		Account account = accountDAO.getByUsername(state.getUserDTO().getUsername());
//		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"utf-8\"?><Name>FFF</Name>")));
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(sBody)));

		NodeList nodes = null;
		nodes = document.getElementsByTagName("Command");
		String sID = getXMLNodeValue(nodes, "ID");
		m_log.debug("XMLProcessing. Command:" + sID);
		if(sID.equals("97"))
		{ // 2.13.1	User Logon Procedure
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document myDocument = builder.newDocument();
			Element elRequest = myDocument.createElement("LLERequest");
			Element elCommand = myDocument.createElement("Command");
			Element elCommandID = myDocument.createElement("ID");
			Element elUser = myDocument.createElement("UserInfo");
			Element elUserID = myDocument.createElement("ID");

			elCommandID.setTextContent(sID);
			elUserID.setTextContent(account.getUsername());

			elCommand.appendChild(elCommandID);
			elUser.appendChild(elUserID);
			elRequest.appendChild(elCommand);
			elRequest.appendChild(elUser);
			myDocument.appendChild(elRequest);
			myDocument.normalizeDocument();

			m_log.error("XMLProcessing. Add to XML User ID:" + account.getUsername());
			sBuffer = XML2String(myDocument);
			m_log.error("XMLProcessing. Xml was parsed successfully:" + sBuffer);
		}
		else if(sID.equals("2"))
		{ // 2.1.1 Get User Services
			m_log.debug("XMLProcessing. Connected:" + desktop.isConnected());
			m_log.debug("XMLProcessing. RDP Enabled:" + desktop.getRdpEnabled());
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document myDocument = builder.newDocument();
			Element elRequest = myDocument.createElement("LLERequest");
			Element elCommand = myDocument.createElement("Command");
			Element elCommandID = myDocument.createElement("ID");
			Element elParameters = myDocument.createElement("Parameters");

			elCommandID.setTextContent(sID);

			if(!desktop.isConnected() && desktop.getRdpEnabled())
			{
				Element elOnlyRC = myDocument.createElement("OnlyRC");
				elOnlyRC.setTextContent("1");
				elParameters.appendChild(elOnlyRC);
			}

			if(desktop.isHasExpired())
			{
				Element elExpiredLic = myDocument.createElement("ExpiredLicense");
				int iDays = desktop.getDaysToExpiry() * -1;
				elExpiredLic.setTextContent(Integer.toString(iDays));
				elParameters.appendChild(elExpiredLic);
			}

			elCommand.appendChild(elCommandID);
			elRequest.appendChild(elCommand);
			elRequest.appendChild(elParameters);
			myDocument.appendChild(elRequest);
			myDocument.normalizeDocument();

			m_log.error("XMLProcessing. Add to XML User ID:" + account.getUsername());
			sBuffer = XML2String(myDocument);
			m_log.error("XMLProcessing. Xml was parsed successfully:" + sBuffer);
		}
		else
		{
			PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS, "Unknown Command ID");
			throw new ServiceException(error);
		}
	}
	catch (DataException ex)
	{
		m_log.error("catch. DataException. ClientToHostCommands. getByUsername:" + state.getUserDTO().getUsername() + ". " + ex);
		sBuffer = "";
		PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
		throw new ServiceException(error, ex);
	}
	catch (ParserConfigurationException ex)
	{
		m_log.error("catch. ParserConfigurationException. ClientToHostCommands. request xml:" + sBody + ". " + ex);
		sBuffer = "";
		PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
		throw new ServiceException(error, ex);
	}
	catch (SAXException ex)
	{
		m_log.error("catch. SAXException. ClientToHostCommands. request xml:" + sBody + ". " + ex);
		sBuffer = "";
		PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
		throw new ServiceException(error, ex);
	}
	catch (IOException ex)
	{
		m_log.error("catch. IOException. ClientToHostCommands. request xml:" + sBody + ". " + ex);
		sBuffer = "";
		PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
		throw new ServiceException(error, ex);
	}
	return sBuffer;
}


/**
 * Translating the request to ICM server (later to host).
 *
 * @param sDid  - the Desktop Identifier.
 *                e.g. icm1.bar.com/?did=123
 * @param request --
 * @param sBody   --
 * @param state   --
 *                <p/>
 *                * @return byte[] - response of host.
 * @throws com.infowave.c4p.model.CannotConnectToICMException
 *
 */
private void TranslateRequestToICM(String sURL, String sDid, HttpServletRequest request, String sBody, SessionState state) throws CannotConnectToICMException
{
	DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
	PostMethod httpmethod = null;
	String username = state.getUserDTO().getUsername();
	String password = "ignored";
	try
	{
		Desktop desktop = desktopDAO.getByDid(sDid);
		m_log.debug("RedirectRequestToICM. Begin. UserName:" + username);
		HttpClient httpclient = new HttpClient();
		httpmethod = new PostMethod(sURL);
		String referer = request.getHeader("Referer");
		String useragent = request.getHeader("User-Agent");
		String hsid = request.getHeader("HSID"); // added by lexx. 28.09.07
//		String authorization = "Basic " + Base64Encoder.encode(username + ":" + password);
//		httpmethod.setRequestHeader("Authorization", authorization);
		httpmethod.setRequestHeader("Referer", referer);
		httpmethod.setRequestHeader("User-Agent", useragent);
		httpmethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		httpmethod.setRequestHeader("SSID", desktop.getSsid()); // added by lexx. 27.09.07
		httpmethod.setRequestHeader("HSID", hsid); // added by lexx. 28.09.07

//		httpmethod.setRequestHeader("content-type", "text/xml; charset=utf-8");
		//m_log.debug("RedirectRequestToICM Param:(" + sBody + ")");
  //      Part[] parts = {
    //                        new StringPart("xmlrequest", sBody,"utf8")
      //                  };
//        httpmethod.setRequestEntity(new MultipartRequestEntity(parts, httpmethod.getParams()));                        
        NameValuePair nvps[] = new NameValuePair[1];
		nvps[0] = new NameValuePair("xmlrequest", sBody);
		httpmethod.setRequestBody(nvps);
		httpclient.executeMethod(httpmethod);

		responseHeaders = httpmethod.getResponseHeaders();
		responseBodyByte = httpmethod.getResponseBody();
		iState = httpmethod.getStatusCode();
		String sCharset = "";
		if(responseBodyByte.length > 0)
		{
			sCharset = httpmethod.getResponseCharSet();
			Header hContent = httpmethod.getResponseHeader("Content-Type");
			if(hContent.getValue().indexOf("xml") >= 0)
			{
				responseBody = new String(responseBodyByte, sCharset);
			}
			else
			{
				responseBody = null;
			}
		}
		else
		{
			responseBody = null;
		}
		responseLength = responseBodyByte.length;

//		byte responsechar[] = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Name>������</Name>".getBytes("UTF-8");
//		String responseFake2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><LLEResponse><UserInfo><ID>lexx</ID><Services><Service><ID>1</ID><Group>Remote Desktop Control</Group><Name>������ �������</Name><Description></Description></Service><Service><ID>2</ID><Group>Remote Desktop Control</Group><Name>Remote Control 4</Name><Description></Description></Service><Service><ID>4</ID><Group>Transfer files and folders</Group><Name>Single Panel</Name><Description></Description></Service><Service><ID>5</ID><Group>Transfer files and folders</Group><Name>Dual Panel</Name><Description></Description></Service><Service><ID>6</ID><Group></Group><Name>Send Files</Name><Description></Description></Service><Service><ID>12</ID><Group>Transfer files and folders</Group><Name>Google Desktop</Name><Description></Description></Service><Service><ID>13</ID><Group>Transfer files and folders</Group><Name>Windows Desktop Search 3.0 </Name><Description></Description></Service><Service><ID>14</ID><Group></Group><Name>Internet Favorites</Name><Description></Description></Service></Services></UserInfo></LLEResponse>";
//		String responseFake2 = new String(responsechar, "UTF-8");
//		responseBodyByte = responseFake2.getBytes("UTF-8");
//		responseBody = responseFake2;
//		responseLength = responsechar.length;
//		m_log.debug("Response lengtht:" + responseLength + " Fake length:" + responseFake.length());
		m_log.debug("Response lengtht:" + responseLength + " (charset:" + sCharset + ") from host:" + responseBody);
	}
	catch(DataException e)
	{
		m_log.error("catch. DataException. TranslateRequestToICM. Unknown DID (" + sDid + ")", e);
		throw new CannotConnectToICMException(e);
	}
	catch(IOException e)
	{
		m_log.error("catch. TranslateRequestToICM. Could not contact the ICM (" + sURL + ")", e);
		throw new CannotConnectToICMException(e);
	}
	finally
	{
		if(httpmethod != null)
		{
			httpmethod.releaseConnection();
		}
	}
}

public static void main(String[] args)
{
	try
	{
		clienttohostCommands command = new clienttohostCommands();
		command.testXML();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


public void testXML()
{
	try
	{
//		String sID = "3";
		String sResponse, sEnc;

		String responseFake2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Name>FFF</Name>";

//		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"utf-8\"?><Name>FFF</Name>")));
//		Document document = builder.parse("d:\\_request2.xml");
		document.normalize();
		String sBody = XML2String(document);


//		m_log.debug("Response from host:" + responseBody);
		responseBodyByte = responseFake2.getBytes("UTF-8");
		String responseFake = new String(responseBodyByte, "UTF-8");
		int iLe = responseBodyByte.length;

		sResponse = "%3C%3Fxml%20version%3D%221.0%22%20encoding%3D%22UTF-8%22%3F%3E%3CLLEResponse%3E%3CUserInfo%3E%3CID%3Elexx%3C%2FID%3E%3CServices%3E%3CService%3E%3CID%3E1%3C%2FID%3E%3CGroup%3ERemote%20Desktop%20Control%3C%2FGroup%3E%3CName%3ERemote%20Desktop%3C%2FName%3E%3CDescription%3E%3C%2FDescription%3E%3C%2FService%3E%3CService%3E%3CID%3E2%3C%2FID%3E%3CGroup%3ERemote%20Desktop%20Control%3C%2FGroup%3E%3CName%3ERemote%20Control%204%3C%2FName%3E%3CDescription%3E%3C%2FDescription%3E%3C%2FService%3E%3CService%3E%3CID%3E4%3C%2FID%3E%3CGroup%3ETransfer%20files%20and%20folders%3C%2FGroup%3E%3CName%3ESingle%20Panel%3C%2FName%3E%3CDescription%3E%3C%2FDescription%3E%3C%2FService%3E%3CService%3E%3CID%3E5%3C%2FID%3E%3CGroup%3ETransfer%20files%20and%20folders%3C%2FGroup%3E%3CName%3EDual%20Panel%3C%2FName%3E%3CDescription%3E%3C%2FDescription%3E%3C%2FService%3E%3CService%3E%3CID%3E6%3C%2FID%3E%3CGroup%3E%3C%2FGroup%3E%3CName%3ESend%20Files%3C%2FName%3E%3CDescription%3E%3C%2FDescription%3E%3C%2FService%3E%3CService%3E%3CID%3E12%3C%2FID%3E%3CGroup%3ETransfer%20files%20and%20folders%3C%2FGroup%3E%3CName%3EGoogle%20Desktop%3C%2FName%3E%3CDescription%3E%3C%2FDescription%3E%3C%2FService%3E%3CService%3E%3CID%3E13%3C%2FID%3E%3CGroup%3ETransfer%20files%20and%20folders%3C%2FGroup%3E%3CName%3EWindows%20Desktop%20Search%203.0%20%3C%2FName%3E%3CDescription%3E%3C%2FDescription%3E%3C%2FService%3E%3CService%3E%3CID%3E14%3C%2FID%3E%3CGroup%3E%3C%2FGroup%3E%3CName%3EInternet%20Favorites%3C%2FName%3E%3CDescription%3E%3C%2FDescription%3E%3C%2FService%3E%3C%2FServices%3E%3C%2FUserInfo%3E%3C%2FLLEResponse%3E";
		sResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><LLEResponse><UserInfo><ID>lexx</ID><Services><Service><ID>1</ID><Group>Remote Desktop Control</Group><Name>Remote Desktop</Name><Description></Description></Service><Service><ID>2</ID><Group>Remote Desktop Control</Group><Name>Remote Control 4</Name><Description></Description></Service><Service><ID>4</ID><Group>Transfer files and folders</Group><Name>Single Panel</Name><Description></Description></Service><Service><ID>5</ID><Group>Transfer files and folders</Group><Name>Dual Panel</Name><Description></Description></Service><Service><ID>6</ID><Group></Group><Name>Send Files</Name><Description></Description></Service><Service><ID>12</ID><Group>Transfer files and folders</Group><Name>Google Desktop</Name><Description></Description></Service><Service><ID>13</ID><Group>Transfer files and folders</Group><Name>Windows Desktop Search 3.0 </Name><Description></Description></Service><Service><ID>14</ID><Group></Group><Name>Internet Favorites</Name><Description></Description></Service></Services></UserInfo></LLEResponse>";
		sResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><LLEResponse><UserInfo><ID>lexx</ID><Services><Service><ID>1</ID><Group>Remote Desktop Control</Group><Name>������ �������</Name><Description></Description></Service><Service><ID>2</ID><Group>Remote Desktop Control</Group><Name>Remote Control 4</Name><Description></Description></Service><Service><ID>4</ID><Group>Transfer files and folders</Group><Name>Single Panel</Name><Description></Description></Service><Service><ID>5</ID><Group>Transfer files and folders</Group><Name>Dual Panel</Name><Description></Description></Service><Service><ID>6</ID><Group></Group><Name>Send Files</Name><Description></Description></Service><Service><ID>12</ID><Group>Transfer files and folders</Group><Name>Google Desktop</Name><Description></Description></Service><Service><ID>13</ID><Group>Transfer files and folders</Group><Name>Windows Desktop Search 3.0 </Name><Description></Description></Service><Service><ID>14</ID><Group></Group><Name>Internet Favorites</Name><Description></Description></Service></Services></UserInfo></LLEResponse>";
		sEnc = StringManager.htmlencode(sResponse);
//		if(Integer.getInteger(sID) == 3)
//		{// Get Host List
//			sResponse = getHostList(null);
//		}

//		String sURL = "https://lipatnikov.laplink.yumasoft:7443/XMLRequest?did=30aec93c";
//		String sUsername = "lexx";
//		String sPass = "1111";
//		boolean isRedirect = RedirectRequestToICM(sURL, sUsername, sPass, document);
//		sBody = nodes.item(0).getNodeName();
//		int iSize = nodeList.getLength();
//		Node item = nodeList.item(0);
//		sBody = item.getTextContent();
//		sBody = item.getFirstChild().getTextContent();
//		sBody = item.getNodeValue();
//		sBody = nodeList.item(0).getNodeValue();
//		m_log.debug("Children(" + iSize + ")");
	}
	catch(Exception ex)
	{
		System.out.println(ex);
	}

}

}
