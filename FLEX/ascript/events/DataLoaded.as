package ascript.events
{
	import flash.events.Event;

	public class DataLoaded extends Event
	{
		public static const DATA_LOADED:String = "dataLoaded";
		
		public function DataLoaded(type:String) {
			super(type);
		}
	}
}