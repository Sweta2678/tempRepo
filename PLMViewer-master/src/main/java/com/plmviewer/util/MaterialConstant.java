/**
 *
 */
package com.plmviewer.util;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialConstant {

    public static final String EdgeStainType = "EdgeStain";
    public static final String EnamelType = "Enamel";
    public static final String FillerType = "Filler";
    public static final String HardwareType = "Hardware";
    public static final String LeatherType = "Leather";
    public static final String MiscType = "Misc";
    public static final String PackagingType = "Packaging";
    public static final String TextileType = "Textile";
    public static final String ThreadType = "Thread";
    public static final String WovenTrimType = "WovenTrim";
    public static final String ZipperType = "Zipper";

    public static final String EdgeStainType_id = "6648";
    public static final String EnamelType_id = "6675";
    public static final String FillerType_id = "6797";
    public static final String HardwareType_id = "6868";
    public static final String LeatherType_id = "7084";
    public static final String MiscType_id = "7323";
    public static final String PackagingType_id = "7396";
    public static final String TextileType_id = "7578";
    public static final String ThreadType_id = "7743";
    public static final String WovenTrimType_id = "7802";
    public static final String ZipperType_id = "8038";

    public static String edgeStainSql = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\",att57 \"assignee\" ,att2 \"autoCreateMultiColor\" ,att39 \"brand\" ,"
            + "att50 \"bulkTestRegime\",att49 \"careCards\" ,att54 \"careInstructions\" ,att47 \"cmParentGroup\" ,att3 \"conceptReferencePart\" ,"
            + "att51 \"conflictFlag\" ,num6 \"currentVersion\" ,att45 \"developmentTeams\" ,att46 \"devIntros\" ,att44 \"ecVEarlyDevDesingerName\" ,"
            + "att43 \"ecVMMSlotNumber\" ,att4 \"htsCode\" ,att53 \"longLeadTime\" ,att7 \"materialComments\" ,att9 \"materialUsedByWearables\" ,"
            + "att41 \"mcg\" ,att10 \"partNumber\" ,num4 \"preConceptMaterial\" ,att52 \"resolveConflictFlag\" ,att11 \"shortName\" ,num1 \"stdCostAllSupp\" ,"
            + "att12 \"stdCostCurrency\" ,to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\" ,att13 \"stdCostEstimateFlagAllSupp\" ,att14 \"stdCostFyAllSupp\" ,"
            + "att17 \"subType\" ,att18 \"variant\" ,att56 \"washingInstructions\" "
            + "from lcsmaterial where ida3a11=6648	";

    public static String enamel = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\" ,att57 \"assignee\" ,att2 \"autoCreateMultiColor\" ,att39 \"brand\" ,"
            + "att50 \"bulkTestRegime\" ,att49 \"careCards\" ,att54 \"careInstructions\" ,att47 \"cmParentGroup\" ,att3 \"conceptReferencePart\" ,"
            + "att51 \"conflictFlag\" ,num6 \"currentVersion\" ,att45 \"developmentTeams\" ,att46 \"devIntros\" ,att44 \"ecVEarlyDevDesingerName\" ,"
            + "att43 \"ecVMMSlotNumber\" ,att4 \"htsCode\" ,att53 \"longLeadTime\" ,att7 \"materialComments\" ,att9 \"materialUsedByWearables\" ,"
            + "att41 \"mcg\" ,att10 \"partNumber\" ,num4 \"preConceptMaterial\" ,att52 \"resolveConflictFlag\" ,att11 \"shortName\" ,num1 \"stdCostAllSupp\" ,"
            + "att12 \"stdCostCurrency\" ,to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\" ,att13 \"stdCostEstimateFlagAllSupp\" ,att14 \"stdCostFyAllSupp\" ,"
            + "att17 \"subType\" ,att18 \"variant\" ,att56 \"washingInstructions\" ,"
            + "att19 \"buckleType\" ,to_char(Date2,'mm/dd/yyyy') \"cadATA\" ,to_char(Date3,'mm/dd/yyyy') \"cadETA\" ,att20 \"dap\" ,att21 \"inkColor\" ,att22 \"logoType\" ,att23 \"matingParts\" ,"
            + "to_char(Date4,'mm/dd/yyyy') \"modelATA\" ,to_char(Date5,'mm/dd/yyyy') \"modelETA\" ,num2 \"numberPC\" ,att24 \"prongMfgMethod\" from lcsmaterial where ida3a11=6675 and latestiterationinfo=1 ";

    public static String filler = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\" ,att57 \"assignee\" ,att2 \"autoCreateMultiColor\" ,att39 \"brand\" ,"
            + "att50 \"bulkTestRegime\" ,att49 \"careCards\" ,att54 \"careInstructions\" ,att47 \"cmParentGroup\",att3 \"conceptReferencePart\" ,"
            + "att51 \"conflictFlag\",num6 \"currentVersion\" ,att45 \"developmentTeams\" ,att46 \"devIntros\" ,att44 \"ecVEarlyDevDesingerName\" ,"
            + "att43 \"ecVMMSlotNumber\" ,att4 \"htsCode\" ,att53 \"longLeadTime\" ,att7 \"materialComments\" ,att9 \"materialUsedByWearables\" ,"
            + "att41 \"mcg\",att10 \"partNumber\" ,num4 \"preConceptMaterial\" ,att52 \"resolveConflictFlag\" ,att11 \"shortName\" ,num1 \"stdCostAllSupp\" ,"
            + "att12 \"stdCostCurrency\" ,to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\" ,att13 \"stdCostEstimateFlagAllSupp\" ,att14 \"stdCostFyAllSupp\" ,"
            + "att17 \"subType\" ,att18 \"variant\" ,att56 \"washingInstructions\" ,"
            + "att19 \"adhesive\" ,att20 \"texture\"  from lcsmaterial where ida3a11=6797 and latestiterationinfo=1 ";

    public static String hardware = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\" ,att57 \"assignee\" , att2 \"autoCreateMultiColor\" , att39 \"brand\" ,"
            + "att50 \"bulkTestRegime\" ,att49 \"careCards\" ,att54 \"careInstructions\",att47 \"cmParentGroup\",att3 \"conceptReferencePart\" , "
            + "att51 \"conflictFlag\",num6 \"currentVersion\" ,att45 \"developmentTeams\" , att46 \"devIntros\" ,att44 \"ecVEarlyDevDesingerName\" ,"
            + "att43 \"ecVMMSlotNumber\" ,att4 \"htsCode\",att53 \"longLeadTime\" ,att7 \"materialComments\" , att9 \"materialUsedByWearables\" ,"
            + "att41 \"mcg\",att10 \"partNumber\" ,num4 \"preConceptMaterial\" ,att52 \"resolveConflictFlag\" ,att11 \"shortName\" ,num1 \"stdCostAllSupp\" ,"
            + "att12 \"stdCostCurrency\" , to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\" , att13 \"stdCostEstimateFlagAllSupp\" , att14 \"stdCostFyAllSupp\" ,"
            + "att17 \"subType\" ,att18 \"variant\" ,att56 \"washingInstructions\" ,"
            + "att19 \"buckleType\" ,to_char(Date2,'mm/dd/yyyy') \"cadATA\" ,to_char(Date3,'mm/dd/yyyy') \"cadETA\" ,att20 \"chainLength\" ,att21 \"chainType\" ,att22 \"channelWidth\" ,att23 \"dap\" ,"
            + "att24 \"jawOpening\" ,att25 \"logoType\" ,att26 \"matingParts\" ,to_char(Date4,'mm/dd/yyyy') \"modelATA\" ,to_char(Date5,'mm/dd/yyyy') \"modelETA\" , num2 \"numLinks\" ,"
            + "num3 \"numberPC\",att27 \"orbSize\", att28 \"orbSolderPoints\", att29 \"orbWireLength\", att30 \"prongMfgMethod\", att31 \"ringType\","
            + "att32 \"rivetType\", att33 \"surfaceFinishAllSupp\"  from lcsmaterial where ida3a11=6868 and latestiterationinfo =1";

    public static String leather = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\" ,att57 \"assignee\" , att2 \"autoCreateMultiColor\" , att39 \"brand\" ,"
            + "att50 \"bulkTestRegime\" ,att49 \"careCards\" ,att54 \"careInstructions\",att47 \"cmParentGroup\", att3 \"conceptReferencePart\" , "
            + "att51 \"conflictFlag\",num6 \"currentVersion\" , att45 \"developmentTeams\" , att46 \"devIntros\" , att44 \"ecVEarlyDevDesingerName\" ,"
            + "att43 \"ecVMMSlotNumber\" ,att4 \"htsCode\",att53 \"longLeadTime\" ,att7 \"materialComments\" ,att9 \"materialUsedByWearables\" ,"
            + "att41 \"mcg\",att10 \"partNumber\" ,num4 \"preConceptMaterial\" ,att52 \"resolveConflictFlag\" ,att11 \"shortName\" ,num1 \"stdCostAllSupp\" ,"
            + "att12 \"stdCostCurrency\" , to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\" , att13 \"stdCostEstimateFlagAllSupp\" , att14 \"stdCostFyAllSupp\" ,"
            + "att17 \"subType\", att18 \"variant\",att56 \"washingInstructions\" ,"
            + "att55 \"limitations\", att48 \"materialTestRegime\", att19 \"averageSkinSizeAllSupp\", att20 \"commonName\", att21 \"fancy\", att22 \"grainTypeAllSupp\","
            + "att23 \"hideSegmentation\", att24 \"leatherEndUse\", att25 \"patentFinish\", att26 \"patternRepeat\", att27 \"wearablesLeatherType\" "
            + " from lcsmaterial where ida3a11 =7084 and latestiterationinfo =1";
    public static String misc = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\" ,att57 \"assignee\" ,att2  \"autoCreateMultiColor\" ,att39 \"brand\" ,"
            + "att50 \"bulkTestRegime\" ,att49 \"careCards\" ,att54 \"careInstructions\",att47 \"cmParentGroup\", att3 \"conceptReferencePart\" , "
            + "att51 \"conflictFlag\",num6 \"currentVersion\" , att45 \"developmentTeams\" , att46 \"devIntros\" , att44 \"ecVEarlyDevDesingerName\" , "
            + "att43 \"ecVMMSlotNumber\" ,att4 \"htsCode\",att53 \"longLeadTime\", att7 \"materialComments\", att9 \"materialUsedByWearables\" , "
            + "att41 \"mcg\" ,att10 \"partNumber\" ,num4 \"preConceptMaterial\" ,att52 \"resolveConflictFlag\" ,att11 \"shortName\" ,num1 \"stdCostAllSupp\" ,"
            + "att12 \"stdCostCurrency\" , to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\" ,att13 \"stdCostEstimateFlagAllSupp\" , att14 \"stdCostFyAllSupp\" ,"
            + "att17 \"subType\",att18 \"variant\" ,att56 \"washingInstructions\" ,"
            + "to_char(Date2,'mm/dd/yyyy') \"cadATA\" , to_char(Date3,'mm/dd/yyyy') \"cadETA\" , att19 \"inkColor\" , att20 \"materialContentAllSupp\" , att21 \"materialType\" , to_char(Date4,'mm/dd/yyyy') \"modelATA\" ,"
            + "to_char(Date5,'mm/dd/yyyy') \"modelETA\"  from lcsmaterial where ida3a11 =7323 and latestiterationinfo =1";

    public static String packaging = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\" ,att57 \"assignee\" ,att2 \"autoCreateMultiColor\" ,att39 \"brand\" ,"
            + "att50 \"bulkTestRegime\" ,att49 \"careCards\" ,att54 \"careInstructions\",att47 \"cmParentGroup\",att3 \"conceptReferencePart\" , "
            + "att51 \"conflictFlag\",num6 \"currentVersion\" ,att45 \"developmentTeams\" , att46 \"devIntros\" , att44 \"ecVEarlyDevDesingerName\" ,"
            + "att43 \"ecVMMSlotNumber\" ,att4 \"htsCode\",att53 \"longLeadTime\" ,att7 \"materialComments\" ,att9 \"materialUsedByWearables\" ,"
            + "att41 \"mcg\" ,att10 \"partNumber\" ,num4 \"preConceptMaterial\" ,att52 \"resolveConflictFlag\" ,att11 \"shortName\" ,num1 \"stdCostAllSupp\" ,"
            + "att12 \"stdCostCurrency\" ,to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\" ,att13 \"stdCostEstimateFlagAllSupp\" ,att14 \"stdCostFyAllSupp\" ,"
            + "att17 \"subType\" ,att18 \"variant\" ,att56 \"washingInstructions\" ,"
            + "att19 \"boxType\" ,att20 \"channelUse\" ,att21 \"contentAllSupp\" ,att22 \"dimensions\" ,att23 \"insertType\" ,att24 \"insertType2\" ,"
            + "att25 \"labelType\" ,att26 \"packagingCategory\" from lcsmaterial where ida3a11 =7396 and latestiterationinfo =1";

    public static String textile = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\" ,att57 \"assignee\" ,att2 \"autoCreateMultiColor\" ,att39 \"brand\"  ,"
            + "att50 \"bulkTestRegime\" ,att49 \"careCards\" ,att54 \"careInstructions\",att47 \"cmParentGroup\",att3 \"conceptReferencePart\" ,"
            + "att51 \"conflictFlag\",num6 \"currentVersion\" ,att45 \"developmentTeams\" ,att46 \"devIntros\" ,att44 \"ecVEarlyDevDesingerName\"  ,"
            + "att43 \"ecVMMSlotNumber\"  ,att4 \"htsCode\",att53 \"longLeadTime\" ,att7 \"materialComments\"  ,att9 \"materialUsedByWearables\"  ,att41 \"mcg\"  ,"
            + "att10 \"partNumber\"  ,num4 \"preConceptMaterial\"  ,att52 \"resolveConflictFlag\"  ,att11 \"shortName\"  ,num1 \"stdCostAllSupp\"  ,"
            + "att12 \"stdCostCurrency\"  ,to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\"  ,att13 \"stdCostEstimateFlagAllSupp\"  ,att14 \"stdCostFyAllSupp\"  ,"
            + "att17 \"subType\"  ,att18 \"variant\"  ,att56 \"washingInstructions\"  ,"
            + "att19 \"backingContentAllSupp\"  ,att20 \"baseFabricName\"  ,"
            + "att21 \"coloringMethodAllSupp\"  ,att27 \"fiberTypeAllSupp\",att40 \"gcc\"  ,att55 \"limitations\"  ,att32 \"standardCuttableWidth\",att33 \"surfaceFinishAllSupp\"  ,att34 \"typeOfWeaveAllSupp\"  ,"
            + "att35 \"woolType\"  ,att36 \"yarnSizeAllSupp\"  ,att22 \"contentWithBackingAllSupp\"  ,att23 \"cottonType\"  ,att24 \"fabricConstructionAllSupp\"  ,"
            + "att25 \"fabricContentAllSupp\"  ,att26 \"faceFabricWeightAllSupp\"  ,att28 \"knitPileConstruction\"  ,att29 \"knitType\"  ,"
            + "att30 \"liningWeightAllSupp\"  ,att31 \"patternRepeat\"  from lcsmaterial where ida3a11=7578 and latestiterationinfo =1";

    public static String thread = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\", att57 \"assignee\",  att2 \"autoCreateMultiColor\",  att39 \"brand\" ,  "
            + "att50 \"bulkTestRegime\" ,att49 \"careCards\" ,att54 \"careInstructions\",att47 \"cmParentGroup\",att3 \"conceptReferencePart\" ,  "
            + "att51 \"conflictFlag\",num6 \"currentVersion\" ,att45 \"developmentTeams\" ,att46 \"devIntros\" ,att44 \"ecVEarlyDevDesingerName\"  ,"
            + "att43 \"ecVMMSlotNumber\" ,att4 \"htsCode\",att53 \"longLeadTime\" ,att7 \"materialComments\" ,att9 \"materialUsedByWearables\"  ,att41 \"mcg\"  ,"
            + "att10 \"partNumber\" ,num4 \"preConceptMaterial\" ,att52 \"resolveConflictFlag\" ,att11 \"shortName\"  ,num1 \"stdCostAllSupp\" ,"
            + "att12 \"stdCostCurrency\" , to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\" ,att13 \"stdCostEstimateFlagAllSupp\"  ,att14 \"stdCostFyAllSupp\" ,"
            + "att17 \"subType\",  att18 \"variant\",  att56 \"washingInstructions\",  "
            + "att19 \"content\",  att20 \"lubricated\",  att21 \"stitchingPurpose\" from lcsmaterial where ida3a11 =7743 and latestiterationinfo=1";

    public static String wovernTrim = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\", att57 \"assignee\", att2 \"autoCreateMultiColor\", att39 \"brand\"  , "
            + "att50 \"bulkTestRegime\" , att49 \"careCards\" ,att54 \"careInstructions\",att47 \"cmParentGroup\",att3 \"conceptReferencePart\" , "
            + "att51 \"conflictFlag\",num6 \"currentVersion\" ,att45 \"developmentTeams\", att46 \"devIntros\" , att44 \"ecVEarlyDevDesingerName\" ,"
            + "att43 \"ecVMMSlotNumber\", att4 \"htsCode\", att53 \"longLeadTime\", att7 \"materialComments\", att9 \"materialUsedByWearables\", att41 \"mcg\"  ,"
            + "att10 \"partNumber\", num4 \"preConceptMaterial\", att52 \"resolveConflictFlag\", att11 \"shortName\", num1 \"stdCostAllSupp\"  ,"
            + "att12 \"stdCostCurrency\", to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\", att13 \"stdCostEstimateFlagAllSupp\", att14 \"stdCostFyAllSupp\"  ,"
            + "att17 \"subType\", att18 \"variant\", att56 \"washingInstructions\", "
            + "att36 \"thicknessAllSupp\", att37 \"totalContentAllSupp\", att40 \"gcc\", att19 \"careAndContent\", att20 \"coloringMethodAllSupp\", "
            + "att21 \"coreContentAllSupp\", att22 \"finishedGoodsCOO\", att23 \"foldPosition\",att24 \"fsBullet\",att25 \"gender\", att26 \"labelConstruction\", att27 \"labelSize\", "
            + "att28 \"labelType\", att29 \"logoType\", att30 \"openingSide\", att31 \"sided\", att32 \"sizeDescription\", att33 \"sizeDesignation\", "
            + "att34 \"surfaceContent\", att35 \"surfaceFinishAllSupp\" from lcsmaterial where ida3a11 =7802 and latestiterationinfo=1 ";

    public static String zipper = "select att1 \"materialName\",ida3a11 \"materialTypeId\",partprimaryimageurl \"partPrimaryImageUrl\",ida3masterreference \"ida3masterreference\",att38 \"activeMaterial\", att57 \"assignee\", att2 \"autoCreateMultiColor\", att39 \"brand\"  , "
            + "att50 \"bulkTestRegime\" , att49 \"careCards\", att54 \"careInstructions\", att47 \"cmParentGroup\", att3 \"conceptReferencePart\" , "
            + "att51 \"conflictFlag\" , num6 \"currentVersion\", att45 \"developmentTeams\", att46 \"devIntros\", att44 \"ecVEarlyDevDesingerName\", "
            + "att43 \"ecVMMSlotNumber\" , att4 \"htsCode\" , att53 \"longLeadTime\", att7 \"materialComments\", att9 \"materialUsedByWearables\", att41 \"mcg\" , "
            + "att10 \"partNumber\", num4 \"preConceptMaterial\", att52 \"resolveConflictFlag\", att11 \"shortName\", num1 \"stdCostAllSupp\"  , "
            + "att12 \"stdCostCurrency\", to_char(Date1,'mm/dd/yyyy') \"stdCostEffectivityAllSupp\", att13 \"stdCostEstimateFlagAllSupp\", att14 \"stdCostFyAllSupp\"  , "
            + "att17 \"subType\", att18 \"variant\", att56 \"washingInstructions\", "
            + "to_char(Date2,'mm/dd/yyyy') \"cadATA\", to_char(Date3,'mm/dd/yyyy') \"cadETA\", to_char(Date4,'mm/dd/yyyy') \"modelATA\", to_char(Date5,'mm/dd/yyyy') \"modelETA\", att19 \"separator\", att20 \"tapeContentAllSupp\", "
            + "att21 \"teethContentAllSupp\"  from lcsmaterial where ida3a11 =8038 and latestiterationinfo=1 ";

    public static String materialColor = "  select c.att1 \"colorName\",s.att1 \"supplierName\",mc.ida2a2 \"colorId\",mc.att39 \"materialColorStatus\" "
            + " from  lcsmaterialcolor mc join lcsmaterialsuppliermaster lms on mc.ida3d10 = lms.ida2a2 join lcscolor c  on mc.ida3b10 = c.ida2a2 "
            + " join lcsmaterial m  on m.ida3masterreference = mc.ida3a10 and m.latestiterationinfo = 1 join lcssupplier s on "
            + " s.ida3masterreference = lms.ida3b6 and s.latestiterationinfo =1 ";

    public static String materialColorDetail = "  select  att37 \"shippingTerms\",att38 \"standardCostCurrency\",att39 \"status\",round(num4,2) \"stdCost\",round(num5,2) \"stdCostBasis\",to_char(Date11,'mm/dd/yyyy') \"stdCostEffectivity\","
            + "  att40 \"stdCostEstimateFlag\",att41 \"stdCostFy\",Date12 \"supStdCostDate\",att42 \"supplierArticleNumber\",att43 \"supplierBasisUOM\",att44 \"supplierColorCode\","
            + "  att45 \"unitOfCurrency\",att46 \"unitOfMeasure\",att47 \"materialType\",att48 \"materialSubType\",att2 \"articleApproval\",att3 \"articleApprovalComments\","
            + "  to_char(Date1,'mm/dd/yyyy') \"articleApprovalDate\",att4 \"articleApprovalBasis\",att5 \"awbNumber\",att6 \"carrier\",att7 \"category\",att8 \"channel\",att9 \"collection\","
            + "  att10 \"colorApproval\",att11 \"colorApprovalComments\",to_char(Date2,'mm/dd/yyyy') \"colorApprovalDate\",att12 \"cuttableWidth\",att13 \"cuttableWidthFace\","
            + "  att14 \"designApprovalComments\",att15 \"developmentComments\",att16 \"endUse\",att17 \"estCuttableWidth\",att18 \"estCuttableWidthFace\","
            + "  att19 \"estFullWidth\",att20 \"estFullWidthFace\",att21 \"feedMulti\",att22 \"finalColorApproval\",to_char(Date3,'mm/dd/yyyy') \"finalColorApprovalDate\",att23 \"fullWidth\","
            + "  att24 \"fullWidthFace\",att25 \"htsCode\",att26 \"partNumber\",att27 \"pegRate\",round(num2,2) \"productionMinimum\",att28 \"productionTolerance\",att29 \"qaApproval\","
            + "  to_char(Date4,'mm/dd/yyyy') \"qaApprovalDate\",to_char(Date5,'mm/dd/yyyy') \"requestDate\",round(num3,2) \"requestedBy\",to_char(Date6,'mm/dd/yyyy') \"rmStandardIssuedDate\",att30 \"rmStandardQuantity\",att32 \"sampleAWBNumber\","
            + "  att33 \"sampleComments\",round(num1,2) \"productionLeadTime\",to_char(Date7,'mm/dd/yyyy') \"sampleETA\",to_char(Date8,'mm/dd/yyyy') \"samplePassesToDesignDate\",att34 \"sampleQuantity\",Date9 \"sampleReceivedDate\","
            + "  to_char(Date10,'mm/dd/yyyy') \"sampleRequestDate\",att35 \"sampleSubmitComments\",att36 \"sampleType\",att53 \"associatedIntroDates\","
            + "  ( select  lcsseason.att1 from lcsseason  where lcsseason.branchiditerationinfo=lcsmaterialcolor.num9  and "
            + "  lcsseason.latestiterationinfo =1) \"QuaterFiscalYrProductType\",att49 \"designerName\",att50 \"sampleQtyMin\",att51 \"sampleQtyLeadtime\","
            + "  to_char(Date13,'mm/dd/yyyy') \"artworkColorReceivedFromDesign\",to_char(Date14,'mm/dd/yyyy') \"artworkSubmittedtoSupplier\",to_char(Date15,'mm/dd/yyyy') \"labDipStrikoffETDfromSupplier\",to_char(Date16,'mm/dd/yyyy') \"artworkColorPassedtoDesign\","
            + "  to_char(Date17,'mm/dd/yyyy') \"artworkRejectedbyDesign\",att52 \"priceComments\",att31 \"rmStandardSentTo\",att55 \"sampleReservation\",att56 \"samplePDQty\",att57 \"sampleMWQty\","
            + "  att58 \"pdMwReservation\",att60 \"mcAverageSkinSize\",att61 \"mcBrand\",att54 \"associatedTeams\",to_char(Date18,'mm/dd/yyyy') \"shippedDate\",att62 \"MSCconflictFlag\","
            + "  round(num10,2) \"MSCcurrentVersion\",att63 \"MSCresolveConflictFlag\",att59 \"mcFabricContent\",to_char(Date19,'mm/dd/yyyy') \"sampleRequestedShipDate\",att64 \"updatedByUser\","
            + "  att65 \"ecVRelatedSR\", "
            + "  ( select  lcscountry.att1 from lcscountry  where lcscountry.branchiditerationinfo=lcsmaterialcolor.num6  and "
            + "  lcscountry.latestiterationinfo =1) \"countryOfOrigin\", "
            + "  ( select  seasongroup.att1 from seasongroup  where seasongroup.branchiditerationinfo=lcsmaterialcolor.num7  and  "
            + "  seasongroup.latestiterationinfo =1) \"introDate\",(select lcslifecyclemanaged.att1 from lcslifecyclemanaged  where  "
            + "  lcslifecyclemanaged.ida3a8=6230  and lcslifecyclemanaged.ida2a2 = lcsmaterialcolor.num8) \"productTeam\"  from lcsmaterialcolor where  ";

    public static String MaterialPricing = "  select mpe.att1 \"currency\" , ida3a5 \"matSupplierId\" , "
            + " case when mpe.ida3b5 <>0 then (select (select lcscolor.att1 from lcscolor where ida2a2 = msc.ida3b10) "
            + " from lcsmaterialcolor msc where msc.ida2a2 = ida3b5)  else null end \"color\", "
            + " mpe.price \"price\", to_char(mpe.indate, 'mm/dd/yyyy') \"fromDate\",to_char(mpe.outdate, 'mm/dd/yyyy') \"toDate\","
            + " CASE WHEN mpe.indate is null THEN 'Estimated'  ELSE 'Final' END \"costType\"  from materialpricingentry mpe "
            + " where  ";
    public static String materialtypecase = "CASE ida3a11 WHEN 6648 THEN 'EdgeStain'"
            + " WHEN 6675 THEN 'Enamel' "
            + " WHEN 6797 THEN 'Filler' "
            + " WHEN 6868 THEN 'Hardware' "
            + " WHEN 7084 THEN 'Leather' "
            + " WHEN 7323 THEN 'Misc' "
            + " WHEN 7396 THEN 'Packaging' "
            + " WHEN 7578 THEN 'Textile' "
            + " WHEN 7743 THEN 'Thread' "
            + " WHEN 7802 THEN 'WovenTrim' "
            + " WHEN 8038 THEN 'Zipper' else 'others' end";

}
