<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>

<%
SessionState state = WebUtil.getSessionState(request); 
Locale locale = state.getLocale();
ResourceBundle rb = ResourceBundle.getBundle( "messages", locale);
String defaultName=rb.getString( "title.myPC" );
%>

<br>

<center>



<center>

<html:form action="attachDesktopToAccount" method="post">

<input type="hidden" name="did" value='<c:out value="${param.did}"/>'/>

  <table cellspacing="0" width="50%" align="center" cellpadding="0">

    <tr>

        <td colspan="2" class="content_title" valign="middle" background="x-res/GRADIENT_2.GIF">

          <fmt:message key="title.addDesktopToAccount"/></td>

    </tr>
    <tr>

	      <td colspan="2">&nbsp; </td>

    </tr>

    <tr>

	      <td colspan="2" height="1" bgcolor="black"> </td>

    </tr>

    <tr>

        <td class="shaded_text" colspan="2">&nbsp;</td>

    </tr>

    <tr>

        <td class="shaded_caption"><fmt:message key="field.username"/>:</td>

        <td class="shaded_text">

          <html:text style="content_input" property="username"  maxlength="20" />

    </tr>

    <tr>

        <td class="shaded_caption"><fmt:message key="field.password"/>:</td>

        <td class="shaded_text">

          <html:password style="content_input" property="password" maxlength="12" />

        </td>

    </tr>

    <tr>

        <td class="shaded_caption"><fmt:message key="field.pcName"/>:</td>

        <td class="shaded_text">

          <html:text style="content_input" property="pcName" value='<%=defaultName %>' />

        </td>

    </tr>
    <tr>

        <td colspan="2" class="shaded_text">&nbsp;</td>

    </tr>

      <tr>

          <td class="content_note" align="center" colspan="2"><fmt:message key="msg.enterSerialNo"/></td>

      </tr>
    <tr>

        <td colspan="2" class="shaded_text">&nbsp;</td>

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


          <td class="shaded_text">

		<input type="text" name="grouprc1" size="4" maxlength="4" class="content_input"/>
		<input type="text" name="grouprc2" size="4" maxlength="4" class="content_input"/>
		<input type="text" name="grouprc3" size="4" maxlength="4" class="content_input"/>
           <!-- <input type=text style="content_input" name="rcSerialNumber" size="12" maxlength="12"/> -->
			</td>

      </tr>
    <tr>

        <td class="shaded_text" align="center" colspan="2"><input type="submit" name="submit" value='<fmt:message key="action.submit"/>'></td>

    </tr>

    <tr>

        <td colspan="2" class="shaded_text">&nbsp;</td>

    </tr>

    <tr>

        <td align="center" colspan="2">

            <a class="content_link"

             href='showCreateAccountPage.do?did=<c:out value="${param.did}"/>' class="font"><fmt:message key="msg.assignpc"/></a>

        </td>

    </tr>

  </table>

</html:form>



<br>

