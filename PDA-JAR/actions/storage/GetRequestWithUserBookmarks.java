package com.infowave.pda.actions.storage;

import com.infowave.pda.kernel.actions.concurrent.MultipleRequestBaseAction;
import com.infowave.pda.kernel.objects.Bookmark;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.utils.xml.XmlUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 17.03.2008
 * Time: 13:00:42
 * Paccage : com.infowave.pda.actions.storage
 */
public class GetRequestWithUserBookmarks extends MultipleRequestBaseAction {

    public static final String BOOKMARKS = "bookmarks_list";
    @Override
    protected ActionForward preHostRequest(HostConnector hostConnector, HttpServletRequest request, ActionMapping mapping, HttpServletResponse response) {
        //Get action parameter from request
        String action = request.getParameter("useraction");
        HttpSession session = request.getSession();
        if (action!=null && request.getParameter("id")!=null) {
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Bookmark> bookmarks = (ArrayList<Bookmark>) session.getAttribute(BOOKMARKS);
            if (action.equals("delete")) {
                bookmarks.remove(id);
            }
            session.setAttribute(BOOKMARKS,bookmarks);
        }
        return null;
    }

    @Override
    protected void afterHostRequests(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
        //Parsing response
        Document doc;
        HttpSession session = request.getSession();
        ArrayList<Bookmark> listOfBookmarks = new ArrayList<Bookmark>();
        if (request.getAttribute("bookmarkXML") != null) {
            doc = (Document) request.getAttribute("bookmarkXML");
            doc.normalizeDocument();
            NodeList bookmarks = doc.getElementsByTagName("bookmark");
            Node bookmark;
            NodeList childs;
            for (int i = 0; i < bookmarks.getLength(); i++) {
                Bookmark temp = new Bookmark();
                bookmark = bookmarks.item(i);
                //This functions return us null if there is no Node in XML
                String name = XmlUtils.getXMLNode(bookmark, "name").getTextContent();
                String description = XmlUtils.getXMLNode(bookmark, "description").getTextContent();
                String path = XmlUtils.getXMLNode(bookmark, "path").getTextContent();
                temp.setId(i);
                temp.setName(name);
                temp.setPath(path);
                /* If bookmarks path is the path for the SystemFolder.
                 * we should use pathIDL params for navigation in the client
                 * (e.g GetFolderContent.do?pathIDL=dsfasdfsdf ) and we should 
                 * set this Node when saving.
                 */
                temp.setPathIDL(XmlUtils.getXMLNode(bookmark,"pathIDL")!=null);
                temp.setDescription(description);
                //Adding bookmark to the list
                listOfBookmarks.add(temp);
            }

            session.setAttribute(BOOKMARKS,listOfBookmarks);
        }
    }
}
