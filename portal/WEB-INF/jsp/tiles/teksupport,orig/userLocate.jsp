<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<script type="text/javascript" language="JavaScript">
</script>

<html:form action="tsAdminSearchUser" method="post" >
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <font size="+1">Search For Account</font>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>Type in your search string:</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
          <input type="text" style="content_input" size="55" maxlength="255" name="data" value=""/>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
		Hint: use % for wildcards such as %john% to return strings like j_john, John, Johnny, Johnston
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>What type of information is this?</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="accountname" CHECKED>Account Name
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="email">E-mail
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="firstname">First Name
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <INPUT TYPE="radio" NAME="type" VALUE="lastname">Last Name
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <INPUT TYPE="submit" name="save" value=" Locate " />
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
      </table>
</html:form>
