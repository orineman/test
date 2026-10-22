<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<br>
<html:form action="showLoginPage" method="post">
	<table cellspacing="0" width="50%" align="center" cellpadding="0">
	    <tr>
	        <td class="content_title" valign="middle" background="x-res/greygradient.gif"><fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></td>
	    </tr>
			<tr>
			   <td align="center" class="shaded_heading" background="x-res/greygradient.gif">
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
