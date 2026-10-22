package com.infowave.pda.kernel.hostConnector;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.model.CannotConnectToICMException;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.web.actions.lle5.hostCommands;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.pda.kernel.filters.AddAttachFilter;
import com.infowave.pda.kernel.hostConnector.errors.CannotFindDesktop;
import com.infowave.pda.kernel.hostConnector.errors.CouldNotFindProtocolJSP;
import com.infowave.pda.kernel.hostConnector.errors.InvalidHostResponseXML;
import com.infowave.pda.kernel.hostConnector.errors.InvalidProtocolData;
import com.infowave.pda.kernel.objects.Attachment;
import com.infowave.pda.utils.xml.XmlUtils;
import com.sun.org.apache.xml.internal.serialize.DOMSerializer;
import com.sun.org.apache.xml.internal.serialize.Method;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.SerializerFactory;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Alexey.Ogarkov
 */
public class HostConnector {

    private Lock lock = new ReentrantLock();

    private byte[] responseBodyByte;

    private InputStream responseBody;

    private long responseLength;

    private ArrayList<Header> requestHeaders;

    private Header[] responseHeaders;

    private int responseState;

    private Map<String, String> parameters;

    private String protocolPath;

    private String protocolResponseBody;

    private Document xmlResponse;

    private SessionState state;

    private static ArrayList<String> allowCommands = new ArrayList<String>();

    static {
        allowCommands.add("/pdaProtocol/request/search/googleSearch.jsp");
        allowCommands.add("/pdaProtocol/request/search/windowsSearch.jsp");
        allowCommands.add("/pdaProtocol/request/search/getSearchItem.jsp");
        allowCommands.add("/pdaProtocol/request/favorites/getFavoritesList.jsp");
        allowCommands.add("/pdaProtocol/request/services/userLogonProcedure.jsp");
        allowCommands.add("/pdaProtocol/request/storage/getUserParameters.jsp");
        allowCommands.add("/pdaProtocol/request/storage/saveUserPrameters.jsp");
        allowCommands.add("/pdaProtocol/request/services/getServiceListRequest.jsp");
    }

    private InputStream protocolBody;
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(HostConnector.class);

    private HttpServletRequest request;

    private ErrorCode error;
    private HttpClient httpClient;
    private String requestMethod;
    private String referer;
    private String useragent;

    /**
     * Creates a new instance of HostConnector
     * @param request HttpServletRequest
     */
    public HostConnector(HttpServletRequest request) {
        initialize(request);
    }

    public void initialize(HttpServletRequest request) {
        this.request = request;
        requestMethod = this.request.getMethod();
        referer = request.getHeader("Referer");
        useragent = request.getHeader("User-Agent");
        this.state = WebUtil.getSessionState(request);
        this.setParameters(request);
        this.setRequestHeaders(request);
        MultiThreadedHttpConnectionManager connectionManager =
            new MultiThreadedHttpConnectionManager();

          httpClient = new HttpClient(connectionManager);
        httpClient.getHostConfiguration().setHost(
                request.getServerName(),
                80,
                "http"
        );

        httpClient.getState().setCookiePolicy(CookiePolicy.COMPATIBILITY);
        if (request.getCookies()!=null) {
            for (javax.servlet.http.Cookie cooky : request.getCookies()) {
                /*
               public Cookie(String name,
             String value,
             String domain,
             String path,
             Date expires,
             boolean secure)
                */
                org.apache.commons.httpclient.Cookie c = new org.apache.commons.httpclient.Cookie();
                c.setName(cooky.getName());
                c.setValue(cooky.getValue());
                c.setPath(request.getContextPath());
                c.setSecure(cooky.getSecure());
                c.setDomain(request.getServerName());
                httpClient.getState().addCookie(c);
            }
        } else {
            org.apache.commons.httpclient.Cookie c = new org.apache.commons.httpclient.Cookie();
            c.setName("JSESSIONID");
            c.setValue(request.getSession().getId());
            c.setPath(request.getContextPath());
            c.setDomain(request.getServerName());
            httpClient.getState().addCookie(c);
        }
    }


