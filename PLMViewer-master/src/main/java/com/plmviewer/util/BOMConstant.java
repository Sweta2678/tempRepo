package com.plmviewer.util;

import java.util.Date;

public class BOMConstant {
	
	public static String flexBomPartDetails  =" select  f.att1 \"bomPartName\", "+
		"  f.ida3masterreference \"bomPartMasterReference\"  from flexbompart f, prodarev p  where " +
		" f.ida3a12 = p.ida3masterreference  and f.latestiterationinfo=1 and f.statecheckoutinfo <>'wrk'  ";

	
	public static String flexBomLinkDetails  =" SELECT FLEXBOMLINK.IDA3A5 \"ida3a5\", FLEXBOMLINK.IDA3B5 \"ida3b5\", FLEXBOMLINK.IDA3C5 \"ida3c5\", FLEXBOMLINK.IDA3G5 \"ida3g5\", "+ 
	 " FLEXBOMLINK.PARENTREV \"parentRev\", FLEXBOMLINK.WIP \"wip\", FLEXBOMLINK.BRANCHID \"branchId\",FLEXBOMLINK.INDATE \"inDate\", FLEXBOMLINK.OUTDATE \"outDate\", FLEXBOMLINK.SEQUENCE \"sequence\", "+
     " FLEXBOMLINK.YIELD \"yield\", FLEXBOMLINK.IDA3E5 \"ida3e5\" , FLEXBOMLINK.IDA3F5 \"ida3f5\", FLEXBOMLINK.IDA3H5 \"ida3h5\", FLEXBOMLINK.IDA3D5 \"ida3d5\", FLEXBOMLINK.SIZE1 \"size1\", FLEXBOMLINK.SIZE2 \"size2\", "+
	 " FLEXBOMLINK.SORTINGNUMBER \"sortingNumber\", FLEXBOMLINK.IDA2A2 \"ida2a2\", FLEXBOMLINK.DROPPED \"dropped\",FLEXBOMLINK.DIMENSIONID \"dimensionId\", FLEXBOMLINK.DIMENSIONNAME \"dimensionName\", FLEXBOMLINK.MASTERBRANCHID \"masterBranchId\", "+
     " FLEXBOMLINK.MASTERBRANCH \"masterBranch\",  FLEXBOMLINK.num5 \"num5\", FLEXBOMLINK.att10 \"att10\", FLEXBOMLINK.att12 \"att12\",FLEXBOMLINK.att4 \"att4\", FLEXBOMLINK.date2 \"date2\", FLEXBOMLINK.num2 \"num2\", "+
	 " FLEXBOMLINK.att6 \"att6\", FLEXBOMLINK.att14 \"att14\", FLEXBOMLINK.att2 \"att2\", FLEXBOMLINK.num4 \"num4\", FLEXBOMLINK.att13 \"att13\", FLEXBOMLINK.att5 \"att5\", FLEXBOMLINK.att9 \"att9\", FLEXBOMLINK.num1 \"num1\", "+
     " FLEXBOMLINK.att3 \"att3\", FLEXBOMLINK.att1 \"att1\", FLEXBOMLINK.att8 \"att8\", FLEXBOMLINK.att11 \"att11\", FLEXBOMLINK.num3 \"num3\", FLEXBOMLINK.num6 \"num6\", LCSMATERIAL.IDA3MASTERREFERENCE \"lcsMaterialIda3masterRef\",LCSMATERIAL.att10 \"partNumber\",LCSMATERIAL.att1 \"materialName\", "+
	 "  LCSSUPPLIER.att1 \"supplierName\",LCSSUPPLIER.ida3masterreference \"lcsSupplierIda3MasterRef\", LCSMATERIALCOLOR.ida2a2 \"lcsMaterialColorId2a2\", LCSCOLOR.ATT1 \"lcsColorAtt1\",LCSCOLOR.ida2a2 \"lcsColorIda2a2\" FROM "+ 
     " FLEXBOMLINK, LATESTITERLCSMATERIAL LCSMATERIAL, LCSSUPPLIERMASTER, LCSSUPPLIER,LCSMATERIALSUPPLIER, LCSMATERIALSUPPLIERMASTER,  LCSCOLOR, LCSMATERIALCOLOR  "+  
     " WHERE LCSMATERIAL.IDA3MASTERREFERENCE(+) = FLEXBOMLINK.IDA3B5 AND LCSSUPPLIERMASTER.IDA2A2 = FLEXBOMLINK.IDA3C5 AND LCSSUPPLIERMASTER.IDA2A2 = LCSSUPPLIER.IDA3MASTERREFERENCE AND "+ 
     " LCSMATERIALSUPPLIER.IDA3MASTERREFERENCE = LCSMATERIALSUPPLIERMASTER.IDA2A2 AND  LCSMATERIALSUPPLIERMASTER.IDA3B6 = FLEXBOMLINK.IDA3C5 AND "+
     " LCSMATERIALSUPPLIERMASTER.IDA3A6 = FLEXBOMLINK.IDA3B5 AND LCSCOLOR.IDA2A2(+) = FLEXBOMLINK.IDA3D5 AND LCSMATERIALCOLOR.IDA2A2(+) = FLEXBOMLINK.IDA3G5  AND "+
     " FLEXBOMLINK.PARENTREV = 'A'  AND FLEXBOMLINK.OUTDATE IS NULL   AND FLEXBOMLINK.IDA3F5 = '0' AND FLEXBOMLINK.IDA3H5 = '0' AND FLEXBOMLINK.SIZE1 IS NULL  " +
     " AND FLEXBOMLINK.SIZE2 IS NULL  AND LCSSUPPLIER.STATECHECKOUTINFO <> 'wrk' AND LCSSUPPLIER.LATESTITERATIONINFO = '1' AND LCSMATERIALSUPPLIER.STATECHECKOUTINFO <> 'wrk' AND  " +
     " LCSMATERIALSUPPLIER.LATESTITERATIONINFO = '1'   "; 
	
	
	public static String skuInfoDetails =" select (select att1 from lcsrevisableentity where ida3a10='14230' and latestiterationinfo =1 and statecheckoutinfo <> 'wrk' "+
			"and lcsrevisableentity.branchiditerationinfo = s.num12) \"skuGroupName\", "+
			"s.att1 \"skuName\", "+
			"(select c.att7 from lcscolor c where c.ida2a2=s.num13) \"skuFullName\", "+
			"s.att6 \"bomSkuStatus\", "+
			"case when s.att27 is null  then (select sgp.name from seasongroupmaster sgp where sgp.ida2a2=prdarev.att27) "+
			"else (select sgp.name from seasongroupmaster sgp where sgp.ida2a2=s.att27) END \"introDate\", "+
			"case when s.att24 is null  then (select sgp.name from seasongroupmaster sgp where sgp.ida2a2=prdarev.att24) "+
			"else (select sgp.name from seasongroupmaster sgp where sgp.ida2a2=s.att24)END \"fsIntroDate\", "+
			"case when s.num47 <1 then (select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2=prdarev.num47) else "+
			"(select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2=s.num47) END \"skuTeam\" "+
			"from skuarev s,prodarev prdarev where s.ida3a12=prdarev.ida3masterreference and prdarev.ida3masterreference = ";


}
