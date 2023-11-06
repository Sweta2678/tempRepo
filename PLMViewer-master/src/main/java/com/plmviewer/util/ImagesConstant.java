/**
 * 
 */
package com.plmviewer.util;

/**
 * @author uthanasekarapandian
 *
 */
public class ImagesConstant {
	
	public  static String imgSearch =" select imglink.documentname \"docName\", imglink.documentname \"pageName\",imglink.pagelayout  \"pageLayout\", imglink.pagedescription \"pageDesc\", "
     +"  doc.thumbnaillocation \"thumbNailLocation\", p.att44 \"styleNum\", p.att42 \"slotNum\", p.att1 \"styleName\", "  
     +"   (select display from PRODUCTATTRIBUTESTORE where valuekey = p.att50 and attributename = 'CLASS' ) \"theClass\","
     +"(select display from PRODUCTATTRIBUTESTORE where valuekey = p.att51 and attributename = 'SUBCLASS' ) \"subClass\","
     +"(select display from PRODUCTATTRIBUTESTORE where valuekey = p.att13 and attributename = 'COLLECTION') \"collection\","
     +"(select display from PRODUCTATTRIBUTESTORE where valuekey = p.att45 and attributename = 'SUBCOLLECTION') \"subCollection\", "
     +"    imglink.pageType \"pageType\",p.ida3MasterReference \"ida3MasterRef\",imglink.documentmasterid \"docMasterId\"  from IMAGESPAGEDOCTOPRODUCTLINK imglink, prodarev p, lcsdocument doc "
     +"  where  doc.latestiterationinfo =1 and doc.statecheckoutinfo <> 'wrk' and doc.ida3a11 = '6401' and " ;
     
	 public static String prodSpecImgList = " select documentname \"pageName\",pagetype \"pageType\",pagelayout \"pageLayout\"," +
		   		" pagedescription \"pageDesc\",image \"image\",imagetype \"imagecolType\", secondarycontentfile \"secondarycontentfile\","+
		" case when imagetype = 'IMAGE1' THEN 1 when imagetype = 'IMAGE2' THEN 2 when imagetype = 'IMAGE3' THEN 3  "+
		" when imagetype = 'IMAGE4' THEN 4 when imagetype = 'IMAGE5' THEN 5  when imagetype = 'IMAGE6' THEN 6 "+
		" when imagetype = 'IMAGE7' THEN 7 when imagetype = 'IMAGE8' THEN 8 when  imagetype = 'IMAGE9' THEN 9  "+
		"  end as \"imagePosition\" from imagespagedoctoproductlink  unpivot (image for imagetype in (image1,image2,image3,image4,image5,image6,image7,image8,image9)) ";

}
