package com.plmviewer.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;


public class PropertyReaderUtil {

	private static Properties pbProperties;

	public static void main(String[]args){
		String test = PropertyReaderUtil.getValue("plmViewer.from");
		System.out.println("the "+test);
	}
	/**
	 * @throws IOException
	 */
     private static void loadProperties() {
		InputStream inputStream = PropertyReaderUtil.class.getClassLoader().getResourceAsStream("application.properties");  
		System.out.println(inputStream);
		pbProperties = new Properties();
		// load the inputStream using the Properties
		// TODO: Need to propagate the exception, adding try/catch for now.
		try {
			pbProperties.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("Eror loading the properties file");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Eror loading the properties file");
		}
		

	}
	public static boolean getBoolean(String s,boolean defaultValue)
    {
        boolean value = false;
        if(pbProperties == null || pbProperties.size() < 1)
            loadProperties();
        if(pbProperties.get(s)== null)
        	return defaultValue;
        else
        	return "true".equals((String)pbProperties.get(s));
    }

	/**
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {

		if (pbProperties == null) {
			loadProperties();
		}
		return pbProperties.getProperty(key);
	}
	
	public static String getValue(String key,String defaultValue) {

		if (pbProperties == null) {
			loadProperties();
		}
		String value = pbProperties.getProperty(key);
		if(!FormatHelper.hasContent(value))
			value = defaultValue;
		return value;
	}
	public static int getValue(String key,int defaultValue) {

		if (pbProperties == null) {
			loadProperties();
		}
		int value= defaultValue;
		try{
			value = Integer.parseInt(pbProperties.getProperty(key));
		}catch(Exception ex)
		{
			value = defaultValue;
		}
		if(!FormatHelper.hasContent(value))
			value = defaultValue;
		return value;
	}
	public static String getValueIgnoreCase(String key) {

		if (pbProperties == null) {
			loadProperties();
		}
		if(pbProperties != null && pbProperties.size()>0){
			Iterator iter = pbProperties.keySet().iterator();
			while(iter.hasNext()){
				String propertyName = (String)iter.next();
				if(key.equalsIgnoreCase(propertyName)){
					return pbProperties.getProperty(propertyName);
				}
			}
		}
		return pbProperties.getProperty(key);
	}
	
}
