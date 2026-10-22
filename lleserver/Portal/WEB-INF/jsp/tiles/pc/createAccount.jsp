<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/tlds/c4.tld" prefix="c4" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>

<%
SessionState state = WebUtil.getSessionState(request); 
Locale locale = state.getLocale();
String language = locale.getLanguage();
ResourceBundle rb = ResourceBundle.getBundle( "messages", locale);
String defaultName=rb.getString( "title.myPC" );
%>

<br>



<html:form action="createAccount" method="post" onreset="username.focus()">



<%--<FORM action="/createAccount.do" method="post" > --%?

<%-- remember the did if we have one --%>

<c:if test="${not empty param.did}">

  <input type="hidden" name="did" value='<c:out value="${param.did}"/>'/>
  <input type="hidden" name="locale" value='<%=language %>'/>

</c:if>



    <table cellspacing="0" width="80%" align="center" cellpadding="0">



      <tr>

          <td colspan="2" class="content_title" valign="middle" background="x-res/GRADIENT_2.GIF">



          <fmt:message key="title.createAccount"/>

          </td>


      </tr>

      <c:if test="${not empty param.did}">
      <tr>

          <td align="center" colspan="2" class="content_title">

              <Font color="red"><b><fmt:message key="msg.note"/></b></font> <fmt:message key="msg.haveaccount"/>
          </td>


      </tr>
      <tr>

          <td align="center" colspan="2" class="content_title">

              <a class="content_title"

                href='showAttachDesktopToAccountPage.do?did=<c:out value="${param.did}"/>'><fmt:message key="msg.clicktoassign"/></a> 

          </td>


      </tr>

      </c:if>

      <tr>

          <td colspan="2" class="shaded_text">&nbsp;</td>

      </tr>

      <tr>

          <td class="shaded_caption" width="30%"><fmt:message key="field.username"/>:</td>

          <td class="shaded_text">

            <html:text style="content_input" property="username"  maxlength="20" size="30"/>

          </td>

      </tr>

      <tr>

          <td class="shaded_caption"><fmt:message key="field.password"/>:</td>

          <td class="shaded_text">

            <html:password style="content_input" property="password" maxlength="12" size="30"/>

          </td>

      </tr>

      <tr valign="top">

          <td class="shaded_caption"><fmt:message key="field.passwordAgain"/>:</td>

          <td class="shaded_text">

            <html:password style="content_input" property="passwordConfirmation" maxlength="12" size="30"/>

          </td>

      </tr>

      <tr valign="top">

          <td class="shaded_caption"><fmt:message key="field.emailAddress"/>:</td>

          <td class="shaded_text">

            <html:text style="content_input" size="30" property="email"/>

      </tr>

      <tr valign="top">

          <td class="shaded_caption"><fmt:message key="field.firstName"/>:</td>

          <td class="shaded_text">

            <html:text style="content_input" size="30" maxlength="50" property="firstName"/>

      </tr>

      <tr valign="top">

          <td class="shaded_caption"><fmt:message key="field.lastName"/>:</td>

          <td class="shaded_text">

            <html:text style="content_input" size="30" maxlength="50" property="lastName"/>

      </tr>
      
      <tr>

          <td class="shaded_caption" valign="top"><fmt:message key="field.countryCode"/></td>

          <td class="shaded_text">

          <select name="countryCode" size="1">

          <c:forEach begin="0" end="238" var="countryQuestionIdx">

                <fmt:message>

                  countryCode.<c:out value="${countryQuestionIdx}"/>

                </fmt:message>

          </c:forEach>



          </td>

      </tr>


      <c:if test="${not empty param.did}">

        <tr valign="top">

            <td class="shaded_caption"><fmt:message key="field.pcName"/>:</td>

            <td class="shaded_text">

              <html:text style="content_input" property="pcName" size="30" value='<%=defaultName %>' />

            </td>

        </tr>

      </c:if>

      <tr>

          <td class="content_note" align="center" colspan="2"><fmt:message key="msg.secretAnswer"/></td>

      </tr>

      <tr>

          <td class="shaded_caption" valign="top"><fmt:message key="field.secretQuestion"/>:</td>

          <td class="shaded_text">



          <select name="securityQuestion" size="1">
		      <option value='0'> 
		      <fmt:message key="msg.secretQuestion"/>
		      <br></option>

            <c:forEach begin="1" end="5" var="questionIdx">

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

          <td class="content_note" align="center" colspan="2"><fmt:message key="msg.enterSerialNo"/></td>

      </tr>
	  <tr>

          	<td class="shaded_caption"><fmt:message key="action.enterLicense"/>:</td>


          <td class="shaded_text">

		<input type="text" name="group1" size="4" maxlength="4" class="content_input"/>
		<input type="text" name="group2" size="4" maxlength="4" class="content_input"/>
		<input type="text" name="group3" size="4" maxlength="4" class="content_input"/>

           <!-- <input type=text style="content_input" name="pcSerialNumber" size="12" maxlength="12"/> -->
			</td>




      </tr>

      <tr>

          	<td class="shaded_caption"><fmt:message key="action.rcenterLicense"/>:</td>

          </td>
          <td class="shaded_text">
		<input type="text" name="grouprc1" size="4" maxlength="4" class="content_input"/>
		<input type="text" name="grouprc2" size="4" maxlength="4" class="content_input"/>
		<input type="text" name="grouprc3" size="4" maxlength="4" class="content_input"/>


         <!--  <td class="shaded_text">

            <input type=text style="content_input" name="rcSerialNumber" size="12" maxlength="12"/> -->
			</td>

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

