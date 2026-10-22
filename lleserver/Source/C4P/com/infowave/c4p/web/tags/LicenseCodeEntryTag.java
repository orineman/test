package com.infowave.c4p.web.tags;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO This is not portable. JSTL 2.0 has a standard way to use teh EL in
// custom tag.We should revwrite this code when we switch to Tomcat 5 and
// JSTL 2.0.
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

public class LicenseCodeEntryTag extends LicenseCodeTag
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
    String inputName = namePrefix + (index+1);
    buff.append("<input type=\"text\"");
    buff.append(" name=\"").append(inputName).append("\"");
    buff.append(" size=\"").append(chunkSize).append("\"");
    buff.append(" maxlength=\"").append(chunkSize).append("\"");
    if (displayClass != null){
      buff.append(" class=\"").append(displayClass).append("\"");
    }
    if (code != null){
      buff.append(" value=\"").append(chunk).append("\"");
    }
    buff.append("/>").append("\n");

    return buff.toString();
  }

}
