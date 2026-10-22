package com.infowave.pda.actions.search;

import com.infowave.pda.kernel.actions.BaseAction;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.InvalidHostResponseXML;
import com.infowave.pda.utils.xml.XmlUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 05.02.2008
 * Time: 18:30:06
 * Paccage : com.infowave.pda.actions.search
 */
public class GetSearchItemAction extends BaseAction {

    public static final String EMAIL_VIEW = "emailView";
    public static final String TASK_VIEW = "taskView";
    public static final String EVENT_VIEW = "eventView";
    public static final String NOTE_VIEW = "noteView";
    public static final String CONTACT_VIEW = "contactView";
    public static final String EMAIL_EDIT = "emailEdit";
    public static final String TASK_EDIT = "taskEdit";
    public static final String NOTE_EDIT = "noteEdit";
    public static final String EVENT_EDIT = "eventEdit";
    public static final String CONTACT_EDIT = "contactEdit";

    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(BaseAction.class);

    protected ActionForward afterHostRequest(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
        m_log.debug("Recognizing host response");
        Document doc;
        if (req.getXmlResponse() != null) {
            try {
                doc = req.getXmlResponse();
                doc.normalizeDocument();
                request.setAttribute("hostXML",doc);
                if (XmlUtils.getXMLNodeValue(doc,"EmailFolder")!=null) {
                    String fromMe = XmlUtils.getXMLNodeValue(doc, "FromMe");
                    String notSend = XmlUtils.getXMLNodeValue(doc, "NotSend");
                    if ((fromMe !=null)&&(notSend !=null)) {
                        if (fromMe.equals("1")) {
                            if (notSend.equals("0")) {
                                return mapping.findForward(EMAIL_VIEW);
                            } else {
                                return mapping.findForward(EMAIL_EDIT);
                            }
                        } else {
                            return mapping.findForward(EMAIL_VIEW);
                        }
                    } else {
                        throw new InvalidHostResponseXML("Tag FromME or NotSend missed");
                    }
                } else if (XmlUtils.getXMLNodeValue(doc,"ContactFolder")!=null) {
                    return mapping.findForward(CONTACT_VIEW);
                } else if (XmlUtils.getXMLNodeValue(doc,"TaskFolder")!=null) {
                    return mapping.findForward(TASK_VIEW);
                } else if (XmlUtils.getXMLNodeValue(doc,"NotesFolder")!=null) {
                    return mapping.findForward(NOTE_VIEW);
                } else if (XmlUtils.getXMLNodeValue(doc,"CalendarFolder")!=null) {
                    return mapping.findForward(EVENT_VIEW);
                } else {
                    throw new InvalidHostResponseXML("Not Supported Item Format");    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new InvalidHostResponseXML("DOM Parser failed, invalid XML structure");
            }
           
        } else {
            throw new InvalidHostResponseXML("DOM Parser failed, invalid XML structure");
        }
    }
}
