package com.plmviewer.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;


//@SuppressWarnings("unchecked")
public class FormatHelper {
	
	
	
	public static final boolean hasContentDecimal(BigDecimal bigDecimal)
    {
        if(bigDecimal == null || "null".equals(bigDecimal) || "undefined".equals(bigDecimal) || "0".equals(bigDecimal) || "0.0".equals(bigDecimal))
            return false;
        
        return true;
    }
	public static boolean hasContentIgnoreZero(String incomingString) {
		   if(incomingString == null || "null".equals(incomingString) || "undefined".equals(incomingString))
	           return false;
		   if("".equals(incomingString.trim()))
			   return false;

	       return true;

		} 
	
	public static final boolean hasContent(Integer integerNum)
    {
		//System.out.println("the float "+ floatNumber);
        if(integerNum == null || "null".equals(integerNum) || "undefined".equals(integerNum) || "0".equals(integerNum) || "0.0".equals(integerNum))
            return false;
        
        return true;
    }
	public static final Float formatFloat(String floatNumber)
	
    {
		//System.out.println("the float "+ floatNumber);
        if(floatNumber == null || "null".equals(floatNumber) || "undefined".equals(floatNumber) || "0".equals(floatNumber) || "0.0".equals(floatNumber)|| "".equalsIgnoreCase(floatNumber.trim()))
            return new Float(0) ;
        
        return new Float(floatNumber);
    }
	
	public static final int format(Integer integerNum)
    {
        if(integerNum == null || "null".equals(integerNum) || "undefined".equals(integerNum) || "0".equals(integerNum) || "0.0".equals(integerNum))
            return 0 ;
        
        return Integer.valueOf(integerNum);
    }
	public static final boolean hasContentAllowZero(String s)
    {
        if(s == null || "null".equals(s) || "undefined".equals(s))
            return false;
        if(s.length()> 0)
        	return true;
        return false;
    }
	public static String format(String incomingString){
		if(!hasContent(incomingString))
			return "";
		else
			return incomingString.trim();
	}

   public static boolean hasContent(String incomingString) {
	   if(incomingString == null || "null".equals(incomingString) || "undefined".equals(incomingString) || "0".equals(incomingString) || "0.0".equals(incomingString))
           return false;
	   if("".equals(incomingString.trim()))
		   return false;
	   
       return true;
		
	}

public static Double formatDouble(String incomingString){
	   if(!hasContent(incomingString))
		   return new Double(0.0);
	   else{
		   //Double value = Double.parseDouble(incomingString);
		   Double value = new Double(incomingString);
		   return value;
	   }
   }
   public static Double formatDouble(Double incoming){
	   if(incoming == null)
		   return new Double(0.0);
	   return incoming;
   }
	public static Collection sort(Collection toSort){
		Vector coll = new Vector();
		if(toSort == null)return coll;
		java.util.Iterator iter = toSort.iterator();
		while(iter.hasNext()){
			String val = (String)iter.next();
			if(val.indexOf("###---STOREDOBJECTSKEY---###")> -1)
   			continue;
			coll.add(val.trim());
		}
		Collections.sort(coll);
		return coll;
	}
   
   public static Collection sortIntegerKeys(Collection toSort){
		Vector coll = new Vector();
		if(toSort == null)return coll;
		java.util.Iterator iter = toSort.iterator();
		while(iter.hasNext()){
			Integer val = (Integer)iter.next();
			coll.add(val);
		}
		Collections.sort(coll);
		return coll;
	}
   
   public static HashMap formatStringToMap(String incomingString){
	   HashMap stringToMap = new HashMap();
	   if(! FormatHelper.hasContent(incomingString))
		   return stringToMap;
	   String[] strTokens = incomingString.split(",");
	  for(int i =0; i < strTokens.length; i++){
		   String tokens = strTokens[i];
		   String[]tokenArray = tokens.split("-");
		   if(tokenArray.length > 1){
			   String colorCode = tokenArray[0];
			   String colorName = tokenArray[1];
			   stringToMap.put(colorCode, colorName);
		   }
	   }
	   return stringToMap;
   }
   
