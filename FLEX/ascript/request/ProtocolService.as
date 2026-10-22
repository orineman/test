package ascript.request
{
	import mx.rpc.http.mxml.HTTPService;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.events.FaultEvent;
	import flash.net.SharedObject;
	
	public class ProtocolService
	{
		public static const CLIENT_HANDLER:String = "/protected/clientCommands.do";
		
		public static const CLIENT_TO_HOST_HANDLER:String = "/protected/clienttohostCommands.do"
		
		private var _clientCommands:HTTPService;
		
		private var _clientToHostCommands:HTTPService;
		
		private var _headers:Object = {};		
		
		//Constructor
		public function ProtocolService() {
			
		}
		
		private function createService(destination:HTTPService,url:String,resultFormat:String):HTTPService {						
			if (destination == null) {
				destination = new HTTPService();	
				destination.useProxy = false;
				destination.method = "POST";
				destination.headers = this._headers;
				destination.contentType = "application/xml";
				destination.showBusyCursor = true;
				destination.addEventListener(ResultEvent.RESULT,this.onRequestResult);
				destination.addEventListener(FaultEvent.FAULT,this.onRequestFault);			
			}	
			destination.url = url;	
			destination.resultFormat =resultFormat;		
			return destination;
		}											
		
		public function getClientCommands():HTTPService {
			return this.createService(_clientCommands,CLIENT_HANDLER,"object");
		}
		
		public function getXMLClientCommands():HTTPService {
			return this.createService(_clientCommands,CLIENT_HANDLER,"xml");
		}
		
		public function getE4XClientCommands():HTTPService {
			return this.createService(_clientCommands,CLIENT_HANDLER,"e4x");
		}
		
		public function getClientToHostCommands():HTTPService {
			return this.createService(_clientToHostCommands,CLIENT_TO_HOST_HANDLER,"object");	
		}
		
		public function getXMLClientToHostCommands():HTTPService {
			return this.createService(_clientToHostCommands,CLIENT_TO_HOST_HANDLER,"xml");		
		}

		public function getE4XClientToHostCommands():HTTPService {
			return this.createService(_clientToHostCommands,CLIENT_TO_HOST_HANDLER,"e4x");		
		}
		
		public function flush():void { 
			this._clientCommands=null;
			this._clientToHostCommands=null;
		}		
		
		public function addRequestHeader(name:String,value:String):void {
			this._headers[name] = value;	
		}
		
		public function getRequestHeaders():Object {
			return this._headers;
		}
		
		public function removeRequestHeader(name:String):void {
			delete this._headers[name];
		}
		
		private function onRequestFault(event:FaultEvent):void {
			trace(event.eventPhase);	
		}	
		
		private function onRequestResult(event:ResultEvent):void {
			trace(event.result);
		}	
	}
}