<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.TechnicianForm" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>
<%@ page import="com.infowave.c4p.model.Technician" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>
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
SessionState state = WebUtil.getSessionState(request);
boolean isTechnician = !state.isAnyAdmin();
boolean isCoAdmin = state.isCompanyAdmin();
boolean isSiteAdmin = state.isSiteAdmin();
TechnicianForm form = (TechnicianForm)request.getAttribute("techniciandata");
String strCount = form.getTechnicianCount();
int count = new Integer(strCount).intValue();
if( count == 0 ) {
  String jtemp1 = request.getParameter( "data" );
  if( jtemp1 == null ) jtemp1 = "%";
  String dataname1 = new String(jtemp1.getBytes("8859_1"), "UTF-8");
%>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
        <font size="+1">&nbsp;&nbsp;<fmt:message key="technicianresultsedit.header.None">
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
  <tr><td>&nbsp;</td></tr>
<%
} else {
List list = form.getTechnicianList();
TechnicianDTO technician = (TechnicianDTO)list.get(0);
boolean isUserCoAdmin = technician.isCompanyAdmin();
boolean isUserSiteAdmin = technician.isSiteAdmin();
boolean isUserTechnician = !technician.isAnyAdmin();
boolean isUserActive = technician.isPartiallyActive();
boolean fromError = false;
String companyid = technician.getCompanyid();
String companyIdEnc = new String(companyid.getBytes("8859_1"), "UTF-8");
String techcid = technician.getTechcid();
String name = technician.getHtmlencodedName();
String phone = technician.getHtmlencodedPhone();
String misc = technician.getHtmlencodedMisc();
String email = technician.getHtmlencodedEmail();
String techid = technician.getTechid();
String techidstr = technician.getHtmlencodedTechid();
List errlist = (List)request.getAttribute("c4p_errors");
if( errlist != null && !errlist.isEmpty() ) {
	fromError = true;
	companyid = form.getCompanyid();
	companyIdEnc = new String(companyid.getBytes("8859_1"), "UTF-8");
	techcid = form.getTechcid();
	name = form.getName();
	phone = form.getPhone();
	misc = form.getMisc();
	email = form.getEmail();
	techid = form.getTechid();
	techidstr = form.getTechid();
}
String dataname = techidstr;
if( !isTechnician ) {
  String jtemp = request.getParameter( "data" );
  if( jtemp == null || jtemp.length() == 0 ) jtemp = "%";
  dataname = new String(jtemp.getBytes("8859_1"), "UTF-8");
}
%>
  <tr><td>&nbsp;</td></tr>
  <c:if test="${not empty requestScope.c4p_errors}">

    <c:forEach items="${requestScope.c4p_errors}" var="error">
      <tr><td>
          <font color="red" size="+1"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="error.error" />&nbsp;<fmt:message key="${error.messageKey}" /></strong></font>
      </td></tr>
    </c:forEach>
  </c:if>
  <tr><td>&nbsp;</td></tr>
<%
if( count > 1 ) {
%>
      <tr><td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="technicianresultsedit.header.Multi">
              <fmt:param><bean:write name="techniciandata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=dataname %></fmt:param>
        </fmt:message></font>
      </td></tr>
      <tr><td>&nbsp;</td></tr>
<%
} else if( !isTechnician ) {
  if( fromError ) {
%>
      <tr><td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="technicianresultsedit.header.SingleError">
        </fmt:message></font>
      </td></tr>
<%
  } else {
%>
      <tr><td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="technicianresultsedit.header.Single">
              <fmt:param><%=techidstr %></fmt:param>
        </fmt:message></font>
      </td></tr>
      <tr><td>&nbsp;</td></tr>
<%
  }
} else {
%>
      <tr><td>
	<font size="+1"><fmt:message key="technicianresultsedit.header.Self">
        </fmt:message></font>
      </td></tr>
      <tr><td>&nbsp;</td></tr>
<%
}
%>
<%
// If more than one result comes up, add a message to indicate how to get to the edit screen
if( count > 1 ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td><strong>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message  key="technicianresultsedit.instruction.editlink" />
     </strong></td>
  </tr>
<%
}
%>

<tr><td>
<jsp:include page="technicianSummaryTable.jsp">
  <jsp:param name="linkfmtcompany" value="/RAssist/protected/SearchForCompany.do?type=companyid&nextaction=edit&data="/>  
  <jsp:param name="linkfmttechnician" value="/RAssist/protected/SearchForTechnician.do?type=techcid&nextaction=edit&data="/>  
  <jsp:param name="linkfmttechnicianpwd" value="/RAssist/protected/SearchForTechnician.do?type=techcid&nextaction=pwd&data="/>  
</jsp:include>
</td></tr>

<%
// Add the fields to edit the item if only one is selected
if( count == 1 ) {
boolean isCompanyDedicated = form.isCompanyDedicated();
%>
  <tr><td>&nbsp;</td></tr>
   <html:form action="/protected/updateTechnician" method="post" onreset="name.focus()">
     <input type="hidden" name="nextaction" value="update" >
     <input type="hidden" name="companyid" value='<%=companyid %>' >
     <input type="hidden" name="techcid" value='<%=techcid %>' >
   <tr><td >
     <table class="shaded_text" border="5" cellspacing="0" width="65%" align="center" cellpadding="0">
 	<tr>
          <td colspan="2" class="shaded_text"><strong>&nbsp;<fmt:message  key="technicianresultsedit.instruction.changeinfo" /></strong></td>
      	</tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.techid"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="techid"  maxlength="32" size="96" value='<%=techidstr %>'
                onblur="if( !ScreenText(document.TechnicianForm.techid.value) ) { document.TechnicianForm.techid.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.name"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="name"  maxlength="64" size="96" value='<%=name %>' 
		onblur="if( !ScreenText(document.TechnicianForm.name.value) ) { document.TechnicianForm.name.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.email"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="email"  maxlength="128" size="96" value='<%=email %>' 
		onblur="if( !ScreenText(document.TechnicianForm.email.value) ) { document.TechnicianForm.email.focus(); return false;}
                        if( !ValidEmail(document.TechnicianForm.email.value) ) { document.TechnicianForm.email.focus(); return false;}"
            />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.phone"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="phone"  maxlength="32" size="96" value='<%=phone %>' 
		onblur="if( !ScreenText(document.TechnicianForm.phone.value) ) { document.TechnicianForm.phone.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.misc"/>:</div></td>
          <td class="shaded_text">
            <html:textarea style="content_input" property="misc" cols="74" rows="7" value='<%=misc %>'
		onblur="if( !ScreenText(document.TechnicianForm.misc.value) ) { document.TechnicianForm.misc.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.type"/>:</div></td>
          <td class="shaded_text">
          <select name="accessname" size="1">
<%
if( isUserTechnician ) {
%>
                <fmt:message>
                  technicianType.selected.0
                </fmt:message>
<%
} else {
%>
                <fmt:message>
                  technicianType.0
                </fmt:message>
<%
}
if( !isCompanyDedicated && ( isCoAdmin || isSiteAdmin ) ) {
    if( isUserCoAdmin ) {
%>
                <fmt:message>
                  technicianType.selected.1
                </fmt:message>
<%
    } else {
%>
                <fmt:message>
                  technicianType.1
                </fmt:message>
<%
    }
}
if( !isCompanyDedicated && isSiteAdmin ) {
    if( isUserSiteAdmin ) {
%>
                <fmt:message>
                  technicianType.selected.2
                </fmt:message>
<%
    } else {
%>
                <fmt:message>
                  technicianType.2
                </fmt:message>
<%
    }
}
%>
          </td>
        </tr>

        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.status"/>:</div></td>
          <td class="shaded_text">
<%
if( isUserActive ) {
%>
	    <input type="radio" name="activeflags" value="Y" checked><fmt:message key="technician.label.active"/>
	    <input type="radio" name="activeflags" value="N"><fmt:message key="technician.label.inactive"/>
<%
} else {
%>
	    <input type="radio" name="activeflags" value="Y"><fmt:message key="technician.label.active"/>
	    <input type="radio" name="activeflags" value="N" checked><fmt:message key="technician.label.inactive"/>
<%
}
%>
          </td>
        </tr>
        <tr align="middle">
          <td colspan="2">
            <input type="submit" name="update" class="btnLogin" 
		 onClick="if(!EnsureText(techid.value, '<fmt:message key="error.needsdata"><fmt:param><fmt:message key="technician.label.techid"/></fmt:param></fmt:message>')) {document.TechnicianForm.techid.focus(); return false;}"

		value='<fmt:message key="technicianresultsedit.button.submit"/>'>&nbsp;&nbsp;
            <button type="button" name="cancel" class="btnLogin" onclick="window.location='/RAssist/protected/showMainPage.do'">
                <fmt:message key="technicianresultsedit.button.cancel"/>
	    <input type="reset" class="btnLogin" value='<fmt:message key="technician.button.reset"/>'>
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

