<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<html:form action="checkSecurityAnswer"  method="post">
	<table cellspacing="0" cellpadding="0" width="100%" id="BodyBar">
  	<tr align="center">
			<td class="body_title">
			   <strong><fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></strong>
			</td>
   	</tr>
  	<tr align="center" class="body_text">
			<td>&nbsp;</td>
   	</tr>
		<tr align="center">
      <td class="body_heading">
        <strong><fmt:message key="title.passwordRecovery.securityQuestion"/></strong>
			</td>
		</tr>
		<tr align="center">
      <td class="body_heading">
        <font class="body_warning" color=red><br><fmt:message  bundle="${c4p_resellerBundle}">
                  securityQuestion.<c:out value="${sessionScope.c4p_pr_question}"/>
                </fmt:message></font>
			</td>
		</tr>
		<tr>
      <td width="60%" class="body_text" align="center"> 
        <input name="securityAnswer" size="20" maxlength="20">
      </td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
		   <td class="body_text" align="center">
		     <input type="submit" name="try" value='<fmt:message key="action.continue"/>'/>
		   </td>
		</tr>
	</table>
</html:form>
