<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<PCList>
  <c:forEach items="${infowave_sessionState.desktops}" var="desktop">
    <c:if test="${desktop.visible}"><PC><c:if test="${desktop.hasExpired}"><c:out value="${desktop.name} (expired)"/></c:if><c:if test="${desktop.LLGEnabled && !desktop.hasExpired}"><c:out value="${desktop.name}"/></c:if><c:if test="${!desktop.LLGEnabled && !desktop.hasExpired}"><c:out value="${desktop.name} (unavailable)"/></c:if></PC>
    <did><c:out value="${desktop.did}"/></did></c:if>
  </c:forEach>
</PCList>