    synchronized public Document send(String protocolURL) throws CouldNotFindProtocolJSP, InvalidProtocolData, CannotConnectToICMException, InvalidHostResponseXML, ParserConfigurationException, CannotFindDesktop {
        DesktopDTO desktop = state.getCurrentDesktop();
        if (desktop != null) {
            if (desktop.isHasExpired()) {
                if (!allowCommands.contains(protocolURL)) {
                    return errorDocument(ErrorCode.ACCESS_RESTRICTED);
                }
            }
        }
        String hostRequestBody = this.getProtocolXML(protocolURL);

        if (this.isAttachRequest(request)) {
            hostRequestBody = this.updateProtocolRequest(request);
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("xmlrequest", hostRequestBody);

        return send(params);
    }

    private Document errorDocument(ErrorCode accessRestricted) throws ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document myDocument = builder.newDocument();
        Element elError = myDocument.createElement("LLEError");
        Element elCode = myDocument.createElement("Code");
        Element elDescription = myDocument.createElement("ID");

        elCode.setTextContent(String.valueOf(accessRestricted.toInt()));
        elDescription.setTextContent(accessRestricted.toString());

        elError.appendChild(elCode);
        elError.appendChild(elDescription);
        myDocument.appendChild(elError);
        return myDocument;
    }

    public Document send(HashMap<String, String> params) throws CouldNotFindProtocolJSP, InvalidProtocolData, CannotConnectToICMException, InvalidHostResponseXML, ParserConfigurationException, CannotFindDesktop {
        DesktopDTO desktop;
        if (request.getParameter("did") != null) {
            desktop = state.getDesktop(request.getParameter("did"));
            state.setCurrentDesktop(desktop);
        } else if (request.getHeader("did") != null) {
            desktop = state.getDesktop(request.getHeader("did"));
            state.setCurrentDesktop(desktop);
        } else if (state.getCurrentDesktop() != null) {
            desktop = state.getCurrentDesktop();
        } else {
            throw new CannotFindDesktop("Invalid DID parameter");
        }

        String icmURL = desktop.getIcmURL() + "/LLE5?did=" + desktop.getDid();
        m_log.debug(params.get("xmlrequest"));
        InputStream io = this.translateRequestToICM(desktop.getDid(), icmURL, request, state, params);
        return this.checkForError(io);
    }

