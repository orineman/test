package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class expiryUpdate_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_form_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;

  public expiryUpdate_jsp() {
    _jspx_tagPool_c_forEach_var_items = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_html_form_method_action = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_html_form_method_action.release();
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
      response.setContentType("text/html; charset=UTF-8");
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
      /* ----  c:forEach ---- */
      org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
      _jspx_th_c_forEach_0.setPageContext(pageContext);
      _jspx_th_c_forEach_0.setParent(null);
      _jspx_th_c_forEach_0.setItems("${DesktopForm.desktopList}");
      _jspx_th_c_forEach_0.setVar("desktopstr");
      int _jspx_push_body_count_c_forEach_0 = 0;
      try {
        int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
        if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            if (_jspx_meth_html_form_0(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
              return;
            out.write("\r\n");
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

  private boolean _jspx_meth_html_form_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:form ---- */
    org.apache.struts.taglib.html.FormTag _jspx_th_html_form_0 = (org.apache.struts.taglib.html.FormTag) _jspx_tagPool_html_form_method_action.get(org.apache.struts.taglib.html.FormTag.class);
    _jspx_th_html_form_0.setPageContext(pageContext);
    _jspx_th_html_form_0.setParent(_jspx_th_c_forEach_0);
    _jspx_th_html_form_0.setAction("tsAdminUpdateExpiry");
    _jspx_th_html_form_0.setMethod("post");
    int _jspx_eval_html_form_0 = _jspx_th_html_form_0.doStartTag();
    if (_jspx_eval_html_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("<input type=\"hidden\" name=\"type\" value=\"license\" >\r\n");
        out.write("<input type=\"hidden\" name=\"data\" value='");
        if (_jspx_meth_c_out_0(_jspx_th_html_form_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("' >\r\n\r\n");
        out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n  ");
        out.write("<tr>");
        out.write("<td>&nbsp;");
        out.write("</td>");
        out.write("</tr>\r\n  ");
        out.write("<tr>\r\n  ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n  ");
        out.write("<td>\r\n  ");
        out.write("<b>Owner:");
        out.write("</b> ");
        if (_jspx_meth_c_out_1(_jspx_th_html_form_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n  ");
        out.write("</td>");
        out.write("</tr>\r\n  ");
        out.write("<tr>\r\n  ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n  ");
        out.write("<td>\r\n  ");
        out.write("<b>PC Name:");
        out.write("</b> ");
        if (_jspx_meth_c_out_2(_jspx_th_html_form_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n  ");
        out.write("</td>");
        out.write("</tr>\r\n  ");
        out.write("<tr>\r\n  ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n  ");
        out.write("<td>\r\n  ");
        out.write("<b>License:");
        out.write("</b> ");
        if (_jspx_meth_c_out_3(_jspx_th_html_form_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n  ");
        out.write("</td>");
        out.write("</tr>\r\n  ");
        out.write("<tr>\r\n  ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n  ");
        out.write("<td>\r\n  ");
        out.write("<b>Expires:");
        out.write("</b> ");
        if (_jspx_meth_c_out_4(_jspx_th_html_form_0, pageContext, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\r\n  ");
        out.write("</td>");
        out.write("</tr>\r\n  ");
        out.write("<tr>");
        out.write("<td>&nbsp;");
        out.write("</td>");
        out.write("</tr>\r\n  ");
        out.write("<tr>\r\n  ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n  ");
        out.write("<td>\r\n  ");
        out.write("<b>Extend expiration date by:");
        out.write("</b>\r\n  ");
        out.write("</td>\r\n  ");
        out.write("</tr>\r\n  ");
        out.write("<tr>\r\n  ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n  ");
        out.write("<td>\r\n  ");
        out.write("<INPUT TYPE=\"text\" NAME=\"days\" SIZE=\"5\" MAXLENGTH=\"4\"> ");
        out.write("<b>days");
        out.write("</b>\r\n  ");
        out.write("</td>\r\n  ");
        out.write("</tr>\r\n  ");
        out.write("<tr>");
        out.write("<td>&nbsp;");
        out.write("</td>");
        out.write("</tr>\r\n  ");
        out.write("<tr>\r\n  ");
        out.write("<td>&nbsp;");
        out.write("</td>\r\n  ");
        out.write("<td>\r\n  ");
        out.write("<INPUT TYPE=\"submit\" VALUE=\"Update\">\r\n  ");
        out.write("<INPUT TYPE=\"reset\">\r\n  ");
        out.write("</td>\r\n  ");
        out.write("</tr>\r\n  ");
        out.write("<tr>");
        out.write("<td>&nbsp;");
        out.write("</td>");
        out.write("</tr>\r\n");
        out.write("</table>\r\n");
        out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n  ");
        out.write("<tr>\r\n     ");
        out.write("<td>\r\n\t");
        out.write("<TABLE CELLPADDING=3 CELLSPACING=30 />\r\n\t");
        out.write("<TR>\r\n\t");
        out.write("<TD ALIGN=middle>");
        out.write("<a href=\"tsAdminSetType.do?type=user\">");
        out.write("<Font size=\"+1\">Search For Account");
        out.write("</Font>");
        out.write("</a>");
        out.write("</TD>\r\n\t");
        out.write("<TD ALIGN=middle>");
        out.write("<a href=\"tsAdminSetType.do?type=desktop\">");
        out.write("<Font size=\"+1\">Search For Desktop");
        out.write("</Font>");
        out.write("</a>");
        out.write("</TD>\r\n\t");
        out.write("<TD ALIGN=middle>");
        out.write("<a href=\"tsAdminSetType.do?type=licenserevoke\">");
        out.write("<Font size=\"+1\">Revoke License");
        out.write("</Font>");
        out.write("</a>");
        out.write("</TD>\r\n\t");
        out.write("<TD ALIGN=middle>");
        out.write("<a href=\"tsAdminSetType.do?type=licensecancel\">");
        out.write("<Font size=\"+1\">Cancel License");
        out.write("</Font>");
        out.write("</a>");
        out.write("</TD>\r\n\t");
        out.write("</TR>\r\n\t");
        out.write("<tr>\r\n\t  ");
        out.write("<TD>");
        out.write("<a href=\"tekSupport.do\">");
        out.write("<font size=\"-2\">Top");
        out.write("</font>");
        out.write("</a>");
        out.write("</TD>\r\n\t");
        out.write("</tr>\r\n\t");
        out.write("</TABLE>\r\n     ");
        out.write("</td>\r\n  ");
        out.write("</tr>\r\n");
        out.write("</table>\r\n");
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

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(_jspx_th_html_form_0);
    _jspx_th_c_out_0.setValue("${desktopstr.licenseSerialNumber}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_html_form_0);
    _jspx_th_c_out_1.setValue("${desktopstr.owner}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_c_out_2(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_2.setPageContext(pageContext);
    _jspx_th_c_out_2.setParent(_jspx_th_html_form_0);
    _jspx_th_c_out_2.setValue("${desktopstr.name}");
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_2);
    return false;
  }

  private boolean _jspx_meth_c_out_3(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_3 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_3.setPageContext(pageContext);
    _jspx_th_c_out_3.setParent(_jspx_th_html_form_0);
    _jspx_th_c_out_3.setValue("${desktopstr.licenseSerialNumber}");
    int _jspx_eval_c_out_3 = _jspx_th_c_out_3.doStartTag();
    if (_jspx_th_c_out_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_3);
    return false;
  }

  private boolean _jspx_meth_c_out_4(javax.servlet.jsp.tagext.Tag _jspx_th_html_form_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_4 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_4.setPageContext(pageContext);
    _jspx_th_c_out_4.setParent(_jspx_th_html_form_0);
    _jspx_th_c_out_4.setValue("${desktopstr.expiryDate}");
    int _jspx_eval_c_out_4 = _jspx_th_c_out_4.doStartTag();
    if (_jspx_th_c_out_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_4);
    return false;
  }
}
