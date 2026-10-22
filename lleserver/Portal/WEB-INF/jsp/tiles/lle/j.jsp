<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>


<PCList>
    <c:forEach items="${infowave_sessionState.desktops}" var="desktop">

        <c:if test="${desktop.visible}">

            <c:if test="${desktop.hasExpired}">
<PC>${desktop.name} (expired)</PC>
<did>${desktop.did}</did>
            </c:if>

            <c:if test="${desktop.connected}">
<PC>${desktop.name}</PC>
<did>${desktop.did}</did>
            </c:if>

            <c:if test="${!desktop.connected}">
<PC>${desktop.name} (offline)</PC>
<did>${desktop.did}</did>
            </c:if>


            <c:if test="${!desktop.connected && !desktop.hasExpired}">
<PC>${desktop.name} (unavailable)</PC>
<did>${desktop.did}</did>
            </c:if>
    </c:forEach>
</PCList>
