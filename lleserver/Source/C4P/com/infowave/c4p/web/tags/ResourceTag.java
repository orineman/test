package com.infowave.c4p.web.tags;

import org.apache.struts.util.ResponseUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.http.HttpServletRequest;

import com.infowave.c4p.web.util.ResourceManager;

// TODO This is not portable. JSTL 2.0 has a standard way to use teh EL in
// custom tag.We should revwrite this code when we switch to Tomcat 5 and
// JSTL 2.0.
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Nov 18, 2003
 * Time: 2:30:06 PM
 * To change this template use Options | File Templates.
 */
public class ResourceTag extends TagSupport
{
  /** The class's logger */
  private static Log m_log= LogFactory.getLog(ResourceTag.class);

  private String name;
  private String localized;

  public String getName(){return name;}
  public void setName(String name){this.name = name;}

  public String isLocalized(){return localized;}
  public void setLocalized(String localized){this.localized = localized;}

  public int doStartTag() throws JspException
  {
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();



    String resource = (String)ExpressionEvaluatorManager.evaluate(
            "name",                // attribute name
            name,              // expression
            String.class,  // expected type
            this,                 // this tag handler
            pageContext);         // the page context
    m_log.debug("Getting resource: " + resource);
    if(resource == null)
      return SKIP_BODY;

    try {
      boolean doLocalize = "true".equalsIgnoreCase(localized);
      byte[] res = ResourceManager.getResourceAsByteArray(request,resource,doLocalize);
      if (res != null) {
        for (int i=0;i<res.length;i++) {
          pageContext.getOut().write(res[i]);
        }
      }
    }
    catch (Exception e){
      m_log.error("Get the resource: " + name,e);
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
    name = null;
  }



}
