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


    <html:form action="protected/editDesktop" method="post">
      <input type="hidden" name="did" value='<c:out value="${param.did}"/>' >
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="shaded_caption"><fmt:message key="field.pcName"/>:</td>
          <td class="shaded_text">
            <input type="text" style="content_input" name="pcName" value='<c:out value="${param.name}"/>' />
          </td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
          <td>
            <c:if test="${param.visible}">
              <input type="checkbox" name="visible" CHECKED>
            </c:if>
            <c:if test="${not param.visible}">
              <input type="checkbox" name="visible">
            </c:if>
              &nbsp;<fmt:message  key="field.enablePC"/></input>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          <c:if test="${!param.licensed}">
            <input type="submit" name="remove" value='<fmt:message key="action.remove"/>'/>&nbsp;&nbsp;&nbsp;
          </c:if>
            <input type="submit" name="save" value='<fmt:message key="action.save"/>' />&nbsp;
            <input type="button" onClick="cancel()" value='<fmt:message key="action.cancel"/>'/>
          </td>
        </tr>
      </table>
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="3">
        <tr>
        <td>
        <c:if test="${param.licensed || param.rclicensed}">
          <fmt:message key="msg.removelicense"/>
        </c:if>
        </td>
        </tr>
      </table>
    </html:form>
