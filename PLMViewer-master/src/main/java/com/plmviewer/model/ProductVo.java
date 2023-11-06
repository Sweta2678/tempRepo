/**
 *
 */
package com.plmviewer.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.Subselect;

/**
 * @author uthanasekarapandian
 *
 */
@Entity
@Table(name = "PRODAREV")
@Access(value = AccessType.FIELD)
public class ProductVo {

    @Id
    @Column(name = "att1")
    private String styleName;

    @Column(name = "att6")//att6
    private String abbreviatedStyleName;

    @Column(name = "att44")
    private String styleNumber;
    @Column(name = "att35")
    private String licensedStyleNumber;

    @Column(name = "att49")
    private String department;

    @Column(name = "att13")
    private String collection;

    @Column(name = "att50")
    private String productClass;

    @Column(name = "att8")
    private String adopted;

    @Column(name = "att25")
    private String gender;

    @Column(name = "att15")
    private String deletedDate;
    @Transient
    private String tmpDeleteDate;
    @Column(name = "att69")
    private String dropped;

    @Column(name = "att42")
    private String slotNumber;

    @Column(name = "att45")
    private String subCollection;

    @Column(name = "att51")
    private String subClass;

    @Column(name = "date1")
    private String earlyReleaseDate;

    @Column(name = "att27")
    /*@OneToMany(targetEntity=SeasonGroupMasterVo.class)
	@JoinColumn(name="ida2a2")*/
    private String introDate;//(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att27) introDate,
    @Transient
    private String tmpIntroDate;
    @Column(name = "statestate")
    private String prodLifeCycleState;
    @Column(name = "ida3a12")
    private String ida3a12;

    @Column(name = "ida3masterreference")
    private String ida3masterreference;

    @Column(name = "PARTPRIMARYIMAGEURL")
    private String partPrimaryImageUrl;
    @Transient
    private Map<String, String> productSource;
    @Transient
    private Map<String, String> productSkuStyle;
    @Transient
    private List<String> productSpecification;
    @Transient
    private List<String> productSeason;

    @Transient
    private ProductadditionDetailsVo productadditionDetailsVo;

    @Transient
    private List<LCSMoaObjectVo> merchandisingFullPrice;
    @Transient
    private List<String> skuGroupTable;

    /**
     * @return the skuGroupTable
     */
    public List<String> getSkuGroupTable() {
        return skuGroupTable;
    }

    /**
     * @param skuGroupTable the skuGroupTable to set
     */
    public void setSkuGroupTable(List<String> skuGroupTable) {
        this.skuGroupTable = skuGroupTable;
    }

    @Transient
    private List<LCSMoaObjectVo> merchandisingCJIPrice;

    @Transient
    private List<LCSMoaObjectVo> merchandisingFactoryPrice;

