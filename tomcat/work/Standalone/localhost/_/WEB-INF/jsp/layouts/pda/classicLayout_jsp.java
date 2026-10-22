package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class classicLayout_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_bundle;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_insert_attribute;

  public classicLayout_jsp() {
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

      out.write("\n\n\n\n");
      out.write("\n\n\n\n\n\n\n\n");
      out.write("<HTML>\n\n\n\n  ");
      out.write("<HEAD>\n\n    ");
      out.write("<title>");
      if (_jspx_meth_fmt_message_0(pageContext))
        return;
      out.write("</title>\n\n\t");
      out.write("<LINK REL=StyleSheet HREF=\"x-res/pda_style.css\" TYPE=\"text/css\">\n    ");
      out.write("<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\">\n    ");
      out.write("<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">\n    ");
      out.write("<META HTTP-EQUIV=\"max-age\" CONTENT=\"0\">\n    ");
      out.write("<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\n\n    ");
      out.write("<META HTTP-EQUIV=\"HandheldFriendly\" CONTENT=\"True\">\n  ");
      out.write("</HEAD>\n\n\n");
      out.write("<script language=\"javascript\">\n");
      out.write("\nfunction OnLoadBasic(){\n}\n");
      out.write("</script>\n\n\n\n\n");
      out.write("<body  class=\"window\"  onload=\"return OnLoadBasic();\">\n\n\n\n  ");
      out.write("<!-- header start -->\n\n\n\n  ");
      if (_jspx_meth_tiles_insert_0(pageContext))
        return;
      out.write("\n\n\n\n  ");
      out.write("<!-- header stop -->\n\n\n\n  ");
      out.write("<!-- pageErrors start -->\n\n\n\n    ");
      if (_jspx_meth_tiles_insert_1(pageContext))
        return;
      out.write("\n\n\n\n  ");
      out.write("<!-- pageErrors stop -->\n\n\n\n  ");
      out.write("<!-- body start -->\n\n\n\n    ");
      if (_jspx_meth_tiles_insert_2(pageContext))
        return;
      out.write("\n\n\n\n  ");
      out.write("<!-- body stop -->\n\n\n\n  ");
      out.write("<!-- footer start -->\n\n\n\n  ");
      if (_jspx_meth_tiles_insert_3(pageContext))
        return;
      out.write("\n\n\n\n  ");
      out.write("<!-- footer stop -->\n\n\n\n");
      out.write("</body>\n\n\n\n");
      out.write("</html>\n\n\n\n\n\n\n\n");
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
