<%@ page contentType="text/html; charset=UTF-8" %>
<%
  response.sendRedirect("/x-res/mainPageOpener.html");
%>
<%--
<script language="javascript">
function OnLoadBasic(){
<!--
window.open("protected/showMainPageInPopup.do",null,
             height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");

-->
}
</script>
--%>
This is the jsp  popup opener
<%--
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
--%>