package ascript.utils
{
	public class DateTimeUtils
	{
		/**
		 * The difference between the Windows epoch (1601-01-01
		 * 00:00:00) and the Unix epoch (1970-01-01 00:00:00) in
		 * milliseconds: 11644473600000.
		 */
		public static const EPOCH_DIFF:Number = 11644473600000;
		
		/**
		 * Converts a Windows FILETIME into a {@link Date}. The Windows
		 * FILETIME structure holds a date and time associated with a
		 * file. The structure identifies a 64-bit integer specifying the
		 * number of 100-nanosecond intervals which have passed since
		 * January 1, 1601.
		 *
		 * @param filetime {Integer | String} The filetime to convert.
		 * @return {Date} The Windows FILETIME as a {@link Date}
		 *     or null if <code>filetime</code> cannot be converted to {@link Date}.
		 */
		public static function windowsStringFiletimeToDate(filetime:String):Date
		{
		    var dResult:Date = null;
		    if (!filetime)
		    	return null;
		           
		        
		        dResult = new Date(( parseInt(filetime) / 10000) - DateTimeUtils.EPOCH_DIFF);
		//		var iYear = dResult.getYear();
		//		var iMonth = dResult.getMonth();
		//		var iDay = dResult.getDay();
		//		var iHour = dResult.getHour();
		//		var iMinute = dResult.getMinute();
		//		dResult.setUTCDate(dResult);		
		    return dResult;
		}
		
		public static function windowsIntFiletimeToDate(filetime:Number):Date {
			var dResult:Date = null;
			dResult = new Date((filetime / 10000) - DateTimeUtils.EPOCH_DIFF);
			return dResult;		
		}
		
		/**
		 * Converts a {@link Date} into a Windows FILETIME.
		 *
		 * @param date {Date} The date to be converted.
		 * @return {Integer} The number that represents corresponding filetime
		 *     or -1 if <code>date</code> cannot be converted to filetime.
		 *
		 * @see LLE.util.windowsFiletimeToDate
		 */
		public static function dateToWindowsFiletime(date:Date):int
		{
		    if (date) {
		        return (date.getTime() + DateTimeUtils.EPOCH_DIFF) * 10000;
		    }
		    else {
		        return -1;
		    }
		}
	}
}