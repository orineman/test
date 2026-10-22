package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class siteLayout_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_bundle;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_insert_attribute;

  public siteLayout_jsp() {
    _jspx_tagPool_fmt_message_key_bundle = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_tiles_insert_attribute = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_fmt_message_key_bundle.release();
    _jspx_tagPool_tiles_insert_attribute.release();
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
      out.write("\r\n\r\n\r\n\r\n");
      out.write("<HTML>\r\n\r\n  ");
      out.write("<HEAD>\r\n\r\n    ");
      out.write("<title>");
      if (_jspx_meth_fmt_message_0(pageContext))
        return;
      out.write("</title>\r\n\r\n\t");
      out.write("<LINK REL=StyleSheet HREF=\"x-res/style.css\" TYPE=\"text/css\">\r\n\r\n");
      out.write("<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\">\r\n");
      out.write("<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">\r\n");
      out.write("<META HTTP-EQUIV=\"max-age\" CONTENT=\"0\">\r\n");
      out.write("<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\r\n");

response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

      out.write("\r\n  ");
      out.write("</HEAD>\r\n\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("\r\nfunction OnLoadBasic(){\r\n}\r\n");
      out.write("</script>\r\n\r\n");
      out.write("<body  class=\"basicpage\" onload=\"return OnLoadBasic();\">\r\n\r\n  ");
      out.write("<table class=\"basicpage\" id=\"basicpageID\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\r\n\r\n    ");
      out.write("<tr>\r\n\r\n      ");
      out.write("<td colspan=\"2\">\r\n\r\n      ");
      out.write("<!-- header start -->\r\n\r\n        ");
      if (_jspx_meth_tiles_insert_0(pageContext))
        return;
      out.write("\r\n\r\n      ");
      out.write("<!-- header stop -->\r\n\r\n      ");
      out.write("</td>\r\n\r\n    ");
      out.write("</tr>\r\n\r\n    ");
      out.write("<tr>\r\n\r\n      ");
      out.write("<td class=\"basic_section\" width=\"100%\" valign=\"top\" align=\"left\">\r\n\r\n        ");
      out.write("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\r\n\r\n          ");
      out.write("<tr>\r\n\r\n            ");
      out.write("<td class=\"basic_section\">\r\n\r\n\t\t\t\t\t  ");
      out.write("<!-- because this is used for help pages as well, this section results in white line about help bar -->\r\n\r\n\t\t\t\t    ");
      out.write("<!-- pageErrors start -->\r\n\r\n        \t    ");
      if (_jspx_meth_tiles_insert_1(pageContext))
        return;
      out.write("\r\n\r\n\t\t\t\t    ");
      out.write("<!-- pageErrors stop -->\r\n\r\n            ");
      out.write("</td>\r\n\r\n          ");
      out.write("</tr>\r\n          ");
      out.write("<tr>\r\n\r\n            ");
      out.write("<td>\r\n\r\n\t\t\t      ");
      out.write("<!-- body start -->\r\n\r\n              ");
      if (_jspx_meth_tiles_insert_2(pageContext))
        return;
      out.write("\r\n\r\n            ");
      out.write("<!-- body stop -->\r\n\r\n            ");
      out.write("</td>\r\n\r\n          ");
      out.write("</tr>\r\n\r\n        ");
      out.write("</table>\r\n\r\n      ");
      out.write("</td>\r\n\r\n    ");
      out.write("</tr>\r\n\r\n    ");
      out.write("<tr class=\"footer_bar\" >\r\n\r\n\t    ");
      out.write("<td colspan=\"3\" width=\"100%\">\r\n\r\n\t\t  ");
      out.write("<!-- footer start -->\r\n\r\n\t\t    ");
      if (_jspx_meth_tiles_insert_3(pageContext))
        return;
      out.write("\r\n\r\n\t\t  ");
      out.write("<!-- footer stop -->\r\n\r\n\t    ");
      out.write("</td>\r\n\r\n    ");
      out.write("</tr>\r\n\r\n  ");
      out.write("</table>\r\n\r\n");
      out.write("</body>\r\n\r\n");
      out.write("</html>\r\n\r\n\r\n\r\n");
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
    _jspx_th_fmt_message_0.setKey("title.mainWindowTitle");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key_bundle.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_tiles_insert_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  tiles:insert ---- */
    org.apache.struts.taglib.tiles.InsertTag _jspx_th_tiles_insert_0 = (org.apache.struts.taglib.tiles.InsertTag) _jspx_tagPool_tiles_insert_attribute.get(org.apache.struts.taglib.tiles.InsertTag.class);
    _jspx_th_tiles_insert_0.setPageContext(pageContext);
    _jspx_th_tiles_insert_0.setParent(null);
    _jspx_th_tiles_insert_0.setAttribute("header");
    int _jspx_eval_tiles_insert_0 = _jspx_th_tiles_insert_0.doStartTag();
    if (_jspx_th_tiles_insert_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_tiles_insert_attribute.reuse(_jspx_th_tiles_insert_0);
    return false;
  }

  private boolean _jspx_meth_tiles_insert_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  tiles:insert ---- */
    org.apache.struts.taglib.tiles.InsertTag _jspx_th_tiles_insert_1 = (org.apache.struts.taglib.tiles.InsertTag) _jspx_tagPool_tiles_insert_attribute.get(org.apache.struts.taglib.tiles.InsertTag.class);
    _jspx_th_tiles_insert_1.setPageContext(pageContext);
    _jspx_th_tiles_insert_1.setParent(null);
    _jspx_th_tiles_insert_1.setAttribute("pageErrors");
    int _jspx_eval_tiles_insert_1 = _jspx_th_tiles_insert_1.doStartTag();
    if (_jspx_th_tiles_insert_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_tiles_insert_attribute.reuse(_jspx_th_tiles_insert_1);
    return false;
  }

  private boolean _jspx_meth_tiles_insert_2(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  tiles:insert ---- */
    org.apache.struts.taglib.tiles.InsertTag _jspx_th_tiles_insert_2 = (org.apache.struts.taglib.tiles.InsertTag) _jspx_tagPool_tiles_insert_attribute.get(org.apache.struts.taglib.tiles.InsertTag.class);
    _jspx_th_tiles_insert_2.setPageContext(pageContext);
    _jspx_th_tiles_insert_2.setParent(null);
    _jspx_th_tiles_insert_2.setAttribute("body");
    int _jspx_eval_tiles_insert_2 = _jspx_th_tiles_insert_2.doStartTag();
    if (_jspx_th_tiles_insert_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_tiles_insert_attribute.reuse(_jspx_th_tiles_insert_2);
    return false;
  }

  private boolean _jspx_meth_tiles_insert_3(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  tiles:insert ---- */
    org.apache.struts.taglib.tiles.InsertTag _jspx_th_tiles_insert_3 = (org.apache.struts.taglib.tiles.InsertTag) _jspx_tagPool_tiles_insert_attribute.get(org.apache.struts.taglib.tiles.InsertTag.class);
    _jspx_th_tiles_insert_3.setPageContext(pageContext);
    _jspx_th_tiles_insert_3.setParent(null);
    _jspx_th_tiles_insert_3.setAttribute("footer");
    int _jspx_eval_tiles_insert_3 = _jspx_th_tiles_insert_3.doStartTag();
    if (_jspx_th_tiles_insert_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_tiles_insert_attribute.reuse(_jspx_th_tiles_insert_3);
    return false;
  }
}
