package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class desktopList_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_url_var_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_param_value_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  public desktopList_jsp() {
    _jspx_tagPool_c_forEach_var_items = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_url_var_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_param_value_name = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_if_test = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_url_var_value.release();
    _jspx_tagPool_c_param_value_name.release();
    _jspx_tagPool_fmt_message.release();
    _jspx_tagPool_c_out_value.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_c_if_test.release();
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

      out.write("\n\n\n\n\n\n\n\n");
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      out.write("<wml>\n\n\n\n\n\n\n\n");
      out.write("<card id=\"PCList\" title=\"PC List\">\n\n\n");
      out.write("<!--\n\t");
      out.write("<do type=\"options\" label=\"Logout\">\n\n\n\n\t\t");
      out.write("<go href=\"logout.do\">\n\n\n\n\t\t");
      out.write("</go>\n\n\n\n\t");
      out.write("</do>\n-->\n\n\n  ");
      /* ----  c:forEach ---- */
      org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
      _jspx_th_c_forEach_0.setPageContext(pageContext);
      _jspx_th_c_forEach_0.setParent(null);
      _jspx_th_c_forEach_0.setItems("${infowave_sessionState.clientCapabilities.capabilities}");
      _jspx_th_c_forEach_0.setVar("mode");
      int _jspx_push_body_count_c_forEach_0 = 0;
      try {
        int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
        if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n\n\n\n    ");
            if (_jspx_meth_c_url_0(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
              return;
            out.write("\n\n\n\n\t\t");
            out.write("<do type=\"options\" label='");
            if (_jspx_meth_fmt_message_0(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
              return;
            out.write(" ");
            if (_jspx_meth_fmt_message_1(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
              return;
            out.write("'>\n\n\n\n\t\t\t");
            out.write("<go href='");
            if (_jspx_meth_c_out_1(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
              return;
            out.write("'>\n\n\n\n\t\t\t");
            out.write("</go>\n\n\n\n\t\t");
            out.write("</do>\n\n\n\n  ");
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
      out.write("\n\n\n\n\n\n\n\n\t");
      out.write("<do type=\"refresh\" label=\"Cancel\">\n\n\n\n\t\t");
      out.write("<refresh/>\n\n\n\n\t");
      out.write("</do>\n\n\n\n\t");
      out.write("<p>\n\n\n\n  ");
      /* ----  c:forEach ---- */
      org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_forEach_1 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
      _jspx_th_c_forEach_1.setPageContext(pageContext);
      _jspx_th_c_forEach_1.setParent(null);
      _jspx_th_c_forEach_1.setItems("${infowave_sessionState.desktops}");
      _jspx_th_c_forEach_1.setVar("desktop");
      int _jspx_push_body_count_c_forEach_1 = 0;
      try {
        int _jspx_eval_c_forEach_1 = _jspx_th_c_forEach_1.doStartTag();
        if (_jspx_eval_c_forEach_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n\n\n\n    ");
            if (_jspx_meth_c_if_0(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\n\n\n\n  ");
            int evalDoAfterBody = _jspx_th_c_forEach_1.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_forEach_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_forEach_1-- > 0)
          out = pageContext.popBody();
        _jspx_th_c_forEach_1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_forEach_1.doFinally();
        _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_1);
      }
      out.write("\n\n\n\n  ");
      out.write("</p>\n\n\n\n");
      out.write("</card>\n\n\n\n");
      out.write("</wml>\n\n\n\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_c_url_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:url ---- */
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_url_0 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _jspx_tagPool_c_url_var_value.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_url_0.setPageContext(pageContext);
    _jspx_th_c_url_0.setParent(_jspx_th_c_forEach_0);
    _jspx_th_c_url_0.setValue("/changeDisplayMode.do");
    _jspx_th_c_url_0.setVar("theUrl");
    int _jspx_eval_c_url_0 = _jspx_th_c_url_0.doStartTag();
    if (_jspx_eval_c_url_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_url_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        _jspx_push_body_count_c_forEach_0++;
        out = _bc;
        _jspx_th_c_url_0.setBodyContent(_bc);
        _jspx_th_c_url_0.doInitBody();
      }
      do {
        out.write("\n\n\n\n      ");
        if (_jspx_meth_c_param_0(_jspx_th_c_url_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n\n\n    ");
        int evalDoAfterBody = _jspx_th_c_url_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_url_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
        _jspx_push_body_count_c_forEach_0--;
    }
    if (_jspx_th_c_url_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_url_var_value.reuse(_jspx_th_c_url_0);
    return false;
  }

  private boolean _jspx_meth_c_param_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_url_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:param ---- */
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_param_0 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _jspx_tagPool_c_param_value_name.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_param_0.setPageContext(pageContext);
    _jspx_th_c_param_0.setParent(_jspx_th_c_url_0);
    _jspx_th_c_param_0.setName("mode");
    _jspx_th_c_param_0.setValue("${mode.code}");
    int _jspx_eval_c_param_0 = _jspx_th_c_param_0.doStartTag();
    if (_jspx_th_c_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_param_value_name.reuse(_jspx_th_c_param_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(_jspx_th_c_forEach_0);
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_eval_fmt_message_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_fmt_message_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        _jspx_push_body_count_c_forEach_0++;
        out = _bc;
        _jspx_th_fmt_message_0.setBodyContent(_bc);
        _jspx_th_fmt_message_0.doInitBody();
      }
      do {
        out.write("displayMode.");
        if (_jspx_meth_c_out_0(_jspx_th_fmt_message_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        int evalDoAfterBody = _jspx_th_fmt_message_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_fmt_message_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
        _jspx_push_body_count_c_forEach_0--;
    }
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(_jspx_th_fmt_message_0);
    _jspx_th_c_out_0.setValue("${mode.name}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(_jspx_th_c_forEach_0);
    _jspx_th_fmt_message_1.setKey("title.view");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_c_forEach_0);
    _jspx_th_c_out_1.setValue("${theUrl}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(pageContext);
    _jspx_th_c_if_0.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_if_0.setTest("${desktop.visible}");
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n\n\n    ");
        if (_jspx_meth_c_if_1(_jspx_th_c_if_0, pageContext, _jspx_push_body_count_c_forEach_1))
          return true;
        out.write("\n\n\n\n    ");
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

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(pageContext);
    _jspx_th_c_if_1.setParent(_jspx_th_c_if_0);
    _jspx_th_c_if_1.setTest("${desktop.connected}");
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n\n\n      ");
        out.write("<!--");
        out.write("<input class=\"device_text\" type=\"submit\" name=\"did.jshnnws\" value=\"PC:\">-->\n\n\n\n      ");
        if (_jspx_meth_c_url_1(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_1))
          return true;
        out.write("\n\n\n\n      ");
        out.write("<a href='");
        if (_jspx_meth_c_out_2(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_1))
          return true;
        out.write("'>\n\n\n\n      ");
        if (_jspx_meth_c_out_3(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_1))
          return true;
        out.write(":&nbsp;");
        if (_jspx_meth_c_out_4(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_1))
          return true;
        out.write("\n\n\n\n      ");
        out.write("</a>\n\n      ");
        out.write("<br/>\n\n    ");
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

  private boolean _jspx_meth_c_url_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:url ---- */
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_url_1 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _jspx_tagPool_c_url_var_value.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_url_1.setPageContext(pageContext);
    _jspx_th_c_url_1.setParent(_jspx_th_c_if_1);
    _jspx_th_c_url_1.setValue("/protected/showDesktopPage.do");
    _jspx_th_c_url_1.setVar("desktopUrl");
    int _jspx_eval_c_url_1 = _jspx_th_c_url_1.doStartTag();
    if (_jspx_eval_c_url_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_url_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        _jspx_push_body_count_c_forEach_1++;
        out = _bc;
        _jspx_th_c_url_1.setBodyContent(_bc);
        _jspx_th_c_url_1.doInitBody();
      }
      do {
        out.write("\n\n\n\n        ");
        if (_jspx_meth_c_param_1(_jspx_th_c_url_1, pageContext, _jspx_push_body_count_c_forEach_1))
          return true;
        out.write("\n\n\n\n      ");
        int evalDoAfterBody = _jspx_th_c_url_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_url_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
        _jspx_push_body_count_c_forEach_1--;
    }
    if (_jspx_th_c_url_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_url_var_value.reuse(_jspx_th_c_url_1);
    return false;
  }

  private boolean _jspx_meth_c_param_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_url_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:param ---- */
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_param_1 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _jspx_tagPool_c_param_value_name.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_param_1.setPageContext(pageContext);
    _jspx_th_c_param_1.setParent(_jspx_th_c_url_1);
    _jspx_th_c_param_1.setName("did");
    _jspx_th_c_param_1.setValue("${desktop.did}");
    int _jspx_eval_c_param_1 = _jspx_th_c_param_1.doStartTag();
    if (_jspx_th_c_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_param_value_name.reuse(_jspx_th_c_param_1);
    return false;
  }

  private boolean _jspx_meth_c_out_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_2.setPageContext(pageContext);
    _jspx_th_c_out_2.setParent(_jspx_th_c_if_1);
    _jspx_th_c_out_2.setValue("${desktopUrl}");
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_2);
    return false;
  }

  private boolean _jspx_meth_c_out_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_3 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_3.setPageContext(pageContext);
    _jspx_th_c_out_3.setParent(_jspx_th_c_if_1);
    _jspx_th_c_out_3.setValue("${desktop.owner}");
    int _jspx_eval_c_out_3 = _jspx_th_c_out_3.doStartTag();
    if (_jspx_th_c_out_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_3);
    return false;
  }

  private boolean _jspx_meth_c_out_4(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_4 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_4.setPageContext(pageContext);
    _jspx_th_c_out_4.setParent(_jspx_th_c_if_1);
    _jspx_th_c_out_4.setValue("${desktop.name}");
    int _jspx_eval_c_out_4 = _jspx_th_c_out_4.doStartTag();
    if (_jspx_th_c_out_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_4);
    return false;
  }
}
