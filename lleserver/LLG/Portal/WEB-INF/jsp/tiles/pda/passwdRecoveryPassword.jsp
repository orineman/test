<%@ page contentType="text/html; charset=UTF-8" %>
<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">

<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>



<html:form action="resetPassword" method="POST">

	<table cellspacing="0" cellpadding="0" width="100%" id="BodyBar">

  	<tr align="center">

			<td colspan="2" class="body_title">

			   <strong><fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></strong>

			</td>

   	</tr>

  	<tr align="center" class="body_text">

			<td colspan="2">&nbsp;</td>

   	</tr>

		<tr align="center">

      <td colspan="2" class="body_heading">

        <strong><fmt:message key="title.passwordRecovery.enterPassword">

              <fmt:param value="${sessionScope.c4p_pr_uname}"/>

            </fmt:message></strong><p>

			</td>

		</tr>

    <tr> 

      <td align="left" class="body_heading" width="40%"><fmt:message key="field.newPassword"/>:</td>

      <td width="60%" class="body_text"> 

        <input type="password" name="newPassword" size="12" maxlength="12">

      </td>

    </tr>

    <tr> 

      <td align="left" class="body_heading" width="40%"><fmt:message key="field.newPasswordAgain"/>:</td>

      <td width="60%" class="body_text"> 

        <input type="password" name="newPasswordConfirmation" size="12" maxlength="12">

      </td>

    </tr>

		<tr>

		   <td colspan="2" class="body_text" align="center">

		     <input type="submit" name="try" value='<fmt:message key="action.continue"/>'/>

		   </td>

		</tr>

		<tr><td>&nbsp;</td></tr>

	</table>

</html:form>

