<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>



<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">

<br>



<html:form action="createAccount" method="post" onreset="username.focus()">



<%--<FORM action="/createAccount.do" method="post" > --%?

<%-- remember the did if we have one --%>

<c:if test="${not empty param.did}">

  <input type="hidden" name="did" value='<c:out value="${param.did}"/>'/>

</c:if>



    <table cellspacing="0" width="80%" align="center" cellpadding="0">



      <tr>

          <td colspan="2" class="content_title" valign="middle"><img src="x-res/ARROW.GIF">&nbsp;



          <fmt:message key="title.createAccount"/>

          </td>


      </tr>

      <c:if test="${not empty param.did}">
      <tr>

          <td align="center" colspan="2" class="content_title">

              <Font color="red"><b>Note:</b></font> If you already have a Laplink Everywhere 3.0 Account 
          </td>


      </tr>
      <tr>

          <td align="center" colspan="2" class="content_title">

              <a class="content_title"

                href='showAttachDesktopToAccountPage.do?did=<c:out value="${param.did}"/>'>Click here to assign this PC to that Account</a> 

          </td>


      </tr>

      </c:if>

      <tr>

          <td colspan="2" class="shaded_text">&nbsp;</td>

      </tr>

      <tr>

          <td class="shaded_caption" width="30%"><fmt:message key="field.username"/>:</td>

          <td class="shaded_text">

            <html:text style="content_input" property="username"  maxlength="20" />

          </td>

      </tr>

      <tr>

          <td class="shaded_caption"><fmt:message key="field.password"/>:</td>

          <td class="shaded_text">

            <html:password style="content_input" property="password" maxlength="12" />

          </td>

      </tr>

      <tr valign="top">

          <td class="shaded_caption"><fmt:message key="field.passwordAgain"/>:</td>

          <td class="shaded_text">

            <html:password style="content_input" property="passwordConfirmation" maxlength="12" />

          </td>

      </tr>

      <tr valign="top">

          <td class="shaded_caption"><fmt:message key="field.emailAddress"/>:</td>

          <td class="shaded_text">

            <html:text style="content_input" property="email"/>

      </tr>

      <c:if test="${not empty param.did}">

        <tr valign="top">

            <td class="shaded_caption"><fmt:message key="field.pcName"/>:</td>

            <td class="shaded_text">

              <html:text style="content_input" property="pcName" value="My PC"/>

            </td>

        </tr>

      </c:if>

      <tr>

          <td class="shaded_caption" valign="top"><fmt:message key="field.secretQuestion"/>:</td>

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

          <td class="shaded_caption"><fmt:message key="field.secretAnswer"/>:</td>

          <td class="shaded_text">

            <html:text style="content_input" property="securityAnswer" size="48" maxlength="48"/>

          </td>

      </tr>

      <tr>

          <td colspan="2" class="shaded_text">&nbsp;</td>

      </tr>

      <tr>

          <td class="content_note" align="center" colspan="2"><fmt:message key="msg.secretAnswer"/></td>

      </tr>


      <tr>

          <td colspan="2">&nbsp;</td>

      </tr>

      <tr>

          <td align="center" colspan="2">

            <input type="submit" name="submit" value='<fmt:message key="action.submit"/>'/></td>

      </tr>

    </table>

</html:form>

