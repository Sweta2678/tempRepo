package com.plmviewer.util;
import java.util.*;

public class SortHelper {
	
	public static final HashMap<String,Collection<FlexObject>> groupIntoCollections(Collection<FlexObject> collection, String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Index value must not be null.");
        HashMap<String,Collection<FlexObject>> hashmap = new HashMap<String,Collection<FlexObject>>();
        Vector<FlexObject> obj;
        String s1;
        int i =0;
        for(Iterator<FlexObject> iterator = collection.iterator(); iterator.hasNext(); hashmap.put(s1, obj))
        {
            FlexObject flexobject = (FlexObject)iterator.next();
            if(i==0)
            	System.out.println(flexobject);
            i++;
            s1 = flexobject.getString(s);
            if(s1 == null)
                s1 = "";
            obj = (Vector<FlexObject>)hashmap.get(s1);
            if(obj == null)
                obj = new Vector<FlexObject>();
            ((Collection<FlexObject>) (obj)).add(flexobject);
        }

        return hashmap;
    }

	 public static Collection<FlexObject> sortFlexObjects(Collection<FlexObject> collection, String s)
	    {
		 FlexObjectComparator flexobjectComparator = new FlexObjectComparator(s);
	        Vector<FlexObject> vector = new Vector<FlexObject>(collection);
	        Collections.sort(vector, flexobjectComparator);
	        return vector;
	    }
	 
	 public static Vector<LinkedHashMap<String,String>> convertFlexObjectsIntoMap(Collection<FlexObject> collOfFlexObject,Collection<String>columnOrder){
		 
		 Vector<LinkedHashMap<String,String>> newCollection = new Vector<LinkedHashMap<String,String>>();
		 if(collOfFlexObject == null || collOfFlexObject.size()< 1)
			 return newCollection;
		 for(FlexObject flexObject : collOfFlexObject){
			 LinkedHashMap<String,String> flexObjToMap= new  LinkedHashMap<String,String>();
			 
			 for(String key : columnOrder){
				 flexObjToMap.put(key, flexObject.getString(key));
			 }
			 System.out.println("flexObjToMap "+ flexObjToMap);
			 newCollection.add(flexObjToMap);
		 }
		 return newCollection;
	 }
	 
	 public static String formatString(String incomingString){
		 if(incomingString == null ||"".equals(incomingString) || "0".equals(incomingString) ||"".equals(incomingString.trim())|| "undefined".equalsIgnoreCase(incomingString))
				 return "";
		 else
			 return incomingString;
	 }
}

