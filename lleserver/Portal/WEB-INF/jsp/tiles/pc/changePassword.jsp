<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
    <br>
    <html:form action="protected/changePassword" method="post">
	    <table width="95%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="2" class="content_title" valign="middle" background="x-res/greygradient.gif" ><fmt:message key="title.changePassword"/></td>
        </tr>
        <tr>
		      <td colspan="2" height="1" bgcolor="black"> </td>
        </tr>
        <tr>
		      <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2" class="shaded_text">&nbsp;</td>
        </tr>
        <tr>
            <td width="25%" class="shaded_caption">
              <fmt:message key="field.currentPassword"/>:
            </td>
            <td width="75%" class="shaded_text"><input type="password" name="password" maxlength="12" value="" class="content_input"></td>
        </tr>
        <tr>
            <td width="25%" class="shaded_caption">
              <fmt:message key="field.newPassword"/>:
            </td>
            <td width="75%" class="shaded_text"><input type="password" name="newPassword" value="" MAXLENGTH="12" class="content_input"></td>
        </tr>
        <tr>
            <td width="25%" class="shaded_caption">
              <fmt:message key="field.newPasswordAgain"/>:
            </td>
            <td width="75%" class="shaded_text"><input type="password" name="newPasswordConfirmation" value="" MAXLENGTH="12" class="content_input"></td>
        </tr>
        <tr>
            <td colspan="2" class="shaded_text">&nbsp;</td>
        </tr>
        <tr>
        		<td width="25%" class="shaded_caption">&nbsp;</td>
		        <td width="75%" class="shaded_text" >
		        	<input type="submit" name="Save" value='<fmt:message key="action.save"/>'>
		        	&nbsp;
		        	<%-- <input type="button" name="Cancel" value='<fmt:message key="action.cancel"/>' --%>
		        </td>
        </tr>
	    </table>
    </html:form>
