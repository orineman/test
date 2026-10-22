package com.infowave.c4p.web.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Class XmlUtil some helper method for request processing
 *
 * @author alexogar
 * Created on Jul 28, 2008
 */
public class XmlUtil {
    /**
     * Method getXMLNodeValue get XML string value from XML by tag name
     *
     * @param nodes of type NodeList
     * @param sName of type String
     * @return String
     */
    public static String getXMLNodeValue(NodeList nodes, String sName) {
        String sValue = "";
        if (nodes.getLength() > 0) {
            nodes = nodes.item(0).getChildNodes();
            for (int i1 = 0; i1 < nodes.getLength(); i1++) {
                if (nodes.item(i1).getNodeName().equalsIgnoreCase(sName)) {
                    sValue = nodes.item(i1).getTextContent();
                    break;
                }
            }
        } else {
            //TODO: Logs
        }

        return sValue;
    }

    /**
     * Method XML2String constructs String from XML
     *
     * @param document of type Document
     * @return String
     */
    public static String XML2String(Document document) {
        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Method Error2XML Xonstructs XML from LLE Error object 
     *
     * @param iCode of type int
     * @param sDescription of type String
     * @return String
     */
    public static String Error2XML(int iCode, String sDescription) {
        String sResponse;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();
            Element elResponse = document.createElement("LLEError");
            Element elCode = document.createElement("Code");
            elCode.setTextContent(Integer.toString(iCode));
            elResponse.appendChild(elCode);

            Element elDesc = document.createElement("Description");
            elDesc.setTextContent(sDescription);
            elResponse.appendChild(elDesc);

            document.appendChild(elResponse);
            document.normalizeDocument();
            sResponse = XML2String(document);
        }
        catch (Exception ex) {
            return "";
        }
        return sResponse;
    }
}
