
function OnMouseOutOfBar(obj)
{
	SetColor(obj,'#959699');
	window.status = null;
}
function OnMouseOverBar(obj, text)
{
	SetColor(obj,'#ff9933');
	window.status = text;
}
function OnLapMenuClick(obj, fid, lid)
{
	document.f1.MFID.value=fid;
	document.f1.MLID.value=lid;
	document.forms[0].submit();
}
function SetColor(obj,color)
{
	if(typeof(obj) == 'undefined')
	return;
	obj.style.backgroundColor = color;
}
function Show(obj)
{
	var NoOfComboBoxes = 0;
	if(typeof(obj) == 'undefined')
		return;
	var ComboObj;
	for(var i = 1; i <= NoOfComboBoxes; ++i)
	{
		ComboObj = document.getElementById('ComboBox' + i);
		if(typeof(ComboObj) == 'undefined')
			continue;
		var LabelObj = document.getElementById('Label' + i);
		LabelObj.innerText = ComboObj.value;
		ComboObj.style.visibility = 'hidden';
	}
	var MenuObjID = obj.id + '_H'
	var MenuObj = document.getElementById(MenuObjID);
	if (MenuObj == null)
		MenuObj = obj;
	MenuObj.style.visibility = 'visible';
}
function Hide(obj) 
{
	var NoOfComboBoxes = 0;
	if(typeof(obj) == 'undefined')
		return;
	var ComboObj;
	for(var i = 1; i <= NoOfComboBoxes; ++i)
	{
		ComboObj = document.getElementById('ComboBox' + i);
		if(typeof(ComboObj) == 'undefined')
		continue;
		ComboObj.style.visibility = 'visible';
	}
	var MenuObjID = obj.id + '_H'
	var MenuObj = document.getElementById(MenuObjID);
	if (MenuObj == null)
		MenuObj = obj;
	MenuObj.style.visibility = 'hidden';
}
function checkRDP()
{
   var activexobj = document.getElementById("rdpcontrol");
    if (activexobj && activexobj.readyState > 0) 
    {                   
        if (!activexobj.SystemDid
         || activexobj.SystemDid == "" )   
        {
            showxp2screen();       
        }                     
    }                                                    
}
function showxp2screen()
{
    document.all.xp2.style.visibility   = "visible";
    document.all.installing.style.visibility = "hidden";

}
function checkWS()
{
   var activexobj = document.getElementById("wscontrol");
    if (activexobj && activexobj.readyState > 0) 
    {                   
        if (!activexobj.DID
         || activexobj.DID == "" )   
        {
            showxp2screen();       
        }                     
    }                                                    
}
function checkMFT()
{
   var activexobj = document.getElementById("mftcontrol");
    if (activexobj && activexobj.readyState > 0) 
    {                   
        if (!activexobj.Authentication
         || activexobj.Authentication == "" )   
        {
            showxp2screen();       
        }                     
    }                                                    
}

function goLink(url)
{ 
	location.replace(url); 
}

function hidescreens()
{
    document.all.xp2.style.visibility   = "hidden";
    document.all.installing.style.visibility = "hidden";

}
var connect = 0;

function rdpconnected()
{
	window.document.title="Laplink Remote Desktop - Connect\351";

	if (connect == 0)
	{
	   alert("Laplink Remote Desktop\n\n" +
	      "Connectez-vous \340 l'aide de l'authentification Windows normale de votre ordinateur distant\n\n" +
	      "Tapez CTRL-SHIFT-F pour passer du mode plein \351cran au mode fen\352tres et vice-versa\n");
	   connect = 1;
	}
	hidescreens();
}
function rdpdisconnected()
{
    connect = 0;
	window.document.title="Laplink Remote Desktop - D\351connect\351";
	alert("Attention !\n\n" +
	   "Vous avez \351t\351 d\351connect\351 de l'ordinateur distant\n\n" +
	   "Appuyez sur F5 pour vous reconnecter ou fermez la fen\352tre du navigateur \n" +
	   "pour retourner \340 Laplink Everywhere.\n");

}
function rdpfullscreen(mode)
{
	if ( mode == 1 )
	{
		
	   alert("Tapez CTRL-SHIFT-F pour passer du mode plein \351cran au mode fen\352tres et vice-versa\n");

	}
	else
	{
	}
}
