// ActionScript file
// This file contain all event handlers for ../ServicePanel.mxml

import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.events.DataGridEvent;
import mx.events.ListEvent;
import mx.events.TreeEvent;
import flash.events.Event;
import flash.events.MouseEvent;
import view.emails.EditEmailWindow;
import mx.managers.PopUpManager;

private function _getUserServicesFault(event:FaultEvent):void {
	trace("bad request");		
}

private function _getUserServicesComplete(event:ResultEvent):void {
	xmlResult = XMLNode(event.result);
	xmlList = new XMLList(xmlResult);
	_services = new XMLListCollection(xmlList.UserInfo.Services.Service);	
}

private function _emailTreeFolderChange(event:Event):void {
	this._emails.FolderID = event.currentTarget.selectedItem.@id;
	if (_isViewEmailShown) {
		content.removeChildAt(content.getChildren().length-1);
		_isViewEmailShown = false;
	}
	emailMesages.initData(false);
}

private var _isViewEmailShown:Boolean = false;

private function _emailDataGridChange(event:Event):void {
	this._emails.MessageID = event.currentTarget.selectedItem.ID;
	if (!_isViewEmailShown) {		
		content.addChild(viewEmail);		
		_isViewEmailShown = true;
	}	
	viewEmail.initData();
}

private function _emailMessagesDataLoaded(event:Event):void {
	this.emailTree.initTree();
}

private function _openNewEmailWindow(event:MouseEvent):void {	    		
	var newEmailWindow:EditEmailWindow = new EditEmailWindow();
//		this.addChild(newEmailWindow);
		newEmailWindow.emails = this._emails;
        PopUpManager.addPopUp(newEmailWindow,this, false);
        PopUpManager.centerPopUp(newEmailWindow);
}