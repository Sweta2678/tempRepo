package com.coach.middleware.batch.itemProcessor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.coach.middleware.batch.dao.NightlyOutBoundDAO;
import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.dao.VO.PRODMASTFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;
import com.coach.middleware.batch.dao.VO.SizeScaleVO;
import com.coach.middleware.batch.dto.NightlyOutBoundDTO;


public class ProdMastItemProcessor implements
		ItemProcessor<NightlyOutBoundVO, List<NightlyOutBoundVO>> {

	private static final Logger logger = Logger
			.getLogger(ProdMastItemProcessor.class);

	@Autowired
	public NightlyOutBoundDAO nightlyOutBoundDAO;

	@Autowired
	NightlyOutBoundDTO nightlyOutBoundDTO;
	
	private List sizedDeptList = new ArrayList();
	private List swDeptList = new ArrayList();
	
	private List<String> stylePrefixList = new ArrayList();
	public static PrintWriter recordsSkipped = null;
	
	/*@Resource(name = "nightlyOutboundProps")
	private Properties nightlyOutboundProps;*/
	
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.defaultDate']}")
	private String defaultDate;
	
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.defaultIntroDate']}")
	private String defaultIntroDate;
	
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.sizedDepartments']}")
	private String sizedDepartments;
	private String swDepartments;
	
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.stylePrefixes']}")
	private String stylePrefixes;
	
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.ignoreStyleSuffix']}")
	private String ignoreStyleSuffix;
	
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.skippedRecordsPath']}")
	private String skippedRecordsPath;
	
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.skippedRecordsFileName']}")
	private String skippedRecordsFileName;
	
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.sizeLength']}")
	private int sizeLength;
	
	boolean fetchAttrStoreDet = true;
	boolean isAboveZero = true;
	//private String DEFAULT_DATE="1/1/2041";

	String STYLETYPE = null;

	List<NightlyOutBoundVO> prodMastExtractVOWrite = new ArrayList();
	
	public Set<String> departmentRow = new HashSet<String>();
	public Set<String> classRow = new HashSet<String>();
	public Set<String> subClassRow = new HashSet<String>();
	public Set<String> styleRow = new HashSet<String>();
	public Set<String> skuRow = new HashSet<String>();
	public Set<String> sizeRow = new HashSet<String>();
	

	List<AttributeStoreVO> attributeStoreVOList = new ArrayList();
	List<SizeScaleVO> sizeScaleMappingList = new ArrayList();
	/*List<PRODMASTFullPriceMOAVO> costMoaList = new ArrayList();
	List<RpFtpAbcVO> rpFtpAbcList = new ArrayList();*/
	List<PRODMASTFullPriceMOAVO> costMoaList = null;
	List<RpFtpAbcVO> rpFtpAbcList = null;
	
	
	List<String> sizeList = null;
	boolean isFStyleRow = false;
	String fStyleNum = null;
	boolean styleExclusion = true;
	String styleNumToProcess = null;
	boolean nextChunk = false;
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<NightlyOutBoundVO> process(NightlyOutBoundVO nightlyOutBoundVO)
			throws Exception {
		
		List<NightlyOutBoundVO> prodMastExtractVOList = new ArrayList();
		sizeList = new ArrayList();
		
		styleExclusion = false;
		try{
		if (fetchAttrStoreDet) {
			
			costMoaList = new ArrayList();
			rpFtpAbcList = new ArrayList();
			String file = skippedRecordsPath+skippedRecordsFileName;
			recordsSkipped = new PrintWriter(new BufferedWriter(new FileWriter(
					file, true)));
			logger.info("ProdMastExtract Process - Fetching Attribute store details");
			getReadNightlyProp();
			setSWDepartmentlist();
			getAttributeStoreValues(nightlyOutBoundVO);
			getSizeScaleMappingValues(nightlyOutBoundVO);
			getCostFromMOA(nightlyOutBoundVO);
			getRpFtpAbcList(nightlyOutBoundVO);
			fetchAttrStoreDet = false;
			this.setNextChunk(false);
			formatStaticRow();
		}
		
		//if(costMoaList.size() <= 5){
		if(isNextChunk()){
			logger.info("Fetching details for next chunk");
			costMoaList = new ArrayList();
			rpFtpAbcList = new ArrayList();
			/*for(int i = 0; i<costMoaList.size();i++ ){
				if(!costMoaList.get(i).getOwnerUniqueId().contains(nightlyOutBoundVO.getSkuUniqueId()))*/
					getCostFromMOA(nightlyOutBoundVO);
			//}
		
			/*for(int i = 0; i<rpFtpAbcList.size();i++ ){
				if(!rpFtpAbcList.get(i).getStyleNumber().contains(nightlyOutBoundVO.getStyleNum()))*/
					getRpFtpAbcList(nightlyOutBoundVO);
			//}
			
			
		}
		this.setNextChunk(false);
		
		if (nightlyOutBoundVO.getStaticLine() != null) {
			nightlyOutBoundVO.setStaticLine(null);
		}
		
		
		if(nightlyOutBoundVO.getStyleNum().length() <= 6 &&
				!isNullOrEmpty(nightlyOutBoundVO.getStyleClass()) &&
				!isNullOrEmpty(nightlyOutBoundVO.getDept()) && 
				!isNullOrEmpty(nightlyOutBoundVO.getSubClass()) && passedOtherRules(nightlyOutBoundVO))
				{
			styleNumToProcess = nightlyOutBoundVO.getStyleNum();
			logger.info("ProdMastExtract styleNumToProcess ==>"+styleNumToProcess);
			nightlyOutBoundDTO = formatLineItem(nightlyOutBoundVO, STYLETYPE);
			NightlyOutBoundDTO nightlyOutBoundDTO2 = (NightlyOutBoundDTO) nightlyOutBoundDTO.Clone();
			
			
			styleExclusion = getStyleExclusionRule(nightlyOutBoundDTO); 
			
			//System.out.println("Style list :"+sizeList.size());
			if(!styleExclusion) {
			if(skuRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO.getStyleNum()+nightlyOutBoundDTO.getSkuName()))
			{
			
				if(styleRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO.getStyleNum()))
				{
						if(subClassRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()))
						{
								if(classRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()))
								{
									
									if(departmentRow.add(nightlyOutBoundDTO.getDeptCode()))
									{
										departmentRow = new HashSet<String>();
										departmentRow.add(nightlyOutBoundDTO.getDeptCode());
										logger.info("new departmentRow added==>"+nightlyOutBoundDTO.getDeptCode());
										nightlyOutBoundVO =  formatDeptLevelRow(nightlyOutBoundDTO,nightlyOutBoundVO);
										prodMastExtractVOList.add(nightlyOutBoundVO);
										
									}	
									
									logger.info(" new classRow added==>"+nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode());
									classRow = new HashSet<String>();
									classRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode());
									nightlyOutBoundVO =  formatClassLevelRow(nightlyOutBoundDTO,nightlyOutBoundVO);
									prodMastExtractVOList.add(nightlyOutBoundVO);
								}
								
							
								logger.info("new subClassRow added==>"+nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode());
								subClassRow = new HashSet<String>();
								subClassRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode());
								nightlyOutBoundVO =  formatSubClassLevelRow(nightlyOutBoundDTO,nightlyOutBoundVO);
								prodMastExtractVOList.add(nightlyOutBoundVO);
								
							}
				
						logger.info("new styleRow  added ==>"+nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO.getStyleNum());
						styleRow = new HashSet<String>();
						skuRow = new HashSet<String>(); // new skuRow collection to hold sku's for new style
						styleRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO.getStyleNum());
						nightlyOutBoundVO =  formatStyleLevelRow(nightlyOutBoundDTO,nightlyOutBoundVO);
						prodMastExtractVOList.add(nightlyOutBoundVO);
						//if(nightlyOutBoundDTO.getStyleNum().trim().length() <= 6){
							writeFStyleLevel(nightlyOutBoundDTO,nightlyOutBoundDTO2,nightlyOutBoundVO,prodMastExtractVOList);
							//styleRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO2.getStyleNum());
						//}
						isFStyleRow = false;
					}
					
				nightlyOutBoundDTO.setStyleNum(styleNumToProcess);	
				logger.info("new skuRow added  ==>"+nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO.getStyleNum()+nightlyOutBoundDTO.getSkuName());
				nightlyOutBoundVO =  formatSKuLevelRow(nightlyOutBoundDTO,nightlyOutBoundVO);
				prodMastExtractVOList.add(nightlyOutBoundVO);
					
					//if(nightlyOutBoundDTO.getStyleNum().trim().length() < 6){
						writeFSkuLevl(nightlyOutBoundDTO,nightlyOutBoundDTO2,nightlyOutBoundVO,prodMastExtractVOList);
						skuRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO2.getStyleNum()+nightlyOutBoundDTO.getSkuName());
					//}
				isFStyleRow = false;
					
				if(sizeList.size() >0){	
					sizeRow = new HashSet<String>();
					for(String sizeStr: sizeList)
						{
							nightlyOutBoundDTO.setSize(sizeStr);
							//NightlyOutBoundDTO nightlyOutBoundDTO3 = (NightlyOutBoundDTO) nightlyOutBoundDTO.Clone();
							logger.info("New sizeRow  added ==>"+nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO.getStyleNum()+nightlyOutBoundDTO.getSkuName()+nightlyOutBoundDTO.getSize());
							if(sizeRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO.getStyleNum()+nightlyOutBoundDTO.getSkuName()+nightlyOutBoundDTO.getSize()))
							{
								nightlyOutBoundVO =  formatSizeLevelRow(nightlyOutBoundDTO,nightlyOutBoundVO);
								prodMastExtractVOList.add(nightlyOutBoundVO);
								
									//if(nightlyOutBoundDTO.getStyleNum().trim().length() < 6){	
										
										writeFSizeLevl(nightlyOutBoundDTO,nightlyOutBoundDTO2,nightlyOutBoundVO,prodMastExtractVOList);
										sizeRow.add(nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()+nightlyOutBoundDTO.getSubClassCode()+nightlyOutBoundDTO2.getStyleNum()+nightlyOutBoundDTO.getSkuName()+nightlyOutBoundDTO.getSize());
									//}
								isFStyleRow = false;
							}
						}	
				}	
				
			}
		}
			
			/*logger.info("Capacity departmentRow==>"+this.getDepartmentRow().size());
			logger.info("Capacity classRow==>"+this.getClassRow().size());
			logger.info("Capacity subClassRow==>"+this.getSubClassRow().size());
			logger.info("Capacity styleRow==>"+this.getStyleRow().size());
			logger.info("Capacity skuRow==>"+this.getSkuRow().size());
			logger.info("Capacity sizeRow==>"+sizeRow.size());
			logger.info("Capacity costMoaList==>"+costMoaList.size());
			logger.info("Capacity sizeList==>"+sizeList.size());
			logger.info("Capacity rpFtpAbcList==>"+rpFtpAbcList.size());*/
			
			
			prodMastExtractVOWrite.addAll(prodMastExtractVOList);
			
			
		}
		}catch(Exception e){
			logger.error("style "+styleNumToProcess+" processing failed", e);
		}
		
		return prodMastExtractVOWrite;
	}

	

	private boolean getStyleExclusionRule(NightlyOutBoundDTO nightlyOutBoundDTO2) {

		boolean excludeStyle =  false;
		
		if(sizedDeptList.contains(nightlyOutBoundDTO.getDeptCode().trim()) 
				&& sizeList.size() == 0){
			logger.info("******Sized department*****");
			logger.info("******No Sized level records*******");
			return true;
			
		}
		
		if (swDeptList.contains(nightlyOutBoundDTO.getDeptCode().trim())) {

			logger.info("******Stuart Weitzman department*****"
					+ nightlyOutBoundDTO.getDeptCode());

			return true;

		}
		
		if(nightlyOutBoundDTO.getStyleNum().trim().length() > 6){
			return true;
		}
		/*Size length greater than 5 changes starts*/
		//System.out.println("sizeLength "+sizeLength);
		if(sizeList.size() >0){	
			for(String sizeStr: sizeList){
				if(sizeStr.trim().length()>sizeLength){
					recordsSkipped.println(nightlyOutBoundDTO.getStyleNum() + " " +nightlyOutBoundDTO.getSkuName());
					logger.info("Excluded style for size ---> " +sizeLength + " * ** " +nightlyOutBoundDTO.getStyleNum() + " -- "+nightlyOutBoundDTO.getSkuName() + " -- "+sizeStr);
					return true;
				}
				else{
					excludeStyle=false;
				}
			}
			return excludeStyle;
		}
		/*Size length greater than 5 changes ends*/
		
		return false;
	}

	private void writeFStyleLevel(NightlyOutBoundDTO nightlyOutBoundDTO,
			NightlyOutBoundDTO nightlyOutBoundDTO2,NightlyOutBoundVO nightlyOutBoundVO,List prodMastExtractVOList) {
		
		isFStyleRow = checkFStyleEligibility(nightlyOutBoundDTO);  
		logger.info("Style "+styleNumToProcess+" level Eligibility for FStyle==>"+isFStyleRow);
		
		if(isFStyleRow){
			fStyleNum = getFStyleNumVal(nightlyOutBoundDTO);
			nightlyOutBoundDTO2.setStyleNum(fStyleNum);
			nightlyOutBoundDTO2.setDefaultIndicator("2");
			nightlyOutBoundVO =  formatStyleLevelRow(nightlyOutBoundDTO2,nightlyOutBoundVO);
			prodMastExtractVOList.add(nightlyOutBoundVO);	
		}
		
	}
	
	private String getFStyleNumVal(NightlyOutBoundDTO nightlyOutBoundDTO) {
		
		String fStyleNum = null;
		
		if(nightlyOutBoundDTO.getStyleNum().length() <= 5){
			fStyleNum = "F".concat(nightlyOutBoundDTO.getStyleNum().trim());
		}
		
		if(nightlyOutBoundDTO.getStyleNum().length() < 5){
			fStyleNum = "IR".concat(nightlyOutBoundDTO.getStyleNum().trim());
		}
		
		/*if(nightlyOutBoundDTO.getStyleNum().startsWith("HH") && nightlyOutBoundDTO.getSkuName().trim().equalsIgnoreCase("HLD") ){
			fStyleNum = "F".concat(nightlyOutBoundDTO.getStyleNum().trim());
		}*/
		
		return fStyleNum;
	}

	private void writeFSkuLevl(NightlyOutBoundDTO nightlyOutBoundDTO,
			NightlyOutBoundDTO nightlyOutBoundDTO2,NightlyOutBoundVO nightlyOutBoundVO,List prodMastExtractVOList) {
		isFStyleRow = checkFStyleEligibility(nightlyOutBoundDTO);  
		logger.info("Sku level Eligibility for FStyle==>"+isFStyleRow);
		
		if(isFStyleRow){
			
			fStyleNum = getFStyleNumVal(nightlyOutBoundDTO);
			nightlyOutBoundDTO2.setStyleNum(fStyleNum);
			nightlyOutBoundDTO2.setDefaultIndicator("2");
			nightlyOutBoundVO =  formatSKuLevelRow(nightlyOutBoundDTO2,nightlyOutBoundVO);
			prodMastExtractVOList.add(nightlyOutBoundVO);	
		}
		
	}
	
	private void writeFSizeLevl(NightlyOutBoundDTO nightlyOutBoundDTO,
			NightlyOutBoundDTO nightlyOutBoundDTO2,NightlyOutBoundVO nightlyOutBoundVO,List prodMastExtractVOList) {
		boolean writeFstyle = false;
		
		isFStyleRow = checkFStyleEligibility(nightlyOutBoundDTO);  
		logger.info("Size level Eligibility for FStyle==>"+isFStyleRow);
		
		if(isFStyleRow){
			
			fStyleNum = getFStyleNumVal(nightlyOutBoundDTO);
			nightlyOutBoundDTO2.setStyleNum(fStyleNum);
			nightlyOutBoundDTO2.setDefaultIndicator("2");
			nightlyOutBoundDTO2.setSize(nightlyOutBoundDTO.getSize());
			logger.info("Size in FStyle size "+nightlyOutBoundDTO.getSize());
			nightlyOutBoundVO =  formatSizeLevelRow(nightlyOutBoundDTO2,nightlyOutBoundVO);
			prodMastExtractVOList.add(nightlyOutBoundVO);	
		}
		
	}

	private boolean checkFStyleEligibility(NightlyOutBoundDTO nightlyOutBoundDTO) {

		boolean result = true;
		
		String styleNum = styleNumToProcess;
		
		String startChar = styleNum.charAt(0) + "";
		String isLastCharExist = styleNum.charAt(styleNum.length() - 1) + "";

		if (isLastCharExist.equalsIgnoreCase(ignoreStyleSuffix) && styleNum.length() <= 6) {
			String truncStyle = styleNum.substring(0, styleNum.length() - 1);
			styleNum = truncStyle;
			logger.info("truncated Style for "+ignoreStyleSuffix+" style==>" + styleNum);
			nightlyOutBoundDTO.setStyleNum(truncStyle);
		}
		
		/*if (startChar.equalsIgnoreCase("H") && styleNum.length() <= 6) {
			String truncStyle = styleNum.substring(1, styleNum.length());
			styleNum = truncStyle;
			logger.info("truncated Style for H style==>" + styleNum);
			nightlyOutBoundDTO.setStyleNum(truncStyle);
		}*/
		if(startChar.equalsIgnoreCase("H")){
			if(styleNum.startsWith("HH")){
				logger.info("HH style***********************************" + styleNum);
				if(styleNum.length()>=6){
					String truncStyle = styleNum.substring(1, styleNum.length());
					styleNum = truncStyle;
					nightlyOutBoundDTO.setStyleNum(styleNum);
				}else{
					nightlyOutBoundDTO.setStyleNum(styleNum);
				}
			}
			else if(styleNum.length() <= 4){
				logger.info("H style---------------------------------4 " + styleNum);
				nightlyOutBoundDTO.setStyleNum(styleNum);
			}else{
				result=false;
			}
		}
		
		//logger.info("Style for Eligibility Check==>" + styleNum);

		for (String tmp : stylePrefixList) {

			if (tmp.equalsIgnoreCase(startChar)) {
				result = false;
				return result;
			}
			
		}

		if (styleNum.length() <= 5) {
			logger.info("Style len <= 5");
			if (isLastCharExist.equalsIgnoreCase("P")) {
				result = false;
				return result;
			}

			if(nightlyOutBoundDTO.getSkuName().trim().startsWith("DUM")){
				 logger.info("Sku name is DUM");
				 result = false; 
				 return result;
			 }
			
			

		}
		
		if (styleNum.length() >= 6) {
			logger.info("Style len >= 6");
			result = false;
			
			/*if (styleNum.startsWith("HH") && nightlyOutBoundDTO.getSkuName().trim().equalsIgnoreCase("HLD")) {
				result = true;
				return result;
			}*/
			
			
			return result;
		}
		
		return result;
	}

	private NightlyOutBoundVO formatSizeLevelRow(
			NightlyOutBoundDTO nightlyOutBoundDTO,
			NightlyOutBoundVO nightlyOutBoundVO) {

		if(nightlyOutBoundDTO.getSkuName().length() <= 5){
			
			nightlyOutBoundDTO.setSkuNamePadded(padWithSpace(nightlyOutBoundDTO.getSkuName(),5));	
		}
		
		//if(nightlyOutBoundDTO.getSize().length() <= 5){
			
			//nightlyOutBoundDTO.setSizePadded(padWithSpace(nightlyOutBoundDTO.getSize(),5));
		if(!isNullOrEmpty(nightlyOutBoundDTO.getSize()))
		nightlyOutBoundDTO.setSizePadded(nightlyOutBoundDTO.getSize());
		else
			nightlyOutBoundDTO.setSizePadded("");
		//}
		nightlyOutBoundVO = new NightlyOutBoundVO();
		String sizeLevelRow =""
						+ nightlyOutBoundDTO.getStyleNumPadded()+nightlyOutBoundDTO.getSkuNamePadded()+nightlyOutBoundDTO.getSizePadded()+ ","+nightlyOutBoundDTO.getDefaultIndicator()+
						","+ nightlyOutBoundDTO.getStyleNumPadded()+ nightlyOutBoundDTO.getSkuNamePadded()+nightlyOutBoundDTO.getSizePadded()+ ","+ nightlyOutBoundDTO.getStyleNumPadded()
						+ nightlyOutBoundDTO.getSkuName()+ ","+ nightlyOutBoundDTO.getStyleNum()+ ","+ nightlyOutBoundDTO.getStyleDesc()+ ","+ nightlyOutBoundDTO.getMerDesc()+ ","
						+ nightlyOutBoundDTO.getSubClassCode()+ ","+ nightlyOutBoundDTO.getSubClassDesc()+ ","+ nightlyOutBoundDTO.getClassCode()+ ","
						+ nightlyOutBoundDTO.getClassDesc()+ ","+ nightlyOutBoundDTO.getDeptCode()+ ","+ nightlyOutBoundDTO.getDeptDesc()+ ",Total Product,"
						//Handled Attributes
				+ nightlyOutBoundDTO.getCollectionCode()+ ","+ nightlyOutBoundDTO.getCollectionDesc()+ ","+ nightlyOutBoundDTO.getColorFamily()
				+ ","
				+ nightlyOutBoundDTO.getSkuName()+ ","+ nightlyOutBoundDTO.getColorName()+ ","
				// Sku Intro Date
				+ nightlyOutBoundDTO.getSkuIntroYearStr()+ ","+ nightlyOutBoundDTO.getSkuIntroQuaterStr()+ ","+ nightlyOutBoundDTO.getSkuIntroDateStr()+ ","
				+ nightlyOutBoundDTO.getSkuIntroCalenderDate()
				// Style Intro Date
				+ ","+ nightlyOutBoundDTO.getStyleIntroYearStr()+ ","+ nightlyOutBoundDTO.getStyleIntroQuaterStr()
				+ ","+ nightlyOutBoundDTO.getStyleIntroDateStr()+ ","+ nightlyOutBoundDTO.getStyleIntroCalenderDate()
				// Sku Delete Date
				+ ","+ nightlyOutBoundDTO.getSkuDeleteYearStr()+ ","+ nightlyOutBoundDTO.getSkuDeleteQuaterStr()+ ","+ nightlyOutBoundDTO.getSkuDeleteDateStr()
				+ ","+ nightlyOutBoundDTO.getSkuDeleteCalenderDate()
				// Style Delete Date
				+ ","+ nightlyOutBoundDTO.getStyleDeleteYearStr()+ ","+ nightlyOutBoundDTO.getStyleDeleteQuaterStr()+ ","+ nightlyOutBoundDTO.getStyleDeleteDateStr()
				+ ","+ nightlyOutBoundDTO.getStyleDeleteCalenderDate()
				// Sku Fs Intro Date
				+ ","+ nightlyOutBoundDTO.getSkuFsIntroYearStr()
				+ ","+ nightlyOutBoundDTO.getSkuFsIntroQuaterStr()+ ","+ nightlyOutBoundDTO.getSkuFsIntroDateStr()+ ","+ nightlyOutBoundDTO.getSkuFsIntroCalenderDate()
				// Style Fs Intro Date
				+ ","+ nightlyOutBoundDTO.getStyleFsIntroYearStr()+ ","+ nightlyOutBoundDTO.getStyleFsIntroQuaterStr()+ ","+ nightlyOutBoundDTO.getStyleFsIntroDateStr()
				+ ","+ nightlyOutBoundDTO.getStyleFsIntroCalenderDate()
				// Sku Fs Delete Date
				+ ","+ nightlyOutBoundDTO.getSkuFsDeleteYearStr()+ ","+ nightlyOutBoundDTO.getSkuFsDeleteQuaterStr()
				+ ","+nightlyOutBoundDTO.getSkuFsDeleteDateStr()+","+nightlyOutBoundDTO.getSkuFsDeleteCalenderDate()
				// Style Fs Delete Date
				+ ","+ nightlyOutBoundDTO.getStyleFsDeleteYearStr()+ ","+ nightlyOutBoundDTO.getStyleFsDeleteQuaterStr()+ ","+ nightlyOutBoundDTO.getStyleFsDeleteDateStr()
				+ ","+ nightlyOutBoundDTO.getStyleFsDeleteCalenderDate()
				/**/
				+ ","
				+ nightlyOutBoundDTO.getMaterialGroupName()
				//+ "materialGrpName"
				+ ","
				+ nightlyOutBoundDTO.getMaterialGroupDesc()
				//+ "materialGrpDesc"
				+ ","
				+ nightlyOutBoundDTO.getMaterial()+ ","+nightlyOutBoundDTO.getMaterialName()+","+ nightlyOutBoundDTO.getSilhouette()+ ","
				+ nightlyOutBoundDTO.getSilhouetteDesc()+ ","+nightlyOutBoundDTO.getHardwareColor()+","+nightlyOutBoundDTO.getHardwareColorDesc()+","
				+ nightlyOutBoundDTO.getGender()+ ","+ nightlyOutBoundDTO.getGenderDesc()+ ","+ nightlyOutBoundDTO.getSpecialProduct()+ ","
				+ nightlyOutBoundDTO.getSpecialProductDesc()+ ","+nightlyOutBoundDTO.getAbcCode()+","+nightlyOutBoundDTO.getAbcCodeDesc()
				+","+nightlyOutBoundDTO.getPlanExclusion()+ ","+ nightlyOutBoundDTO.getChannelExclusive()+ ","+ nightlyOutBoundDTO.getStyleGroup()
				+ ",N/A,"+ nightlyOutBoundDTO.getParentStyle()+ ","+ nightlyOutBoundDTO.getUpdated()+ ","+ nightlyOutBoundDTO.getMpg()+ ","
				+ nightlyOutBoundDTO.getStatus()+ ","+ nightlyOutBoundDTO.getUpcCode()+ ","+ nightlyOutBoundDTO.getSignatureType()+ ","
				+ nightlyOutBoundDTO.getFactoryType()+ ","+ nightlyOutBoundDTO.getShortFactoryType()+ ","+ nightlyOutBoundDTO.getExotic()
				+ ","+ nightlyOutBoundDTO.getSubCollection()+ ","+ nightlyOutBoundDTO.getSubCollectionDesc()
				+ ","+nightlyOutBoundDTO.getStyleNumImg()+nightlyOutBoundDTO.getSkuNameImg().replace("/", "_")+"d1.jpg,"
				+ "0"
				/*
				 * ,OrgTargetCost,OrgRetailPrice,WHPrice,WorkingCost1,
		 * CurrentCost
		 * ,SignatureType,HandBagSizeDesc,compCollection,compSubCollection
		 * ,compClass,CompSubClass,TotalCost,crossBody,Collaboration,Gifting,
		 * productSegmentation
		 * ,attitudinal,Pint,embellishment,soletype,toeType,toeShape
		 * ,heelHeight,caseSize
		 * ,functionality,faceColor,caseShape,lensType,productIdentity,attitude,
				 * */
				+ ",7,"
				+ nightlyOutBoundDTO.getLicensed()
				+ ","
				//+ nightlyOutBoundDTO.getSkuTargetCost()
				+ nightlyOutBoundDTO.getTargetCost()
				+ ","
				+ nightlyOutBoundDTO.getOrgRetailPrice()
				+ ","
				+ nightlyOutBoundDTO.getWhPrice()+ ","
				+ nightlyOutBoundDTO.getSkuAvgWorkingCost1() + ","
				+ nightlyOutBoundDTO.getFinalStdCost() + "," + nightlyOutBoundDTO.getSignatureDesc()
				+ "," +nightlyOutBoundDTO.getHandBagSizeDesc()+ ",,,,,"
				+ nightlyOutBoundDTO.getSkuTotalCost()
				
				+ ","+nightlyOutBoundDTO.getCrossBody()+","+ nightlyOutBoundDTO.getCrossBodyDesc()+","+nightlyOutBoundDTO.getCollaboration()+","+nightlyOutBoundDTO.getGifting()+","+nightlyOutBoundDTO.getProductSegmentation()+","
				+ nightlyOutBoundDTO.getAttitudinalSegments() + "," + nightlyOutBoundDTO.getPint() + ","
				+ nightlyOutBoundDTO.getEmbellishment() + ","
				+ nightlyOutBoundDTO.getSoleType() + ","
				+ nightlyOutBoundDTO.getToeType() + ","
				+ nightlyOutBoundDTO.getToeShape() + ","
				+ nightlyOutBoundDTO.getHeelHeight1() + ","
				+ nightlyOutBoundDTO.getWatchCaseSize() + ","
				+ nightlyOutBoundDTO.getFunctionality() + ","
				+ nightlyOutBoundDTO.getFaceColor() + ","
				+ nightlyOutBoundDTO.getWatchCaseShape() + ","
				+ nightlyOutBoundDTO.getLens() + ","
				+ nightlyOutBoundDTO.getProductIdentity() + ","
				+ nightlyOutBoundDTO.getAttitude() + ","
				+ nightlyOutBoundDTO.getStyleuniqueid() + "," 
				+ nightlyOutBoundDTO.getSkuUniqueId() + ","+ nightlyOutBoundDTO.getSizeScale() +"," + nightlyOutBoundDTO.getSizePadded();
				
				//""+nightlyOutBoundDTO.getSize()+"";
		
		nightlyOutBoundVO.setProdMastWriteRow(sizeLevelRow);
		
		
		
		return nightlyOutBoundVO;
	}

	private NightlyOutBoundVO formatSKuLevelRow(
			NightlyOutBoundDTO nightlyOutBoundDTO,
			NightlyOutBoundVO nightlyOutBoundVO) {
				
		nightlyOutBoundVO = new NightlyOutBoundVO();
		
		if(nightlyOutBoundDTO.getStyleNum().length() <= 6){
			
			nightlyOutBoundDTO.setStyleNumPadded(padWithSpace(nightlyOutBoundDTO.getStyleNum(),6));	
		}else{
			nightlyOutBoundDTO.setStyleNumPadded(nightlyOutBoundDTO.getStyleNum());
		}
		if (!isNullOrEmpty(nightlyOutBoundDTO.getSize()))
			nightlyOutBoundDTO.setSizePadded(nightlyOutBoundDTO.getSize());
		else
			nightlyOutBoundDTO.setSizePadded("");

		String skuLevelRow = ""
				+ nightlyOutBoundDTO.getStyleNumPadded()+nightlyOutBoundDTO.getSkuName()+ ","+nightlyOutBoundDTO.getDefaultIndicator()+","+ nightlyOutBoundDTO.getStyleNumPadded() 
				+ nightlyOutBoundDTO.getSkuName()+ ","+ nightlyOutBoundDTO.getStyleNumPadded()+ nightlyOutBoundDTO.getSkuName()+ ","
				+ nightlyOutBoundDTO.getStyleNum()+ ","+ nightlyOutBoundDTO.getStyleDesc()+ ","+ nightlyOutBoundDTO.getMerDesc()+ ","
				+ nightlyOutBoundDTO.getSubClassCode()+ ","+ nightlyOutBoundDTO.getSubClassDesc()+ ","+ nightlyOutBoundDTO.getClassCode()+ ","
				+ nightlyOutBoundDTO.getClassDesc()+ ","+ nightlyOutBoundDTO.getDeptCode()+ ","+ nightlyOutBoundDTO.getDeptDesc()+ ",Total Product,"
				//Handled Attributes
				+ nightlyOutBoundDTO.getCollectionCode()+ ","+ nightlyOutBoundDTO.getCollectionDesc()+ ","+ nightlyOutBoundDTO.getColorFamily()
				+ ","
				+ nightlyOutBoundDTO.getSkuName()+ ","+ nightlyOutBoundDTO.getColorName()+ ","
				// Sku Intro Date
				+ nightlyOutBoundDTO.getSkuIntroYearStr()+ ","+ nightlyOutBoundDTO.getSkuIntroQuaterStr()+ ","+ nightlyOutBoundDTO.getSkuIntroDateStr()+ ","
				+ nightlyOutBoundDTO.getSkuIntroCalenderDate()
				// Style Intro Date
				+ ","+ nightlyOutBoundDTO.getStyleIntroYearStr()+ ","+ nightlyOutBoundDTO.getStyleIntroQuaterStr()
				+ ","+ nightlyOutBoundDTO.getStyleIntroDateStr()+ ","+ nightlyOutBoundDTO.getStyleIntroCalenderDate()
				// Sku Delete Date
				+ ","+ nightlyOutBoundDTO.getSkuDeleteYearStr()+ ","+ nightlyOutBoundDTO.getSkuDeleteQuaterStr()+ ","+ nightlyOutBoundDTO.getSkuDeleteDateStr()
				+ ","+ nightlyOutBoundDTO.getSkuDeleteCalenderDate()
				// Style Delete Date
				+ ","+ nightlyOutBoundDTO.getStyleDeleteYearStr()+ ","+ nightlyOutBoundDTO.getStyleDeleteQuaterStr()+ ","+ nightlyOutBoundDTO.getStyleDeleteDateStr()
				+ ","+ nightlyOutBoundDTO.getStyleDeleteCalenderDate()
				// Sku Fs Intro Date
				+ ","+ nightlyOutBoundDTO.getSkuFsIntroYearStr()
				+ ","+ nightlyOutBoundDTO.getSkuFsIntroQuaterStr()+ ","+ nightlyOutBoundDTO.getSkuFsIntroDateStr()+ ","+ nightlyOutBoundDTO.getSkuFsIntroCalenderDate()
				// Style Fs Intro Date
				+ ","+ nightlyOutBoundDTO.getStyleFsIntroYearStr()+ ","+ nightlyOutBoundDTO.getStyleFsIntroQuaterStr()+ ","+ nightlyOutBoundDTO.getStyleFsIntroDateStr()
				+ ","+ nightlyOutBoundDTO.getStyleFsIntroCalenderDate()
				// Sku Fs Delete Date
				+ ","+ nightlyOutBoundDTO.getSkuFsDeleteYearStr()+ ","+ nightlyOutBoundDTO.getSkuFsDeleteQuaterStr()
				+ ","+nightlyOutBoundDTO.getSkuFsDeleteDateStr()+","+nightlyOutBoundDTO.getSkuFsDeleteCalenderDate()
				// Style Fs Delete Date
				+ ","+ nightlyOutBoundDTO.getStyleFsDeleteYearStr()+ ","+ nightlyOutBoundDTO.getStyleFsDeleteQuaterStr()+ ","+ nightlyOutBoundDTO.getStyleFsDeleteDateStr()
				+ ","+ nightlyOutBoundDTO.getStyleFsDeleteCalenderDate()
				/**/
				+ ","
				+ nightlyOutBoundDTO.getMaterialGroupName()
				//+ "materialGrpName"
				+ ","
				+ nightlyOutBoundDTO.getMaterialGroupDesc()
				//+ "materialGrpDesc"
				+ ","
				+ nightlyOutBoundDTO.getMaterial()+ ","+nightlyOutBoundDTO.getMaterialName()+","+ nightlyOutBoundDTO.getSilhouette()+ ","
				+ nightlyOutBoundDTO.getSilhouetteDesc()+ ","+nightlyOutBoundDTO.getHardwareColor()+","+nightlyOutBoundDTO.getHardwareColorDesc()+","
				+ nightlyOutBoundDTO.getGender()+ ","+ nightlyOutBoundDTO.getGenderDesc()+ ","+ nightlyOutBoundDTO.getSpecialProduct()+ ","
				+ nightlyOutBoundDTO.getSpecialProductDesc()+ ","+nightlyOutBoundDTO.getAbcCode()+","+nightlyOutBoundDTO.getAbcCodeDesc()
				+","+nightlyOutBoundDTO.getPlanExclusion()+ ","+ nightlyOutBoundDTO.getChannelExclusive()+ ","+ nightlyOutBoundDTO.getStyleGroup()
				+ ",N/A,"+ nightlyOutBoundDTO.getParentStyle()+ ","+ nightlyOutBoundDTO.getUpdated()+ ","+ nightlyOutBoundDTO.getMpg()+ ","
				+ nightlyOutBoundDTO.getStatus()+ ","+ nightlyOutBoundDTO.getUpcCode()+ ","+ nightlyOutBoundDTO.getSignatureType()+ ","
				+ nightlyOutBoundDTO.getFactoryType()+ ","+ nightlyOutBoundDTO.getShortFactoryType()+ ","+ nightlyOutBoundDTO.getExotic()
				+ ","+ nightlyOutBoundDTO.getSubCollection()+ ","+ nightlyOutBoundDTO.getSubCollectionDesc()
				+ ","+nightlyOutBoundDTO.getStyleNumImg()+nightlyOutBoundDTO.getSkuNameImg().replace("/", "_")+"d1.jpg,"
				+ nightlyOutBoundDTO.getSizeIntLevel()
				+ ",6,"
				+ nightlyOutBoundDTO.getLicensed()
				+ ","
				+ nightlyOutBoundDTO.getTargetCost()
				+ ","
				+ nightlyOutBoundDTO.getOrgRetailPrice()
				+ ","
				+ nightlyOutBoundDTO.getWhPrice()+ ","
				+ nightlyOutBoundDTO.getSkuAvgWorkingCost1() + ","
				+ nightlyOutBoundDTO.getFinalStdCost() + "," + nightlyOutBoundDTO.getSignatureDesc()
				+ "," + nightlyOutBoundDTO.getHandBagSizeDesc() + ",,,,,"
				+ nightlyOutBoundDTO.getSkuTotalCost()
				+ ","+nightlyOutBoundDTO.getCrossBody()+","+ nightlyOutBoundDTO.getCrossBodyDesc()+","+nightlyOutBoundDTO.getCollaboration()+","+nightlyOutBoundDTO.getGifting()+","+nightlyOutBoundDTO.getProductSegmentation()+","
				+ nightlyOutBoundDTO.getAttitudinalSegments() + "," + nightlyOutBoundDTO.getPint() + ","
				+ nightlyOutBoundDTO.getEmbellishment() + ","
				+ nightlyOutBoundDTO.getSoleType() + ","
				+ nightlyOutBoundDTO.getToeType() + ","
				+ nightlyOutBoundDTO.getToeShape() + ","
				+ nightlyOutBoundDTO.getHeelHeight1() + ","
				+ nightlyOutBoundDTO.getWatchCaseSize() + ","
				+ nightlyOutBoundDTO.getFunctionality() + ","
				+ nightlyOutBoundDTO.getFaceColor() + ","
				+ nightlyOutBoundDTO.getWatchCaseShape() + ","
				+ nightlyOutBoundDTO.getLens() + ","
				+ nightlyOutBoundDTO.getProductIdentity() + ","
				+ nightlyOutBoundDTO.getAttitude() + ","
				+ nightlyOutBoundDTO.getStyleuniqueid() + "," 
				+ nightlyOutBoundDTO.getSkuUniqueId() + ","+ nightlyOutBoundDTO.getSizeScale() +"," + nightlyOutBoundDTO.getSizePadded();

		nightlyOutBoundVO.setProdMastWriteRow(skuLevelRow);
		return nightlyOutBoundVO;
	}

	private NightlyOutBoundVO formatStyleLevelRow(
			NightlyOutBoundDTO nightlyOutBoundDTO,
			NightlyOutBoundVO nightlyOutBoundVO) {
		
		nightlyOutBoundVO = new NightlyOutBoundVO();
		String styleLevelRow = ""
				+ nightlyOutBoundDTO.getStyleNum()
				+ ","+nightlyOutBoundDTO.getDefaultIndicator()+","
				+ nightlyOutBoundDTO.getStyleNum()
				+ ",,"
				+ nightlyOutBoundDTO.getStyleNum()
				+ ","
				+ nightlyOutBoundDTO.getStyleDesc()
				+ ","
				+ nightlyOutBoundDTO.getMerDesc()
				+ ","
				+ nightlyOutBoundDTO.getSubClassCode()
				+ ","
				+ nightlyOutBoundDTO.getSubClassDesc()
				+ ","
				+ nightlyOutBoundDTO.getClassCode()
				+ ","
				+ nightlyOutBoundDTO.getClassDesc()
				+ ","
				+ nightlyOutBoundDTO.getDeptCode()
				+ ","
				+ nightlyOutBoundDTO.getDeptDesc()
				+ ",Total Product,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,2,5,"
				+ nightlyOutBoundDTO.getLicensed() + ","
				+ nightlyOutBoundDTO.getTargetCost() + ","
				+ nightlyOutBoundDTO.getOriginalRetailPrice() + ","
				+ nightlyOutBoundDTO.getWhPrice() + ","
				+ nightlyOutBoundDTO.getAvgWorkingCost1() + ","+nightlyOutBoundDTO.getProdStdCost()+",,,,,,,,"+nightlyOutBoundDTO.getCrossBody()+","+nightlyOutBoundDTO.getCrossBodyDesc()+","
				+ ",,,,,,,,,,,,,,,,,"
				+ nightlyOutBoundDTO.getStyleuniqueid()
				+ ",,,";
				

		nightlyOutBoundVO.setProdMastWriteRow(styleLevelRow);
		return nightlyOutBoundVO;
		
	}

	private NightlyOutBoundVO formatSubClassLevelRow(
			NightlyOutBoundDTO nightlyOutBoundDTO,
			NightlyOutBoundVO nightlyOutBoundVO) {
		
		/*
		 * sample string : D09BCLBLN,,D09BCLBLN,,,,,BLN,Bracelet
		 * Link,BCL,Bracelets,D09,Womens Watches,Total
		 * Product,,,,,,,,,,,,,,,,,,,
		 * ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,3,4,
		 */
		nightlyOutBoundVO = new NightlyOutBoundVO();
		String subClassLevelRow = ""
				+ nightlyOutBoundDTO.getDeptCode()
				+ nightlyOutBoundDTO.getClassCode()
				+ nightlyOutBoundDTO.getSubClassCode()
				+ ",,"
				+ nightlyOutBoundDTO.getDeptCode()
				+ nightlyOutBoundDTO.getClassCode()
				+ nightlyOutBoundDTO.getSubClassCode()
				+ ",,,,,"
				+ nightlyOutBoundDTO.getSubClassCode()
				+ ","
				+ nightlyOutBoundDTO.getSubClassDesc()
				+ ","
				+ nightlyOutBoundDTO.getClassCode()
				+ ","+nightlyOutBoundDTO.getClassDesc()+","+nightlyOutBoundDTO.getDeptCode()+","+nightlyOutBoundDTO.getDeptDesc()+",Total Product,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,3,4,";

		nightlyOutBoundVO.setProdMastWriteRow(subClassLevelRow);
		return nightlyOutBoundVO;
	}

	private NightlyOutBoundVO formatClassLevelRow(NightlyOutBoundDTO nightlyOutBoundDTO,NightlyOutBoundVO nightlyOutBoundVO) {

		/*
		 * sample str - D01LGO,,D01LGO,,,,,,,LGO,Logo,D01,Womens Bags,Total Product,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,4,3,
		 * */
		
		nightlyOutBoundVO = new NightlyOutBoundVO();
		String classLevelRow = ""
				+ nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()
				+ ",,"
				+ nightlyOutBoundDTO.getDeptCode()+ nightlyOutBoundDTO.getClassCode()
				+ ",,,,,,,"
				+ nightlyOutBoundDTO.getClassCode()
				+ ","
				+ nightlyOutBoundDTO.getClassDesc()
				+ ","
				+ nightlyOutBoundDTO.getDeptCode()
				+ ","
				+ nightlyOutBoundDTO.getDeptDesc()
				+ ",Total Product,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,4,3,";
		//nightlyOutBoundVO.setClassLevelRow(classLevelRow);
		nightlyOutBoundVO.setProdMastWriteRow(classLevelRow);
		//prodMastExtractVOList.add(nightlyOutBoundVO);
		return nightlyOutBoundVO;

	}

	private NightlyOutBoundVO formatDeptLevelRow(NightlyOutBoundDTO nightlyOutBoundDTO,NightlyOutBoundVO nightlyOutBoundVO) {

		// nightlyOutBoundDTO.checkSizedDept();
		/*
		 * 
		 * sample string : D09,,D09,,,,,,,,,D09,Womens Watches,Total Product,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,5,2,
		 */
		nightlyOutBoundVO = new NightlyOutBoundVO();
		String deptLevelRow = ""
				+ nightlyOutBoundDTO.getDeptCode()
				+ ",,"
				+ nightlyOutBoundDTO.getDeptCode()
				+ ",,,,,,,,,"
				+ nightlyOutBoundDTO.getDeptCode()
				+ ","
				+ nightlyOutBoundDTO.getDeptDesc()
				+ ",Total Product,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,5,2,"; 
		nightlyOutBoundVO.setProdMastWriteRow(deptLevelRow);

		return nightlyOutBoundVO;
		//prodMastExtractVOList.add(nightlyOutBoundVO);

	}

	private void formatStaticRow() {

		NightlyOutBoundVO staticLineObj = new NightlyOutBoundVO();
		staticLineObj.setStaticLine(nightlyOutBoundDTO.getStaticLine());
		staticLineObj.setProdMastWriteRow(nightlyOutBoundDTO.getStaticLine());
		prodMastExtractVOWrite.add(staticLineObj);
		logger.info("ProdMastExtract Process - Static row added");
	}

	
	private void getSizeScaleMappingValues(NightlyOutBoundVO nightlyOutBoundVO) {
		
		if (nightlyOutBoundVO != null) {
			sizeScaleMappingList = nightlyOutBoundDAO
					.getSizeScaleMappingList(nightlyOutBoundVO);
		}
		
	}
	
	private void getCostFromMOA(NightlyOutBoundVO nightlyOutBoundVO) {
		if (nightlyOutBoundVO != null) {
			costMoaList = nightlyOutBoundDAO
					.getCostMoaList(nightlyOutBoundVO);
			logger.info("Size of costMoaList is ==>"+costMoaList.size());
		}
		
	}
	
	private void getRpFtpAbcList(NightlyOutBoundVO nightlyOutBoundVO) {
		if (nightlyOutBoundVO != null) {
			rpFtpAbcList = nightlyOutBoundDAO
					.getRpFtpAbcList(nightlyOutBoundVO);
			logger.info("Size of rpFtpAbcList is after==>"+rpFtpAbcList.size());
		}
		
	}
	
	private void getAttributeStoreValues(NightlyOutBoundVO nightlyOutBoundVO) {

		if (nightlyOutBoundVO != null) {
			attributeStoreVOList = nightlyOutBoundDAO
					.getTotoExtractAttrKeys(nightlyOutBoundVO);
		}
	}

	private NightlyOutBoundDTO formatLineItem(
			NightlyOutBoundVO nightlyOutBoundVO, String STYLETYPE)
			throws Exception {

		NightlyOutBoundDTO nightlyOutBoundDTO = new NightlyOutBoundDTO();
		
		String tmpDate = null;
		
		
		
			
			nightlyOutBoundDTO.setDefaultIndicator("1");
			nightlyOutBoundDTO.setStyleNum(nightlyOutBoundVO.getStyleNum());
			if(nightlyOutBoundVO.getStyleuniqueid()!=null)
			nightlyOutBoundDTO.setStyleuniqueid(nightlyOutBoundVO.getStyleuniqueid());
			else
				nightlyOutBoundDTO.setStyleuniqueid("");
			if(nightlyOutBoundVO.getSkuUniqueId()!=null)
			nightlyOutBoundDTO.setSkuUniqueId(nightlyOutBoundVO.getSkuUniqueId());
			else
				nightlyOutBoundDTO.setSkuUniqueId("");
			if(nightlyOutBoundVO.getSizeScale()!=null)
			nightlyOutBoundDTO.setSizeScale(nightlyOutBoundVO.getSizeScale());
			else
				nightlyOutBoundDTO.setSizeScale("");
			
			if(nightlyOutBoundVO.getStyleNum().length()<=5){
				String str = nightlyOutBoundVO.getStyleNum();
				
					if(str.length()<5 ){
					str = nightlyOutBoundVO.getStyleNum()+"_";
						for(int i = str.length(); str.length()<7;i++)
						 str = str+"_";
						str = str.substring(0, 6);
						
					}else if(str.length() ==5){
						str = str+"_";
					
					}else{
						str = nightlyOutBoundVO.getStyleNum();
					}
					
				nightlyOutBoundDTO.setStyleNumImg(str);
			}else{
				nightlyOutBoundDTO.setStyleNumImg(nightlyOutBoundVO.getStyleNum());
			}
			
			if(nightlyOutBoundVO.getSkuName().length()<=4){
				String str = nightlyOutBoundVO.getSkuName();
				
					/*if(str.length()<=4 ){
						str = nightlyOutBoundVO.getSkuName()+"____";
						str = str.substring(0, 4);
						
					}else{
						str = nightlyOutBoundVO.getSkuName();
					}*/
					
				nightlyOutBoundDTO.setSkuNameImg(str+"_");
			}else{
				nightlyOutBoundDTO.setSkuNameImg(nightlyOutBoundVO.getSkuName()+"_");
			}
		
		
		if (!isNullOrEmpty(nightlyOutBoundVO.getStyleDesc())) {
			nightlyOutBoundDTO.setStyleDesc(convertCharUCaseWithSpace(applyRuleUCase(rightTrim(nightlyOutBoundVO.getStyleDesc()))));
		}else{
			nightlyOutBoundDTO.setStyleDesc("");
		}
		
		if (!isNullOrEmpty(nightlyOutBoundVO.getMerDesc())) {
			nightlyOutBoundDTO.setMerDesc(convertCharUCaseWithSpace(cleanCode(rightTrim(nightlyOutBoundVO.getMerDesc()),",", "")));
		}
		
		if (!isNullOrEmpty(nightlyOutBoundVO.getSkuName())) {
			nightlyOutBoundDTO.setSkuName(nightlyOutBoundVO.getSkuName());
		}
		
		
		// Size List for sku

		
		if (!isNullOrEmpty(nightlyOutBoundVO.getSizeScale())) {
			nightlyOutBoundDTO.setSizeIntLevel("1");
			for (SizeScaleVO sizeScaleVO : sizeScaleMappingList) {
				
				if (sizeScaleVO.getSizescale() != null
						&& sizeScaleVO.getSizescale().equalsIgnoreCase(
								nightlyOutBoundVO.getSizeScale())) {
					if (sizeScaleVO.getSizelist() != null
							&& sizeScaleVO.getSizelist().indexOf("|~*~|") > 0) {
						String sizeListStr = sizeScaleVO.getSizelist().trim();
						sizeListStr = sizeListStr.replace("|~*~|", "@");
						// logger.info(("String replaced by char====>" +
						// sizeListStr));
						String[] tmpStr = sizeListStr.split("@");
						String valKey = null;
						for (int k = 0; k < tmpStr.length; k++) {
							valKey = tmpStr[k];
							/*sizeList.add(attributemapper("SIZES", valKey,
									"display"));*/
							sizeList.add(valKey.trim());
						}
						//Collections.sort(sizeList);

					}
					if (sizeScaleVO.getSizelist() != null
							&& sizeScaleVO.getSizelist().indexOf("|~*~|") ==-1) {
						sizeList.add(sizeScaleVO.getSizelist().trim());
					}

				}
			}

		} else {
			nightlyOutBoundDTO.setSizeIntLevel("0");
		}
		
		
		

		if (!isNullOrEmpty(nightlyOutBoundVO.getDept())) {
			
				/*nightlyOutBoundDTO.setDept(attributemapper("DEPARTMENT",
					nightlyOutBoundVO.getDept().trim(),"display2"));*/
			
				nightlyOutBoundDTO.setDeptCode(nightlyOutBoundVO.getDept());
				nightlyOutBoundDTO.setDeptDesc(attributemapper("DEPARTMENT",
						nightlyOutBoundVO.getDept().trim(),"display2"));
		}else{
			nightlyOutBoundDTO.setDeptCode("");
			nightlyOutBoundDTO.setDeptDesc("");
		}
		
		

		if (!isNullOrEmpty(nightlyOutBoundVO.getStyleClass())) {
			nightlyOutBoundDTO.setClassDesc(attributemapper("STYLECLASS",
					nightlyOutBoundVO.getStyleClass().trim(),"display2"));
			nightlyOutBoundDTO.setClassCode(nightlyOutBoundVO.getStyleClass());
			
			
		}else{
			nightlyOutBoundDTO.setClassCode("");
			nightlyOutBoundDTO.setClassDesc("");
		}

		if (!isNullOrEmpty(nightlyOutBoundVO.getSubClass())) {
			nightlyOutBoundDTO.setSubClassDesc(attributemapper("SUBCLASS",
					nightlyOutBoundVO.getSubClass().trim(),"display2"));
			nightlyOutBoundDTO.setSubClassCode(nightlyOutBoundVO.getSubClass());
			
		}else{
			nightlyOutBoundDTO.setSubClassCode("");
			nightlyOutBoundDTO.setSubClassDesc("");
		}

		
		// Pricing attributes calculation
		calculatePricingAttributes(nightlyOutBoundVO,nightlyOutBoundDTO);
		
		// Licensed

		if (!isNullOrEmpty(nightlyOutBoundVO.getLicensed())) {
			nightlyOutBoundDTO
					.setLicensed(attributemapper("LICENSED",
							nightlyOutBoundVO.getLicensed().trim(),"notNullDisplay"));
		} else {
			nightlyOutBoundDTO.setLicensed("No");
		}

		//Collection
		if (!isNullOrEmpty(nightlyOutBoundVO.getCollection())) {

			nightlyOutBoundDTO.setCollection(attributemapper("COMPOSITION",
					nightlyOutBoundVO.getCollection().trim(),"display"));

			if (nightlyOutBoundDTO.getCollection().indexOf("-") > 0) {

				nightlyOutBoundDTO
						.setCollectionCode(nightlyOutBoundDTO.getCollection()
								.substring(
										0,
										nightlyOutBoundDTO.getCollection()
												.indexOf("-")));
				nightlyOutBoundDTO
						.setCollectionDesc(nightlyOutBoundDTO.getCollection()
								.substring(
										nightlyOutBoundDTO.getCollection()
												.indexOf("-") + 1));
			} else {
				nightlyOutBoundDTO.setCollectionCode(nightlyOutBoundVO
						.getCollection());
				nightlyOutBoundDTO.setCollectionDesc(nightlyOutBoundVO
						.getCollection());
			}
		} else {
			nightlyOutBoundDTO.setCollectionCode("N/A");
			nightlyOutBoundDTO.setCollectionDesc("No");
		}
		
		
		// Color Family
		if (!isNullOrEmpty(nightlyOutBoundVO.getColorFamily())) {
			nightlyOutBoundDTO.setColorFamily(
					attributemapper("COLORFAMILY",
							nightlyOutBoundVO.getColorFamily().trim(),"display"));
		}
		
		//Color Name
		if (!isNullOrEmpty(nightlyOutBoundVO.getColorName())) {
			/*nightlyOutBoundDTO.setColorName(
					attributemapper("FACECOLOR",
							nightlyOutBoundVO.getColorName().trim(),"display")
					);*/
			nightlyOutBoundDTO.setColorName(nightlyOutBoundVO.getColorName());
		}
		
		calculateDateAttributes(nightlyOutBoundVO,nightlyOutBoundDTO);
		
		// Material Group Name
        String material = nightlyOutBoundVO.getSkuMaterial();
        if(isNullOrEmpty(material))
        	material = nightlyOutBoundVO.getMaterial();
		if (!isNullOrEmpty(material)) {

			nightlyOutBoundDTO.setMaterialGroupName(attributemapper("MATERIAL",
					material, "display3").trim());
			nightlyOutBoundDTO.setMaterialGroupDesc(attributemapper("MATERIAL",
					material, "display4").trim());
			nightlyOutBoundDTO.setMaterialName(attributemapper("MATERIAL",
					material, "display2").trim());
			nightlyOutBoundDTO.setMaterial(material);
		} else {
			nightlyOutBoundDTO.setMaterialGroupName("N/A");
			nightlyOutBoundDTO.setMaterialGroupDesc("No");
			nightlyOutBoundDTO.setMaterialName("No");
			nightlyOutBoundDTO.setMaterial("N/A");

		}

		// Silhouette

		if (!isNullOrEmpty(nightlyOutBoundVO.getSilhouette())) {

			nightlyOutBoundDTO.setSilhouette(nightlyOutBoundVO.getSilhouette()
					.trim());
			nightlyOutBoundDTO.setSilhouetteDesc(attributemapper("SILHOUETTE",
					nightlyOutBoundVO.getSilhouette().trim(),"display2"));

		}else{
			nightlyOutBoundDTO.setSilhouette("N/A");
			nightlyOutBoundDTO.setSilhouetteDesc("No");
		}

		// Gender

		if (!isNullOrEmpty(nightlyOutBoundVO.getGender())) {

			nightlyOutBoundDTO.setGender(nightlyOutBoundVO.getGender().trim());
			nightlyOutBoundDTO.setGenderDesc(attributemapper("GENDER",
					nightlyOutBoundVO.getGender().trim(),"display"));

		}else{
			nightlyOutBoundDTO.setGender("N/A");
			nightlyOutBoundDTO.setGenderDesc("No");
		}
		// HArdware color
		String hardwarecolor = nightlyOutBoundVO.getSkuHardwareColor();
		if(isNullOrEmpty(hardwarecolor))
			hardwarecolor = nightlyOutBoundVO.getHardwareColor();
		if (!isNullOrEmpty(hardwarecolor)) {
			nightlyOutBoundDTO.setHardwareColor(hardwarecolor.trim());
			nightlyOutBoundDTO.setHardwareColorDesc(attributemapper("HARDWARECOLOR",
					hardwarecolor.trim(),"notNullDisplay"));

		}else{
			nightlyOutBoundDTO.setHardwareColor("N/A");
			nightlyOutBoundDTO.setHardwareColorDesc("No");
		}
		
		// Product Segmentation

				if (!isNullOrEmpty(nightlyOutBoundVO.getProductSegmentation())) {

			nightlyOutBoundDTO.setProductSegmentation(attributemapper(
					"PRODUCTSEGMENTATION",
					nightlyOutBoundVO.getProductSegmentation().trim(),
					"display"));

				}else{
					nightlyOutBoundDTO.setProductSegmentation("");
				}
		
		// Speacial Prod

		if (!isNullOrEmpty(nightlyOutBoundVO.getSpecialProduct())) {

			nightlyOutBoundDTO.setSpecialProduct(nightlyOutBoundVO
					.getSpecialProduct().trim());
			nightlyOutBoundDTO.setSpecialProductDesc(attributemapper("SPECIALPRODUCT",
					nightlyOutBoundVO.getSpecialProduct().trim(),"notNullDisplay"));

		} else {
			nightlyOutBoundDTO.setSpecialProduct("N/A");
			nightlyOutBoundDTO.setSpecialProductDesc("No");
		}
		
		
		retrieveRpFtpAbcDet(nightlyOutBoundVO,nightlyOutBoundDTO);

				
		// Plan Execution

		if (!isNullOrEmpty(nightlyOutBoundVO.getPlanExclusion())) {

			nightlyOutBoundDTO.setPlanExclusion(
					attributemapper("PLANEXCLUSION",
							nightlyOutBoundVO.getPlanExclusion().trim(),"display")
					);
		}else{
			nightlyOutBoundDTO.setPlanExclusion("N/A");
		}

		// Channel Exclusive

		if (!isNullOrEmpty(nightlyOutBoundVO.getChannelExclusive())) {

			nightlyOutBoundDTO.setChannelExclusive(
					attributemapper("CHANNELEXCLUSIVE",
							nightlyOutBoundVO.getChannelExclusive().trim(),"notNullDisplay")
					);
		}else{
			nightlyOutBoundDTO.setChannelExclusive("N/A");
		}	
		
		// StyleGroup

		if (!isNullOrEmpty(nightlyOutBoundVO.getStyleGroup())) {

			nightlyOutBoundDTO.setStyleGroup(
					attributemapper("MASTERSTYLE",
							nightlyOutBoundVO.getStyleGroup().trim(),"notNullDisplay")
					);
		}else if(!isNullOrEmpty(nightlyOutBoundVO.getMerDesc())){
			nightlyOutBoundDTO.setStyleGroup(applyRuleUCase(rightTrim(cleanCode(nightlyOutBoundVO.getMerDesc(),",", ""))));
		}else{
			nightlyOutBoundDTO.setStyleGroup("");
		}
		
		// ParentStyle

		if (!isNullOrEmpty(nightlyOutBoundVO.getParentStyle())) {

			nightlyOutBoundDTO.setParentStyle(nightlyOutBoundVO.getParentStyle());
			/*nightlyOutBoundDTO.setParentStyleDesc(attributemapper("ParentStyle",
					nightlyOutBoundVO.getParentStyle().trim()));*/
		}else{
			
			nightlyOutBoundDTO.setParentStyle("No Parent Style");
			/*nightlyOutBoundDTO.setParentStyleDesc("No Parent Style");*/
			
		}
		
		// Updated

		if (!isNullOrEmpty(nightlyOutBoundVO.getUpdated())) {

			nightlyOutBoundDTO.setUpdated(attributemapper("UPDATED",
					nightlyOutBoundVO.getUpdated().trim(),"notNullDisplay"));
			
		}else{
			nightlyOutBoundDTO.setUpdated("No");
		}
		
		// Status
		
		if (!isNullOrEmpty(nightlyOutBoundVO.getStatus())) {

			nightlyOutBoundDTO.setStatus(
					attributemapper("STATUS",
							nightlyOutBoundVO.getStatus().trim(),"display")
					);
		}
		
		// UPC Code

		if (!isNullOrEmpty(nightlyOutBoundVO.getUpcCode()) || false) {

			nightlyOutBoundDTO
					.setUpcCode(nightlyOutBoundVO.getUpcCode().trim());
		} else {
			nightlyOutBoundDTO.setUpcCode("0");
		}
		nightlyOutBoundDTO.setUpcCode("0");
		// Signature Type
		String signatureType = nightlyOutBoundVO.getSkuSignatureType();
		if(isNullOrEmpty(signatureType))
			signatureType = nightlyOutBoundVO.getSignatureType();
			
		if (!isNullOrEmpty(signatureType)) {
			
			nightlyOutBoundDTO.setSignatureType(signatureType.trim());
			nightlyOutBoundDTO.setSignatureDesc(
					attributemapper("LOGOTYPE",
							signatureType.trim(),"notNullDisplay").trim()
					);
		}else{
			
			nightlyOutBoundDTO.setSignatureType("N/A");
			nightlyOutBoundDTO.setSignatureDesc("No");
		}
		
		
		// Hand Bag Size

				if (!isNullOrEmpty(nightlyOutBoundVO.getHandBagSize())) {

					nightlyOutBoundDTO.setHandBagSizeDesc(
							attributemapper("HANDBAGSIZE",
									nightlyOutBoundVO.getHandBagSize().trim(),"display")
							);
				}else{
					
					nightlyOutBoundDTO.setHandBagSizeDesc("N/A");
				}
		
		
		// Factory Store Type 

		if (!isNullOrEmpty(nightlyOutBoundVO.getFactoryType())) {

			nightlyOutBoundDTO.setFactoryType(attributemapper("FACTORYSTORETYPEPRODUCT",
					nightlyOutBoundVO.getFactoryType().trim(),"display"));
			nightlyOutBoundDTO.setShortFactoryType(attributemapper("FACTORYSTORETYPEPRODUCT",
					nightlyOutBoundVO.getFactoryType().trim(),"display2"));
		}else{
			nightlyOutBoundDTO.setFactoryType("No Factory");
			nightlyOutBoundDTO.setShortFactoryType("");
		}
		
		// Exotic

		if (!isNullOrEmpty(nightlyOutBoundVO.getExotic())) {

			nightlyOutBoundDTO.setExotic(applyRuleUCase(nightlyOutBoundVO.getExotic().trim()));
		}else{
			nightlyOutBoundDTO.setExotic("No");
		}
		
		// Sub Collection

		if (!isNullOrEmpty(nightlyOutBoundVO.getSubCollection())) {

			nightlyOutBoundDTO.setSubCollection(nightlyOutBoundVO
					.getSubCollection().trim());
			nightlyOutBoundDTO.setSubCollectionDesc(attributemapper(
					"PLATFORM", nightlyOutBoundVO.getSubCollection().trim(),"display2"));
		} else {
			nightlyOutBoundDTO.setSubCollection("N/A");
			nightlyOutBoundDTO.setSubCollectionDesc("No");
		}
		
		
		
		
		// Attitudinal Segments
		if (!isNullOrEmpty(nightlyOutBoundVO.getAttitudinalSegments())) {

			nightlyOutBoundDTO.setAttitudinalSegments(attributemapper("ATTITUDINALSEGMENTS",
					nightlyOutBoundVO.getAttitudinalSegments().trim(),"display")
			);
		} else {

			nightlyOutBoundDTO.setAttitudinalSegments("");
		}
		
		// Attitude
		if (!isNullOrEmpty(nightlyOutBoundVO.getAttitude())) {

			nightlyOutBoundDTO.setAttitude(attributemapper(
					"FAMILY", nightlyOutBoundVO.getAttitude()
					.trim(),"display"));
		} else {

			nightlyOutBoundDTO.setAttitude("");
		}
		
		
		// Embellishment
		if (!isNullOrEmpty(nightlyOutBoundVO.getEmbellishment())) {

			nightlyOutBoundDTO.setEmbellishment(attributemapper(
					"EMBELLISHMENT", nightlyOutBoundVO.getEmbellishment().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setEmbellishment("");
		}
		
		// PINT
		if (!isNullOrEmpty(nightlyOutBoundVO.getPint())) {
			//Ravinder
			nightlyOutBoundDTO.setPint(attributemapper(
					"PRINT", nightlyOutBoundVO.getPint().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setPint("");
		}
		// Gifting
				if (!isNullOrEmpty(nightlyOutBoundVO.getGifting())) {
					//Ravinder
					nightlyOutBoundDTO.setGifting(attributemapper(
							"PRINT", nightlyOutBoundVO.getGifting().trim(),"display"));
				} else {

					nightlyOutBoundDTO.setGifting("");
				}
		
		//Collaboration
		if (!isNullOrEmpty(nightlyOutBoundVO.getCollaboration())) {
			//Ravinder
			nightlyOutBoundDTO.setCollaboration(attributemapper(
					"COLLABORATION", nightlyOutBoundVO.getCollaboration().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setCollaboration("");
		}
		
		//Cross body

		if (!isNullOrEmpty(nightlyOutBoundVO.getCrossBody())) {
			//Ravinder
			nightlyOutBoundDTO.setCrossBody(nightlyOutBoundVO.getCrossBody());
			nightlyOutBoundDTO.setCrossBodyDesc(attributemapper("CROSSBODY",
					nightlyOutBoundVO.getCrossBody().trim(),"notNullDisplay"));
		}else{
			nightlyOutBoundDTO.setCrossBody("");
			nightlyOutBoundDTO.setCrossBodyDesc("");
		}
		// Product Indentity
		if (!isNullOrEmpty(nightlyOutBoundVO.getProductIdentity())) {

			//ravinder
			/*nightlyOutBoundDTO.setProductIdentity(
					convertCharUCaseWithSpace(cleanCode(rightTrim(ConvertStringToCamelCase(nightlyOutBoundVO
							.getProductIdentity())),",", "")));*/
			nightlyOutBoundDTO.setProductIdentity(attributemapper(
					"PRODUCTIDENTITY", nightlyOutBoundVO.getProductIdentity().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setProductIdentity("");
		}
		
		// Lens Type
		if (!isNullOrEmpty(nightlyOutBoundVO.getLens())) {

			nightlyOutBoundDTO.setLens(attributemapper(
					"LENSTYPE", nightlyOutBoundVO.getLens().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setLens("");
		}
		
		// Watch Case Shape
		if (!isNullOrEmpty(nightlyOutBoundVO.getWatchCaseShape())) {
//Ravinder
			//System.out.println("the getWAtchase " +nightlyOutBoundVO.getWatchCaseShape());
			nightlyOutBoundDTO.setWatchCaseShape(attributemapper(
					"CASESHAPE", nightlyOutBoundVO.getWatchCaseShape().trim(),"display"));
			//System.out.println("the getWAtchase " +nightlyOutBoundDTO.getWatchCaseShape());
		} else {

			nightlyOutBoundDTO.setWatchCaseShape("");
		}
		
		// Watch Case Size3	1
		if (!isNullOrEmpty(nightlyOutBoundVO.getWatchCaseSize())) {

			nightlyOutBoundDTO.setWatchCaseSize(attributemapper(
					"CASESIZE", nightlyOutBoundVO.getWatchCaseSize().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setWatchCaseSize("");
		}
		
		// Face Color
		if (!isNullOrEmpty(nightlyOutBoundVO.getFaceColor())) {

			nightlyOutBoundDTO.setFaceColor(attributemapper(
					"FACECOLOR", nightlyOutBoundVO.getFaceColor().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setFaceColor("");
		}
		
		// Functionality
		if (!isNullOrEmpty(nightlyOutBoundVO.getFunctionality())) {

			nightlyOutBoundDTO.setFunctionality(attributemapper(
					"FUNCTIONALITY", nightlyOutBoundVO.getFunctionality().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setFunctionality("");
		}
		
		// HeelHieght
		if (!isNullOrEmpty(nightlyOutBoundVO.getHeelHeight1())) {

			nightlyOutBoundDTO.setHeelHeight1(attributemapper(
					"HEELHEIGHT", nightlyOutBoundVO.getHeelHeight1().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setHeelHeight1("");
		}
		
		// Toe Type
		if (!isNullOrEmpty(nightlyOutBoundVO.getToeType())) {

			nightlyOutBoundDTO
					.setToeType(attributemapper(
							"TOETYPE", nightlyOutBoundVO.getToeType().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setToeType("");
		}
		
		// Toe Shape
		if (!isNullOrEmpty(nightlyOutBoundVO.getToeShape())) {

			nightlyOutBoundDTO.setToeShape(attributemapper(
					"TOESHAPE", nightlyOutBoundVO.getToeShape().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setToeShape("");
		}
		
		// Sole Type
		if (!isNullOrEmpty(nightlyOutBoundVO.getSoleType())) {

			nightlyOutBoundDTO.setSoleType(attributemapper(
					"SOLETYPE", nightlyOutBoundVO.getSoleType().trim(),"display"));
		} else {

			nightlyOutBoundDTO.setSoleType("");
		}
		
		
		return nightlyOutBoundDTO;
	}

	private void retrieveRpFtpAbcDet(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) {
		
		logger.info("Inside method - retrieveRpFtpAbcDet");
		nightlyOutBoundVO.setAbcCode("");
		nightlyOutBoundVO.setMpg("0.0");
		
		
		Iterator iter = rpFtpAbcList.iterator();
		while (iter.hasNext()) {
			RpFtpAbcVO rpFtpAbcVO = (RpFtpAbcVO) iter.next();
/*			logger.info("nightlyOutBoundVO getStyleNum ==>"+nightlyOutBoundVO.getStyleNum()+nightlyOutBoundVO.getSkuName());
			logger.info("rpFtpAbcVO getStyleNumber ==>"+rpFtpAbcVO.getStyleNumber()+rpFtpAbcVO.getSkuName());*/
				if((rpFtpAbcVO.getStyleNumber()+rpFtpAbcVO.getSkuName()).equalsIgnoreCase(nightlyOutBoundVO.getStyleNum()+nightlyOutBoundVO.getSkuName())){
					nightlyOutBoundVO.setAbcCode(rpFtpAbcVO.getAbcCode());
					nightlyOutBoundVO.setMpg(rpFtpAbcVO.getMpg());
					
				}
			}	
		
		// ABC code
				String display2="display2";
				if (!isNullOrEmpty(nightlyOutBoundVO.getAbcCode())) {
					
//					logger.info("nightlyOutBoundVO getAbcCode ==>"+nightlyOutBoundVO.getAbcCode());
					
					nightlyOutBoundDTO.setAbcCode(nightlyOutBoundVO
							.getAbcCode().trim());
					
					String abcCode = attributemapper("ABCCODE",nightlyOutBoundVO.getAbcCode().trim(),"display");
					
					if(abcCode!= null){
						abcCode= abcCode.replaceAll(",","").trim();
						abcCode = nightlyOutBoundVO.getAbcCode().trim() +" - "+ abcCode;
					}
					
					nightlyOutBoundDTO.setAbcCodeDesc(abcCode);
					//System.out.println("ABCCode des after "+ abcCode);

				}else{
					nightlyOutBoundDTO.setAbcCode("N/A");
					nightlyOutBoundDTO.setAbcCodeDesc("No ABC");
				}
				
				
				// Mpg

				if (!isNullOrEmpty(nightlyOutBoundVO.getMpg())
						&& Float.parseFloat(nightlyOutBoundVO.getMpg()) > 0.0) {

					nightlyOutBoundDTO.setMpg(getDecimalRounded(1, nightlyOutBoundVO
							.getMpg().trim()));
				} else {
					nightlyOutBoundDTO.setMpg("N/A");
				}

		
	}



	private void calculateDateAttributes(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception {
		
		
		/* Sku Intro date calculations*/
		
		calculateSkuIntroDate(nightlyOutBoundVO,nightlyOutBoundDTO); 
		
		/* Style Intro date calculations*/
		
		calculateStyleIntroDate(nightlyOutBoundVO,nightlyOutBoundDTO);
		
		/* Style Intro date calculations */

		calculateStyleDeleteDate(nightlyOutBoundVO,nightlyOutBoundDTO);
		
		/* Sku Delete date calculations */

		calculateSkuDeleteDate(nightlyOutBoundVO,nightlyOutBoundDTO);
		
		/* Sku Fs Intro date calculations */

		calculateSkuFsIntroDate(nightlyOutBoundVO,nightlyOutBoundDTO);		
		
		/* Style Fs Intro date calculations */

		calculateStyleFsIntroDate(nightlyOutBoundVO,nightlyOutBoundDTO);
		
		/* Style Fs Delete date calculations */

		calculateStyleFsDeleteDate(nightlyOutBoundVO,nightlyOutBoundDTO);
		
		/* Sku Fs Delete date calculations */

		calculateSkuFsDeleteDate(nightlyOutBoundVO,nightlyOutBoundDTO);
		
	}

	private void calculateSkuFsDeleteDate(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception{
		
		String tmp=" ";
		
		// Sku Fs Delete Year ; Sku Delete Quater from Season Name
		// sample data : Q2 FY07 Watches

		/*if (nightlyOutBoundDTO.getSkuFsDeleteYear() != null) {
			
			nightlyOutBoundDTO.setSkuFsDeleteQuaterStr((nightlyOutBoundDTO
					.getSkuFsDeleteYear().trim()).substring(0, 2)
					+ (nightlyOutBoundDTO.getSkuFsDeleteYear().trim())
							.substring(3, 7));
			nightlyOutBoundDTO.setSkuFsDeleteYearStr((nightlyOutBoundDTO
					.getSkuFsDeleteYear().trim()).substring(3, 7));
			
		} else {
			nightlyOutBoundDTO.setSkuFsDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setSkuFsDeleteYearStr("No Delete Year");
		}*/
		
		// Sku Fs Delete Date
		// Sample data : 7/1/FY09 Watches
		// No Intro Date
		if (!isNullOrEmpty(nightlyOutBoundDTO.getSkuFSDeleteDate())) {
			
			String fmtDateStr = null;
			//Fiscal date
			 if (nightlyOutBoundDTO.getSkuFSDeleteDate().trim().indexOf(tmp) > -1)
				 fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuFSDeleteDate().trim()).substring(0, nightlyOutBoundDTO.getSkuFSDeleteDate().trim().indexOf(tmp)));
			 else
				 fmtDateStr = formatDate((nightlyOutBoundDTO
							.getSkuFSDeleteDate().trim()).substring(0));
			 
			/*String fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuFSDeleteDate().trim()).substring(0,  nightlyOutBoundDTO
							.getSkuFSDeleteDate().trim().indexOf(tmp)));*/
			nightlyOutBoundDTO.setSkuFsDeleteDateStr(fmtDateStr);
			
			// Quarter and Year
			String qtr = getQuarterFromSeasonGroup(nightlyOutBoundDTO
					.getSkuFSDeleteDate());

			nightlyOutBoundDTO.setSkuFsDeleteQuaterStr(qtr);

			// Fiscal Year
			if (!isNullOrEmpty(qtr))
				nightlyOutBoundDTO.setSkuFsDeleteYearStr(qtr.trim().substring(2));
			//calendar date
			nightlyOutBoundDTO.setSkuFsDeleteCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundDTO
							.getSkuFSDeleteDate().trim()));

		}  else {
			nightlyOutBoundDTO.setSkuFsDeleteDateStr("No Delete Date");
			nightlyOutBoundDTO.setSkuFsDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setSkuFsDeleteYearStr("No Delete Year");
			nightlyOutBoundDTO.setSkuFsDeleteCalenderDate(defaultDate);
		}
		
		// Sku Fs delete calender data

		/*if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuFSDeleteDate())) {

			nightlyOutBoundDTO.setSkuFsDeleteCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundDTO
							.getSkuFSDeleteDate().trim()));
		} else {
			nightlyOutBoundDTO.setSkuFsDeleteCalenderDate(defaultDate);
		}*/
		
	}

	private void calculateStyleFsDeleteDate(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception {
		
		String tmp=" ";
		// Style Fs Delete Year ; Style Delete Quater from Season Name
		// sample data : Q2 FY07 Watches

		/* if (nightlyOutBoundVO.getStyleFsDeleteYear() != null) {
			nightlyOutBoundDTO.setStyleFsDeleteQuaterStr((nightlyOutBoundVO
					.getStyleFsDeleteYear().trim()).substring(0, 2)
					+ (nightlyOutBoundVO.getStyleFsDeleteYear().trim())
							.substring(3, 7));
			nightlyOutBoundDTO.setStyleFsDeleteYearStr((nightlyOutBoundVO
					.getStyleFsDeleteYear().trim()).substring(3, 7));
		} else {
			nightlyOutBoundDTO.setStyleFsDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setStyleFsDeleteYearStr("No Delete Year");
		}*/
		
		// Style Fs Delete Date
		// Sample data : 7/1/FY09 Watches
		 if (nightlyOutBoundVO.getStyleFSDeleteDate() != null) {
			 
			 String fmtDateStr = null;
				//Fiscal date
				 if (nightlyOutBoundVO.getStyleFSDeleteDate().trim().indexOf(tmp) > -1)
					 fmtDateStr = formatDate((nightlyOutBoundVO
						.getStyleFSDeleteDate().trim()).substring(0, nightlyOutBoundVO.getStyleFSDeleteDate().trim().indexOf(tmp)));
				 else
					 fmtDateStr = formatDate((nightlyOutBoundVO
								.getStyleFSDeleteDate().trim()).substring(0));
			
			/*String fmtDateStr = formatDate((nightlyOutBoundVO
					.getStyleFSDeleteDate().trim()).substring(0, nightlyOutBoundVO
							.getStyleFSDeleteDate().trim().indexOf(tmp)));*/
			nightlyOutBoundDTO.setStyleFsDeleteDateStr(fmtDateStr);
			
			// Quarter and Year
			String qtr = getQuarterFromSeasonGroup(nightlyOutBoundVO
					.getStyleFSDeleteDate().trim());

			nightlyOutBoundDTO.setStyleFsDeleteQuaterStr(qtr);

			// Fiscal Year
			if (!isNullOrEmpty(qtr))
				nightlyOutBoundDTO.setStyleFsDeleteYearStr(qtr.trim().substring(
						2));
			//calendar date
			nightlyOutBoundDTO.setStyleFsDeleteCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundVO
							.getStyleFSDeleteDate().trim()));
			

		} else {
			nightlyOutBoundDTO.setStyleFsDeleteDateStr("No Delete Date");
			nightlyOutBoundDTO.setStyleFsDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setStyleFsDeleteYearStr("No Delete Year");
			nightlyOutBoundDTO.setStyleFsDeleteCalenderDate(defaultDate);
		}
		

		// Style Fs delete calender date
		 /*if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFSDeleteDate())) {

			nightlyOutBoundDTO.setStyleFsDeleteCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundVO
							.getStyleFSDeleteDate().trim()));

		} else {
			nightlyOutBoundDTO.setStyleFsDeleteCalenderDate(defaultDate);
		}*/
		
	}

	private void calculateStyleFsIntroDate(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception{
		
		String tmp=" ";
		// Style FsIntro Year

		/*if (nightlyOutBoundVO.getStyleFsIntroYear() != null) {

			nightlyOutBoundDTO.setStyleFsIntroQuaterStr((nightlyOutBoundVO
					.getStyleFsIntroYear().trim()).substring(0, 2)
					+ (nightlyOutBoundVO.getStyleFsIntroYear().trim())
							.substring(3, 7));
			nightlyOutBoundDTO.setStyleFsIntroYearStr((nightlyOutBoundVO
					.getStyleFsIntroYear().trim()).substring(3, 7));
		} else {
			nightlyOutBoundDTO.setStyleFsIntroQuaterStr("No Intro Qtr");
			nightlyOutBoundDTO.setStyleFsIntroYearStr("No Intro Year");
		}*/

		// Style Fs Intro Date
		// Sample data : 7/1/FY09 Watches
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFsIntroDate())) {

			
			String fmtDateStr = null;
			//Fiscal date
			 if (nightlyOutBoundVO.getStyleFsIntroDate().trim().indexOf(tmp) > -1)
				 fmtDateStr = formatDate((nightlyOutBoundVO
					.getStyleFsIntroDate().trim()).substring(0, nightlyOutBoundVO.getStyleFsIntroDate().trim().indexOf(tmp)));
			 else
				 fmtDateStr = formatDate((nightlyOutBoundVO
							.getStyleFsIntroDate().trim()).substring(0));
			
			/*String fmtDateStr = formatDate((nightlyOutBoundVO
					.getStyleFsIntroDate().trim()).substring(0, nightlyOutBoundVO
							.getStyleFsIntroDate().trim().indexOf(tmp)));*/
			nightlyOutBoundDTO.setStyleFsIntroDateStr(fmtDateStr);
			
			// Quarter and Year
			String qtr = getQuarterFromSeasonGroup(nightlyOutBoundVO
					.getStyleFsIntroDate());

			nightlyOutBoundDTO.setStyleFsIntroQuaterStr(qtr);

			// Fiscal Year
			if (!isNullOrEmpty(qtr))
				nightlyOutBoundDTO.setStyleFsIntroYearStr(qtr.trim().substring(2));
			
			//Calendar date 
			nightlyOutBoundDTO.setStyleFsIntroCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundVO
							.getStyleFsIntroDate().trim()));

		} else {
			nightlyOutBoundDTO.setStyleFsIntroDateStr("No Intro Date");
			nightlyOutBoundDTO.setStyleFsIntroQuaterStr("No Intro Qtr");
			nightlyOutBoundDTO.setStyleFsIntroYearStr("No Intro Year");
			nightlyOutBoundDTO.setStyleFsIntroCalenderDate(defaultDate);
		}
		
		// Style Fs intro calender data

		/*if (nightlyOutBoundVO.getStyleFsIntroDate() != null) {

			nightlyOutBoundDTO.setStyleFsIntroCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundVO
							.getStyleFsIntroDate().trim()));
		} else {
			nightlyOutBoundDTO.setStyleFsIntroCalenderDate(defaultDate);
		}*/

	}

	private void calculateSkuDeleteDate(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception{
		
		String tmp=" ";
		
		// Sku Delete Year ; Sku Delete Quater from Season Name
		// sample data : Q2 FY07 Watches

		/*if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuDeleteYear())) {

			nightlyOutBoundDTO.setSkuDeleteQuaterStr((nightlyOutBoundDTO
					.getSkuDeleteYear().trim()).substring(0, 2)
					+ (nightlyOutBoundDTO.getSkuDeleteYear().trim()).substring(
							3, 7));
			nightlyOutBoundDTO.setSkuDeleteYearStr((nightlyOutBoundDTO
					.getSkuDeleteYear().trim()).substring(3, 7));
		} else if(!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuFsDeleteYear())
				&& nightlyOutBoundVO.getFactoryType().equalsIgnoreCase("factoryExclusive")){
		
			nightlyOutBoundDTO.setSkuDeleteQuaterStr((nightlyOutBoundDTO
					.getSkuFsDeleteYear().trim()).substring(0, 2)
					+ (nightlyOutBoundDTO.getSkuFsDeleteYear().trim())
							.substring(3, 7));
			nightlyOutBoundDTO.setSkuDeleteYearStr((nightlyOutBoundDTO
					.getSkuFsDeleteYear().trim()).substring(3, 7));
		}else if(isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuDeleteYearStr())) {
			nightlyOutBoundDTO.setSkuDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setSkuDeleteYearStr("No Delete Year");
		}*/
		
		// Sku delete calender data
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuDeleteDate())) {

			nightlyOutBoundDTO.setSkuDeleteCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundDTO
							.getSkuDeleteDate().trim()));
			
			// Quarter and Year
			String qtr = getQuarterFromSeasonGroup(nightlyOutBoundDTO.getSkuDeleteDate());

			nightlyOutBoundDTO.setSkuDeleteQuaterStr(qtr);

			// Fiscal Year
			if (!isNullOrEmpty(qtr))
				nightlyOutBoundDTO.setSkuDeleteYearStr(qtr.trim().substring(2));
			
			
			String fmtDateStr = null;
			//Fiscal date
			 if (nightlyOutBoundDTO.getSkuDeleteDate().trim().indexOf(tmp) > -1)
				 fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuDeleteDate().trim()).substring(0, nightlyOutBoundDTO.getSkuDeleteDate().trim().indexOf(tmp)));
			 else
				 fmtDateStr = formatDate((nightlyOutBoundDTO
							.getSkuDeleteDate().trim()).substring(0));	
			
			
			/*String fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuDeleteDate().trim()).substring(0, nightlyOutBoundDTO
							.getSkuDeleteDate().trim().indexOf(tmp)));*/
			nightlyOutBoundDTO.setSkuDeleteDateStr(fmtDateStr);
			
		} else if(!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuFSDeleteDate())
				&& "factoryExclusive".equalsIgnoreCase(nightlyOutBoundVO.getFactoryType())){
		
			nightlyOutBoundDTO.setSkuDeleteCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundDTO
							.getSkuFSDeleteDate().trim()));
			
			// Quarter and Year
			String qtr = getQuarterFromSeasonGroup(nightlyOutBoundDTO.getSkuFSDeleteDate());
			
			nightlyOutBoundDTO.setSkuDeleteQuaterStr(qtr);
			
			// Fiscal Year
			if (!isNullOrEmpty(qtr))
				nightlyOutBoundDTO.setSkuDeleteYearStr(qtr.trim().substring(2));
			
			
			String fmtDateStr = null;
			//Fiscal date
			 if (nightlyOutBoundDTO.getSkuFSDeleteDate().trim().indexOf(tmp) > -1)
				 fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuFSDeleteDate().trim()).substring(0, nightlyOutBoundDTO.getSkuFSDeleteDate().trim().indexOf(tmp)));
			 else
				 fmtDateStr = formatDate((nightlyOutBoundDTO
							.getSkuFSDeleteDate().trim()).substring(0));
			 
		/*
			
			String fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuFSDeleteDate().trim()).substring(0, nightlyOutBoundDTO
							.getSkuFSDeleteDate().trim().indexOf(tmp)));*/
			nightlyOutBoundDTO.setSkuDeleteDateStr(fmtDateStr);
			
		}else {
			nightlyOutBoundDTO.setSkuDeleteCalenderDate(defaultDate);
			nightlyOutBoundDTO.setSkuDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setSkuDeleteYearStr("No Delete Year");
			nightlyOutBoundDTO.setSkuDeleteDateStr("No Delete Date");
		}
		
		// Sku Delete Date
		// Sample data : 7/1/FY09 Watches
		// No Intro Date
		/*if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuDeleteDate())) {
			
			String fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuDeleteDate().trim()).substring(0, nightlyOutBoundDTO
							.getSkuDeleteDate().trim().indexOf(tmp)));
			
			nightlyOutBoundDTO.setSkuDeleteDateStr(fmtDateStr);

		} else if(!isNullOrEmpty(nightlyOutBoundDTO.getSkuFSDeleteDate())
				&& nightlyOutBoundVO.getFactoryType().equalsIgnoreCase(
						"factoryExclusive")){
		
			
				 String fmtDateStr = formatDate((nightlyOutBoundDTO
							.getSkuFSDeleteDate().trim()).substring(0, nightlyOutBoundDTO
									.getSkuFSDeleteDate().trim().indexOf(tmp))); 
				 
				nightlyOutBoundDTO.setSkuDeleteDateStr(fmtDateStr);
			
		}
		
		if(isNullOrEmpty(nightlyOutBoundDTO.getSkuDeleteDateStr())) {
			nightlyOutBoundDTO.setSkuDeleteDateStr("No Delete Date");
		}*/
		
	}

	private void calculateStyleDeleteDate(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception {
		
		String tmp=" ";
		
		
		
		
			/*if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleDeleteYear())) {
				nightlyOutBoundDTO.setStyleDeleteQuaterStr((nightlyOutBoundDTO
						.getStyleDeleteYear().trim()).substring(0, 2)
						+ (nightlyOutBoundDTO.getStyleDeleteYear().trim())
								.substring(3, 7));
				nightlyOutBoundDTO.setStyleDeleteYearStr((nightlyOutBoundDTO
						.getStyleDeleteYear().trim()).substring(3, 7));
		} else {
			nightlyOutBoundDTO.setStyleDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setStyleDeleteYearStr("No Delete Year");
		}
		
		if(isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleDeleteQuaterStr())){
			nightlyOutBoundDTO.setStyleDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setStyleDeleteYearStr("No Delete Year");
		}*/
		
		
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleDeleteDate())) {
				
			//calendar date
			nightlyOutBoundDTO.setStyleDeleteCalenderDate(ToToUtil
						.getIntroWithCalendarYear(nightlyOutBoundDTO.getStyleDeleteDate().trim()));
				
				// Quarter and Year
				String qtr = getQuarterFromSeasonGroup(nightlyOutBoundDTO.getStyleDeleteDate());

				nightlyOutBoundDTO.setStyleDeleteQuaterStr(qtr);

				// Fiscal Year
				if (!isNullOrEmpty(qtr))
					nightlyOutBoundDTO.setStyleDeleteYearStr(qtr.trim().substring(2));
				
				
				String fmtDateStr = null;
				//Fiscal date
				 if (nightlyOutBoundDTO.getStyleDeleteDate().trim().indexOf(tmp) > -1)
					 fmtDateStr = formatDate((nightlyOutBoundDTO
						.getStyleDeleteDate().trim()).substring(0, nightlyOutBoundDTO.getStyleDeleteDate().trim().indexOf(tmp)));
				 else
					 fmtDateStr = formatDate((nightlyOutBoundDTO
								.getStyleDeleteDate().trim()).substring(0));	
				
			
				/*String fmtDateStr = formatDate((nightlyOutBoundDTO.getStyleDeleteDate().trim()).
						substring(0, nightlyOutBoundDTO.getStyleDeleteDate().trim().indexOf(tmp)));*/
				nightlyOutBoundDTO.setStyleDeleteDateStr(fmtDateStr);
			
		 
		} else {
			nightlyOutBoundDTO.setStyleDeleteCalenderDate(defaultDate);
			nightlyOutBoundDTO.setStyleDeleteQuaterStr("No Delete Qtr");
			nightlyOutBoundDTO.setStyleDeleteYearStr("No Delete Year");
			nightlyOutBoundDTO.setStyleDeleteDateStr("No Delete Date");
		}
		
		/*if(isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleDeleteCalenderDate())){
			nightlyOutBoundDTO.setStyleDeleteCalenderDate(defaultDate);
		}*/
		
		
		/*if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleDeleteDate())) {
			
			String fmtDateStr = formatDate((nightlyOutBoundDTO.getStyleDeleteDate().trim()).
					substring(0, nightlyOutBoundDTO.getStyleDeleteDate().trim().indexOf(tmp)));
			nightlyOutBoundDTO.setStyleDeleteDateStr(fmtDateStr);
			
		}
		else {
			nightlyOutBoundDTO.setStyleDeleteDateStr("No Delete Date");
		}
		
		if(isNullOrEmpty(nightlyOutBoundDTO.getStyleDeleteDateStr())){
			nightlyOutBoundDTO.setStyleDeleteDateStr("No Delete Date");
		}*/
		
	}

	private void calculateStyleIntroDate(NightlyOutBoundVO nightlyOutBoundVO,
			NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception {

		String tmp = " ";
		/*logic for setStyleIntroQuaterStr and
		 * setStyleIntroYearStr  - begins
		 * */
		applyInheritanceForStyle(nightlyOutBoundVO,nightlyOutBoundDTO);  
		
		/*if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleIntroYear())) {

			nightlyOutBoundDTO.setStyleIntroQuaterStr((nightlyOutBoundDTO
					.getStyleIntroYear().trim()).substring(0, 2)
					+ (nightlyOutBoundDTO.getStyleIntroYear().trim()).substring(
							3, 7));
			nightlyOutBoundDTO.setStyleIntroYearStr((nightlyOutBoundDTO
					.getStyleIntroYear().trim()).substring(3, 7));
		}else if (isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleIntroQuaterStr())) {
			nightlyOutBoundDTO.setStyleIntroQuaterStr("No Intro Qtr");
			nightlyOutBoundDTO.setStyleIntroYearStr("No Intro Year");
		}*/
		
				
		if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleIntroDate())) {

			String fmtDateStr = null;
			if (nightlyOutBoundDTO.getStyleIntroDate().trim().indexOf(tmp) > -1)
				 fmtDateStr = formatDate((nightlyOutBoundDTO
					.getStyleIntroDate().trim()).substring(0, nightlyOutBoundDTO.getStyleIntroDate().trim().indexOf(tmp)));
			 else
				 fmtDateStr = formatDate((nightlyOutBoundDTO
							.getStyleIntroDate().trim()).substring(0));
			
			
			/*String fmtDateStr = formatDate((nightlyOutBoundDTO
					.getStyleIntroDate().trim()).substring(0, nightlyOutBoundDTO
					.getStyleIntroDate().trim().indexOf(tmp)));*/
			nightlyOutBoundDTO.setStyleIntroDateStr(fmtDateStr);
			
			// Quarter and Year
			String qtr = getQuarterFromSeasonGroup(nightlyOutBoundDTO
					.getStyleIntroDate());

			nightlyOutBoundDTO.setStyleIntroQuaterStr(qtr);

			// Fiscal Year
			if (!isNullOrEmpty(qtr))
				nightlyOutBoundDTO
						.setStyleIntroYearStr(qtr.trim().substring(2));
			
			//calendar date
			nightlyOutBoundDTO.setStyleIntroCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundDTO
							.getStyleIntroDate().trim()));
			
			
			
		}else if (isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleIntroDateStr())) {
			nightlyOutBoundDTO.setStyleIntroDateStr("No Intro Date");
			nightlyOutBoundDTO.setStyleIntroQuaterStr("No Intro Qtr");
			nightlyOutBoundDTO.setStyleIntroYearStr("No Intro Year");
			nightlyOutBoundDTO.setStyleIntroCalenderDate(defaultIntroDate);
		}
		
	
		/*if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleIntroDate())) {

			nightlyOutBoundDTO.setStyleIntroCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundDTO
							.getStyleIntroDate().trim()));
		} else if (isNullOrEmptyOrZero(nightlyOutBoundDTO.getStyleIntroCalenderDate())) {
			nightlyOutBoundDTO.setStyleIntroCalenderDate(defaultIntroDate);
		}*/
		
		

	}

	private void applyInheritanceForStyle(NightlyOutBoundVO nightlyOutBoundVO,
			NightlyOutBoundDTO nightlyOutBoundDTO) {

		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleIntroYear())) {
			nightlyOutBoundDTO.setStyleIntroYear(nightlyOutBoundVO.getStyleIntroYear());	
		}else if(!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFsIntroYear())
				&& nightlyOutBoundVO.getFactoryType().equalsIgnoreCase(
						"factoryExclusive")){
			nightlyOutBoundDTO.setStyleIntroYear(nightlyOutBoundVO.getStyleFsIntroYear());
		}
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleIntroDate())) {
			nightlyOutBoundDTO.setStyleIntroDate(nightlyOutBoundVO.getStyleIntroDate());	
		}else if(!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFsIntroDate())
				&& nightlyOutBoundVO.getFactoryType().equalsIgnoreCase(
						"factoryExclusive")){
			nightlyOutBoundDTO.setStyleIntroDate(nightlyOutBoundVO.getStyleFsIntroDate());
		}
		
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFSDeleteDate()) 
				/*&& nightlyOutBoundVO.getFactoryType().equalsIgnoreCase(
				"factoryExclusive"*/
				){
			nightlyOutBoundDTO.setStyleDeleteYear(nightlyOutBoundVO.getStyleFsDeleteYear());
			nightlyOutBoundDTO.setStyleDeleteDate(nightlyOutBoundVO.getStyleFSDeleteDate());
		}/*else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleDeleteDate()) 
				&& !nightlyOutBoundVO.getFactoryType().equalsIgnoreCase(
				"factoryExclusive")) {
			nightlyOutBoundDTO.setStyleDeleteYear(nightlyOutBoundVO.getStyleDeleteYear());
			nightlyOutBoundDTO.setStyleDeleteDate(nightlyOutBoundVO.getStyleDeleteDate());
		} */
		
		
	}
	
	private void calculatePricingAttributes(NightlyOutBoundVO nightlyOutBoundVO, NightlyOutBoundDTO nightlyOutBoundDTO) {
		// Org Retail Cost
		
		nightlyOutBoundVO.setOrgRetailPrice("0.0");
		nightlyOutBoundVO.setTargetCost("0.0");
		nightlyOutBoundVO.setWhPrice("0.0");
		nightlyOutBoundVO.setTotalCost("0.0");
		
		Iterator iter = costMoaList.iterator();
		while (iter.hasNext()) {
			PRODMASTFullPriceMOAVO fullPriceMOAVO = (PRODMASTFullPriceMOAVO) iter.next();
				if(fullPriceMOAVO.getOwnerUniqueId().equalsIgnoreCase(nightlyOutBoundVO.getSkuUniqueId())){
/*					logger.info("nightlyOutBoundVO Sku uniqueId ==>"+nightlyOutBoundVO.getSkuUniqueId());
					logger.info("fullPriceMOAVO owner uniqueId ==>"+fullPriceMOAVO.getOwnerUniqueId());
*/					nightlyOutBoundVO.setOrgRetailPrice(fullPriceMOAVO.getRetailPrice());
					nightlyOutBoundVO.setTargetCost(fullPriceMOAVO.getTargetCost());
					nightlyOutBoundVO.setWhPrice(fullPriceMOAVO.getWhPrice());
					nightlyOutBoundVO.setTotalCost(fullPriceMOAVO.getTotalCost());
				}
			}	
		
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getProdRetailPrice())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getProdRetailPrice());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setOriginalRetailPrice(getDecimalRounded(scale,
					nightlyOutBoundVO.getProdRetailPrice().trim()));
			
		}else{
			nightlyOutBoundDTO.setOriginalRetailPrice("0.0");
		}
		
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getOrgRetailPrice())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getOrgRetailPrice());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setOrgRetailPrice(getDecimalRounded(scale,
					nightlyOutBoundVO.getOrgRetailPrice().trim()));
			
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getProdRetailPrice())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getProdRetailPrice());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setOrgRetailPrice(getDecimalRounded(scale,
					nightlyOutBoundVO.getProdRetailPrice().trim()));
			
		}else {
			nightlyOutBoundDTO.setOrgRetailPrice("0.0");
		}

		// Target Cost
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuTargetCost())) {
			
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getSkuTargetCost());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setTargetCost(getDecimalRounded(scale,
					nightlyOutBoundVO.getSkuTargetCost().trim()));

		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getProdTargetCost())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getProdTargetCost());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setTargetCost(getDecimalRounded(scale,
					nightlyOutBoundVO.getProdTargetCost().trim()));
		} else if(!isNullOrEmptyOrZero(nightlyOutBoundVO.getTargetCost())){ 
			
				int scale = 1;
				isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getTargetCost());
				if(isAboveZero)
					scale = 2;
				nightlyOutBoundDTO.setTargetCost(getDecimalRounded(scale,
						nightlyOutBoundVO.getTargetCost().trim()));
			
			} else 
		
		{
			nightlyOutBoundDTO.setTargetCost("0.0");
		}

		// WH Price
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getWhPrice())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getWhPrice());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setWhPrice(getDecimalRounded(scale,
					nightlyOutBoundVO.getWhPrice().trim()));
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getProdWhPrice())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getProdWhPrice());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setWhPrice(getDecimalRounded(scale,
					nightlyOutBoundVO.getProdWhPrice().trim()));
		} else{
			nightlyOutBoundDTO.setWhPrice("0.0");
		}

		// Avg Working Cost
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getAvgWorkingCost1())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getAvgWorkingCost1());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setAvgWorkingCost1(getDecimalRounded(scale,
					nightlyOutBoundVO.getAvgWorkingCost1().trim()));
		} else {
			nightlyOutBoundDTO.setAvgWorkingCost1("0.0");
		}

		// Sku Avg Working Cost
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuAvgWorkingCost1())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getSkuAvgWorkingCost1());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setSkuAvgWorkingCost1(getDecimalRounded(scale,
					nightlyOutBoundVO.getSkuAvgWorkingCost1().trim()));
		} else {
			nightlyOutBoundDTO.setSkuAvgWorkingCost1("0.0");
		}

		// Current Cost
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getCurrentCost())) {
			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getCurrentCost());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setCurrentCost(getDecimalRounded(scale,
					nightlyOutBoundVO.getCurrentCost().trim()));
		}else{
			nightlyOutBoundDTO.setCurrentCost("0.0");
		}

		// Final Std Cost
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getFinalStdCost())) {

			int scale = 1;
			isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getFinalStdCost());
			if(isAboveZero)
				scale = 2;
			nightlyOutBoundDTO.setFinalStdCost(getDecimalRounded(scale,
					nightlyOutBoundVO.getFinalStdCost().trim()));
		} else {
			nightlyOutBoundDTO.setFinalStdCost("0.0");
		}

		// Prod Std Cost
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getProdStdCost())
				&& Float.parseFloat(nightlyOutBoundVO.getProdStdCost()) > 0.0) {
			// if(nightlyOutBoundDTO){
			nightlyOutBoundDTO.setProdStdCost(getDecimalRounded(2,
					nightlyOutBoundVO.getProdStdCost().trim()));
		} else {
			nightlyOutBoundDTO.setProdStdCost("0.0");
		}
		
		
		// Total Cost
				/*if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getTotalCost())) {
					int scale = 1;
					isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getTotalCost());
					if(isAboveZero)
						scale = 2;
					nightlyOutBoundDTO.setTotalCost(getDecimalRounded(scale,nightlyOutBoundVO
							.getTotalCost().trim()));
				}else{
					nightlyOutBoundDTO.setTotalCost("");
				}*/
				
				// Sku Total Cost
				if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuTotalCost())) {
					int scale = 1;	
					isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getSkuTotalCost());
					if(isAboveZero)
						scale = 2;
					nightlyOutBoundDTO.setSkuTotalCost(getDecimalRounded(scale,
							nightlyOutBoundVO.getSkuTotalCost().trim()));
				} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getProdTotalCost())) {
					int scale = 1;	
					isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getProdTotalCost());
					if(isAboveZero)
						scale = 2;
					nightlyOutBoundDTO.setSkuTotalCost(getDecimalRounded(scale,
							nightlyOutBoundVO.getProdTotalCost().trim()));
				}else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getTotalCost())) {
					int scale = 1;
					isAboveZero = isValGreatedThanZero(nightlyOutBoundVO.getTotalCost());
					if(isAboveZero)
						scale = 2;
					nightlyOutBoundDTO.setSkuTotalCost(getDecimalRounded(scale,nightlyOutBoundVO
							.getTotalCost().trim()));
				}else{
					nightlyOutBoundDTO.setSkuTotalCost("");
				}

	}

	private boolean isValGreatedThanZero(String value)
			throws NumberFormatException {
		String tmp = null;
		boolean result = false;
		int isInt = 0;
		Double isDouble = 0.0;
		try {

			tmp = Integer.parseInt(value) + "";
			isInt = Integer.parseInt(tmp);
			if (isInt > 0) {
				result = true;
				return result;
			}

		} catch (Exception e) {

		}

		try {
			tmp = Double.parseDouble(value) + "";
			isDouble = Double.parseDouble(tmp);

			if (isDouble > 0) {
				result = true;
				return result;
			}

		} catch (Exception e) {

		}

		return result;

	}

	private void calculateSkuFsIntroDate(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception{
		
		String tmp=" ";
		
		/*if (nightlyOutBoundDTO.getSkuFsIntroYear() != null) {

			nightlyOutBoundDTO.setSkuFsIntroQuaterStr((nightlyOutBoundDTO
					.getSkuFsIntroYear().trim()).substring(0, 2)
					+ (nightlyOutBoundDTO.getSkuFsIntroYear().trim()).substring(
							3, 7));
			nightlyOutBoundDTO.setSkuFsIntroYearStr((nightlyOutBoundDTO
					.getSkuFsIntroYear().trim()).substring(3, 7));
		}  else {
			nightlyOutBoundDTO.setSkuFsIntroQuaterStr("No Intro Qtr");
			nightlyOutBoundDTO.setSkuFsIntroYearStr("No Intro Year");
		}*/
		
	
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuFsIntroDate())) {
			
			
			String fmtDateStr = null;
			//Fiscal date
			 if (nightlyOutBoundDTO.getSkuFsIntroDate().trim().indexOf(tmp) > -1)
				 fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuFsIntroDate().trim()).substring(0, nightlyOutBoundDTO.getSkuFsIntroDate().trim().indexOf(tmp)));
			 else
				 fmtDateStr = formatDate((nightlyOutBoundDTO
							.getSkuFsIntroDate().trim()).substring(0));	
			
			/*String fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuFsIntroDate().trim()).substring(0, nightlyOutBoundDTO
							.getSkuFsIntroDate().trim().indexOf(tmp)));
			*/
			 nightlyOutBoundDTO.setSkuFsIntroDateStr(fmtDateStr);

			// Quarter and Year
			String qtr = getQuarterFromSeasonGroup(nightlyOutBoundDTO.getSkuFsIntroDate());
			
			nightlyOutBoundDTO.setSkuFsIntroQuaterStr(qtr);
			
			// Fiscal Year
			if(!isNullOrEmpty(qtr))
			nightlyOutBoundDTO.setSkuFsIntroYearStr(qtr.trim().substring(2));
			
			//calendar date
			nightlyOutBoundDTO.setSkuFsIntroCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundDTO
							.getSkuFsIntroDate().trim()));
			

		}  else {
			nightlyOutBoundDTO.setSkuFsIntroDateStr("No Intro Date");
			nightlyOutBoundDTO.setSkuFsIntroQuaterStr("No Intro Qtr");
			nightlyOutBoundDTO.setSkuFsIntroYearStr("No Intro Year");
			nightlyOutBoundDTO.setSkuFsIntroCalenderDate(defaultDate);
		}
		
		
		
		/*if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuFsIntroDate())) {

		nightlyOutBoundDTO.setSkuFsIntroCalenderDate(ToToUtil
				.getIntroWithCalendarYear(nightlyOutBoundDTO
						.getSkuFsIntroDate().trim()));
	} else {
		nightlyOutBoundDTO.setSkuFsIntroCalenderDate(defaultDate);
	}*/
		
	}

	private void calculateSkuIntroDate(NightlyOutBoundVO nightlyOutBoundVO,NightlyOutBoundDTO nightlyOutBoundDTO) throws Exception {
		
		String tmp=" ";
		
		applyInheritanceForSku(nightlyOutBoundVO,nightlyOutBoundDTO);
		// Sku Intro date - calendar date
		/*logic for setSkuIntroCalenderDate - begins
		 * */
		if (!isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuIntroDate())) {
			
			// calender date
			nightlyOutBoundDTO.setSkuIntroCalenderDate(ToToUtil
					.getIntroWithCalendarYear(nightlyOutBoundDTO
							.getSkuIntroDate().trim()));
			
			// Quarter and Year
			String qtr = getQuarterFromSeasonGroup(nightlyOutBoundDTO.getSkuIntroDate());
			
			nightlyOutBoundDTO.setSkuIntroQuaterStr(qtr);
			
			// Fiscal Year
			if(!isNullOrEmpty(qtr))
			nightlyOutBoundDTO.setSkuIntroYearStr(qtr.trim().substring(2));
			
			String fmtDateStr = null;
			//Fiscal date
			 if (nightlyOutBoundDTO.getSkuIntroDate().trim().indexOf(tmp) > -1)
				 fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuIntroDate().trim()).substring(0, nightlyOutBoundDTO.getSkuIntroDate().trim().indexOf(tmp)));
			 else
				 fmtDateStr = formatDate((nightlyOutBoundDTO
							.getSkuIntroDate().trim()).substring(0));
			 
			nightlyOutBoundDTO.setSkuIntroDateStr(fmtDateStr);
			
		} else if("factoryExclusive".equalsIgnoreCase(nightlyOutBoundVO.getFactoryType())
				&& !isNullOrEmptyOrZero(nightlyOutBoundDTO.getSkuFsIntroDate())){
			
				nightlyOutBoundDTO.setSkuIntroCalenderDate(ToToUtil
						.getIntroWithCalendarYear(nightlyOutBoundDTO
								.getSkuFsIntroDate().trim()));
				
				String qtr = getQuarterFromSeasonGroup(nightlyOutBoundDTO.getSkuFsIntroDate());
				
				nightlyOutBoundDTO.setSkuIntroQuaterStr(qtr);
				
				if(!isNullOrEmpty(qtr))
				nightlyOutBoundDTO.setSkuIntroYearStr(qtr.trim().substring(2));
				
				String fmtDateStr = null;
				//Fiscal date
				 if (nightlyOutBoundDTO.getSkuFsIntroDate().trim().indexOf(tmp) > -1)
					 fmtDateStr = formatDate((nightlyOutBoundDTO
						.getSkuFsIntroDate().trim()).substring(0, nightlyOutBoundDTO.getSkuFsIntroDate().trim().indexOf(tmp)));
				 else
					 fmtDateStr = formatDate((nightlyOutBoundDTO
								.getSkuFsIntroDate().trim()).substring(0));
				 
				/*String fmtDateStr = formatDate((nightlyOutBoundDTO
						.getSkuFsIntroDate().trim()).substring(0, nightlyOutBoundDTO
								.getSkuFsIntroDate().trim().indexOf(tmp)));
				*/
				 nightlyOutBoundDTO.setSkuIntroDateStr(fmtDateStr);
				
			 
		}else{
			nightlyOutBoundDTO.setSkuIntroCalenderDate(defaultIntroDate);
			nightlyOutBoundDTO.setSkuIntroQuaterStr("No Intro Qtr");
			nightlyOutBoundDTO.setSkuIntroYearStr("No Intro Year");
			nightlyOutBoundDTO.setSkuIntroDateStr("No Intro Date");
		}
		
		
		/*logic for setSkuIntroCalenderDate - ends
		 * */
		
		
		if (!isNullOrEmpty(nightlyOutBoundDTO.getSkuIntroDate())) {
			
			
			/*String fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuIntroDate().trim()).substring(0, nightlyOutBoundDTO
							.getSkuIntroDate().trim().indexOf(tmp)));
			
			nightlyOutBoundDTO.setSkuIntroDateStr(fmtDateStr);*/

		} else if(!isNullOrEmpty(nightlyOutBoundDTO.getSkuFsIntroDate())
				&& nightlyOutBoundVO.getFactoryType().equalsIgnoreCase("factoryExclusive")){
		
			/*String fmtDateStr = formatDate((nightlyOutBoundDTO
					.getSkuFsIntroDate().trim()).substring(0, nightlyOutBoundDTO
							.getSkuFsIntroDate().trim().indexOf(tmp)));
			nightlyOutBoundDTO.setSkuIntroDateStr(fmtDateStr);*/
		
		}else if(isNullOrEmpty(nightlyOutBoundDTO.getSkuIntroDate())) {
			nightlyOutBoundDTO.setSkuIntroDateStr("No Intro Date");
		}
		
		

	}

	private void applyInheritanceForSku(NightlyOutBoundVO nightlyOutBoundVO,
			NightlyOutBoundDTO nightlyOutBoundDTO) {
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuIntroDate())) {

			nightlyOutBoundDTO.setSkuIntroDate(nightlyOutBoundVO.getSkuIntroDate());
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleIntroDate())) {
			nightlyOutBoundDTO.setSkuIntroDate(nightlyOutBoundVO.getStyleIntroDate());
		} 
		
		if (!isNullOrEmpty(nightlyOutBoundVO.getSkuIntroYear())) {
			nightlyOutBoundDTO.setSkuIntroYear(nightlyOutBoundVO.getSkuIntroYear());
		}else if (!isNullOrEmpty(nightlyOutBoundVO.getStyleIntroYear())) {
			nightlyOutBoundDTO.setSkuIntroYear(nightlyOutBoundVO.getStyleIntroYear());
		} 
		
		
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuFsIntroDate())) {

			nightlyOutBoundDTO.setSkuFsIntroDate(nightlyOutBoundVO.getSkuFsIntroDate());
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFsIntroDate())) {
			nightlyOutBoundDTO.setSkuFsIntroDate(nightlyOutBoundVO.getStyleFsIntroDate());
		} 
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuFsIntroYear())) {
			nightlyOutBoundDTO.setSkuFsIntroYear(nightlyOutBoundVO.getSkuFsIntroYear());
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFsIntroYear())) {
			nightlyOutBoundDTO.setSkuFsIntroYear(nightlyOutBoundVO.getStyleFsIntroYear());
		} 
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuDeleteYear())) {

			nightlyOutBoundDTO.setSkuDeleteYear(nightlyOutBoundVO
					.getSkuDeleteYear());
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleDeleteYear())) {

			nightlyOutBoundDTO.setSkuDeleteYear(nightlyOutBoundVO
					.getStyleDeleteYear());
		}
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuDeleteDate())) {

			nightlyOutBoundDTO.setSkuDeleteDate(nightlyOutBoundVO
					.getSkuDeleteDate());
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleDeleteDate())) {

			nightlyOutBoundDTO.setSkuDeleteDate(nightlyOutBoundVO
					.getStyleDeleteDate());
		}
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuFsDeleteYear())) {

			nightlyOutBoundDTO.setSkuFsDeleteYear(nightlyOutBoundVO
					.getSkuFsDeleteYear());
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFsDeleteYear())) {

			nightlyOutBoundDTO.setSkuFsDeleteYear(nightlyOutBoundVO
					.getStyleFsDeleteYear());
		}
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuFSDeleteDate())) {

			nightlyOutBoundDTO.setSkuFSDeleteDate(nightlyOutBoundVO
					.getSkuFSDeleteDate());
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFSDeleteDate())) {
	/*		if(!isNullOrEmpty(nightlyOutBoundVO.getFactoryType()) 
					&& nightlyOutBoundVO.getFactoryType().equalsIgnoreCase("factoryExclusive")){
	*/		nightlyOutBoundDTO.setSkuFSDeleteDate(nightlyOutBoundVO
					.getStyleFSDeleteDate());
	//		}
		}
		
		if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getSkuFsDeleteYear())) {

			nightlyOutBoundDTO.setSkuFsDeleteYear(nightlyOutBoundVO
					.getSkuFsDeleteYear());
		} else if (!isNullOrEmptyOrZero(nightlyOutBoundVO.getStyleFsDeleteYear())) {
			if(!isNullOrEmpty(nightlyOutBoundVO.getFactoryType()) 
					&& nightlyOutBoundVO.getFactoryType().equalsIgnoreCase("factoryExclusive")){
			nightlyOutBoundDTO.setSkuFsDeleteYear(nightlyOutBoundVO
					.getStyleFsDeleteYear());
			}
		}
		
		
		
		
		
	}

	/*private String getValidDate(String dateStr,
			String dateStrFs, NightlyOutBoundDTO nightlyOutBoundDTO,NightlyOutBoundVO nightlyOutBoundVO) {
		
		String tmpStr = null;
		if (!isNullOrEmptyOrZero(dateStr)) {
				tmpStr = dateStr.trim();
		}else if(!isNullOrEmptyOrZero(dateStrFs)){
			
		} 
		
		return tmpStr;
	}*/

	/*private boolean isNotZero(String valueStr) {

		if (!isNullOrEmpty(valueStr)) {
			if (valueStr.contains(".")) {
				float floatVal = Float.parseFloat(valueStr);
				if (floatVal == 0.0) {
					logger.info("Val is Zero");
					return false;
				}
			} else {
				int intValue = Integer.parseInt(valueStr);
				if (intValue == 0) {
					logger.info("Val is Zero");
					return false;
				}
			}
		}

		return true;

	}*/

	private String getDecimalRounded(int scale, String doubleVal) {
		BigDecimal valToRound = null;
		String cnvStr = "";
		if (!isNullOrEmpty(doubleVal)) {
			String tmp = doubleVal.trim();

			valToRound = new BigDecimal(tmp);
			valToRound = valToRound.setScale(scale, BigDecimal.ROUND_HALF_UP);
			cnvStr = valToRound.toString();
			int decPos = cnvStr.indexOf(".");
			if (decPos > 0 && scale == 2) {
				String isZero = cnvStr.charAt(decPos+2) + "";
				if (isZero.trim().equals("0")) {
					cnvStr = cnvStr.substring(0, decPos+2);
				}
				//System.out.println("cnvStr:" + cnvStr);
			}

		}
		return cnvStr;
	}

	private boolean isNullOrEmpty(String tmp) {

		if(tmp == null){
			//System.out.println("This is null");
			return true;
		}else if(tmp.trim().isEmpty()){
			//System.out.println("This is empty");
			return true;
		}
		
		return false;
	}
	private boolean isNullOrEmptyOrZero(String tmp) {

		if(tmp == null){
			//System.out.println("This is null");
			return true;
		}else if(tmp.trim().isEmpty()){
			//System.out.println("This is empty");
			return true;
		}else if(tmp.trim().equals("0")){
			//System.out.println("This is empty");
			return true;
		}else if(tmp.trim().equals("0.0")){
			//System.out.println("This is empty");
			return true;
		}
		
		return false;
	}

	private String formatDate(String dateStr) {
		// TODO Auto-generated method stub
		String dateString = dateStr.substring(0, dateStr.lastIndexOf("/"));
		String yearStr = dateStr.substring(dateStr.lastIndexOf("/")+1);
		String mon = dateStr.substring(0, dateStr.indexOf("/"));
		int dayBeginInd = dateStr.indexOf("/")+1; 
		String day = dateString.substring(dayBeginInd);
		if(day.length() > 0 && day.length() < 2){
			day = "0"+day;
		}
		if(mon.length() > 0 && mon.length() < 2){
			mon = "0"+mon;
		}
		StringBuffer tmpDateStr= new StringBuffer(); 
		tmpDateStr.append(mon.trim()).append("/").append(day.trim()).append("/").append(yearStr.trim());
		//String fmtDateStr= tmpDateStr.trim()+yearStr.trim();
		//System.out.println(tmpDateStr.toString());
		return tmpDateStr.toString();
	}

	private String padWithSpace(String value,int padding) {
		
		StringBuffer sb = new StringBuffer();
		int len = 0;
		if(padding >= value.length()){
			len = padding - value.length();
		}else{
			len = value.length();
		}
		sb.append(value);
		for(int i=0;i<len;i++)
		{
			sb.append(" ");
		}
		 
		return sb.toString();
		
	}

	public String attributemapper(String name, String value,String strDisplay) {
		boolean isvalueexist = false;
		StringBuffer sb = new StringBuffer();
		String attbStroVlaueKey = null;
		
		for (AttributeStoreVO attributeStoreVO : attributeStoreVOList) {
			if ((attributeStoreVO.getAttributeName()).trim().equalsIgnoreCase(name)) {
				
				attbStroVlaueKey = attributeStoreVO.getValueKey().indexOf(" ") > 0 ?  attributeStoreVO.getValueKey().trim() : attributeStoreVO.getValueKey();
				if("CASESHAPE".equals(name)){
					//System.out.println("GOt the watch sape "+ name +"value " + value);
					//System.out.println("GOt the watch attbStroVlaueKey "+ attbStroVlaueKey );
				}
				if (attbStroVlaueKey.equalsIgnoreCase(value)) { 
							
							if(strDisplay.equalsIgnoreCase("notNullDisplay")) {
								sb.append(attributeStoreVO.getDisplay2() != null ? attributeStoreVO
											.getDisplay2().trim() : attributeStoreVO
											.getDisplay());
							}else if(strDisplay.equalsIgnoreCase("display")) {
								sb.append(attributeStoreVO.getDisplay());
							}else if(strDisplay.equalsIgnoreCase("display2")) {
								sb.append(attributeStoreVO.getDisplay2());
							}else if(strDisplay.equalsIgnoreCase("display3")) {
								sb.append(attributeStoreVO.getDisplay3());
							}else if(strDisplay.equalsIgnoreCase("display4")) {
								sb.append(attributeStoreVO.getDisplay4());
							}else{
								sb.append(value);
							}		
					
					
					return sb.toString();
				}
			}
		}
		
		if (!isvalueexist) {
			sb = flushStringBuffer(sb);
			sb.append(value);
			return sb.toString();
		}
		
		if (sb.toString().equals("")) {
			return "";
		}

		return "";
	}

	private StringBuffer flushStringBuffer(StringBuffer sb) {
		if (sb.length() > 0) {
			sb.setLength(0);
		}

		return sb;
	}
	
	
	/*private String ConvertStringToCamelCase(String desc){
		
		if (desc != null && desc.length() > 0) {
			// Convert first character to upperCase and rest all charecter to
			// lowercase.
			desc = (desc.substring(0, 1).toUpperCase() + desc.substring(1)
					.toLowerCase()).trim();
			char value[] = desc.trim().toCharArray();
			int count = 1;
			while (count < value.length - 1) {
				if ((value[count] == ' ' || value[count] == '-' || value[count] == '/')) {
					value[count + 1] = Character.toUpperCase(value[count + 1]);
				} else if ((value[count] >= 0 && value[count] <= 9)
						&& (value[count + 1] > 'a' && value[count + 1] < 'z')
						&& (value[count + 1] != 'n' || value[count + 1] != 't'
								|| value[count + 1] != 's' || value[count + 1] != 'r')) {
					value[count + 1] = Character.toUpperCase(value[count + 1]);
				} else if ((value[count] == '.' || (value[count + 1] != ' ' || value[count + 1] != ','))
						&& ((value[count - 1] == 'm' && value[count - 2] == 'c') || (value[count - 1] == 'O')
								&& value[count] == '\'')) {
					value[count + 1] = Character.toUpperCase(value[count + 1]);
				}
				count = count + 1;
			}
			desc = new String(value);
			if (desc.indexOf("/") == 2) {
				desc = desc.substring(0, 2).toUpperCase() + desc.substring(2);
			}
			//System.out.println("Converted String ==>"+desc); 
		}else{
			desc="";
		}
        return desc;
	}
	
	*/
	public void getReadNightlyProp() {
		if(sizedDepartments != null || sizedDepartments != "")
		{
			String[] sizedDeptArray = sizedDepartments.split(",");
			sizedDeptList = Arrays.asList(sizedDeptArray); 
			/*for(int i=0; i<sizedDeptArray.length;i++){
				logger.info("These are sized dept===>"+sizedDeptArray[i]); 
			}*/
			
			
		}
		
		if(stylePrefixes != null || stylePrefixes != "")
		{
			
			String[] stylePrefixArray = stylePrefixes.split(",");
			stylePrefixList = Arrays.asList(stylePrefixArray);
			
			/*for(int i=0; i<stylePrefixArray.length;i++){
				logger.info("These are stylePrefixArray===>"+stylePrefixArray[i]); 
			}*/
			
			
		}
		
		//logger.info(" getDefaultDeleteCalDate===>"+defaultDate);
	}
	
	
	public void setSWDepartmentlist() {
		if(swDepartments != null && !swDepartments.isEmpty())
		{
			String[] swDeptArray = swDepartments.split(",");
			swDeptList = Arrays.asList(swDeptArray); 
		}			
	}
	
	/*public String getDefaultDeleteCalDate() {
		return defaultDeleteCalDate;
	}
	public void setDefaultDeleteCalDate(String defaultDeleteCalDate) {
		this.defaultDeleteCalDate = defaultDeleteCalDate;
	}*/
	public List getSizedDeptList() {
		return sizedDeptList;
	}
	public void setSizedDeptList(List sizedDeptList) {
		this.sizedDeptList = sizedDeptList;
	}
	

	public void setNightlyOutBoundDAO(NightlyOutBoundDAO nightlyOutBoundDAO) {
		this.nightlyOutBoundDAO = nightlyOutBoundDAO;
	}

	public NightlyOutBoundDTO getNightlyOutBoundDTO() {
		return nightlyOutBoundDTO;
	}

	public void setNightlyOutBoundDTO(NightlyOutBoundDTO nightlyOutBoundDTO) {
		this.nightlyOutBoundDTO = nightlyOutBoundDTO;
	}
	
	/*public Properties getNightlyOutboundProps() {
		return nightlyOutboundProps;
	}
	public void setNightlyOutboundProps(Properties nightlyOutboundProps) {
		this.nightlyOutboundProps = nightlyOutboundProps;
	}*/

	public Set getDepartmentRow() {
		return departmentRow;
	}

	public void setDepartmentRow(Set departmentRow) {
		this.departmentRow = departmentRow;
	}

	public Set getClassRow() {
		return classRow;
	}

	public void setClassRow(Set classRow) {
		this.classRow = classRow;
	}

	public Set getSubClassRow() {
		return subClassRow;
	}

	public void setSubClassRow(Set subClassRow) {
		this.subClassRow = subClassRow;
	}

	public Set getStyleRow() {
		return styleRow;
	}

	public void setStyleRow(Set styleRow) {
		this.styleRow = styleRow;
	}

	public Set getSkuRow() {
		return skuRow;
	}

	public void setSkuRow(Set skuRow) {
		this.skuRow = skuRow;
	}

	public Set<String> getSizeRow() {
		return sizeRow;
	}

	public void setSizeRow(Set<String> sizeRow) {
		this.sizeRow = sizeRow;
	}
	
	public List<PRODMASTFullPriceMOAVO> getCostMoaList() {
		return costMoaList;
	}



	public void setCostMoaList(List<PRODMASTFullPriceMOAVO> costMoaList) {
		this.costMoaList = costMoaList;
	}



