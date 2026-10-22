<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>
<%@ page import="com.infowave.c4p.web.actions.TechnicianForm" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>
<%@ page import="com.infowave.c4p.model.Technician" %>
<%@ page import="java.util.List" %>




<jsp:include page="../header.jsp" />

<table class="shaded_text" width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>  
      <td height="50" colspan="1" background="x-res/filing_cabinet.jpg"></td>
    </tr>       
    <tr>  
      <td colspan="1" background="x-res/filing_cabinet_btm.jpg"></td>
    </tr>

<%
SessionState state = WebUtil.getSessionState(request);
boolean isTechnician = !state.isAnyAdmin();
boolean isCoAdmin = state.isCompanyAdmin();
boolean isSiteAdmin = state.isSiteAdmin();

String technicianname = "not found";
String companyid = "not found";
String companyidEnc = "not found";
TechnicianForm form = (TechnicianForm)request.getAttribute("techniciandata");
if( form != null ) {
    technicianname = form.getName();
    companyid = form.getCompanyid();
    //companyidEnc = new String(companyid.getBytes("8859_1"), "UTF-8");
    if( technicianname == null || technicianname.length() == 0 ) {
        technicianname = form.getTechid();
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
if( isDelete ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<fmt:message  key="technicianchangeoraddsuccess.deletesuccess.header" >
	    <fmt:param><%=technicianname %></fmt:param>
            <fmt:param><%=companyid %></fmt:param>
	</fmt:message>
        </font>
     </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
 
<%
} else {
%>

  <tr>
     <td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message  key="technicianchangeoraddsuccess.header" /></font>
     </td>
  </tr>
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

