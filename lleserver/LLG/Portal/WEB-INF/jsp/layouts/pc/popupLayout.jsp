<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<html>

<HEAD>
  <title><tiles:getAsString name="title"/></title>
  <LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">



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