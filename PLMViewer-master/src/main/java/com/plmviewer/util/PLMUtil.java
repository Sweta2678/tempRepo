/**
 * 
 */
package com.plmviewer.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author uthanasekarapandian
 *
 */
@Component
public class PLMUtil {
	
	public List<String> getProductSearch(){
		List<String> productsearchOption = new ArrayList<String> ();
		productsearchOption.add("Style#");
		productsearchOption.add("Product Name");
		productsearchOption.add("Material Name");
		productsearchOption.add("Material CM Number");
		productsearchOption.add("Document Name");

		return productsearchOption;
	}
	
	public Map<String,String> getProductStyleSearch(){
		Map<String,String>  subSearchOption = new LinkedHashMap<String,String> ();
		subSearchOption.put("Details", "Details");
		subSearchOption.put("Specifications", "Specifications");
		subSearchOption.put("Images", "Images");
		subSearchOption.put("BOM", "BOM");
		subSearchOption.put("Sample", "Sample");
		subSearchOption.put("Sourcing", "Sourcing");
		return subSearchOption;
	}
	
	public Map<String,String> getDocumentSearch(){
		Map<String,String>  subSearchOption = new LinkedHashMap<String,String> ();
		subSearchOption.put("Details", "Details");
		
		return subSearchOption;
	}
	
	
	public Map<String,String> getMaterialSearch(){
		Map<String,String>  subSearchOption = new LinkedHashMap<String,String> ();
		subSearchOption.put("Details", "Details");
		subSearchOption.put("Colors", "Colors");
		subSearchOption.put("Images", "Images");
		subSearchOption.put("Sourcing", "Sourcing");
		subSearchOption.put("Testing", "Testing");
		subSearchOption.put("Documents", "Documents");
		return subSearchOption;
	}
	
	public LinkedHashMap<String,String>getBomSectionMap(){
		LinkedHashMap<String,String>sectionMap = new LinkedHashMap<String,String> ();
		sectionMap.put("shell","Shell");
		sectionMap.put("trim","Exterior Trim");
		sectionMap.put("facings","Facings");
		sectionMap.put("interior","Interior Trim");
		sectionMap.put("lining","Lining");
		sectionMap.put("hardware","Hardware");
		sectionMap.put("thread","Thread");
		sectionMap.put("mtm","Edgestain");
		sectionMap.put("misc","Miscellaneous");
		sectionMap.put("collateral","Collateral");
		sectionMap.put("packaging","Packaging");

		return sectionMap;
	}
	
	public static final float parseFloat(String s)
    {
        if(s == null || "".equalsIgnoreCase(s))
            return 0.0F;
        else
            return Float.parseFloat(s);
    }


}
