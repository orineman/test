
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ page import="com.infowave.c4p.web.actions.teksupport.DesktopForm" %>
<%@ page import="com.infowave.c4p.model.ICM" %>

<%
ICM icm = ICM.findRCICM();
String icmname = icm.getHostname();
String protocol = icm.getProtocol();
%>

<script type="text/javascript" language="JavaScript">
<!--
function setTitle(){
<!-- document.title='<fmt:message  bundle="${c4p_resellerBundle}" key="title.pcConfiguration"/>'; -->
document.title='<fmt:message key="title.vnc"/>';
}

function cancel(){
this.close();
}
// -->
</script>

</td></tr></table>


<iframe src='<%=protocol %>://<%=icmname %>/did=<bean:write name="desktopinfo" property="did" scope="request" />/' width='100%' height='95%'>
<fmt:message key="msg.vncdownload"/>
<TABLE><TR><TD width='100%'><a href='<%=protocol %>//<%=icmname %>/did=<bean:write name="desktopinfo" property="did" scope="request" />/'>
<fmt:message key="msg.vncstart"/>
</a></TD></TR></TABLE>
</iframe>



<table><tr><td>
</body>

