<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<br>
    <html:form action="getSecurityQuestion" method="post">
        <table cellspacing="0" width="50%" align="center" cellpadding="0">
            <tr>
                <td colspan="2" class="content_title" valign="middle" background="x-res/greygradient.gif"><fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></td>
            </tr>
            <tr>
                <td class="shaded_header" colspan="2" background="x-res/GRADIENT_1.GIF"><fmt:message key="title.passwordRecovery.username"/></td>
            </tr>
            <tr>
                <td colspan="2" class="shaded_text">&nbsp;</td>
            </tr>
            <tr>
                <td class="shaded_caption">
                  <fmt:message key="field.username"/>:</td>
                <td class="shaded_text">
                    <input class="content_input" name="username" size="20" maxlength="20">
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
            <tr>
                <td colspan="2" class="content_note">
                  <fmt:message bundle="${c4p_resellerBundle}" key="msg.passwordRecoveryHint"/>
                    <BR>
                </td>
            </tr>
        </table>
    </html:form>
