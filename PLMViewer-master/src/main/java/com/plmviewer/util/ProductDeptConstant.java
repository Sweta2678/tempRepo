/**
 * 
 */
package com.plmviewer.util;



/**
 * @author uthanasekarapandian
 *
 */
public class ProductDeptConstant {
	
	public String[]  AccessoriesDepart ={"D01", "D02", "D03", "D04", "D05", "D06", "D08", "D10", "D14", "D23"};
	public String[]  FootWearDepart= {"D11", "D19"};
	public String[]  FragranceDepart= {"D17","D21"};
	public String[]  JewelryDepart={"D13"};
	public String[]  SunWearDepart={"D12","D20"};
	public String[]  WatchesDepart={"D09","D18"};
	public String[]  Wearables={"D07","D15","D16","D22"};
	
	public static final String accessoriesDepartType= "Accessories";
	public static final String fragranceDepartType= "Fragrance";
	public static final String footWearDepartType= "FootWear";
	public static final String jewelryDepartType= "Jewelry";
	public static final String sunWearDepartType= "SunWear";
	public static final String watchesDepartType= "Watches";
	public static final String wearablesDepartType= "Wearables";
	public static final String merchFullPrice ="MerchandisingFullPrice";
	public static final String merchCJIPrice ="MerchandisingCJIPrice";
	public static final String merchFactoryPrice ="MerchandisingFactoryPrice";
	
	
	public String[] skuAccessoriesDepart = {"D01", "D02", "D03", "D04", "D05", "D06", "D08", "D10", "D14",
			"D23","D07","D15","D16","D22"};
	public String[] skuFragranceDepart = {"D17", "D21","D12","D20"};
	public String[] skuFootwearDepart = {"D09", "D18"};
	
