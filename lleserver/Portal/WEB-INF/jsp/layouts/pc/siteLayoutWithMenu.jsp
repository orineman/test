<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<HTML>
  <HEAD>
    <title><fmt:message  bundle="${c4p_resellerBundle}" key="title.mainWindowTitle"/></title>
	<LINK REL=StyleSheet HREF='<fmt:message key="size.stylesheet"/>' TYPE="text/css">
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
</HEAD>
<script language="javascript">
<%-- this method can be overloaded by the desktop-generated pages --%>
function OnLoadBasic(){
}
</script>
<body  class="basicpage" onload="return OnLoadBasic();">

<table class="basicpage"  id="basicpageID" width="100%" cellspacing="0" cellpadding="0">
<tr>
  <td colspan="2">
  <!-- header start -->
  <tiles:insert attribute="header" />
  <!-- header stop -->
  </td>
</tr>
<tr>
  <td class="body_bar" valign="top">
	  <!-- menu start -->
    <tiles:insert attribute='menu'/>
  	<!-- menu stop -->
  </td>
  <td class="basic_section" width="100%" valign="top" align="left">
    <table width="100%">
      <tr>
        <td colspan="2">
				  <!-- pageErrors start -->
        	<tiles:insert attribute="pageErrors" />
				  <!-- pageErrors stop -->
        </td>
      </tr>
      <tr>
        <td>
				  <!-- body start -->
          <tiles:insert attribute='body' />
          <!-- body stop -->
        </td>
      </tr>
    </table>
  </td>
</tr>
<tr class="footer_bar" >
	<td colspan="3" width="100%">
		<!-- footer start -->
		<tiles:insert attribute="footer" />
		<!-- footer stop -->
	</td>
</tr>
</table>
</body>
</html>