    /*@Column(name="att6")
	private String abbrStyleName;
	@Column(name="att7")
	private String additionalFeatures;
	@Column(name="num1")
	private double additive;
	
	@Column(name="att9")
	private String adoptedDesignOption;
	@Column(name="att97")
	private String attitude;
	@Column(name="att94")
	private String attitudinalSegments;
	@Column(name="num2")
	private String averageCost;
	@Column(name="num3")
	private String  averageCurrentCost;
	@Column(name="num4")
	private String  averageFinalStandardCost;
	@Column(name="num7")
	private String  averagestdCostWsaleMUPct;
	@Column(name="num5")
	private String  averageWorkingCost1;
	@Column(name="num6")
	private String  averageWorkingCost2;
	@Column(name="att10")
	private String  benchStyleForSilhoutte;
	@Column(name="att11")
	private String  benchStyleforTargetMargin;
	@Column(name="att2")
	private String  CalcWholeSale1;
	@Column(name="att83")
	private String   careCardCm;
	@Column(name="att12")
	private String   channelExclusive;
	@Column(name="att89")
	private String   collaboration;
	@Column(name="att14")
	private String   costAgreement;
	@Column(name="att87")
	private String dampCloth;
	
	@Column(name="att18")
	private String   designRequestComments;
	
	@Column(name="att95")
	private String  emblishment;
	@Column(name="att86")
	private String fabricCleaner;
	@Column(name="att22")
	private String factorystoreTypeProduct;
	@Column(name="num8")
	private String freightCost;
	
	@Column(name="att23")
	@OneToMany(targetEntity=SeasonGroupMasterVo.class)
	@JoinColumn(name="ida2a2")
	private String fsDeleteDate;//(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att23) 
	@Transient
	private String tmpFSDeleteDate;
	@Column(name="att24")
	@OneToMany(targetEntity=SeasonGroupMasterVo.class)
	@JoinColumn(name="ida2a2")
	private String  fsIntroDate;	//(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att24)
	@Transient
	private String tmpFSIntroDate;
	@Column(name="num9")
	private String fsPromoPrice;
	@Column(name="num10")
	private String fsTicketPrice;
	@Column(name="num11")
	private String fxRate;
	@Column(name="att88")
	private String gcc;
	
	@Column(name="att90")
	private String gifting;
	@Column(name="att26")
	private String hardwareColor;
	
	
	@Column(name="att74")
	private String jewelryDimensions;
	@Column(name="num12")
	private String jpYenRetail;
	@Column(name="num13")
	private String jpYenRetailWithTax;
	@Column(name="att84")
	private String leatherCleaner;
	@Column(name="att85")
	private String leatherMoisturizer;
	@Column(name="att29")
	private String longLeadTimeMaterial;
	@Column(name="att98")
	private String look;
	@Column(name="att30")
	private String material;
	@Column(name="att4")
	private String MerchCJIPrices;
		@Column(name="att69")
	private String merchDropped;
	@Column(name="att5")
	private String MerchFactoryPrices;
	@Column(name="att32")
	private String oneTimeBuy;
	@Column(name="num14")
	private String overhead;
	@Column(name="att33")
	private String parentStyle;
	@Column(name="num15")
	private String pctOffRetail;
	@Column(name="num16")
	private String pctOffTicket;
	@Column(name="att92")
	private String pint
	;@Column(name="att34")
	private String planExclusion;
	@Column(name="num17")
	private String poCost;
	@Column(name="num18")
	private String premiumRate;
	@Column(name="att96")
	private String productidentity;
	@Column(name="att91")
	private String productSegmentation;
	@Column(name="att35")
	private String proposedStyleNumber;
	@Column(name="num19")
	private String retailPrice;
	@Column(name="att38")
	private String rounding;
	@Column(name="att39")
	private String signatureType;
	@Column(name="att40")
	private String silhouette;
	@Column(name="att42")
	private String Stringslot;
	@Column(name="att43")
	private String specialProduct;
	@Column(name="num20")
	private String stdFSPromoGMPct;
	@Column(name="num21")
	private String stdFSTicketGMPct;


	@Column(name="num22")
	private String targetCost;
	@Column(name="num23")
	private String targetFSPromoGMPct;
	@Column(name="num24")
	private String targetFSTargetGMPct;
	@Column(name="num25")
	private String targetRetailGMPct;
//	(select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2=prodarev.num47) team,
	
	@Column(name="num47")
	private String team;
	
	@Transient
		private String lcsTeam;
	
	
	@Column(name="att54")
	private String  att54;
	@Column(name="att55")
	private String  att55;
	@Column(name="att56")
	private String  att56;
	@Column(name="att57")
	private String  crossBodys;
	@Column(name="att59")
	private String  att59;
	@Column(name="att52")
	private String  att52;
	@Column(name="att61")
	private String  att61;
	@Column(name="att62")
	private String  att62;
	@Column(name="num48")
	private String  num48;
	@Column(name="att63")
	private String  att63 ;

	@Column(name="att58")
	private String  att58;
	@Column(name="att59")
	private String  soletype;
	@Column(name="att60")
	private String  toetype;
	@Column(name="att64")
	private String  att64;
	 

	@Column(name="num26")
	private String teamAD;
	@Column(name="num27")
	private String teamAsstAD;
	@Column(name="num28")
	private String teamAsstDD;
	@Column(name="num29")
	private String teamAsstDesigner;
	@Column(name="num30")
	private String teamDD;
	@Column(name="num31")
	private String teamDesigner1;
	@Column(name="num32")
	private String teamDesigner2;
	@Column(name="num33")
	private String teamEngineer;
	@Column(name="num34")
	private String teamMerchant1;
	@Column(name="num35")
	private String teamMerchant2;
	@Column(name="num36")
	private String teamPreProduction;
	@Column(name="num37")
	private String teamProduction;
	@Column(name="num38")
	private String teamTechnicalDesigner;
	@Column(name="num39")
	private String totalCost;
	@Column(name="att80")
	private String totoBillfoldPockets;
	@Column(name="att81")
	private String totoCheckbook;
	@Column(name="att73")
	private String totoCollection;
	@Column(name="att71")
	private String totoColors;
	@Column(name="num54")
	private String totoDropLengthIn;
	@Column(name="att79")
	private String totoExteriorPocket;
	@Column(name="num51")
	private String totoHeightCm;
	@Column(name="att78")
	private String totoInteriorPocket;
	@Column(name="num50")
	private String totoLengthCm;
	@Column(name="att77")
	private String totoLiningMaterial;
	@Column(name="att75")
	private String totoShellMaterial;
	@Column(name="att72")
	private String totoSizes;
	@Column(name="num53")
	private String totoStrapLengthIn;
	@Column(name="att76")
	private String totoTrimMaterial;
	@Column(name="att82")
	private String totoTypeOfSole;
	@Column(name="num52")
	private String totoWidthCm;
	@Column(name="num40")
	private String tradeMUPct;
	@Column(name="att48")
	private String updated;
	@Column(name="num41")
	private String wholesaleMUPct;
	@Column(name="num42")
	private String wholesalePrice;
	@Column(name="num43")
	private String workingCost1FSPromoGMPct;
	@Column(name="num44")
	private String workingCost1FSTargetGMPct;
	@Column(name="num45")
	private String workingCost1RetailGMPct;
	@Column(name="num49")
	private String num49;*/
    /**
     * @return the merchandisingFullPrice
     */
    public List<LCSMoaObjectVo> getMerchandisingFullPrice() {
        return merchandisingFullPrice;
    }

