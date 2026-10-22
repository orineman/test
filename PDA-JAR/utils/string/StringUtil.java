package com.infowave.pda.utils.string;

import com.infowave.pda.utils.xml.XmlUtils;
import org.w3c.dom.Document;


/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 04.02.2008
 * Time: 12:29:14
 * Paccage : com.infowave.pda.utils.string
 */
public class StringUtil {
    public static boolean isContainEmail(String s1, String email1) {
        boolean result = false;
        String s = s1.toLowerCase();
        String email = email1.toLowerCase();
        int len = email.length();
        String left, right;
        int pos = s.indexOf(email);
        while (pos > -1) {
            if (pos!=0) {
                left = s.substring(pos - 1, pos);
            } else {
                left="";
            }
            if (pos+len < s.length()) {
                right = s.substring(pos + len, pos + len + 1);
            } else {
                right="";
            }
            if ((left.length() == 0 || left.equals(" "))
                    && (right.length() == 0 || right.equals(" "))) {
                result = true;
                break;
            }
            if (pos<len) {
                pos = s.indexOf(email, pos + 1);
            } else {
                break;
            }
        }
        return result;
    }

    public static String formCCString(Document hostXML) {
         String from = XmlUtils.getXMLNodeValue(hostXML, "From");
         String cc = XmlUtils.getXMLNodeValue(hostXML, "CC");
         if (StringUtil.isContainEmail(cc, from)) {
            if (!cc.equals("")) {
                cc = cc.replaceAll(from, "");
                cc = cc.replaceAll(from.toLowerCase(), "");
                cc = cc.trim();
                if (cc.charAt(0) == ';') {
                    cc = cc.substring(1);
                }
                if (cc.charAt(cc.length() - 1) == ';') {
                    cc = cc.substring(0, cc.length() - 1);
                }
                cc = cc.trim();
            }
        }

        return cc;
    }

    public static String formReplyText(Document hostXML) {
        String res = "";
        String to = XmlUtils.getXMLNodeValue(hostXML, "To");
        String from = XmlUtils.getXMLNodeValue(hostXML, "From");
        String cc = XmlUtils.getXMLNodeValue(hostXML, "CC");
        String bcc = XmlUtils.getXMLNodeValue(hostXML, "BCC");
        if (!StringUtil.isContainEmail(to, from) &&
                !StringUtil.isContainEmail(cc, from) &&
                !StringUtil.isContainEmail(bcc, from)) {

            if (to != null) {
                res += to;
            }
            res += "; ";
            res += from;
        } else if (to != null) {
            res = to;
        }

        return res;
    }

    public static void main(String args[]) {
        System.out.println(StringUtil.isContainEmail("","alexey.Ogarkov@laplik.yumasoft"));
        System.out.println(StringUtil.isContainEmail("alexey.Ogarkov@laplik.yumasoft","alexey.Ogarkov@laplik.yumasoft"));
        System.out.println(StringUtil.isContainEmail("alexey.Ogarkov@laplik.yumasoft","alexey.Ogarkdfgdfgdfgov@laplik.yumasoft"));
        System.out.println(StringUtil.isContainEmail("alexey.Ogarsdfsdfkov@laplik.yumasoft alexeysadasfaf.Ogarkov@laplik.yumasoft","alexey.Ogarkov@laplik.yumasoft"));
        System.out.println(StringUtil.isContainEmail("alexey.Ogarkov@laplik.yumasoft alexey.Ogsdfsdfsssdarkov@laplik.yumasoft","alexey.Ogarkov@laplik.yumasoft"));
        System.out.println(StringUtil.isContainEmail("alexey.Ogarkov@laplik.yumasoft alexey.Ogarkov@laplik.yumasoft",""));
    }
}