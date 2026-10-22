package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class login_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_bundle;

  public login_jsp() {
    _jspx_tagPool_c_choose = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_when_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key_bundle = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_fmt_message_key.release();
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

      out.write("\n\n\n\n");
      out.write("\n\n\n\n");
      out.write("\n\n\n\n");
      out.write("<script language=\"JavaScript\" type=\"text/javascript\">\n\n");
      out.write("<!--\nfunction OnLoadBasic(){\ndocument.forms[0].j_username.focus();\n}\n-->\n");
      out.write("</script>\n\n\n\n");
      out.write("<FORM action=\"https://");
      out.print(request.getServerName());
      out.write("/loginRouter.do\" method=\"post\" >\n\n");
      out.write("<!--");
      out.write("<FORM action=\"/loginRouter.do\" method=\"post\" >-->\n\n\n\n  ");
      out.write("\n\n\n\n  ");
      out.write("<input type=\"hidden\" name=\"j_security_check\" value=\"/j_security_check\"/>\n\n\n\n\n\n\n\n  ");
      out.write("\n\n\n\n");
      out.write("<!-- this section optional -->\n\n\n\n  ");
      out.write("<table cellspacing=\"0\" align=\"center\" cellpadding=\"0\">\n\n\n\n    ");
      if (_jspx_meth_c_choose_0(pageContext))
        return;
      out.write("\n\n\n\n\t\t\t\t");
      out.write("<tr>\n\n\t\t\t\t");
      out.write("<td>\n\n\t\t\t\t");
      out.write("<table cellspacing=\"0\" align=\"center\" cellpadding=\"0\">\n\n\n        ");
      out.write("<tr>\n\n\n\n            ");
      out.write("<td colspan=\"2\" class=\"body_heading\" valign=\"middle\">");
      out.write("<img src=\"x-res/ARROW.GIF\">&nbsp;");
      if (_jspx_meth_fmt_message_2(pageContext))
        return;
      out.write("</td>\n\n\n\n        ");
      out.write("</tr>\n\t\t\t\t\n\t\t\t\t");
      out.write("<tr>\n\n\t\t\t\t\t");
      out.write("<td>");
      out.write("<br>");
      out.write("</td>\n\n\t\t\t\t");
      out.write("</tr>\n        ");
      out.write("<tr>\n\n\n\n            ");
      out.write("<td align=\"left\" class=\"body_heading\">");
      if (_jspx_meth_fmt_message_3(pageContext))
        return;
      out.write(":");
      out.write("</td>\n\n\n\n            ");
      out.write("<td class=\"body_text\">\n\n\n\n                ");
      out.write("<input class=\"content_input\" name=\"j_username\" size=\"15\">\n\n\n\n            ");
      out.write("</td>\n\n\n\n        ");
      out.write("</tr>\n\n\n\n        ");
      out.write("<tr>\n\n\n\n          ");
      out.write("<td align=\"left\" class=\"body_heading\">");
      if (_jspx_meth_fmt_message_4(pageContext))
        return;
      out.write(":");
      out.write("</td>\n\n\n\n          ");
      out.write("<td class=\"body_text\">");
      out.write("<input class=\"content_input\" name=\"j_password\" type=\"password\" size=\"15\" maxlength=\"12\">");
      out.write("</td>\n\n\n\n        ");
      out.write("</tr>\n\n\t\t\t\t");
      out.write("<tr>\n\n\t\t\t\t\t");
      out.write("<td>");
      out.write("<br>");
      out.write("</td>\n\n\t\t\t\t");
      out.write("</tr>\n\n\n        ");
      out.write("<tr class=\"body_heading\">\n\n          ");
      out.write("<td colspan=\"2\" align=\"center\" class=\"body_text\">\n\n            ");
      out.write("<input type=\"submit\" name=\"login_secure\" value=\"Login\"/>\n\n\t        ");
      out.write("</td>\n\n        ");
      out.write("</tr>\n\n        ");
      out.write("</table>\n\n        ");
      out.write("</td>\n\n        ");
      out.write("</tr>\n\n\t\t\t\t");
      out.write("<tr>\n\n\t\t\t\t\t");
      out.write("<td>");
      out.write("<img width=\"1\" height=\"3\" border=\"0\">");
      out.write("</td>\n\n\t\t\t\t");
      out.write("</tr>\n\n        ");
      out.write("<tr>\n\n\n\n            ");
      out.write("<td align=\"center\">\n\n                ");
      out.write("<a class=\"body_text\" href=\"http://");
      out.print(request.getServerName());
      out.write("/showStdLoginPage.do\">\n\n\n\n                  ");
      if (_jspx_meth_fmt_message_5(pageContext))
        return;
      out.write("\n\n\n\n                ");
      out.write("</a>\n\n\n\n            ");
      out.write("</td>\n\n\n\n        ");
      out.write("</tr>\n\n\n\n        ");
      out.write("<tr>\n\n\n\n            ");
      out.write("<td align=\"center\">\n\n\n\n                ");
      out.write("<a class=\"body_text\" href=\"/showPasswordRecoverypage.do\">\n\n\n\n                  ");
      if (_jspx_meth_fmt_message_6(pageContext))
        return;
      out.write("\n\n\n\n                ");
      out.write("</a>\n\n\n\n            ");
      out.write("</td>\n\n\n\n        ");
      out.write("</tr>\n\n\n\n\t    ");
      out.write("</table>\n\n\n\n    ");
      out.write("</form>\n\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
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
        out.write("\n\n\n\n      ");
        if (_jspx_meth_c_when_0(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\n\n\n\n      ");
        if (_jspx_meth_c_when_1(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\n\n\n\n      ");
        if (_jspx_meth_c_when_2(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\n\n\n\n      ");
        if (_jspx_meth_c_when_3(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\n\n\n\n    ");
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
        out.write("\n\n\n\n      ");
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
        out.write("\n\n\t\t\t  ");
        out.write("<tr>\n\n\t\t\t  ");
        out.write("<td align=\"center\" class=\"body_text\">\n\n        ");
        if (_jspx_meth_fmt_message_0(_jspx_th_c_when_1, pageContext))
          return true;
        out.write("<br>&nbsp;\n\n\t\t\t\t");
        out.write("</td>\n\n\t\t\t\t");
        out.write("</tr>\n\n      ");
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

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_when_1, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(_jspx_th_c_when_1);
    _jspx_th_fmt_message_0.setKey("msg.logoutMessage");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_0);
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
        out.write("\n\n\t\t\t  ");
        out.write("<tr>\n\n\t\t\t  ");
        out.write("<td align=\"center\" class=\"body_text\">\n\n        ");
        out.write("<img src=\"x-res/ALERT.GIF\" border=\"0\"/>\n\n        ");
        out.write("<span class=\"body_warning\">\n\n          ");
        if (_jspx_meth_fmt_message_1(_jspx_th_c_when_2, pageContext))
          return true;
        out.write("<br>&nbsp;\n\n        ");
        out.write("</span>\n\n\t\t\t\t");
        out.write("</td>\n\n\t\t\t\t");
        out.write("</tr>\n\n      ");
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

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_when_2, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(_jspx_th_c_when_2);
    _jspx_th_fmt_message_1.setKey("msg.invalidUsernameOrPassword");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
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
        out.write("\n\n      ");
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

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key_bundle.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(null);
    _jspx_th_fmt_message_2.setKey("message.pda_login");
    _jspx_th_fmt_message_2.setBundle("${c4p_resellerBundle}");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key_bundle.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(pageContext);
    _jspx_th_fmt_message_3.setParent(null);
    _jspx_th_fmt_message_3.setKey("field.username");
    int _jspx_eval_fmt_message_3 = _jspx_th_fmt_message_3.doStartTag();
    if (_jspx_th_fmt_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_3);
    return false;
  }

  private boolean _jspx_meth_fmt_message_4(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_4 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_4.setPageContext(pageContext);
    _jspx_th_fmt_message_4.setParent(null);
    _jspx_th_fmt_message_4.setKey("field.password");
    int _jspx_eval_fmt_message_4 = _jspx_th_fmt_message_4.doStartTag();
    if (_jspx_th_fmt_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_4);
    return false;
  }

  private boolean _jspx_meth_fmt_message_5(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_5 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_5.setPageContext(pageContext);
    _jspx_th_fmt_message_5.setParent(null);
    _jspx_th_fmt_message_5.setKey("msg.link.noHttpsSupport");
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
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_6 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_6.setPageContext(pageContext);
    _jspx_th_fmt_message_6.setParent(null);
    _jspx_th_fmt_message_6.setKey("msg.link.forgotYourPassword");
    int _jspx_eval_fmt_message_6 = _jspx_th_fmt_message_6.doStartTag();
    if (_jspx_th_fmt_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_6);
    return false;
  }
}
