<%--
  Created by IntelliJ IDEA.
  User: vlanik
  Date: 10.10.2007
  Time: 15:12:46
  To change this template use File | Settings | File Templates.
  parentFSElemPath
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib prefix="bean" uri="/WEB-INF/tlds/struts-bean.tld" %>

<td><!-- Enter here right top menu if needed --></td></tr><tr>

<td colspan="2">
    <c:if test="${sessionScope.WDS_ENABLED eq 'true'}">
        <a href="/pda/WindowsSearch.do"><bean:message key="msg.wds"/></a>
        <c:if test="${sessionScope.GDS_ENABLED eq 'true'}">
            &nbsp;|&nbsp;
            <a href="/pda/GoogleSearch.do"><bean:message key="msg.gds"/></a>
        </c:if>
    </c:if>
    <c:if test="${not sessionScope.WDS_ENABLED eq 'true'}">
        <c:if test="${sessionScope.GDS_ENABLED eq 'true'}">
            <a href="/pda/GoogleSearch.do"><bean:message key="msg.gds"/></a>
        </c:if>
    </c:if>
</td>