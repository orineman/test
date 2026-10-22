package ascript.utils
{
	public class Attachment
	{
		private var _id:String;
		private var _name:String;
		private var _size:String;
		private var _isLink:Boolean;
		private var _additionalType:String;
		private var _additionalID:String;
		private var _folderID:String;
		private var _messageID:String;
		
		public function Attachment(att:XML,folderID:String,messageID:String) {
			this._name = att.FSElement.Name;	
			this._id = att.ID;
			this._size = att.FSElement.Size;
			this._isLink = (att.AsLink==1);
			this._folderID = folderID;
			this._messageID = messageID;
		}		

		public function get ID():String { return _id; }
		public function get FolderID():String { return _folderID; }
		public function get MessageID():String { return _messageID; }		
		public function get Name():String { return _name; }
		public function get Size():String { return _size; }
		public function get isLink():Boolean { return _isLink; }
		public function get AdditionalType():String { return _additionalType; }
		public function get AdditionalID():String { return _additionalID; }		
	}
}