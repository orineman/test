package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;
import com.infowave.c4p.web.actions.teksupport.DesktopForm;

public class desktopResults_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_bean_write_scope_property_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  public desktopResults_jsp() {
    _jspx_tagPool_bean_write_scope_property_name = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_forEach_var_items = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_if_test = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_bean_write_scope_property_name.release();
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_out_value.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n\r\n");

DesktopForm form = (DesktopForm)request.getAttribute("desktopdata");
boolean wasUpdated = form.getWasUpdated();
boolean wasError = form.getWasError();
int count = new Integer(form.getDesktopCount()).intValue();
if( count > 500 ) {

      out.write("\r\n");
      out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n  ");
      out.write("<tr>");
      out.write("<td>&nbsp;");
      out.write("</td>");
      out.write("</tr>\r\n  ");
      out.write("<tr>\r\n     ");
      out.write("<td>&nbsp;");
      out.write("</td>\r\n  ");
      out.write("<td>\r\n\t");
      out.write("<font size=\"+1\"");
      out.write("<b>Error! Your query returned too many results (");
      if (_jspx_meth_bean_write_0(pageContext))
        return;
      out.write(" items). Please consider changing your search string.");
      out.write("</b>");
      out.write("</font>\r\n  ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n  ");
      out.write("<tr>");
      out.write("<td>&nbsp;");
      out.write("</td>");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
 
} else if( wasError ) {

      out.write("\r\n");
      out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n  ");
      out.write("<tr>");
      out.write("<td>&nbsp;");
      out.write("</td>");
      out.write("</tr>\r\n  ");
      out.write("<tr>\r\n     ");
      out.write("<td>&nbsp;");
      out.write("</td>\r\n  ");
      out.write("<td>\r\n\t");
      out.write("<font size=\"+1\"");
      out.write("<b>Error in update! ");
      if (_jspx_meth_bean_write_1(pageContext))
        return;
      out.write(" \r\n\t");
      out.write("</b>");
      out.write("</font>\r\n        ");
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
            out.write("\r\n        ");
            out.write("<a href=/tsAdminGetExpiryInfo.do?type=license&data=");
            if (_jspx_meth_c_out_0(_jspx_th_c_forEach_0, pageContext, _jspx_push_body_count_c_forEach_0))
              return;
            out.write(" >try again");
            out.write("</a>\r\n        ");
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
      out.write("\r\n  ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n  ");
      out.write("<tr>");
      out.write("<td>&nbsp;");
      out.write("</td>");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
 
} else {

      out.write("\r\n");
      out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n  ");
      out.write("<tr>   ");
      out.write("<td>&nbsp;");
      out.write("</td> ");
      out.write("</tr>\r\n  ");
      out.write("<tr>\r\n     ");
      out.write("<td>&nbsp;");
      out.write("</td>\r\n     ");
      out.write("<td>\r\n        ");
      out.write("<font size=\"+1\">\r\n");

	if( wasUpdated ) {

      out.write("\r\n\tSuccessfully updated!\r\n");
 
} else {
String jtemp = request.getParameter( "data" ); 
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");

      out.write("\r\n\tResults for desktop search on ");
      if (_jspx_meth_bean_write_2(pageContext))
        return;
      out.write(" for '");
      out.print(name );
      out.write("'\r\n");
 
}

      out.write("\r\n\t");
      out.write("</font>\r\n     ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n  ");
      out.write("<tr>   ");
      out.write("<td>&nbsp;");
      out.write("</td> ");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("<table class=\"shaded_text\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n  ");
      out.write("<tr>\r\n  ");
      out.write("<td>\r\n");
      out.write("<table class=\"shaded_text\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\r\n  ");
      out.write("<tr>\r\n     ");
      out.write("<td>&nbsp;");
      out.write("</td>\r\n     ");
      out.write("<td>&nbsp;");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >Account Name");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >Desktop Name");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >License");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >Type");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >Expiration Date");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >RC License");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >RC Type");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >RC Expiration Date");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >Go To");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >Update");
      out.write("</td>\r\n     ");
      out.write("<td class=\"shaded_header\" >Update");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n  ");
      /* ----  c:forEach ---- */
      org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_forEach_1 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
      _jspx_th_c_forEach_1.setPageContext(pageContext);
      _jspx_th_c_forEach_1.setParent(null);
      _jspx_th_c_forEach_1.setItems("${DesktopForm.desktopList}");
      _jspx_th_c_forEach_1.setVar("desktopstr");
      int _jspx_push_body_count_c_forEach_1 = 0;
      try {
        int _jspx_eval_c_forEach_1 = _jspx_th_c_forEach_1.doStartTag();
        if (_jspx_eval_c_forEach_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n  ");
            out.write("<tr>\r\n     ");
            out.write("<td>&nbsp;");
            out.write("</td>\r\n     ");
            out.write("<td>&nbsp;");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            if (_jspx_meth_c_out_1(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            if (_jspx_meth_c_out_2(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            if (_jspx_meth_c_out_3(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td align=\"center\">\r\n     ");
            if (_jspx_meth_c_out_4(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            if (_jspx_meth_c_out_5(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            if (_jspx_meth_c_out_6(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td align=\"center\">\r\n     ");
            if (_jspx_meth_c_out_7(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            if (_jspx_meth_c_out_8(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            out.write("<a href=/tsAdminSearchUser.do?type=accountname&data=");
            if (_jspx_meth_c_out_9(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write(" >account");
            out.write("</a>\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            if (_jspx_meth_c_if_0(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n     ");
            out.write("<td>\r\n     ");
            if (_jspx_meth_c_if_1(_jspx_th_c_forEach_1, pageContext, _jspx_push_body_count_c_forEach_1))
              return;
            out.write("\r\n     ");
            out.write("</td>\r\n  ");
            out.write("</tr>\r\n  ");
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
      out.write("\r\n");
      out.write("</table>\r\n  ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n  ");
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
      out.write("</table>\r\n\r\n");
 
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

  private boolean _jspx_meth_bean_write_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:write ---- */
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_0 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_scope_property_name.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_0.setPageContext(pageContext);
    _jspx_th_bean_write_0.setParent(null);
    _jspx_th_bean_write_0.setName("desktopdata");
    _jspx_th_bean_write_0.setProperty("desktopCount");
    _jspx_th_bean_write_0.setScope("request");
    int _jspx_eval_bean_write_0 = _jspx_th_bean_write_0.doStartTag();
    if (_jspx_th_bean_write_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_write_scope_property_name.reuse(_jspx_th_bean_write_0);
    return false;
  }

  private boolean _jspx_meth_bean_write_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:write ---- */
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_1 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_scope_property_name.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_1.setPageContext(pageContext);
    _jspx_th_bean_write_1.setParent(null);
    _jspx_th_bean_write_1.setName("desktopdata");
    _jspx_th_bean_write_1.setProperty("error");
    _jspx_th_bean_write_1.setScope("request");
    int _jspx_eval_bean_write_1 = _jspx_th_bean_write_1.doStartTag();
    if (_jspx_th_bean_write_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_write_scope_property_name.reuse(_jspx_th_bean_write_1);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(_jspx_th_c_forEach_0);
    _jspx_th_c_out_0.setValue("${desktopstr.licenseSerialNumber}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_bean_write_2(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  bean:write ---- */
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_2 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_scope_property_name.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_2.setPageContext(pageContext);
    _jspx_th_bean_write_2.setParent(null);
    _jspx_th_bean_write_2.setName("desktopdata");
    _jspx_th_bean_write_2.setProperty("type");
    _jspx_th_bean_write_2.setScope("request");
    int _jspx_eval_bean_write_2 = _jspx_th_bean_write_2.doStartTag();
    if (_jspx_th_bean_write_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_bean_write_scope_property_name.reuse(_jspx_th_bean_write_2);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_1.setValue("${desktopstr.htmlencodedOwner}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_c_out_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_2.setPageContext(pageContext);
    _jspx_th_c_out_2.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_2.setValue("${desktopstr.htmlencodedName}");
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_2);
    return false;
  }

  private boolean _jspx_meth_c_out_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_3 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_3.setPageContext(pageContext);
    _jspx_th_c_out_3.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_3.setValue("${desktopstr.licenseSerialNumber}");
    int _jspx_eval_c_out_3 = _jspx_th_c_out_3.doStartTag();
    if (_jspx_th_c_out_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_3);
    return false;
  }

  private boolean _jspx_meth_c_out_4(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_4 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_4.setPageContext(pageContext);
    _jspx_th_c_out_4.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_4.setValue("${desktopstr.licenseTypeStr}");
    int _jspx_eval_c_out_4 = _jspx_th_c_out_4.doStartTag();
    if (_jspx_th_c_out_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_4);
    return false;
  }

  private boolean _jspx_meth_c_out_5(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_5 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_5.setPageContext(pageContext);
    _jspx_th_c_out_5.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_5.setValue("${desktopstr.expiryDate}");
    int _jspx_eval_c_out_5 = _jspx_th_c_out_5.doStartTag();
    if (_jspx_th_c_out_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_5);
    return false;
  }

  private boolean _jspx_meth_c_out_6(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_6 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_6.setPageContext(pageContext);
    _jspx_th_c_out_6.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_6.setValue("${desktopstr.rclicenseSerialNumber}");
    int _jspx_eval_c_out_6 = _jspx_th_c_out_6.doStartTag();
    if (_jspx_th_c_out_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_6);
    return false;
  }

  private boolean _jspx_meth_c_out_7(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_7 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_7.setPageContext(pageContext);
    _jspx_th_c_out_7.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_7.setValue("${desktopstr.rclicenseTypeStr}");
    int _jspx_eval_c_out_7 = _jspx_th_c_out_7.doStartTag();
    if (_jspx_th_c_out_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_7);
    return false;
  }

  private boolean _jspx_meth_c_out_8(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_8 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_8.setPageContext(pageContext);
    _jspx_th_c_out_8.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_8.setValue("${desktopstr.rcexpiryDate}");
    int _jspx_eval_c_out_8 = _jspx_th_c_out_8.doStartTag();
    if (_jspx_th_c_out_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_8);
    return false;
  }

  private boolean _jspx_meth_c_out_9(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_9 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_9.setPageContext(pageContext);
    _jspx_th_c_out_9.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_out_9.setValue("${desktopstr.linkencodedOwner}");
    int _jspx_eval_c_out_9 = _jspx_th_c_out_9.doStartTag();
    if (_jspx_th_c_out_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_9);
    return false;
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(pageContext);
    _jspx_th_c_if_0.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_if_0.setTest("${not empty desktopstr.licenseSerialNumber}");
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n     ");
        out.write("<a href=/tsAdminGetExpiryInfo.do?type=license&data=");
        if (_jspx_meth_c_out_10(_jspx_th_c_if_0, pageContext, _jspx_push_body_count_c_forEach_1))
          return true;
        out.write(" >Desktop Expiry Date");
        out.write("</a>\r\n     ");
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

  private boolean _jspx_meth_c_out_10(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_0, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_10 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_10.setPageContext(pageContext);
    _jspx_th_c_out_10.setParent(_jspx_th_c_if_0);
    _jspx_th_c_out_10.setValue("${desktopstr.licenseSerialNumber}");
    int _jspx_eval_c_out_10 = _jspx_th_c_out_10.doStartTag();
    if (_jspx_th_c_out_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_10);
    return false;
  }

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_forEach_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(pageContext);
    _jspx_th_c_if_1.setParent(_jspx_th_c_forEach_1);
    _jspx_th_c_if_1.setTest("${not empty desktopstr.rclicenseSerialNumber}");
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n     ");
        out.write("<a href=/tsAdminGetExpiryInfoRC.do?type=rclicense&data=");
        if (_jspx_meth_c_out_11(_jspx_th_c_if_1, pageContext, _jspx_push_body_count_c_forEach_1))
          return true;
        out.write(" >RC Expiry Date");
        out.write("</a>\r\n     ");
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

  private boolean _jspx_meth_c_out_11(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext, int _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_11 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_11.setPageContext(pageContext);
    _jspx_th_c_out_11.setParent(_jspx_th_c_if_1);
    _jspx_th_c_out_11.setValue("${desktopstr.rclicenseSerialNumber}");
    int _jspx_eval_c_out_11 = _jspx_th_c_out_11.doStartTag();
    if (_jspx_th_c_out_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_11);
    return false;
  }
}
