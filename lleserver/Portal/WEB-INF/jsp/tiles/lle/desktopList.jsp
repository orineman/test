<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/ll.tld" prefix="ll" %>
<PCList>
   <c:forEach items="${infowave_sessionState.desktops}" var="desktop">
      <c:if test="${desktop.visible}"><PC>
      <ll:LLCheckIcm id="j" host="ll.ll2go.com" did="PDJABKNE" isAvailable="true">
       jae1
      </ll:LLCheckIcm>
      <c:out value="${isAvailable}"/>
      <c:if test="${isAvailable}">JAEJAEJAE 
      </c:if>
      <c:if test="${desktop.hasExpired}"><c:out value="${desktop.name} (expired)"/></c:if>
      <c:if test="${desktop.connected && isAvailable}"><c:out value="${desktop.name}"/></c:if>
      <c:if test="${desktop.connected && !isAvailable}"><c:out value="${desktop.name} (unavailable)"/></c:if>
      <c:if test="${!desktop.connected && isAvailable && !desktop.hasExpired}"><c:out value="${desktop.name} (offline)"/></c:if>
      <c:if test="${!desktop.connected && !isAvailable && !desktop.hasExpired}"><c:out value="${desktop.name} (offline/unavailable)"/></c:if>
      </PC><did><c:out value="${desktop.did}"/></did>
      </c:if></c:forEach></PCList>
