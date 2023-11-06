package com.coach.middleware.util;

import java.text.DecimalFormat;

public class CoachUtil {
	public static  String padd(int tolength,String value){
		StringBuffer sb = new StringBuffer();
		try{
			
			if(value == null){
				value = "";
			}
			sb.append(value);
			for(int i=value.length();i<tolength;i++){
				sb.append(" ");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(value.length()>tolength){
			return sb.toString().substring(0, tolength);
		}
		return sb.toString();
		
	}
	
	public static String formatWithoutSymbols(double d, int i)
    {
		DecimalFormat decimalformat = new DecimalFormat("###############.###############");
        if(-1 != i)
        {
            decimalformat.setMinimumFractionDigits(i);
            decimalformat.setMaximumFractionDigits(i);
            d = roundDouble(d, i);
        }
        return decimalformat.format(d);
    }
	public static String toInitCap(String param) {
		if(param != null && param.length()>0){			
			char[] charArray = param.toCharArray(); // convert into char array
			charArray[0] = Character.toUpperCase(charArray[0]); // set capital letter to first postion
			return new String(charArray); // return desired output
		}else{
			return "";
		}
	}

public static double roundDouble(double d, int i)
    {
        int j = 1;
        for(int k = 0; k < i; k++)
            j *= 10;

        
        d *= j;
        d = Math.round(d);
        d /= j;
        
        return d;
    } 


	public static String getIntroWithCalendarYear(String inputString) throws Exception {
		if(hasContent(inputString) && inputString.indexOf("/") != -1) {
			//remove product type
			String tmp = inputString.substring(0, inputString.lastIndexOf("/") + 5);			
			//replace FY12 with 2011
			String part1 = tmp.substring(0, tmp.lastIndexOf("/") + 1);
			//System.out.println("part1 : " + part1);
			String part2 = tmp.substring(tmp.lastIndexOf("FY") + 2, tmp.length());
			//System.out.println("part2 : " + part2);
			String month = tmp.substring(0, tmp.indexOf("/"));
			//System.out.println("month : " + month);
			int iMonth = new Integer(month).intValue();			
			if(new Integer(part2).intValue() == 0) {
				if(iMonth >= 7 && iMonth <= 12)
					part2 = "1999";
				else 
					part2 = "2000";
			} else if(new Integer(part2).intValue() > 0 && new Integer(part2).intValue() < 50) {
				if(iMonth >= 7 && iMonth <= 12)
					part2 = String.valueOf(1999 + new Integer(part2).intValue());
				else 
					part2 = String.valueOf(2000 + new Integer(part2).intValue());
			} else if(new Integer(part2).intValue() > 50) {
				if(iMonth >= 7 && iMonth <= 12)
					part2 = String.valueOf(1900 + new Integer(part2).intValue() -1);
				else
					part2 = String.valueOf(1900 + new Integer(part2).intValue());
			}			
			tmp = part1.trim() + part2.trim();
			tmp = tmp.trim();
			return tmp;
		} else {
			return inputString;
		}
	}
	
	public static String ConvertCMtoInch(String dimention) throws Exception {
		DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
		String cm_x = " cm x ";
		String in_x = " in x ";
		String cm = " cm";
		String in = " in";
		//String dimensionsCms = "";
		String dimensionsInch = "";
		//1 cm = 0.393700
		double cms2InchConversion = 0.393700;
		//1 inch = 2.54
		double inch2CmsConversion = 2.54;
		double dd;
		double dd2dec;
		double lengthInch;
		double widthInch;
		double heightInch;
		if(hasContent(dimention)) {
			dd = new Double(dimention).doubleValue();
			lengthInch = cms2InchConversion * (new Double(dd).doubleValue());
			dd2dec = new Double(df2.format(dd)).doubleValue();
			dimention = String.valueOf(dd2dec);
			dimention = dimention + cm;
			
			dd2dec = new Double(df2.format(lengthInch)).doubleValue();
			dimensionsInch = getNearestRoundedValue(dd2dec, 0.25) + in;
			return dimensionsInch;
		} else {
			dimention = "";
			dimensionsInch = "";
		}
		return dimensionsInch;
	}
	public static String roundup(String dimention) throws Exception {
		DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
		String cm_x = " cm x ";
		String in_x = " in x ";
		String cm = " cm";
		String in = " in";
		//String dimensionsCms = "";
		String dimensionsInch = "";
		//1 cm = 0.393700
		//double cms2InchConversion = 0.393700;
		//1 inch = 2.54
		double inch2CmsConversion = 2.54;
		double dd;
		double dd2dec;
		double lengthInch;
		double widthInch;
		double heightInch;
		if(hasContent(dimention)) {
			dd = new Double(dimention).doubleValue();
			//lengthInch = cms2InchConversion * (new Double(dd).doubleValue());
			dd2dec = new Double(df2.format(dd)).doubleValue();
			dimention = String.valueOf(dd2dec);
			dimention = dimention + cm;
			
			//dd2dec = new Double(df2.format(lengthInch)).doubleValue();
			//dimensionsInch = getNearestRoundedValue(dd2dec, 0.25) + in;
			return dimention;
		} else {
			dimention = "";
			dimensionsInch = "";
		}
		return dimention;
	}

	public static void getDimensions(String totoLengthCm,String totoHeightCm,String totoWidthCm,String totoStrapLengthCms,String totoDropLengthCms) throws Exception {
		DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
		String cm_x = " cm x ";
		String in_x = " in x ";
		String cm = " cm";
		String in = " in";
		//String dimensionsCms = "";
		String dimensionsInch = "";
		//1 cm = 0.393700
		double cms2InchConversion = 0.393700;
		//1 inch = 2.54
		double inch2CmsConversion = 2.54;
		double dd;
		double dd2dec;
		double lengthInch;
		double widthInch;
		double heightInch;
		if(hasContent(totoLengthCm)) {
			dd = new Double(totoLengthCm).doubleValue();
			lengthInch = cms2InchConversion * (new Double(dd).doubleValue());
			dd2dec = new Double(df2.format(dd)).doubleValue();
			totoLengthCm = String.valueOf(dd2dec);
			totoLengthCm = totoLengthCm + cm;
			
			dd2dec = new Double(df2.format(lengthInch)).doubleValue();
			dimensionsInch = getNearestRoundedValue(dd2dec, 0.25) + in;
		} else {
			totoLengthCm = "";
			dimensionsInch = "";
		}
		if(hasContent(totoHeightCm)) {
			dd = new Double(totoHeightCm).doubleValue();
			heightInch = cms2InchConversion * (new Double(dd).doubleValue());
			dd2dec = new Double(df2.format(dd)).doubleValue();
			totoHeightCm = String.valueOf(dd2dec);
			totoHeightCm = totoHeightCm + cm;
			
			dd2dec = new Double(df2.format(heightInch)).doubleValue();
			dimensionsInch = getNearestRoundedValue(dd2dec, 0.25) + in;
		} else {
			totoHeightCm = "";
			dimensionsInch = "";
		}
		if(hasContent(totoWidthCm)) {
			dd = new Double(totoWidthCm).doubleValue();
			widthInch = cms2InchConversion * (new Double(dd).doubleValue());
			dd2dec = new Double(df2.format(dd)).doubleValue();
			totoWidthCm = String.valueOf(dd2dec);
			totoWidthCm = totoWidthCm + cm;
			
			dd2dec = new Double(df2.format(widthInch)).doubleValue();
			dimensionsInch = getNearestRoundedValue(dd2dec, 0.25) + in;
		}else {
			totoWidthCm = "";
			dimensionsInch = "";
		}
	
		String totoStrapLengthInches = "";
		if(hasContent(totoStrapLengthCms)) {
			dd = new Double(totoStrapLengthCms).doubleValue();
			double inches = cms2InchConversion * (new Double(dd).doubleValue());
			dd2dec = new Double(df2.format(dd)).doubleValue();
			totoStrapLengthCms = String.valueOf(dd2dec);
			totoStrapLengthCms = totoStrapLengthCms + " cm";
			
			dd2dec = new Double(df2.format(inches)).doubleValue();
			totoStrapLengthInches = getNearestRoundedValue(dd2dec, 0.25) + in;
		} else {
			totoStrapLengthCms = "";
		}
	
		String totoDropLengthInches = "";
		if(hasContent(totoDropLengthCms)) {
			dd = new Double(totoDropLengthCms).doubleValue();
			double inches = cms2InchConversion * (new Double(dd).doubleValue());
			dd2dec = new Double(df2.format(dd)).doubleValue();
			totoDropLengthCms = String.valueOf(dd2dec);
			totoDropLengthCms = totoDropLengthCms + " cm";
			
			dd2dec = new Double(df2.format(inches)).doubleValue();
			totoDropLengthInches = getNearestRoundedValue(dd2dec, 0.25) + in;
		} else {
			totoDropLengthCms = "";
		}
		
	}
	public static String getNearestRoundedValue(double inputValue, double nearestRoundedTo) throws Exception {
		String roundedToNearestValue = null;
		DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
		DecimalFormat dfNoDecimal = new DecimalFormat( "#,###,###,##0" );
		inputValue = new Double(df2.format(inputValue)).doubleValue();
		
		int integerPart = (int) inputValue;		
		double decimalPart = (double)(inputValue - integerPart);
				
		double divisor = decimalPart / nearestRoundedTo;		
		divisor = new Double(dfNoDecimal.format(divisor)).doubleValue();
				
		double finalRoundedDecimalPart = nearestRoundedTo * divisor;
		//System.out.println("finalRoundedDecimalPart : " + finalRoundedDecimalPart);		
		double roundedToNearestRoundValue = integerPart + finalRoundedDecimalPart;
		
		roundedToNearestValue = df2.format(roundedToNearestRoundValue);		
		//System.out.println("roundedToNearestValue : " + roundedToNearestValue);
			
		return roundedToNearestValue;		
	}
	
	public static final boolean hasContent(String s)
    {
        if(s == null || "null".equals(s) || "undefined".equals(s) || "0".equals(s) || "0.0".equals(s))
            return false;
        for(int i = 0; i < s.length(); i++)
            if(!Character.isWhitespace(s.charAt(i)))
                return true;

        return false;
    }
}
