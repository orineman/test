<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<script language="JavaScript" type="text/javascript">

<!--
function OnLoadBasic(){
document.forms[0].j_username.focus();
}
-->
</script>


<FORM action="https://<%=request.getServerName()%>/loginRouter.do" method="post" name="loginform" >
<%-- remember the did if we have one --%>

<c:if test="${not empty param.did}">

  <input type="hidden" name="did" value='<c:out value="${param.did}"/>'/>

</c:if>


<%-- <html:form action="/loginRouter"> --%>
<input type="hidden" name="j_security_check" value="/j_security_check"/>
<!-- this section optional -->
<table width="95%">
  <tr>
    <td align="center">
      <c:choose>
        <c:when test="${param.action eq \"protected\"}">
 
        </c:when>
      <c:when test="${param.action eq \"logout\"}">
        <span class="content_error_heading">
           <br>
           <c:if test="${not empty param.username}">
        	  <fmt:message key="msg.logoutNamedUserMessage">
                <fmt:param value="${param.username}"/>
              </fmt:message>
            </c:if>
            <c:if test="${empty param.username}">
        	  <fmt:message key="msg.logoutMessage"/>
            </c:if>
      </c:when>
      <c:when test="${param.action eq \"error\"}">
        <span class="content_error_heading">

          <br><img src="x-res/ALERT.GIF" border="0"/>&nbsp;<fmt:message key="msg.invalidUsernameOrPassword"/>

        </span>
      </c:when>
      <c:when test="${param.action eq \"expiry\"}">
      </c:when>
    </c:choose>
</td>
 </tr>
<tr>
<td>



				    <br>



			    </td>



		    </tr>



	    </table>



<%-- ---------------------------------------------------------------------- --%>
<%-- The normal login section --------------------------------------------- --%>
<%-- ---------------------------------------------------------------------- --%>

		<TABLE WIDTH="80%" BORDER="0" ALIGN="center">
			<TR>
				<TD WIDTH="50%" VALIGN="top">
				<TABLE WIDTH="100%" BORDER="0" ALIGN="center">
					<tr><td>
					<fmt:message key="msg.marketing"/>
					</td></tr>
			      	<c:if test="${not empty param.did}">
			      	<tr>

			          	<td align="center" colspan="2" class="left_body">

			              <a class="left_body" 

			                href='showAttachDesktopToAccountPage.do?did=<c:out value="${param.did}"/>'>Click here to assign this PC to your Account</a> 

			         	 </td>


			     	 </tr>

	     			 </c:if>

			      	<tr>

			          	<td align="center" colspan="2" class="left_body">
			              <a class="left_body" 

			                href='showCreateAccountPage.do?did=<c:out value="${param.did}"/>'>Click here to create a new Laplink Internet Account</a> 
			          	</td>


			      	</tr>

				</TABLE>


				</TD>
				<TD WIDTH="50%">


	    <table cellspacing="0" width="90%" align="center" cellpadding="0">
        <tr>
            <td class="content_title" valign="middle"><img src="x-res/ARROW.GIF">&nbsp;<fmt:message key="message.login" bundle="${c4p_resellerBundle}"/></td>
        </tr>
        <tr>
            <td colspan="2" class="shaded_text">
                <br>
                <table cellspacing="0" cellpadding="0" width="50%" align="center">
                    <tr>
                        <td class="shaded_caption"><fmt:message key="field.username"/>:</td>
                        <td class="shaded_text">
                            <input class="content_input" name="j_username" size="20" >
                        </td>
                    </tr>
                    <tr>
                      <td class="shaded_caption"><fmt:message key="field.password"/>:</td>
                      <td class="shaded_text"><input class="content_input" name="j_password" type="password" size="20" maxlength="12"></td>
                    </tr>
                    <tr>
                        <td class="shaded_text" align="center" colspan="2">
                            <input type="submit" name="login_secure" value="Login"/>
                        </td>
                    </tr>
					<TR align="center">
						<TD colspan="2" class="shaded_text">Mode: <fmt:message key="msg.secure"/> | 
                			<a class="content_link" href="http://<%=request.getServerName()%>/showStdLoginPage.do">
                  				<fmt:message key="msg.standard"/>
                			</a>
						</TD>
					</TR>
					<TR align="center">
						<TD colspan="2" class="shaded_text">
			                <a class="content_link" href="/showPasswordRecoverypage.do">
			                  <fmt:message key="msg.link.forgotYourPassword"/>
			                </a>
						</TD>
					</TR>

                </table>
                <br>
				<tr>
					<td>
						<br>
					</td>
				</tr>
	    </table>
   </form>
<%-- </html:form> --%>


