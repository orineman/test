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
String companyId = form.getCompanyid();
String companyName = form.getCompanyname();
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
} else if ( count == 0 ) {
%>
  <tr>
     <td>&nbsp;</td>
     <td>
	<font size="+1"><fmt:message key="companyresultsedit.header.None">
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
  <tr>
     <td>&nbsp;</td>
  </tr>
<%
if( count > 1 ) {
%>
  <tr><td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="companyresultsedit.header.Multi">
              <fmt:param><bean:write name="companydata" property="type" scope="request" /></fmt:param>
              <fmt:param><%=name %></fmt:param>
        </fmt:message></font>
     </td></tr>
<%
} else {
%>
  <tr><td>
	<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="companyresultsedit.header.Single">
              <fmt:param><%=companyName %></fmt:param>
        </fmt:message></font>
     </td></tr>
<%
}
%>
<%
// If more than one result comes up, add a message to indicate how to get to the edit screen
if( count > 1 ) {
%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message  key="companyresultsedit.instruction.editlink" />
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
  <jsp:param name="linkfmt" value="/RAssist/protected/SearchForCompany.do?type=companyid&nextaction=edit&data="/>  
</jsp:include>
</td></tr>

<%
// Add the fields to edit the item if only one is selected
List list = form.getCompanyList();
CompanyDTO company = (CompanyDTO)list.get(0);
String coName = company.getHtmlencodedName();
String coXref = company.getHtmlencodedXref();
String coMiscinfo1 = company.getHtmlencodedMiscinfo1();
String coMiscinfo2 = company.getHtmlencodedMiscinfo2();
String coMaxconns = company.getMaxconnsstr();
if( count == 1 ) {
%>
  <tr><td>&nbsp;</td></tr>
 <tr>
  <td>
   <table class="shaded_text" border="0" cellspacing="0" cellpadding="0" width="75%" align="center" >
   <tr><td >
   <html:form action="/protected/updateCompany" method="post" onreset="name.focus()">
     <input type="hidden" name="companyid" value='<%=companyId %>' >
     <input type="hidden" name="nextaction" value="update" >
     <table class="shaded_text" border="5" cellspacing="0" width="85%" align="center" cellpadding="0">
 	<tr>
          <td colspan="2" class="shaded_text"><strong>&nbsp;&nbsp;<fmt:message  key="company.label.editdata" />:</strong></td>
      	</tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.companyname"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="name"  maxlength="255" size="90" value='<%=coName %>' />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.xref"/>:</div></td>
          <td class="shaded_text">
	    <html:text style="content_input" property="sn" maxlength="256" size="90" value='<%=coXref %>' />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.miscinfo1"/>:</div></td>
          <td class="shaded_text">
	    <html:textarea style="content_input" property="miscinfo1" cols="54" rows="4" value='<%=coMiscinfo1 %>' />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.miscinfo2"/>:</div></td>
          <td class="shaded_text">
	    <html:textarea style="content_input" property="miscinfo2" cols="54" rows="4" value='<%=coMiscinfo2 %>' />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.maxlogins"/>:</div></td>
          <td class="shaded_text">
            <html:text style="content_input" property="maxconns"  maxlength="4" size="4" value='<%=coMaxconns %>' 
		onblur="if(!ValidNumber(maxconns.value)) { document.CompanyForm.maxconns.focus(); return false;}" />
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.licensetype"/>:</div></td>
          <td class="shaded_text">
<%
String licenseFlag = company.getLicensetypeflag();
if( licenseFlag.equals( "D" ) ) {
%>
	    <input type="radio" name="licenseType" value="D" checked><fmt:message key="company.label.licensetype_dedicated"/>
	    <input type="radio" name="licenseType" value="C"><fmt:message key="company.label.licensetype_concurrent"/>
<%
} else {
%>
	    <input type="radio" name="licenseType" value="D"><fmt:message key="company.label.licensetype_dedicated"/>
	    <input type="radio" name="licenseType" value="C" checked><fmt:message key="company.label.licensetype_concurrent"/>
<%
}
%>
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" nowrap><div align="right"><fmt:message key="company.label.status"/>:</div></td>
          <td class="shaded_text">
<%
String activeFlag = company.getActiveflag();
if( activeFlag.equals( "Y" ) ) {
%>
	    <input type="radio" name="activeflags" value="Y" checked><fmt:message key="company.label.active"/>
	    <input type="radio" name="activeflags" value="N"><fmt:message key="company.label.inactive"/>
<%
} else {
%>
	    <input type="radio" name="activeflags" value="Y"><fmt:message key="company.label.active"/>
	    <input type="radio" name="activeflags" value="N" checked><fmt:message key="company.label.inactive"/>
<%
}
%>
          </td>
        </tr>
        <tr>
          <td class="shaded_caption" width="15%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="company.label.language"/>:</td>
          <td class="shaded_text">
          <select name="locale" size="1">
          <c:forEach begin="0" end="1" var="languageQuestionIdx">
                <fmt:message>
                  language.<c:out value="${languageQuestionIdx}"/>
                </fmt:message>
          </c:forEach>
          </td>
        </tr>
        <tr align="left">
          <td colspan="2">
            <input type="submit" name="update" class="btnLogin" value='<fmt:message key="companyresultsedit.button.submit"/>'>&nbsp;&nbsp;
            <button type="button" name="cancel" class="btnLogin" onclick="window.location='/RAssist/protected/showMainPage.do'">
                <fmt:message key="companyresultsedit.button.cancel"/>
	    <input type="reset" class="btnLogin" value='<fmt:message key="company.button.reset"/>'>
          </td>
        </tr>


     </table>
   </html:form>
  </td>
 </tr>
</table>
</td></tr>

<%
}
}
%>
    <tr>
      <td height="29"  background="x-res/fade.jpg" bgcolor="#e8e8e8" class="style4" ></td>
    </tr>
</table>
