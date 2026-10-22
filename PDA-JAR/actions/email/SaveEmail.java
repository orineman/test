package com.infowave.pda.actions.email;

import com.infowave.pda.kernel.actions.BaseAction;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.InvalidHostResponseXML;
import com.infowave.pda.kernel.objects.Attachment;
import com.infowave.pda.kernel.filters.AddAttachFilter;
import com.infowave.pda.utils.xml.XmlUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 10.10.2007
 * Time: 17:38:06
 * To change this template use File | Settings | File Templates.
 */
public class SaveEmail extends BaseAction {
    protected ActionForward afterHostRequest(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("useraction").equalsIgnoreCase("send")) {
            Document doc;
            String messageID = "";
            String folderID = "";
            if (req.getXmlResponse() != null) {
                try {
                    doc = req.getXmlResponse();
                    doc.normalizeDocument();
                    ArrayList<String> arr = XmlUtils.getXMLNodeValues(doc, "ID");
                    folderID = arr.get(0);
                    messageID = arr.get(1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new InvalidHostResponseXML("DOM Parser failed, invalid XML structure");
                }
                request.setAttribute("messageID", messageID);
                request.setAttribute("folderID", folderID);
                request.setAttribute("go","exit");
            } else {
                if (request.getParameter("folderID")!=null) {
                    request.setAttribute("folderID", request.getParameter("folderID"));
                }
                if (request.getParameter("messageID")!=null) {
                    request.setAttribute("messageID", request.getParameter("messageID"));    
                }
            }
            if (request.getParameter("useraction") != null) {
                if (mapping.findForward(request.getParameter("useraction")) != null) {
                    return mapping.findForward(request.getParameter("useraction"));
                }
            }
            return mapping.findForward(SUCCESS);
        } else {
            request.setAttribute("go","exit");
            return super.afterHostRequest(req, mapping, request, response);
        }
    }

/*
    public ActionForward send(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws InvalidHostResponseXML {
            Document doc;
            String messageID = "";
            String folderID = "";
            try {
                doc = req.getXmlResponse();
                doc.normalizeDocument();
                ArrayList<String> arr = XmlUtils.getXMLNodeValues(doc, "ID");
                folderID = arr.get(0);
                messageID = arr.get(1);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new InvalidHostResponseXML("DOM Parser failed, invalid XML structure");
            }
            request.setAttribute("messageID", messageID);
            request.setAttribute("folderID", folderID);
            if (request.getParameter("action") != null) {
                if (mapping.findForward(request.getParameter("action")) != null) {
                    return mapping.findForward(request.getParameter("action"));
                }
            }
            return mapping.findForward(SUCCESS);        
    }

    public ActionForward addAttach(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("AddAttachAction");    
    }

*/

}
