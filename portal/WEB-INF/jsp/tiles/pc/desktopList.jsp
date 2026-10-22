<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>









  <table class="body_bar" cellspacing="0" cellpadding="0" width="100%" border="0">

    <c:if test="${empty infowave_sessionState.desktops}">
      <span class="toolbar_heading">
      <fmt:message key="msg.noDesktopsAvail"/>
      </span>
    </c:if>

    <c:forEach items="${infowave_sessionState.desktops}" var="desktop">

        <c:if test="${desktop.visible}">
			<tr>
				<td background='x-res/GRADIENT_2.GIF'>
				<table id="PcUserNameTable" width="100%">
				<tr><td colspan=2>
	            		<span class="toolbar_heading">

	              		<fmt:message key="field.user"/>:

	            		</span>

	            		<span class="toolbar_text">

	              			<c:out value="${desktop.owner}"/>

	            		</span>
            		</td>
				</tr>


				<tr>

					<td>

            <c:if test="${desktop.connected}">

              <c:url value="/protected/showDesktopPage.do" var="desktopUrl">

                <c:param name="did" value="${desktop.did}"/>

              </c:url>

              <img src='x-devicon?did=<c:out value="${desktop.did}"/>' height="20" />

            </c:if>

            <c:if test="${! desktop.connected}">

              <img src='x-devicon?did=<c:out value="${desktop.did}"/>' height="20" />

            </c:if>

					</td>

					<td class="toolbar_text" align="left" width="100%">



            <c:if test="${desktop.connected && !desktop.hasExpired}">

                  <c:out value="${desktop.name}"/>

            </c:if>

            <c:if test="${!desktop.connected || desktop.hasExpired}">

              <c:out value="${desktop.name}"/>

            </c:if>

            		</td>
            	</tr>
			</table>
			</td>
		</tr>


            <c:if test="${desktop.hasExpired || (desktop.daysToExpiry>0 && desktop.daysToExpiry<16)}">
	    <tr><td background="x-res/GRADIENT_1.GIF">
			<TABLE width="100%" align="center" border="0">
			<tr><td align="middle">
              <c:if test="${infowave_sessionState.userDTO!=NULL && desktop.owner==infowave_sessionState.userDTO.username}">
                <a class='device_warning' href=<c:out value="${infowave_sessionState.reseller.expiryUrl}"/> target="_blank">
              </c:if>

              <span class='device_warning'>
              <c:if test="${desktop.hasExpired}">

                <fmt:message key="msg.desktopHasExpired"/>

              </c:if>

              <c:if test="${!desktop.hasExpired && desktop.daysToExpiry>0}">

                <fmt:message key="msg.daysToDesktopExpiryDate">

                  <fmt:param value="${desktop.daysToExpiry}"/>

                </fmt:message>

              </c:if>
              </span>

              <c:if test="${infowave_sessionState.userDTO!=NULL && desktop.owner==infowave_sessionState.userDTO.username}">
                </a>
              </c:if>
			  </td></tr></table>
        </td></tr>

            </c:if>

        </c:if>






    </c:forEach>



	</table>