public List<RpFtpAbcVO> getRpFtpAbcList() {
		return rpFtpAbcList;
	}



	public void setRpFtpAbcList(List<RpFtpAbcVO> rpFtpAbcList) {
		this.rpFtpAbcList = rpFtpAbcList;
	}



public  boolean passedOtherRules(NightlyOutBoundVO nightlyOutBoundVO ) {
	   String styleNumber = nightlyOutBoundVO.getStyleNum();
		String department = nightlyOutBoundVO.getDept();
		String colorCode = nightlyOutBoundVO.getSkuName();
		String sizeScale = nightlyOutBoundVO.getSizeScale();
		//logger.debug("checkRecordIsGoodOrBad(NightlyParentVO) - start");
		boolean status = true;
		// holds the reason for bad record
		String reason = "";
		// RULE 1A: Check if style number is blank
		if (isNullOrEmptyOrZero(styleNumber) || styleNumber.trim().length() == 0) {
			// record is bad
			status = false;
			// reason
		}
		if (status && styleNumber.length() > 6) {
			
			status = false;
		}

		if (status) {
			// Check if style number has only one integer value
			// convert string to char array
			char[] styleCharArray = styleNumber.toCharArray();
			// compute the length
			int styleNumberLength = styleCharArray.length;
			// holds the number of integer occurrence
			int intCount = 0;
			// check character by character for integer
			for (int i = 0; i < styleNumberLength; i++) {
				// if the character is within 0 and 9
				if ((int) styleCharArray[i] >= 48
						&& (int) styleCharArray[i] <= 57) {
					// increment the count
					++intCount;
				}
			}

			// if the integer count is l then it is a bad record
			if (intCount == 1) {
				// record is bad
				status = false;
			}
		}
		if (status
				&& styleNumber.length() >= 2
				&& "IR".equalsIgnoreCase(styleNumber.substring(0, 2))) {
			// record is bad
			status = false;
		}

		// Check for F and 5 Digit
		if (status
				&& styleNumber.trim().length() == 6
				&& "F".equalsIgnoreCase(styleNumber.substring(0, 1))
				&& isNumeric(styleNumber.trim().substring(1))) {
			// record is bad
			status = false;
		}

		// check for color code
		if (isNullOrEmptyOrZero(colorCode)) {
			// record is bad
			status = false;

		}

		// RULE 6: Check for size scale
		String SizeScaledDepartments = "";
		//SizeScaledDepartments = //<SizeDepartments from PropertyFile>
		boolean isSizeScaledDepartment = false;
		if(sizedDeptList.contains(nightlyOutBoundVO.getDept().trim()) ){
			
				isSizeScaledDepartment= true;
		}
		
				if (status && isSizeScaledDepartment) 
		{
			if (status && ("IA".equalsIgnoreCase(colorCode) == false && "DUM".equalsIgnoreCase(colorCode) == false && "HLD".equalsIgnoreCase(colorCode) == false)) 
			{
				if (isNullOrEmpty(sizeScale)) {
					status = false;
					isSizeScaledDepartment = false;
				}
			}
		} else if (status && "D07".equalsIgnoreCase(department)) {
			if ("OUT".equalsIgnoreCase(nightlyOutBoundVO.getDept().trim())
					|| "GLO".equalsIgnoreCase(nightlyOutBoundVO.getStyleClass().trim())) {
				if ("IA".equalsIgnoreCase(colorCode) == false
						&& "DUM".equalsIgnoreCase(colorCode) == false) {
					if ("C771".equalsIgnoreCase(styleNumber) == false) {
						if (isNullOrEmptyOrZero(nightlyOutBoundVO.getSizeScale())) {
							// record is bad
							status = false;
							isSizeScaledDepartment = false;
							// reason
							reason = "RULE 6 Failed. Size scale is empty for style " + nightlyOutBoundVO.getStyleNum();
						}
					}
				}
			}
		}

		return status;
	}

