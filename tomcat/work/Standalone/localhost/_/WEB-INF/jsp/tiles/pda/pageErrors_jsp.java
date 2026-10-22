package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class pageErrors_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_param_value;

  public pageErrors_jsp() {
    _jspx_tagPool_c_if_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_forEach_var_items = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_param_value = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_fmt_message_key.release();
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
      out.write("\n");
      out.write("\n\n");
      /* ----  c:if ---- */
      org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(pageContext);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest("${not empty requestScope.c4p_errors}");
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n\n");
          out.write("<table width=\"100%\" border=\"0\">\n  ");
          out.write("<tr>\n    ");
          out.write("<td align=\"right\">");
          out.write("<img src=\"x-res/ERROR.GIF\" border=\"0\"/>");
          out.write("</td>\n    ");
          out.write("<td>\n      ");
          out.write("<table width=\"100%\" border=\"0\">\n        ");
          /* ----  c:forEach ---- */
          org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
          _jspx_th_c_forEach_0.setPageContext(pageContext);
          _jspx_th_c_forEach_0.setParent(_jspx_th_c_if_0);
          _jspx_th_c_forEach_0.setItems("${requestScope.c4p_errors}");
          _jspx_th_c_forEach_0.setVar("error");
          int _jspx_push_body_count_c_forEach_0 = 0;
          try {
            int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
            if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n          ");
                out.write("<tr>\n            ");
                out.write("<td>\n              ");
                out.write("<span class=\"content_error_heading\">\n               ");
                if (_jspx_meth_fmt_message_0(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("\n               ");
                out.write("</span>\n             ");
                out.write("</td>\n           ");
                out.write("</tr>\n        ");
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
          out.write("\n      ");
          out.write("</table>\n    ");
          out.write("</td>\n  ");
          out.write("</tr>\n");
          out.write("</table>\n\n\n");
          int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      out.write("\n\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(pageContext);
    _jspx_th_fmt_message_0.setParent(_jspx_th_c_forEach_0);
    _jspx_th_fmt_message_0.setKey("${error.messageKey}");
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
        out.write("\n               ");
        if (_jspx_meth_fmt_param_0(_jspx_th_fmt_message_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n               ");
        if (_jspx_meth_fmt_param_1(_jspx_th_fmt_message_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n               ");
        if (_jspx_meth_fmt_param_2(_jspx_th_fmt_message_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n               ");
        if (_jspx_meth_fmt_param_3(_jspx_th_fmt_message_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n               ");
        if (_jspx_meth_fmt_param_4(_jspx_th_fmt_message_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n               ");
        if (_jspx_meth_fmt_param_5(_jspx_th_fmt_message_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n               ");
        if (_jspx_meth_fmt_param_6(_jspx_th_fmt_message_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n               ");
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
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_fmt_param_0(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_0 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_0.setPageContext(pageContext);
    _jspx_th_fmt_param_0.setParent(_jspx_th_fmt_message_0);
    _jspx_th_fmt_param_0.setValue("${error.firstField}");
    int _jspx_eval_fmt_param_0 = _jspx_th_fmt_param_0.doStartTag();
    if (_jspx_th_fmt_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_0);
    return false;
  }

  private boolean _jspx_meth_fmt_param_1(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_1 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_1.setPageContext(pageContext);
    _jspx_th_fmt_param_1.setParent(_jspx_th_fmt_message_0);
    _jspx_th_fmt_param_1.setValue("${error.secondField}");
    int _jspx_eval_fmt_param_1 = _jspx_th_fmt_param_1.doStartTag();
    if (_jspx_th_fmt_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_1);
    return false;
  }

  private boolean _jspx_meth_fmt_param_2(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_2 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_2.setPageContext(pageContext);
    _jspx_th_fmt_param_2.setParent(_jspx_th_fmt_message_0);
    _jspx_th_fmt_param_2.setValue("${error.thirdField}");
    int _jspx_eval_fmt_param_2 = _jspx_th_fmt_param_2.doStartTag();
    if (_jspx_th_fmt_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_2);
    return false;
  }

  private boolean _jspx_meth_fmt_param_3(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_3 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_3.setPageContext(pageContext);
    _jspx_th_fmt_param_3.setParent(_jspx_th_fmt_message_0);
    _jspx_th_fmt_param_3.setValue("${error.fourthField}");
    int _jspx_eval_fmt_param_3 = _jspx_th_fmt_param_3.doStartTag();
    if (_jspx_th_fmt_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_3);
    return false;
  }

  private boolean _jspx_meth_fmt_param_4(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_4 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_4.setPageContext(pageContext);
    _jspx_th_fmt_param_4.setParent(_jspx_th_fmt_message_0);
    _jspx_th_fmt_param_4.setValue("${error.fifthField}");
    int _jspx_eval_fmt_param_4 = _jspx_th_fmt_param_4.doStartTag();
    if (_jspx_th_fmt_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_4);
    return false;
  }

  private boolean _jspx_meth_fmt_param_5(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_5 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_5.setPageContext(pageContext);
    _jspx_th_fmt_param_5.setParent(_jspx_th_fmt_message_0);
    _jspx_th_fmt_param_5.setValue("${error.sixthField}");
    int _jspx_eval_fmt_param_5 = _jspx_th_fmt_param_5.doStartTag();
    if (_jspx_th_fmt_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_5);
    return false;
  }

  private boolean _jspx_meth_fmt_param_6(javax.servlet.jsp.tagext.Tag _jspx_th_fmt_message_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:param ---- */
    org.apache.taglibs.standard.tag.el.fmt.ParamTag _jspx_th_fmt_param_6 = (org.apache.taglibs.standard.tag.el.fmt.ParamTag) _jspx_tagPool_fmt_param_value.get(org.apache.taglibs.standard.tag.el.fmt.ParamTag.class);
    _jspx_th_fmt_param_6.setPageContext(pageContext);
    _jspx_th_fmt_param_6.setParent(_jspx_th_fmt_message_0);
    _jspx_th_fmt_param_6.setValue("${error.seventhField}");
    int _jspx_eval_fmt_param_6 = _jspx_th_fmt_param_6.doStartTag();
    if (_jspx_th_fmt_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_param_value.reuse(_jspx_th_fmt_param_6);
    return false;
  }
}
