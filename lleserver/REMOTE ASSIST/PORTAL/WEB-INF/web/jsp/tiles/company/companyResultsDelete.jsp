<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.CompanyForm" %>
<%@ page import="com.infowave.c4p.service.dto.CompanyDTO" %>
<%@ page import="com.infowave.c4p.model.Company" %>
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
String jtemp = request.getParameter( "data" );
if( jtemp == null ) jtemp = "";
String name = new String(jtemp.getBytes("8859_1"), "UTF-8");
if( name == null || name.length() == 0 ) {
  name = "%";
}
CompanyForm form = (CompanyForm)request.getAttribute("companydata");
String companyName = form.getCompanyname();
String companyId = form.getCompanyid();
if( companyName == null || companyName.length() == 0 ) {
    companyName = companyId;
}
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
  <tr><td>&nbsp;</td></tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
  <td>
	&nbsp;&nbsp;<a href = "javascript:history.back()"><fmt:message key="company.link.back" /></a>
  </td>
  </tr>
<%
} else if( count == 0 ) {
%>
  <tr>
     <td>
	<font size="+1"><fmt:message key="companyresultsdelete.header.None">
              <fmt:param><bean:write name="companydata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
        </fmt:message></font>
     </td>
  </tr>
  <TR>
    <TD><a href="/RAssist/protected/showMainPage.do"><font size="-2"><fmt:message  key="company.link.top" /></font></a></TD>
  </tr>
<%
} else {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>
<%
if( count > 1 ) {
%>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="companyresultsdelete.header.Multi">
              <fmt:param><bean:write name="companydata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
        </fmt:message></font>
<%
} else {
%>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="companyresultsdelete.header.Single">
              <fmt:param><%=companyName %></fmt:param>
        </fmt:message></font>
<%
}
%>
     </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
<%
// If more than one result comes up, add a message to indicate how to get to the delete screen
if( count > 1 ) {
%>
  <tr>
     <td>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message  key="companyresultsdelete.instruction.deletelink" />
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
<jsp:include page="companySummaryTable.jsp">
  <jsp:param name="linkfmt" value="/RAssist/protected/SearchForCompany.do?type=companyid&nextaction=delete&data="/>  
</jsp:include>
</td></tr>

<%
// Add the delete/cancel buttons if only one company is selected
if( count == 1 ) {
%>
   <html:form action="/protected/updateCompany" method="post">
     <input type="hidden" name="companyid" value='<%=companyId %>' >
     <input type="hidden" name="companyname" value='<%=companyName %>' >
     <input type="hidden" name="nextaction" value="delete" >
     <tr><td>&nbsp;</td></tr>
        <tr align="left">
          <td>
    	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="delete" class="btnLogin" value='<fmt:message key="companyresultsdelete.button.submit"/>'>&nbsp;
	    <button type="button" name="cancel" class="btnLogin" onclick="window.location='/RAssist/protected/showMainPage.do'"> <fmt:message key="companyresultsdelete.button.cancel"/> 
          </td>
        </tr>
   </html:form>

<%
}
}
%>

  <tr>
    <td height="29" colspan="11" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
  </tr>
</table>
