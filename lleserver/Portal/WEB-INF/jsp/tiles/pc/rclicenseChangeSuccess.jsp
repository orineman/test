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
                <fmt:message key="msg.rclicenseChangeSuccess"/>
            </font></b>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <c:if test="${requestScope.c4p_freedup_licenses != null}">
          <tr>
            <td>
		  <fmt:message key="msg.licensefreedup"/>
              <br>
              <ul>
              <c:forEach items="${requestScope.c4p_freedup_licenses}" var="license">
                <li>
                <c:choose>
                  <c:when test="${license.fixedTerm}">
                    <c4:LicenseCodeDisplay code="${license.code}"/>
                     <fmt:message key="msg.with"/><c:out value="${license.remainingDays}"/>&nbsp;<fmt:message key="msg.daysremain"/>
                  </c:when>
                  <c:otherwise>
                    <c4:LicenseCodeDisplay code="${license.code}"/>
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
