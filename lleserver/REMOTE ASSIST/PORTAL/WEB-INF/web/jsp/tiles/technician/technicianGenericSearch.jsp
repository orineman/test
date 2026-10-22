<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>





<%
String action = request.getParameter("nextaction");  
SessionState state = WebUtil.getSessionState(request);
TechnicianDTO tech = state.getTechnicianDTO();
String companyId = tech.getCompanyid();
String companyIdEnc = tech.getLinkencodedCompanyid();
// Override the companyid if this is the siteadmin user
if( state.isSiteAdmin() ) {
    companyId = request.getParameter("co");
    companyIdEnc = new String(companyId.getBytes("8859_1"), "UTF-8");
}

boolean isTechLocate = false;
boolean isTechLocateByEmail = false;
boolean isTechAdd = false;
boolean isTechDelete = false;
boolean isTechEdit = false;
if( "locatetechnicianbyemail".equals( action ) ) {
      isTechLocateByEmail = true;
}
if( "technicianlocate".equals( action ) ) {
      isTechLocate = true;
}
if( "edit".equals( action ) ) {
      isTechEdit = true;
}
if( "add".equals( action ) ) {
      isTechAdd = true;
}
if( "delete".equals( action ) ) {
      isTechDelete = true;
}

%>
<html:form action="/protected/SearchForTechnician" method="post" >
      <input type="hidden" name="nextaction" value='<%=action %>' >
      <input type="hidden" name="companyid" value='<%=companyIdEnc %>' >
<%
if( isTechLocateByEmail ) {
%>
      <input type="hidden" name="type" value="email" >
<%
}
%>
      <table class="shaded_text" width="600" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="130" colspan="2" background="x-res/filing_cabinet.jpg">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <font size="+1">
<%
if( isTechLocate ) {
%>
            <fmt:message key="technician.header.techlocate"/>&nbsp;
<%
} else if( isTechLocateByEmail ) {
%>
            <fmt:message key="technician.header.techlocatebyemail"/>&nbsp;
<%
} else if( isTechEdit ) {
%>
            <fmt:message key="technician.header.techedit"/>&nbsp;
<%
} else if( isTechAdd ) {
%>
            <fmt:message key="technician.header.techadd"/>&nbsp;
<%
} else if( isTechDelete ) {
%>
            <fmt:message key="technician.header.techdelete"/>&nbsp;
<%
}
%>

<%
if( !isTechLocateByEmail ) {
%>
	      <fmt:message key="techniciangenericsearch.header">
              <fmt:param><%=companyIdEnc %></fmt:param>
	      </fmt:message>
<%
}
%>
	    </font>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <b><fmt:message key="techniciangenericsearch.instruction"/></b>
          </td>
        </tr>

        <tr>
          <td>&nbsp;</td>
          <td >
          <input type="text" style="content_input" size="64" maxlength="256" name="data" value=""/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
<%
if( !isTechLocateByEmail ) {
%>
                <fmt:message key="techniciangenericsearch.hint.wildcardsid"/><br>
<%
} else {
%>
                <fmt:message key="techniciangenericsearch.hint.wildcardsemail"/><br>
<%
}
%>
          </td>
        </tr>
<%
if( !isTechLocateByEmail ) {
%>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <b><fmt:message key="techniciangenericsearch.question.info"/><b>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="techid" CHECKED><fmt:message key="techniciangenericsearch.label.techid"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="techcid"><fmt:message key="techniciangenericsearch.label.techcid"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="name"><fmt:message key="techniciangenericsearch.label.techname"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="email"><fmt:message key="techniciangenericsearch.label.techemail"/>
          </td>
        </tr>
<%
}
%>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >
            <INPUT TYPE="submit" name="save" class="btnLogin" value='<fmt:message key="techniciangenericsearch.button.locate" />' />&nbsp;&nbsp;
            <button type="button" name="cancel" class="btnLogin" onclick="window.location='/RAssist/protected/showMainPage.do'"> 
                <fmt:message key="techniciangenericsearch.button.cancel"/>

          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="29" colspan="2" background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4">&nbsp;</td>
        </tr>

      </table>
</html:form>

