package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class login_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_param_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_bundle;

  public login_jsp() {
    _jspx_tagPool_c_if_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_choose = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_when_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_param_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key_bundle = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_c_out_value.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_fmt_param_value.release();
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
      out.write("\r\n\r\n");
      out.write("<script language=\"JavaScript\" type=\"text/javascript\">\r\n\r\n");
      out.write("<!--\r\nfunction OnLoadBasic(){\r\ndocument.forms[0].j_username.focus();\r\n}\r\n-->\r\n");
      out.write("</script>\r\n\r\n\r\n");
      out.write("<FORM action=\"https://");
      out.print(request.getServerName());
      out.write("/loginRouter.do\" method=\"post\" name=\"loginform\" >\r\n");
      out.write("\n\n");
      if (_jspx_meth_c_if_0(pageContext))
        return;
      out.write("\n\r\n\r\n");
      out.write("\r\n");
      out.write("<input type=\"hidden\" name=\"j_security_check\" value=\"/j_security_check\"/>\r\n");
      out.write("<!-- this section optional -->\r\n");
      out.write("<table width=\"95%\">\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<td align=\"center\">\r\n      ");
      if (_jspx_meth_c_choose_0(pageContext))
        return;
      out.write("\r\n");
      out.write("</td>\r\n ");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("<td>\r\n\r\n\r\n\r\n\t\t\t\t    ");
      out.write("<br>\r\n\r\n\r\n\r\n\t\t\t    ");
      out.write("</td>\r\n\r\n\r\n\r\n\t\t    ");
      out.write("</tr>\r\n\r\n\r\n\r\n\t    ");
      out.write("</table>\r\n\r\n\r\n\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n\r\n\t\t");
      out.write("<TABLE WIDTH=\"80%\" BORDER=\"0\" ALIGN=\"center\">\r\n\t\t\t");
      out.write("<TR>\r\n\t\t\t\t");
      out.write("<TD WIDTH=\"50%\" VALIGN=\"top\">\r\n\t\t\t\t");
      out.write("<TABLE WIDTH=\"100%\" BORDER=\"0\" ALIGN=\"center\">\r\n\t\t\t\t\t");
      out.write("<tr>");
      out.write("<td>\r\n\t\t\t\t\t");
      if (_jspx_meth_fmt_message_3(pageContext))
        return;
      out.write("\r\n\t\t\t\t\t");
      out.write("</td>");
      out.write("</tr>\r\n\t\t\t      \t");
      if (_jspx_meth_c_if_3(pageContext))
        return;
      out.write("\r\n\r\n\t\t\t      \t");
      out.write("<tr>\n\n\t\t\t          \t");
      out.write("<td align=\"center\" colspan=\"2\" class=\"left_body\">\n\t\t\t              ");
      out.write("<a class=\"left_body\" \n\n\t\t\t                href='showCreateAccountPage.do?did=");
      if (_jspx_meth_c_out_2(pageContext))
        return;
      out.write("'>\r\n\t\t\t                ");
      if (_jspx_meth_fmt_message_5(pageContext))
        return;
      out.write("\r\n\t\t\t                ");
      out.write("</a> \n\t\t\t          \t");
      out.write("</td>\n\n\n\t\t\t      \t");
      out.write("</tr>\n\r\n\t\t\t\t");
      out.write("</TABLE>\n\r\n\r\n\t\t\t\t");
      out.write("</TD>\r\n\t\t\t\t");
      out.write("<TD WIDTH=\"50%\">\r\n\r\n\r\n\t    ");
      out.write("<table cellspacing=\"0\" width=\"90%\" align=\"center\" cellpadding=\"0\">\r\n        ");
      out.write("<tr>\r\n            ");
      out.write("<td class=\"content_title\" valign=\"middle\">");
      out.write("<img src=\"x-res/ARROW.GIF\">&nbsp;");
      if (_jspx_meth_fmt_message_6(pageContext))
        return;
      out.write("</td>\r\n        ");
      out.write("</tr>\r\n        ");
      out.write("<tr>\r\n            ");
      out.write("<td colspan=\"2\" class=\"shaded_text\">\r\n                ");
      out.write("<br>\r\n                ");
      out.write("<table cellspacing=\"0\" cellpadding=\"0\" width=\"50%\" align=\"center\">\r\n                    ");
      out.write("<tr>\r\n                        ");
      out.write("<td class=\"shaded_caption\">");
      if (_jspx_meth_fmt_message_7(pageContext))
        return;
      out.write(":");
      out.write("</td>\r\n                        ");
      out.write("<td class=\"shaded_text\">\r\n                            ");
      out.write("<input class=\"content_input\" name=\"j_username\" size=\"20\" >\r\n                        ");
      out.write("</td>\r\n                    ");
      out.write("</tr>\r\n                    ");
      out.write("<tr>\r\n                      ");
      out.write("<td class=\"shaded_caption\">");
      if (_jspx_meth_fmt_message_8(pageContext))
        return;
      out.write(":");
      out.write("</td>\r\n                      ");
      out.write("<td class=\"shaded_text\">");
      out.write("<input class=\"content_input\" name=\"j_password\" type=\"password\" size=\"20\" maxlength=\"12\">");
      out.write("</td>\r\n                    ");
      out.write("</tr>\r\n                    ");
      out.write("<tr>\r\n                        ");
      out.write("<td class=\"shaded_text\" align=\"center\" colspan=\"2\">\r\n                            ");
      out.write("<input type=\"submit\" name=\"login_secure\" value=");
      if (_jspx_meth_fmt_message_9(pageContext))
        return;
      out.write(">\r\n                        ");
      out.write("</td>\r\n                    ");
      out.write("</tr>\r\n\t\t\t\t\t");
      out.write("<TR align=\"center\">\r\n\t\t\t\t\t\t");
      out.write("<TD colspan=\"2\" class=\"shaded_text\">Mode: ");
      if (_jspx_meth_fmt_message_10(pageContext))
        return;
      out.write(" | \r\n                \t\t\t");
      out.write("<a class=\"content_link\" href=\"http://");
      out.print(request.getServerName());
      out.write("/showStdLoginPage.do\">\r\n                  \t\t\t\t");
      if (_jspx_meth_fmt_message_11(pageContext))
        return;
      out.write("\r\n                \t\t\t");
      out.write("</a>\r\n\t\t\t\t\t\t");
      out.write("</TD>\r\n\t\t\t\t\t");
      out.write("</TR>\r\n\t\t\t\t\t");
      out.write("<TR align=\"center\">\r\n\t\t\t\t\t\t");
      out.write("<TD colspan=\"2\" class=\"shaded_text\">\r\n\t\t\t                ");
      out.write("<a class=\"content_link\" href=\"/showPasswordRecoverypage.do\">\r\n\t\t\t                  ");
      if (_jspx_meth_fmt_message_12(pageContext))
        return;
      out.write("\r\n\t\t\t                ");
      out.write("</a>\r\n\t\t\t\t\t\t");
      out.write("</TD>\r\n\t\t\t\t\t");
      out.write("</TR>\r\n\r\n                ");
      out.write("</table>\r\n                ");
      out.write("<br>\r\n\t\t\t\t");
      out.write("<tr>\r\n\t\t\t\t\t");
      out.write("<td>\r\n\t\t\t\t\t\t");
      out.write("<br>\r\n\t\t\t\t\t");
      out.write("</td>\r\n\t\t\t\t");
      out.write("</tr>\r\n\t    ");
      out.write("</table>\r\n   ");
      out.write("</form>\r\n");
      out.write("\r\n\r\n\r\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(pageContext);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest("${not empty param.did}");
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n  ");
        out.write("<input type=\"hidden\" name=\"did\" value='");
        if (_jspx_meth_c_out_0(_jspx_th_c_if_0, pageContext))
          return true;
        out.write("'/>\n\n");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(_jspx_th_c_if_0);
    _jspx_th_c_out_0.setValue("${param.did}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_c_choose_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:choose ---- */
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_choose_0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _jspx_tagPool_c_choose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_choose_0.setPageContext(pageContext);
    _jspx_th_c_choose_0.setParent(null);
    int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
    if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n        ");
        if (_jspx_meth_c_when_0(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\r\n      ");
        if (_jspx_meth_c_when_1(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\r\n      ");
        if (_jspx_meth_c_when_2(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\r\n      ");
        if (_jspx_meth_c_when_3(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\r\n    ");
        int evalDoAfterBody = _jspx_th_c_choose_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
    return false;
  }

  private boolean _jspx_meth_c_when_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_0 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_0.setPageContext(pageContext);
    _jspx_th_c_when_0.setParent(_jspx_th_c_choose_0);
    _jspx_th_c_when_0.setTest("${param.action eq \"protected\"}");
    int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
    if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n \r\n        ");
        int evalDoAfterBody = _jspx_th_c_when_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
    return false;
  }

  private boolean _jspx_meth_c_when_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_1 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_1.setPageContext(pageContext);
    _jspx_th_c_when_1.setParent(_jspx_th_c_choose_0);
    _jspx_th_c_when_1.setTest("${param.action eq \"logout\"}");
    int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
    if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n        ");
        out.write("<span class=\"content_error_heading\">\r\n           ");
        out.write("<br>\r\n           ");
        if (_jspx_meth_c_if_1(_jspx_th_c_when_1, pageContext))
          return true;
        out.write("\r\n            ");
        if (_jspx_meth_c_if_2(_jspx_th_c_when_1, pageContext))
          return true;
        out.write("\r\n      ");
        int evalDoAfterBody = _jspx_th_c_when_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
    return false;
  }

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_when_1, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(pageContext);
    _jspx_th_c_if_1.setParent(_jspx_th_c_when_1);
    _jspx_th_c_if_1.setTest("${not empty param.username}");
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n        \t  ");
        if (_jspx_meth_fmt_message_0(_jspx_th_c_if_1, pageContext))
          return true;
        out.write("\r\n            ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(_jspx_th_c_if_1);
    _jspx_th_fmt_message_0.setKey("msg.logoutNamedUserMessage");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_eval_fmt_message_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_fmt_message_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        out = _bc;
        _jspx_th_fmt_message_0.setBodyContent(_bc);
        _jspx_th_fmt_message_0.doInitBody();
      }
      do {
        out.write("\r\n                ");
        if (_jspx_meth_fmt_param_0(_jspx_th_fmt_message_0, pageContext))
          return true;
        out.write("\r\n              ");
        int evalDoAfterBody = _jspx_th_fmt_message_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_fmt_message_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
    }
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_fmt_param_0(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_0 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_0.setPageContext(pageContext);
    _jspx_th_fmt_param_0.setParent(_jspx_th_fmt_message_0);
    _jspx_th_fmt_param_0.setValue("${param.username}");
    int _jspx_eval_fmt_param_0 = _jspx_th_fmt_param_0.doStartTag();
    if (_jspx_th_fmt_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_0);
    return false;
  }

  private boolean _jspx_meth_c_if_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_when_1, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(pageContext);
    _jspx_th_c_if_2.setParent(_jspx_th_c_when_1);
    _jspx_th_c_if_2.setTest("${empty param.username}");
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n        \t  ");
        if (_jspx_meth_fmt_message_1(_jspx_th_c_if_2, pageContext))
          return true;
        out.write("\r\n            ");
        int evalDoAfterBody = _jspx_th_c_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
    return false;
  }

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_2, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(_jspx_th_c_if_2);
    _jspx_th_fmt_message_1.setKey("msg.logoutMessage");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_c_when_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_2 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_2.setPageContext(pageContext);
    _jspx_th_c_when_2.setParent(_jspx_th_c_choose_0);
    _jspx_th_c_when_2.setTest("${param.action eq \"error\"}");
    int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
    if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n        ");
        out.write("<span class=\"content_error_heading\">\r\n\r\n          ");
        out.write("<br>");
        out.write("<img src=\"x-res/ALERT.GIF\" border=\"0\"/>&nbsp;");
        if (_jspx_meth_fmt_message_2(_jspx_th_c_when_2, pageContext))
          return true;
        out.write("\r\n\r\n        ");
        out.write("</span>\r\n      ");
        int evalDoAfterBody = _jspx_th_c_when_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
    return false;
  }

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_when_2, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(_jspx_th_c_when_2);
    _jspx_th_fmt_message_2.setKey("msg.invalidUsernameOrPassword");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_c_when_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_3 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_3.setPageContext(pageContext);
    _jspx_th_c_when_3.setParent(_jspx_th_c_choose_0);
    _jspx_th_c_when_3.setTest("${param.action eq \"expiry\"}");
    int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
    if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n      ");
        int evalDoAfterBody = _jspx_th_c_when_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
    return false;
  }

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(pageContext);
    _jspx_th_fmt_message_3.setParent(null);
    _jspx_th_fmt_message_3.setKey("msg.marketing");
    int _jspx_eval_fmt_message_3 = _jspx_th_fmt_message_3.doStartTag();
    if (_jspx_th_fmt_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_3);
    return false;
  }

  private boolean _jspx_meth_c_if_3(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(pageContext);
    _jspx_th_c_if_3.setParent(null);
    _jspx_th_c_if_3.setTest("${not empty param.did}");
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\t\t\t      \t");
        out.write("<tr>\n\n\t\t\t          \t");
        out.write("<td align=\"center\" colspan=\"2\" class=\"left_body\">\n\n\t\t\t              ");
        out.write("<a class=\"left_body\" \n\n\t\t\t                href='showAttachDesktopToAccountPage.do?did=");
        if (_jspx_meth_c_out_1(_jspx_th_c_if_3, pageContext))
          return true;
        out.write("'>\r\n\t\t\t                ");
        if (_jspx_meth_fmt_message_4(_jspx_th_c_if_3, pageContext))
          return true;
        out.write("\r\n\t\t\t                ");
        out.write("</a> \n\n\t\t\t         \t ");
        out.write("</td>\n\n\n\t\t\t     \t ");
        out.write("</tr>\n\n\t     \t\t\t ");
        int evalDoAfterBody = _jspx_th_c_if_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_3, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_c_if_3);
    _jspx_th_c_out_1.setValue("${param.did}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_fmt_message_4(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_3, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_4 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_4.setPageContext(pageContext);
    _jspx_th_fmt_message_4.setParent(_jspx_th_c_if_3);
    _jspx_th_fmt_message_4.setKey("msg.link.assignaccount");
    int _jspx_eval_fmt_message_4 = _jspx_th_fmt_message_4.doStartTag();
    if (_jspx_th_fmt_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_4);
    return false;
  }

  private boolean _jspx_meth_c_out_2(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_2.setPageContext(pageContext);
    _jspx_th_c_out_2.setParent(null);
    _jspx_th_c_out_2.setValue("${param.did}");
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_2);
    return false;
  }

  private boolean _jspx_meth_fmt_message_5(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_5 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_5.setPageContext(pageContext);
    _jspx_th_fmt_message_5.setParent(null);
    _jspx_th_fmt_message_5.setKey("msg.link.createaccount");
    int _jspx_eval_fmt_message_5 = _jspx_th_fmt_message_5.doStartTag();
    if (_jspx_th_fmt_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_5);
    return false;
  }

  private boolean _jspx_meth_fmt_message_6(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_6 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key_bundle.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_6.setPageContext(pageContext);
    _jspx_th_fmt_message_6.setParent(null);
    _jspx_th_fmt_message_6.setKey("message.login");
    _jspx_th_fmt_message_6.setBundle("${c4p_resellerBundle}");
    int _jspx_eval_fmt_message_6 = _jspx_th_fmt_message_6.doStartTag();
    if (_jspx_th_fmt_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key_bundle.reuse(_jspx_th_fmt_message_6);
    return false;
  }

  private boolean _jspx_meth_fmt_message_7(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_7 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_7.setPageContext(pageContext);
    _jspx_th_fmt_message_7.setParent(null);
    _jspx_th_fmt_message_7.setKey("field.username");
    int _jspx_eval_fmt_message_7 = _jspx_th_fmt_message_7.doStartTag();
    if (_jspx_th_fmt_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_7);
    return false;
  }

  private boolean _jspx_meth_fmt_message_8(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_8 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_8.setPageContext(pageContext);
    _jspx_th_fmt_message_8.setParent(null);
    _jspx_th_fmt_message_8.setKey("field.password");
    int _jspx_eval_fmt_message_8 = _jspx_th_fmt_message_8.doStartTag();
    if (_jspx_th_fmt_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_8);
    return false;
  }

  private boolean _jspx_meth_fmt_message_9(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_9 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_9.setPageContext(pageContext);
    _jspx_th_fmt_message_9.setParent(null);
    _jspx_th_fmt_message_9.setKey("action.login");
    int _jspx_eval_fmt_message_9 = _jspx_th_fmt_message_9.doStartTag();
    if (_jspx_th_fmt_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_9);
    return false;
  }

  private boolean _jspx_meth_fmt_message_10(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_10 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_10.setPageContext(pageContext);
    _jspx_th_fmt_message_10.setParent(null);
    _jspx_th_fmt_message_10.setKey("msg.secure");
    int _jspx_eval_fmt_message_10 = _jspx_th_fmt_message_10.doStartTag();
    if (_jspx_th_fmt_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_10);
    return false;
  }

  private boolean _jspx_meth_fmt_message_11(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_11 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_11.setPageContext(pageContext);
    _jspx_th_fmt_message_11.setParent(null);
    _jspx_th_fmt_message_11.setKey("msg.standard");
    int _jspx_eval_fmt_message_11 = _jspx_th_fmt_message_11.doStartTag();
    if (_jspx_th_fmt_message_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_11);
    return false;
  }

  private boolean _jspx_meth_fmt_message_12(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_12 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_12.setPageContext(pageContext);
    _jspx_th_fmt_message_12.setParent(null);
    _jspx_th_fmt_message_12.setKey("msg.link.forgotYourPassword");
    int _jspx_eval_fmt_message_12 = _jspx_th_fmt_message_12.doStartTag();
    if (_jspx_th_fmt_message_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_12);
    return false;
  }
}
