<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.CompanyForm" %>



<jsp:include page="../header.jsp" />

<table class="shaded_text" width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td height="50" colspan="1" background="x-res/filing_cabinet.jpg"></td>
    </tr>
    <tr>
      <td colspan="1" background="x-res/filing_cabinet_btm.jpg"></td>
    </tr>


<%
String companyname = "not found";
CompanyForm form = (CompanyForm)request.getAttribute("companydata");
if( form != null ) {
    companyname = form.getCompanyname();
    if( companyname == null || companyname.length() == 0 ) {
 	companyname = form.getCompanyid();
    }
}
String action = request.getParameter("nextaction");
boolean isDelete = false;
boolean isEdit = false;
boolean isAdd = false;
if( "delete".equals( action ) )
{
        isDelete = true;
} else if( "update".equals( action ) )
{
        isEdit = true;
} else if( "add".equals( action ) )
{
        isAdd = true;
}

if( isDelete )
{
%>
  <tr>
     <td>
	<font size="+1"<b>&nbsp;&nbsp;&nbsp;&nbsp;
	<fmt:message  key="companychangeoraddsuccess.deletesuccess.header" >
	    <fmt:param><%=companyname %></fmt:param>
	</fmt:message>
        </b></font>
     </td>
  </tr>
 
<%
} else {
%>

  <tr>
     <td>
	<font size="+1"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message  key="companychangeoraddsuccess.header" /></b></font>
     </td>
  </tr>

<tr><td>
<jsp:include page="companySummaryTable.jsp">
  <jsp:param name="linkfmt" value="/RAssist/protected/SearchForCompany.do?type=companyid&nextaction=edit&data="/>  
</jsp:include>
</td></tr>

<%
}
%>

  <tr>
    <td height="29" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
  </tr>

</table>