public String convertCharUCaseWithSpace(String stringValue) {

	if (!isNullOrEmpty(stringValue)) {

		String st[] = stringValue.trim().split(" ");

		StringBuffer sb = new StringBuffer(50);

		String token = "";

		int count = st.length;

		for (int i = 0; i < count; i++) {

			token = st[i].toString();
			if (token.length() == 0) {
				sb.append(" ");
			} else {
				sb.append(token.substring(0, 1).toUpperCase()).append(
						token.substring(1).toLowerCase()).append(
						" ");
			}
		}
		String returnString = sb.toString();

		// remove the last space
		if (returnString.length() > 0) {
			// remove the last character
			returnString = returnString.substring(0,
					returnString.length() - 1);
		}

		return returnString;
	} else {

		return "";
	}
}


public static boolean isNumeric(String value) {
	try {
		// convert it to a number
		Integer.parseInt(value);
	} catch (NumberFormatException exception) {
		return false;
	}
	// return true if it is a number

	return true;
}


/**
 * replacing string with a given string
 * 
 * @param data
 * @param find
 * @param replace
 * @return returns modified string
 */
public String cleanCode(String data, String find, String replace) {

	if (!isNullOrEmpty(data)) {
		if (".".equals(find)) {
			find = "[" + find + "]+";
		}
		data = data.replaceAll(find, replace);
	}

	return data;
}

