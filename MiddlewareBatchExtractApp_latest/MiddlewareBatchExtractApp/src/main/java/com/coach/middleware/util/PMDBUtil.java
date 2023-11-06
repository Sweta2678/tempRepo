package com.coach.middleware.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.coach.middleware.extractloader.LPPRICESExtract;


public class PMDBUtil {
	private static final Logger logger = Logger
	.getLogger(PMDBUtil.class);
	public static String space(int numberOfSpaces) {
		StringBuffer returnString = new StringBuffer(5);
		for (int i = 0; i < numberOfSpaces; i++) {
			returnString.append(" ");
		}
		String returnString2 = returnString.toString();
		return returnString2;
	}
	public static boolean CheckForLength(String value,int length)
	{
		if(!isNullOrEmpty(value)&&value.length()>length)
		{
			return true;
		}
		return false;
	}
	public static boolean isNullOrEmpty(String value) {

		if (value != null && value.trim().length() > 0) {
			return false;
		}

		return true;
	}
	public static String checkForNull(String value) {
		if (value == null || value.equalsIgnoreCase("null")) {
			value = "";
		}
		return value.trim();
	}
	public static String checkColorCodeNull(String value) {
		if (value == null || value=="") {
			value = "N/A";
		}
		return value.trim();
	}
	public static Double checkForNull(Double value) {
		if (value == null) {
			value = new Double(0.0f);
		}
		return value;
	}
	
	
	public static String convertTimestampToDate(Timestamp string) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				MaterialConstants.FORMAT_DATE_YYYY_MM_DD);
		String valuetoReturn = MaterialConstants.EMPTY;
		if (string != null) {
			valuetoReturn = sdf.format(string);
		}
		return valuetoReturn;

	}
	public static String getUnitOfMeasure(String key){
		String value="";
		HashMap<String,String> uomKeyValue = new HashMap<String, String>();
		uomKeyValue.put("BUNDLE","BD");
		uomKeyValue.put("CM","CM");
		uomKeyValue.put("EACH","EA");
		uomKeyValue.put("FEET","FT");
		uomKeyValue.put("GALLON","GL");
		uomKeyValue.put("GRAMS","G");
		uomKeyValue.put("INCH","IN");
		uomKeyValue.put("KILOGRAM","KG");
		uomKeyValue.put("LBS","LBS");
		uomKeyValue.put("LM","LM");
		uomKeyValue.put("METER","M");
		uomKeyValue.put("OUNCE","OZ");
		uomKeyValue.put("PACK","PAC");
		uomKeyValue.put("PAIR","PR");
		uomKeyValue.put("ROLL","ROL");
		uomKeyValue.put("SET","SET");
		uomKeyValue.put("SHEET","SHT");
		uomKeyValue.put("SQFEET","SF");
		uomKeyValue.put("SQMETER","SM");
		uomKeyValue.put("SQYARD","SY");
		uomKeyValue.put("YARD","YD");
		value=uomKeyValue.get(key);
		uomKeyValue.clear();
		return value;
	}
	public static String getStatusValue(String key){
		String value="";
		HashMap<String,String> statusMap = new HashMap<String, String>();
		statusMap.put("ACTIVE","Active");
		statusMap.put("CANCELLED","Cancelled");
		statusMap.put("CIF","CIF");
		statusMap.put("COMPLETED","Completed");
		statusMap.put("CONCEPT","In Concept");
		statusMap.put("CONDITIONAL","Conditional");
		statusMap.put("DROPPED","Dropped");
		statusMap.put("DROPPED","Dropped from Line/Cancelled");
		statusMap.put("EXW","EXW");
		statusMap.put("FAIL","Fail");
		statusMap.put("FOB","FOB");
		statusMap.put("IMDCANCELLED","IMD Cancelled");
		statusMap.put("IMDCOMPLETED","IMD Completed");
		statusMap.put("IMDRECDBUTNOTSTARTED","IMD Received But Not Started");
		statusMap.put("IMDWIP","IMD Work-In-Progress");
		statusMap.put("INACTIVE","Inactive");
		statusMap.put("LIMITEDUSE","Limited Use");
		statusMap.put("MERCHHASNOTSENT","Merchant Has Not Sent");
		statusMap.put("NO","No");
		statusMap.put("PASS","Pass");
		statusMap.put("PASSBYFM","Pass by FM");
		statusMap.put("PASSBYLM","Pass by LM");
		statusMap.put("PASSEXISTINGFINISH","Pass - Existing Finish");
		statusMap.put("PASSNEEDTESTING","Pass - Needs Testing");
		statusMap.put("PASSWITHOUTTESTING","Pass Without Testing");
		statusMap.put("PASSWITHWAIVER","Pass with Waiver");
		statusMap.put("PENDING","Pending");
		statusMap.put("PENDINGART","Pending-Art");
		statusMap.put("PREFERRED","Preferred");
		statusMap.put("PRERELEASED","PreReleased");
		statusMap.put("RELEASED","Released");
		statusMap.put("REQUESTED","Requested");
		statusMap.put("RND","R&D");
		statusMap.put("SUBMIT","Submit");
		value=statusMap.get(key);
		statusMap.clear();
		return value;
	}
	public static String underscore(String value) {
		if (value == null || value.equals(MaterialConstants.EMPTY)) {
			value = MaterialConstants.UNDERSCORE;
		}
		return value.trim();
	}
	public static String toUpperCase(String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		value = new StringBuffer().append(value.substring(0, 1).toUpperCase())
				.append(value.substring(1)).toString();
		return value;
	}
	public static String checkEstimate(String  value)
	{
		if (value.equals(MaterialConstants.ONE))
			{
			return MaterialConstants.ESTIMATED_YES_FLAG;
			}else if (value.equals(MaterialConstants.ZERO))
			{
				return MaterialConstants.ESTIMATED_NO_FLAG;	
			}else 
			{
		      return MaterialConstants.EMPTY;
			}
	}
	public static String getEstimate(String estimate) {

		if (estimate==null || estimate.equals("false") || estimate.equals("No")
				|| estimate.equals("")) {
			return "0";
		} else {
			return "1";
		}
	}
	public static Timestamp getFormatedDate() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		return currentTimestamp;
	}
	public static void writeRDYFile(String fileName,String data, String fileDir) {
		System.out.println("Write rdy file started");
		PrintWriter out = null;
		File file = new File(fileDir);
		try {
		if (fileName == null) {
			logger.error(" Rdy File Name is null");
			return;
		}
		if (fileName != null && fileName.trim().length() > 0) {
			int i = fileName.indexOf('.');

			if (i == -1) {
				fileName = fileName.trim() + ".rdy";
			} else {
				fileName = fileName.substring(0, i).trim() + ".rdy";
			}
			logger.info("fileName:" + fileName);
		}
		if(file.exists())
			out = new PrintWriter(new FileWriter(file+"/"+fileName));
		else{
			file.mkdir();
		out = new PrintWriter(new FileWriter(file+"/"+fileName));
		}
			out.print(data);
		} catch (IOException e) {
			logger.error("writeToFile(String, String)", e); //$NON-NLS-1$
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
