package com.coach.middleware.extractloader;

import com.coach.middleware.extractloader.Exceptions.CoachExtractException;

public interface IExtractLoader {
	public boolean launchExtract(String extractname,String runmode,String propertyPath) throws  Exception;
	public static String LPSKUExtract = "LPSKU";
	public static String TOTOExtract = "TOTO";
	public static String LPPRICESExtract = "LPPRICES";
	public static String MATMSTRXExtract = "MATMSTRX";
	public static String MATPRICEExtract = "MATPRICE";
	public static String PRODMASTExtract = "PRODMAST";
	public static String RPFTPABCExtract = "RPFTPABC";
	public static String SAPMATLPExtract = "SAPMATLP";
	public static String SAPMCOMPLPExtract = "SAPCOMPLP";
	public static String LPSKUSAPExtract = "LPSKUSAP";
	public static String ESSSTYLECOLORExtract = "ESSSTYLECOLOR";
	public static String ESSSTYLEExtract = "ESSSTYLE";
	public static String BATCHMETADATACONFIG = "BATCHMETADATACONFIG";
	public static String DELTAEXTRACT = "DELTAEXTRACT";
	public static String PRODMASTDWExtract = "PRODMASTDW";
	public static String PRODUCTRENAMEExtract = "PRODUCTRENAME";
}

