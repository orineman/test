<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>



<%
  String action = request.getParameter("nextaction");  
  String subtype = request.getParameter("stype");  
  boolean isTechListAll = false;
  boolean isTechLocate = false;
  boolean isTechAdd = false;
  boolean isTechDelete = false;
  boolean isTechEdit = false;
  if( "listalltechnicians".equals( subtype ) ) {
	isTechListAll = true;
  }
  if( "locatetechnician".equals( subtype ) ) {
	isTechLocate = true;
  }
  if( "edittechnician".equals( subtype ) ) {
	isTechEdit = true;
  }
  if( "addtechnician".equals( subtype ) ) {
	isTechAdd = true;
  }
  if( "deletetechnician".equals( subtype ) ) {
	isTechDelete = true;
  }
%>

<html:form action="/protected/SearchForCompany" method="post" >
      <input type="hidden" name="nextaction" value='<%=action %>' >
      <table class="shaded_text" align="center" width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="130" colspan="2" background="x-res/filing_cabinet.jpg">&nbsp;</td>
      </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
<%
if( isTechLocate ) {
%>
            <font size="+1"><fmt:message key="companygenericsearch.header.techlocate"/></font>
<%
} else if( isTechEdit ) {
%>
            <font size="+1"><fmt:message key="companygenericsearch.header.techlistall"/></font>
<%
} else if( isTechEdit ) {
%>
            <font size="+1"><fmt:message key="companygenericsearch.header.techedit"/></font>
<%
} else if( isTechAdd ) {
%>
            <font size="+1"><fmt:message key="companygenericsearch.header.techadd"/></font>
<%
} else if( isTechDelete ) {
%>
            <font size="+1"><fmt:message key="companygenericsearch.header.techdelete"/></font>
<%
} else {
%>
            <font size="+1"><fmt:message key="companygenericsearch.header"/></font>
<%
}
%>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <b><fmt:message key="companygenericsearch.instruction"/><b>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
          <input type="text" style="content_input" size="64" maxlength="256" name="data" value=""/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
                <fmt:message key="companygenericsearch.hint.wildcards"/><br>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <b><fmt:message key="companygenericsearch.question.info"/><b>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="companyid" CHECKED><fmt:message key="companygenericsearch.label.companyid"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="name"><fmt:message key="companygenericsearch.label.companyname"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="sn"><fmt:message key="companygenericsearch.label.companyxref"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="submit" name="save" class="btnLogin" value='<fmt:message key="companygenericsearch.button.locate"/>' />
	    &nbsp;&nbsp;
            <button type="button" name="cancel" class="btnLogin" onclick="window.location='/RAssist/protected/showMainPage.do'">
                <fmt:message key="companygenericsearch.button.cancel"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="29" colspan="2" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
        </tr>
      </table>
</html:form>

