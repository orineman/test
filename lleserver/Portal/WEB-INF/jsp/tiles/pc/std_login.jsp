<%@ page contentType="text/html; charset=UTF-8" %>
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


<FORM action="/loginRouter.do" method="post">

<%-- <html:form action="/loginRouter"> --%>
<input type="hidden" name="j_security_check" value="/j_security_check"/>
<!-- this section optional -->
<table width="95%">
  <tr>
     <td class="content_error_heading" align="center">
         <fmt:message key="msg.notSecure"/><br><a href="https://<%=request.getServerName()%>/showLoginPage.do" class="content_link"><fmt:message key="msg.clickSSL"/></a>
         <BR>
     </td>
  </tr>

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
<!--  <tr><td><br></td></tr> -->
</table>



<%-- ---------------------------------------------------------------------- --%>
<%-- The normal login section --------------------------------------------- --%>
<%-- ---------------------------------------------------------------------- --%>

<TABLE WIDTH="100%" cellpadding="6" cellspacing="0" BORDER="0" class="hometable">
  <TR>
      <td width="120">
<!-- begin left nav menu -->
        <table cellpadding="0" cellspacing="0" border="0" width="100%" class="leftmenutable">
        <tr>
          <td width="7"><img src="x-res/corner-blue-tl.gif" border="0" alt="" /></td>
          <td><img src="x-res/spacer.gif" border="0" alt="" /></td>
          <td width="7"><img src="x-res/corner-blue-tr.gif" border="0" alt="" /></td>
        </tr>
        <tr>
          <td><img src="x-res/spacer.gif" border="0" alt="" /></td>
          <td nowrap>
          <a href="http://<%=request.getServerName()%>"><fmt:message key="msg.home"/></a>
          <hr size="1" noshade />
          <a href="<fmt:message key="msg.link.overview"/>"><fmt:message key="msg.overview"/></a>
          <hr size="1" noshade />
          <a href="<fmt:message key="msg.link.whoneedsit"/>"><fmt:message key="msg.whoneedsit"/></a>
          <hr size="1" noshade />
          <a href="<fmt:message key="msg.link.howworks"/>"><fmt:message key="msg.howworks"/></a>
          <hr size="1" noshade />
          <a href="<fmt:message key="msg.link.features"/>"><fmt:message key="msg.features"/></a>
          <hr size="1" noshade />
     <!--     <a href="<fmt:message key="msg.link.tutorial"/>"><fmt:message key="msg.tutorial"/></a>
          <hr size="1" noshade /> -->
          <a href="<fmt:message key="msg.link.faqs"/>"><fmt:message key="msg.faqs"/></a>
          <hr size="1" noshade />
          <a href="<fmt:message key="msg.link.support"/>"><fmt:message key="msg.support"/></a>
          <hr size="1" noshade />
          <a href="<fmt:message key="msg.link.company"/>"><fmt:message key="msg.company"/></a>
          </td>
          <td><img src="x-res/spacer.gif" border="0" alt="" /></td>
        </tr>
        <tr>
          <td><img src="x-res/corner-blue-bl.gif" border="0" alt="" /></td>
          <td><img src="x-res/spacer.gif" border="0" alt="" /></td>
          <td><img src="x-res/corner-blue-br.gif" border="0" alt="" /></td>
        </tr>
        </table>
<!-- end left nav menu -->
        <!-- Spacer to give the table cell the appropriate width -->
        <img src="x-res/spacer.gif" border="0" alt="" width="120" />
      </td>
      <td>

<!-- begin header table -->
        <table cellpadding="0" cellspacing="0" border="0" width="100%" class="taglinetable">
        <tr>
        	<td width="166" rowspan="2"><img src="x-res/lle4-home-page_03.jpg" border="0" alt="" /></td>
          <td class="tagline" height="95" nowrap><fmt:message key="msg.tagline"/></td>
          <td width="7" height="7" rowspan="2"><img src="x-res/lle4-home-page_10.jpg" border="0" alt="" /></td>
        </tr>
        <tr>
          <td>
			<table class="taglinetable" cellSpacing="0" cellPadding="3" width="100%" border="0">
				<tr>
			 	<td nowrap vAlign="center" align="middle" width='<fmt:message key="size.login.freetrial"/>' background='<fmt:message key="size.login.orangebutton"/>'height="23" border="0"><A href="<fmt:message key="msg.link.trial"/>"><fmt:message key="msg.freetrial"/></A></td>
			 	<td nowrap vAlign="center" align="middle" width='<fmt:message key="size.login.buynow"/>' background='<fmt:message key="size.login.orangebutton"/>' height="23" border="0"><a href="<fmt:message key="msg.link.plans"/>" target="_blank"><fmt:message key="msg.buynow"/></A></td>
			 	<td bgcolor=white>&nbsp;</td>
			</table>
		  </td>
        </tr>
        </table>
