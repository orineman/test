package com.infowave.pda.actions.storage;

import com.infowave.pda.kernel.actions.BaseAction;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.InvalidHostResponseXML;
import com.infowave.pda.kernel.objects.UserSettings;
import com.infowave.pda.kernel.objects.WeatherService;
import com.infowave.pda.utils.xml.XmlUtils;
import com.infowave.c4p.util.ErrorCode;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 29.11.2007
 * Time: 10:43:51
 * Paccage : com.infowave.pda.actions.storage
 */
public class GetUserParameters extends BaseAction {
    static final String PROTOCOL_URL = "mainPDAPageProtocolURL";
    
    protected ActionForward errorHandler(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ErrorCode error = req.getError();
        if (error.toInt()==1016) {
            UserSettings settings = new UserSettings();
            HttpSession session = request.getSession();
            session.setAttribute("user_settings",settings);
            session.setAttribute(PROTOCOL_URL,settings.getProtocolURL());
            session.setAttribute("PDA_items_on_page",settings.getItems_on_page());
            return mapping.findForward(SUCCESS);        
        } else {
            return super.errorHandler(req,mapping,request,response);
        }
    }

    protected ActionForward afterHostRequest(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Document doc;
        HttpSession session = request.getSession();
        session.setAttribute("PDA_items_on_page", 4);
        if (req.getXmlResponse() != null) {
            try {
                doc = req.getXmlResponse();                
                doc.normalizeDocument();
                NodeList storage = doc.getElementsByTagName("settings");
                String items = XmlUtils.getFirstXMLNodeValue(storage,"items_on_page").trim();
                String background_color = XmlUtils.getFirstXMLNodeValue(storage,"background_color").trim();
                String text_color = XmlUtils.getFirstXMLNodeValue(storage,"text_color").trim();
                String link_color = XmlUtils.getFirstXMLNodeValue(storage,"link_color").trim();
                String selection_color = XmlUtils.getFirstXMLNodeValue(storage,"selection_color").trim();
                UserSettings settings = new UserSettings();
                if (!items.equals("")) {
                    session.setAttribute("PDA_items_on_page", Integer.parseInt(XmlUtils.getFirstXMLNodeValue(storage,"items_on_page")));
                    settings.setItems_on_page(Integer.parseInt(XmlUtils.getFirstXMLNodeValue(storage,"items_on_page")));
                }
//                session.setAttribute("LINK_COLOR", link_color.equals("")?"blue":link_color);
//                session.setAttribute("BACKGROUND_COLOR", background_color.equals("")?"white":background_color);
//                session.setAttribute("TEXT_COLOR", text_color.equals("")?"black":text_color);
//                session.setAttribute("SELECTION_COLOR", text_color.equals("")?"aqua":text_color);
                settings.setLink_color(link_color.equals("")?"blue":link_color);
                settings.setBackground_color(background_color.equals("")?"white":background_color);
                settings.setText_color(text_color.equals("")?"black":text_color);
                settings.setSelection_color(selection_color.equals("")?"aqua":selection_color);

                NodeList weatherXML = doc.getElementsByTagName("weather");
                String locale = XmlUtils.getFirstXMLNodeValue(weatherXML,"locale").trim();
                String location = XmlUtils.getFirstXMLNodeValue(weatherXML,"location").trim();
                String enabled = XmlUtils.getStrongXMLNodeValue(weatherXML,"enabled");
                WeatherService weather = new WeatherService();
                if  (locale.equals("")) {
                    weather.setLocale(Locale.US);
                } else {
                    weather.setLocale(new Locale(locale));
                }
                weather.setEnabled(enabled!=null);
                weather.setLocation(location);

                NodeList generalXML = doc.getElementsByTagName("general_settings");
                settings.setShow_hints(XmlUtils.getStrongXMLNodeValue(generalXML,"show_hints")!=null);

                settings.setWeather_service(weather);
                /*
                        <c:if test="${not empty param.email_service}"><email_service/></c:if>
                        <c:if test="${not empty param.event_service}"><event_service/></c:if>
                        <c:if test="${not empty param.contact_service}"><contact_service/></c:if>
                        <c:if test="${not empty param.note_service}"><note_service/></c:if>
                        <c:if test="${not empty param.task_service}"><task_service/></c:if>
                        <c:if test="${not empty param.wds_service}"><wds_service/></c:if>
                        <c:if test="${not empty param.gds_service}"><gds_service/></c:if>
                        <c:if test="${not empty param.bookmarks_service}"><bookmarks_service/></c:if>
                 */
                NodeList contentXML = doc.getElementsByTagName("content_settings");
                settings.setEmail_service(XmlUtils.getStrongXMLNodeValue(contentXML,"email_service")!=null);
                settings.setEvent_service(XmlUtils.getStrongXMLNodeValue(contentXML,"event_service")!=null);
                settings.setContact_service(XmlUtils.getStrongXMLNodeValue(contentXML,"contact_service")!=null);
                settings.setNote_service(XmlUtils.getStrongXMLNodeValue(contentXML,"note_service")!=null);
                settings.setTask_service(XmlUtils.getStrongXMLNodeValue(contentXML,"task_service")!=null);
                settings.setFavorites_service(XmlUtils.getStrongXMLNodeValue(contentXML,"favorites_service")!=null);                
                settings.setWds_service(XmlUtils.getStrongXMLNodeValue(contentXML,"wds_service")!=null);
                settings.setGds_service(XmlUtils.getStrongXMLNodeValue(contentXML,"gds_service")!=null);
                settings.setBookmarks_service(XmlUtils.getStrongXMLNodeValue(contentXML,"bookmarks_service")!=null);

                session.setAttribute("user_settings",settings);
                
                session.setAttribute(PROTOCOL_URL,settings.getProtocolURL());
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new InvalidHostResponseXML("DOM Parser failed, invalid XML structure");
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