   public static String formatMapToString(HashMap  map){
	   String mappedString ="";
	   if(map == null || map.size() < 1)
		   return mappedString;
	   Iterator iter = map.keySet().iterator();
	   while(iter.hasNext()){
		   String key = (String)iter.next();
		   String value = (String)map.get(key);
		   mappedString = mappedString+key+"-"+value;
		   if(iter.hasNext())
			   mappedString = mappedString+",";
	   }
	   return mappedString;
   }
   
   public static final String formatJavascriptString(String s)
   {
       return formatJavascriptString(s, true);
   }

   public static final String formatJavascriptString(String s, boolean flag)
   {
       return formatJavascriptString(s, flag, true);
   }
   public static final String formatJavascriptString(String s, boolean flag, boolean flag1)
   {
       s = format(s);
       if(s.indexOf('\'') > -1 || s.indexOf('\n') > -1 || flag && s.indexOf('"') > -1 || s.indexOf('\r') > -1 || s.indexOf('\\') > -1)
       {
           StringBuffer stringbuffer = new StringBuffer(s);
           synchronized(stringbuffer)
           {
               for(int i = stringbuffer.indexOf("\\"); i > -1; i = stringbuffer.indexOf("\\", i + 2))
                   stringbuffer.insert(i, '\\');

               for(int j = stringbuffer.indexOf("'"); j > -1; j = stringbuffer.indexOf("'", j + 2))
                   stringbuffer.insert(j, "\\");

               if(flag1)
               {
                   for(int k = stringbuffer.indexOf("\n"); k > -1; k = stringbuffer.indexOf("\n", k + 2))
                       stringbuffer.insert(k, "\\");

               } else
               {
                   for(int l = stringbuffer.indexOf("\r\n"); l > -1; l = stringbuffer.indexOf("\r\n", l + 7))
                       stringbuffer.replace(l, l + 1, "<newline>\\");

               }
               if(flag)
               {
                   for(int i1 = stringbuffer.indexOf("\""); i1 > -1; i1 = stringbuffer.indexOf("\"", i1 + 4))
                       stringbuffer.replace(i1, i1 + 1, "&quot;");

               }
               if(flag1)
               {
                   for(int j1 = stringbuffer.indexOf("\r"); j1 > -1; j1 = stringbuffer.indexOf("\r", j1 + 2))
                       stringbuffer.insert(j1, "\\");

               }
           }
           s = stringbuffer.toString();
       }
       return s;
   }
   
   public static final double parseDouble(String s)
   {
       if(s != null && s.startsWith("$"))
           s = s.substring(1, s.length());
       if(s == null || "".equalsIgnoreCase(s))
           return 0.0D;
       else
           return Double.parseDouble(s);
   }
   
   public static final String applyFormat(String s, String s1, int i){
	   return applyFormat(s,s1,i,null);
   }
   public static final String applyFormat(String s, String s1, int i, String s2)
   {
       if(false)
       {
           System.out.println("############################");
           System.out.println("string:  " + s);
           System.out.println("formatType:  " + s1);
       }
       //if("STRING_FORMAT".equals(s1))
           return format(s);
   }
   public static final String applyDoubleFormat(String s, int i)
   {
       double d;
       System.out.println(" applyDoubleFormat incoming str "+ s);
       if(s==null || s.trim() == ""){
    	   String str ="0.";
    	   for(int j=0; j < i ;j++)
    		  str = str+"0";
    	   return str;
       }else
       System.out.println(" applyDoubleFormat incoming str "+ s);
       d = parseDouble(s);
       return formatWithPrecisionPLM(d, i);
      
   }
   public static final String applyDoubleFormat(Float f, int i)
   {
       double d;
       if(f == null)
    	   return "0";
 
       System.out.println(" applyDoubleFormat incoming str "+ f);
       d= f.doubleValue();
       return formatWithPrecisionPLM(d, i);
      
   }
   public static final String formatWithPrecisionPLM(double d, int i)
   {
       DecimalFormat decimalformat = new DecimalFormat("##0.#;-##0.#");
       decimalformat.setMinimumFractionDigits(i);
       decimalformat.setMaximumFractionDigits(i);
       if(-1 != i)
           d = roundDouble(d, i);
       return decimalformat.format(d);
   }
	public static ArrayList<String> splitString(String data, String delim){
		
		ArrayList<String> arr = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(data, delim);
		while(st.hasMoreTokens()){
			arr.add(st.nextToken().trim());
		}		
		return arr;
	}
	