<!-- end header table -->

		    <fmt:message key="msg.marketing"/>    
        <table cellpadding="2" cellspacing="0" border="0" class="featuretable">
        <tr>
        	<td width="44"><img src="x-res/lle4-home-page_27.gif" border="0" alt="" /></td>
        	<td width="50%"><fmt:message key="msg.remotecontrol"/></td>
        	<td width="44"><img src="x-res/lle4-home-page_31.gif" border="0" alt="" /></td>
        	<td width="50%"><fmt:message key="msg.firewalls"/></td>
        </tr>
        <tr>
        	<td><img src="x-res/lle4-home-page_32.gif" border="0" alt="" /></td>
        	<td><fmt:message key="msg.files"/></td>
        	<td><img src="x-res/lle4-home-page_20.gif" border="0" alt="" /></td>
        	<td><fmt:message key="msg.email"/></td>
        </tr>
        <tr>
        	<td><img src="x-res/lle4-home-page_37.gif" border="0" alt="" /></td>
        	<td><fmt:message key="msg.search"/></td>
        	<td><img src="x-res/lle4-home-page_23.gif" border="0" alt="" /></td>
        	<td><fmt:message key="msg.pim"/></td>
        </tr>
        </table>
		<fmt:message key="msg.marketing3"/>    

        <p><br /></p>
      </td>
    <td nowrap>
<!--      <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        	<td><img src="x-res/spacer.gif" border="0" alt="" /></td>
        	<td><img src="x-res/LOCK.GIF" border="0" alt="" /></td>
        </tr>
	 </table> -->
<!-- login table start -->
      <table cellpadding="0" cellspacing="0" border="0" width="100%" class="logintable">
        <tr>
        	<td width="5" nowrap><img src="x-res/crn01-tl.gif" border="0" alt="" /></td>
        	<td><img src="x-res/spacer.gif" border="0" alt="" /></td>
        	<td width="5" nowrap><img src="x-res/crn01-tr.gif" border="0" alt="" /></td>
        </tr>

        <tr>
        	<td><img src="x-res/spacer.gif" border="0" alt="" /></td>
        	<td>
		<h2><fmt:message key="message.login" bundle="${c4p_resellerBundle}"/></h2>
		<!--	<h2>Login</h2> -->
		<p><fmt:message key="msg.loginmsg"/></p>
            <table cellpadding="2" cellspacing="0" border="0" width="100%" class="loginform">
              <tr>
                 <td><fmt:message key="field.username"/>:<br>
                 <input name="j_username" size="20"></td>
              </tr>
              <tr>
                 <td><fmt:message key="field.password"/>:<br>
                 <input name="j_password" type="password" size="22" maxlength="12"></td>
              </tr>
              <tr>
                 <td><input type="submit" name="login_secure" class="login" value=<fmt:message key="action.login"/>></td>
              </tr>
   			  <tr>
			    <TD colspan="2">
			    <p><fmt:message key="msg.mode"/>: <fmt:message key="msg.standard"/> |   
                   <a class="content_link" href="https://<%=request.getServerName()%>/showLoginPage.do"><fmt:message key="msg.secure"/>
                  				</a></p>
			       <a class="content_link" href="/showPasswordRecoverypage.do">
			                  <fmt:message key="msg.link.forgotYourPassword"/></a></p>
			    </TD>
			  </TR>

            </table>
        	<td><img src="x-res/spacer.gif" border="0" alt="" /></td>
        </tr>
        <tr>
        	<td><img src="x-res/crn01-bl.gif" border="0" alt="" /></td>
        	<td><img src="x-res/spacer.gif" border="0" alt="" /></td>
        	<td><img src="x-res/crn01-br.gif" border="0" alt="" /></td>
        </tr>
     </table>
	   <p><br /></p>
     </td>
   </tr>
</table>

</form>
<%-- </html:form> --%>


