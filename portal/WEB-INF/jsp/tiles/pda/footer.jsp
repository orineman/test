<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

		<TABLE bgcolor="black" cellspacing="0" cellpadding="0" width="100%" id="FooterBar">
			<tr class="footer_bar">
				<td align="center">
				<font color="white">
						<!-- include if logged in
						-->
				    <c:choose>
				        <c:when test="${infowave_sessionState.userDTO != null}">
									<span>
									<fmt:message key="action.logout"/>&nbsp;
									<a href="/logout.do" class="footer_text"><c:out value="${infowave_sessionState.userDTO.username}"/></a>
				        </c:when>
				        <c:otherwise>
									<span class="footer_heading"><fmt:message key="title.view"/>:
				        </c:otherwise>
				    </c:choose>
				    &nbsp;</span>
          <%--                                           --%>
          <%-- A list of display supported display modes --%>
          <%--                                           --%>
              <c:forEach items="${infowave_sessionState.clientCapabilities.capabilities}"
                         var="mode">
                <c:url value="/changeDisplayMode.do" var="theUrl">
                   <c:param name="mode"
                            value="${mode.code}"/>
                </c:url>
                <a href='<c:out value="${theUrl}"/>' class="footer_text">
                  <fmt:message>displayMode.<c:out value="${mode.name}"/></fmt:message></a>&nbsp;
              </c:forEach>
          </td>
        </tr>
				</font>
				</td>
			</tr>
			<tr class="footer_bar">
				<td><img width="1" height="3" border="0"></td>
			</tr>
			<tr class="footer_bar">
				<td class="footer_text" align="center">
				<font color="#ffffff">
        <%-- <fmt:bundle basename="laplink"> --%>
        <fmt:message key="message.pda_copyright" bundle="${c4p_resellerBundle}"/>
        <%-- </fmt:bundle> --%>
				</font>
				</td>
			</tr>
		</TABLE>
