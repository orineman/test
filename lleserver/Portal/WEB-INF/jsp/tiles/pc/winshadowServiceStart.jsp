
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ page import="com.infowave.c4p.web.actions.teksupport.DesktopForm" %>

<script type="text/javascript" language="JavaScript">
<!--
function setTitle(){
<!-- document.title='<fmt:message  bundle="${c4p_resellerBundle}" key="title.pcConfiguration"/>'; -->
document.title='<fmt:message key="title.winshadow"/>';
}

function cancel(){
this.close();
}
// -->
</script>

<HEAD><TITLE><fmt:message key="title.winshadow"/>
</TITLE>
</HEAD>
<BODY bgColor=white onload=checkWS();>

<script language='javascript' src='<fmt:message key="msg.winshadow.popcal"/>'></script>
<script language='javascript' src='<fmt:message key="msg.winshadow.ehandler"/>'></script>

<style type='text/css'>
a { font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none;}
.MenuToolbar { background-color: #336666; }
</style>


<DIV id=xp2 style="VISIBILITY: hidden; POSITION: absolute; TOP: 25px"><TABLE class=emailbody width=100%><TBODY><TR><td><img src=x-res/lle4-globe-waiting.gif></td><td><IMG style='POSITION: absolute; TOP: 15px; LEFT: 540px;' alt='<fmt:message key="msg.alt.yellowbar"/>' src='x-res/LOOKUPAX.GIF'></td><td class='left_body' style="POSITION: absolute; TOP: 30px; LEFT: 260px;">
<fmt:message key="msg.winshadow1"/>
</td></TR></TBODY></TABLE></DIV>

<DIV id="installing" style="VISIBILITY: visible; POSITION: absolute; TOP: 55px "><TABLE class="emailbody" width="100%"><TBODY><TR><td><img src="x-res/lle4-globe-waiting.gif"></td><TD class="left_body">
<fmt:message key="msg.winshadow2"/>
</TD><TD vAlign="bottom"></TD></TR></TBODY></TABLE></DIV>


<object ID='wscontrol' WIDTH=600 HEIGHT=50 STANDBY='<fmt:message key="msg.winshadow.standby"/>'
classid='<fmt:message key="msg.winshadow.classid"/>' codebase='<fmt:message key="msg.winshadow.codebase"/>' 
<PARAM NAME='_Version' VALUE='65536'><PARAM NAME='_ExtentX' VALUE='2646'><PARAM NAME='_ExtentY' VALUE='1323'><PARAM NAME='_StockProps' VALUE='0'><PARAM NAME='Debug' VALUE='1'><PARAM NAME='HideFileTransfer' VALUE='1'>
<PARAM NAME='DID' VALUE='<bean:write name="desktopinfo" property="did" scope="request" />'>
<PARAM NAME='LicString' VALUE='USER|COMPANY|04-0001-000141|qscycei'>
</object>

</BODY>