	public static final String skuAccessoriesDepartType= "Accessories";
	public static final String skuFragranceDepartType= "Fragrance";
	public static final String skuFootWearDepartType= "FootWear";
        public static final String skuSunWearDepartType= "SunWear";
        
	
	public static final String prodDptSql="select att6 \"abbrStyleName\",att7 \"additionalFeatures\",num1 \"additive\",att8 \"adopted\",att9 \"adoptedDesignOption\",att97 \"attitude\",att94 \"attitudinalSegments\",num2 \"averageCost\","+
	    	"round(num3,2) \"averageCurrentCost\",num4 \"averageFinalStandardCost\",num7 \"averagestdCostWsaleMUPct\",num5 \"averageWorkingCost1\",num6 \"averageWorkingCost2\",att10 \"benchStyleForSilhoutte\","+
	    	"att11 \"benchStyleforTargetMargin\",att2 \"CalcWholeSale1\",att83 \"careCardCm\",att12 \"channelExclusive\",att50 \"productClass\",att89 \"collaboration\",att13 \"collection\",att14 \"costAgreement\","+
	    	"att87 \"dampCloth\",(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att15) \"deleteDate\",att49 \"department\",att18 \"designRequestComments\",to_char(date1, 'mm/dd/yyyy') \"earlyReleaseDate\",att95 \"emblishment\",att86 \"fabricCleaner\","+
	    	"att22 \"factorystoreTypeProduct\",num8 \"freightCost\",(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att23) \"fsDeleteDate\",(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att24) \"fsIntroDate\","+
	    	"num9 \"fsPromoPrice\",num10 \"fsTicketPrice\",num11 \"fxRate\",att88 \"gcc\",att25 \"gender\","+
	    	"att90 \"gifting\",att26 \"hardwareColor\",(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att27) \"introDate\",att74 \"jewelryDimensions\",round(num12,2) \"jpYenRetail\",round(num13,2) \"jpYenRetailWithTax\",att84 \"leatherCleaner\",att85 \"leatherMoisturizer\","+
	    	" att29 \"longLeadTimeMaterial\",att98 \"look\",att30 \"material\",att4 \"MerchCJIPrices\",att69 \"merchDropped\",att5 \"MerchFactoryPrices\",att32 \"oneTimeBuy\",num14 \"overhead\",att33 \"parentStyle\","+
	    	"num15 \"pctOffRetail\",num16 \"pctOffTicket\",att92 \"pint\",att34 \"planExclusion\",num17 \"poCost\",round(num18,2) \"premiumRate\",att96 \"productidentity\",att91 \"productSegmentation\",att35 \"proposedStyleNumber\",num19 \"retailPrice\",att38 \"rounding\",att39 \"signatureType\","+
	    	 "att40 \"silhouette\",att42 \"slot\",att43 \"specialProduct\",num20 \"stdFSPromoGMPct\",num21 \"stdFSTicketGMPct\",att44 \"styleNumber\",att51 \"subClass\",att45 \"subCollection\",round(num22,2) \"targetCost\",num23 \"targetFSPromoGMPct\","+
	    	"round(num24,2) \"targetFSTargetGMPct\",round(num25,2) \"targetRetailGMPct\",(select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2=prodarev.num47) \"team\",(select wt.name from wtuser wt where wt.ida2a2 =num26) \"teamAD\",(select wt.name from wtuser wt where wt.ida2a2 =num27) \"teamAsstAD\",(select wt.name from wtuser wt where wt.ida2a2 =num28) \"teamAsstDD\",(select wt.name from wtuser wt where wt.ida2a2 =num29) \"teamAsstDesigner\"," +
	    	"(select wt.name from wtuser wt where wt.ida2a2 =num30) \"teamDD\",(select wt.name from wtuser wt where wt.ida2a2 =num31) \"teamDesigner1\",(select wt.name from wtuser wt where wt.ida2a2 =num32) \"teamDesigner2\",(select wt.name from wtuser wt where wt.ida2a2 =num33) \"teamEngineer\",(select wt.name from wtuser wt where wt.ida2a2 =num34) \"teamMerchant1\",(select wt.name from wtuser wt where wt.ida2a2 =num35) \"teamMerchant2\",(select wt.name from wtuser wt where wt.ida2a2 =num36) \"teamPreProduction\",(select wt.name from wtuser wt where wt.ida2a2 =num37) \"teamProduction\",(select wt.name from wtuser wt where wt.ida2a2 =num38) \"teamTechnicalDesigner\",(select wt.name from wtuser wt where wt.ida2a2 =num39) \"totalCost\",att80 \"totoBillfoldPockets\","+
	    	" att81 \"totoCheckbook\",att73 \"totoCollection\",att71 \"totoColors\",num54 \"totoDropLengthIn\",att79 \"totoExteriorPocket\",num51 \"totoHeightCm\",att78 \"totoInteriorPocket\",num50 \"totoLengthCm\",att77 \"totoLiningMaterial\",att75 \"totoShellMaterial\","+
	    	 " att72 \"totoSizes\",num53 \"totoStrapLengthIn\",att76 \"totoTrimMaterial\",att82 \"totoTypeOfSole\",num52 \"totoWidthCm\",num40 \"tradeMUPct\",att48 \"updated\",round(num41,2) \"wholesaleMUPct\",num42 \"wholesalePrice\",round(num43,2) \"workingCost1FSPromoGMPct\",round(num44,2) \"workingCost1FSTargetGMPct\","+
	    	"round(num45,2) \"workingCost1RetailGMPct\"";
	public static final String accessoriesSql=",att52 as \"idwindow\",att54 \"closures\",att55 \"coin\",att56 \"creditCardPocket\",att57 \"crossBody\",att59 \"handbagSize\",att61 \"keyringDimensions\",att62 \"organizationalPanel\","+
			" num48 \"sampleFacility\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num49 and re.latestiterationinfo=1 ) \"sizeScale\",att63 \"styleGroup\" ";
	public static final String footWearSql=",att61 \"toeshape\",att62 \"heelHeight1\",att58 \"styleGroup\",att59 \"soletype\",att60 \"toetype\",att54 \"upperMaterial\"," +
			"   (select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1) \"sizeScale\" ";
	public static final String fragranceSql=",att55 \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1) \"sizeScale\" "; 
	public static final String jewelrySql=",att55 \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1) \"sizeScale\" ";
	
        public static final String sunWearSql=",att55 \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1) \"sizeScale\", att58 \"lens\" ";
	
