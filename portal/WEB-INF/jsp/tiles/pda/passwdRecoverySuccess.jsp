<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<html:form action="showLoginPage" method="post">
	<table cellspacing="0" width="100%" align="center" cellpadding="0">
	    <tr>
				<td class="body_title" align="center">
				   <strong><fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></strong>
				</td>
	    </tr>
			<tr>
		    <td align="center"  class="body_text">
		       <br><fmt:message key="msg.passwordRecoverySuccess"/><br>&nbsp;
		    </td>
			</tr>
	    <tr>
	      <td  class="body_text" align="center">
	          <input type="submit" name="try" value='<fmt:message key="action.continue"/>'>
	      </td>
	    </tr>
	</table>
</html:form>