    /**
     * @param merchandisingFullPrice the merchandisingFullPrice to set
     */
    public void setMerchandisingFullPrice(List<LCSMoaObjectVo> merchandisingFullPrice) {
        this.merchandisingFullPrice = merchandisingFullPrice;
    }

    /**
     * @return the merchandisingCJIPrice
     */
    public List<LCSMoaObjectVo> getMerchandisingCJIPrice() {
        return merchandisingCJIPrice;
    }

    /**
     * @param merchandisingCJIPrice the merchandisingCJIPrice to set
     */
    public void setMerchandisingCJIPrice(List<LCSMoaObjectVo> merchandisingCJIPrice) {
        this.merchandisingCJIPrice = merchandisingCJIPrice;
    }

    /**
     * @return the merchandisingFactoryPrice
     */
    public List<LCSMoaObjectVo> getMerchandisingFactoryPrice() {
        return merchandisingFactoryPrice;
    }

    /**
     * @param merchandisingFactoryPrice the merchandisingFactoryPrice to set
     */
    public void setMerchandisingFactoryPrice(
            List<LCSMoaObjectVo> merchandisingFactoryPrice) {
        this.merchandisingFactoryPrice = merchandisingFactoryPrice;
    }

    /**
     * @return the deleteDate
     */
    public String getDeletedDate() {
        return deletedDate;
    }

    /**
     * @param deleteDate the deleteDate to set
     */
    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * @return the productadditionDetailsVo
     */
    public ProductadditionDetailsVo getProductadditionDetailsVo() {
        return productadditionDetailsVo;
    }

    /**
     * @param productadditionDetailsVo the productadditionDetailsVo to set
     */
    public void setProductadditionDetailsVo(
            ProductadditionDetailsVo productadditionDetailsVo) {
        this.productadditionDetailsVo = productadditionDetailsVo;
    }

    /**
     * @return the prodLifeCycleState
     */
    public String getProdLifeCycleState() {
        return prodLifeCycleState;
    }

    /**
     * @param prodLifeCycleState the prodLifeCycleState to set
     */
    public void setProdLifeCycleState(String prodLifeCycleState) {
        this.prodLifeCycleState = prodLifeCycleState;
    }

    /**
     * @return the ida3a12
     */
    public String getIda3a12() {
        return ida3a12;
    }

    /**
     * @param ida3a12 the ida3a12 to set
     */
    public void setIda3a12(String ida3a12) {
        this.ida3a12 = ida3a12;
    }

    /**
     * @return the productSeason
     */
    public List<String> getProductSeason() {
        return productSeason;
    }

    /**
     * @param productSeason the productSeason to set
     */
    public void setProductSeason(List<String> productSeason) {
        this.productSeason = productSeason;
    }

    /**
     * @return the productSpecification
     */
    public List<String> getProductSpecification() {
        return productSpecification;
    }

    /**
     * @param productSpecification the productSpecification to set
     */
    public void setProductSpecification(List<String> productSpecification) {
        this.productSpecification = productSpecification;
    }

