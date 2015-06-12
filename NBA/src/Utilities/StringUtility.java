package Utilities;

public class StringUtility {
	
	public static String safeString(String str){
		if(str != null && !str.isEmpty()){
			return str;
		}
		return "";
	}

}
