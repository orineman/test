
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<script type="text/javascript" language="JavaScript">
<!--
function setTitle(){
document.title='<fmt:message  bundle="${c4p_resellerBundle}" key="title.pcConfiguration"/>';
}

function cancel(){
this.close();
}
// -->
</script>

<html>
<BL>
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>What do you wish to locate?</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <LI><a class="content_link" href="/tsAdminSetType.do?type=user">User</a>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <LI><a class="content_link" href="/tsAdminSetType.do?type=desktop">Desktop</a>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
      </table>
</BL>
</html>

