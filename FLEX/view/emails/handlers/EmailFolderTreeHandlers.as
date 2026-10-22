// ActionScript file
// This file consist of event handlers of EmailFolderTree.mxml

import mx.rpc.events.ResultEvent;
import mx.rpc.events.FaultEvent;
import mx.collections.XMLListCollection;
import flash.xml.XMLNode;
import ascript.utils.Utils;
import mx.events.TreeEvent;
import flash.xml.XMLDocument;
import mx.controls.Tree;
import mx.events.DragEvent;
import mx.managers.DragManager;
import mx.core.DragSource;
import mx.core.UIComponent;
import mx.controls.Tree;
import ascript.events.DataLoaded;

private function _getGetEmailListComplete(event:ResultEvent):void {	
	if (this._operationID == "2") {			
		var temp:XML = <root></root>;
		this._counstructEmailTree(XML(event.result).EmailFolder.EmailFolder,temp);	
		_xmlList = new XMLList(temp);
		_xmlResult = temp;
		_emailFolders = new XMLListCollection(_xmlList.folder);						
		_emailFolders.refresh();
		this.dataProvider = _emailFolders;				
		this._openTempItems.push(this.__xml1);		
	} else {
		this._findItemAndUpdate(_xmlResult.folder,this._emailService.FolderID,new XMLList((event.result).EmailFolder.EmailFolder));		
		this._openTempItems.map(this._expandItems,this);
	}
}

private function _expandItems(item:*,index:int, array:Array):void {
	this.expandItem(array[index], true);	
}

private function expandDefaultFolder() : void {
	if (this.__xml1!=null) {
		this.expandItem(this.__xml1,true);
		this.selectedItem = this.__xml2;	
	}
}

private function _findItemAndUpdate(node:XMLList,id:String,result:XMLList):void {
	for each (var folder:XML in node) {
		if (folder.@id == this._emailService.FolderID){
  	        this._counstructEmailTree(result,folder);
			return;
		} else if (folder.hasOwnProperty("folder")) {
			this._findItemAndUpdate(folder.folder,id,result);
		}
	}	
}
private var __xml1:XML;
private var __xml2:XML;

private var __flag:Boolean = false;

private function _counstructEmailTree(node:XMLList,result:XML):XML {
	var temp:XML = null;
	if (node.children().length()>0) {
		temp = <folder/>;
	}
	for each (var folder:Object in node) {
		temp.@name = folder.Name;
		temp.@id = folder.ID;
		if (temp.@id == this._emailService.ParentID) {
			this.__xml1 = temp.copy();
		}
		if (folder.hasOwnProperty("EmailFolder")) {
			var resp:XML = this._counstructEmailTree(folder.EmailFolder,temp);
		}		
		if (temp!=null) {
			result.appendChild(temp.copy());
			if (temp.@id == this._emailService.FolderID) {
				this.__xml2 = temp;
			}
			temp = <folder/>
		}
	}
	return temp;		
}

private function _getGetEmailListFault(event:FaultEvent):void {
	trace("bad request");				
}

private function _emailTreeFolderChange(event:Event):void {
	this._openNewFolder(event.currentTarget.selectedItem);
}

private function _onOpenNewFolderComplete(event:ResultEvent):void {
	this._xmlResult = XML( event.result );
	this._xmlList = new XMLList( _xmlResult );
}

private function _emailTreeFolderOpen(event:TreeEvent):void {
	
}

private function _emailTreeFolderClose(event:TreeEvent):void {
	delete this._openTempItems[this._openTempItems.indexOf(event.item)];
}

/*=============
	DRAG n DROP
==============*/

private function _onDragEnter(event:DragEvent):void {
	DragManager.acceptDragDrop(UIComponent(event.currentTarget));	
}

private function _onDragOver(event:DragEvent):void {
        var dropTarget:Tree = Tree(event.currentTarget);
        var r:int = dropTarget.calculateDropIndex(event);
        this.selectedIndex = r;
        var node:XML = this.selectedItem as XML;
        if( node.@id == "" ) {

            DragManager.showFeedback(DragManager.NONE);
            return;
        }
        if (event.ctrlKey)
            DragManager.showFeedback(DragManager.COPY);
        else if (event.shiftKey)
            DragManager.showFeedback(DragManager.LINK);
        else {
            DragManager.showFeedback(DragManager.MOVE);
        }	
}

private function _onDragDrop(event:DragEvent):void {
        var ds:DragSource = event.dragSource;
        var dropTarget:Tree = Tree(event.currentTarget);
        var items:Array = ds.dataForFormat("items") as Array;
        var r:int = this.calculateDropIndex(event);
        this.selectedIndex = r;
        var node:XML = this.selectedItem as XML;
        this._destinationFolderID = node.@id;
        var p:*;
        // if the selected node has children (it is type==city),

        for(var i:Number=0; i < items.length; i++) {

            this._selectedEmailMessageID = items[i].ID;
            this.moveEmailToFolder();
            //Add Child
            //this.dataDescriptor.addChildAt(p, insert, r+i);
        }	
}

private function _moveEmailComplete(event:ResultEvent):void {
	dispatchEvent(new DataLoaded(DataLoaded.DATA_LOADED));	
}

private function _moveEmailFault(event:FaultEvent):void {
	
}

