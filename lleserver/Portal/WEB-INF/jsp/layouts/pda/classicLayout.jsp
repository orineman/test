<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>



<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>







<HTML>



  <HEAD>

    <title><fmt:message  bundle="${c4p_resellerBundle}" key="title.mainWindowTitle"/></title>

	<LINK REL=StyleSheet HREF="x-res/pda_style.css" TYPE="text/css">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="max-age" CONTENT="0">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	

    <META HTTP-EQUIV="HandheldFriendly" CONTENT="True">
  </HEAD>


<script language="javascript">
<%-- this method can be overloaded by the desktop-generated pages --%>
function OnLoadBasic(){
}
</script>




<body  class="window"  onload="return OnLoadBasic();">



  <!-- header start -->



  <tiles:insert attribute="header" />



  <!-- header stop -->



  <!-- pageErrors start -->



    <tiles:insert attribute="pageErrors" />



  <!-- pageErrors stop -->



  <!-- body start -->



    <tiles:insert attribute='body' />



  <!-- body stop -->



  <!-- footer start -->



  <tiles:insert attribute="footer" />



  <!-- footer stop -->



</body>



</html>







