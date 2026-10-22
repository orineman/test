/*
 * LoginToHost.java
 *
 * Created on 4 ќкт€брь 2007 г., 13:09
 */

package com.infowave.pda.kernel.actions;

import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.InvalidHostResponseXML;
import com.infowave.pda.utils.xml.XmlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexey.Ogarkov
 */

public class LoginToHost extends BaseAction {

    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(DownloadAction.class);

    private final static String SUCCESS = "success";
    private final static String METHOD_NOT_ALLOWED = "methodNotAllowed";

    @Override
    protected ActionForward preHostRequest(HostConnector hostConnector, HttpServletRequest request,ActionMapping mapping, HttpServletResponse response){
        SessionState state = WebUtil.getSessionState(request);
        hostConnector.getParameters().put("userID",state.getUserDTO().getUsername());
        return null;
    }

    @Override
    protected ActionForward afterHostRequest(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws InvalidHostResponseXML {
        SessionState state = WebUtil.getSessionState(request);
         if (req.getResponseState() == 200) {
            Document responseXML = req.getXmlResponse();
            String hsid = XmlUtils.getXMLNodeValue(responseXML,"HSID");
            state.setHsid(hsid);
            state.setCurrentDesktop(state.getDesktop(request.getParameter("did")));
            WebUtil.setSessionState(request,state);
            return mapping.findForward(SUCCESS);
         } else {
             request.setAttribute("errorXML",req.getXmlResponse());
             return mapping.findForward(METHOD_NOT_ALLOWED);
         }
    }
}
