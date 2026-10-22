// ActionScript file

import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import ascript.events.EmailMessageSaved;
import flash.events.MouseEvent;
import flash.printing.PrintJob;
import mx.controls.Alert;
import flash.printing.PrintJobOptions;
import flash.geom.Rectangle;
import mx.printing.FlexPrintJob;
import mx.printing.FlexPrintJobScaleType;
import mx.core.IUIComponent;
import mx.controls.TextArea;

//REQUEST HANDLER START.

private function _onSaveEmailComplete(event:ResultEvent):void {	
	dispatchEvent(new EmailMessageSaved(EmailMessageSaved.EMAIL_MESSAGE_SAVED));
}

private function _onSaveBeforeSendComplite(event:ResultEvent):void {
	var _emailResult:XML = XML(event.result.EmailFolder);
	this._folderID = _emailResult.ID;
	this._messageID =_emailResult.Email.ID;
	this.sendEmailMessage();	
}

private function _onSaveEmailFault(event:FaultEvent):void {
	trace("save email fault");	
}	

private function _onSendComplete(event:ResultEvent):void {
	this.removeEventListener(MouseEvent.CLICK, panelClickHandler);
	this.parent.removeChild(this);	
}

private function _onSendFault(event:ResultEvent):void {
		
}
//REQUEST HANDLERS END.
//UI HANDLERS START.
private function onSaveButtonClick(event:MouseEvent):void {
	this.saveEmailMessage();	
}

public function onSendClick(event:MouseEvent):void {
	this._clientToHostCommands = _emailService.getE4XEmailRequest(this.SaveEmail,
												 this._onSaveBeforeSendComplite,
												 this._onSaveEmailFault);
	this._clientToHostCommands.send();
}

public function onPrintButtonClick(event:MouseEvent):void {
	var pj:FlexPrintJob = new FlexPrintJob();
	var t:Object = this.prepareToPrint(this.bodyText.textArea)
	if (pj.start()) {
		try
        {
        	
            pj.addObject(this.bodyText.textArea,FlexPrintJobScaleType.SHOW_ALL);
        }
        catch (e:Error)
        {
            Alert.show("Print error!!");// Do nothing.
        }
        pj.send();
        this.finishPrint(t,this.bodyText.textArea);        
	} else {
		Alert.show("Print canceled!!");
	}
}
//UI HANDLER END.