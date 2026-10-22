<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<html>

<HEAD>
  <title><tiles:getAsString name="title"/></title>
  <LINK REL=StyleSheet HREF='<fmt:message key="size.stylesheet"/>' TYPE="text/css">
  <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">



</HEAD>

<body class="shaded_text" onLoad="setTitle();">
  <tiles:insert attribute="pageErrors" />
  <table border="0" width="100%" cellspacing="0">
    <tr>
      <td valign="top"  align="left">
        <tiles:insert attribute='body' />
      </td>
    </tr>
  </table>

</body>

</html>
