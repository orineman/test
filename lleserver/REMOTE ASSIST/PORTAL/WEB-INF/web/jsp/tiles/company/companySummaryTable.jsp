<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.CompanyForm" %>


<%
  String linkfmt = request.getParameter("linkfmt");  
%>

<table class="shaded_text" align="center" border="0" cellspacing="0" cellpadding="0" width="95%" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td class="shaded_header" ><fmt:message  key="companysummarytable.tableheader.companyid" /></td>
     <td class="shaded_header" ><fmt:message  key="companysummarytable.tableheader.companyname" /></td>
     <td class="shaded_header" ><fmt:message  key="companysummarytable.tableheader.companyxref" /></td>
     <td class="shaded_header" ><fmt:message  key="companysummarytable.tableheader.companymiscinfo1" /></td>
     <td class="shaded_header" ><fmt:message  key="companysummarytable.tableheader.companymiscinfo2" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="companysummarytable.tableheader.companymaxlogins" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="companysummarytable.tableheader.companylicense" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="companysummarytable.tableheader.companyactive" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="companysummarytable.tableheader.companylocale" /></td>
     <td class="shaded_header" align="center" ><fmt:message  key="companysummarytable.tableheader.companycreatedate" /></td>
     <td>&nbsp;</td>
  </tr>
  <c:forEach items="${CompanyForm.companyList}" var="companystr" >
  <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>
       &nbsp;&nbsp;<a href=<%=linkfmt %><c:out value="${companystr.linkencodedCompanyid}"/>&amp;lt=<c:out value="${companystr.licensetypeflag}"/> ><c:out value="${companystr.htmlencodedCompanyid}" /></a>
     </td>
     <td >
       &nbsp;&nbsp;<c:out value="${companystr.htmlencodedName}" />
     </td>
     <td >
       <c:out value="${companystr.htmlencodedXref}" />
     </td>
     <td >
       <c:out value="${companystr.htmlencodedMiscinfo1}" />
     </td>
     <td >
       <c:out value="${companystr.htmlencodedMiscinfo2}" />
     </td>
     <td ><div align="center">
       <c:out value="${companystr.maxconnsstr}" />
     </div></td>
     <td ><div align="center">
     <c:choose>
        <c:when test='${companystr.licensetypeflag == "D"}'>
	    <fmt:message key="company.label.licensetype_dedicated_abbr" />
        </c:when>
        <c:when test='${companystr.licensetypeflag == "C"}'>
	    <fmt:message key="company.label.licensetype_concurrent_abbr" />
        </c:when>
        <c:otherwise>
	    <fmt:message key="company.label.licensetype_dedicated_abbr" />
        </c:otherwise>
     </c:choose>
     </div></td>
     <td ><div align="center">
     <c:choose>
        <c:when test='${companystr.activeflag == "Y"}'>
	    <fmt:message key="company.label.active_abbr" />
        </c:when>
        <c:when test='${companystr.activeflag == "N"}'>
	    <fmt:message key="company.label.inactive_abbr" />
        </c:when>
        <c:otherwise>
	    <fmt:message key="company.label.inactive_abbr" />
        </c:otherwise>
     </c:choose>
     </div></td>
     <td ><div align="center">
       <c:out value="${companystr.locale}" />
     </div></td>
     <td ><div align="center">
       <c:out value="${companystr.createdatetimestr}" />
     </div></td>
     <td>&nbsp;</td>
  </tr>
  </c:forEach>
</table>


