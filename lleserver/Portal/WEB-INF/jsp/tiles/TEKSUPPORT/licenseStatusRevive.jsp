<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" language="JavaScript">
</script>
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
	  <td><b>Status of license reset code <bean:write name="licensedata" property="data" scope="request"/>:</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
	  <td><bean:write name="licensedata" property="results" scope="request"/></td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
	  <td>
	  <a href=tekSupport.do>Okay</a>
	  </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
      </table>
