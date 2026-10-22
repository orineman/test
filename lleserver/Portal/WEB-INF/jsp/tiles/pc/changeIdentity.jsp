<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.infowave.c4p.web.util.SessionState" %>
<%@ page import="com.infowave.c4p.web.util.WebUtil" %>
<%@ page import="com.infowave.c4p.service.dto.UserDTO" %>


<%
SessionState state = WebUtil.getSessionState(request);
UserDTO user = state.getUserDTO();
String jtemp = user.getFirstName();
String firstname;
if (jtemp != null)
        firstname = new String(jtemp.getBytes("8859_1"), "UTF-8");
else
        firstname = new String("");
jtemp = user.getLastName();
String lastname;
if (jtemp != null)
        lastname = new String(jtemp.getBytes("8859_1"), "UTF-8");
else
        lastname = new String("");
jtemp = user.getUsername();
String username = new String(jtemp.getBytes("8859_1"), "UTF-8");
jtemp = user.getEmail();
String email = new String(jtemp.getBytes("8859_1"), "UTF-8");
%>

  <br>
  <html:form action="changeIdentity" method="post" >
    <table width="95%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" class="content_title" valign="middle" background="x-res/greygradient.gif"><fmt:message key="title.changeAccountInfo"/></td>
      </tr>
      <tr>
        <td class="shaded_header" colspan="2" background="x-res/GRADIENT_1.GIF"><fmt:message key="title.manageProfile"/></td>
      </tr>
      <tr>
        <td class="shaded_text" colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td class="shaded_caption"><fmt:message key="field.username"/>:</td>
        <td class="shaded_text"><%=username %></td>
      </tr>
      <tr>
        <td class="shaded_caption"><fmt:message key="field.firstName"/>:</td>
        <td class="shaded_text">
          <input type="text" style="content_input" size="40" maxlength="50" name="firstName"
            value='<%=firstname %>'/>
        </td>
      </tr>
      <tr>
        <td class="shaded_caption"><fmt:message key="field.lastName"/>:</td>
        <td class="shaded_text">
          <input type="text" style="content_input" size="40" maxlength="50" name="lastName"
            value='<%=lastname %>'/>
        </td>
      </tr>
      <tr>
        <td class="shaded_caption"><fmt:message key="field.emailAddress"/>:</td>
        <td class="shaded_text">
          <input type="text" style="content_input" size="40" maxlength="64" name="email"
            value='<%=email %>'/>

        </td>
      </tr>
      <tr>
          <td class="shaded_caption" valign="top"><fmt:message key="field.language"/></td>
          <td class="shaded_text">
          <select name="locale" size="1">
          <c:forEach begin="0" end="6" var="languageQuestionIdx">
                <fmt:message>
                  language.<c:out value="${languageQuestionIdx}"/>
                </fmt:message>
          </c:forEach>
          </td>
      </tr>


      <tr>
        <td class="shaded_caption"></td>
        <td class="shaded_text" >
           <input type="submit" name="submit" value='<fmt:message key="action.save"/>'/></td>
          </td>
      </tr>
    </table>
  </html:form>
