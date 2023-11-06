/**
 *
 */
package com.plmviewer.util;

/**
 * @author uthanasekarapandian
 *
 */
public class DocumentConstant {

    //IDA3A11= 28290875,  DocumentType :Design Documents			
    public static String designDocuments = "	select att1 \"documentName\", ida3masterreference \"ida3masterreference\", att2 \"department\", "
            + " (select flextype.typename from flextype where flextype.ida2a2=28290875) \"documentType\", "
            + " to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument "
            + " where latestiterationinfo=1 and ida3a11=28290875	";

    //	IDA3A11= 28290897, DocumentType : Eng. Construction Details			
    public static String constructionDetails = "	select att1 \"documentName\",  ida3masterreference \"ida3masterreference\",att2 \"accessoriesdepartment\","
            + " att3 \"engConstdtl\",att4 \"silhouette\", (select flextype.typename from flextype where flextype.ida2a2=28290897) \"documentType\","
            + " to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\"  from LCSDocument where "
            + " latestiterationinfo=1 and ida3a11=28290897		";

    //	IDA3A11= 62450372,  DocumentType : Fabric Placement Documents			
    public static String fabricPlacementDocuments = "	select att1 \"documentName\", ida3masterreference \"ida3masterreference\", att2 \"accessoriesdepartment\","
            + "  att3 \"collection\",att4 \"introDate\",att6 \"subCollection\", "
            + " (select flextype.typename from flextype where flextype.ida2a2=62450372) \"documentType\", to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", "
            + " to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1 and ida3a11=62450372	";

    //IDA3A11= 28290960,  DocumentType : Fillers			
    public static String fillers = "	select att1 \"documentName\", ida3masterreference \"ida3masterreference\", att2 \"class\", att3 \"collection\", att4 \"department\", "
            + " att5 \"internalOffice\", att6 \"introDate\", att7 \"silhouette\", att8 \"subClass\", att9 \"subCollection\", "
            + " (select lcsseason.att1 from lcsseason where lcsseason.branchiditerationinfo = lcsdocument.num1 and "
            + " lcsseason.latestiterationinfo=1) \"season\", (select lcsfactory.att1 from lcsfactory  where "
            + " lcsfactory.branchiditerationinfo = lcsdocument.num2 and lcsfactory.latestiterationinfo=1)num2 \"serviceProvider\","
            + " (select flextype.typename from flextype where flextype.ida2a2=28290960,) \"documentType\" , to_char(createstampa2,'mm/dd/yyyy')   \"createDate\", "
            + " to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1	and ida3a11 =28290960	";

    //IDA3A11= 51112237,  DocumentType :Job Aids and Shortcuts			
    public static String jobAidsandShortcuts = " select att1 \"documentName\",  ida3masterreference \"ida3masterreference\", att2 \"intendedUsers\", "
            + " (select flextype.typename from flextype where flextype.ida2a2=51112237) \"documentType\",to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", "
            + " to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1 and ida3a11=51112237	";

    //IDA3A11= 28291069,  DocumentType : Packaging & Packing Details			
    public static String packagingAndPackingDetails = " select att1 \"documentName\",  ida3masterreference \"ida3masterreference\", att2 \"collection\",att3 \"department\", "
            + " att4 \"silhouette\", att5 \"subCollection\",att6 \"theClass\", att7 \"wearDept\",att8 \"wearSilhouette\", "
            + " (select flextype.typename from flextype where flextype.ida2a2=lcsdocument.ida3a11) \"documentType\",to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", "
            + " to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1 and ida3a11=28291069 ";

    //IDA3A11= 28291103,2829110  DocumentType : Patterns/Development and Pattern/Line Presentation			
    public static String patternsDev_LinePresentation = " select att1 \"documentName\",  ida3masterreference \"ida3masterreference\",  att2 \"theClass\", att3 \"collection\", att4 \"department\", "
            + " att5 \"finalSPpattern\", att6 \"internalOffice\", att8 \"silhouette\", att9 \"subClass\", att10 \"subCollection\", "
            + " att11 \"singleintro\", att12 \"singleSeason\", (select lcsfactory.att1 from lcsfactory  where "
            + " lcsfactory.branchiditerationinfo = lcsdocument.num2 and lcsfactory.latestiterationinfo=1) \"serviceProvider\","
            + " (select flextype.typename from flextype where flextype.ida2a2=lcsdocument.ida3a11)  \"documentType\" , to_char(createstampa2,'mm/dd/yyyy')   \"createDate\","
            + " to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1 and ida3a11 in (28291103,28291104)		";

