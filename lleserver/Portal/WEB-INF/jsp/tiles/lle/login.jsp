<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<FORM action="https://<%=request.getServerName()%>/loginRouter.do" method="post" >

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



	    <table cellspacing="0" width="50%" align="center" cellpadding="0">



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



                            <input class="content_input" name="j_username" size="20">



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



                </table>



                <br>



				<tr>



					<td>



						<br>



					</td>



				</tr>



        <tr>



            <td align="center">

                <a class="content_link" href="http://<%=request.getServerName()%>/showStdLoginPage.do">



                  <fmt:message key="msg.link.noHttpsSupport"/>



                </a>



            </td>



        </tr>



        <tr>



            <td align="center">



                <a class="content_link" href="/showPasswordRecoverypage.do">



                  <fmt:message key="msg.link.forgotYourPassword"/>



                </a>



            </td>



        </tr>



        <tr>



            <td align="center">



                <a class="content_link" href="/showCreateAccountPage.do">



                  <fmt:message key="msg.link.signupNewAccount"/>



                </a>



            </td>



        </tr>



        <tr>



            <td align="center">



                <a class="content_link" href="/help.do?page=downloads.htm">



                <fmt:message key="msg.link.downloadSoftware"/>



                </a>

								<br>&nbsp;

            </td>



        </tr>



	    </table>



   </form>
<%-- </html:form> --%>


