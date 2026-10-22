<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" language="JavaScript">
</script>

<html:form action="tsAdminRevokeLicense" method="post" >
      <input type="hidden" name="reseller" value="laplink" >
      <input type="hidden" name="data" value='<bean:write name="licensedata" property="data" scope="request"/>' >
      <input type="hidden" name="type" value="statusc" >
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>Please Confirm:</b> <br>&nbsp; cancelling license '<bean:write name="licensedata" property="data" scope="request"/>'
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <INPUT TYPE="submit" name="statusc" value=" Proceed " />
	    &nbsp; &nbsp; &nbsp;
	    <a href=tekSupport.do>Abort cancel</a>
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