	public static String formatDateField(String inputDate, String strFromFormat, String strToFormat) {

		String formatedDate = "";
		try {
			if(FormatHelper.hasContent(inputDate)){
				
				//debug("inputDate: "+inputDate);			
				SimpleDateFormat fromFormat = new SimpleDateFormat(strFromFormat);
				SimpleDateFormat toFormat = new SimpleDateFormat(strToFormat);				
				
				Date fromDate = fromFormat.parse(inputDate);
				formatedDate = toFormat.format(fromDate);
				
				//debug("formatedDate: "+formatedDate); 
			}

		} catch (Exception excp) {
			excp.printStackTrace();
		}
		return formatedDate;
	}
	public static Date formatDateFieldToNewFormat(String inputDate, String strFromFormat, String strToFormat) {

		String formatedDate = "";
		Date returnDate = new Date();
		try {
			if(FormatHelper.hasContent(inputDate)){
				
				//debug("inputDate: "+inputDate);			
				SimpleDateFormat fromFormat = new SimpleDateFormat(strFromFormat);
				SimpleDateFormat toFormat = new SimpleDateFormat(strToFormat);				
				
				Date fromDate = fromFormat.parse(inputDate);
				formatedDate = toFormat.format(fromDate);
				returnDate= toFormat.parse(formatedDate);
				//debug("formatedDate: "+formatedDate); 
			}

		} catch (Exception excp) {
			excp.printStackTrace();
		}
		return returnDate;
	}
	
	public static String formatDateForPMDB(String date)throws Exception{
		//date.replaceAll("-", "/");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(!hasContent(date))return "";
		date = date+" EST";
		String formattedDate =date ;
		Date today = df.parse(date);
		formattedDate = today.toString();
		System.out.println("date = " + formattedDate);
		return formattedDate;
	}
	public static Date  formatDateStringToDate(String date)throws Exception{
		//date.replaceAll("-", "/");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd z");
		if(!hasContent(date)){
			date = new Date().toString();
		}
		else
		date = date+" EST";
		String formattedDate =date ;
		Date today = df.parse(date);
		formattedDate = today.toString();
		System.out.println("date = " + formattedDate);
		return today;
	}
	
	public static String  formatDate(Date date, String db)throws Exception{
		if(date == null )return "";
		if(!FormatHelper.hasContent(db))
			db ="MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(db);
	//	Date today = df.format(date);
		String formattedDate = df.format(date);//today.toString();
		System.out.println("date = " + formattedDate);
		return formattedDate;
	}
	public static final double roundDouble(double d, int i)
    {
        int j = 1;
        for(int k = 0; k < i; k++)
            j *= 10;
        d *= j;
        d = Math.round(d);
        d /= j;
       
        return d;
    }
	
	public static Date format2DateFromECV(String date)throws Exception{
		//date.replaceAll("/", "-");
		//"MM/dd/yyyy HH:mm:ss"
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.setLenient(true);
		if(!hasContent(date))return null;
		date = date;
		String formattedDate =date ;
		Date today = df.parse(date);
		return today;
	}
	public static Date formatDateFromECV(String date)throws Exception{
		if(!FormatHelper.hasContent(date))
			return null;
		date.replaceAll("/", "-");
		//"MM/dd/yyyy HH:mm:ss"
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(!hasContent(date))return null;
		date = date;
		String formattedDate =date ;
		Date today = df.parse(date);
		return today;
	}
	
	public static Date formatDateFromECV(String date,String format)throws Exception{
		//date.replaceAll("-", "/");
		//"MM/dd/yyyy HH:mm:ss"
		DateFormat df = new SimpleDateFormat(format);
		SimpleDateFormat EstFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		if(!hasContent(date))
			return null;
		String formattedDate =date ;
		Date today = df.parse(date);
		
		return today;
	}
	
	  public static Date convertDateToEST() {
        
        SimpleDateFormat EstFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss aaa");
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.LONG);
        df.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
        String DateString = df.format(new Date());
      //  System.out.println("FormatHelper.convertDateToEST "+"\n Date String Format :\n" + DateString);
        
        Date timeInEST = new Date();
        try
        {
            timeInEST= EstFormat.parse(DateString);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("FormatHelper.convertDateToEST" +"\n Formatted Date :\n" + timeInEST);
        
        return timeInEST;
        
    }
}


