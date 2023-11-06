/**
 * 
 */
package com.plmviewer.util;

/**
 * @author uthanasekarapandian
 *
 */
//Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
//Available From: http://www.reflections.ath.cx
//Decompiler options: packimports(3) 
//Source File Name:   FlexObject.java



import java.util.*;

public class FlexObject extends HashMap{

 public FlexObject()
 {
 }

 public Object get(Object obj)
 {
     if(obj instanceof String)
     {
         Object obj1 = super.get(((String)obj).toUpperCase());
         if(obj1 != null)
             return obj1.toString();
         else
             return null;
     } else
     {
         return super.get(obj);
     }
 }

 public Object put(String s, Object obj)
 {
     return super.put(s.toUpperCase(), obj);
 }

 public String toString()
 {
     StringBuffer stringbuffer = new StringBuffer();
     stringbuffer.append("\t[FlexObject]\n");
     Object obj;
     for(Iterator iterator = keySet().iterator(); iterator.hasNext(); stringbuffer.append("\t" + obj + " - " + get(obj) + "\n"))
         obj = iterator.next();

     return stringbuffer.toString();
 }

 public String getString(String s)
 {
     Object obj = get(s.toUpperCase());
     if(obj != null)
         return obj.toString();
     else
         return null;
 }

 public boolean getBoolean(String s)
 {
     String s1 = getString(s);
     return "true".equalsIgnoreCase(s1) || "yes".equalsIgnoreCase(s1) || "1".equals(s1);
 }

 public int getInt(String s)
 {
     String s1 = getString(s);
     if(s1 == null || "".equalsIgnoreCase(s1))
         return 0;
     else
         return Integer.parseInt(s1);
 }

 public float getFloat(String s)
 {
     String s1 = getString(s);
     if(s1 == null || "".equalsIgnoreCase(s1))
         return 0.0F;
     else
         return Float.parseFloat(s1);
 }

 public Date getDate(String s)
 {
     return (Date)get(s);
 }

 public String getData(String s)
 {
     return getString(s);
 }

 public void setData(String s, String s1)
 {
     put(s, s1);
 }

 public Collection getIndexes()
 {
     return keySet();
 }

 public FlexObject dup()
 {
     FlexObject flexobject = new FlexObject();
     Collection collection = getIndexes();
     String s;
     for(Iterator iterator = collection.iterator(); iterator.hasNext(); flexobject.put(s, get(s)))
         s = (String)iterator.next();

     return flexobject;
 }
}
