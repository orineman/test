<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>



<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>



<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>







<FORM action="/loginRouter.do" method="post" >



  <%-- <html:form action="/loginRouter"> --%>



  <input type="hidden" name="j_security_check" value="/j_security_check"/>







  <%-- reseller: <cout="${infowave_sessionState.reseller.name}"/> --%>



<!-- this section optional -->



  <table cellspacing="0" align="center" cellpadding="0">



    <c:choose>



      <c:when test="${param.action eq \"protected\"}">



      </c:when>



      <c:when test="${param.action eq \"logout\"}">

			  <tr>

			  <td align="center" class="body_text">

        <fmt:message key="msg.logoutMessage"/><br>&nbsp;

				</td>

				</tr>

      </c:when>



      <c:when test="${param.action eq \"error\"}">

			  <tr>

			  <td align="center" class="body_text">

        <img src="x-res/ALERT.GIF" border="0"/>

        <span class="body_warning">

          <fmt:message key="msg.invalidUsernameOrPassword"/><br>&nbsp;

        </span>

				</td>

				</tr>

      </c:when>



      <c:when test="${param.action eq \"expiry\"}">

      </c:when>



    </c:choose>

				<tr>

				<td>

				<table align="center" cellspacing="0" cellpadding="0">


        <tr>



            <td colspan="2" class="body_heading" valign="middle"><img src="x-res/ARROW.GIF">&nbsp;<fmt:message key="message.pda_login" bundle="${c4p_resellerBundle}"/></td>



        </tr>
				<tr>

					<td><br></td>

				</tr>

        <tr>
            <td align="left" class="body_heading"><fmt:message key="field.username"/>:</td>
            <td class="body_text"><input class="content_input" name="j_username" size="15"></td>
        </tr>
        <tr>
          <td align="left" class="body_heading"><fmt:message key="field.password"/>:</td>
          <td class="body_text"><input class="content_input" name="j_password" type="password" size="15" maxlength="12"></td>
        </tr>

				<tr>

					<td><br></td>

				</tr>

        <tr class="body_heading">

          <td colspan="2" align="center" class="body_text">

            <input type="submit" name="login_secure" value="Login"/>

	        </td>

        </tr>

        </table>

        </td>

        </tr>

				<tr>

					<td><img width="1" height="3" border="0"></td>

				</tr>
        <tr>

            <td class="body_warning" align="center">

			          <fmt:message key="msg.notSecure"/><br><a href="https://<%=request.getServerName()%>/showLoginPage.do" class="body_link">Click here to switch to SSL</a>

                <BR>

            </td>

        </tr>

        <tr>



            <td align="center">



                <a class="body_text" href="/showPasswordRecoverypage.do">



                  <fmt:message key="msg.link.forgotYourPassword"/>



                </a>



            </td>



        </tr>



	    </table>



    </form>

