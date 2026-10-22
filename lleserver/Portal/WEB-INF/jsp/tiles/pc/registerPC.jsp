<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">

<body>

<br>
<table width='90%' align=center>
<tr>

          <tr><td align="left" ><font size=+2><fmt:message key="msg.congratulations1"/></font></td></tr>
	  <tr><td>&nbsp;&nbsp;</td></tr>
          <tr><td align="left" ><font size=+1><fmt:message key="msg.congratulations2"/></font></td></tr>
	  <tr><td>&nbsp;&nbsp;</td></tr>
          <tr><td align="left" ><font size=+1><fmt:message key="msg.congratulations3"/></font></td></tr>

      </tr>
</table>
<br>
<br>
<table width='90%' align=right>
<tr>
<td bgcolor=ffffa0>
      <table>
      <tr>
	<td>
	<button name="Create New Account" onclick="location.href='/showCreateAccountPage1.do?did=<c:out value="${param.did}"/>'" style="font:12pt regular;font-weight=bold;color=black">
	<fmt:message key="action.createaccount"/></button>
	</td>
	<td>&nbsp;&nbsp;</td>
        <td ><font size=-1><strong><fmt:message key="msg.createaccount"/></strong></font></td>
      </tr>
      </table>
</td>
<td>&nbsp;</td>
</tr>
<tr><td>&nbsp;&nbsp;</td></tr>
<tr>
<td bgcolor=ffffa0>
      <table>
      <tr>
	<td>
	<button name="Create New Account" onclick="location.href='/showAttachDesktopToAccountPage.do?did=<c:out value="${param.did}" />'" style="font:12pt regular;font-weight=bold;color=black;width=100;height=50">
	<fmt:message key="action.assignpc"/>
	</button>
	</td>
	<td>&nbsp;&nbsp;</td>
        <td ><font size=-1><strong><fmt:message key="msg.assignthispc"/></strong></font></td>
      </tr>
      </table>
</td>
<td>&nbsp;</td>
</tr>
<tr><td>&nbsp;&nbsp;</td></tr>
<tr><td>&nbsp;&nbsp;</td></tr>
</table>
</body>
