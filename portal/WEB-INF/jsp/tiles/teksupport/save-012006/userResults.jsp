<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.teksupport.UserForm" %>

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
String jtemp = request.getParameter( "data" ); 
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");
%>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td>&nbsp;</td>
     <td>
        <font size="+1">Results for user search on <bean:write name="userdata" property="type" scope="request" /> for '<%=name %>'</font>
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
     <c:out value="${userstr.htmlencodedUsername}" />
     </td>
     <td>
     <c:out value="${userstr.htmlencodedFirstName}" />
     </td>
     <td>
     <c:out value="${userstr.htmlencodedLastName}" />
     </td>
     <td>
     <c:out value="${userstr.htmlencodedEmail}" />
     </td>
     <td>
     <a href=/tsAdminSearchDesktop.do?type=accountname&data=<c:out value="${userstr.htmlencodedUsername}"/> >desktop</a>
     </td>
     <td>
     <a href=tsAdminSetType.do?type=passwordreset&data=<c:out value="${userstr.htmlencodedUsername}"/> >reset</a>
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
