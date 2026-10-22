<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>


<jsp:include page="../header.jsp" />

<%
String action = request.getParameter("nextaction");
if( action == null ) action = "locate";
%>

<jsp:include page="technicianGenericSearch.jsp">
	<jsp:param name="nextaction" value='<%=action %>' />
</jsp:include>

