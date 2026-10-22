<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>
<%@ page import="com.infowave.c4p.service.dto.TechnicianDTO" %>



<%
String attop = request.getParameter("attop");
boolean isAtMainMenu = false;
if( attop != null ) {
  isAtMainMenu = true;
}

SessionState state = WebUtil.getSessionState(request);
TechnicianDTO tech = state.getTechnicianDTO();
String jtemp = "";
String techid = "";
String status = "null";
if( tech != null ) {
        techid = tech.getTechid();
        //techid = new String(jtemp.getBytes("8859_1"), "UTF-8");
}
%>

  <c:if test="${laplink_sessionState.technicianDTO != null}">
	<TABLE class="header_bar" cellspacing="0" cellpadding="0" width="95%" id="HeaderBar">
          <TR>
            <td width="2%" class="style4">&nbsp;</td>
<%
if( isAtMainMenu ) {
%>
            <td height="21"><div align="right"><span class="style4"><fmt:message key="header.home"/></a></span> </div></td>
            <td height="21"><div align="right"><span class="style4"><fmt:message key="header.back"/></a></span> </div></td>
<%
} else {
%>
            <td height="21"><div align="right"><span class="style4"><a href="/RAssist/protected/showMainPage.do" class="style4"><fmt:message key="header.home"/></a></span> </div></td>
            <td height="21"><div align="right"><span class="style4"><a href="javascript:history.back();" class="style4"><fmt:message key="header.back"/></a></span> </div></td>
<%
} 
%>
            <td width="70%" class="style4">&nbsp;</td>
            <td height="21"><div align="left"><span class="style4"><a href="/RAssist/logout.do" class="style4"><fmt:message key="header.logout"/></a></span> </div></td>
        </tr>
        </table>
  </c:if>

