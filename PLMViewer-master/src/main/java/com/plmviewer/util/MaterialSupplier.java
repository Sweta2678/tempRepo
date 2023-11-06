/**
 * 
 */
package com.plmviewer.util;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialSupplier {
	
	//--- To get the list of supplier for Material ( input in Material.ida3masterreference or Material MasterID )
	public static String materialSupplier = " select  ms.ida2a2 ,supplier.att1  from 	lcsmaterialsuppliermaster ms, " +
			"lcsmaterial mat, lcssupplier supplier where ms.ida3a6 = mat.ida3masterreference and" +
			" ms.ida3b6 = supplier.ida3masterreference and mat.latestiterationinfo=1 and supplier.latestiterationinfo = 1 " +
			" and ms.placeholder =0  " ;
	
	


	

	//--- To get the list of Color for Material and Supplier  ( input in MaterialSupplier.ida3masterreference or MaterialSupplier MasterID )
	public static String materialSupplierColor =" select (select c.att1 from lcscolor where ida2a2 = msc.ida3b10)ColorName from materialcolor," +
			" materialsuppliermaster  where materialcolor.ida3d10= materialSupplierMaster.ida3masterreference  ";


	// --- To get detail attributes on mateiral supplier ( input in MaterialSupplier.ida3masterreference or MaterialSupplier MasterID  )-- will change based on Materialtype
	public static String materialSupplierAttributes = "  select ms.att1, material.att1, material.att10, s.att1 from lcsmaterialsuppliermaster  lms " +
			"join lcsmaterialsupplier ms on lms.ida2a2 = ms.ida3masterreference and  ms.latestiterationinfo =1 and  " +
			" ms.ida3masterreference =<?> and lms.placeholder =0  join lcsmaterial material on  " +
			" lms.ida3a6= material.ida3masterreference and material.latestiterationinfo=1 join 	lcssupplier s on  " +
			"  s.ida3masterreference = lms.ida3b6 and s.latestiterationinfo =1 and  "; 


	//-- To get Material Color details 
	public static String materialColorAttributes = "   select mc.att26,s.att1,c.att1,mc.ida2a2,mc.num4,mc.att41,mc.date11, " +
			"  mc.att38,mc.ida2a2 from lcsmaterialcolor mc join lcsmaterialsuppliermaster lms 	on  mc.ida3d10 = lms.ida2a2  " +
			"  join lcscolor c 	on mc.ida3b10 = c.ida2a2 	join lcsmaterial m  on m.ida3masterreference = mc.ida3a10 " +
			"	and m.latestiterationinfo = 1  	join lcssupplier s on  	s.ida3masterreference = lms.ida3b6 	and s.latestiterationinfo =1  ";

}
