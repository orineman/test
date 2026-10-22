<%@ page contentType="text/html; charset=UTF-8" %>
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
String jtemp = request.getParameter( "data" ); 
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");
%>
	Results for desktop search on <bean:write name="desktopdata" property="type" scope="request" /> for '<%=name %>'
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
     <td class="shaded_header" >Type</td>
     <td class="shaded_header" >Expiration Date</td>
     <td class="shaded_header" >RC License</td>
     <td class="shaded_header" >RC Type</td>
     <td class="shaded_header" >RC Expiration Date</td>
     <td class="shaded_header" >Go To</td>
     <td class="shaded_header" >Update</td>
     <td class="shaded_header" >Update</td>
  </tr>
  <c:forEach items="${DesktopForm.desktopList}" var="desktopstr" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>
     <c:out value="${desktopstr.htmlencodedOwner}" />
     </td>
     <td>
     <c:out value="${desktopstr.htmlencodedName}" />
     </td>
     <td>
     <c:out value="${desktopstr.licenseSerialNumber}" />
     </td>
     <td align="center">
     <c:out value="${desktopstr.licenseTypeStr}" />
     </td>
     <td>
     <c:out value="${desktopstr.expiryDate}" />
     </td>
     <td>
     <c:out value="${desktopstr.rclicenseSerialNumber}" />
     </td>
     <td align="center">
     <c:out value="${desktopstr.rclicenseTypeStr}" />
     </td>
     <td>
     <c:out value="${desktopstr.rcexpiryDate}" />
     </td>
     <td>
     <a href=/tsAdminSearchUser.do?type=accountname&data=<c:out value="${desktopstr.linkencodedOwner}"/> >account</a>
     </td>
     <td>
     <c:if test="${not empty desktopstr.licenseSerialNumber}">
     <a href=/tsAdminGetExpiryInfo.do?type=license&data=<c:out value="${desktopstr.licenseSerialNumber}"/> >Desktop Expiry Date</a>
     </c:if>
     </td>
     <td>
     <c:if test="${not empty desktopstr.rclicenseSerialNumber}">
     <a href=/tsAdminGetExpiryInfoRC.do?type=rclicense&data=<c:out value="${desktopstr.rclicenseSerialNumber}"/> >RC Expiry Date</a>
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
