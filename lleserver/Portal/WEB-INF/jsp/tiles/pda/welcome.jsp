<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c4.tld" prefix="c4" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>
<%@ page import="com.infowave.c4p.service.dto.UserDTO" %>



<%
SessionState state = WebUtil.getSessionState(request);
UserDTO user = state.getUserDTO();
String jtemp = "";
String username = "";
if( user != null ) {
	jtemp = user.getUsername();
	username = new String(jtemp.getBytes("8859_1"), "UTF-8");
}
%>


<TABLE align="center" cellSpacing="0" cellPadding="0" width="95%" border="0">
	<tr>
		<td class="body_title" align="center">
	    <fmt:message key="msg.welcome"/>&nbsp;
	    <c:if test="${infowave_sessionState.userDTO != null}">
	      <%=username %>
	    </c:if>
		</td>
	</tr>
	<c4:Resource name="pda_welcome.htm" localized="true"/>
</table>
