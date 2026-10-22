package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class licenseRevive_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_form_method_action;

  public licenseRevive_jsp() {
    _jspx_tagPool_html_form_method_action = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_html_form_method_action.release();
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
      out.write("</script>\n\n");
      if (_jspx_meth_html_form_0(pageContext))
        return;
      out.write("\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_html_form_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:form ---- */
    org.apache.struts.taglib.html.FormTag _jspx_th_html_form_0 = (org.apache.struts.taglib.html.FormTag) _jspx_tagPool_html_form_method_action.get(org.apache.struts.taglib.html.FormTag.class);
    _jspx_th_html_form_0.setPageContext(pageContext);
    _jspx_th_html_form_0.setParent(null);
    _jspx_th_html_form_0.setAction("tsAdminCancelLicense");
    _jspx_th_html_form_0.setMethod("post");
    int _jspx_eval_html_form_0 = _jspx_th_html_form_0.doStartTag();
    if (_jspx_eval_html_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n      ");
        out.write("<input type=\"hidden\" name=\"reseller\" value=\"laplink\" >\n      ");
        out.write("<input type=\"hidden\" name=\"type\" value=\"statusv\" >\n      ");
        out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n          ");
        out.write("<td >\n            ");
        out.write("<font size=\"+1\">Reset License");
        out.write("</font>\n          ");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n          ");
        out.write("<td >\n            ");
        out.write("<b>Enter the license:");
        out.write("</b>\n          ");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n          ");
        out.write("<td >\n          ");
        out.write("<input type=\"text\" style=\"content_input\" size=\"12\" maxlength=\"12\" name=\"data\" value=\"\"/>\n          ");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n          ");
        out.write("<td >\n            ");
        out.write("<INPUT TYPE=\"submit\" name=\"statusv\" value=\" Proceed \" />\n\t  ");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n       \t  ");
        out.write("<td>Note: This reset acts only on licenses that are not currently assigned. It does not affect the license length.");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n\t  ");
        out.write("<TD>");
        out.write("<a href=\"tekSupport.do\">Top");
        out.write("</a>");
        out.write("</TD>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n        ");
        out.write("</tr>\n        ");
        out.write("<tr>\n       \t  ");
        out.write("<td>&nbsp;");
        out.write("</td>\n        ");
        out.write("</tr>\n      ");
        out.write("</table>\n");
        int evalDoAfterBody = _jspx_th_html_form_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_html_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_html_form_method_action.reuse(_jspx_th_html_form_0);
    return false;
  }
}
