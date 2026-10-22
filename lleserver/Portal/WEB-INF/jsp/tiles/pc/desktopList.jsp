<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>

<SCRIPT TYPE='text/javascript'>
    <!--\r\n 
    function popup(mylink)
    { if (! window.focus)return true;
	var href;
	if (typeof(mylink) == 'string')
	   href=mylink;
	else
	   href=mylink.href;
	window.open(href,'', 'height=600, width=860, left=0, top=0, location=no, menubar=no, status=yes, toolbar=no, scrollbars=yes, resizable=yes');
	return false;
	} //-->
</SCRIPT>


  <table class="body_bar" cellspacing="0" cellpadding="0" width="100%" border="0">

    <c:if test="${empty infowave_sessionState.desktops}">
      <span class="toolbar_heading">
      <fmt:message key="msg.noDesktopsAvail"/>
      </span>
    </c:if>

    <c:forEach items="${infowave_sessionState.desktops}" var="desktop">

        <c:if test="${desktop.visible}">
			<tr>
				<td >
				<table background="x-res/PCGRADIENT.GIF" id="PcUserNameTable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
				   <td width="35" height="35">

		            <c:if test="${desktop.connected}">
		              <img height="35" src='x-devicon?did=<c:out value="${desktop.did}"/>'/>
		            </c:if>
		            <c:if test="${!desktop.connected}">
		              <img height="35" src='x-devicon?did=<c:out value="${desktop.did}"/>'/>
		            </c:if>
					</td>

	            	<td nowrap><p style="line-height:75%;"><span class="toolbar_heading"><fmt:message key="field.user"/>:</span>
						<span class="toolbar_text"><c:out value="${desktop.htmlencodedOwner}"/></span>
						<br>

            		<c:if test="${desktop.connected && !desktop.hasExpired}">
				<c:url value="/protected/showDesktopPage.do" var="desktopUrl">
			       <c:param name="did" value="${desktop.did}"/>
			       </c:url>
				<a class="toolbar_text" href='<c:out value="${desktopUrl}"/>' onClick="return popup(this)"><c:out value="${desktop.htmlencodedName}"/></a>
            		</c:if>
            		<c:if test="${!desktop.connected && !desktop.hasExpired}">
            			<c:if test="${desktop.rdpEnabled}">
              				<c:url value="/protected/showRdpServicePage.do" var="desktopUrl">
			                <c:param name="did" value="${desktop.did}"/>
			                <c:param name="name" value="${desktop.htmlencodedName}"/>
			                </c:url>
					<a class="toolbar_text" href='<c:out value="${desktopUrl}"/>' onClick="return popup(this)"><c:out value="${desktop.htmlencodedName}"/></a>
            			</c:if>
            			<c:if test="${!desktop.rdpEnabled}">
			 		<span class="toolbar_text"><c:out value="${desktop.htmlencodedName}"/></span>
            			</c:if>

            		</c:if>

           		<c:if test="${desktop.hasExpired}">
				 <span class="toolbar_text"><c:out value="${desktop.htmlencodedName}"/></span>
            		</c:if>

            		</p></td>
            	</tr>
			</table>
			</td>
		</tr>


            <c:if test="${desktop.hasExpired || (desktop.daysToExpiry>0 && desktop.daysToExpiry<16)}">
	    <tr><td background="x-res/GRADIENT_2.GIF">
			<TABLE width="100%" align="center" border="0">
			<tr><td align="middle">
              <c:if test="${infowave_sessionState.userDTO!=NULL && desktop.owner==infowave_sessionState.userDTO.username}">
                <a class='device_warning' href="<fmt:message key="msg.link.plans"/>" target="_blank">
              </c:if>

              <span class='device_warning'>
              <c:if test="${desktop.hasExpired}">

                <fmt:message key="msg.desktopHasExpired"/>

              </c:if>

              <c:if test="${!desktop.hasExpired && desktop.daysToExpiry>0}">

                <fmt:message key="msg.daysToDesktopExpiryDate">

                  <fmt:param value="${desktop.daysToExpiry}"/>

                </fmt:message>

              </c:if>
              </span>

              <c:if test="${infowave_sessionState.userDTO!=NULL && desktop.owner==infowave_sessionState.userDTO.username}">
                </a>
              </c:if>
			  </td></tr></table>
        </td></tr>

            </c:if>

        </c:if>






    </c:forEach>



	</table>
