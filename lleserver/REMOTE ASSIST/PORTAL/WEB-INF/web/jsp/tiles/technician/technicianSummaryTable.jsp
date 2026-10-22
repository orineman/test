<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.TechnicianForm" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>




<%
String linkfmtCompany = request.getParameter("linkfmtcompany");  
String linkfmtTechnician = request.getParameter("linkfmttechnician");  
String linkfmtTechnicianPwd = request.getParameter("linkfmttechnicianpwd");  
String action = request.getParameter("nextaction");
SessionState state = WebUtil.getSessionState(request);
TechnicianDTO tech = state.getTechnicianDTO();
String companyId = tech.getCompanyid();
boolean isDelete = false;
if( "delete".equals( action ) || "deletetechnician".equals(action) ) {
	isDelete = true;
}
%>

<table class="shaded_text" border="0" cellspacing="0" cellpadding="0" width="95%" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td class="shaded_header" ><fmt:message  key="techniciansummarytable.tableheader.companyid" /></td>
     <td class="shaded_header" ><fmt:message  key="techniciansummarytable.tableheader.techid" /></td>
     <td class="shaded_header" ><fmt:message  key="techniciansummarytable.tableheader.techcid" /></td>
<%
if( !isDelete ) {
%>
     <td class="shaded_header" ><fmt:message  key="techniciansummarytable.tableheader.password" /></td>
<%
}
%>
     <td class="shaded_header" ><fmt:message  key="techniciansummarytable.tableheader.email" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="techniciansummarytable.tableheader.name" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="techniciansummarytable.tableheader.phone" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="techniciansummarytable.tableheader.misc" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="techniciansummarytable.tableheader.active" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="techniciansummarytable.tableheader.level" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="techniciansummarytable.tableheader.techniciancreatedate" /></td>
  </tr>
  <c:forEach items="${TechnicianForm.technicianList}" var="technicianstr" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>
<%
if( state.isSiteAdmin() && !isDelete ) {
%>
     &nbsp;&nbsp;<a href=<%=linkfmtCompany %><c:out value="${technicianstr.linkencodedCompanyid}"/> ><c:out value="${technicianstr.htmlencodedCompanyid}" /></a>
<%
} else {
%>
     &nbsp;&nbsp;<c:out value="${technicianstr.htmlencodedCompanyid}" />
<%
}
%>
     </td>
     <td>
     &nbsp;&nbsp;<a href=<%=linkfmtTechnician %><c:out value="${technicianstr.linkencodedTechcid}"/> ><c:out value="${technicianstr.htmlencodedTechid}" /></a>
     </td>
     <td >
     &nbsp;&nbsp;<c:out value="${technicianstr.htmlencodedTechcid}" />
     </td>
<%
if( !isDelete ) {
%>
     <td>
     &nbsp;&nbsp;<a href=<%=linkfmtTechnicianPwd %><c:out value="${technicianstr.linkencodedTechcid}"/> ><fmt:message  key="techniciansummarytable.tablelink.pwdchange" /></a>
     </td>
<%
}
%>
     <td >
     &nbsp;&nbsp;<c:out value="${technicianstr.htmlencodedEmail}" />
     </td>
     <td align=middle>
     <c:out value="${technicianstr.htmlencodedName}" />
     </td>
     <td align=middle>
     <c:out value="${technicianstr.htmlencodedPhone}" />
     </td>
     <td align=middle>
     <c:out value="${technicianstr.htmlencodedMisc}" />
     </td>
     <td><div align="center">
     <c:out value="${technicianstr.activeflag}" />
     </div>
     </td>
     <td ><div align="center">
     <c:if test="${technicianstr.siteAdmin}" >
       <fmt:message key="techniciansummarytable.level.siteadmin" />
     </c:if>
     <c:if test="${technicianstr.companyAdmin}" >
       <fmt:message key="techniciansummarytable.level.companyadmin" />
     </c:if>
     <c:if test="${!technicianstr.anyAdmin}" >
       <fmt:message key="techniciansummarytable.level.technician" />
     </c:if>
     </div>
     </td>
     <td><div align="center">
     <c:out value="${technicianstr.createdatetimestr}" />
     </div>
     </td>
     <td>
     </td>
  </tr>
  </c:forEach>
</table>
