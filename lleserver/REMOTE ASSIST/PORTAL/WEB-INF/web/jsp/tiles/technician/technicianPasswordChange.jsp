<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ page import="com.infowave.c4p.web.actions.TechnicianForm" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>
<%@ page import="com.infowave.c4p.model.Technician" %>
<%@ page import="java.util.List" %>



<jsp:include page="../header.jsp" />

<table class="shaded_text" width="600" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>      
       <td height="130" background="x-res/filing_cabinet.jpg"></td>
  </tr> 


<%
String jtemp = request.getParameter("data");
if( jtemp == null ) jtemp = "";
String techcid = new String(jtemp.getBytes("8859_1"), "UTF-8");
%>

  <c:forEach items="${requestScope.c4p_errors}" var="error">
    <tr><td>
	<font color="red" size="+1"><strong>&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="error.error" />&nbsp;<fmt:message key="${error.messageKey}" /></strong></font>
    </td></tr>
  </c:forEach>

   <html:form action="/protected/updateTechnician" method="post" >
     <input type="hidden" name="nextaction" value="pwd" >
     <input type="hidden" name="techcid" value='<%=techcid %>' >
   <tr><td >
     <table class="shaded_text" border="5" cellspacing="0" width="100%" align="center" cellpadding="0">
 	<tr>
          <td colspan="2" class="shaded_text"><strong>&nbsp;&nbsp;<fmt:message  key="technicianpasswordchange.instruction.addinfo" />:</strong></td>
      	</tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.password1"/>:</div></td>
          <td class="shaded_text">
            <html:password style="content_input" property="password1"  maxlength="32" size="70"
		 onblur="if( !ScreenText(document.TechnicianForm.password1.value) ) { document.TechnicianForm.password1.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="technician.label.password2"/>:</div></td>
          <td class="shaded_text">
            <html:password style="content_input" property="password2"  maxlength="32" size="70"
		 onblur="if( !ScreenText(document.TechnicianForm.password2.value) ) { document.TechnicianForm.password2.focus(); return false;}" />
          </td>
        </tr>
        <tr align="middle">
          <td colspan="2">
            <input type="submit" name="pwd" class="btnLogin" 
		onClick="if(!ValidPassword(password1.value, password2.value)) { document.TechnicianForm.password1.focus(); return false;}"
		value='<fmt:message key="technicianpasswordchange.button.submit"/>' 
		>&nbsp;&nbsp;
            <button type="button" name="cancel" class="btnLogin" onclick="window.location='/RAssist/protected/showMainPage.do'">
                <fmt:message key="technicianpasswordchange.button.cancel"/>
	    <input type="reset" class="btnLogin" value='<fmt:message key="technician.button.reset"/>'>
          </td>
        </tr>


     </table>
   </td></tr>
   </html:form>

</table>


