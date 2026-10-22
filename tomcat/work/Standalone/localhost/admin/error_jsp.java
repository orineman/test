package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class error_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  static {
    _jspx_includes = new java.util.Vector(2);
    _jspx_includes.add("/header.jsp");
    _jspx_includes.add("/footer.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_html_locale;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_bean_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_base;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_link_page;

  public error_jsp() {
    _jspx_tagPool_html_html_locale = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_bean_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_html_base = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_html_link_page = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_html_html_locale.release();
    _jspx_tagPool_bean_message_key.release();
    _jspx_tagPool_html_base.release();
    _jspx_tagPool_html_link_page.release();
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
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!-- Standard Struts Entries -->\r\n\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n\r\n");
      if (_jspx_meth_html_html_0(pageContext))
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

  private boolean _jspx_meth_html_html_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:html ---- */
    org.apache.struts.taglib.html.HtmlTag _jspx_th_html_html_0 = (org.apache.struts.taglib.html.HtmlTag) _jspx_tagPool_html_html_locale.get(org.apache.struts.taglib.html.HtmlTag.class);
    _jspx_th_html_html_0.setPageContext(pageContext);
    _jspx_th_html_html_0.setParent(null);
    _jspx_th_html_html_0.setLocale(true);
    int _jspx_eval_html_html_0 = _jspx_th_html_html_0.doStartTag();
    if (_jspx_eval_html_html_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n\r\n");
        out.write("<!-- Standard Content -->\r\n\r\n");
        out.write("<!--\r\n  Copyright (c) 1999-2001 The Apache Software Foundation.  All rights\r\n  reserved.\r\n-->\r\n\r\n");
        out.write("<head>\r\n  ");
        out.write("<title>");
        if (_jspx_meth_bean_message_0(_jspx_th_html_html_0, pageContext))
          return true;
        out.write("</title>\r\n  ");
        if (_jspx_meth_html_base_0(_jspx_th_html_html_0, pageContext))
          return true;
        out.write("\r\n  ");
        out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"tree-control-test.css\">\r\n  ");
        out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"admin.css\">\r\n");
        out.write("</head>\r\n");
        out.write("\r\n\r\n");
        out.write("<!-- Body -->\r\n\r\n");
        out.write("<body bgcolor=\"white\" background=\"images/PaperTexture.gif\">\r\n\r\n");
        out.write("<center>\r\n\r\n");
        out.write("<h2>\r\n  ");
        if (_jspx_meth_bean_message_1(_jspx_th_html_html_0, pageContext))
          return true;
        out.write("\r\n  ");
        out.write("<br>\r\n  ");
        if (_jspx_meth_bean_message_2(_jspx_th_html_html_0, pageContext))
          return true;
        out.write("\r\n  ");
        if (_jspx_meth_html_link_0(_jspx_th_html_html_0, pageContext))
          return true;
        out.write("\r\n");
        out.write("</h2>\r\n\r\n");
        out.write("</center>\r\n\r\n");
        out.write("</body>\r\n\r\n");
        out.write("<!-- Standard Footer -->\r\n\r\n");
        out.write("\r\n\r\n");
        int evalDoAfterBody = _jspx_th_html_html_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_html_html_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_html_html_locale.reuse(_jspx_th_html_html_0);
    return false;
  }

  private boolean _jspx_meth_bean_message_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_html_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:message ---- */
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_message_0 = (org.apache.struts.taglib.bean.MessageTag) _jspx_tagPool_bean_message_key.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_message_0.setPageContext(pageContext);
    _jspx_th_bean_message_0.setParent(_jspx_th_html_html_0);
    _jspx_th_bean_message_0.setKey("application.title");
    int _jspx_eval_bean_message_0 = _jspx_th_bean_message_0.doStartTag();
    if (_jspx_th_bean_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_message_key.reuse(_jspx_th_bean_message_0);
    return false;
  }

  private boolean _jspx_meth_html_base_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_html_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:base ---- */
    org.apache.struts.taglib.html.BaseTag _jspx_th_html_base_0 = (org.apache.struts.taglib.html.BaseTag) _jspx_tagPool_html_base.get(org.apache.struts.taglib.html.BaseTag.class);
    _jspx_th_html_base_0.setPageContext(pageContext);
    _jspx_th_html_base_0.setParent(_jspx_th_html_html_0);
    int _jspx_eval_html_base_0 = _jspx_th_html_base_0.doStartTag();
    if (_jspx_th_html_base_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_html_base.reuse(_jspx_th_html_base_0);
    return false;
  }

  private boolean _jspx_meth_bean_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_html_html_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:message ---- */
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_message_1 = (org.apache.struts.taglib.bean.MessageTag) _jspx_tagPool_bean_message_key.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_message_1.setPageContext(pageContext);
    _jspx_th_bean_message_1.setParent(_jspx_th_html_html_0);
    _jspx_th_bean_message_1.setKey("error.login");
    int _jspx_eval_bean_message_1 = _jspx_th_bean_message_1.doStartTag();
    if (_jspx_th_bean_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_message_key.reuse(_jspx_th_bean_message_1);
    return false;
  }

  private boolean _jspx_meth_bean_message_2(javax.servlet.jsp.tagext.Tag _jspx_th_html_html_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:message ---- */
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_message_2 = (org.apache.struts.taglib.bean.MessageTag) _jspx_tagPool_bean_message_key.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_message_2.setPageContext(pageContext);
    _jspx_th_bean_message_2.setParent(_jspx_th_html_html_0);
    _jspx_th_bean_message_2.setKey("error.tryagain");
    int _jspx_eval_bean_message_2 = _jspx_th_bean_message_2.doStartTag();
    if (_jspx_th_bean_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_message_key.reuse(_jspx_th_bean_message_2);
    return false;
  }

  private boolean _jspx_meth_html_link_0(javax.servlet.jsp.tagext.Tag _jspx_th_html_html_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  html:link ---- */
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_link_0 = (org.apache.struts.taglib.html.LinkTag) _jspx_tagPool_html_link_page.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_link_0.setPageContext(pageContext);
    _jspx_th_html_link_0.setParent(_jspx_th_html_html_0);
    _jspx_th_html_link_0.setPage("/");
    int _jspx_eval_html_link_0 = _jspx_th_html_link_0.doStartTag();
    if (_jspx_eval_html_link_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_link_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        out = _bc;
        _jspx_th_html_link_0.setBodyContent(_bc);
        _jspx_th_html_link_0.doInitBody();
      }
      do {
        out.write("\r\n    ");
        if (_jspx_meth_bean_message_3(_jspx_th_html_link_0, pageContext))
          return true;
        out.write("\r\n  ");
        int evalDoAfterBody = _jspx_th_html_link_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_link_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
    }
    if (_jspx_th_html_link_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_html_link_page.reuse(_jspx_th_html_link_0);
    return false;
  }

  private boolean _jspx_meth_bean_message_3(javax.servlet.jsp.tagext.Tag _jspx_th_html_link_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:message ---- */
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_message_3 = (org.apache.struts.taglib.bean.MessageTag) _jspx_tagPool_bean_message_key.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_message_3.setPageContext(pageContext);
    _jspx_th_bean_message_3.setParent(_jspx_th_html_link_0);
    _jspx_th_bean_message_3.setKey("error.here");
    int _jspx_eval_bean_message_3 = _jspx_th_bean_message_3.doStartTag();
    if (_jspx_th_bean_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_message_key.reuse(_jspx_th_bean_message_3);
    return false;
  }
}
