<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/c4.tld" prefix="c4" %>
<script type="text/javascript" language="JavaScript">

function setTitle(){
document.title='<fmt:message  bundle="${c4p_resellerBundle}" key="title.subscripion"/>';

}

function cancel(){
this.close();
}


</script>


      <html:form action="/protected/unlicenseDesktop" method="post">
      <input type="hidden" name="did" value='<c:out value="${param.did}"/>' >

      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center">
            <b>
             <fmt:message key="msg.unlicenseConfirmationQuestion"/>
            </font>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td align="center">
            <input type="submit" name="sub,it" value='<fmt:message key="action.submit"/>'/>&nbsp;
            <input type="button" onClick="cancel()" value='<fmt:message key="action.cancel"/>'/>
          </td>
        </tr>
      </table>
    </html:form>
