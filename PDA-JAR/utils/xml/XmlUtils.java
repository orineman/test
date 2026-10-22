package com.infowave.pda.utils.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 03.10.2007
 * Time: 12:54:29
 * To change this template use File | Settings | File Templates.
 */
public class XmlUtils {

    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(XmlUtils.class);

    public static ArrayList<String> getXMLNodeValues(Document document, String tagname) {
        ArrayList<String> result = new ArrayList<String>();
        NodeList nodes = document.getElementsByTagName(tagname);
        for (int i = 0; i < nodes.getLength(); i++) {
            result.add(nodes.item(i).getTextContent());
        }
        return result;
    }

    public static HashMap<String, String> getXMLHashValues(Document document, String parentTagName, String keytag, String valuetag) {
        HashMap<String, String> map = new HashMap<String, String>();
        NodeList nodes = document.getElementsByTagName(parentTagName);
        String key = null;
        String value = null;
        NodeList childs = null;
        for (int i = 0; i < nodes.getLength(); i++) {
            childs = nodes.item(i).getChildNodes();
            for (int j = 0; j < childs.getLength(); i++) {
                if (keytag.equalsIgnoreCase(childs.item(j).getTextContent())) {
                    key = childs.item(j).getTextContent();
                }

                if (valuetag.equalsIgnoreCase(childs.item(j).getTextContent())) {
                    value = childs.item(j).getTextContent();
                }
            }
            if (value != null && key != null) {
                if (map.containsKey(key)) {
                    map.remove(key);
                }
                map.put(key, value);
            }
            key = null;
            value = null;
        }
        return map;
    }

    public static String getFirstXMLNodeValue(Document document, String tagname) {
        throw new UnsupportedOperationException("This function not yet implemented");
    }

    public static ArrayList<String> getXMLNodeValues(NodeList nodes, String tagname) {
        throw new UnsupportedOperationException("This function not yet implemented");
    }

    public static String getFirstXMLNodeValue(NodeList nodes, String tagname) {
        String sValue = "";
        try {
            if (nodes.getLength() > 0) {
                nodes = nodes.item(0).getChildNodes();
                for (int i1 = 0; i1 < nodes.getLength(); i1++) {
                    if (nodes.item(i1).getNodeName().equalsIgnoreCase(tagname)) {
                        sValue = nodes.item(i1).getTextContent();
                        break;
                    }
                }
            } else {
                m_log.debug("+++ getNodeValue fails: " + tagname);
            }
        } catch (Exception ex) {
            m_log.error("Error. getNodeValue. catch:" + ex);
            sValue = "";
        }
        return sValue;
    }

    public static String getStrongXMLNodeValue(NodeList nodes, String tagname) {
        String sValue = null;
        try {
            if (nodes.getLength() > 0) {
                nodes = nodes.item(0).getChildNodes();
                for (int i1 = 0; i1 < nodes.getLength(); i1++) {
                    if (nodes.item(i1).getNodeName().equalsIgnoreCase(tagname)) {
                        sValue = nodes.item(i1).getTextContent();
                        break;
                    }
                }
            } else {
                m_log.debug("+++ getNodeValue fails: " + tagname);
            }
        } catch (Exception ex) {
            m_log.error("Error. getNodeValue. catch:" + ex);
            sValue = "";
        }
        return sValue;
    }

    public static String getChildNodeValue(Node n, String tagname) {
        String result = null;
        NodeList list = n.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(tagname)) {
                result = list.item(i).getTextContent();
            }
        }
        return result;
    }

    public static String getXMLNodeValue(Document document, String tagname) {
        String result = null;
        try {
            result = document.getElementsByTagName(tagname).item(0).getTextContent();
        } catch (Exception e) {
        }
        finally {
            return result;
        }
    }

    public static Node getXMLNode(Node parent, String tagname) {
        Node result = null;
        NodeList list = parent.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(tagname)) {
                result = list.item(i);
            }
        }
        return result;
    }
}
