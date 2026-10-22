package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class enterLicenseCode_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_bundle;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_form_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c4_LicenseCodeEntry_namePrefix_displayClass_code;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_param_value;

  public enterLicenseCode_jsp() {
    _jspx_tagPool_fmt_message_key_bundle = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_html_form_method_action = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_if_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c4_LicenseCodeEntry_namePrefix_displayClass_code = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_param_value = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_fmt_message_key_bundle.release();
    _jspx_tagPool_html_form_method_action.release();
    _jspx_tagPool_c_out_value.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_c4_LicenseCodeEntry_namePrefix_displayClass_code.release();
    _jspx_tagPool_fmt_param_value.release();
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

      out.write("\r\n\r\n");
      out.write("\r\n\r\n");
      out.write("\r\n\r\n");
      out.write("\r\n\r\n");
      out.write("<script type=\"text/javascript\" language=\"JavaScript\">\r\n\r\n");
      out.write("<!--\r\n\r\nfunction setTitle(){\r\n\r\ndocument.title='");
      if (_jspx_meth_fmt_message_0(pageContext))
        return;
      out.write("';\r\n\r\n\r\n\r\n}\r\n\r\n\r\n\r\nfunction cancel(){\r\n\r\nthis.close();\r\n\r\n}\r\n\r\n\r\n\r\n// -->\r\n\r\n");
      out.write("</script>\r\n\r\n      ");
      if (_jspx_meth_html_form_0(pageContext))
        return;
      out.write("\r\n\r\n");
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

  private boolean _jspx_meth_html_form_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:form ---- */
    org.apache.struts.taglib.html.FormTag _jspx_th_html_form_0 = (org.apache.struts.taglib.html.FormTag) _jspx_tagPool_html_form_method_action.get(org.apache.struts.taglib.html.FormTag.class);
    _jspx_th_html_form_0.setPageContext(pageContext);
    _jspx_th_html_form_0.setParent(null);
    _jspx_th_html_form_0.setAction("/protected/getLicenseCode");
    _jspx_th_html_form_0.setMethod("post");
    int _jspx_eval_html_form_0 = _jspx_th_html_form_0.doStartTag();
    if (_jspx_eval_html_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n\r\n      ");
        out.write("<input type=\"hidden\" name=\"did\" value='");
        if (_jspx_meth_c_out_0(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("' >\r\n\r\n      ");
        out.write("<input type=\"hidden\" name=\"expired\" value='");
        if (_jspx_meth_c_out_1(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("' >\r\n\r\n\r\n\r\n\r\n\r\n      ");
        out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td>\r\n\r\n            ");
        if (_jspx_meth_c_if_0(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("\r\n\r\n          ");
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td>");
        if (_jspx_meth_fmt_message_2(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td>\r\n\r\n          \t");
        if (_jspx_meth_fmt_message_3(_jspx_th_html_form_0, pageContext))
          return true;
        out.write(":&nbsp;\r\n\r\n\r\n\r\n            ");
        if (_jspx_meth_c4_LicenseCodeEntry_0(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("\r\n\r\n          ");
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td>");
        if (_jspx_meth_fmt_message_4(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td>\r\n\r\n            ");
        if (_jspx_meth_fmt_message_5(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("\r\n\r\n\r\n\r\n\r\n\r\n            ");
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n        ");
        out.write("<tr>\r\n\r\n          ");
        out.write("<td align=\"center\">\r\n\r\n            ");
        out.write("<input type=\"submit\" name=\"enterLicenseCodeButton\" value='");
        if (_jspx_meth_fmt_message_6(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("'/>&nbsp;\r\n\r\n            ");
        out.write("<input type=\"button\" onClick=\"cancel()\" value='");
        if (_jspx_meth_fmt_message_7(_jspx_th_html_form_0, pageContext))
          return true;
        out.write("'/>\r\n\r\n          ");
        out.write("</td>\r\n\r\n        ");
        out.write("</tr>\r\n\r\n      ");
        out.write("</table>\r\n\r\n    ");
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

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_html_form_0);
    _jspx_th_c_out_1.setValue("${requestScope.expired}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(pageContext);
    _jspx_th_c_if_0.setParent(_jspx_th_html_form_0);
    _jspx_th_c_if_0.setTest("${requestScope.expired == false}");
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n\r\n              ");
        out.write("<b>");
        out.write("<font color=\"blue\">\r\n\r\n                ");
        if (_jspx_meth_fmt_message_1(_jspx_th_c_if_0, pageContext))
          return true;
        out.write("\r\n\r\n              ");
        out.write("</font>");
        out.write("</b>\r\n\r\n            ");
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

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(_jspx_th_c_if_0);
    _jspx_th_fmt_message_1.setKey("msg.licenseHasNotExpiredWarning");
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
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_2.setKey("msg.pleaseEnterUnusedLicense");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(pageContext);
    _jspx_th_fmt_message_3.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_3.setKey("action.setLicense");
    int _jspx_eval_fmt_message_3 = _jspx_th_fmt_message_3.doStartTag();
    if (_jspx_th_fmt_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_3);
    return false;
  }

  private boolean _jspx_meth_c4_LicenseCodeEntry_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c4:LicenseCodeEntry ---- */
    com.infowave.c4p.web.tags.LicenseCodeEntryTag _jspx_th_c4_LicenseCodeEntry_0 = (com.infowave.c4p.web.tags.LicenseCodeEntryTag) _jspx_tagPool_c4_LicenseCodeEntry_namePrefix_displayClass_code.get(com.infowave.c4p.web.tags.LicenseCodeEntryTag.class);
    _jspx_th_c4_LicenseCodeEntry_0.setPageContext(pageContext);
    _jspx_th_c4_LicenseCodeEntry_0.setParent(_jspx_th_html_form_0);
    _jspx_th_c4_LicenseCodeEntry_0.setCode("${param.code}");
    _jspx_th_c4_LicenseCodeEntry_0.setNamePrefix("group");
    _jspx_th_c4_LicenseCodeEntry_0.setDisplayClass("content_input");
    int _jspx_eval_c4_LicenseCodeEntry_0 = _jspx_th_c4_LicenseCodeEntry_0.doStartTag();
    if (_jspx_th_c4_LicenseCodeEntry_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c4_LicenseCodeEntry_namePrefix_displayClass_code.reuse(_jspx_th_c4_LicenseCodeEntry_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_4(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_4 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key_bundle.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_4.setPageContext(pageContext);
    _jspx_th_fmt_message_4.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_4.setBundle("${c4p_resellerBundle}");
    _jspx_th_fmt_message_4.setKey("msg.helpForGettingAlicenseCode");
    int _jspx_eval_fmt_message_4 = _jspx_th_fmt_message_4.doStartTag();
    if (_jspx_th_fmt_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key_bundle.reuse(_jspx_th_fmt_message_4);
    return false;
  }

  private boolean _jspx_meth_fmt_message_5(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_5 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key_bundle.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_5.setPageContext(pageContext);
    _jspx_th_fmt_message_5.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_5.setBundle("${c4p_resellerBundle}");
    _jspx_th_fmt_message_5.setKey("msg.toPurchaseALicenseCode");
    int _jspx_eval_fmt_message_5 = _jspx_th_fmt_message_5.doStartTag();
    if (_jspx_eval_fmt_message_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_fmt_message_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        out = _bc;
        _jspx_th_fmt_message_5.setBodyContent(_bc);
        _jspx_th_fmt_message_5.doInitBody();
      }
      do {
        out.write("\r\n\r\n              ");
        if (_jspx_meth_fmt_param_0(_jspx_th_fmt_message_5, pageContext))
          return true;
        out.write("\r\n\r\n            ");
        int evalDoAfterBody = _jspx_th_fmt_message_5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_fmt_message_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
    }
    if (_jspx_th_fmt_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key_bundle.reuse(_jspx_th_fmt_message_5);
    return false;
  }

  private boolean _jspx_meth_fmt_param_0(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_5, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_0 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_0.setPageContext(pageContext);
    _jspx_th_fmt_param_0.setParent(_jspx_th_fmt_message_5);
    _jspx_th_fmt_param_0.setValue("${infowave_sessionState.reseller.expiryUrl}");
    int _jspx_eval_fmt_param_0 = _jspx_th_fmt_param_0.doStartTag();
    if (_jspx_th_fmt_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_6(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_6 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_6.setPageContext(pageContext);
    _jspx_th_fmt_message_6.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_6.setKey("action.submit");
    int _jspx_eval_fmt_message_6 = _jspx_th_fmt_message_6.doStartTag();
    if (_jspx_th_fmt_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_6);
    return false;
  }

  private boolean _jspx_meth_fmt_message_7(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_7 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_7.setPageContext(pageContext);
    _jspx_th_fmt_message_7.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_7.setKey("action.cancel");
    int _jspx_eval_fmt_message_7 = _jspx_th_fmt_message_7.doStartTag();
    if (_jspx_th_fmt_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_7);
    return false;
  }
}