        public static final String watchesSql=", att55 \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1) \"sizeScale\", att59 \"facecolor\",att63 \"function\",att64 \"watchCaseShape\",att62 \"watchCaseSize\" ";
	public static final String wearablesSql=",num48 \"sampleFacility\",att55 \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num49 and re.latestiterationinfo=1) \"sizeScale\",att52 \"pdtType\" ";
	
	
	//MerchandisingFullPrice:
		public static final String merchandisingFullPrice ="select attStore.display \"wholeSalePrice\", to_char(moa.date1,'mm/dd/yyyy') \"endDate\", moa.num4 \"freight\", moa.att1 \"moaBoolean\"," +
				" moa.num5 \"oldPoCost\", moa.num6 \"overhead\", moa.num1 \"POcost\", moa.num7 \"retailPrice\", to_char(moa.date2,'mm/dd/yyyy') \"startDate\", round(moa.num8,2) \"targetCost\", moa.num9 \"totalCost\", moa.num10 \"tradeMU\", moa.num2 \"WHMU\", moa.num3 \"WHPrice\"  " +
				"from lcsmoaobject moa,Prodarev prodarev,productAttributeStore attStore where attStore.valuekey = moa.att2 and moa.ida3a4=8596 and moa.ida3a5= prodarev.ida3masterreference ";
		 //and ida3a5 <>37057311 and dropped =0 order by rownum

		//MerchandisingCJIPrice
		public static final String merchandisingCJIPrice = " select round(moa.num1,2) \"cjiJPY\",to_char(moa.date1,'mm/dd/yyyy') \"endDate\", moa.num2 \"fxRate\", round(moa.num3,2) \"jpyTaxIncluded\", attStore.display \"merchCJIPrices\", moa.att2 \"moaBooleanCJI\"," +
				"  round(moa.num4,2) \"premiumRate\", to_char(moa.date2,'mm/dd/yyyy') \"startDate\" from lcsmoaobject  moa,Prodarev prodarev  ,productAttributeStore attStore where  attStore.valuekey = moa.att1 and moa.ida3a4=8571 and moa.ida3a5= prodarev.ida3masterreference  "; 

	//	MerchandisingFactoryPrices
		public static final String merchandisingFactoryPrice =" select round(moa.num4,2) \"offRetail\", moa.num5 \"pctOffTicket\", moa.num1 \"additive\", to_char(moa.date1,'mm/dd/yyyy') \"endDate\", moa.num2 \"fsPromoPrice\", moa.num3 \"fsTicket\", " +
				" attStore.display \"merchFactoryPrices\", moa.att2 \"moaBooleanFact\", moa.att3 \"rounding\", to_char(moa.date2,'mm/dd/yyyy') \"startDate\"  from lcsmoaobject moa,Prodarev prodarev,productAttributeStore attStore  where  attStore.valuekey = moa.att1 and moa.ida3a4=8582 and moa.ida3a5= prodarev.ida3masterreference  ";             


		//SKUGROUPTABLE
		public static final String skuGroupTable =" select (select re.att1 from lcsrevisableentity re where re.branchiditerationinfo = moa.num1 and re.latestiterationinfo=1 ) "+
		" from lcsmoaobject moa,ProdaRev prodarev  where moa.ida3a4=8634 and moa.ida3a5 = prodarev.ida3masterreference  ";
		
