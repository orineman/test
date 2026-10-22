<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.CompanyForm" %>
<%@ page import="com.infowave.c4p.util.ApplicationParameters" %>


<jsp:include page="../header.jsp" />


<table class="shaded_text" width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td height="50" colspan="1" background="x-res/filing_cabinet.jpg"></td>
    </tr>
    <tr>
      <td colspan="1" background="x-res/filing_cabinet_btm.jpg"></td>
    </tr>



<%
int maxWebCount = ApplicationParameters.instance().getMaxWebCount();
String subtype = request.getParameter("nextaction");
boolean isTechLocate = false;
boolean isTechListAll = false;
boolean isTechAdd = false;
boolean isTechDelete = false;
boolean isTechEdit = false;
if( "listalltechnicians".equals( subtype ) ) {
      isTechListAll = true;
}
if( "locatetechnician".equals( subtype ) ) {
      isTechLocate = true;
}
if( "edittechnician".equals( subtype ) ) {
      isTechEdit = true;
}
if( "addtechnician".equals( subtype ) ) {
      isTechAdd = true;
}
if( "deletetechnician".equals( subtype ) ) {
      isTechDelete = true;
}

CompanyForm form = (CompanyForm)request.getAttribute("companydata");
String nextaction = form.getNextaction();
String strCount = form.getCompanyCount();
int count = new Integer(strCount).intValue();
if( count > maxWebCount ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>&nbsp;</td>
  <td>
	<font size="+1"><b><fmt:message  key="error.toomanyresults">
              <fmt:param><%=strCount %></fmt:param>
        </fmt:message></b></font>
  </td>
  </tr>
<%
} else {
String jtemp = request.getParameter( "data" );
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");
if( name == null || name.length() == 0 ) {
  name = "%";
}
String url = "/RAssist/protected/SetType.do?type=" +nextaction+ "&nextaction=" +nextaction+ "&co=";
if( isTechListAll ) {
	url = "/RAssist/protected/SearchForTechnician.do?nextaction=listalltechnicians&companyid=";
}
%>

  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%
if( isTechLocate ) {
%>
            <fmt:message key="company.header.techlocate"/>&nbsp;
<%
} else if( isTechListAll ) {
%>
            <fmt:message key="company.header.techlistall"/>&nbsp;
<%
} else if( isTechEdit ) {
%>
            <fmt:message key="company.header.techedit"/>&nbsp;
<%
} else if( isTechAdd ) {
%>
            <fmt:message key="company.header.techadd"/>&nbsp;
<%
} else if( isTechDelete ) {
%>
            <fmt:message key="company.header.techdelete"/>&nbsp;
<%
}
%>

	   <fmt:message  key="company.header.results">
              <fmt:param><bean:write name="companydata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
        </fmt:message></font>
     </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<fmt:message key="companyresultstotechnician.instruction.continue"/>
     </td>
  </tr>

<tr><td>
<jsp:include page="companySummaryTable.jsp">
  <jsp:param name="linkfmt" value='<%=url %>' />  
</jsp:include>
</td></tr>


<%
}
%>

  <tr>
    <td height="29" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
  </tr>
</table>