/**
 * rightTrim - Trims the string from right
 * 
 * @param value
 * @return
 */
public String rightTrim(String value) {
	// validate
	if (isNullOrEmpty(value)) {
		return "";
	}

	// compute the length
	int valueArrayCount = value.length();
	int i = 0;

	// iterate the array
	for (i = valueArrayCount; i > 0; i--) {
		// check for non empty string
		if ((value.substring(i - 1, i)).equals(" ") == true) {
			value = value.substring(0, i - 1);
			// exit the for loop
			// break;
		} else {
			// no space available
			break;
		}
	}

	// remove the blanks from the end
	/*
	 * if (i > 0) { value = value.substring(0, i); }
	 */

	// return the value
	/*
	 * if (true) { logger.debug("rightTrim(String) -
	 * end"); }
	 */
	return value;
}

public String getQuarterFromSeasonGroup(String seasongroupName){
    
    String quarter = null;
    String [] introArray = seasongroupName.split("/");
    String fiscalYear = introArray[2].substring(0, 4);
    int  month = Integer.parseInt(introArray[0]);
    if(month == 7 || month ==8 || month == 9)
           return "Q1"+fiscalYear;
    else if(month == 10 || month ==11)
           return "Q2"+fiscalYear;
    else if(month == 12){
           int day = Integer.parseInt(introArray[1]);
           if(day == 26)
                  return "Q3"+fiscalYear;
           else 
                  return "Q2"+fiscalYear;
    }else if(month == 1 || month ==2 || month == 3)
           return "Q3"+fiscalYear;
    else if(month == 4 || month ==5 || month == 6)
           return "Q4"+fiscalYear;
    return seasongroupName;
}

