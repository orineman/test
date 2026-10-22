package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class licenseStatusCancel_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_bean_write_scope_property_name;

  public licenseStatusCancel_jsp() {
    _jspx_tagPool_bean_write_scope_property_name = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_bean_write_scope_property_name.release();
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n\n");
      out.write("<script type=\"text/javascript\" language=\"JavaScript\">\n");
      out.write("</script>\n      ");
      out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n\t  ");
      out.write("<td>");
      out.write("<b>Status of cancellation of license code ");
      if (_jspx_meth_bean_write_0(pageContext))
        return;
      out.write(":");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n\t  ");
      out.write("<td>");
      if (_jspx_meth_bean_write_1(pageContext))
        return;
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n\t  ");
      out.write("<td>\n\t  ");
      out.write("<a href=tekSupport.do>Okay");
      out.write("</a>\n\t  ");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n        ");
      out.write("</tr>\n      ");
      out.write("</table>\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_bean_write_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:write ---- */
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_0 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_scope_property_name.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_0.setPageContext(pageContext);
    _jspx_th_bean_write_0.setParent(null);
    _jspx_th_bean_write_0.setName("licensedata");
    _jspx_th_bean_write_0.setProperty("data");
    _jspx_th_bean_write_0.setScope("request");
    int _jspx_eval_bean_write_0 = _jspx_th_bean_write_0.doStartTag();
    if (_jspx_th_bean_write_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_write_scope_property_name.reuse(_jspx_th_bean_write_0);
    return false;
  }

  private boolean _jspx_meth_bean_write_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:write ---- */
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_1 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_scope_property_name.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_1.setPageContext(pageContext);
    _jspx_th_bean_write_1.setParent(null);
    _jspx_th_bean_write_1.setName("licensedata");
    _jspx_th_bean_write_1.setProperty("results");
    _jspx_th_bean_write_1.setScope("request");
    int _jspx_eval_bean_write_1 = _jspx_th_bean_write_1.doStartTag();
    if (_jspx_th_bean_write_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_write_scope_property_name.reuse(_jspx_th_bean_write_1);
    return false;
  }
}
