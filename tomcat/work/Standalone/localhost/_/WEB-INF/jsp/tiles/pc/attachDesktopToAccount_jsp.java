package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class attachDesktopToAccount_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_form_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_text_style_property_maxlength;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_password_style_property_maxlength;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_text_value_style_property;

  public attachDesktopToAccount_jsp() {
    _jspx_tagPool_html_form_method_action = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_html_text_style_property_maxlength = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_html_password_style_property_maxlength = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_html_text_value_style_property = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_html_form_method_action.release();
    _jspx_tagPool_c_out_value.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_html_text_style_property_maxlength.release();
    _jspx_tagPool_html_password_style_property_maxlength.release();
    _jspx_tagPool_html_text_value_style_property.release();
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

      out.write("\n\n");
      out.write("\n\n");
      out.write("\n\n\t");
      out.write("<LINK REL=StyleSheet HREF=\"x-res/style.css\" TYPE=\"text/css\">\n\n");
      out.write("<br>\n\n");
      out.write("<center>\n\n\n\n");
      out.write("<center>\n\n");
      if (_jspx_meth_html_form_0(pageContext))
        return;
      out.write("\n\n\n\n");
      out.write("<br>\n\n");
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
    _jspx_th_html_form_0.setAction("attachDesktopToAccount");
    _jspx_th_html_form_0.setMethod("post");
    int _jspx_eval_html_form_0 = _jspx_th_html_form_0.doStartTag();
    if (_jspx_eval_html_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n");
        out.write("<input type=\"hidden\" name=\"did\" value='");
        if (_jspx_meth_c_out_0(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("'/>\n\n  ");
        out.write("<table cellspacing=\"0\" width=\"50%\" align=\"center\" cellpadding=\"0\">\n\n    ");
        out.write("<tr>\n\n        ");
        out.write("<td colspan=\"2\" class=\"content_title\" valign=\"middle\">\n\n          ");
        out.write("<img src=\"x-res/ARROW.GIF\">&nbsp; ");
        if (_jspx_meth_fmt_message_0(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("</td>\n\n    ");
        out.write("</tr>\n\n    ");
        out.write("<tr>\n\n\t      ");
        out.write("<td colspan=\"2\" height=\"1\" bgcolor=\"black\"> ");
        out.write("</td>\n\n    ");
        out.write("</tr>\n\n    ");
        out.write("<tr>\n\n        ");
        out.write("<td class=\"shaded_text\" colspan=\"2\">&nbsp;");
        out.write("</td>\n\n    ");
        out.write("</tr>\n\n    ");
        out.write("<tr>\n\n        ");
        out.write("<td class=\"shaded_caption\">");
        if (_jspx_meth_fmt_message_1(_jspx_th_html_form_0, pageContext))
          return true;
        out.write(":");
        out.write("</td>\n\n        ");
        out.write("<td class=\"shaded_text\">\n\n          ");
        if (_jspx_meth_html_text_0(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("\n\n    ");
        out.write("</tr>\n\n    ");
        out.write("<tr>\n\n        ");
        out.write("<td class=\"shaded_caption\">");
        if (_jspx_meth_fmt_message_2(_jspx_th_html_form_0, pageContext))
          return true;
        out.write(":");
        out.write("</td>\n\n        ");
        out.write("<td class=\"shaded_text\">\n\n          ");
        if (_jspx_meth_html_password_0(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("\n\n        ");
        out.write("</td>\n\n    ");
        out.write("</tr>\n\n    ");
        out.write("<tr>\n\n        ");
        out.write("<td class=\"shaded_caption\">");
        if (_jspx_meth_fmt_message_3(_jspx_th_html_form_0, pageContext))
          return true;
        out.write(":");
        out.write("</td>\n\n        ");
        out.write("<td class=\"shaded_text\">\n\n          ");
        if (_jspx_meth_html_text_1(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("\n\n        ");
        out.write("</td>\n\n    ");
        out.write("</tr>\n\n    ");
        out.write("<tr>\n\n        ");
        out.write("<td class=\"shaded_text\" align=\"center\" colspan=\"2\">");
        out.write("<input type=\"submit\" name=\"submit\" value='");
        if (_jspx_meth_fmt_message_4(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("'>");
        out.write("</td>\n\n    ");
        out.write("</tr>\n\n    ");
        out.write("<tr>\n\n        ");
        out.write("<td colspan=\"2\" class=\"shaded_text\">&nbsp;");
        out.write("</td>\n\n    ");
        out.write("</tr>\n\n    ");
        out.write("<tr>\n\n        ");
        out.write("<td align=\"center\" colspan=\"2\">\n\n            ");
        out.write("<a class=\"content_link\"\n\n             href='showCreateAccountPage.do?did=");
        if (_jspx_meth_c_out_1(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("' class=\"font\">Assign this PC to a New User");
        out.write("</a>\n\n        ");
        out.write("</td>\n\n    ");
        out.write("</tr>\n\n  ");
        out.write("</table>\n\n");
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

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(_jspx_th_html_form_0);
    _jspx_th_c_out_0.setValue("${param.did}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_0.setKey("title.addDesktopToAccount");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_1.setKey("field.username");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_html_text_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:text ---- */
    org.apache.struts.taglib.html.TextTag _jspx_th_html_text_0 = (org.apache.struts.taglib.html.TextTag) _jspx_tagPool_html_text_style_property_maxlength.get(org.apache.struts.taglib.html.TextTag.class);
    _jspx_th_html_text_0.setPageContext(pageContext);
    _jspx_th_html_text_0.setParent(_jspx_th_html_form_0);
    _jspx_th_html_text_0.setStyle("content_input");
    _jspx_th_html_text_0.setProperty("username");
    _jspx_th_html_text_0.setMaxlength("20");
    int _jspx_eval_html_text_0 = _jspx_th_html_text_0.doStartTag();
    if (_jspx_th_html_text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_html_text_style_property_maxlength.reuse(_jspx_th_html_text_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_2.setKey("field.password");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_html_password_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:password ---- */
    org.apache.struts.taglib.html.PasswordTag _jspx_th_html_password_0 = (org.apache.struts.taglib.html.PasswordTag) _jspx_tagPool_html_password_style_property_maxlength.get(org.apache.struts.taglib.html.PasswordTag.class);
    _jspx_th_html_password_0.setPageContext(pageContext);
    _jspx_th_html_password_0.setParent(_jspx_th_html_form_0);
    _jspx_th_html_password_0.setStyle("content_input");
    _jspx_th_html_password_0.setProperty("password");
    _jspx_th_html_password_0.setMaxlength("12");
    int _jspx_eval_html_password_0 = _jspx_th_html_password_0.doStartTag();
    if (_jspx_th_html_password_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_html_password_style_property_maxlength.reuse(_jspx_th_html_password_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(pageContext);
    _jspx_th_fmt_message_3.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_3.setKey("field.pcName");
    int _jspx_eval_fmt_message_3 = _jspx_th_fmt_message_3.doStartTag();
    if (_jspx_th_fmt_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_3);
    return false;
  }

  private boolean _jspx_meth_html_text_1(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:text ---- */
    org.apache.struts.taglib.html.TextTag _jspx_th_html_text_1 = (org.apache.struts.taglib.html.TextTag) _jspx_tagPool_html_text_value_style_property.get(org.apache.struts.taglib.html.TextTag.class);
    _jspx_th_html_text_1.setPageContext(pageContext);
    _jspx_th_html_text_1.setParent(_jspx_th_html_form_0);
    _jspx_th_html_text_1.setStyle("content_input");
    _jspx_th_html_text_1.setProperty("pcName");
    _jspx_th_html_text_1.setValue("My PC");
    int _jspx_eval_html_text_1 = _jspx_th_html_text_1.doStartTag();
    if (_jspx_th_html_text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_html_text_value_style_property.reuse(_jspx_th_html_text_1);
    return false;
  }

  private boolean _jspx_meth_fmt_message_4(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_4 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_4.setPageContext(pageContext);
    _jspx_th_fmt_message_4.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_4.setKey("action.submit");
    int _jspx_eval_fmt_message_4 = _jspx_th_fmt_message_4.doStartTag();
    if (_jspx_th_fmt_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_4);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_html_form_0);
    _jspx_th_c_out_1.setValue("${param.did}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }
}