/**
 * fn_case(name) sql function implementation.
 * 
 * @param desc
 * @return
 */
public static String applyRuleUCase(String desc) {
	if (desc != null && desc.length() > 0) {
		// Convert first character to upperCase and rest all charecter to
		// lowercase.
		desc = (desc.substring(0, 1).toUpperCase() + desc.substring(1)
				.toLowerCase()).trim();
		char value[] = desc.trim().toCharArray();
		int count = 1;
		while (count < value.length - 1) {
			if ((value[count] == ' ' || value[count] == '-' || value[count] == '/')) {
				value[count + 1] = Character.toUpperCase(value[count + 1]);
			} else if ((value[count] >= 0 && value[count] <= 9)
					&& (value[count + 1] > 'a' && value[count + 1] < 'z')
					&& (value[count + 1] != 'n' || value[count + 1] != 't'
							|| value[count + 1] != 's' || value[count + 1] != 'r')) {
				value[count + 1] = Character.toUpperCase(value[count + 1]);
			} else if ((value[count] == '.' || (value[count + 1] != ' ' || value[count + 1] != ','))
					&& ((value[count - 1] == 'm' && value[count - 2] == 'c') || (value[count - 1] == 'O')
							&& value[count] == '\'')) {
				value[count + 1] = Character.toUpperCase(value[count + 1]);
			}
			count = count + 1;
		}
		desc = new String(value);
		if (desc.indexOf("/") == 2) {
			desc = desc.substring(0, 2).toUpperCase() + desc.substring(2);
		}
		return desc;
	}
	return desc;
}



