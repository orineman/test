<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.teksupport.UserForm" %>

<!--
<html>user results<br>
count=<bean:write name="userdata" property="userCount" scope="request" />=.<br>
     <bean:write name="userdata" property="userList" scope="request" /><br>
-->
<%
UserForm form = (UserForm)request.getAttribute("userdata");
int count = new Integer(form.getUserCount()).intValue();
if( count > 100 ) {
%>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>&nbsp;</td>
  <td>
	<font size="+1"<b>Error! Your query returned too many results (<bean:write name="userdata" property="userCount" scope="request" /> items). Please consider changing your search string.</b></font>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
</table>
<% 
} else {
%>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td>&nbsp;</td>
     <td>
        <font size="+1">Results for user search on <bean:write name="userdata" property="type" scope="request" /> for '<bean:write name="userdata" property="data" scope="request" />'</font>
     </td>
  </tr>
</table>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td>
<table class="shaded_text" border="0" cellspacing="0" cellpadding="0" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td class="shaded_header" >Owner Name</td>
     <td class="shaded_header" >First Name</td>
     <td class="shaded_header" >Last Name</td>
     <td class="shaded_header" >E-Mail</td>
     <td class="shaded_header" >Go To</td>
     <td class="shaded_header" >Password</td>
  </tr>
  <c:forEach items="${UserForm.userList}" var="userstr" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>
     <c:out value="${userstr.username}" />
     </td>
     <td>
     <c:out value="${userstr.firstName}" />
     </td>
     <td>
     <c:out value="${userstr.lastName}" />
     </td>
     <td>
     <c:out value="${userstr.email}" />
     </td>
     <td>
     <a href=/tsAdminSearchDesktop.do?type=accountname&data=<c:out value="${userstr.username}"/> >desktop</a>
     </td>
     <td>
     <a href=tsAdminSetType.do?type=passwordreset&data=<c:out value="${userstr.username}"/> >reset</a>
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
