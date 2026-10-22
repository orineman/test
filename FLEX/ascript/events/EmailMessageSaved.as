package ascript.events
{
	import flash.events.Event;

	public class EmailMessageSaved extends Event
	{
		public static const EMAIL_MESSAGE_SAVED:String = "emailMessageSaved";
		
		public function EmailMessageSaved(type:String) {
			super(type);
		}
	}
}