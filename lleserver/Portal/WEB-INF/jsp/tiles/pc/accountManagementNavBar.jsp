<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<!-- left tool strip -->
  <table class="body_bar" cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr>
				<td valign="top">
					<!-- this table	defines the toolbar -->
					<table class="body_bar" cellspacing="0" cellpadding="2" width="100%" border="0">
    		    <tr>
				      <td valign="middle"><img src="x-res/BULLET.GIF" hspace="2"></td>
				      <td>
                        <a class="left_body" href="/protected/showAccountManagementPage.do">
                          <fmt:message key="action.EditAccountInformation"/>
                        </a>
                      </td>
				    </tr>
				    <tr>
				      <td valign="middle"><img src="x-res/BULLET.GIF" hspace="2"></td>
				      <td>
                        <a class="left_body" href="/protected/showAccountManagementPage.do?section=password">
                          <fmt:message key="action.changePassword"/>
                        </a>
                      </td>
				    </tr>
				    <tr>
				      <td valign="middle"><img src="x-res/BULLET.GIF" hspace="2"></td>
				      <td>
                        <a class="left_body" href="/protected/showAccountManagementPage.do?section=security">
                          <fmt:message key="action.changeSecurityQuestion"/>
                        </a>
                      </td>
				    </tr>
					</table>
				</Td>
			</tr>
</table>
			<!-- end left tool strip -->
