package com.core;

public class ChStr {
	public static String toChinese(String strvalue) {
		try {
			if (strvalue == null) {										
				strvalue="";											
			} else {
				strvalue = new String(strvalue.getBytes("ISO8859_1"), "utf-8");	
				strvalue = strvalue.trim();								
			}
		} catch (Exception e) {
			strvalue="";												
		}
		return strvalue;											
	}

	
	public static final String nullToString(String v, String toV) {
	    if (v == null || "".equals(v)) {			
	        v = toV;						
	    }
	    return v;							
	}
	//¹ýÂËÎ£ÏÕ×Ö·û
	public static final String filterStr(String str){
		str=str.replaceAll(";","");
		str=str.replaceAll("&","&amp;");
		str=str.replaceAll("<","&lt;");
		str=str.replaceAll(">","&gt;");
		str=str.replaceAll("'","");
		str=str.replaceAll("--"," ");
		str=str.replaceAll("/","");
		str=str.replaceAll("%","");
		return str;
	}
}