    /**
     * @return the ida3masterreference
     */
    public String getIda3masterreference() {
        return ida3masterreference;
    }

    public Map<String, String> getProductSource() {
        return productSource;
    }

    public void setProductSource(Map<String, String> productSource) {
        this.productSource = productSource;
    }

    public Map<String, String> getProductSkuStyle() {
        return productSkuStyle;
    }

    public void setProductSkuStyle(Map<String, String> productSkuStyle) {
        this.productSkuStyle = productSkuStyle;
    }

    /**
     * @param ida3masterreference the ida3masterreference to set
     */
    public void setIda3masterreference(String ida3masterreference) {
        this.ida3masterreference = ida3masterreference;
    }

    /**
     * @return the styleName
     */
    public String getStyleName() {
        return styleName;
    }

    /**
     * @param styleName the styleName to set
     */
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    /**
     * @return the abbreviatedStyleName
     */
    public String getAbbreviatedStyleName() {
        return abbreviatedStyleName;
    }

    /**
     * @param abbreviatedStyleName the abbreviatedStyleName to set
     */
    public void setAbbreviatedStyleName(String abbreviatedStyleName) {
        this.abbreviatedStyleName = abbreviatedStyleName;
    }

    /**
     * @return the styleNumber
     */
    public String getStyleNumber() {
        return styleNumber;
    }

    /**
     * @param styleNumber the styleNumber to set
     */
    public void setStyleNumber(String styleNumber) {
        this.styleNumber = styleNumber;
    }

    /**
     * @return the licensedStyleNumber
     */
    public String getLicensedStyleNumber() {
        return licensedStyleNumber;
    }

    /**
     * @param licensedStyleNumber the licensedStyleNumber to set
     */
    public void setLicensedStyleNumber(String licensedStyleNumber) {
        this.licensedStyleNumber = licensedStyleNumber;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the collection
     */
    public String getCollection() {
        return collection;
    }

    /**
     * @param collection the collection to set
     */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    /**
     * @return the productClass
     */
    public String getProductClass() {
        return productClass;
    }

    /**
     * @param productClass the productClass to set
     */
    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    /**
     * @return the adopted
     */
    public String getAdopted() {
        
        return adopted;
    }

    /**
     * @param adopted the adopted to set
     */
    public void setAdopted(String adopted) {
        if (adopted.equalsIgnoreCase("true")) {
            this.adopted = "Yes";
        } else {
            this.adopted = "No";
        }
        this.adopted = adopted;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dropped
     */
    public String getDropped() {
        return dropped;
    }

    /**
     * @param dropped the dropped to set
     */
    public void setDropped(String dropped) {
        if(dropped.equalsIgnoreCase("true")){
            this.dropped = "Yes";
        }else{
            this.dropped = "No";
        }
        this.dropped = dropped;
    }

    /**
     * @return the slotNumber
     */
    public String getSlotNumber() {
        return slotNumber;
    }

    /**
     * @param slotNumber the slotNumber to set
     */
    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    /**
     * @return the subCollection
     */
    public String getSubCollection() {
        return subCollection;
    }

    /**
     * @param subCollection the subCollection to set
     */
    public void setSubCollection(String subCollection) {
        this.subCollection = subCollection;
    }

    /**
     * @return the subClass
     */
    public String getSubClass() {
        return subClass;
    }

    /**
     * @param subClass the subClass to set
     */
    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    /**
     * @return the earlyReleaseDate
     */
    public String getEarlyReleaseDate() {
        return earlyReleaseDate;
    }

    /**
     * @param earlyReleaseDate the earlyReleaseDate to set
     */
    public void setEarlyReleaseDate(String earlyReleaseDate) {
        this.earlyReleaseDate = earlyReleaseDate;
    }

    /**
     * @return the introDate
     */
    public String getIntroDate() {
        return introDate;
    }

    /**
     * @param introDate the introDate to set
     */
    public void setIntroDate(String introDate) {
        this.introDate = introDate;
    }

    /**
     * @return the partPrimaryImageUrl
     */
    public String getPartPrimaryImageUrl() {
        return partPrimaryImageUrl;
    }

    /**
     * @param partPrimaryImageUrl the partPrimaryImageUrl to set
     */
    public void setPartPrimaryImageUrl(String partPrimaryImageUrl) {
        this.partPrimaryImageUrl = partPrimaryImageUrl;
    }

}
