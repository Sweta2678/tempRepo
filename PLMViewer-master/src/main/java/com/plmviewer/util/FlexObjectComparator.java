package com.plmviewer.util;

import java.util.Comparator;
public class FlexObjectComparator implements Comparator {
	public String comparingAttribute;
	
	    public FlexObjectComparator(String s)
	    {
	        comparingAttribute = s;
	    }

	    @SuppressWarnings("unused")
		public int compare(Object obj, Object obj1)
	    {
	        String s = ((FlexObject)obj).getString(comparingAttribute);
	        Float float1 = new Float(parseFloat(s));
	        String s1 = ((FlexObject)obj1).getData(comparingAttribute);
	        Float float2 = new Float(parseFloat(s1));
	       // String s1 = ((FlexObject)obj1).getString(comparingAttribute);
	        if(float1 == null && float2 != null)
	            return -1;
	        if(float2 == null && float1 != null)
	            return 1;
	        if(float1 == null && float2 == null)
	        {
	            return 0;
	        } else
	        {
	            int i = float1.compareTo(float2);
	            return i;
	        }
	    }
	    
	    public static final float parseFloat(String s)
	    {
	        if(s == null || "".equalsIgnoreCase(s))
	            return 0.0F;
	        else
	            return Float.parseFloat(s);
	    }
	    
	}


