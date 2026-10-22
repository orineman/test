<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>




<%
SessionState state = WebUtil.getSessionState(request);
TechnicianDTO tech = state.getTechnicianDTO();
boolean isSiteAdmin = state.isSiteAdmin();

String subtype = request.getParameter("stype");
String nextaction = "locate";
if( isSiteAdmin && !(subtype == null || subtype.length() == 0) ) {
	nextaction = subtype;
}
%>

<jsp:include page="../header.jsp" />

<jsp:include page="companyGenericSearch.jsp">
	<jsp:param name="nextaction" value='<%=nextaction %>' />
</jsp:include>

