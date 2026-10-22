<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>




    <table class="siongtable" cellspacing="0" cellpadding="0">
        <tr>
          <td width="300" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SearchForTechnician.do?nextaction=listalltechnicians"><fmt:message key="techniciancompanyadminmenu.listitem.listtechnicians_companyadmin"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="300" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SetType.do?type=locatetechnician"><fmt:message key="techniciancompanyadminmenu.listitem.locate"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="300" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SetType.do?type=edittechnician"><fmt:message key="techniciancompanyadminmenu.listitem.edit"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="300" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SetType.do?type=addtechnician"><fmt:message key="techniciancompanyadminmenu.listitem.add"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="300" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SetType.do?type=deletetechnician"><fmt:message key="techniciancompanyadminmenu.listitem.delete"/></a>
          </p></td>
        </tr>
    </table>