		public static final String skuDeptSql = "select case when sku.att15 is null then prdarev.att15 else sku.att15 END  \"deleteDate\",case when sku.att39 is null then prdarev.att39 else sku.att39 END \"signatureType\",case when sku.att23 is null then prdarev.att23 else sku.att23 END \"fsDeleteDate\", case when sku.att24 is null then prdarev.att24 else sku.att24 END \"fsIntroDate\","+
			    " case when sku.att26 is null then prdarev.att26 else sku.att26 END \"hardwareColor\",case when sku.att27 is null then prdarev.att27 else sku.att27 END \"introDate\", "+
			    " case when sku.att12 is null then prdarev.att12 else sku.att12 END \"channelExclusive\",case when sku.att98 is null then prdarev.att98 else sku.att98 END \"look\",case when sku.num22 is null then prdarev.num22 else sku.num22 END \"targetCost\", "+
			    " case when sku.num23 is null then prdarev.num23 else sku.num23 END \"targetFSPromoGMPct\",case when sku.num24 is null then round(prdarev.num24,2) else round(sku.num24,2) END \"targetFSTargetGMPct\", "+
			    " case when sku.att18 is null then prdarev.att18 else sku.att18 END \"designRequestComments\",case when sku.att2 is null then prdarev.att2 else sku.att2 END \"CalcWholeSale1\",case when sku.num17 is null then prdarev.num17 else sku.num17 END \"poCost\", "+
			    " case when sku.num20 is null then prdarev.num20 else sku.num20 END \"stdFSPromoGMPct\",case when sku.num21 is null then prdarev.num21 else sku.num21 END \"stdFSTicketGMPct\", "+
			    " case when sku.num25 is null then round(prdarev.num25,2) else round(sku.num25,2) END \"targetRetailGMPct\",case when sku.num26 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num26) else (select wt.name from wtuser wt where wt.ida2a2=sku.num26) END \"teamAD\",case when sku.num27 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num27) else (select wt.name from wtuser wt where wt.ida2a2=sku.num27) END \"teamAsstAD\", "+
			    " case when sku.num28 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num28)else(select wt.name from wtuser wt where wt.ida2a2=sku.num28) END \"teamAsstDD\",case when sku.num29 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num29) else (select wt.name from wtuser wt where wt.ida2a2=sku.num29) END \"teamAsstDesigner\",case when sku.num30 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num30) else (select wt.name from wtuser wt where wt.ida2a2=sku.num30) END \"teamDD\", "+
			    " case when sku.num31 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num31) else (select wt.name from wtuser wt where wt.ida2a2=sku.num31) END \"teamDesigner1\",case when sku.num32 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num32) else (select wt.name from wtuser wt where wt.ida2a2=sku.num32) END \"teamDesigner2\",case when sku.num33 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num33) else (select wt.name from wtuser wt where wt.ida2a2=sku.num33) END \"teamEngineer\", "+
			    " case when sku.num34 =0  then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num34) else (select wt.name from wtuser wt where wt.ida2a2=sku.num34) END \"teamMerchant1\",case when sku.num35 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num35) else (select wt.name from wtuser wt where wt.ida2a2=sku.num35) END \"teamMerchant2\",case when sku.num36 =0 then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num36) else (select wt.name from wtuser wt where wt.ida2a2=sku.num36) END \"teamPreProduction\", "+
			    " case when sku.num37 =0  then (select wt.name from wtuser wt where wt.ida2a2=prdarev.num37) else (select wt.name from wtuser wt where wt.ida2a2=sku.num37) END \"teamProduction\",case when sku.num39 =0 then prdarev.num39 else sku.num39 END \"totalCost\", "+
			    " case when sku.date1 is null then to_char(prdarev.date1, 'mm/dd/yyyy') else to_char(sku.date1, 'mm/dd/yyyy') END \"earlyReleaseDate\",case when sku.num8 is null then prdarev.num8 else sku.num8 END \"freightCost\", "+
			    " (select att1 from lcsrevisableentity re  where re.ida3a10=14230 and  re.branchiditerationinfo= sku.num12 and re.latestiterationinfo=1 ) \"coachSkuGroup\", "+
			    " (select c.att1 from lcscolor c where c.ida2a2=sku.num13) \"finishedGoodColorCodes\",sku.num16 \"fpNetSalesQty\",sku.num18 \"fpWholesaleNetSales\", "+
			    " sku.num19 \"fsNetSalesCost\",sku.num40 \"fsNetSalesQty\",sku.num42 \"fsWholesaleNetSales\", "+
			    " sku.num15 \"fpNetSalesCost\",sku.att9 \"styleFGColorCode\",sku.num5 \"stdRetailGMPct\", "+
			    " sku.att3 \"abcCode\",sku.att7 \"status\",sku.num3 \"stdCost\", "+
			    " sku.num4\"stdCostWsaleMUPct\",sku.att4  \"exotic\", "+
			    " sku.att5 \"factorystoreTypeSku\", sku.num1 \"finalStandard\",sku.num2 \"mpg\", "+
			    " sku.num6 \"unitVolumeFP\",sku.num7 \"unitVolumeFS\",sku.num9 \"workingCost1\", "+
			    " sku.num10 \"workingCost2\",round(sku.num11,2) \"workingCostRetailGMPct\", "+
			    " case when sku.att29 is null then prdarev.att29 else sku.att29 END \"longLeadTimeMaterial\",case when sku.att30 is null then prdarev.att30 else sku.att30 END \"material\",case when sku.att32 is null then prdarev.att32 else sku.att32 END \"oneTimeBuy\", "+
			    " case when sku.num14 is null then prdarev.num14 else sku.num14 END \"overhead\",case when sku.num38 is null then prdarev.num38 else sku.num38 END \"teamTechnicalDesigner\",case when sku.num41 is null then prdarev.num41 else sku.num41 END \"wholesaleMUPct\", "+
			    " case when sku.num43 is null then round(prdarev.num43,2) else round(sku.num43,2) END \"workingCost1FSPromoGMPct\",case when sku.num44 is null then round(prdarev.num44,2) else round(sku.num44,2) END \"workingCost1FSTargetGMPct\"," +
			    " case when sku.num45 is null then round(prdarev.num45,2) else round(sku.num45,2) END \"workingCost1RetailGMPct\", "+
			    " case when sku.num47 <1 then (select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2=prdarev.num47) else (select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2=sku.num47) end \"team\",case when sku.num48 is null then prdarev.num48 else sku.num48 END \"sampleFacility\" "; 
	public static final String skuFootWearDeptSql = ",case when sku.att59 is null then prdarev.att59 else sku.att59 END \"facecolor\" " ;
	
