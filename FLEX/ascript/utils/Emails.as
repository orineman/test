package ascript.utils
{
	import ascript.request.ProtocolService;
	import mx.rpc.http.mxml.HTTPService;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectProxy;
	import flash.net.URLRequestHeader;
	
	public class Emails
	{		
		private var _service:ProtocolService;
		
		[Bindable]
		public var FolderID:String ="";
		
		[Bindable]
		public var MessageID:String ="";
		
		[Bindable]
		public var FolderName:String ="";
		
		[Bindable]
		public var StartingNumber:Number = 0;
		
		[Bindable]
		public var ResultNumber:Number = 20;
		
		public var Count:Number = 0;
		
		public var ParentID:String="";
		
		public function Emails(service:ProtocolService) {
			this._service = service;
		}		
		
		public function initE4XEmailRequest(request:ObjectProxy,result:Function,fault:Function):HTTPService {			
			var	_clientToHostCommands:HTTPService = this.getE4XEmailRequest(request,result,fault);
				_clientToHostCommands.send();
				return _clientToHostCommands;	
		}
		
		public function initObjectEmailRequest(request:ObjectProxy,result:Function,fault:Function):HTTPService {
			var	_clientToHostCommands:HTTPService = this.getObjectEmailRequest(request,result,fault);
				_clientToHostCommands.send();
			return _clientToHostCommands;
		}
		
		public function getObjectEmailRequest(request:ObjectProxy,result:Function,fault:Function):HTTPService {
			var	_clientToHostCommands:HTTPService = _service.getClientToHostCommands();
				_clientToHostCommands.request = request;
				_clientToHostCommands.addEventListener(ResultEvent.RESULT,result);					
				_clientToHostCommands.addEventListener(FaultEvent.FAULT,fault);	
			return _clientToHostCommands;
		}
		
		public function getE4XEmailRequest(request:ObjectProxy,result:Function,fault:Function):HTTPService {
			var	_clientToHostCommands:HTTPService = _service.getE4XClientToHostCommands();
				_clientToHostCommands.addEventListener(ResultEvent.RESULT,result);					
				_clientToHostCommands.addEventListener(FaultEvent.FAULT,fault);
				_clientToHostCommands.request = request;	
			return _clientToHostCommands;
		}
		
		public function doAfterGetEmailList(request:ObjectProxy,result:Function,fault:Function):void {
			if (this.Count > this.ResultNumber) {				
				var _clientToHostCommands:HTTPService = this.getE4XEmailRequest(request,result,fault);
					this.StartingNumber = this.ResultNumber;
					this.ResultNumber = this.Count - this.ResultNumber;					
					_clientToHostCommands.showBusyCursor = false;
					_clientToHostCommands.send();
			}
		}	
		
		public function getRequestHeaders():Object {
			return this._service.getRequestHeaders();
		}	
		
		public function getURLRequestHeaders():Array {
			var headers:Object = this._service.getRequestHeaders();
			var array:Array = new Array();
			for(var key:String in headers) {
				array.push(new URLRequestHeader(key,headers[key]));
			}
			return array;
		}
		
		// Getters and Setters
/*		[Bindable]
		public function get FolderID() : String { return this._folderID;}
		public function set FolderID(id:String):void { this._folderID = id;}
		[Bindable]
		public function get MessageID() : String { return this._messageID;}
		public function set MessageID(id:String):void { this._messageID = id;}	*/	
		public function get Service() : ProtocolService { return this._service;}		
	}
}