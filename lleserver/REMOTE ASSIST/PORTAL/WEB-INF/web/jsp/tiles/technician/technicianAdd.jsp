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



<jsp:include page="../header.jsp" />

<%
// Determine if we're here on an initial add or from an error
// (e.g., a duplicate techid)
boolean fromError = true;
boolean isDedicated = true;
SessionState state = WebUtil.getSessionState(request);
String licenseTypeStr = (String)request.getParameter("lt");
TechnicianForm form = (TechnicianForm)request.getAttribute("techniciandata");
if( form == null ) {
  // initial add
  fromError = false;
  if( licenseTypeStr != null ) { 
    isDedicated = (licenseTypeStr.equals("C")?false:true);
  } else {
    isDedicated = state.isCompanyDedicated();
  }
} else {
  isDedicated = form.isCompanyDedicated();
}
TechnicianDTO tech = state.getTechnicianDTO();
String companyId = tech.getCompanyid();
String companyIdEnc = tech.getHtmlencodedCompanyid();
boolean isTechnician = !state.isAnyAdmin();
boolean isCoAdmin = state.isCompanyAdmin();
boolean isSiteAdmin = state.isSiteAdmin();
// Override the companyid if this is the siteadmin user
if( isSiteAdmin ) {
    if( fromError ) {
      companyId = form.getCompanyid();
      companyIdEnc = companyId;
    } else {
      companyId = request.getParameter("co");
      companyIdEnc = new String(companyId.getBytes("8859_1"), "UTF-8"); 
    }
}


%>
  <table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td height="130" colspan="1" background="x-res/filing_cabinet.jpg"></td>
    </tr>
 <tr>
  <td>
   <table class="shaded_text" border="0" cellspacing="0" cellpadding="0" width="100%" >
<%
if( fromError ) {
%>
  <c:forEach items="${requestScope.c4p_errors}" var="error">
    <tr><td>
        <font color="red" size="+1"><strong>&nbsp;&nbsp;&nbsp;<fmt:message key="error.error" />&nbsp;<fmt:message key="${error.messageKey}" /></strong></font>
    </td></tr>
  </c:forEach>
<%
}
%>

    <tr>
     <td>
        <font size="+1">&nbsp;&nbsp;&nbsp;<fmt:message  key="technician.header.company">
              <fmt:param><%=companyIdEnc %></fmt:param>
        </fmt:message></font>
     </td>
    </tr>
   </table>
   </td></tr>
   <tr><td >
   <html:form action="/protected/updateTechnician" method="post" onreset="name.focus()">
     <input type="hidden" name="nextaction" value="add" >
     <input type="hidden" name="companyid" value='<%=companyIdEnc %>' >
     <table class="shaded_text" border="5" cellspacing="0" width="100%" align="center" cellpadding="0">
 	<tr>
           <td colspan="2" class="shaded_text"><strong>
<%
if( fromError ) {
%>
     &nbsp;&nbsp;<fmt:message  key="technicianadd.instruction.changeinfo" />:
<%
} else {
%>
          &nbsp;&nbsp;<fmt:message  key="technicianadd.instruction.addinfo" />:
<%
}
%>
           </strong></td>
      	</tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.techid"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="techid"  maxlength="32" size="71"
		onblur="if( !ScreenText(document.TechnicianForm.techid.value) ) { document.TechnicianForm.techid.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.password1"/>:</div></td>
          <td class="shaded_text">
            <html:password style="content_input" property="password1"  maxlength="32" size="72" 
		onblur="if( !ScreenText(document.TechnicianForm.password1.value) ) { document.TechnicianForm.password1.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.password2"/>:</div></td>
          <td class="shaded_text">
            <html:password style="content_input" property="password2"  maxlength="32" size="72" 
		onblur="if( !ScreenText(document.TechnicianForm.password2.value) ) { document.TechnicianForm.password2.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.name"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="name"  maxlength="64" size="71"
		onblur="if( !ScreenText(document.TechnicianForm.name.value) ) { document.TechnicianForm.name.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.email"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="email"  maxlength="128" size="71"
		onblur="if( !ScreenText(document.TechnicianForm.email.value) ) { document.TechnicianForm.email.focus(); return false;} 
			if( !ValidEmail(document.TechnicianForm.email.value) ) { document.TechnicianForm.email.focus(); return false;}" 
	     />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.phone"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="phone"  maxlength="32" size="71"
		onblur="if( !ScreenText(document.TechnicianForm.phone.value) ) { document.TechnicianForm.phone.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.misc"/>:</div></td>
          <td class="shaded_text">
            <html:textarea style="content_input" property="misc" cols="55" rows="7" 
		onblur="if( !ScreenText(document.TechnicianForm.misc.value) ) { document.TechnicianForm.misc.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.status"/>:</div></td>
          <td class="shaded_text">
	    <input type="radio" name="activeflags" value="Y" checked><fmt:message key="technician.label.active"/>
	    <input type="radio" name="activeflags" value="N"><fmt:message key="technician.label.inactive"/>
          </td>
        </tr>
	<tr>
 	  <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.type"/>:</div></td>
          <td class="shaded_text">
          <select name="accessname" size="1">

                <fmt:message>
                  technicianType.selected.0
                </fmt:message>
<%
if( !isDedicated && ( isCoAdmin || isSiteAdmin ) ) {
%>
                <fmt:message>
                  technicianType.1
                </fmt:message>
<%
}
if( !isDedicated && isSiteAdmin ) {
%>
                <fmt:message>
                  technicianType.2
                </fmt:message>
<%
}
%>

  	  </td>
	</tr>
        <tr align="middle">
          <td colspan="2">
            <input type="submit" name="add" class="btnLogin"
	onClick="if(!EnsureText(techid.value, '<fmt:message key="error.needsdata"><fmt:param><fmt:message key="technician.label.techid"/></fmt:param></fmt:message>')) {document.TechnicianForm.techid.focus(); return false;} if(!ValidPassword(password1.value, password2.value)) { document.TechnicianForm.password1.focus(); return false;}"
		value='<fmt:message key="technicianadd.button.submit"/>'>&nbsp;&nbsp;
            <button type="button" name="cancel" class="btnLogin" onclick="window.location='/RAssist/protected/showMainPage.do'"> 
		<fmt:message key="technicianadd.button.cancel"/>
	    <input type="reset" class="btnLogin" value='<fmt:message key="technician.button.reset"/>'>
          </td>
        </tr>
     </table>
   </html:form>
  </td>
 </tr>
</table>


