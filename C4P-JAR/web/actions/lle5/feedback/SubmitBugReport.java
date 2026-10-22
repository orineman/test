package com.infowave.c4p.web.actions.lle5.feedback;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.model.CannotConnectToICMException;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.web.util.BrowserInfo;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

/**
 * Class SubmitBugReport ...
 *
 * @author alexogar
 *         Created on Jul 28, 2008
 */
public abstract class SubmitBugReport extends HttpServlet {

    /**
     * The class's logger
     */
    protected static Log m_log = LogFactory.getLog(SubmitBugReport.class);
    protected SessionState state;
    protected UserDTO user;
    protected DesktopDTO desktop;
    protected HostReport hostReport;
    protected BugReport bugReport = new BugReport();;
    protected String hostLogContentType;

    /**
     * Method doGet ...
     *
     * @param request  of type HttpServletRequest
     * @param response of type HttpServletResponse
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("empty.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Method execute ...
     *
     * @param request  of type HttpServletRequest
     * @param response of type HttpServletResponse
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{        
        state = WebUtil.getSessionState(request);

        //If state is null then this user are not loginned yet.
        if (state != null) {
            user = state.getUserDTO();
            desktop = state.getCurrentDesktop();
            bugReport.setUser(user);
            bugReport.setDesktop(desktop);
            bugReport.setClientCapabilities(state.getClientCapabilities());


            String userComment = request.getParameter("userComment");
            if (userComment != null) {
                bugReport.setUserComment(userComment);
            }

            if (desktop!=null) {
                String url = desktop.getIcmURL() + "/LLE5?did=" + desktop.getDid();

                hostReport = null;
                try {
                    byte[] b = requestToHost(url, state.getHsid(), desktop.getDid(), request, constructHostRequest());

                    hostReport = getDesktopInfo(b);
                } catch (Exception e) {

                }

                bugReport.setHostReport(hostReport);
                try {
                    byte[] b = requestToHost(url, state.getHsid(), desktop.getDid(), request, constructHostLogRequest());
                    bugReport.setHostLogFile(b);
                } catch (CannotConnectToICMException e) {

                }
            }
        }

        bugReport.setBrowserInfo(new BrowserInfo(request, request.getSession()));

        sendBugReport();
    }

    private String constructHostRequest() {
        return "";       //TODO:Construct request
    }

    private String constructHostLogRequest() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><LLERequest><Command><ID>4</ID><Params><BackURL>https%3A%2F%2F192.168.100.254%2Fpda%2FGetFolderContent.do%3F%26amp%3BparentFSElemIDL%3D%26amp%3BparentFSElemPath%3D%26amp%3Bgo%3Dexit</BackURL></Params></Command><FSElement><FullName>C%3a%5cDocuments%20and%20Settings%5calexogar%5cDesktop%5c_llservermain.log</FullName></FSElement></LLERequest>";       //TODO:Construct request
    }

    protected HostReport getDesktopInfo(byte[] b) {
        XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(b));

        decoder.setExceptionListener(new ExceptionListener() {

            public void exceptionThrown(Exception e) {
                e.printStackTrace();
            }
        });

        return (HostReport) decoder.readObject();
    }

    private byte[] requestToHost(String sURL, String hsid, String sDid, HttpServletRequest request, String sBody) throws CannotConnectToICMException {
        DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
        PostMethod httpmethod = null;
        try {
            Desktop desktop = desktopDAO.getByDid(sDid);
            HttpClient httpclient = new HttpClient();
            httpmethod = new PostMethod(sURL);
            String referer = request.getHeader("Referer");
            String useragent = request.getHeader("User-Agent");
            httpmethod.setRequestHeader("Referer", referer);
            httpmethod.setRequestHeader("User-Agent", useragent);
            httpmethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpmethod.setRequestHeader("SSID", desktop.getSsid());
            httpmethod.setRequestHeader("HSID", hsid);

            NameValuePair nvps[] = new NameValuePair[1];
            nvps[0] = new NameValuePair("xmlrequest", sBody);
            httpmethod.setRequestBody(nvps);
            httpclient.executeMethod(httpmethod);
            hostLogContentType = httpmethod.getResponseHeader("Content-Type").getValue();
            int status = httpmethod.getStatusCode();
            m_log.debug("Host response status:" + status);

            if (status == 200) {
                return httpmethod.getResponseBody();
            } else {
                return httpmethod.getResponseBody();
            }
        }
        catch (DataException e) {
            m_log.error("catch. DataException. translateRequestToICM. Unknown DID (" + sDid + ")", e);
            throw new CannotConnectToICMException(e);
        }
        catch (IOException e) {
            m_log.error("catch. translateRequestToICM. Could not contact the ICM (" + sURL + ")", e);
            throw new CannotConnectToICMException(e);
        }
        finally {
            if (httpmethod != null) {
                httpmethod.releaseConnection();
            }
        }
    }

    protected abstract boolean sendBugReport();

    public static void main(String args[]) throws FileNotFoundException {
        FileOutputStream fstream = new FileOutputStream("foo.xml");
        XMLEncoder enc = new XMLEncoder(fstream);

        HostReport h = new HostReport();
        h.setOs("Linux");
        h.setOutlook("Tunderbird");
        h.setUsercreditcard("1111");
        h.setFile(new FileInputStream("foo1.xml"));
        ArrayList list = new ArrayList();
        list.add("sdsdsd");
        list.add("sdsdsd");
        list.add("sdsdsd");
        list.add("sdsdsd");
        h.setPasswords(list);

        enc.writeObject(h);
        enc.flush();

        FileInputStream iStream = new FileInputStream("foo.xml");
        XMLDecoder dec = new XMLDecoder(iStream);

        HostReport h1 = (HostReport) dec.readObject();
        System.out.println("HostReport:" + h1.getOs() + ";" + h1.getOutlook() + ";" + h1.getPasswords().get(0));
    }
}