	public static final String skuFragranceDeptSql = ",sku.att8 \"upccode\" ";
        
        public static final String skuSunwearDeptSql = ",sku.att8 \"upccode\" ";
        
		
	public static final String skuSizeScaleMoaSql = "select (select display from productattributestore where trim(valuekey)=moa.att1 and attributename='SIZES') \"moaSize\", moa.att2 \"upc\", moa.att3 \"vendorRefNumber\" from lcsmoaobject moa,skuaRev  sku where  moa.ida3a4=8694 and moa.ida3a5= sku.ida3masterreference ";


	                                                 																																		  
	
	public static final String skuMerchansingFullPrice ="  select attStore.display \"wholeSalePrice\", to_char(moa.date1,'mm/dd/yyyy') \"endDate\", moa.num4 \"freight\", moa.att1 \"moaBoolean\", moa.num5 \"oldPoCost\","+
  " moa.num6 \"overhead\", moa.num1 \"POcost\", moa.num7 \"retailPrice\", to_char(moa.date2,'mm/dd/yyyy') \"startDate\", round(moa.num8,2) \"targetCost\","+
 " round(moa.num9,2) \"totalCost\", moa.num10 \"tradeMU\", moa.num2 \"WHMU\", moa.num3 \"WHPrice\" from lcsmoaobject moa, skuaRev  sku,ProdaRev prodarev,productAttributeStore attStore  "+
 " where attStore.valuekey = moa.att2 and  moa.ida3a4=8596 and moa.ida3a5 <>37057311 and moa.ida3a5= prodarev.ida3masterreference  and sku.ida3a12= prodarev.ida3masterreference and ";  
 public static final String sqldates = "SELECT  CASE WHEN sku.att15 IS NULL THEN prdarev.att15 ELSE sku.att15  END  as deleteDate, CASE WHEN sku.att23 IS NULL THEN prdarev.att23    ELSE sku.att23 END as fsDeleteDate, CASE    WHEN sku.att24 IS NULL THEN prdarev.att24    ELSE sku.att24 END as fsIntroDate,  CASE WHEN sku.att27 IS NULL THEN prdarev.att27 ELSE sku.att27   END as introDate,case when sku.att9 is null then prdarev.att9 else sku.att9 END as styleFGColorCode FROM skuarev sku,Prodarev prdarev WHERE sku.ida3a12 = prdarev.ida3masterreference and ";
	 
}