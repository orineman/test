<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.CompanyForm" %>
<%@ page import="com.infowave.c4p.util.ApplicationParameters" %>


<jsp:include page="../header.jsp" />

<table class="shaded_text" width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td height="50" colspan="1" background="x-res/filing_cabinet.jpg">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="1" background="x-res/filing_cabinet_btm.jpg"></td>
    </tr>


<%
int maxWebCount = ApplicationParameters.instance().getMaxWebCount();
CompanyForm form = (CompanyForm)request.getAttribute("companydata");
String jtemp = request.getParameter( "data" );
if( jtemp == null || jtemp.length() == 0 ) jtemp = "%";
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");
String strCount = form.getCompanyCount();
int count = new Integer(strCount).intValue();
if( count == 0 ) {
%>

  <tr><td>&nbsp;</td></tr>
  <tr><td>
        <font size="+1">&nbsp;&nbsp;<fmt:message key="companyresultslocate.header.None">
              <fmt:param><bean:write name="companydata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
        </fmt:message></font>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>

<%
} else if( count > maxWebCount ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>
	<font size="+1"><b><fmt:message  key="error.toomanyresults">
              <fmt:param><%=strCount %></fmt:param>
        </fmt:message></b></font>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>
	&nbsp;&nbsp;<a href = "javascript:history.back()"><fmt:message key="company.link.back" /></a>
  </td>
  </tr>
<%
} else {
%>

  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   <fmt:message  key="company.header.results">
              <fmt:param><bean:write name="companydata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
        </fmt:message></font>
     </td>
  </tr>
  <tr><td>&nbsp;</td></tr>

<tr><td>
<jsp:include page="companySummaryTable.jsp">
  <jsp:param name="linkfmt" value="/RAssist/protected/SearchForCompany.do?type=companyid&nextaction=edit&data="/>  
</jsp:include>
</td></tr>

<%
}
%>

  <tr>
    <td height="29" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
  </tr>
</table>

