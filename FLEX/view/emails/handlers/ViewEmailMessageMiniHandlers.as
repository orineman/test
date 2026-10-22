//Action Script file with event handlers for ViewEmailMessageMini.as

import flash.events.MouseEvent;
import flash.ui.Mouse;

import mx.collections.ArrayCollection;
import ascript.utils.Attachment;
import view.component.LinkButtonWithDATA;
import mx.core.Application;
import mx.core.IFlexDisplayObject;
import mx.managers.PopUpManager;
import view.component.DownloadPopUp;

private function _getGetEmailComplete(event:ResultEvent):void {	
	this._emailResult = XML(event.result.EmailFolder);
	this._bodyText = this._emailResult.Email.Body;
	this._fromAddress = this._emailResult.Email.From;
	this._ccAddress = this._emailResult.Email.CC;
	this._toAddress = this._emailResult.Email.To;
	this._subject = this._emailResult.Email.Subject;											
	this._attachments = new XMLList(this._emailResult.Email.Attach.AttachElement);	 
	if (this._attachments.length()>0) {
	}
}

private function _getGetEmailFault(event:FaultEvent):void {
	trace("bad request");				
}

private function _getEmailAttachment(event:MouseEvent):void {
/*	var request:URLRequest = new URLRequest();
        request.url = "";
        fr.download(request);	*/    	    	
    	
    	var attachment:Attachment = new Attachment(XML((event.currentTarget as LinkButtonWithDATA).objectdata),this._emailService.FolderID,this._emailService.MessageID); 	
    	this.downloadPopUp = new DownloadPopUp();
    	this.downloadPopUp.service = this._emailService;
		this.downloadPopUp.attachment = attachment;
		this.downloadPopUp.url = "https://alexog.laplink.yumasoft/pda/GetEmailMessageAttachment.do";
        PopUpManager.addPopUp(downloadPopUp,this, true);
        PopUpManager.centerPopUp(downloadPopUp);
        
}