    public String updateProtocolRequest(HttpServletRequest request) {
        DocumentBuilder builder;
        Document protocolXML;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            protocolXML = builder.parse(this.getProtocolBody());
            Node attach = protocolXML.getElementsByTagName("Attach").item(0);
            if (attach == null) {
                return this.getProtocolResponseBody();
            }
            ArrayList<Attachment> attaches = (ArrayList<Attachment>) request.getSession().getAttribute(AddAttachFilter.ATTACH_LIST);
            int i = 0;
            for (Attachment a : attaches) {
                Element attachEl = protocolXML.createElement("AttachElement" + i);

                Element fsElement = protocolXML.createElement("FSElement");
                Element fullName = protocolXML.createElement("FullName");
                fullName.setTextContent(a.getFullName());
                fsElement.appendChild(fullName);

                Element AsLink = protocolXML.createElement("AsLink");
                AsLink.setTextContent(String.valueOf(a.getLink() ? 0 : 1));

                attach.appendChild(attachEl);
            }

            return hostCommands.XML2String(protocolXML);

        } catch (Exception e) {
            return this.getProtocolResponseBody();
        }
    }

    public boolean isAttachRequest(HttpServletRequest request) {
        return (request.getSession().getAttribute(AddAttachFilter.ATTACH_LIST) != null);
    }

    synchronized private Document checkForError(InputStream io) throws InvalidHostResponseXML {
        DocumentBuilder builder;
        Document xml = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            if (this.responseLength != 0) {
                xml = builder.parse(io);
                xmlResponse = xml;
            }
        } catch (Exception e) {
            m_log.error("Cannot recognize host response xml.This error will be throw later");
            throw new InvalidHostResponseXML("DOM Parser failed, invalid XML structure");
        }

        if (XmlUtils.getXMLNodeValue(xmlResponse, "LLEError") != null) {
            int code = Integer.parseInt(XmlUtils.getXMLNodeValue(xmlResponse, "Code"));
            String description = XmlUtils.getXMLNodeValue(xmlResponse, "Description");
            error = new ErrorCode(code, description);
        }

        return xml;
    }


    synchronized public String getProtocolXML(String protocolPath) throws CouldNotFindProtocolJSP, InvalidProtocolData {
        HttpMethod httpMethod = null;
        try {

            m_log.debug("++Getting Protocol JSP");

            if (this.requestMethod.equalsIgnoreCase("post")) {
                httpMethod = new PostMethod(protocolPath);
            } else {
                httpMethod = new GetMethod(protocolPath);
            }

            this.setMethodHeaders(httpMethod);

            httpMethod = this.setMethodParameters(httpMethod);
            httpMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpMethod.setRequestHeader("protocolPath", protocolPath);
            int status = httpClient.executeMethod(httpMethod);
            if (status == HttpServletResponse.SC_OK) {
                setProtocolBody(httpMethod.getResponseBodyAsStream());
                setProtocolResponseBody(httpMethod.getResponseBodyAsString());
                m_log.debug("Protocol Response:" + getProtocolResponseBody());
                return getProtocolResponseBody();
            } else {
                m_log.debug("Invalid validating JSP");
                throw new InvalidProtocolData("Invalid Data,Protocol JSP return status code : " + status);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new CouldNotFindProtocolJSP("Invalid path to JSP Source");
        } finally {
            if (httpMethod != null) {
                httpMethod.releaseConnection();
            }
        }
    }

    private HttpMethod setMethodParameters(HttpMethod method) {
        NameValuePair[] nvps = new NameValuePair[parameters.size()];
        int i = 0;
        for (Map.Entry entry : parameters.entrySet()) {
            nvps[i] = new NameValuePair((String) entry.getKey(), (String) entry.getValue());
            i++;
        }
        //method.setQueryString(nvps);
        if (this.request.getMethod().equalsIgnoreCase("post")) {
            ((PostMethod) method).setRequestBody(nvps);
        } else {
            method.setQueryString(nvps);
        }

        method.removeRequestHeader("content-length");
        return method;
    }

    private void setMethodHeaders(HttpMethod method) {
        for (Header h : requestHeaders) {
            method.addRequestHeader(h);
        }
    }

    public void addParameter(String name, String value) {
        if (this.parameters.containsKey(name)) {
            this.parameters.remove(name);
        }
        this.parameters.put(name, value);
    }

    public void addRequestHeader(Header header) {
        this.requestHeaders.add(header);
    }

    public void addRequestHeader(String name, String value) {
        this.requestHeaders.add(new Header(name, value));
    }

    synchronized private InputStream translateRequestToICM(String did, String icmUrl, HttpServletRequest request, SessionState state, HashMap<String, String> params) throws CannotConnectToICMException {
        DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();

        PostMethod httpmethod = null;
        String username = state.getUserDTO().getUsername();
        String password = "ignored";
        try {
            m_log.debug("+++RedirectRequestToICM. Begin. UserName:" + username);
            HttpClient httpclient = new HttpClient();
            httpmethod = new PostMethod(icmUrl);
            //String authorization = "Basic " + Base64Encoder.encode(username + ":" + password);

            //httpmethod.setRequestHeader("Authorization", authorization);
            httpmethod.setRequestHeader("Referer", referer);
            httpmethod.setRequestHeader("User-Agent", useragent);
            httpmethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpmethod.setRequestHeader("SSID", desktopDAO.getByDid(did).getSsid());
            if (state.getHsid() != null) {
                httpmethod.setRequestHeader("HSID", state.getHsid());
            }
            //m_log.debug("+++RedirectRequestToICM Param:(" + sBody + ")");

            NameValuePair nvps[] = new NameValuePair[params.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (i < params.size()) {
                    nvps[i] = new NameValuePair(entry.getKey(), entry.getValue());
                    i++;
                }
            }

            httpmethod.setRequestBody(nvps);
            httpclient.executeMethod(httpmethod);

            setResponseHeaders(httpmethod.getResponseHeaders());
            setResponseBodyByte(httpmethod.getResponseBody());
            //TODO remove it!!! This code only for pretty printing of response XML
            try {
                Document XMLDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                        new InputSource(
                                new StringReader(httpmethod.getResponseBodyAsString())));
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
                m_log.debug(httpmethod.getResponseBodyAsString().replaceAll("><", ">\n<"));
            } catch (ParserConfigurationException e) {
                m_log.debug(httpmethod.getResponseBodyAsString().replaceAll("><", ">\n<"));
            }

            setResponseState(httpmethod.getStatusCode());
            setResponseBody(httpmethod.getResponseBodyAsStream());
            setResponseLength(getResponseBodyByte().length);
            return httpmethod.getResponseBodyAsStream();
        } catch (DataException e) {
            m_log.error("catch. DataException. TranslateRequestToICM. Unknown DID (" + did + ")", e);
            throw new CannotConnectToICMException(e);
        } catch (IOException e) {
            m_log.error("catch. TranslateRequestToICM. Could not contact the ICM (" + icmUrl + ")", e);
            throw new CannotConnectToICMException(e);
        } finally {
            if (httpmethod != null) {
                httpmethod.releaseConnection();
            }
        }
    }

    protected void setHostRequestParameters() {

    }

    private void setParameters(HttpServletRequest request) {
        if (this.parameters == null) {
            this.parameters = new HashMap<String, String>();
        }
        //this.parameters = (Map<String, String>) request.getParameterNames()getParameterMap();
        Enumeration en = request.getParameterNames();

        while (en.hasMoreElements()) {
            String s = (String) en.nextElement();
            this.parameters.put(s, request.getParameter(s));
        }

        Enumeration an = request.getAttributeNames();

        while (an.hasMoreElements()) {
            String s = (String) an.nextElement();
            if (request.getAttribute(s) instanceof String) {
                this.parameters.put(s, (String) request.getAttribute(s));
            }
        }
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getProtocolPath() {
        return protocolPath;
    }

    public void setProtocolPath(String protocolPath) {
        this.protocolPath = protocolPath;
    }

    public byte[] getResponseBodyByte() {
        return responseBodyByte;
    }

    public void setResponseBodyByte(byte[] responseBodyByte) {
        this.responseBodyByte = responseBodyByte;
    }

    public InputStream getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(InputStream responseBody) {
        this.responseBody = responseBody;
    }

    public long getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(long responseLength) {
        this.responseLength = responseLength;
    }

    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public ArrayList<Header> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(ArrayList<Header> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    private void setRequestHeaders(HttpServletRequest request) {
        if (requestHeaders == null) {
            requestHeaders = new ArrayList<Header>();
        }
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = (String) headerNames.nextElement();
            String value = request.getHeader(name);
            this.requestHeaders.add(new Header(name, value));
        }
    }

    public Document getXmlResponse() {
        return xmlResponse;
    }

    public void setXmlResponse(Document xmlResponse) {
        this.xmlResponse = xmlResponse;
    }

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }

    public int getResponseState() {
        return responseState;
    }

    public void setResponseState(int responseState) {
        this.responseState = responseState;
    }

    public String getProtocolResponseBody() {
        return protocolResponseBody;
    }

    public void setProtocolResponseBody(String protocolResponseBody) {
        this.protocolResponseBody = protocolResponseBody;
    }

    public InputStream getProtocolBody() {
        return protocolBody;
    }

    public void setProtocolBody(InputStream protocolBody) {
        this.protocolBody = protocolBody;
    }
}