<br>
<form>
  <table width="95%" border="0" cellpadding="0" cellspacing="0">
    <tr class="shaded_header">
      <td colspan="2" background="x-res/GRADIENT_1.GIF"><fmt:message key="msg.yourPCs"/></td>
      <td colspan="11" align="right" background="x-res/GRADIENT_1.GIF">
      </td>
    </tr>
    <tr align="center" class="shaded_heading">
    <td><fmt:message key="field.pcName"/></td>
    <td>&nbsp;</td>
    <td><fmt:message key="msg.settings"/></td>
    <td bgcolor=#EDEDED><fmt:message key="field.license"/></td>
    <td bgcolor=#EDEDED><fmt:message key="field.expiryDate"/></td>
    <td bgcolor=#EDEDED colspan="2" align="center"><fmt:message key="msg.pcLicenses"/></td>
    <td><fmt:message key="field.rclicense"/></td>
    <td><fmt:message key="field.rcexpiryDate"/></td>
    <td align="center"><fmt:message key="msg.pcRCLicenses"/></td>
    <td bgcolor=#EDEDED><fmt:message key="field.enabled"/></td>
    <td bgcolor=#EDEDED><fmt:message key="field.softwareVersion"/></td>
    </tr>
    <c:forEach items="${infowave_sessionState.userDesktops}" var="desktop">


    <tr align="center" class="shaded_text">
      <td><c:out value="${desktop.htmlencodedName}"/></td>
      <td>&nbsp;</td>
      <td>
        <c:url value="/forward.do" var="editDesktopUrl">
  	      <c:param name="page" value="editDesktop"/>
          <c:param name="did" value="${desktop.did}"/>
          <c:param name="visible" value="${desktop.visible}"/>
          <c:param name="licensed" value="${desktop.licensed}"/>
          <c:param name="rclicensed" value="${desktop.rclicensed}"/>
          <c:param name="name" value="${desktop.htmlencodedName}"/> 
        </c:url>
        <c:url value="/protected/showEnterLicenseCodePage.do" var="enterLicenseCodeUrl">
          <c:param name="did" value="${desktop.did}"/>
        </c:url>
        <c:url value="/protected/forward.do" var="unlicenseDeskopUrl">
          <c:param name="page" value="unlicenseDesktop"/>
          <c:param name="did" value="${desktop.did}"/>
        </c:url>

        <input type="button" value='<fmt:message key="action.editDesktop"/>'
           onClick="devEdit = window.open('<c:out value="${editDesktopUrl}"/>','Edit','scrollbars=no,resizable=no,width=400,height=130');if (devEdit.opener == null) devEdit.opener = self;return false">
      </td>
 
      <td bgcolor=#EDEDED>
      <c:choose>
        <c:when test="${desktop.licenseSerialNumber == null}">
        <fmt:message key="msg.unsubscribed"/>
        </c:when>
        <c:when test="${desktop.licenseSerialNumber != null}">
          <fmt:message key="msg.subscribed"/>
       </c:when>
      </c:choose>
      </td>
      <td bgcolor=#EDEDED>
        <c:choose>
          <c:when test="${desktop.expiryDate == null}">
            &nbsp;
          </c:when>
          <c:when test="${desktop.expiryDate != null}">
            <fmt:formatDate value="${desktop.expiryDate}"/>
          </c:when>
        </c:choose>
     </td>
		<td bgcolor=#EDEDED>
		<c:choose>
		  <c:when test="${!desktop.licensed}">
		<input type="button" value='<fmt:message key="action.setLicense"/>' onClick="devReg = window.open('<c:out value="${enterLicenseCodeUrl}"/>','License','scrollbars=no,resizable=no,width=400,height=220');if (devReg.opener == null) devReg.opener = self;return false">
		  </c:when>
		  <c:when test="${desktop.licensed}">
		  <input type="button" value='<fmt:message key="action.unlicense"/>' onClick="devReg = window.open('<c:out value="${unlicenseDeskopUrl}"/>','Unlicense','scrollbars=no,resizable=no,width=400,height=180');if (devReg.opener == null) devReg.opener = self;return false">
		  </c:when>
		</c:choose>
		</td>


		<td bgcolor=#EDEDED>
		<c:url value="/protected/forward.do" var="rcunlicenseDeskopUrl">
		  <c:param name="page" value="rcunlicenseDesktop"/>
		  <c:param name="did" value="${desktop.did}"/>
		</c:url>
		<c:url value="/protected/showEnterRCLicenseCodePage.do" var="enterRCLicenseCodeUrl">
		  <c:param name="did" value="${desktop.did}"/>
		</c:url>
		    &nbsp;
		</td>
      <td>
      <c:choose>
        <c:when test="${desktop.rclicenseSerialNumber == null}">
        <fmt:message key="msg.rcunsubscribed"/>
        </c:when>
        <c:when test="${desktop.rclicenseSerialNumber != null}">
          <fmt:message key="msg.rcsubscribed"/>
       </c:when>
      </c:choose>
      </td>
      <td>
        <c:choose>
          <c:when test="${desktop.rcexpiryDate == null}">
            &nbsp;
          </c:when>
          <c:when test="${desktop.rcexpiryDate != null}">
            <fmt:formatDate value="${desktop.rcexpiryDate}"/>
          </c:when>
        </c:choose>
     </td>

	    <td>
	    <c:choose>
	      <c:when test="${!desktop.rclicensed}">
	    <input type="button" value='<fmt:message key="action.rcsetLicense"/>' onClick="devReg = window.open('<c:out value="${enterRCLicenseCodeUrl}"/>','License','scrollbars=no,resizable=no,width=400,height=220');if (devReg.opener == null) devReg.opener = self;return false">
	      </c:when>
	      <c:when test="${desktop.rclicensed}">
         <input type="button" value='<fmt:message key="action.rcunlicense"/>' onClick="devReg = window.open('<c:out value="${rcunlicenseDeskopUrl}"/>','Unlicense','scrollbars=no,resizable=no,width=400,height=180');if (devReg.opener == null) devReg.opener = self;return false">
	      </c:when>
	    </c:choose>
	    </td>

      <td bgcolor=#EDEDED>
      <c:choose>
        <c:when test="${desktop.visible}">
          <fmt:message key="msg.yes"/>
        </c:when>
        <c:when test="${!desktop.visible}">
          <fmt:message key="msg.no"/>
       </c:when>
     </c:choose>
      </td>
      <td bgcolor=#EDEDED>
      <c:choose>
        <c:when test="${desktop.softwareVersion != null}">
          <c:out value="${desktop.softwareVersion}"/>
        </c:when>
        <c:otherwise>
          <fmt:message key="msg.notAvailableAbbreviation"/>
        </c:otherwise>
      </c:choose>
      </td>

    </tr>
   </c:forEach>

  </table>
</form>


