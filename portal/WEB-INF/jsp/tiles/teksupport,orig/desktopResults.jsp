<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.teksupport.DesktopForm" %>

<%
DesktopForm form = (DesktopForm)request.getAttribute("desktopdata");
boolean wasUpdated = form.getWasUpdated();
boolean wasError = form.getWasError();
int count = new Integer(form.getDesktopCount()).intValue();
if( count > 100 ) {
%>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>&nbsp;</td>
  <td>
	<font size="+1"<b>Error! Your query returned too many results (<bean:write name="desktopdata" property="desktopCount" scope="request" /> items). Please consider changing your search string.</b></font>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
</table>
<% 
} else if( wasError ) {
%>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>&nbsp;</td>
  <td>
	<font size="+1"<b>Error in update! <bean:write name="desktopdata" property="error" scope="request" /> 
	</b></font>
        <c:forEach items="${DesktopForm.desktopList}" var="desktopstr" >
        <a href=/tsAdminGetExpiryInfo.do?type=license&data=<c:out value="${desktopstr.licenseSerialNumber}"/> >try again</a>
        </c:forEach>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
</table>
<% 
} else {
%>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>   <td>&nbsp;</td> </tr>
  <tr>
     <td>&nbsp;</td>
     <td>
        <font size="+1">
<%
	if( wasUpdated ) {
%>
	Successfully updated!
<% 
} else {
%>
	Results for desktop search on <bean:write name="desktopdata" property="type" scope="request" /> for '<bean:write name="desktopdata" property="data" scope="request" />'
<% 
}
%>
	</font>
     </td>
  </tr>
  <tr>   <td>&nbsp;</td> </tr>
</table>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td>
<table class="shaded_text" border="0" cellspacing="0" cellpadding="0" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td class="shaded_header" >Account Name</td>
     <td class="shaded_header" >Desktop Name</td>
     <td class="shaded_header" >License</td>
     <td class="shaded_header" >Expiration Date</td>
     <td class="shaded_header" >Go To</td>
     <td class="shaded_header" >Update</td>
  </tr>
  <c:forEach items="${DesktopForm.desktopList}" var="desktopstr" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>
     <c:out value="${desktopstr.owner}" />
     </td>
     <td>
     <c:out value="${desktopstr.name}" />
     </td>
     <td>
     <c:out value="${desktopstr.licenseSerialNumber}" />
     </td>
     <td>
     <c:out value="${desktopstr.expiryDate}" />
     </td>
     <td>
     <a href=/tsAdminSearchUser.do?type=accountname&data=<c:out value="${desktopstr.owner}"/> >account</a>
     </td>
     <td>
     <c:if test="${not empty desktopstr.licenseSerialNumber}">
     <a href=/tsAdminGetExpiryInfo.do?type=license&data=<c:out value="${desktopstr.licenseSerialNumber}"/> >Expiration Date</a>
     </c:if>
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
	</TR>
	</TABLE>
     </td>
  </tr>
</table>

<% 
}
%>
