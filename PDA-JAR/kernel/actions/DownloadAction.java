package com.infowave.pda.kernel.actions;

import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.InvalidHostResponseXML;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 25.09.2007
 * Time: 18:49:07
 * To change this template use File | Settings | File Templates.
 */
public class DownloadAction extends BaseAction {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(DownloadAction.class);

    @Override
    protected ActionForward afterHostRequest(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response)  throws InvalidHostResponseXML {
        ActionForward forward = mapping.findForward("success");
        String path = forward.getPath();
        if (path.indexOf("contentType")!=-1) {
            response.setContentType(path.split("=")[1]);
        }
        InputStream resourceStream = req.getResponseBody();
        if (resourceStream != null) {
            //m_log.debug("Found requested resource: " + request.getRequestURI());
            byte[] buff = new byte[256];
            int nbBytes = 0;
            try {
                while ((nbBytes = resourceStream.read(buff)) != -1) {
                    response.getOutputStream().write(buff, 0, nbBytes);
                }
                resourceStream.close();
                response.flushBuffer();
            } catch (IOException e) {
                throw new InvalidHostResponseXML("Invalide content fro host");
            }
        } else {
            m_log.warn("Could not find requested resource: " + request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return null;
    }
}
