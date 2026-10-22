package com.infowave.c4p.web.tags;

import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO This is not portable. JSTL 2.0 has a standard way to use the EL in
// custom tag.We should revwrite this code when we switch to Tomcat 5 and
// JSTL 2.0.
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

public abstract class LicenseCodeTag extends TagSupport
{
   /** The class's logger */
  private static Log m_log = LogFactory.getLog(LicenseCodeEntryTag.class);

  String displayClass;
  String format;
  String code;
  String namePrefix;

  public String getDisplayClass(){return displayClass;}
  public void setDisplayClass(String clazz){this.displayClass = clazz;}

  public String getFormat(){return format;}
  public void setFormat(String format){this.format = format;}

  public String getCode(){return code;}
  public void setCode(String code){this.code = code;}

  public String getNamePrefix(){return namePrefix;}
  public void setNamePrefix(String namePrefix) {this.namePrefix = namePrefix;}

   public int doStartTag() throws JspException
   {
     HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
     SessionState state = WebUtil.getSessionState(request);

     try {
     // Get the code
     String theCode = (String)ExpressionEvaluatorManager.evaluate(
             "code",                // attribute name
             code,              // expression
             String.class,  // expected type
             this,                 // this tag handler
             pageContext);         // the page context

     if (theCode != null) {
       theCode = theCode.trim();
       if ("".equals(theCode)){
         theCode = null;
       }
     }

     // Get the format
     List licenseCodeGroups;
     int licenseCodeLength = 0;
     licenseCodeGroups = state.getReseller().getLicenseCodeGroups();
     licenseCodeLength = state.getReseller().getLicenseCodeLength();

      // If we have a code, make sure it has the right size and chunk it.
     List codeChunks = new ArrayList(licenseCodeGroups.size());
     if (theCode != null) {
       /*
       if (theCode.length() != licenseCodeLength){
         m_log.error("invalid license code length: " + theCode + " (" +
                     licenseCodeLength + " digits required)");
         throw new JspException("invalid license code length: " + theCode + " (" +
                                licenseCodeLength + " digits required)");
       }
       */
       int startNextChunk = 0;
       for (int i=0;i<licenseCodeGroups.size();i++){
         Integer groupSize = (Integer) licenseCodeGroups.get(i);
         int actualSize = Math.min(theCode.length() - startNextChunk,groupSize.intValue());
         //m_log.debug("as: " + actualSize + " group: " + i + " next: " + startNextChunk);
         try {
           String chunk = theCode.substring(startNextChunk,
                                            startNextChunk + actualSize);
           startNextChunk += groupSize.intValue();
           codeChunks.add(chunk);
         }
         catch (IndexOutOfBoundsException e){
           m_log.error("OOB: tehCode=" + theCode + " startnext = " + startNextChunk + " actualsize=" + actualSize);
         }
         if (theCode.length()<= startNextChunk ){
           // nothing left to process - We got in incomplete code
           //We add blank chunks to the list
           for (int j=i+1;j<licenseCodeGroups.size();j++) {
             codeChunks.add("");
           }
           break;
           }

       }
     }
     // Build the text entry fields
     StringBuffer buff = new StringBuffer();
     for (int i=0;i<licenseCodeGroups.size();i++){
       //Integer groupSize = (Integer) licenseCodeGroups.get(i);
       String chunk = theCode == null? null: (String)codeChunks.get(i);
       buff.append(generateGroupOutput(theCode,chunk,
                                       (Integer)licenseCodeGroups.get(i),i));
       /*
       String inputName = namePrefix + (i+1);
       buff.append("<input type=\"text\"");
       buff.append(" name=\"").append(inputName).append("\"");
       buff.append(" size=\"").append(licenseCodeGroups.get(i)).append("\"");
       buff.append(" maxlength=\"").append(licenseCodeGroups.get(i)).append("\"");
       if (displayClass != null){
         buff.append(" class=\"").append(displayClass).append("\"");
       }
       if (theCode != null){
         buff.append(" value=\"").append(codeChunks.get(i)).append("\"");
       }
       buff.append("/>").append("\n");
       */
     }

       pageContext.getOut().write(buff.toString());
     }
     catch (Exception e){
       m_log.error ("cannot write tag",e);
       throw new JspException("cannot write tag");
     }

     // Continue processing this page
     return (SKIP_BODY);
   }


  /**
   * Evaluate the remainder of the current page normally.
   *
   * @exception JspException if a JSP exception occurs
   */
  public int doEndTag() throws JspException {
    return (EVAL_PAGE);
  }

  /**
   * Release all allocated resources.
   */
  public void release() {
    super.release();
    code = null;
    displayClass = null;
    format = null;
    namePrefix = null;
  }

    /**
   * Generates the output for a group
   * @param code - the complete code
   * @param chunk - The chunk to display
   * @param index - index of the chunk in the group
   * @return
   */
  protected abstract String generateGroupOutput(String code, String chunk,
                                                Integer  chunkSize,int index);



}
