package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class desktopList_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_url_var_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_param_value_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_param_value;

  public desktopList_jsp() {
    _jspx_tagPool_c_if_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_forEach_var_items = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_url_var_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_param_value_name = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_param_value = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_out_value.release();
    _jspx_tagPool_c_url_var_value.release();
    _jspx_tagPool_c_param_value_name.release();
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

      out.write("\n\n");
      out.write("\n\n\n\n\n\n\n\n\n\n  ");
      out.write("<table class=\"body_bar\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n\n    ");
      if (_jspx_meth_c_if_0(pageContext))
        return;
      out.write("\n\n    ");
      /* ----  c:forEach ---- */
      org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
      _jspx_th_c_forEach_0.setPageContext(pageContext);
      _jspx_th_c_forEach_0.setParent(null);
      _jspx_th_c_forEach_0.setItems("${infowave_sessionState.desktops}");
      _jspx_th_c_forEach_0.setVar("desktop");
      int _jspx_push_body_count_c_forEach_0 = 0;
      try {
        int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
        if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n\n        ");
            if (_jspx_meth_c_if_1(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
              return;
            out.write("\n\n\n\n\n\n\n    ");
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
      out.write("\n\n\n\n\t");
      out.write("</table>\n\n\n\n");
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
    _jspx_th_c_if_0.setTest("${empty infowave_sessionState.desktops}");
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n      ");
        out.write("<span class=\"toolbar_heading\">\n      ");
        if (_jspx_meth_fmt_message_0(_jspx_th_c_if_0, pageContext))
          return true;
        out.write("\n      ");
        out.write("</span>\n    ");
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

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(_jspx_th_c_if_0);
    _jspx_th_fmt_message_0.setKey("msg.noDesktopsAvail");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(pageContext);
    _jspx_th_c_if_1.setParent(_jspx_th_c_forEach_0);
    _jspx_th_c_if_1.setTest("${desktop.visible}");
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\t\t\t");
        out.write("<tr>\n\t\t\t\t");
        out.write("<td background='x-res/GRADIENT_2.GIF'>\n\t\t\t\t");
        out.write("<table id=\"PcUserNameTable\" width=\"100%\">\n\t\t\t\t");
        out.write("<tr>");
        out.write("<td colspan=2>\n\t            \t\t");
        out.write("<span class=\"toolbar_heading\">\n\n\t              \t\t");
        if (_jspx_meth_fmt_message_1(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write(":\n\n\t            \t\t");
        out.write("</span>\n\n\t            \t\t");
        out.write("<span class=\"toolbar_text\">\n\n\t              \t\t\t");
        if (_jspx_meth_c_out_0(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n\t            \t\t");
        out.write("</span>\n            \t\t");
        out.write("</td>\n\t\t\t\t");
        out.write("</tr>\n\n\n\t\t\t\t");
        out.write("<tr>\n\n\t\t\t\t\t");
        out.write("<td>\n\n            ");
        if (_jspx_meth_c_if_2(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n            ");
        if (_jspx_meth_c_if_3(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n\t\t\t\t\t");
        out.write("</td>\n\n\t\t\t\t\t");
        out.write("<td class=\"toolbar_text\" align=\"left\" width=\"100%\">\n\n\n\n            ");
        if (_jspx_meth_c_if_4(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n            ");
        if (_jspx_meth_c_if_5(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n            \t\t");
        out.write("</td>\n            \t");
        out.write("</tr>\n\t\t\t");
        out.write("</table>\n\t\t\t");
        out.write("</td>\n\t\t");
        out.write("</tr>\n\n\n            ");
        if (_jspx_meth_c_if_6(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n        ");
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

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(_jspx_th_c_if_1);
    _jspx_th_fmt_message_1.setKey("field.user");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(_jspx_th_c_if_1);
    _jspx_th_c_out_0.setValue("${desktop.owner}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_c_if_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(pageContext);
    _jspx_th_c_if_2.setParent(_jspx_th_c_if_1);
    _jspx_th_c_if_2.setTest("${desktop.connected}");
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n              ");
        if (_jspx_meth_c_url_0(_jspx_th_c_if_2, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n              ");
        out.write("<img src='x-devicon?did=");
        if (_jspx_meth_c_out_1(_jspx_th_c_if_2, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("' height=\"20\" />\n\n            ");
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

  private boolean _jspx_meth_c_url_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_2, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:url ---- */
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_url_0 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _jspx_tagPool_c_url_var_value.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_url_0.setPageContext(pageContext);
    _jspx_th_c_url_0.setParent(_jspx_th_c_if_2);
    _jspx_th_c_url_0.setValue("/protected/showDesktopPage.do");
    _jspx_th_c_url_0.setVar("desktopUrl");
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
        out.write("\n\n                ");
        if (_jspx_meth_c_param_0(_jspx_th_c_url_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n              ");
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
    _jspx_th_c_param_0.setName("did");
    _jspx_th_c_param_0.setValue("${desktop.did}");
    int _jspx_eval_c_param_0 = _jspx_th_c_param_0.doStartTag();
    if (_jspx_th_c_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_param_value_name.reuse(_jspx_th_c_param_0);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_2, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_c_if_2);
    _jspx_th_c_out_1.setValue("${desktop.did}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_c_if_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(pageContext);
    _jspx_th_c_if_3.setParent(_jspx_th_c_if_1);
    _jspx_th_c_if_3.setTest("${! desktop.connected}");
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n              ");
        out.write("<img src='x-devicon?did=");
        if (_jspx_meth_c_out_2(_jspx_th_c_if_3, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("' height=\"20\" />\n\n            ");
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

  private boolean _jspx_meth_c_out_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_3, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_2.setPageContext(pageContext);
    _jspx_th_c_out_2.setParent(_jspx_th_c_if_3);
    _jspx_th_c_out_2.setValue("${desktop.did}");
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_2);
    return false;
  }

  private boolean _jspx_meth_c_if_4(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_4 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_4.setPageContext(pageContext);
    _jspx_th_c_if_4.setParent(_jspx_th_c_if_1);
    _jspx_th_c_if_4.setTest("${desktop.connected && !desktop.hasExpired}");
    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n                  ");
        if (_jspx_meth_c_out_3(_jspx_th_c_if_4, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n            ");
        int evalDoAfterBody = _jspx_th_c_if_4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
    return false;
  }

  private boolean _jspx_meth_c_out_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_4, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_3 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_3.setPageContext(pageContext);
    _jspx_th_c_out_3.setParent(_jspx_th_c_if_4);
    _jspx_th_c_out_3.setValue("${desktop.name}");
    int _jspx_eval_c_out_3 = _jspx_th_c_out_3.doStartTag();
    if (_jspx_th_c_out_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_3);
    return false;
  }

  private boolean _jspx_meth_c_if_5(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_5 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_5.setPageContext(pageContext);
    _jspx_th_c_if_5.setParent(_jspx_th_c_if_1);
    _jspx_th_c_if_5.setTest("${!desktop.connected || desktop.hasExpired}");
    int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
    if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n              ");
        if (_jspx_meth_c_out_4(_jspx_th_c_if_5, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n            ");
        int evalDoAfterBody = _jspx_th_c_if_5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
    return false;
  }

  private boolean _jspx_meth_c_out_4(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_5, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_4 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_4.setPageContext(pageContext);
    _jspx_th_c_out_4.setParent(_jspx_th_c_if_5);
    _jspx_th_c_out_4.setValue("${desktop.name}");
    int _jspx_eval_c_out_4 = _jspx_th_c_out_4.doStartTag();
    if (_jspx_th_c_out_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_4);
    return false;
  }

  private boolean _jspx_meth_c_if_6(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_6 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_6.setPageContext(pageContext);
    _jspx_th_c_if_6.setParent(_jspx_th_c_if_1);
    _jspx_th_c_if_6.setTest("${desktop.hasExpired || (desktop.daysToExpiry>0 && desktop.daysToExpiry<16)}");
    int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
    if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\t    ");
        out.write("<tr>");
        out.write("<td background=\"x-res/GRADIENT_1.GIF\">\n\t\t\t");
        out.write("<TABLE width=\"100%\" align=\"center\" border=\"0\">\n\t\t\t");
        out.write("<tr>");
        out.write("<td align=\"middle\">\n              ");
        if (_jspx_meth_c_if_7(_jspx_th_c_if_6, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n              ");
        out.write("<span class='device_warning'>\n              ");
        if (_jspx_meth_c_if_8(_jspx_th_c_if_6, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n              ");
        if (_jspx_meth_c_if_9(_jspx_th_c_if_6, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n              ");
        out.write("</span>\n\n              ");
        if (_jspx_meth_c_if_10(_jspx_th_c_if_6, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\t\t\t  ");
        out.write("</td>");
        out.write("</tr>");
        out.write("</table>\n        ");
        out.write("</td>");
        out.write("</tr>\n\n            ");
        int evalDoAfterBody = _jspx_th_c_if_6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
    return false;
  }

  private boolean _jspx_meth_c_if_7(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_6, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_7 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_7.setPageContext(pageContext);
    _jspx_th_c_if_7.setParent(_jspx_th_c_if_6);
    _jspx_th_c_if_7.setTest("${infowave_sessionState.userDTO!=NULL && desktop.owner==infowave_sessionState.userDTO.username}");
    int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
    if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                ");
        out.write("<a class='device_warning' href=");
        if (_jspx_meth_c_out_5(_jspx_th_c_if_7, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write(" target=\"_blank\">\n              ");
        int evalDoAfterBody = _jspx_th_c_if_7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
    return false;
  }

  private boolean _jspx_meth_c_out_5(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_7, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_5 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_5.setPageContext(pageContext);
    _jspx_th_c_out_5.setParent(_jspx_th_c_if_7);
    _jspx_th_c_out_5.setValue("${infowave_sessionState.reseller.expiryUrl}");
    int _jspx_eval_c_out_5 = _jspx_th_c_out_5.doStartTag();
    if (_jspx_th_c_out_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_5);
    return false;
  }

  private boolean _jspx_meth_c_if_8(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_6, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_8 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_8.setPageContext(pageContext);
    _jspx_th_c_if_8.setParent(_jspx_th_c_if_6);
    _jspx_th_c_if_8.setTest("${desktop.hasExpired}");
    int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
    if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n                ");
        if (_jspx_meth_fmt_message_2(_jspx_th_c_if_8, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n              ");
        int evalDoAfterBody = _jspx_th_c_if_8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
    return false;
  }

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_8, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(_jspx_th_c_if_8);
    _jspx_th_fmt_message_2.setKey("msg.desktopHasExpired");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_c_if_9(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_6, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_9 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_9.setPageContext(pageContext);
    _jspx_th_c_if_9.setParent(_jspx_th_c_if_6);
    _jspx_th_c_if_9.setTest("${!desktop.hasExpired && desktop.daysToExpiry>0}");
    int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
    if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n                ");
        if (_jspx_meth_fmt_message_3(_jspx_th_c_if_9, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n              ");
        int evalDoAfterBody = _jspx_th_c_if_9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
    return false;
  }

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_9, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(pageContext);
    _jspx_th_fmt_message_3.setParent(_jspx_th_c_if_9);
    _jspx_th_fmt_message_3.setKey("msg.daysToDesktopExpiryDate");
    int _jspx_eval_fmt_message_3 = _jspx_th_fmt_message_3.doStartTag();
    if (_jspx_eval_fmt_message_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_fmt_message_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        _jspx_push_body_count_c_forEach_0++;
        out = _bc;
        _jspx_th_fmt_message_3.setBodyContent(_bc);
        _jspx_th_fmt_message_3.doInitBody();
      }
      do {
        out.write("\n\n                  ");
        if (_jspx_meth_fmt_param_0(_jspx_th_fmt_message_3, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n\n                ");
        int evalDoAfterBody = _jspx_th_fmt_message_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_fmt_message_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
        _jspx_push_body_count_c_forEach_0--;
    }
    if (_jspx_th_fmt_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_3);
    return false;
  }

  private boolean _jspx_meth_fmt_param_0(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_3, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_0 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_0.setPageContext(pageContext);
    _jspx_th_fmt_param_0.setParent(_jspx_th_fmt_message_3);
    _jspx_th_fmt_param_0.setValue("${desktop.daysToExpiry}");
    int _jspx_eval_fmt_param_0 = _jspx_th_fmt_param_0.doStartTag();
    if (_jspx_th_fmt_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_0);
    return false;
  }

  private boolean _jspx_meth_c_if_10(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_6, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_10 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_10.setPageContext(pageContext);
    _jspx_th_c_if_10.setParent(_jspx_th_c_if_6);
    _jspx_th_c_if_10.setTest("${infowave_sessionState.userDTO!=NULL && desktop.owner==infowave_sessionState.userDTO.username}");
    int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
    if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                ");
        out.write("</a>\n              ");
        int evalDoAfterBody = _jspx_th_c_if_10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
    return false;
  }
}
