<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/c4.tld" prefix="c4" %>

<script type="text/javascript" language="JavaScript">
<!--
function setTitle(){
document.title='<fmt:message  bundle="${c4p_resellerBundle}" key="title.subscripion"/>';
refreshOpener();
}

function cancel(){
this.close();
}

function refreshOpener(){
// Refresh the opener window if we were passed a page in the request
 <c:if test="${not empty requestScope.page}">
    opener.location.replace('<c:out value="${requestScope.page}"/>');
 </c:if>
}
// -->
</script>
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
            <b><font color="blue">
                <fmt:message key="msg.licenseChangeSuccess"/>
            </font></b>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <c:if test="${requestScope.c4p_freedup_licenses != null}">
          <tr>
            <td>
              The following licenses can now be used on other PC's:<br>
              <ul>
              <c:forEach items="${requestScope.c4p_freedup_licenses}" var="license">
                <li>
                <c:choose>
                  <c:when test="${license.fixedTerm}">
                    <c4:LicenseCodeDisplay code="${license.code}"/>
                     with <c:out value="${license.remainingDays}"/>&nbsp;day(s) remaining
                     <c:if test="${license.numAvailableUses>1}">(# uses:&nbsp;<c:out value="${license.numAvailableUses}"/>)
                     </c:if>
                  </c:when>
                  <c:otherwise>
                    <c4:LicenseCodeDisplay code="${license.code}"/>
                     <c:if test="${license.numAvailableUses>1}">
                       (# uses:&nbsp;<c:out value="${license.numAvailableUses}"/>)
                     </c:if>
                  </c:otherwise>
                </c:choose>
                </li>
              </c:forEach>
              </ul>
            </td>
          </tr>
        </c:if>

        <tr>
          <td align="center">
            <input type="button" onClick="cancel()" value='<fmt:message key="action.close"/>'/>
          </td>
        </tr>
      </table>
