<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

    <br>
    <html:form action="protected/changeSecurityQuestion" method="post">
	    <table width="95%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="2" class="content_title" valign="middle">
            	<img src="x-res/ARROW.GIF">&nbsp;<fmt:message key="title.changeSecurityQuestion"/>
            </td>
        </tr>
        <tr>
		      <td colspan="2" height="1" bgcolor="black"> </td>
        </tr>
        <tr>
            <td colspan="2" class="shaded_text">&nbsp;</td>
        </tr>
        <tr>
            <td width="25%" class="shaded_caption" valign="middle">
              <fmt:message key="field.currentPassword"/>:
            </td>
            <td width="75%" class="shaded_text"><input type="password" name="password" value="" class="content_input"></td>
        </tr>
        <tr>

          <td class="shaded_caption" valign="top">
            <fmt:message key="field.secretQuestion"/>:</td>
          <td class="shaded_text">
          <select name="securityQuestion" size="1">
            <c:forEach begin="1" end="${infowave_sessionState.reseller.nbSecurityQuestions}" var="questionIdx">
              <option value='<c:out value="${questionIdx}"/>'>
                <fmt:message  bundle="${c4p_resellerBundle}">
                  securityQuestion.<c:out value="${questionIdx}"/>
                </fmt:message><br>
              </option>
          </c:forEach>

          </td>

        </tr>
        <tr>
          <td width="25%" class="shaded_caption" valign="middle"><fmt:message key="field.secretAnswer"/>:</td>
          <td width="75%" class="shaded_text">
            <input type="text" style="content_input" name="securityAnswer" size="48" maxlength="48"/>
          </td>
        </tr>
        <tr>
            <td colspan="2" class="shaded_text">&nbsp;</td>
        </tr>
        <tr valign="top">
        		<td width="25%" class="shaded_caption">&nbsp;</td>
		        <td width="75%" class="shaded_text" >
		        	<input type="submit" name="Save" value='<fmt:message key="action.save"/>'>
		        	&nbsp;
		        	<%-- <input type="button" name="Cancel" value="Cancel"> --%>
		        </td>
        </tr>
	    </table>
    </html:form>
