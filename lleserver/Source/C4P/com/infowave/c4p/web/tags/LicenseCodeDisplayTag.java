package com.infowave.c4p.web.tags;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Jan 6, 2004
 * Time: 10:19:27 PM
 * To change this template use Options | File Templates.
 */
public class LicenseCodeDisplayTag extends LicenseCodeTag
{
    /** The class's logger */
  private static Log m_log = LogFactory.getLog(LicenseCodeEntryTag.class);

  /**
   * Generates the output for a group
   * @param code - the complete code
   * @param chunk - The chunk to display
   * @param index - index of the chunk in the group
   * @return
   */
  protected  String generateGroupOutput(String code, String chunk,
                                        Integer chunkSize,int index)
  {
    StringBuffer buff = new StringBuffer();
    if (displayClass != null){
      buff.append("<span class=\"").append(displayClass).append("\">");
    }
    if (chunk != null){
      buff.append(chunk).append("&nbsp;");
    }
    if (displayClass != null){
      buff.append("</span>");
    }
    return buff.toString();
  }

}
