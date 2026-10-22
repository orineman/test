package com.infowave.pda.kernel.actions;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 18.02.2008
 * Time: 13:56:27
 * Paccage : com.infowave.pda.kernel.actions
 */

import com.infowave.c4p.model.CannotConnectToICMException;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.*;
import org.apache.commons.httpclient.Header;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseDownloadAction extends org.apache.struts.actions.DownloadAction {


    /* forward name="success" path="" */
    public final static String SUCCESS = "success";

    protected DynaActionForm dynamicForm;

    protected HostConnector req;

    private SessionState state;

    protected String protocolURL;
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(BaseAction.class);

    public BaseDownloadAction() {
    }

    protected StreamInfo getStreamInfo(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) throws Exception {
        m_log.debug("++Base Action Execute");

        protocolURL = actionMapping.getParameter();
        if (protocolURL == null) {
            protocolURL = httpServletRequest.getParameter("protocol");
        }

        if (protocolURL != null) {
            req = new HostConnector(httpServletRequest);
        } else {
            throw new InvalidInputParameters("parameter protocol not exist");
        }

        m_log.debug("Sending HostConnector to Host");

        try {
            req.send(protocolURL);
        } catch (CouldNotFindProtocolJSP couldNotFindProtocolJSP) {
            m_log.error("CouldNotFindProtocolJSP:" + couldNotFindProtocolJSP.getMessage());
        } catch (InvalidProtocolData invalidProtocolData) {
            m_log.error("InvalidProtocolData:" + invalidProtocolData.getMessage());
        } catch (CannotConnectToICMException e) {
            m_log.error("CannotConnectToICMException:" + e.getMessage());
        } catch (InvalidHostResponseXML invalidHostResponseXML) {
            m_log.error("InvalidHostResponseXML:" + invalidHostResponseXML.getMessage());
        } catch (ParserConfigurationException e) {
            m_log.error("ParserConfigurationException:" + e.getMessage());
        } catch (CannotFindDesktop cannotFindDesktop) {
            m_log.error("CannotFindDesktop:" + cannotFindDesktop.getMessage());
        }

        this.setResponseHeaders(httpServletResponse, req);

        if (req.getError() != null) {
            return null;
        }

        ActionForward forward = actionMapping.findForward("success");
        String path = forward.getPath();
        String contentType = "";

        if (path.indexOf("contentType") != -1) {
            contentType = path.split("=")[1];
        }

        Header[] respHeaders = req.getResponseHeaders();
        for (int i=0;i<respHeaders.length;i++) {
            if (respHeaders[i].getName().equalsIgnoreCase("content-type")) {
                contentType = respHeaders[i].getValue();
                break;
            }
        }
        //InputStream resourceStream = req.getResponseBody();
        byte[] bytes = req.getResponseBodyByte();

        return new ByteArrayStreamInfo(contentType, bytes);
    }

    protected void setResponseHeaders(HttpServletResponse response, HostConnector req) {
        Header[] headers = req.getResponseHeaders();
        if (headers != null) {
            for (Header header : headers) {
                response.setHeader(header.getName(), header.getValue());
            }
        }
    }

    protected class ByteArrayStreamInfo implements StreamInfo {

        protected String contentType;
        protected byte[] bytes;

        public ByteArrayStreamInfo(String contentType, byte[] bytes) {
            this.contentType = contentType;
            this.bytes = bytes;
        }

        public String getContentType() {
            return contentType;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(bytes);
        }
    }
}
