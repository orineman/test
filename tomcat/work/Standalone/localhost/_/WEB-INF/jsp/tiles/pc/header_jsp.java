package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class header_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;

  public header_jsp() {
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_if_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_fmt_message_key = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_choose = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_when_test = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_otherwise = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_out_value.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_fmt_message_key.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_c_otherwise.release();
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

      out.write("\n");
      out.write("\n");
      out.write("\n\n\n\n");
      out.write("<TABLE class=\"header_bar\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" id=\"HeaderBar\">\n  ");
      out.write("<TR>\n    ");
      out.write("<td colspan=\"3\" width=\"100%\">\n    ");
      out.write("<!-- header -->\n      ");
      out.write("<table class=\"header_bar_light\" cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\">\n        ");
      out.write("<tr class=\"header_bar_dark\">\n");
      out.write("<!--          ");
      out.write("<td class=\"header_bar_light\" width=\"160\" >");
      out.write("<img src=\"x-res/logo.gif\" border=\"0\" height=50>");
      out.write("</td> -->\n\t  ");
      out.write("<td class=\"header_bar_light\" width=\"160\" >");
      out.write("<a href=\"http://www.laplink.com\" target=\"_blank\">");
      out.write("<img src=\"x-res/logo.gif\" border=\"0\" height=50>");
      out.write("</a>");
      out.write("</td>\n          ");
      out.write("<td align=\"left\" valign=\"bottom\">");
      out.write("<img src=\"x-res/watermark.gif\" >");
      out.write("</td>\n          ");
      out.write("\n          ");
      out.write("\n          ");
      out.write("\n        ");
      out.write("</tr>\n        ");
      out.write("<tr>\n          ");
      out.write("<td align=\"center\" valign=\"middle\" background='x-res/GRADIENT_1.GIF'>\n");
      out.write("<!--            ");
      out.write("<a href=");
      if (_jspx_meth_c_out_0(pageContext))
        return;
      out.write(" target=\"_blank\">");
      out.write("<img src=\"x-res/BUYNOW.GIF\" width=\"99\" height=\"13\" border=\"0\">");
      out.write("</a> -->\r\n\t\t&nbsp;\r\n\t\t");
      out.write("</td>\n          ");
      out.write("<!-- The page tabs here -->\n          ");
      out.write("<td align=\"left\" valign=\"bottom\" background='x-res/GRADIENT_1.GIF'>\n            ");
      out.write("<table class=\"tabstrip\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n              ");
      out.write("<tr>\n                ");
      out.write("\n\n                ");
      if (_jspx_meth_c_if_0(pageContext))
        return;
      out.write("\n                ");
      if (_jspx_meth_c_if_1(pageContext))
        return;
      out.write("\n                ");
      out.write("\n                ");
      if (_jspx_meth_c_choose_0(pageContext))
        return;
      out.write("\n\n                ");
      out.write("\n                ");
      if (_jspx_meth_c_if_2(pageContext))
        return;
      out.write("\n                ");
      if (_jspx_meth_c_if_3(pageContext))
        return;
      out.write("\n\n                ");
      out.write("\n                ");
      if (_jspx_meth_c_choose_1(pageContext))
        return;
      out.write("\n\n                ");
      out.write("\n                ");
      if (_jspx_meth_c_if_4(pageContext))
        return;
      out.write("\n                ");
      /* ----  c:if ---- */
      org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_5 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
      _jspx_th_c_if_5.setPageContext(pageContext);
      _jspx_th_c_if_5.setParent(null);
      _jspx_th_c_if_5.setTest("${not infowave_sessionState.inHelpSection}");
      int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
      if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n                  ");
          out.write("<td class=\"tabstrip_unsel\">\r\n\t\t\t  ");

			        String locale = request.getLocale().getLanguage();
					String helpUrl = ""	;
				    if( locale != null && locale.equals("de") ) {
						helpUrl = "http://www.laplink.com/de/support/laplink.asp";
				    } else if( locale != null && locale.equals("fr") ) {
						helpUrl = "http://www.laplink.com/fr/support/laplink.asp";
				    } else if( locale != null && locale.equals("it") ) {
						helpUrl = "http://www.laplink.com/support/laplink.asp";
				    } else if( locale != null && locale.equals("es") ) {
						helpUrl = "http://www.laplink.com/es/support/laplink.asp";
				    } else {
						helpUrl = "http://www.laplink.com/support/laplink.asp";
				    }
					 

			  
          out.write("\n                    ");
          out.write("<a class=\"tabstrip\" target=\"_blank\" href=");
          out.print(helpUrl );
          out.write(">");
          if (_jspx_meth_fmt_message_5(_jspx_th_c_if_5, pageContext))
            return;
          out.write("</a>\n                  ");
          out.write("</td>\n                  ");
          out.write("<td>");
          out.write("<img src=\"x-res/tabright_u.gif\" border=\"0\">");
          out.write("</td>\n                ");
          int evalDoAfterBody = _jspx_th_c_if_5.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
      out.write("\n\t\t\t\t");
      out.write("<TD background='x-res/GRADIENT_1.GIF'>&nbsp;");
      out.write("</TD>\r\n\n\n              ");
      out.write("</tr>\n            ");
      out.write("</table>\n            ");
      out.write("\n          ");
      out.write("</td>\n            ");
      if (_jspx_meth_c_if_6(pageContext))
        return;
      out.write("\n        ");
      out.write("</tr>\n      ");
      out.write("</table>\n    ");
      out.write("</td>\n  ");
      out.write("</tr>\n");
      out.write("</table>\n\n\n");
      out.write("\n    ");
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

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(null);
    _jspx_th_c_out_0.setValue("${infowave_sessionState.reseller.expiryUrl}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(pageContext);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest("${infowave_sessionState.inDesktopSection}");
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                  ");
        out.write("<td>");
        out.write("<img src=\"x-res/tableft_s.gif\">");
        out.write("</td>\n                  ");
        out.write("<td class=\"tabstrip_sel\">\n                    ");
        if (_jspx_meth_fmt_message_0(_jspx_th_c_if_0, pageContext))
          return true;
        out.write("\n                  ");
        out.write("</td>\n                ");
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
    _jspx_th_fmt_message_0.setKey("title.myPCs");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(pageContext);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest("${not infowave_sessionState.inDesktopSection}");
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                  ");
        out.write("<td>");
        out.write("<img src=\"x-res/tableft_u.gif\">");
        out.write("</td>\n                  ");
        out.write("<td class=\"tabstrip_unsel\">\n                    ");
        out.write("<a href=\"/protected/showMainPage.do\">");
        if (_jspx_meth_fmt_message_1(_jspx_th_c_if_1, pageContext))
          return true;
        out.write("</a>\n                  ");
        out.write("</td>\n                ");
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

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_1, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(pageContext);
    _jspx_th_fmt_message_1.setParent(_jspx_th_c_if_1);
    _jspx_th_fmt_message_1.setKey("title.myPCs");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_c_choose_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:choose ---- */
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_choose_0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _jspx_tagPool_c_choose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_choose_0.setPageContext(pageContext);
    _jspx_th_c_choose_0.setParent(null);
    int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
    if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                  ");
        out.write("\n                  ");
        if (_jspx_meth_c_when_0(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\n                  ");
        out.write("\n                  ");
        if (_jspx_meth_c_when_1(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\n                  ");
        out.write("\n                  ");
        if (_jspx_meth_c_otherwise_0(_jspx_th_c_choose_0, pageContext))
          return true;
        out.write("\n                ");
        int evalDoAfterBody = _jspx_th_c_choose_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
    return false;
  }

  private boolean _jspx_meth_c_when_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_0 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_0.setPageContext(pageContext);
    _jspx_th_c_when_0.setParent(_jspx_th_c_choose_0);
    _jspx_th_c_when_0.setTest("${infowave_sessionState.inDesktopSection}");
    int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
    if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                    ");
        out.write("<td>");
        out.write("<img src='x-res/tabdiv_su.gif'>");
        out.write("</td>\n                  ");
        int evalDoAfterBody = _jspx_th_c_when_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
    return false;
  }

  private boolean _jspx_meth_c_when_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_1 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_1.setPageContext(pageContext);
    _jspx_th_c_when_1.setParent(_jspx_th_c_choose_0);
    _jspx_th_c_when_1.setTest("${infowave_sessionState.inAccountSection}");
    int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
    if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                    ");
        out.write("<td>");
        out.write("<img src='x-res/tabdiv_us.gif'>");
        out.write("</td>\n                  ");
        int evalDoAfterBody = _jspx_th_c_when_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_0, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:otherwise ---- */
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_otherwise_0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(pageContext);
    _jspx_th_c_otherwise_0.setParent(_jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                   ");
        out.write("<td>");
        out.write("<img src='x-res/tabdiv_uu.gif'>");
        out.write("</td>\n                  ");
        int evalDoAfterBody = _jspx_th_c_otherwise_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_c_if_2(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(pageContext);
    _jspx_th_c_if_2.setParent(null);
    _jspx_th_c_if_2.setTest("${infowave_sessionState.inAccountSection}");
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                  ");
        out.write("<td class=\"tabstrip_sel\">\n                    ");
        if (_jspx_meth_fmt_message_2(_jspx_th_c_if_2, pageContext))
          return true;
        out.write("\n                  ");
        out.write("</td>\n                ");
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

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_2, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(pageContext);
    _jspx_th_fmt_message_2.setParent(_jspx_th_c_if_2);
    _jspx_th_fmt_message_2.setKey("title.myAccount");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_c_if_3(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(pageContext);
    _jspx_th_c_if_3.setParent(null);
    _jspx_th_c_if_3.setTest("${not infowave_sessionState.inAccountSection}");
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                  ");
        out.write("<td class=\"tabstrip_unsel\">\n                    ");
        out.write("<a class=\"tabstrip\" href=\"/protected/showAccountManagementPage.do\">");
        if (_jspx_meth_fmt_message_3(_jspx_th_c_if_3, pageContext))
          return true;
        out.write("</a>\n                  ");
        out.write("</td>\n                ");
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

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_3, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(pageContext);
    _jspx_th_fmt_message_3.setParent(_jspx_th_c_if_3);
    _jspx_th_fmt_message_3.setKey("title.myAccount");
    int _jspx_eval_fmt_message_3 = _jspx_th_fmt_message_3.doStartTag();
    if (_jspx_th_fmt_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_3);
    return false;
  }

  private boolean _jspx_meth_c_choose_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:choose ---- */
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_choose_1 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _jspx_tagPool_c_choose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_choose_1.setPageContext(pageContext);
    _jspx_th_c_choose_1.setParent(null);
    int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
    if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                  ");
        out.write("\n                  ");
        if (_jspx_meth_c_when_2(_jspx_th_c_choose_1, pageContext))
          return true;
        out.write("\n                  ");
        out.write("\n                  ");
        if (_jspx_meth_c_when_3(_jspx_th_c_choose_1, pageContext))
          return true;
        out.write("\n                  ");
        out.write("\n                  ");
        if (_jspx_meth_c_otherwise_1(_jspx_th_c_choose_1, pageContext))
          return true;
        out.write("\n                ");
        int evalDoAfterBody = _jspx_th_c_choose_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
    return false;
  }

  private boolean _jspx_meth_c_when_2(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_1, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_2 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_2.setPageContext(pageContext);
    _jspx_th_c_when_2.setParent(_jspx_th_c_choose_1);
    _jspx_th_c_when_2.setTest("${infowave_sessionState.inAccountSection}");
    int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
    if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                    ");
        out.write("<td>");
        out.write("<img src='x-res/tabdiv_su.gif'>");
        out.write("</td>\n                  ");
        int evalDoAfterBody = _jspx_th_c_when_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
    return false;
  }

  private boolean _jspx_meth_c_when_3(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_1, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:when ---- */
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_when_3 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_when_3.setPageContext(pageContext);
    _jspx_th_c_when_3.setParent(_jspx_th_c_choose_1);
    _jspx_th_c_when_3.setTest("${infowave_sessionState.inHelpSection}");
    int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
    if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                    ");
        out.write("<td>");
        out.write("<img src='x-res/tabdiv_us.gif'>");
        out.write("</td>\n                  ");
        int evalDoAfterBody = _jspx_th_c_when_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_choose_1, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:otherwise ---- */
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_otherwise_1 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_1.setPageContext(pageContext);
    _jspx_th_c_otherwise_1.setParent(_jspx_th_c_choose_1);
    int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
    if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                    ");
        out.write("<td>");
        out.write("<img src='x-res/tabdiv_uu.gif'>");
        out.write("</td>\n                  ");
        int evalDoAfterBody = _jspx_th_c_otherwise_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
    return false;
  }

  private boolean _jspx_meth_c_if_4(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_4 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_4.setPageContext(pageContext);
    _jspx_th_c_if_4.setParent(null);
    _jspx_th_c_if_4.setTest("${infowave_sessionState.inHelpSection}");
    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n                  ");
        out.write("<td class=\"tabstrip_sel\">\n                    ");
        if (_jspx_meth_fmt_message_4(_jspx_th_c_if_4, pageContext))
          return true;
        out.write("\n                  ");
        out.write("</td>\n                  ");
        out.write("<td>");
        out.write("<img src=\"x-res/tabright_s.gif\" border=\"0\">");
        out.write("</td>\n                ");
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

  private boolean _jspx_meth_fmt_message_4(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_4, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_4 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_4.setPageContext(pageContext);
    _jspx_th_fmt_message_4.setParent(_jspx_th_c_if_4);
    _jspx_th_fmt_message_4.setKey("title.help");
    int _jspx_eval_fmt_message_4 = _jspx_th_fmt_message_4.doStartTag();
    if (_jspx_th_fmt_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_4);
    return false;
  }

  private boolean _jspx_meth_fmt_message_5(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_5, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_5 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_5.setPageContext(pageContext);
    _jspx_th_fmt_message_5.setParent(_jspx_th_c_if_5);
    _jspx_th_fmt_message_5.setKey("title.help");
    int _jspx_eval_fmt_message_5 = _jspx_th_fmt_message_5.doStartTag();
    if (_jspx_th_fmt_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_5);
    return false;
  }

  private boolean _jspx_meth_c_if_6(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:if ---- */
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_if_6 = (org.apache.taglibs.standard.tag.el.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_if_6.setPageContext(pageContext);
    _jspx_th_c_if_6.setParent(null);
    _jspx_th_c_if_6.setTest("${infowave_sessionState.userDTO != null}");
    int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
    if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n              ");
        out.write("<td colspan=\"4\" align=\"right\" valign=\"middle\" background='x-res/GRADIENT_1.GIF'>\n                ");
        out.write("<a href=\"/logout.do\" class=\"tabstrip\">");
        if (_jspx_meth_fmt_message_6(_jspx_th_c_if_6, pageContext))
          return true;
        out.write("&nbsp;\n                    ");
        if (_jspx_meth_c_out_1(_jspx_th_c_if_6, pageContext))
          return true;
        out.write("\n                  ");
        out.write("</a>&nbsp;&nbsp;&nbsp;\n              ");
        out.write("</td>\n            ");
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

  private boolean _jspx_meth_fmt_message_6(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_6, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  fmt:message ---- */
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_message_6 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _jspx_tagPool_fmt_message_key.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_message_6.setPageContext(pageContext);
    _jspx_th_fmt_message_6.setParent(_jspx_th_c_if_6);
    _jspx_th_fmt_message_6.setKey("action.logout");
    int _jspx_eval_fmt_message_6 = _jspx_th_fmt_message_6.doStartTag();
    if (_jspx_th_fmt_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_fmt_message_key.reuse(_jspx_th_fmt_message_6);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.Tag _jspx_th_c_if_6, javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(_jspx_th_c_if_6);
    _jspx_th_c_out_1.setValue("${infowave_sessionState.userDTO.username}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }
}
