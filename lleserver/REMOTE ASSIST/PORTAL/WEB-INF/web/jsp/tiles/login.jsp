<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>


<FORM action="/RAssist/loginRouter.do" method="post" name="loginform"> 

<input type="hidden" name="j_security_check" value="/j_security_check"/>
<table width="100%">
  <tr>
    <td><div align="center">
      <c:choose>
        <c:when test="${param.action eq \"protected\"}">
 
        </c:when>
        <c:when test="${param.action eq \"logout\"}">
          <span class="content_error_heading">
           <br>
           <c:if test="${not empty param.username}">
        	  <fmt:message key="login.logoutNamedUserMessage">
                <fmt:param value="${param.username}"/>
              </fmt:message>
            </c:if>
            <c:if test="${empty param.username}">
        	  <fmt:message key="login.logoutMessage"/>
            </c:if>
        </c:when>
        <c:when test="${param.action eq \"error\"}">
          <span class="content_error_heading">
            <br><img src="x-res/ALERT.GIF" border="0"/>&nbsp;<strong><fmt:message key="login.invalidUsernameOrPassword"/></strong>
          </span>
        </c:when>
        <c:when test="${param.action eq \"expiry\"}">
        </c:when>
      </c:choose>
   </td>
 </tr>
 <tr><td>&nbsp;</td></tr>
</table>



<TABLE WIDTH="100%" cellpadding="6" cellspacing="0" BORDER="0" class="hometable">
  <TR>
  <td><div align="center">
    <table width="254" border="0" bgcolor="#e8e8e8" class="siongtable_l">
          <tr>
            <td colspan="2"><p class="style_l2"><fmt:message key="login.login"/></p>
              <p class="style_l4"><fmt:message key="login.loginmsg"/> </p></td>
            </tr>
          <tr>
            <td width="24%" class="style_l4" nowrap><fmt:message key="login.label.companyid"/>:</td>
            <td width="70%"><input name="j_companyid" size="42"></td>
            </tr>
          <tr>
            <td width="24%" class="style_l4" nowrap><fmt:message key="login.label.username"/>:</td>
            <td width="70%"><input name="j_username" size="42"></td>
            </tr>
          <tr>
            <td width="24%" class="style_l4" nowrap><fmt:message key="login.label.password"/>:</td>
            <td width="70%"><input name="j_password" type="password" size="44" maxlength="12"></td>
            </tr>
          
          <tr>
            <td colspan="2"><div align="right">
              <input type="submit" name="login_secure" class="btnLogin" value='<fmt:message key="login.button.login"/>' >
              </div></td>
            </tr>
          <tr>
            <td colspan="2" class="style_l5"><div align="center"><strong><fmt:message key="login.mode"/></strong>: <fmt:message key="login.secure"/> | <a class="content_link" href="http://<%=request.getServerName()%>/RAssist/showStdLoginPage.do">  <fmt:message key="login.standard"/></a></div></td>
            </tr>
          <tr>
            <td height="30" colspan="2" background="x-res/fade.jpg" bgcolor="#FFFFFF" class="style_l4">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" bgcolor="#FFFFFF" class="style_l4"><a href="/RAssist/SetType.do?type=resetpassword" class="style_l3"><fmt:message key="login.forgotYourPassword"/></a></td>
            </tr>
          </table>
    </div>
    </td>
   </tr>
</table>

</form>

