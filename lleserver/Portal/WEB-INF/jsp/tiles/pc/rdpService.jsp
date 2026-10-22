
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ page import="com.infowave.c4p.web.actions.RDPForm" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>

<%@ page import="com.infowave.c4p.model.facade.RCUsageManagerImpl" %>
<%@ page import="com.infowave.c4p.service.dto.RCUsageDTO" %>
<%@ page import="com.infowave.c4p.web.actions.RDPForm" %>


<%

RCUsageManagerImpl rcStats = null;
RCUsageDTO rcInfo = null;
int clientSecondsLeft = 0;
RDPForm form = (RDPForm)request.getAttribute("desktopinfo");
String did = form.getDid();

rcStats = new RCUsageManagerImpl( did );

if( rcStats != null ) {
	clientSecondsLeft = rcStats.getClientSecondsLeft();
}

if( clientSecondsLeft > 0 ) {




%>

<table id='MainTable' width='100%' height='470'class=bodytable' border=0><tr>


<td colspan='2' width='85%' vAlign='top' align='left' class=emailbody><SCRIPT TYPE='text/javascript'><!--
function popup(mylink, windowname){ if (! window.focus)return true;var href;if (typeof(mylink) == 'string')   href=mylink;else   href=mylink.href;window.open(href,'', 'menubar=no, status=no, toolbar=no, resizable=yes');return false;}//--></SCRIPT>

<script language='Javascript'>
function openWin(){  w=screen.availWidth -12; h = screen.availHeight -52; window.open('/protected/rdpServicePage.do?did=<bean:write name="desktopinfo" property="did" scope="request" />','RemoteDesktop','status=yes,location=no,directories=no,menubar=no,scrollbars=no,resizable=yes,menubar=no,toolbar=no,width=' +w+ ',height='+h+',left=0,top=0,screenX=0,screenY=0') }

function openWinShadow(){  w=screen.availWidth -12; h = screen.availHeight -52; window.open('/protected/winshadowServicePage.do?did=<bean:write name="desktopinfo" property="did" scope="request" />','RemoteControl','status=yes,location=no,directories=no,menubar=no,scrollbars=no,resizable=yes,menubar=no,toolbar=no,width=' +w+ ',height='+h+',left=0,top=0,screenX=0,screenY=0') }

function openVncWin(){  w=screen.availWidth -12; h = screen.availHeight -52; window.open('/protected/vncServicePage.do?did=<bean:write name="desktopinfo" property="did" scope="request" />','RemoteDesktop','status=yes,location=no,directories=no,menubar=no,scrollbars=no,resizable=yes,menubar=no,toolbar=no,width=' +w+ ',height='+h+',left=0,top=0,screenX=0,screenY=0') }
</script>

<TABLE class='hometable' bgcolor=white cellSpacing=0 cellPadding=5 width='100%' border='0' ID='ft'><TR><td>

<table id='left'><tr><td class='left_body'><h3>
<fmt:message key="title.rcoverview"/>
</h3></td></tr><tr><td class='left_body'>
<fmt:message key="msg.rcoverview"/>
</td></tr><tr><td>

<TABLE id='grey' class='graytable' cellSpacing='0' cellPadding='0' width='100%' border='0' ><TR><TD noWrap width='5'><IMG alt='' src='x-res/corner-gray-tl.gif' border=0></TD><TD height=7 background='x-res/gray-top.gif'></TD><TD height=7 background='x-res/gray-top.gif'><IMG alt='' src='x-res/spacer.gif' border=0></TD><TD noWrap width='5'><IMG alt='' src='x-res/corner-gray-tr.gif' border=0></TD></TR><TR><TD width=7 background='x-res/gray-left.gif'></TD>

<TD width=86 align='center' valign='middle'><IMG alt='' src='x-res/rc1.gif' border='0'></TD><TD align='left'>

<TABLE class='graytable' cellSpacing='6' cellPadding='0' width='100%' border='0'><TR><TD height='20'><h2>
<fmt:message key="title.remotedesktop"/>
</h2></TD></TR>
<TR><TD>
<fmt:message key="msg.remotedesktop"/>
</TD></TR>
</TABLE>

<TD width='7' background='x-res/gray-right.gif'></TD></TR><TR><TD height=19 width=7 background='x-res/gray-left.gif'></TD>


<TD height=19 width='<fmt:message key="size.rdpservice.graybuttonwidth"/>' align='center' valign='middle' background='<fmt:message key="size.rdpservice.graybutton"/>' border='0'>

<A class='graybutton' href='JavaScript:openWin();'>
<fmt:message key="action.start"/>
</td>

<TD height=19 class=graybutton>

</TD>

<TD  height=19  width='7' background='x-res/gray-right.gif'></TD></TR><TR><TD><IMG alt='' src='x-res/corner-gray-bl.gif' border=0></TD><TD height=7 background='x-res/gray-bottom.gif'></TD><TD height='7' background='x-res/gray-bottom.gif'><IMG alt='' src='x-res/spacer.gif' border=0></TD><TD><IMG alt='' src='x-res/corner-gray-br.gif' border=0></TD></TR>
</TABLE></td></tr><tr><td>

<TABLE id='grey' class='graytable' cellSpacing='0' cellPadding='0' width='100%' border='0' ><TR><TD noWrap width='5'><IMG alt='' src='x-res/corner-gray-tl.gif' border=0></TD><TD height=7 background='x-res/gray-top.gif'></TD><TD height=7 background='x-res/gray-top.gif'><IMG alt='' src='x-res/spacer.gif' border=0></TD><TD noWrap width='5'><IMG alt='' src='x-res/corner-gray-tr.gif' border=0></TD></TR><TR><TD width=7 background='x-res/gray-left.gif'></TD><TD width=86 align='center' valign='middle'><IMG alt='' src='x-res/rc2.gif' border='0'></TD><TD align='left'>

<TABLE class='graytable' cellSpacing='6' cellPadding='0' width='100%' border='0'><TR><TD height='20'><h2>
<fmt:message key="title.winshadow"/>
</h2></TD></TR><TR><TD>
<fmt:message key="msg.winshadow"/>
</TD></TR>
</TABLE><TD width='7' background='x-res/gray-right.gif'></TD></TR><TR><TD height=19 width=7 background='x-res/gray-left.gif'></TD>


<TD height=19 width='<fmt:message key="size.rdpservice.graybuttonwidth"/>' align='center' valign='middle' background='<fmt:message key="size.rdpservice.graybutton"/>' border='0'>
<A class='graybutton' href='JavaScript:openWinShadow();'>
<fmt:message key="action.start"/>
</td><TD height=19 class=graybutton><font color='red'>&nbsp;</font></TD><TD  height=19  width='7' background='x-res/gray-right.gif'></TD></TR><TR><TD><IMG alt='' src='x-res/corner-gray-bl.gif' border=0></TD><TD height=7 background='x-res/gray-bottom.gif'></TD><TD height='7' background='x-res/gray-bottom.gif'><IMG alt='' src='x-res/spacer.gif' border=0></TD><TD><IMG alt='' src='x-res/corner-gray-br.gif' border=0></TD></TR>
</TABLE></td></tr><tr><td>

<TABLE id='grey' class='graytable' cellSpacing='0' cellPadding='0' width='100%' border='0' ><TR><TD noWrap width='5'><IMG alt='' src='x-res/corner-gray-tl.gif' border=0></TD><TD height=7 background='x-res/gray-top.gif'></TD><TD height=7 background='x-res/gray-top.gif'><IMG alt='' src='x-res/spacer.gif' border=0></TD><TD noWrap width='5'><IMG alt='' src='x-res/corner-gray-tr.gif' border=0></TD></TR><TR><TD width=7 background='x-res/gray-left.gif'></TD>

<TD width=86 align='center' valign='middle'><IMG alt='' src='x-res/rc3.gif' border='0'></TD><TD align='left'>

<TABLE class='graytable' cellSpacing='6' cellPadding='0' width='100%' border='0'><TR><TD height='20'><h2>
<fmt:message key="title.vnc"/>
</h2></TD></TR><TR><TD>
<fmt:message key="msg.vnc"/>
</TD></TR>
</TABLE><TD width='7' background='x-res/gray-right.gif'></TD></TR><TR><TD height=19 width=7 background='x-res/gray-left.gif'></TD>


<TD height=19 width='<fmt:message key="size.rdpservice.graybuttonwidth"/>' align='center' valign='middle' background='<fmt:message key="size.rdpservice.graybutton"/>' border='0'>
<A class='graybutton' href='JavaScript:openVncWin();'>
<fmt:message key="action.start"/>
</rd><TD height=19 class=graybutton>
<font color='red'>&nbsp;</font></TD><TD  height=19  width='7' background='x-res/gray-right.gif'></TD></TR><TR><TD><IMG alt='' src='x-res/corner-gray-bl.gif' border=0></TD><TD height=7 background='x-res/gray-bottom.gif'></TD><TD height='7' background='x-res/gray-bottom.gif'><IMG alt='' src='x-res/spacer.gif' border=0></TD><TD><IMG alt='' src='x-res/corner-gray-br.gif' border=0></TD></TR>
</TABLE></td></tr>
</table></td><TD width=120 noWrap>

<TABLE id='blue' class='bluetable' cellSpacing=0 cellPadding=0 width='100%' border='0'><TR><TD noWrap width='5'><IMG alt='' src='x-res/corner-blue-tl.gif' border='0'></TD><TD><IMG alt='' src='x-res/spacer.gif' border='0'></TD><TD noWrap width='5'><IMG alt='' src='x-res/corner-blue-tr.gif' border='0'></TD></TR><TR><TD><IMG alt='' src='x-res/spacer.gif' border=0></TD><TD>

<TABLE class=bluetable cellSpacing=0 cellPadding=2 width=100% border=0 ID=bluemenu><TR><TD></TD></TR>

<TR>
<TD><A href="
<fmt:message key="msg.link.upgrade"/>
" target=_blank>
<fmt:message key="msg.upgrade"/>
</a><hr size="1">
</TD>
</TR>
<TR>
<TD><A href="
<fmt:message key="msg.link.usersguide"/>
">
<fmt:message key="msg.usersguide"/>
</a><hr size="1">
</TD>
</TR>
<TR>
<TD><A href="
<fmt:message key="msg.link.faqs"/>
" target=_blank>
<fmt:message key="msg.faqs"/>
</a><hr size="1">
</TD>
</TR>
<TR>
<TD><A href="
<fmt:message key="msg.link.support"/>
" target=_blank>
<fmt:message key="msg.support"/>
</a><hr size="1">
</TD>
</TR>
<TR>
<TD><A href="
<fmt:message key="msg.link.company"/>
" target=_blank>
<fmt:message key="msg.laplinkwebsite"/>
</a><hr size="1">
</TD>
</TR>
<TR>
<TD><A href="/logout.do" >
<fmt:message key="action.logout"/>
</a></TD>
</TR>

</TABLE></a><TD><IMG alt='' src='x-res/spacer.gif' border='0'></TD></TR><TR><TD><IMG alt='' src='x-res/corner-blue-bl.gif' border=0></TD><TD><IMG alt='' src='x-res/spacer.gif' border=0></TD><TD><IMG alt='' src='x-res/corner-blue-br.gif' border='0'></TD></TR>
</TABLE>
</table></td></tr>
</table><!-- end of Main Table -->

<%
} else {
%>

<fmt:message key="msg.rcexpired"/>

<%
}
%>
