<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.CompanyForm" %>
<%@ page import="com.infowave.c4p.service.dto.CompanyDTO" %>
<%@ page import="com.infowave.c4p.model.Company" %>
<%@ page import="java.util.List" %>



<jsp:include page="../header.jsp" />

<%
boolean fromError = true;
CompanyForm form = (CompanyForm)request.getAttribute("companydata");
if( form == null ) {
  // initial add
  fromError = false;
}

%>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td height="130" colspan="1" background="x-res/filing_cabinet.jpg"></td>
    </tr>
<%
if( fromError ) {
%>
  <c:forEach items="${requestScope.c4p_errors}" var="error">
    <tr><td>
        <font color="red" size="+1"><strong>&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="error.error" />&nbsp;<fmt:message key="${error.messageKey}" /></strong></font>
    </td></tr>
  </c:forEach>
<%
}
%>
 <tr>
  <td>
   <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
   <tr><td >
   <html:form action="/protected/updateCompany" method="post" onreset="name.focus()">
     <input type="hidden" name="nextaction" value="add" >
     <table class="shaded_text" border="5" cellspacing="0" width="80%" align="center" cellpadding="0">
 	<tr>
          <td colspan="2" class="shaded_text"><strong>
<%
if( fromError ) {
%>
	&nbsp;&nbsp;<fmt:message  key="companyadd.instruction.changeinfo" />:
<%
} else {
%>
	&nbsp;&nbsp;<fmt:message  key="companyadd.instruction.addinfo" />:
<%
} 
%>
	  </strong></td>
      	</tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.companyid"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="companyid"  maxlength="64" size="70" 
                onblur="if( !ScreenText(document.CompanyForm.companyid.value) ) { document.CompanyForm.companyid.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.companyname"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="name"  maxlength="256" size="70" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.xref"/>:</div></td>
          <td class="shaded_text">
	    <html:text style="content_input" property="sn" maxlength="256" size="70" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.miscinfo1"/>:</div></td>
          <td class="shaded_text">
	    <html:textarea style="content_input" property="miscinfo1" cols="54" rows="4" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.miscinfo2"/>:</div></td>
          <td class="shaded_text">
	    <html:textarea style="content_input" property="miscinfo2" cols="54" rows="4" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.maxlogins"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="maxconns"  maxlength="4" size="4" 
		onblur="if(!ValidNumber(maxconns.value)) { document.CompanyForm.maxconns.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.status"/>:</div></td>
          <td class="shaded_text">
	    <input type="radio" name="activeflags" value="Y" checked><fmt:message key="company.label.active"/>
	    <input type="radio" name="activeflags" value="N"><fmt:message key="company.label.inactive"/>
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.licensetype"/>:</div></td>
          <td class="shaded_text">
	    <input type="radio" name="licenseType" value="D" checked><fmt:message key="company.label.licensetype_dedicated"/>
	    <input type="radio" name="licenseType" value="C"><fmt:message key="company.label.licensetype_concurrent"/>
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.language"/>:</div></td>
          <td class="shaded_text">
          <select name="locale" size="1">
          <c:forEach begin="0" end="1" var="languageQuestionIdx">
                <fmt:message>
                  language.<c:out value="${languageQuestionIdx}"/>
                </fmt:message>
          </c:forEach>
          </td>
        </tr>
        <tr align="middle">
          <td>
            <input type="submit" class="btnLogin" name="add" 
 		onClick="if(!EnsureText(companyid.value, '<fmt:message key="error.needsdata"><fmt:param><fmt:message key="company.label.companyid"/></fmt:param></fmt:message>')) {document.CompanyForm.companyid.focus(); return false;} if(!ValidNumber(maxconns.value)) { document.CompanyForm.maxconns.focus(); return false;}"

		value='<fmt:message key="companyadd.button.submit"/>'>&nbsp;&nbsp;
            <button type="button" class="btnLogin" name="cancel" onclick="window.location='/RAssist/protected/showMainPage.do'">
                <fmt:message key="companyadd.button.cancel"/>
	    <input type="reset" class="btnLogin" value='<fmt:message key="company.button.reset"/>'>
          </td>
        </tr>


     </table>
   </html:form>
   </td></tr>
   </table>
  </td>
 </tr>

</table>


