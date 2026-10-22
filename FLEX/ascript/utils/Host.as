package ascript.utils
{
	public class Host
	{
		private var _did:String;
		private var _name:String;
		private var _owner:String;
		private var _onlineFlag:Boolean;
		private var _daysToExpiry:int;
		private var _ICM:String;
		private var _ICMRC:String;
		private var _version:String;
		
		public function Host(host:Object) {
			this._did = host.ID;
			this._name = host.Name;
			this._owner = host.Owner;
			this._onlineFlag = (host.OnlineFlag=="Yes");
			this._daysToExpiry = int(host.DaysToExpiry);
			this._ICM = host.ICM;
			this._ICMRC = host.ICMRC;
			this._version = host.Version;						
		}
		
		public function get did():String {
			return this._did;	
		}
		
		public function get name():String {
			return this._name;	
		}
		
		public function get owner():String {
			return this._owner;	
		}				
		
		public function get onlineFlag():Boolean {
			return this._onlineFlag;	
		}
		
		public function get daysToExpiry():int {
			return this._daysToExpiry;	
		}
		
		public function get ICM():String {
			return this._ICM;	
		}
		
		public function get ICMRC():String {
			return this._ICMRC;	
		}
		
		public function get version():String {
			return this._version;	
		}		
	}
}