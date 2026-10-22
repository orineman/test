<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib uri="/WEB-INF/tld/taglibs-log.tld" prefix="log" %>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title><fmt:message  key="sitelayout.mainwindowtitle"/></title> 
		<LINK REL=StyleSheet HREF='<fmt:message key="sitelayout.stylesheet"/>' TYPE="text/css">

		<!--                                                              -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/nav4-0">
		<style media="all" type="text/css"> 
        </style>
<script language="javascript">

<!--
function OnLoadBasic(){
}

function EnsureText(textstr, msg)
{
  if( textstr && textstr.length > 0 ) {
    return true;
  } else {
    alert( msg );
    return false;
  }

}

function ScreenText(textstr)
{
  if( textstr && textstr.length > 0 ) {
        if( textstr.indexOf( ";" ) != -1 ) {
                alert('<fmt:message key="error.semicolonfound"/>');
                return false;
        }
  }
  return true;
}

function ValidNumber(thestring)
{
  if( thestring && thestring.length > 0 ) {
    for (i = 0; i < thestring.length; i++) {
        ch = thestring.substring(i, i+1);
        if (ch < "0" || ch > "9")
          {
          alert('<fmt:message key="error.maxconnsnonumeric"/>');
          return false;
          }
    }
    return true;
  } else {
    alert('<fmt:message key="error.maxconnsempty"/>');
    return false;
  }
}

function ValidPassword(password1str, password2str)
{
  if( !password1str || !password2str ) {
    alert('<fmt:message key="error.passwordstooshort"/>');
    return false;
  }
  if( password1str.length >= 6 || password2str.length >= 6 ) {
    if( password1str != password2str ) {
        alert('<fmt:message key="error.passwordsdontmatch"/>');
        return false;
    }
    return true;
  } else {
    alert('<fmt:message key="error.passwordstooshort"/>');
    return false;
  }
}

function ValidEmail(emailstr)
{
  if( emailstr && emailstr.length >= 5 ) {
    var idxAt = emailstr.indexOf( "@" );
    var idxDot = emailstr.lastIndexOf( "." );
    if( idxAt == -1 || idxDot == -1 || idxAt == 0 || idxAt > idxDot || idxAt+1 == idxDot || idxDot == emailstr.length-1)
    {
      alert('<fmt:message key="error.emailformat"/>');
      return false;
    }
    return true;
  } else {
    alert('<fmt:message key="error.emailformat"/>');
    return false;
  }
}



-->
</script>
	</head>

<body  class="basicpage" onload="return OnLoadBasic();">
  <table class="basicpage" id="basicpageID" width="100%" cellspacing="0" cellpadding="0">
        
    <tr>        
                
      <td colspan="2">                                                            
                
       <TABLE class="header_bar" cellspacing="0" cellpadding="0" width="100%" id="HeaderBar">
         <TR>          
           <td colspan="3" width="100%"></td>
           <td align="right" nowrap></td>       
         </tr>         
       </table>        

       <table border="0" cellpadding="0" cellspacing="0" style="BACKGROUND-IMAGE: url(x-res/header_filler.jpg); WIDTH: 100%; BACKGROUND-REPEAT: repeat-x; HEIGHT: 120px">
         <tr>
            <td width="800"><img src="x-res/header.jpg" width="800" height="120" border="0"></td>
            <td background="x-res/header_filler.jpg">&nbsp;</td>
         </tr>
       </table>
        
    </td>
   </tr> 

<!--
  <tr>
    <td> 
      <tiles:insert attribute="header" />
    </td>
  </tr>
-->

  <tr>
    <td>  
      <tiles:insert attribute="body" />
    </td>
  </tr>

  <tr>
    <td>  
      <tiles:insert attribute="footer" />
    </td>
  </tr>


  </table>

  <p align="center"><span class="style22"> <fmt:message key="footer.copyright" /> </span></p>

</body>

</html>

