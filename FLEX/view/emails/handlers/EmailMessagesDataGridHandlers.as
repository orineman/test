//File of handlers of EmailMessagesDataGrid.as
import ascript.events.DataLoaded;
import ascript.utils.DateTimeUtils;
import ascript.utils.Utils;

import flash.xml.XMLNode;

import mx.collections.XMLListCollection;
import mx.events.ScrollEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

private function _getGetEmailListComplete(event:ResultEvent) : void {	
	_xmlResult = XML(event.result);
	var xmlList:XMLList = new XMLList(_xmlResult);
	this._emailService.FolderID = xmlList.EmailFolder.ID;
	this._emailService.ParentID = xmlList.EmailFolder.ParentID;	
	this._emailService.FolderName = xmlList.EmailFolder.Name;
	this._emailService.Count = xmlList.EmailFolder.Count;
	_dataGridProvider = new XMLListCollection(xmlList.EmailFolder.Email);
	this.dataProvider = _dataGridProvider;
	
	if (this._initTreeFlag) {
		dispatchEvent(new DataLoaded(DataLoaded.DATA_LOADED));
	}
	
	this._emailService.doAfterGetEmailList(this.GetEmailListRequest,this._getAfterRequestComplete,this._getGetEmailListFault);
}

private function _getAfterRequestComplete(event:ResultEvent) :void {
	_xmlResult = XML(event.result);
	var xmlList:XMLList = new XMLList(_xmlResult);	
	for each (var email in xmlList.EmailFolder.Email) {		
		this._dataGridProvider.addItem(email);
	}
	this._dataGridProvider.refresh();
}

private function _getGetEmailListFault(event:FaultEvent) : void {
	trace("bad request");				
}

private function _formateDataGridSizeColumn(item:Object, column:DataGridColumn) : String {	
	return Utils.numToSizeString(parseInt(item.Size));
}

private function _formatDataGridRecievedColumn(item:Object, column:DataGridColumn) : String {
	if (item!=null && item.ReceivingDate!="") {
		return DateTimeUtils.windowsStringFiletimeToDate(item.ReceivingDate).toDateString();
	}
	return "";
}

/*=========
	DRAG n DROP
*===============*/

private function _onDragComplete( event:DragEvent ) : void {
//    tree.selectedIndex = -1;
}  

