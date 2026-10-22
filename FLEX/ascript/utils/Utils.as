// ActionScript file

package ascript.utils {
	import flash.utils.Dictionary;
	import mx.collections.XMLListCollection;
	
	public class Utils {
		public static function numToSizeString(nValue:int, sMaxMeasure:String = "kb", bResInMaxMeasure:Boolean = false , nPrecision:int = 0) :String {
            // Calculate result:
            // Result in bytes
            var sResult:String = "";
            if (sMaxMeasure === "b" || (sMaxMeasure !== "b" && ! bResInMaxMeasure && nValue < 1024)) {
                sResult = new String(Utils.roundNum(nValue, nPrecision)+"byte(s)"); 
            }
            // Result in kilobytes
            nValue = nValue / 1024;
            if (sResult.length === 0
                    && (sMaxMeasure === "kb" || (sMaxMeasure !== "kb" && ! bResInMaxMeasure && nValue < 1024))) {
                sResult = new String(Utils.roundNum(nValue, nPrecision)+"Kb(s)"); 
            }
            // Result in megabytes
            nValue = nValue / 1024;
            if (sResult.length === 0
                    && (sMaxMeasure === "mb" || (sMaxMeasure !== "mb" && ! bResInMaxMeasure && nValue < 1024))) {
                sResult = new String(Utils.roundNum(nValue, nPrecision)+"Mb(s)"); 
            }
            // Result in gigabytes
            nValue = nValue / 1024;
            if (sResult.length === 0) {
                sResult = new String(Utils.roundNum(nValue, nPrecision)+"Gb(s)"); 
            }
            return sResult; 
  		} 		
  		
  		public static function roundNum(nValue:int, nDecimalPlaces:int = 0):int {
            return Number(nValue.toFixed(nDecimalPlaces));
        }
	}
}