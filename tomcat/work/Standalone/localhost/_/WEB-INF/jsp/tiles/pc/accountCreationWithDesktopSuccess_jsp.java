package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class accountCreationWithDesktopSuccess_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c4_Resource_name_localized;

  public accountCreationWithDesktopSuccess_jsp() {
    _jspx_tagPool_c4_Resource_name_localized = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c4_Resource_name_localized.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    javax.servlet.jsp.PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html;charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      if (_jspx_meth_c4_Resource_0(pageContext))
        return;
      out.write("\n\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_c4_Resource_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c4:Resource ---- */
    com.infowave.c4p.web.tags.ResourceTag _jspx_th_c4_Resource_0 = (com.infowave.c4p.web.tags.ResourceTag) _jspx_tagPool_c4_Resource_name_localized.get(com.infowave.c4p.web.tags.ResourceTag.class);
    _jspx_th_c4_Resource_0.setPageContext(pageContext);
    _jspx_th_c4_Resource_0.setParent(null);
    _jspx_th_c4_Resource_0.setName("accountCreationWithDesktopSuccess.html");
    _jspx_th_c4_Resource_0.setLocalized("true");
    int _jspx_eval_c4_Resource_0 = _jspx_th_c4_Resource_0.doStartTag();
    if (_jspx_th_c4_Resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c4_Resource_name_localized.reuse(_jspx_th_c4_Resource_0);
    return false;
  }
}
