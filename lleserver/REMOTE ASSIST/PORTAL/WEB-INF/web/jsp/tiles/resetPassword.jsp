<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>



<html:form action="showPasswordRecoverypage" method="post" >

<table width="95%">
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td><div align="center">
    <c:if test="${not empty requestScope.c4p_errors}">

      <c:forEach items="${requestScope.c4p_errors}" var="error">
        <tr><td><div align="center">
        <font color="red" ><strong>&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="error.error" />&nbsp;<fmt:message key="${error.messageKey}" /></strong></font>
        </td></tr>
        <tr><td>&nbsp;</td></tr>
      </c:forEach>
    </c:if>

   </td>
 </tr>
 <tr><td>&nbsp;</td></tr>
</table>

<TABLE WIDTH="100%" cellpadding="6" cellspacing="0" BORDER="0" class="hometable">
  <TR>
  <td><div align="center">
    <table width="254" border="0" bgcolor="#e8e8e8" class="siongtable_l">
          <tr>
            <td colspan="2"><p class="style_l2"><fmt:message key="resetpassword.header.reset" /></p>
              <p class="style_l4"><fmt:message key="resetpassword.resetmsg"/> </p></td>
          </tr>
          <tr>
            <td width="24%" class="style_l4" nowrap><fmt:message key="login.label.companyid"/>:</td>
            <td width="70%"><input name="companyid" size="42"></td>
          </tr>
          <tr>
            <td width="24%" class="style_l4" nowrap><fmt:message key="login.label.username"/>:</td>
            <td width="70%"><input name="techid" size="42"></td>
          </tr>

          <tr>
            <td colspan="2"><div align="right">
              <input type="submit" name="pwd_reset" class="btnLogin" value=<fmt:message key="resetpassword.button.reset"/> >
              </div></td>
          </tr>
          <tr>
            <td height="30" colspan="2" background="x-res/fade.jpg" bgcolor="#FFFFFF" class="style_l4">&nbsp;</td>
          </tr>

          </table>
    </div>
    </td>
   </tr>
</table>

</html:form>

