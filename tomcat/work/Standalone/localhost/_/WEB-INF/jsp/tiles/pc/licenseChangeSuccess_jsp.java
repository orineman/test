package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class licenseChangeSuccess_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_bundle;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c4_LicenseCodeDisplay_code;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;

  public licenseChangeSuccess_jsp() {
    _jspx_tagPool_fmt_message_key_bundle = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_if_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_forEach_var_items = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_choose = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_when_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c4_LicenseCodeDisplay_code = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_otherwise = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_fmt_message_key_bundle.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_c_out_value.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_c4_LicenseCodeDisplay_code.release();
    _jspx_tagPool_c_otherwise.release();
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
      out.write("\r\n");
      out.write("\r\n\r\n");
      out.write("<script type=\"text/javascript\" language=\"JavaScript\">\r\n");
      out.write("<!--\r\nfunction setTitle(){\r\ndocument.title='");
      if (_jspx_meth_fmt_message_0(pageContext))
        return;
      out.write("';\r\nrefreshOpener();\r\n}\r\n\r\nfunction cancel(){\r\nthis.close();\r\n}\r\n\r\nfunction refreshOpener(){\r\n// Refresh the opener window if we were passed a page in the request\r\n ");
      if (_jspx_meth_c_if_0(pageContext))
        return;
      out.write("\r\n}\r\n// -->\r\n");
      out.write("</script>\r\n      ");
      out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n        ");
      out.write("<tr>\r\n          ");
      out.write("<td>\r\n            ");
      out.write("<b>");
      out.write("<font color=\"blue\">\r\n                ");
      if (_jspx_meth_fmt_message_1(pageContext))
        return;
      out.write("\r\n            ");
      out.write("</font>");
      out.write("</b>\r\n          ");
      out.write("</td>\r\n        ");
      out.write("</tr>\r\n        ");
      out.write("<tr>\r\n          ");
      out.write("<td>&nbsp;");
      out.write("</td>\r\n        ");
      out.write("</tr>\r\n        ");
      /* ----  c:if ---- */
      org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
      _jspx_th_c_if_1.setPageContext(pageContext);
      _jspx_th_c_if_1.setParent(null);
      _jspx_th_c_if_1.setTest("${requestScope.c4p_freedup_licenses != null}");
      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n          ");
          out.write("<tr>\r\n            ");
          out.write("<td>\r\n              The following licenses can now be used on other PC's:");
          out.write("<br>\r\n              ");
          out.write("<ul>\r\n              ");
          /* ----  c:forEach ---- */
          org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
          _jspx_th_c_forEach_0.setPageContext(pageContext);
          _jspx_th_c_forEach_0.setParent(_jspx_th_c_if_1);
          _jspx_th_c_forEach_0.setItems("${requestScope.c4p_freedup_licenses}");
          _jspx_th_c_forEach_0.setVar("license");
          int _jspx_push_body_count_c_forEach_0 = 0;
          try {
            int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
            if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\r\n                ");
                out.write("<li>\r\n                ");
                if (_jspx_meth_c_choose_0(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("\r\n                ");
                out.write("</li>\r\n              ");
                int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
              return;
          } catch (Throwable _jspx_exception) {
            while (_jspx_push_body_count_c_forEach_0-- > 0)
              out = pageContext.popBody();
            _jspx_th_c_forEach_0.doCatch(_jspx_exception);
          } finally {
            _jspx_th_c_forEach_0.doFinally();
            _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
          }
          out.write("\r\n              ");
          out.write("</ul>\r\n            ");
          out.write("</td>\r\n          ");
          out.write("</tr>\r\n        ");
          int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      out.write("\r\n\r\n        ");
      out.write("<tr>\r\n          ");
      out.write("<td align=\"center\">\r\n            ");
      out.write("<input type=\"button\" onClick=\"cancel()\" value='");
      if (_jspx_meth_fmt_message_2(pageContext))
        return;
      out.write("'/>\r\n          ");
      out.write("</td>\r\n        ");
      out.write("</tr>\r\n      ");
      out.write("</table>\r\n");
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
    _jspx_th_fmt_message_0.setKey("title.subscripion");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key_bundle.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(pageContext);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest("${not empty requestScope.page}");
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n    opener.location.replace('");
        if (_jspx_meth_c_out_0(_jspx_th_c_if_0, pageContext))
          return true;
        out.write("');\r\n ");
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
    _jspx_th_c_out_0.setValue("${requestScope.page}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(null);
    _jspx_th_fmt_message_1.setKey("msg.licenseChangeSuccess");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_c_choose_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:choose ---- */
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_choose_0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _jspx_tagPool_c_choose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_choose_0.setPageContext(pageContext);
    _jspx_th_c_choose_0.setParent(_jspx_th_c_forEach_0);
    int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
    if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n                  ");
        if (_jspx_meth_c_when_0(_jspx_th_c_choose_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n                  ");
        if (_jspx_meth_c_otherwise_0(_jspx_th_c_choose_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n                ");
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

  private boolean _jspx_meth_c_when_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_0 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_0.setPageContext(pageContext);
    _jspx_th_c_when_0.setParent(_jspx_th_c_choose_0);
    _jspx_th_c_when_0.setTest("${license.fixedTerm}");
    int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
    if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n                    ");
        if (_jspx_meth_c4_LicenseCodeDisplay_0(_jspx_th_c_when_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n                     with ");
        if (_jspx_meth_c_out_1(_jspx_th_c_when_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("&nbsp;day(s) remaining\r\n                     ");
        if (_jspx_meth_c_if_2(_jspx_th_c_when_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n                  ");
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

  private boolean _jspx_meth_c4_LicenseCodeDisplay_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_when_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c4:LicenseCodeDisplay ---- */
    com.infowave.c4p.web.tags.LicenseCodeDisplayTag _jspx_th_c4_LicenseCodeDisplay_0 = (com.infowave.c4p.web.tags.LicenseCodeDisplayTag) _jspx_tagPool_c4_LicenseCodeDisplay_code.get(com.infowave.c4p.web.tags.LicenseCodeDisplayTag.class);
    _jspx_th_c4_LicenseCodeDisplay_0.setPageContext(pageContext);
    _jspx_th_c4_LicenseCodeDisplay_0.setParent(_jspx_th_c_when_0);
    _jspx_th_c4_LicenseCodeDisplay_0.setCode("${license.code}");
    int _jspx_eval_c4_LicenseCodeDisplay_0 = _jspx_th_c4_LicenseCodeDisplay_0.doStartTag();
    if (_jspx_th_c4_LicenseCodeDisplay_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c4_LicenseCodeDisplay_code.reuse(_jspx_th_c4_LicenseCodeDisplay_0);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_when_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_c_when_0);
    _jspx_th_c_out_1.setValue("${license.remainingDays}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_c_if_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_when_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(pageContext);
    _jspx_th_c_if_2.setParent(_jspx_th_c_when_0);
    _jspx_th_c_if_2.setTest("${license.numAvailableUses>1}");
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("(# uses:&nbsp;");
        if (_jspx_meth_c_out_2(_jspx_th_c_if_2, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write(")\r\n                     ");
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

  private boolean _jspx_meth_c_out_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_2, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_2.setPageContext(pageContext);
    _jspx_th_c_out_2.setParent(_jspx_th_c_if_2);
    _jspx_th_c_out_2.setValue("${license.numAvailableUses}");
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_2);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:otherwise ---- */
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_otherwise_0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(pageContext);
    _jspx_th_c_otherwise_0.setParent(_jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n                    ");
        if (_jspx_meth_c4_LicenseCodeDisplay_1(_jspx_th_c_otherwise_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n                     ");
        if (_jspx_meth_c_if_3(_jspx_th_c_otherwise_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n                  ");
        int evalDoAfterBody = _jspx_th_c_otherwise_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_c4_LicenseCodeDisplay_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_otherwise_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c4:LicenseCodeDisplay ---- */
    com.infowave.c4p.web.tags.LicenseCodeDisplayTag _jspx_th_c4_LicenseCodeDisplay_1 = (com.infowave.c4p.web.tags.LicenseCodeDisplayTag) _jspx_tagPool_c4_LicenseCodeDisplay_code.get(com.infowave.c4p.web.tags.LicenseCodeDisplayTag.class);
    _jspx_th_c4_LicenseCodeDisplay_1.setPageContext(pageContext);
    _jspx_th_c4_LicenseCodeDisplay_1.setParent(_jspx_th_c_otherwise_0);
    _jspx_th_c4_LicenseCodeDisplay_1.setCode("${license.code}");
    int _jspx_eval_c4_LicenseCodeDisplay_1 = _jspx_th_c4_LicenseCodeDisplay_1.doStartTag();
    if (_jspx_th_c4_LicenseCodeDisplay_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c4_LicenseCodeDisplay_code.reuse(_jspx_th_c4_LicenseCodeDisplay_1);
    return false;
  }

  private boolean _jspx_meth_c_if_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_otherwise_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(pageContext);
    _jspx_th_c_if_3.setParent(_jspx_th_c_otherwise_0);
    _jspx_th_c_if_3.setTest("${license.numAvailableUses>1}");
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n                       (# uses:&nbsp;");
        if (_jspx_meth_c_out_3(_jspx_th_c_if_3, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write(")\r\n                     ");
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

  private boolean _jspx_meth_c_out_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_3, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_3 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_3.setPageContext(pageContext);
    _jspx_th_c_out_3.setParent(_jspx_th_c_if_3);
    _jspx_th_c_out_3.setValue("${license.numAvailableUses}");
    int _jspx_eval_c_out_3 = _jspx_th_c_out_3.doStartTag();
    if (_jspx_th_c_out_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_3);
    return false;
  }

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(null);
    _jspx_th_fmt_message_2.setKey("action.close");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_2);
    return false;
  }
}
