package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class changeSecurityQuestion_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_form_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_end_begin;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_bundle;

  public changeSecurityQuestion_jsp() {
    _jspx_tagPool_html_form_method_action = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_forEach_var_end_begin = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_bundle = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_html_form_method_action.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_c_forEach_var_end_begin.release();
    _jspx_tagPool_c_out_value.release();
    _jspx_tagPool_fmt_message_bundle.release();
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

      out.write("<LINK REL=StyleSheet HREF=\"x-res/style.css\" TYPE=\"text/css\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n\n    ");
      out.write("<br>\n    ");
      /* ----  html:form ---- */
      org.apache.struts.taglib.html.FormTag _jspx_th_html_form_0 = (org.apache.struts.taglib.html.FormTag) _jspx_tagPool_html_form_method_action.get(org.apache.struts.taglib.html.FormTag.class);
      _jspx_th_html_form_0.setPageContext(pageContext);
      _jspx_th_html_form_0.setParent(null);
      _jspx_th_html_form_0.setAction("protected/changeSecurityQuestion");
      _jspx_th_html_form_0.setMethod("post");
      int _jspx_eval_html_form_0 = _jspx_th_html_form_0.doStartTag();
      if (_jspx_eval_html_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n\t    ");
          out.write("<table width=\"95%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n        ");
          out.write("<tr>\n            ");
          out.write("<td colspan=\"2\" class=\"content_title\" valign=\"middle\">\n            \t");
          out.write("<img src=\"x-res/ARROW.GIF\">&nbsp;");
          if (_jspx_meth_fmt_message_0(_jspx_th_html_form_0, pageContext))
            return;
          out.write("\n            ");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n\t\t      ");
          out.write("<td colspan=\"2\" height=\"1\" bgcolor=\"black\"> ");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n            ");
          out.write("<td colspan=\"2\" class=\"shaded_text\">&nbsp;");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n            ");
          out.write("<td width=\"25%\" class=\"shaded_caption\" valign=\"middle\">\n              ");
          if (_jspx_meth_fmt_message_1(_jspx_th_html_form_0, pageContext))
            return;
          out.write(":\n            ");
          out.write("</td>\n            ");
          out.write("<td width=\"75%\" class=\"shaded_text\">");
          out.write("<input type=\"password\" name=\"password\" value=\"\" class=\"content_input\">");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n\n          ");
          out.write("<td class=\"shaded_caption\" valign=\"top\">\n            ");
          if (_jspx_meth_fmt_message_2(_jspx_th_html_form_0, pageContext))
            return;
          out.write(":");
          out.write("</td>\n          ");
          out.write("<td class=\"shaded_text\">\n          ");
          out.write("<select name=\"securityQuestion\" size=\"1\">\n            ");
          /* ----  c:forEach ---- */
          org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _jspx_tagPool_c_forEach_var_end_begin.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
          _jspx_th_c_forEach_0.setPageContext(pageContext);
          _jspx_th_c_forEach_0.setParent(_jspx_th_html_form_0);
          _jspx_th_c_forEach_0.setBegin("1");
          _jspx_th_c_forEach_0.setEnd("${infowave_sessionState.reseller.nbSecurityQuestions}");
          _jspx_th_c_forEach_0.setVar("questionIdx");
          int _jspx_push_body_count_c_forEach_0 = 0;
          try {
            int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
            if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n              ");
                out.write("<option value='");
                if (_jspx_meth_c_out_0(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("'>\n                ");
                if (_jspx_meth_fmt_message_3(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("<br>\n              ");
                out.write("</option>\n          ");
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
            _jspx_tagPool_c_forEach_var_end_begin.reuse(_jspx_th_c_forEach_0);
          }
          out.write("\n\n          ");
          out.write("</td>\n\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n          ");
          out.write("<td width=\"25%\" class=\"shaded_caption\" valign=\"middle\">");
          if (_jspx_meth_fmt_message_4(_jspx_th_html_form_0, pageContext))
            return;
          out.write(":");
          out.write("</td>\n          ");
          out.write("<td width=\"75%\" class=\"shaded_text\">\n            ");
          out.write("<input type=\"text\" style=\"content_input\" name=\"securityAnswer\" size=\"48\" maxlength=\"48\"/>\n          ");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr>\n            ");
          out.write("<td colspan=\"2\" class=\"shaded_text\">&nbsp;");
          out.write("</td>\n        ");
          out.write("</tr>\n        ");
          out.write("<tr valign=\"top\">\n        \t\t");
          out.write("<td width=\"25%\" class=\"shaded_caption\">&nbsp;");
          out.write("</td>\n\t\t        ");
          out.write("<td width=\"75%\" class=\"shaded_text\" >\n\t\t        \t");
          out.write("<input type=\"submit\" name=\"Save\" value='");
          if (_jspx_meth_fmt_message_5(_jspx_th_html_form_0, pageContext))
            return;
          out.write("'>\n\t\t        \t&nbsp;\n\t\t        \t");
          out.write("\n\t\t        ");
          out.write("</td>\n        ");
          out.write("</tr>\n\t    ");
          out.write("</table>\n    ");
          int evalDoAfterBody = _jspx_th_html_form_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_html_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_html_form_method_action.reuse(_jspx_th_html_form_0);
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

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_0.setKey("title.changeSecurityQuestion");
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
    _jspx_th_fmt_message_1.setKey("field.currentPassword");
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
    _jspx_th_fmt_message_2.setKey("field.secretQuestion");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(_jspx_th_c_forEach_0);
    _jspx_th_c_out_0.setValue("${questionIdx}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_bundle.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(pageContext);
    _jspx_th_fmt_message_3.setParent(_jspx_th_c_forEach_0);
    _jspx_th_fmt_message_3.setBundle("${c4p_resellerBundle}");
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
        out.write("\n                  securityQuestion.");
        if (_jspx_meth_c_out_1(_jspx_th_fmt_message_3, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n                ");
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
    _jspx_tagPool_fmt_message_bundle.reuse(_jspx_th_fmt_message_3);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_3, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_fmt_message_3);
    _jspx_th_c_out_1.setValue("${questionIdx}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_fmt_message_4(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_4 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_4.setPageContext(pageContext);
    _jspx_th_fmt_message_4.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_4.setKey("field.secretAnswer");
    int _jspx_eval_fmt_message_4 = _jspx_th_fmt_message_4.doStartTag();
    if (_jspx_th_fmt_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_4);
    return false;
  }

  private boolean _jspx_meth_fmt_message_5(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_5 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_5.setPageContext(pageContext);
    _jspx_th_fmt_message_5.setParent(_jspx_th_html_form_0);
    _jspx_th_fmt_message_5.setKey("action.save");
    int _jspx_eval_fmt_message_5 = _jspx_th_fmt_message_5.doStartTag();
    if (_jspx_th_fmt_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_5);
    return false;
  }
}
