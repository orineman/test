<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/tlds/struts-bean.tld" %>
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

<TABLE class="header_bar" cellspacing="0" cellpadding="0" width="100%" id="HeaderBar">
  <TR>
    <td colspan="3" width="100%">
    <!-- header -->
    <table cellSpacing="0" cellPadding="0" border="0" class="changeviewtable" width="100%">
        <tr>
  
          <%--                                           --%>
          <%-- A list of display supported display modes --%>
          <%--                                           --%>
          <td width="90%"><fmt:message key="title.view"/>:
              <c:forEach items="${infowave_sessionState.clientCapabilities.capabilities}"
                         var="mode">
                <c:url value="/changeDisplayMode.do" var="theUrl">
                   <c:param name="mode"
                            value="${mode.code}"/>
                </c:url>
				<c:if test="${mode.code=='L'}">
					<img src="x-res/icon-pc-small.gif" border="0" alt='<fmt:message key="displayMode.pc"/>' />
				</c:if>
				<c:if test="${mode.code=='M'}">
					<img src="x-res/icon-pda-small.gif" border="0" alt='<fmt:message key="displayMode.pda"/>' />
				</c:if>
                <a href='<c:out value="${theUrl}"/>'>
                  <fmt:message>displayMode.<c:out value="${mode.name}"/></fmt:message>
                </a>

              </c:forEach>
          </td>
		  <td align="right" nowrap>
            <c:if test="${infowave_sessionState.userDTO != null}">
				<img src="x-res/icon-logout-small.gif" border="0" alt='<fmt:message key="action.logout"/>' />
                <a href="/logout.do" class="view_bar"><fmt:message key="action.logout"/>
				<%=username %>
                  </a>&nbsp;&nbsp;&nbsp;
            </c:if>
          </td>
	 	</tr>
	</table>

    <table class="headertable" cellSpacing="0" cellPadding="0" border="0" width="100%">
        <tr>
		    <td width="100%">

		      <c:choose> 
		        <c:when test='${param.vendor == "SimplyAccounting"}'>
	          		<img src="x-res/simplyNO2003.gif" border="0" height=50 alt='<fmt:message key="msg.alt.accounting"/>'>
				</c:when>
				<c:otherwise>
	          		<img src="x-res/lle-logo.gif" border="0" alt='<fmt:message key="title.mainWindowTitle"/>'>
				</c:otherwise>
		      </c:choose>
			</td>
	        <td width="74">
	          <a href="<fmt:message key="msg.link.company"/>"><img src="x-res/laplink-logo.gif" border="0" alt='<fmt:message key="msg.alt.llconnectworld"/>'/></a>
	        </td>
        </tr>
	</table>
    <table class="maintabstable" cellSpacing="0" cellPadding="0" border="0" width="100%">

		<%-- Tab Strip --%>
	    <tr><td class="maintabstable-r1"><img src="x-res/spacer.gif" border="0" alt="" width="1" height="2" /></td></tr>
    	<tr><td class="maintabstable-r2"><img src="x-res/spacer.gif" border="0" alt="" width="1" height="1" /></td></tr>
        <tr>
          <!-- The page tabs here -->

          <td class="tabstrip" align="left" valign="bottom">
            <table class="tabstrip" cellpadding="0" cellspacing="0" border="0">
              <tr>
                <%-- Desktop tab --%>
                <c:if test="${infowave_sessionState.inDesktopSection}">
                  <td class="tabstrip_sel">
                    <fmt:message key="title.myPCs"/>
                  </td>
                </c:if>
                <c:if test="${not infowave_sessionState.inDesktopSection}">
                  <td class="tabstrip_unsel">
                    <a class="tabstrip" href="/protected/showMainPage.do"><fmt:message key="title.myPCs"/></a>
                  </td>
                </c:if>	  
                <%-- Tab separator --%>
				<td><img src="x-res/maintab-slash.jpg" border="0" alt="" /></td>
                <%-- Account tab --%>
                <c:if test="${infowave_sessionState.inAccountSection}">
                  <td class="tabstrip_sel">
                    <fmt:message key="title.myAccount"/>
                  </td>
                </c:if>
                <c:if test="${not infowave_sessionState.inAccountSection}">
                  <td class="tabstrip_unsel">
                    <a class="tabstrip" href="/protected/showAccountManagementPage.do"><fmt:message key="title.myAccount"/></a>
                  </td>
                </c:if>
				<td><img src="x-res/maintab-slash.jpg" border="0" alt="" /></td>

                <%-- tab div --%>
                <c:if test="${infowave_sessionState.inHelpSection}">
                  <td class="tabstrip_sel">
                    <fmt:message key="title.help"/>
                  </td>
                </c:if>
                <c:if test="${not infowave_sessionState.inHelpSection}">
                  <td class="tabstrip_unsel">
                    <a class="tabstrip" href="<fmt:message key="msg.link.faqs"/>" target="_blank"><fmt:message key="title.help"/></a>
                  </td>
                </c:if>
				<td><img src="x-res/maintab-slash.jpg" border="0" alt="" /></td>
				<TD class="tabstrip">&nbsp;</TD>


              </tr>

            </table>
          </td>
        </tr>
		<tr><td class="maintabstable-r2"><img src="x-res/spacer.gif" border="0" alt="" width="1" height="1" /></td></tr>
        <tr><td class="maintabstable-r1"><img src="x-res/spacer.gif" border="0" alt="" width="1" height="1" /></td></tr>
      </table>
    </td>
  </tr>
</table>


