package ascript.events
{
	import flash.events.Event;
	
	public class HostPanelEvent extends Event
	{
		private var _host:Object;
		
		public static const HOST_PANEL_EVENT:String = "hostSelected";
		
		public function HostPanelEvent(type:String,host:Object) {						
			super(type);
			this._host = host;
		}
		
		public function get host():Object {
			return this._host;
		}		
		
		override public function clone():Event {
            return new HostPanelEvent(type,_host);
        }
	}
}