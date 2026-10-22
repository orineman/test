<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<c:forEach items="${DesktopForm.desktopList}" var="desktopstr" >
<html:form action="tsAdminUpdateExpiryRC" method="post">
<input type="hidden" name="type" value="rclicense" >
<input type="hidden" name="data" value='<c:out value="${desktopstr.rclicenseSerialNumber}"/>' >

<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>&nbsp;</td>
  <td>
  <b>Owner:</b> <c:out value="${desktopstr.owner}"/>
  </td></tr>
  <tr>
  <td>&nbsp;</td>
  <td>
  <b>PC Name:</b> <c:out value="${desktopstr.name}"/>
  </td></tr>
  <tr>
  <td>&nbsp;</td>
  <td>
  <b>License:</b> <c:out value="${desktopstr.rclicenseSerialNumber}"/>
  </td></tr>
  <tr>
  <td>&nbsp;</td>
  <td>
  <b>Expires:</b> <c:out value="${desktopstr.rcexpiryDate}"/>
  </td></tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>&nbsp;</td>
  <td>
  <b>Extend expiration date by:</b>
  </td>
  </tr>
  <tr>
  <td>&nbsp;</td>
  <td>
  <INPUT TYPE="text" NAME="days" SIZE="5" MAXLENGTH="4"> <b>days</b>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>&nbsp;</td>
  <td>
  <INPUT TYPE="submit" VALUE="Update">
  <INPUT TYPE="reset">
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
</table>
<table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
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
</html:form>
</c:forEach>
