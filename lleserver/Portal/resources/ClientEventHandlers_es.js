
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
	window.document.title="Escritorio remoto de Laplink: conectado";

	if (connect == 0)
	{
	   alert("Laplink Remote Desktop\n\n" +
	      "Inicie sesi\u00F3n mediante autenticaci\u00F3n normal de Windows del ordenador remoto\n\n" +
	      "Pulse CTRL+May\u00FAs+F para alternar entre el modo de pantalla completa y el modo Windows\n");
	   connect = 1;
	}
	hidescreens();
}
function rdpdisconnected()
{
    connect = 0;
	window.document.title="Escritorio remoto de Laplink: desconectado";
	alert("\241Advertencia!\n\n" +
	   "\241Se ha desconectado del ordenador remoto!\n\n" +
	   "Presione F5 para volver a conectarse o cierre la ventana del explorador\n" +
	   "para volver a Laplink Everywhere.\n");

}
function rdpfullscreen(mode)
{
	if ( mode == 1 )
	{
		
	   alert("Pulse CTRL+May\u00FAs+F para alternar entre el modo de pantalla completa y el modo Windows\n");

	}
	else
	{
	}
}
