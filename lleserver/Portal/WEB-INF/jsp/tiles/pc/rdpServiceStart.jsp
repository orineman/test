
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ page import="com.infowave.c4p.web.actions.teksupport.DesktopForm" %>


<script type="text/javascript" language="JavaScript">
<!--
function setTitle(){
<!-- document.title='<fmt:message  bundle="${c4p_resellerBundle}" key="title.pcConfiguration"/>'; -->
document.title='<fmt:message key="title.remotedesktop"/>';
}

function cancel(){
this.close();
}
// -->
</script>

</td></tr></table>
<HEAD> <TITLE>Remote Desktop</TITLE> 
</HEAD> <BODY bgColor=white onload=checkRDP();>

<script language='javascript' src='<fmt:message key="msg.rdp.popcal"/>'></script>
<script language='javascript' src='<fmt:message key="msg.rdp.ehandler"/>'></script>
<script language='javascript' FOR='rdpcontrol' EVENT='Connected'>rdpconnected();</script>
<script language='Javascript' FOR='rdpcontrol' EVENT='Disconnected'>rdpdisconnected();</script>
<script language='Javascript' FOR='rdpcontrol' EVENT='FullScreen(mode)'>rdpfullscreen(mode);</script>
<style type='text/css'>
a { font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none;}
.MenuToolbar { background-color: #336666; }
</style>

<DIV id=xp2 style="VISIBILITY: hidden; POSITION: absolute; TOP: 25px"><TABLE class=emailbody width=100%><TBODY><TR><td><img src=x-res/lle4-globe-waiting.gif></td><td><IMG style='POSITION: absolute; TOP: 15px; LEFT: 540px;' alt='<fmt:message key="msg.alt.yellowbar"/>' src='x-res/LOOKUPAX.GIF'></td><td class='left_body' style="POSITION: absolute; TOP: 30px; LEFT: 260px;">
<fmt:message key="msg.rdp1"/>
</td></TR></TBODY></TABLE></DIV>

<DIV id="installing" style="VISIBILITY: visible; POSITION: absolute; TOP: 25px "><TABLE class="emailbody" width="100%"><TBODY><TR><td><img src="x-res/lle4-globe-waiting.gif"></td><TD class="left_body">
<fmt:message key="msg.rdp2"/>
</TD><TD vAlign="bottom"></TD></TR></TBODY></TABLE></DIV>

<object id=rdpcontrol classid='<fmt:message key="msg.rdp.classid"/>' HSPACE=0 VSPACE=0 
STANDBY='<fmt:message key="msg.standby"/>' codebase='<fmt:message key="msg.rdp.codebase"/>'
width='100%' height='100%' border='0' VIEWASTEXT>
<PARAM name='SystemDid' value='<bean:write name="desktopinfo" property="did" scope="request" />'>
</object>

<table><tr><td>
</body>
