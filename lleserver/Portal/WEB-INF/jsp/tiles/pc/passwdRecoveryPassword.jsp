<%@ page contentType="text/html; charset=UTF-8" %>


<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<br>
    <html:form action="resetPassword" method="post">
        <table cellspacing="0" width="50%" align="center" cellpadding="0">
            <tr>
                <td colspan="2" class="content_title" valign="middle" background="x-res/greygradient.gif"><fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></td>
            </tr>
            <tr>
                <td class="shaded_header" colspan="2" background="x-res/GRADIENT_1.GIF">
                <fmt:message key="title.passwordRecovery.enterPassword">
                  <fmt:param value="${sessionScope.c4p_pr_uname}"/>
                </fmt:message>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="shaded_text">&nbsp;</td>
            </tr>
						<tr>
						   <td class="shaded_caption">
						       <fmt:message key="field.newPassword"/>:
						   </td>
						   <td class="shaded_text">
						       <input class="content_input" type="password" name="newPassword" size="12" maxlength="12">
						   </td>
						</tr>
						<tr>
						   <td class="shaded_caption">
						     <fmt:message key="field.newPasswordAgain"/>:
						   </td>
						   <td class="shaded_text">
						       <input class="content_input" type="password" name="newPasswordConfirmation" size="12" maxlength="12">
						   </td>
						</tr>
            <tr>
                <td colspan="2" class="shaded_text">&nbsp;</td>
            </tr>
            <tr>
                <td class="shaded_text" align="center" colspan="2">
                    <input type="submit" name="try" value='<fmt:message key="action.continue"/>'/>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="shaded_text">&nbsp;</td>
            </tr>
        </table>
    </html:form>
