<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.TechnicianForm" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>
<%@ page import="com.infowave.c4p.util.ApplicationParameters" %>
<%@ page import="java.util.List" %>




<jsp:include page="../header.jsp" />

<table class="shaded_text" width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td height="50" colspan="1" background="x-res/filing_cabinet.jpg">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="1" background="x-res/filing_cabinet_btm.jpg"></td>
    </tr> 
  

<%
int maxWebCount = ApplicationParameters.instance().getMaxWebCount();
TechnicianForm form = (TechnicianForm)request.getAttribute("techniciandata");
String strCount = form.getTechnicianCount();
String companyId = form.getCompanyid();
String companyIdEnc = companyId;
if( companyId == null ) {
  List list = form.getTechnicianList();
  TechnicianDTO technician = (TechnicianDTO)list.get(0);
  companyId = technician.getHtmlencodedCompanyid();
  companyIdEnc = technician.getHtmlencodedCompanyid();
} else {
  companyIdEnc = new String(companyId.getBytes("8859_1"), "UTF-8");
}
String jtemp = request.getParameter( "data" );
if( jtemp == null || jtemp.length() == 0 ) jtemp = "%";
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");
int count = new Integer(strCount).intValue();
if( count == 0 ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
        <font size="+1">&nbsp;&nbsp;<fmt:message key="technicianresultslocate.header.None">
              <fmt:param><bean:write name="techniciandata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
        </fmt:message></font>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>


<%
} else if( count > maxWebCount ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>
	<font size="+1"<b><fmt:message  key="error.toomanyresults">
              <fmt:param><%=strCount %></fmt:param>
        </fmt:message></b></font>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
<%
} else {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;
              <fmt:message  key="technician.header.results">
              <fmt:param><bean:write name="techniciandata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
              <fmt:param><%=companyId %></fmt:param>
        </fmt:message></font>
     </td>
  </tr>
  <tr><td>&nbsp;</td></tr>

<tr><td>
<jsp:include page="technicianSummaryTable.jsp">
  <jsp:param name="linkfmtcompany" value="/RAssist/protected/SearchForCompany.do?type=companyid&nextaction=edit&data="/>  
  <jsp:param name="linkfmttechnician" value="/RAssist/protected/SearchForTechnician.do?type=techcid&nextaction=edit&data="/>  
  <jsp:param name="linkfmttechnicianpwd" value="/RAssist/protected/SearchForTechnician.do?type=techcid&nextaction=pwd&data="/>  
</jsp:include>
</td></tr>

<%
}
%>

  <tr>
    <td height="29" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
  </tr>
</table>

