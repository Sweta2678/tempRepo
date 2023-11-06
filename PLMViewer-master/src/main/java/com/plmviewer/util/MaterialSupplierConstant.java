/**
 * 
 */
package com.plmviewer.util;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialSupplierConstant {
	
	public static String materialSupplier = "  select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1) \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\",  att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " + 
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\" , " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\", " +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\"  " +
			"  from lcsmaterialsupplier where ida3masterreference = ? and latestiterationinfo =1 ";
	
	public static String materialSupplierFiller = " select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\" "+
			"  from lcsmaterialsupplier where  latestiterationinfo =1 and ida3a10 = 6797   and " ;
	
	public static String materialSupplierEdgeStain = " select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", "+
			"  att14  \"chemicalcontent\"  from lcsmaterialsupplier where  latestiterationinfo =1 AND IDA3A10=6648  and  ";
	
	public static String materialSupplierEnamel = " select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(to_char(Date4,'mm/dd/yyyy') ,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", "+
			"  att14 \"manufMethod\", att15 \"metalContent\", num6 \"toolingCharges\", att16 \"toolingChargesPaid\", to_char(Date5,'mm/dd/yyyy')  \"toolingPaidDate\"  " +
			"  from lcsmaterialsupplier where  latestiterationinfo =1 AND IDA3A10=6675   and ";
	
	public static String materialSupplierHardware = " select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", "+
			"  att14 \"metalcontent\" ,att15 \"manufMethof\" ,att16 \"surfaceFinishmsHardware\" ,num6 \"toolingCharges\" ," +
			"  att17 \"toolingPaid\" ,to_char(Date5,'mm/dd/yyyy')  \"toolingPaidDate\"  from lcsmaterialsupplier where latestiterationinfo =1 AND IDA3A10=6868 "+
			"  and   ";
	
	public static String materialSupplierLeather =  "  select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", "+
			"  to_char(Date9,'mm/dd/yyyy')  \"articleMasterSent\" ,att14 \"averageSkinSize\" ,att15 \"cities\" ,to_char(Date5,'mm/dd/yyyy')  \"classificationDate\" ," +
			"  to_char(Date6,'mm/dd/yyyy')  \"classificationRequestDate\" ,to_char(Date7,'mm/dd/yyyy') \"etd\" ,att16 \"exoticCommonName\" ,att17 \"exoticMaterial\" ," +
			"  att18 \"fishAndWildlife\" ,att19 \"harvestMethod\" ,(select att1 from lcscountry where branchiditerationinfo=lcsmaterialsupplier.num6) \"hideCOO\" ," +
			"  att20 \"placeOfHarvest\" ,att21 \"prohibited\" ,att22 \"scientificName\" ,to_char(Date8,'mm/dd/yyyy') \"shipDate\" ,att23 \"unrestricted\" " +
			"  from lcsmaterialsupplier where  latestiterationinfo =1 AND IDA3A10=7084  and ";

	
	public static String materialSupplierMisc =  "  select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", "+
			"  att14 \"cities\" ,to_char(Date5,'mm/dd/yyyy')  \"classificationDate\" ,to_char(Date6,'mm/dd/yyyy')  \"classificationRequestDate\" ,att15 \"exoticCommonName\" ," +
			"  att16 \"exoticMaterial\" ,att17 \"fishAndWildlife\" ,att18 \"harvestMethod\" ,att19 \"materialContent\" ,att20 \"placeOfHarvest\" ," +
			"  att21 \"prohibited\" ,att22 \"scientificName\" ,num6 \"toolingCharges\" ,att23 \"toolingPaid\" ,to_char(Date7,'mm/dd/yyyy') \"toolingPaidDate\" " +
			"  from lcsmaterialsupplier where latestiterationinfo =1 AND IDA3A10=7323  and ";
	
	public static String materialSupplierTextile =  "  select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\",to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", "+
			"  att14 \"backedByMill\" ,att15 \"backingContent\" ,num6 \"backingLeadtime\" ,att16 \"backingMinimum\" ," +
			"  att40 \"bulkMinimumColor\" ,att18 \"contentWithBacking\" ,att19 \"cuttableWidth\" ,att20 \"cuttableWidthFace\" ," +
			"  att38 \"dyeMethod\" ,att21 \"estCuttableWidth\" ,att22 \"estCuttableWidthFace\" ,att23 \"estFullWidth\" ," +
			"  att24 \"estFullWidthFace\" ,att25 \"fabricConstruction\" ,att26 \"fabricContent\" ,att27 \"faceFabricWeight\" ," +
			"  att28 \"fullWidth\" ,att29 \"fullWidthFace\" ,att37 \"printMethod\" ,num7 \"sampleLeadTime\" ,att30 \"sampleMinimum\" ," +
			"  att31 \"surfaceFinishnms\",att48 \"yarnSizeMS\"  " +
			"  from lcsmaterialsupplier where latestiterationinfo =1 AND IDA3A10=7578  and ";
	
	public static String materialSupplierWovenTrim =  "  select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", " +
			"  att14 \"coloringMethodMs\", att15 \"coreContentms\", att16 \"surfaceFinishms\", att17 \"thicknessms\", att18 \"totalContentms\"" +
			"  from lcsmaterialsupplier where latestiterationinfo =1 AND IDA3A10=7802   and ";
	
	public static String materialSupplierThread =  "   select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\" "+
			"  from lcsmaterialsupplier where latestiterationinfo =1 and IDA3A10=7743   and  ";

	
	public static String materialSupplierZipper =  "   select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", " +
			"  att14 \"tapeContent\" ,att15 \"teethContent\" ,num6 \"toolingCharges\" ,att16 \"toolingPaid\" ,to_char(Date5,'mm/dd/yyyy')  \"toolingPaidDate\" "+
			"  from lcsmaterialsupplier where latestiterationinfo =1 AND IDA3A10=8038  and " ;
	
	
	public static String materialSupplierPackaging =  "   select att32 \"activeMaterialSupplier\", att1 \"articleApproval\", " +
			"  att2 \"articleApprovalComments\", to_char(Date1,'mm/dd/yyyy')  \"articleApprovalDate\", att39 \"articleMasterLocation\", " +
			"  att34 \"associatedIntros\", att35 \"associatedTeams\", att3 \"channel\", ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialsupplier.num8  and lcscountry.latestiterationinfo =1)  \"countryOfOrigin\", to_char(Date10,'mm/dd/yyyy')  \"ffpDate\", " +
			"  att44 \"ffpStatus\", (select name from seasongroupmaster where ida2a2=trim(att33)) \"initialIntroDate\", att45 \"longLeadTimeMS\", num1 \"materialPrice\", " +
			"  att36 \"minimumOrderQuantity\", att41 \"MSconflictFlag\", num10 \"MScurrentVersion\", att42 \"MSresolveConflictFlag\", " +
			"  att4 \"pegRate\", num2 \"productionLeadTime\", " +
			"  num3 \"productionMinimum\", att5 \"productionTolerance\", (select att1 from lcslifecyclemanaged where ida2a2=lcsmaterialsupplier.num9) \"productTeam\", to_char(Date2,'mm/dd/yyyy')  \"requestDate\", (select name from wtuser where ida2a2=lcsmaterialsupplier.num4) \"requestedBy\",  " +
			"  att6 \"shippingTerms\", num5 \"stdCostBasis\", att7 \"stdCostCurrencySupplier\", to_char(Date3,'mm/dd/yyyy')  \"stdCostEffectivity\", " +
			"  att8 \"stdCostEstimateFlag\", att9 \"stdCostFy\", att10 \"supplierArticleNumber\"," +
			"  att11 \"supplierBasisUOM\", to_char(Date4,'mm/dd/yyyy')  \"supStdCostDate\", att12 \"unitOfCurrency\", att13 \"unitOfMeasureSupp\", " +
			"  att14  \"materialcontentms\"  from lcsmaterialsupplier where  latestiterationinfo =1 AND IDA3A10=7396   and ";

			
			

}
