<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

	<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">

<br>

<center>



<center>

<html:form action="attachDesktopToAccount" method="post">

<input type="hidden" name="did" value='<c:out value="${param.did}"/>'/>

  <table cellspacing="0" width="50%" align="center" cellpadding="0">

    <tr>

        <td colspan="2" class="content_title" valign="middle">

          <img src="x-res/ARROW.GIF">&nbsp; <fmt:message key="title.addDesktopToAccount"/></td>

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

          <html:text style="content_input" property="pcName" value="My PC"/>

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

             href='showCreateAccountPage.do?did=<c:out value="${param.did}"/>' class="font">Assign this PC to a New User</a>

        </td>

    </tr>

  </table>

</html:form>



<br>

