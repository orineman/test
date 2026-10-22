package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;
import com.infowave.c4p.web.actions.teksupport.UserForm;
import com.infowave.c4p.util.StringManager;

public class passwordReset_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_form_onsubmit_method_action;

  public passwordReset_jsp() {
    _jspx_tagPool_html_form_onsubmit_method_action = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_html_form_onsubmit_method_action.release();
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
      out.write("\n");
      out.write("\n");
      out.write("\n\n");
      out.write("<script type=\"text/javascript\" language=\"JavaScript\">\nfunction TestDataCheck()\n{\nvar pwd1=document.UserForm.pwd1.value;\nvar pwd2=document.UserForm.pwd2.value;\nvar retval = true;\n  if( pwd1.length ");
      out.write("< 4 || pwd2.length ");
      out.write("< 4 ) {\n        alert(\"Please use a password with a length of 4 or greater.\");\n        retval = false;\n  }\n  if( retval && pwd1 != pwd2 ) {\n        alert(\"Please enter the same password in both text boxes.\");\n        retval = false;\n  }\n  return retval;\n}\n");
      out.write("</script>\n\n");

String jtemp = request.getQueryString();
String name = "";
if( jtemp != null && jtemp.length() > 0 ) {	
    int idx = jtemp.indexOf("data=");
    if( idx != -1 ) jtemp = jtemp.substring( idx +5 );
    String jtemp1 = StringManager.linkdecode(jtemp);
    name = new String(jtemp1.getBytes("8859_1"), "UTF-8");
}

      out.write("\n\n\n");
      /* ----  html:form ---- */
      org.apache.struts.taglib.html.FormTag _jspx_th_html_form_0 = (org.apache.struts.taglib.html.FormTag) _jspx_tagPool_html_form_onsubmit_method_action.get(org.apache.struts.taglib.html.FormTag.class);
      _jspx_th_html_form_0.setPageContext(pageContext);
      _jspx_th_html_form_0.setParent(null);
      _jspx_th_html_form_0.setAction("tsAdminResetPassword");
      _jspx_th_html_form_0.setMethod("post");
      _jspx_th_html_form_0.setOnsubmit("return TestDataCheck()");
      int _jspx_eval_html_form_0 = _jspx_th_html_form_0.doStartTag();
      if (_jspx_eval_html_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n      ");
          out.write("<input type=\"hidden\" name=\"data\" value='");
          out.print(name );
          out.write("' >\n      ");
          out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n        ");
          out.write("<tr>\n       \t  ");
          out.write("<td>&nbsp;");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n       \t  ");
          out.write("<td>&nbsp;");
          out.write("</td>\n          ");
          out.write("<td >\n            ");
          out.write("<font size=\"+1\">Reset Password for '");
          out.print(name );
          out.write("'");
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
          out.write("<b>Enter the new password:");
          out.write("</b>\n          ");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n       \t  ");
          out.write("<td>&nbsp;");
          out.write("</td>\n          ");
          out.write("<td >\n          ");
          out.write("<input type=\"text\" style=\"content_input\" size=\"12\" maxlength=\"12\" name=\"pwd1\" value=\"\"/>\n          ");
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
          out.write("<b>Re-enter the new password:");
          out.write("</b>\n          ");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n       \t  ");
          out.write("<td>&nbsp;");
          out.write("</td>\n          ");
          out.write("<td >\n          ");
          out.write("<input type=\"text\" style=\"content_input\" size=\"12\" maxlength=\"12\" name=\"pwd2\" value=\"\"/>\n          ");
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
          out.write("<INPUT TYPE=\"submit\" name=\"confirm\" value=\" Reset \" />\n\t  ");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n       \t  ");
          out.write("<td>&nbsp;");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n       \t  ");
          out.write("<td>&nbsp;");
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
        return;
      _jspx_tagPool_html_form_onsubmit_method_action.reuse(_jspx_th_html_form_0);
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
}
