<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>




<jsp:include page="header.jsp" >
 <jsp:param name="attop" value="true"/>
</jsp:include>

      <table width="650" border="0" align="center" class="siongtable" cellpadding="0" cellspacing="0">
        <tr>
          <td height="130" colspan="2" background="x-res/filing_cabinet.jpg">&nbsp;</td>
        </tr>
        <tr>
           <td colspan="2" bgcolor="#e8e8e8" class="style2">
             <b><fmt:message key="welcome.question"/><b>
           </td>
        </tr>



<%
SessionState state = WebUtil.getSessionState(request);
if( state.isSiteAdmin() ) {
%>
<td>
<jsp:include page="company/companyMenu.jsp" />
</td>
<td>
<jsp:include page="technician/technicianSiteAdminMenu.jsp" />
</td>

<%
} else if( state.isCompanyAdmin() ) {
%>

<td colspan="2" bgcolor="#e8e8e8">
<jsp:include page="technician/technicianCompanyAdminMenu.jsp" />
</td>

<%
} else {
%>

<td colspan="2" bgcolor="#e8e8e8">
<jsp:include page="technician/technicianMenu.jsp" />
</td>

<%
}
%>

        <tr>
          <td height="29" colspan="2" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
        </tr>
      </table>

