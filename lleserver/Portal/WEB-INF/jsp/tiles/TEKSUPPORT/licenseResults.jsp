<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.teksupport.LicenseForm" %>

<%
LicenseForm form = (LicenseForm)request.getAttribute("licensedata");
int count = new Integer(form.getLicenseCount()).intValue();
if( count > 100 ) {
%>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>&nbsp;</td>
  <td>
	<font size="+1"<b>Error! Your query returned too many results (<bean:write name="licensedata" property="userCount" scope="request" /> items). Please consider changing your search string.</b></font>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
</table>
<% 
} else {
String jtemp = request.getParameter( "data" ); 
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");
%>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td>&nbsp;</td>
     <td>
        <font size="+1">Results for user search on <bean:write name="licensedata" property="type" scope="request" /> for '<%=name %>'</font>
     </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
</table>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td>
<table class="shaded_text" border="0" cellspacing="0" cellpadding="0" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td class="shaded_header" >License Code</td>
     <td class="shaded_header" ># licenses</td>
     <td class="shaded_header" ># Used</td>
     <td class="shaded_header" >revoked</td>
     <td class="shaded_header" >RC</td>
     <td class="shaded_header" >type</td>
     <td class="shaded_header" >Expires</td>
     <td class="shaded_header" ># days to expiry</td>
     <td class="shaded_header" >Go To</td>
  </tr>
  <c:forEach items="${TSLicenseForm.licenseList}" var="licstr" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>
     <c:out value="${licstr.code}" />
     </td>
     <td align="center">
     <c:out value="${licstr.corporate}" />
     </td>
     <td align="center">
     <c:out value="${licstr.corporateUsed}" />
     </td>
     <td align="center">
     <c:out value="${licstr.revokedStr}" />
     </td>
     <td align="center">
     <c:out value="${licstr.rc}" />
     </td>
     <td align="center">
     <c:out value="${licstr.type}" />
     </td>
     <td align="center">
     <c:out value="${licstr.expires}" />
     </td>
     <td align="center">
     <c:out value="${licstr.remainingDaysStr}" />
     </td>
     <td>
     <c:choose>
     <c:when test="${licstr.isRcLinked}">
     <a href=/tsAdminSearchDesktop.do?type=rclicense&data=<c:out value="${licstr.code}"/> >desktop</a>
     </c:when>
     <c:when test="${licstr.isLinked}">
     <a href=/tsAdminSearchDesktop.do?type=license&data=<c:out value="${licstr.code}"/> >desktop</a>
     </c:when>
     </c:choose>
     </td>
  </tr>
  </c:forEach>
</table>
  </td>
  </tr>
  <tr>
     <td>
	<TABLE CELLPADDING=3 CELLSPACING=30 />
	<TR>
	<TD ALIGN=middle><a href="tsAdminSetType.do?type=user"><Font size="+1">Search For Account</Font></a></TD>
	<TD ALIGN=middle><a href="tsAdminSetType.do?type=desktop"><Font size="+1">Search For Desktop</Font></a></TD>
	<TD ALIGN=middle><a href="tsAdminSetType.do?type=licenserevoke"><Font size="+1">Revoke License</Font></a></TD>
	<TD ALIGN=middle><a href="tsAdminSetType.do?type=licensecancel"><Font size="+1">Cancel License</Font></a></TD>
	</TR>
	<tr>
	  <TD><a href="tekSupport.do"><font size="-2">Top</font></a></TD>
	</tr>
	</TABLE>
     </td>
  </tr>
</table>

<% 
}
%>
