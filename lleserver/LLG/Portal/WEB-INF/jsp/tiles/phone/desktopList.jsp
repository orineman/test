<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>







<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>















<wml>







<card id="PCList" title="PC List">


<!--
	<do type="options" label="Logout">



		<go href="logout.do">



		</go>



	</do>
-->


  <c:forEach items="${infowave_sessionState.clientCapabilities.capabilities}" var="mode">



    <c:url value="/changeDisplayMode.do" var="theUrl">



      <c:param name="mode" value="${mode.code}"/>



    </c:url>



		<do type="options" label='<fmt:message>displayMode.<c:out value="${mode.name}"/></fmt:message> <fmt:message key="title.view"/>'>



			<go href='<c:out value="${theUrl}"/>'>



			</go>



		</do>



  </c:forEach>







	<do type="refresh" label="Cancel">



		<refresh/>



	</do>



	<p>



  <c:forEach items="${infowave_sessionState.desktops}" var="desktop">



    <c:if test="${desktop.visible}">



    <c:if test="${desktop.connected}">



      <!--<input class="device_text" type="submit" name="did.jshnnws" value="PC:">-->



      <c:url value="/protected/showDesktopPage.do" var="desktopUrl">



        <c:param name="did" value="${desktop.did}"/>



      </c:url>



      <a href='<c:out value="${desktopUrl}"/>'>



      <c:out value="${desktop.owner}"/>:&nbsp;<c:out value="${desktop.name}"/>



      </a>

      <br/>

    </c:if>



    </c:if>



  </c:forEach>



  </p>



</card>



</wml>



