package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class accountManagementNavBar_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;

  public accountManagementNavBar_jsp() {
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_fmt_message_key.release();
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
      out.write("<!-- left tool strip -->\n  ");
      out.write("<table class=\"body_bar\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n\t\t\t");
      out.write("<tr>\n\t\t\t\t");
      out.write("<td valign=\"top\">\n\t\t\t\t\t");
      out.write("<!-- this table\tdefines the toolbar -->\n\t\t\t\t\t");
      out.write("<table class=\"body_bar\" cellspacing=\"0\" cellpadding=\"2\" width=\"100%\" border=\"0\">\n    \t\t    ");
      out.write("<tr>\n\t\t\t\t      ");
      out.write("<td valign=\"middle\">");
      out.write("<img src=\"x-res/BULLET.GIF\" hspace=\"2\">");
      out.write("</td>\n\t\t\t\t      ");
      out.write("<td>\n                        ");
      out.write("<a class=\"left_body\" href=\"/protected/showAccountManagementPage.do\">\n                          ");
      if (_jspx_meth_fmt_message_0(pageContext))
        return;
      out.write("\n                        ");
      out.write("</a>\n                      ");
      out.write("</td>\n\t\t\t\t    ");
      out.write("</tr>\n\t\t\t\t    ");
      out.write("<tr>\n\t\t\t\t      ");
      out.write("<td valign=\"middle\">");
      out.write("<img src=\"x-res/BULLET.GIF\" hspace=\"2\">");
      out.write("</td>\n\t\t\t\t      ");
      out.write("<td>\n                        ");
      out.write("<a class=\"left_body\" href=\"/protected/showAccountManagementPage.do?section=password\">\n                          ");
      if (_jspx_meth_fmt_message_1(pageContext))
        return;
      out.write("\n                        ");
      out.write("</a>\n                      ");
      out.write("</td>\n\t\t\t\t    ");
      out.write("</tr>\n\t\t\t\t    ");
      out.write("<tr>\n\t\t\t\t      ");
      out.write("<td valign=\"middle\">");
      out.write("<img src=\"x-res/BULLET.GIF\" hspace=\"2\">");
      out.write("</td>\n\t\t\t\t      ");
      out.write("<td>\n                        ");
      out.write("<a class=\"left_body\" href=\"/protected/showAccountManagementPage.do?section=security\">\n                          ");
      if (_jspx_meth_fmt_message_2(pageContext))
        return;
      out.write("\n                        ");
      out.write("</a>\n                      ");
      out.write("</td>\n\t\t\t\t    ");
      out.write("</tr>\n\t\t\t\t\t");
      out.write("</table>\n\t\t\t\t");
      out.write("</Td>\n\t\t\t");
      out.write("</tr>\n");
      out.write("</table>\n\t\t\t");
      out.write("<!-- end left tool strip -->\n");
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
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(null);
    _jspx_th_fmt_message_0.setKey("action.EditAccountInformation");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(null);
    _jspx_th_fmt_message_1.setKey("action.changePassword");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(null);
    _jspx_th_fmt_message_2.setKey("action.changeSecurityQuestion");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_2);
    return false;
  }
}
