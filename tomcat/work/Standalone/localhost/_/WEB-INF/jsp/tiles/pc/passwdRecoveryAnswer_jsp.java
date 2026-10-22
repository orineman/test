package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class passwdRecoveryAnswer_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_form_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_bundle;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_bundle;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;

  public passwdRecoveryAnswer_jsp() {
    _jspx_tagPool_html_form_method_action = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key_bundle = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_bundle = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_html_form_method_action.release();
    _jspx_tagPool_fmt_message_key_bundle.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_fmt_message_bundle.release();
    _jspx_tagPool_c_out_value.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n\r\n\t\t");
      out.write("<LINK REL=StyleSheet HREF=\"x-res/style.css\" TYPE=\"text/css\">\r\n\r\n");
      out.write("<br>\r\n    ");
      if (_jspx_meth_html_form_0(pageContext))
        return;
      out.write("\r\n");
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
    _jspx_th_html_form_0.setAction("checkSecurityAnswer");
    _jspx_th_html_form_0.setMethod("post");
    int _jspx_eval_html_form_0 = _jspx_th_html_form_0.doStartTag();
    if (_jspx_eval_html_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n        ");
        out.write("<table cellspacing=\"0\" width=\"50%\" align=\"center\" cellpadding=\"0\">\r\n            ");
        out.write("<tr>\r\n                ");
        out.write("<td colspan=\"2\" class=\"content_title\" valign=\"middle\">");
        out.write("<img src=\"x-res/ARROW.GIF\">&nbsp;");
        if (_jspx_meth_fmt_message_0(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("</td>\r\n            ");
        out.write("</tr>\r\n            ");
        out.write("<tr>\r\n                ");
        out.write("<td class=\"shaded_header\" colspan=\"2\">");
        if (_jspx_meth_fmt_message_1(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("</td>\r\n            ");
        out.write("</tr>\r\n            ");
        out.write("<tr>\r\n                ");
        out.write("<td colspan=\"2\" class=\"shaded_text\">&nbsp;");
        out.write("</td>\r\n            ");
        out.write("</tr>\r\n            ");
        out.write("<tr>\r\n                ");
        out.write("<td class=\"shaded_caption\">\r\n                 ");
        if (_jspx_meth_fmt_message_2(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("\r\n                ");
        out.write("</td>\r\n                ");
        out.write("<td class=\"shaded_text\">\r\n                    ");
        out.write("<input class=\"content_input\" name=\"securityAnswer\" size=\"20\" maxlength=\"20\">\r\n                ");
        out.write("</td>\r\n            ");
        out.write("</tr>\r\n            ");
        out.write("<tr>\r\n                ");
        out.write("<td colspan=\"2\" class=\"shaded_text\">&nbsp;");
        out.write("</td>\r\n            ");
        out.write("</tr>\r\n            ");
        out.write("<tr>\r\n                ");
        out.write("<td class=\"shaded_text\" align=\"center\" colspan=\"2\">\r\n                    ");
        out.write("<input type=\"submit\" name=\"try\" value='");
        if (_jspx_meth_fmt_message_3(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("'/>\r\n                ");
        out.write("</td>\r\n            ");
        out.write("</tr>\r\n            ");
        out.write("<tr>\r\n                ");
        out.write("<td colspan=\"2\" class=\"shaded_text\">&nbsp;");
        out.write("</td>\r\n            ");
        out.write("</tr>\r\n        ");
        out.write("</table>\r\n    ");
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

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key_bundle.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_0.setBundle("${c4p_resellerBundle}");
    _jspx_th_fmt_message_0.setKey("title.passwordRecovery");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key_bundle.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_1.setKey("title.passwordRecovery.securityQuestion");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_bundle.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_2.setBundle("${c4p_resellerBundle}");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_eval_fmt_message_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_fmt_message_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        out = _bc;
        _jspx_th_fmt_message_2.setBodyContent(_bc);
        _jspx_th_fmt_message_2.doInitBody();
      }
      do {
        out.write("\r\n                  securityQuestion.");
        if (_jspx_meth_c_out_0(_jspx_th_fmt_message_2, pageContext))
          return true;
        out.write("\r\n                ");
        int evalDoAfterBody = _jspx_th_fmt_message_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_fmt_message_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
    }
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_bundle.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_2, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(_jspx_th_fmt_message_2);
    _jspx_th_c_out_0.setValue("${sessionScope.c4p_pr_question}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(pageContext);
    _jspx_th_fmt_message_3.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_3.setKey("action.continue");
    int _jspx_eval_fmt_message_3 = _jspx_th_fmt_message_3.doStartTag();
    if (_jspx_th_fmt_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_3);
    return false;
  }
}
