<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.TechnicianForm" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>
<%@ page import="com.infowave.c4p.model.Technician" %>
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
int count = new Integer(strCount).intValue();
if( count == 0 ) {
  String jtemp1 = request.getParameter( "data" );
  String dataname1 = new String(jtemp1.getBytes("8859_1"), "UTF-8");
  if( dataname1 == null || dataname1.length() == 0 ) {
    dataname1 = "%";
  }
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>
        <font size="+1">&nbsp;&nbsp;<fmt:message key="technicianresultsdelete.header.None">
              <fmt:param><bean:write name="techniciandata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=dataname1 %></fmt:param>
        </fmt:message></font>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>

<%
} else {
if( count > maxWebCount ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>
	<font size="+1"><b><fmt:message  key="error.toomanyresults">
              <fmt:param><%=strCount %></fmt:param>
        </fmt:message></b></font>
  </td>
  </tr>
<%
} else {
List list = form.getTechnicianList();
TechnicianDTO technician = (TechnicianDTO)list.get(0);
String technicianName = technician.getName();
String companyId = technician.getCompanyid();
String companyIdEnc = new String(companyId.getBytes("8859_1"), "UTF-8");
String techCid = technician.getTechcid();
String techid = technician.getTechid();
if( technicianName == null || technicianName.length() == 0 ) {
    technicianName = technician.getTechid();
}
String jtemp = request.getParameter( "data" );
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");
if( name == null || name.length() == 0 ) {
  name = "%";
}
%>
  <tr><td>&nbsp;</td></tr>
<%
if( count > 1 ) {
%>
     <tr><td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="technicianresultsdelete.header.Multi">
              <fmt:param><bean:write name="techniciandata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
        </fmt:message></font>
     </td></tr>
<%
} else {
%>
     <tr><td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="technicianresultsdelete.header.Single">
              <fmt:param><%=technicianName %></fmt:param>
        </fmt:message></font>
     </td></tr>
     <tr><td>&nbsp;</td></tr>
<%
}
%>
<%
// If more than one result comes up, add a message to indicate how to get to the delete screen
if( count > 1 ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message  key="technicianresultsdelete.instruction.deletelink" />
     </td>
  </tr>
<%
}
%>
<c:if test="${not empty requestScope.c4p_errors}">

  <c:forEach items="${requestScope.c4p_errors}" var="error">
    <tr><td>
        <font color="red" size="+1"><strong>&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="error.error" />&nbsp;<fmt:message key="${error.messageKey}" /></strong></font>
    </td></tr>
  </c:forEach>
</c:if>

<tr><td>
<jsp:include page="technicianSummaryTable.jsp">
  <jsp:param name="linkfmtcompany" value="/RAssist/protected/SearchForCompany.do?type=companyid&nextaction=delete&data="/>  
  <jsp:param name="linkfmttechnician" value="/RAssist/protected/SearchForTechnician.do?type=techcid&nextaction=delete&data="/>  
  <jsp:param name="linkfmttechnicianpwd" value="/RAssist/protected/SearchForTechnician.do?type=techcid&nextaction=pwd&data="/>  
</jsp:include>
</td></tr>
<%
// Add the delete/cancel buttons if only one technician is selected
if( count == 1 ) {
%>
   <html:form action="/protected/updateTechnician" method="post">
     <input type="hidden" name="companyid" value='<%=companyId %>' >
     <input type="hidden" name="techcid" value='<%=techCid %>' >
     <input type="hidden" name="techid" value='<%=techid %>' >
     <input type="hidden" name="name" value='<%=technicianName %>' >
     <input type="hidden" name="nextaction" value="delete" >
     <tr><td>&nbsp;</td></tr>
     <tr><td>
     <table class="shaded_text" border="0" cellspacing="0" width="95%" align="center" cellpadding="0">
        <tr align="left">
          <td>
            <input type="submit" name="delete" class="btnLogin" value='<fmt:message key="technicianresultsdelete.button.submit"/>'>&nbsp;&nbsp;
	    <button type="button" name="cancel" class="btnLogin" onclick="window.location='/RAssist/protected/showMainPage.do'"> <fmt:message key="technicianresultsdelete.button.cancel"/> 
          </td>
        </tr>
     </table>
     </td></tr>
   </html:form>

<%
}
}
}
%>

  <tr>
    <td height="29" colspan="11" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
  </tr>
</table>