public boolean isNextChunk() {
	return nextChunk;
}



public void setNextChunk(boolean nextChunk) {
	this.nextChunk = nextChunk;
}



public String getDefaultDate() {
	return defaultDate;
}



public void setDefaultDate(String defaultDate) {
	this.defaultDate = defaultDate;
}



public String getDefaultIntroDate() {
	return defaultIntroDate;
}



public void setDefaultIntroDate(String defaultIntroDate) {
	this.defaultIntroDate = defaultIntroDate;
}



public String getSizedDepartments() {
	return sizedDepartments;
}



public void setSizedDepartments(String sizedDepartments) {
	this.sizedDepartments = sizedDepartments;
}





/**
 * @return the swDepartments
 */
public String getSwDepartments() {
	return swDepartments;
}



/**
 * @param swDepartments the swDepartments to set
 */
public void setSwDepartments(String swDepartments) {
	System.out.println("* Setting SW departments*******" + swDepartments);
	this.swDepartments = swDepartments;
}



public String getStylePrefixes() {
	return stylePrefixes;
}



public void setStylePrefixes(String stylePrefixes) {
	this.stylePrefixes = stylePrefixes;
}



public String getIgnoreStyleSuffix() {
	return ignoreStyleSuffix;
}



public void setIgnoreStyleSuffix(String ignoreStyleSuffix) {
	this.ignoreStyleSuffix = ignoreStyleSuffix;
}



public String getSkippedRecordsPath() {
	return skippedRecordsPath;
}



public void setSkippedRecordsPath(String skippedRecordsPath) {
	this.skippedRecordsPath = skippedRecordsPath;
}



public String getSkippedRecordsFileName() {
	return skippedRecordsFileName;
}



public void setSkippedRecordsFileName(String skippedRecordsFileName) {
	this.skippedRecordsFileName = skippedRecordsFileName;
}



public int getSizeLength() {
	return sizeLength;
}



public void setSizeLength(int sizeLength) {
	this.sizeLength = sizeLength;
}

}
