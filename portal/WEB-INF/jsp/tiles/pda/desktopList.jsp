<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>



<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>



















  <table class="device_text" cellspacing="0" cellpadding="0" width="100%" border="0">

    <c:if test="${empty infowave_sessionState.desktops}">
      <span class="device_text">
        <fmt:message key="msg.noDesktopsAvail"/>
      </span>
    </c:if>
    <c:forEach items="${infowave_sessionState.desktops}" var="desktop">
      <c:if test="${desktop.visible}">
       <%-- <c:if test="${desktop.connected && !desktop.hasExpired}"> --%>
	    <tr >
	      <td><br>
            <c:url value="/protected/showDesktopPage.do" var="desktopUrl">
              <c:param name="did" value="${desktop.did}"/>
            </c:url>
            <fmt:message key="field.pc"/>:
            <c:out value="${desktop.owner}"/>
               :&nbsp; 
             <c:choose>
              <c:when test="${desktop.connected && !desktop.hasExpired}">
                <a href='<c:out value="${desktopUrl}"/>'>
                  <c:out value="${desktop.name}"/></span></b><br>
                </a>&nbsp;
              </c:when>
              <c:otherwise>
                <c:out value="${desktop.name}"/></span></b><br>
              </c:otherwise>
            </c:choose>
			<b><span class="device_text">



            <c:if test="${desktop.hasExpired || (desktop.daysToExpiry>0 && desktop.daysToExpiry<16)}">
					<span class="device_warning"><font color="red">
              <c:if test="${infowave_sessionState.userDTO!=NULL && desktop.owner==infowave_sessionState.userDTO.username}">
                <a class='device_warning' href=<c:out value="${infowave_sessionState.reseller.expiryUrl}"/> target="_blank">
              </c:if>
              <c:if test="${desktop.hasExpired}">
                <fmt:message key="msg.desktopHasExpired"/>
              </c:if>
              <c:if test="${!desktop.hasExpired && desktop.daysToExpiry>0}">
                <fmt:message key="msg.daysToDesktopExpiryDate">
                  <fmt:param value="${desktop.daysToExpiry}"/>
                </fmt:message>
              </c:if>
              <c:if test="${infowave_sessionState.userDTO!=NULL && desktop.owner==infowave_sessionState.userDTO.username}">
                </a>
              </c:if>
					</span>
            </c:if>
		              <c:if test="${desktop.hasExpired || desktop.daysToExpiry==0}">
					&nbsp;
				</c:if>
			       </td>
			</tr>
			<tr>
			      <td height="1" bgcolor="black"> </td>
			</tr>
      <%-- </c:if> --%>
      </c:if>
    </c:forEach>
	</table>



