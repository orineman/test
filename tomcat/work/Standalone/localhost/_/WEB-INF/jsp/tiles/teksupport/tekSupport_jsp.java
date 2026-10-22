package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class tekSupport_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_bundle;

  public tekSupport_jsp() {
    _jspx_tagPool_fmt_message_key_bundle = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_fmt_message_key_bundle.release();
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

      out.write("\n\n");
      out.write("\n");
      out.write("\n");
      out.write("\n\n");
      out.write("<script type=\"text/javascript\" language=\"JavaScript\">\n");
      out.write("<!--\nfunction setTitle(){\ndocument.title='");
      if (_jspx_meth_fmt_message_0(pageContext))
        return;
      out.write("';\n}\n\nfunction cancel(){\nthis.close();\n}\n// -->\n");
      out.write("</script>\n\n");
      out.write("<html>\n");
      out.write("<BL>\n      ");
      out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n          ");
      out.write("<td >\n            ");
      out.write("<b>What do you wish to do?");
      out.write("</b>\n          ");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n          ");
      out.write("<td >\n            ");
      out.write("<LI>");
      out.write("<a class=\"content_link\" href=\"/tsAdminSetType.do?type=user\">Locate User");
      out.write("</a>\n          ");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n          ");
      out.write("<td >\n            ");
      out.write("<LI>");
      out.write("<a class=\"content_link\" href=\"/tsAdminSetType.do?type=desktop\">Locate Desktop");
      out.write("</a>\n          ");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n          ");
      out.write("<td >\n            ");
      out.write("<LI>");
      out.write("<a class=\"content_link\" href=\"/tsAdminSetType.do?type=licensecancel\">Cancel License");
      out.write("</a>\n          ");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n          ");
      out.write("<td >\n            ");
      out.write("<LI>");
      out.write("<a class=\"content_link\" href=\"/tsAdminSetType.do?type=licenserevoke\">Revoke License");
      out.write("</a>\n          ");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n          ");
      out.write("<td >\n            ");
      out.write("<LI>");
      out.write("<a class=\"content_link\" href=\"/tsAdminSetType.do?type=licenserevive\">Reset License");
      out.write("</a>\n          ");
      out.write("</td>\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n          ");
      out.write("<td >\n            ");
      out.write("<LI>");
      out.write("<a class=\"content_link\" href=\"/tsAdminSetType.do?type=licensecreate\">Create Licenses");
      out.write("</a>\n          ");
      out.write("</td>\n        ");
      out.write("<tr>\n       \t  ");
      out.write("<td>&nbsp;");
      out.write("</td>\n        ");
      out.write("</tr>\n      ");
      out.write("</table>\n");
      out.write("</BL>\n");
      out.write("</html>\n\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key_bundle.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(null);
    _jspx_th_fmt_message_0.setBundle("${c4p_resellerBundle}");
    _jspx_th_fmt_message_0.setKey("title.pcConfiguration");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key_bundle.reuse(_jspx_th_fmt_message_0);
    return false;
  }
}
