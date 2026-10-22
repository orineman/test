<%@ page contentType="text/html; charset=UTF-8" %>
<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<br>
<html:form action="showLoginPage" method="post">
	<table cellspacing="0" width="50%" align="center" cellpadding="0">
	    <tr>
	        <td class="content_title" valign="middle"><img src="x-res/ARROW.GIF">&nbsp;<fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></td>
	    </tr>
			<tr>
			   <td align="center" class="shaded_heading">
			       <br><fmt:message key="msg.passwordRecoverySuccess"/><br>&nbsp;
			   </td>
			</tr>
	    <tr>
	        <td class="shaded_text" align="center">
	            <input type="submit" name="try" value='<fmt:message key="action.continue"/>'>
	        </td>
	    </tr>
	    <tr>
	        <td class="shaded_text">&nbsp;</td>
	    </tr>
	</table>
</html:form>