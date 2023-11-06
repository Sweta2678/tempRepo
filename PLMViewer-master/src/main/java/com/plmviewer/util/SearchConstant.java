/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plmviewer.util;

/**
 *
 * @author jagadesan
 */
public interface SearchConstant {

    public String AccessoriesDepart = "'D01', 'D02','D03', 'D04','D05','D06','D08','D10','D14', 'D23'";
    public String FootWearDepart = "'D11','D19'";
    public String FragranceDepart = "'D17','D21'";
    public String JewelryDepart = "'D13'";
    public String SunWearDepart = "'D12','D20'";
    public String WatchesDepart = "'D09','D18'";
    public String WearablesDepart = "'D07','D15','D16','D22'";

    public static final String accessoriesDepartType = "Accessories";
    public static final String fragranceDepartType = "Fragrance";
    public static final String footWearDepartType = "FootWear";
    public static final String jewelryDepartType = "Jewelry";
    public static final String sunWearDepartType = "SunWear";
    public static final String watchesDepartType = "Watches";
    public static final String wearablesDepartType = "Wearables";

    public static final String accessoriesSql = ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att63 and attributename = 'STYLEGROUP' ) \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num49 and re.latestiterationinfo=1 ) \"sizeScale\" ";
    public static final String footWearSql = ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att58 and attributename = 'STYLEGROUP' ) \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1  ) \"sizeScale\" ";
    public static final String fragranceSql = ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att55 and attributename = 'STYLEGROUP' ) \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1  ) \"sizeScale\" ";
    public static final String jewelrySql = ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att55 and attributename = 'STYLEGROUP' ) \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1  ) \"sizeScale\" ";
    public static final String sunWearSql = ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att55 and attributename = 'STYLEGROUP' ) \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1  ) \"sizeScale\" ";
    public static final String watchesSql = ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att55 and attributename = 'STYLEGROUP' ) \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num48 and re.latestiterationinfo=1  ) \"sizeScale\" ";
    public static final String wearablesSql = ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att55 and attributename = 'STYLEGROUP' ) \"styleGroup\",(select att1 from lcsrevisableentity re  where re.ida3a10=14280 and re.branchiditerationinfo= prodarev.num49 and re.latestiterationinfo=1  ) \"sizeScale\" ";

    public static final String selectstatement = "select att44 \"styleNumber\",att42 \"slotNumber\",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att49 and attributename = 'DEPARTMENT' ) \"department\""
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att89 and attributename = 'COLLABORATION' ) \"collaboration\"" 
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.statestate and attributename = 'LIFECYCLESTATE' ) \"prodLifeCycleState\""
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att25 and attributename = 'GENDER' ) \"gender\",att1 \"styleName\" "
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att50 and attributename = 'CLASS' )  \"styleClass\""
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att51 and attributename = 'SUBCLASS' ) \"subclass\""
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att13 and attributename = 'COLLECTION' ) \"collection\""
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att45 and attributename = 'SUBCOLLECTION' ) \"subcollection\""
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att40 and attributename = 'SILHOUETTE' )\"silhouette\""
            + ",(select display from PRODUCTATTRIBUTESTORE where valuekey = prodarev.att97 and attributename = 'ATTITUDE' ) \"attitude\""
            + ",(initcap(replace(prodarev.att98,'|~*~|',',' ))) \"Look\""
            + ",(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att27) \"introDate\""
            + ",(select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2=prodarev.num47) \"team\" ";
    
    
      
  

 

}