    //	IDA3A11= 28290878,  DocumentType :Product Revision Notice			
    public static String productRevisionNotice = " select att1 \"documentName\", ida3masterreference \"ida3masterreference\", att5 \"stylenumberdescription\", att6 \"prnType\", "
            + " (select flextype.typename from flextype where flextype.ida2a2=lcsdocument.ida3a11) \"documentType\",to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", "
            + " to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1 and ida3a11=28290878	";

    //	IDA3A11= 122403831,  DocumentType : Product Safety Compliance			
    public static String productSafetyCompliance = " select att1 \"documentName\", ida3masterreference \"ida3masterreference\",  att13 \"testResultsMaintainedBy\", "
            + " (select lcssupplier.att1 from lcssupplier where lcssupplier.branchiditerationinfo=lcsdocument.num3 and "
            + " latestiterationinfo=1) \"supplier\", att14 \"year\", att2 \"cmNumber\", to_char(Date1,'mm/dd/yyyy') \"cocExpiration\", att4 \"externalAnalyticalStatus\", "
            + " to_char(Date2,'mm/dd/yyyy') \"externalAnalyticalStatusDate\",att5 \"externalTestingFacility\", att6 \"externaStatuslTestingType\",att7 \"internalXFStatus\", "
            + " to_char(Date3,'mm/dd/yyyy') \"internalXRFTestDate\",to_char(Date4,'mm/dd/yyyy') \"osilnspectionDate\",num1 \"osiScore\", att8 \"reasonForFailure\", "
            + " (select lcsfactory.att1 from lcsfactory where lcsfactory.branchiditerationinfo = lcsdocument.num2 and "
            + " latestiterationinfo=1) \"serviceProvider\",att9 \"otherExternalTestingFacility\",att10 \"otherExterTestingType\", att11 \"supplierType\", "
            + " att12 \"testPerformedBy\", att3 \"documentType\",  (select flextype.typename from flextype where "
            + " flextype.ida2a2=lcsdocument.ida3a11) \"DocumentType\",to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where "
            + " latestiterationinfo=1 and ida3a11=122403831  ";

    //IDA3A11= 172578037,  DocumentType :Trade Compliance			
    public static String tradeCompliance = " select att1 \"documentName\", ida3masterreference \"ida3masterreference\",  att2 \"status\" ,(select flextype.typename from flextype where "
            + " flextype.ida2a2=lcsdocument.ida3a11) \"DocumentType\",to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where "
            + " latestiterationinfo=1 and ida3a11=172578037	";

    //IDA3A11= 28291107,  DocumentType :Wearables Construction Detials	
    public static String wearablesConstructionDetails = "  select att1 \"documentName\", ida3masterreference \"ida3masterreference\", att2 \"constructiondetails\",att3 \"silhouette\" ,"
            + "  (select flextype.typename from flextype where flextype.ida2a2=lcsdocument.ida3a11) \"documentType\",to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", "
            + "to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1 and ida3a11= 28291107 ";

    //IDA3A11 in (46588034,60100568,172592286,28290874,28290959,28291068,28291105,28291106,5078,88647753,73998205,134525515)
    //(Training Only, Training/Business Process Solutions, Wearables TD, Confirmation Approval Comments, 
    //Engineer Approval Comments, Other, Product Approval Comments, TOL Approval Comments, Document Only, Adv Dev Tracking, Prelim Product Comments, Material Test Results)
    public static String comments = " select att1 \"documentName\",ida3masterreference \"ida3masterreference\",  (select flextype.typename from flextype where flextype.ida2a2=lcsdocument.ida3a11) \"documentType\","
            + "to_char(createstampa2,'mm/dd/yyyy')  \"createDate\", to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1  ";

    public static String SearchDocument = " select att1 \"documentName\",ida3a11  \"ida3a11\",(select flextype.typename from flextype where "
            + " flextype.ida2a2=lcsdocument.ida3a11) \"documentType\",to_char(createstampa2,'mm/dd/yyyy') \"createDate\", "
            + "to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1  ";

    public static String searchDocumentbySubType = " select att1 \"documentName\",(select flextype.typename from flextype where "
            + " flextype.ida2a2=lcsdocument.ida3a11)  \"documentType\",to_char(createstampa2,'mm/dd/yyyy') \"createDate\",  "
            + "to_char(updatestampa2,'mm/dd/yyyy') \"updateDate\" from LCSDocument where latestiterationinfo=1  "
            + " and ida3a11 = 88647753  ";

    public static String documentToPrimaryFileLink = "select  lnk.PRIMARYFILE \"primaryFile\"  "
            + " from DOCUMENTTOFILELINK lnk   where ";

    public static String documentToSecondaryFileLink = "select  lnk.SECONDARYFILE \"secondaryFile\" "
            + " from DOCUMENTTOFILELINK lnk   where ";

}
