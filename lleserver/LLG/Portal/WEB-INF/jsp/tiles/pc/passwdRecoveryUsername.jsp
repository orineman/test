<%@ page contentType="text/html; charset=UTF-8" %>
<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<br>
    <html:form action="getSecurityQuestion" method="post">
        <table cellspacing="0" width="50%" align="center" cellpadding="0">
            <tr>
                <td colspan="2" class="content_title" valign="middle"><img src="x-res/ARROW.GIF">&nbsp;<fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></td>
            </tr>
            <tr>
                <td class="shaded_header" colspan="2"><fmt:message key="title.passwordRecovery.username"/></td>
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
        </table>
    </html:form>
