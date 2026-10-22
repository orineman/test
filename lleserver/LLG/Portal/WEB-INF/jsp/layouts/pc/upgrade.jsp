<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>

<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>



<HTML>

  <HEAD>

    <title><fmt:message  bundle="${c4p_resellerBundle}" key="title.mainWindowTitle"/></title>

	<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="max-age" CONTENT="0">
<META HTTP-EQUIV="Expires" CONTENT="0">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
  response.setHeader ("Content-Type", "text/html; charset=UTF-8");
%>

<%


  response.sendRedirect("/x-res/upgrade.htm");
%>
  </HEAD>


<body >
main page redirector

</body>

</html>



