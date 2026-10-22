<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>

<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>



<HTML>

  <HEAD>

    <title><fmt:message  bundle="${c4p_resellerBundle}" key="title.mainWindowTitle"/></title>

	<LINK REL=StyleSheet HREF='<fmt:message key="size.stylesheet"/>' TYPE="text/css">

<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="max-age" CONTENT="0">
<META HTTP-EQUIV="Expires" CONTENT="0">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
response.setHeader ("Content-Type", "text/html; charset=UTF-8"); //prevents caching at the proxy server
%>
  </HEAD>

<%-- this method can be overloaded by the desktop-generated pages --%>
<script language="javascript">
<!--
function OnLoadBasic(){
}

function expandCollapse() {
  for (var i=0; i<expandCollapse.arguments.length; i++) {
    var element = document.getElementById(expandCollapse.arguments[i]);
    element.style.display = (element.style.display == "none") ? "block" : "none";
  }
}

var msg1='<fmt:message key="msg.js.baddatefmt"/>'
var msg2='<fmt:message key="msg.js.badmonthfmt"/>'
var msg3='<fmt:message key="msg.js.badday1fmt"/>'
var msg4='<fmt:message key="msg.js.badday2fmt"/>'
var msg5='<fmt:message key="msg.js.badday3fmt"/>'
var msg6='<fmt:message key="msg.js.badday4fmt"/>'
var msg7='<fmt:message key="msg.js.badyearfmt"/>'
var format='<fmt:message key="msg.js.datefmt"/>'
var sep='<fmt:message key="msg.js.datesep"/>'
function CheckDate(datestring)
{
  if( datestring ) {
    var idxMonth = format.indexOf( "MM" );
    var idxDay = format.indexOf( "dd" );
    var idxYear = format.indexOf( "yyyy" );
    if( sep.length > 0 ) {
      var idxSep1 = format.indexOf( sep );
      var idxSep2 = format.lastIndexOf( sep );
      if( datestring.charAt( idxSep1 ) != sep || datestring.charAt( idxSep2 ) != sep ) {
        alert(msg1 + format+ ".");
        return false;
      }
    }
    var mon = datestring.substring( idxMonth, idxMonth+2 );
    var imon = parseInt( mon );
    if( imon < 1 || imon > 12 ) {
      alert(msg2 + format+ ".");
      return false;
    }
    var day = datestring.substring( idxDay, idxDay+2 );
    var iday = parseInt( day );
    switch(imon) {
    case '2':
      if( iday < 1 || iday > 29 ) {
        alert(msg3 + format+ ".");
        return false;
      }
      break
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      if( iday < 1 || iday > 31 ) {
        alert(msg4 + format+ ".");
        return false;
      }
      break
    case 4:
    case 6:
    case 9:
    case 11:
      if( iday < 1 || iday > 30 ) {
        alert(msg5 + format+ ".");
        return false;
      }
      break
    default:
      alert(msg6 + format+ ".");
      return false;
    }
    var year = datestring.substring( idxYear, idxYear+4 );
    for( i=0; i<year.length; i++ ) {
      var ch = year.charAt( i );
      if( (ch < "0") || (ch > "9") ) {
        alert(msg7 + format+ ".");
        return false;
      }
    }
  }
  return true;
}

var msg8='<fmt:message key="msg.js.invalidsize"/>'
function CheckSize(thestring)
{
  if( thestring && thestring.length > 0 ) {
    for (i = 0; i < thestring.length; i++) {
      var ch = thestring.charAt(i);
      if ((ch < "0" || ch > "9") && ch != "." && ch != "," )
      {
        alert(msg8);
        return false;
      }
    }
  }
  return true;
}
-->
</script>

<body  class="basicpage" onload="return OnLoadBasic();">

  <table class="basicpage" id="basicpageID" width="100%" cellspacing="0" cellpadding="0">

    <tr>

      <td colspan="2">

      <!-- header start -->

        <tiles:insert attribute="header" />

      <!-- header stop -->

      </td>

    </tr>

    <tr>

      <td class="basic_section" width="100%" valign="top" align="left">

        <table width="100%" cellspacing="0" cellpadding="0">

          <tr>

            <td class="basic_section">

					  <!-- because this is used for help pages as well, this section results in white line about help bar -->

				    <!-- pageErrors start -->

        	    <tiles:insert attribute="pageErrors" />

				    <!-- pageErrors stop -->

            </td>

          </tr>
          <tr>

            <td>

			      <!-- body start -->

              <tiles:insert attribute='body' />

            <!-- body stop -->

            </td>

          </tr>

        </table>

      </td>

    </tr>

    <tr class="footer_bar" >

	    <td colspan="3" width="100%">

		  <!-- footer start -->

		    <tiles:insert attribute="footer" />

		  <!-- footer stop -->

	    </td>

    </tr>

  </table>

</body>

</html>



