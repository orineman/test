<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c4.tld" prefix="c4" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>

<TABLE align="center" cellSpacing="0" cellPadding="0" width="95%" border="0">
	<tr>
		<td class="body_title" align="center">
	    <fmt:message key="msg.welcome"/>&nbsp;
	    <c:if test="${infowave_sessionState.userDTO != null}">
	      <c:out value="${infowave_sessionState.userDTO.username}"/>
	    </c:if>
		</td>
	</tr>
	<c4:Resource name="pda_welcome.htm" localized="true"/>
</table>
