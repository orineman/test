package com.infowave.pda.kernel.actions;

import com.infowave.c4p.model.CannotConnectToICMException;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.util.HashMap;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 15.02.2008
 * Time: 14:15:06
 * Paccage : com.infowave.pda.kernel.actions
 */
public class UploadAction extends BaseAction {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(UploadAction.class);

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param form     The optional ActionForm bean for this request.
     * @param request  The HTTP HostConnector we are processing.
     * @param response The HTTP Response we are processing.
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        m_log.debug("++Upload Action Execute");
        protocolURL = mapping.getParameter();
        if (protocolURL == null) {
            protocolURL = request.getParameter("protocol");
        }

        if (protocolURL != null) {
            req = new HostConnector(request);
        } else {
            throw new InvalidInputParameters("parameter protocol not exist");
        }

        /*this method can be overriden if needed*/
        ActionForward forward = preHostRequest(req, request, mapping, response);
        if (forward != null) {
            return forward;
        }
        m_log.debug("Sending HostConnector to Host");

        try {
            String hostRequestBody = req.getProtocolXML( protocolURL);
            if (req.isAttachRequest(request)) {
                hostRequestBody = req.updateProtocolRequest(request);
            }
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("xmlrequest", hostRequestBody);
            if (request.getParameter("fileName")!=null) {
                params.put("fileName",request.getParameter("fileName"));
            }
            req.send(params);
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

        this.setResponseHeaders(response, req);

        if (req.getError() != null) {
            return errorHandler(req, mapping, request, response);
        }
        /*This method can be overriden */
        try {
            return afterHostRequest(req, mapping, request, response);
        } catch (InvalidHostResponseXML e) {
            return null;
        }
    }
}
