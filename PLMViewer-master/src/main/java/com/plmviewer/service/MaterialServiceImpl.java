/**
 * 
 */
package com.plmviewer.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.plmviewer.dao.MaterialDao;
import com.plmviewer.model.MaterialColorDetail;
import com.plmviewer.model.MaterialColorSearchResultVo;
import com.plmviewer.model.MaterialPricingSearchResult;
import com.plmviewer.model.MaterialReportVo;
import com.plmviewer.model.MaterialResultVo;
import com.plmviewer.model.MaterialSearchVo;
import com.plmviewer.model.MaterialSupplierVo;
import com.plmviewer.model.MaterialVo;
import com.plmviewer.model.User;
import com.plmviewer.util.PropertyReaderUtil;



/**
 * @author uthanasekarapandian
 *
 */
@Service
public class MaterialServiceImpl implements MaterialService {
	private static final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);
	
	@Autowired
	MaterialDao materialDao;
	public List<MaterialResultVo> getMaterialList (MaterialSearchVo materialSearchVo) throws Exception{
		logger.info("getMaterialList Starts");
		List<MaterialResultVo>  materialResultList = materialDao.getMaterialList(materialSearchVo);
		
		logger.info("getMaterialList Ends");
		return materialResultList;
	}
	public MaterialVo getMaterialData(String searchkey,String searchtype , String materialType) throws Exception{
		MaterialVo materialVo = materialDao.getMaterialVo(searchkey,searchtype, materialType);
		try{
		logger.info("getMaterialList Starts");
		if(materialVo.getDevIntros()!=null && !materialVo.getDevIntros().equals(""))
		materialVo.setDevIntros(splitMaterialData(materialVo.getDevIntros(),"MOALIST","ASSOCIATEDINTROS"));
		if(materialVo.getCmParentGroup()!=null && !materialVo.getCmParentGroup().equals(""))
        materialVo.setCmParentGroup(splitMaterialData(materialVo.getCmParentGroup(),"MOALIST","CMPARENTGROUP"));
		if(materialVo.getDevelopmentTeams()!=null && !materialVo.getDevelopmentTeams().equals(""))
        materialVo.setDevelopmentTeams(splitMaterialData(materialVo.getDevelopmentTeams(),"MOALIST","DEVELOPMENTTEAMS"));
		if(materialVo.getGcc()!=null && !materialVo.getGcc().equals(""))
        materialVo.setGcc(splitMaterialData(materialVo.getGcc(),"MOALIST","GCCREQUIRED"));
		if(materialVo.getGender()!=null && !materialVo.getGender().equals(""))
        materialVo.setGender(splitMaterialData(materialVo.getGender(),"MOALIST","GENDER"));
		if(materialVo.getLimitations()!=null && !materialVo.getLimitations().equals(""))
        materialVo.setLimitations(splitMaterialData(materialVo.getLimitations(),"MOALIST","LIMITATIONS"));
		if(materialVo.getFabricContentAllSupp()!=null && !materialVo.getFabricContentAllSupp().equals(""))
        materialVo.setFabricContentAllSupp(splitMaterialData(materialVo.getFabricContentAllSupp(),"COMPOSITE","FABRICCONTENT"));
		if(materialVo.getBackingContentAllSupp()!=null && !materialVo.getBackingContentAllSupp().equals(""))
        materialVo.setBackingContentAllSupp(splitMaterialData(materialVo.getBackingContentAllSupp(),"COMPOSITE","FABRICCONTENT"));
		if(materialVo.getContentWithBackingAllSupp()!=null && !materialVo.getContentWithBackingAllSupp().equals(""))
        materialVo.setContentWithBackingAllSupp(splitMaterialData(materialVo.getContentWithBackingAllSupp(),"COMPOSITE","FABRICCONTENT"));
		if(materialVo.getCoreContentAllSupp()!=null && !materialVo.getCoreContentAllSupp().equals(""))
        materialVo.setCoreContentAllSupp(splitMaterialData(materialVo.getCoreContentAllSupp(),"COMPOSITE","FABRICCONTENT"));
		if(materialVo.getTotalContentAllSupp()!=null && !materialVo.getTotalContentAllSupp().equals(""))
        materialVo.setTotalContentAllSupp(splitMaterialData(materialVo.getTotalContentAllSupp(),"COMPOSITE","FABRICCONTENT"));
		if(materialVo.getStdCostCurrency()!=null && !materialVo.getStdCostCurrency().equals(""))
        materialVo.setStdCostCurrency(splitMaterialData(materialVo.getStdCostCurrency(),"","CURRENCY"));
		if(materialVo.getBrand()!=null && !materialVo.getBrand().equals(""))
        materialVo.setBrand(splitMaterialData(materialVo.getBrand().toUpperCase(),"","BRAND"));
		if(materialVo.getStdCostFyAllSupp()!=null && !materialVo.getStdCostFyAllSupp().equals(""))
        materialVo.setStdCostFyAllSupp(splitMaterialData(materialVo.getStdCostFyAllSupp(),"","STDCOSTFY"));
		if(materialVo.getSubType()!=null && !materialVo.getSubType().equals(""))
        materialVo.setSubType(materialDao.getMatAttributeData("MATERIALSUBTYPE",materialVo.getSubType().toLowerCase(),materialType));
		materialVo.setVariant(materialDao.getMatAttributeData("VARIANT",materialVo.getVariant(),materialType));
		
		if(materialVo.getMcg()!=null && !materialVo.getMcg().equals(""))
			materialVo.setMcg(splitMaterialData(materialVo.getMcg().toUpperCase(),"","MCG"));
			
		
		if(materialVo.getLeatherEndUse()!=null && !materialVo.getLeatherEndUse().equals(""))
			materialVo.setLeatherEndUse(splitMaterialData(materialVo.getLeatherEndUse().toUpperCase(),"","LEATHERENDUSE"));
			
		if(materialVo.getGrainTypeAllSupp()!=null && !materialVo.getGrainTypeAllSupp().equals(""))
			materialVo.setGrainTypeAllSupp(splitMaterialData(materialVo.getGrainTypeAllSupp().toUpperCase(),"","GRAINTYPE"));
	
		if(materialVo.getHideSegmentation()!=null && !materialVo.getHideSegmentation().equals(""))
			materialVo.setHideSegmentation(splitMaterialData(materialVo.getHideSegmentation().toUpperCase(),"","HIDESEGMENTATION"));
	
		
		if(materialVo.getCommonName()!=null && !materialVo.getCommonName().equals(""))
			materialVo.setCommonName(splitMaterialData(materialVo.getCommonName().toUpperCase(),"","COMMONNAMESPECIES"));
	
		
		if(materialVo.getSurfaceFinishAllSupp()!=null && !materialVo.getSurfaceFinishAllSupp().equals(""))
			materialVo.setSurfaceFinishAllSupp(splitMaterialData(materialVo.getSurfaceFinishAllSupp().toUpperCase(),"","SURFACEFINISH"));
	
		
		if(materialVo.getContentWithBackingAllSupp()!=null && !materialVo.getContentWithBackingAllSupp().equals(""))
			materialVo.setContentWithBackingAllSupp(splitMaterialData(materialVo.getContentWithBackingAllSupp().toUpperCase(),"COMPOSITE","CONTENT"));
	
		
		/*if(materialVo.getVariant()!=null && !materialVo.getVariant().equals(""))
			materialVo.setVariant(splitMaterialData(materialVo.getVariant().toUpperCase(),"","VARIANT"));
	*/
		//yes or no values
		
		if(materialVo.getFancy()!=null && !materialVo.getFancy().equals(""))
			materialVo.setFancy(splitMaterialData(materialVo.getFancy(),"","FANCY"));
		
	
		if(materialVo.getActiveMaterial()!=null && !materialVo.getActiveMaterial().equals(""))
			materialVo.setActiveMaterial(splitMaterialData(materialVo.getActiveMaterial(),"","ACTIVEMATERIAL"));
		
		if(materialVo.getMaterialType()!=null && !materialVo.getMaterialType().equals(""))
			materialVo.setMaterialType(splitMaterialData(materialVo.getMaterialType(),"","MATERIALTYPE"));
		
		if(materialVo.getMaterialContentAllSupp()!=null && !materialVo.getMaterialContentAllSupp().equals(""))
			materialVo.setMaterialContentAllSupp(splitMaterialData(materialVo.getMaterialContentAllSupp(),"","MATERIALCONTENT"));
		
		if(materialVo.getContent()!=null && !materialVo.getContent().equals(""))
			materialVo.setContent(splitMaterialData(materialVo.getContent(),"","CONTENT"));
		
		if(materialVo.getAssignee()!=null && !materialVo.getAssignee().equals(""))
			materialVo.setAssignee(splitMaterialData(materialVo.getAssignee(),"","ASSIGNEE"));
		
		if(materialVo.getBaseFabricName()!=null && !materialVo.getBaseFabricName().equals(""))
			materialVo.setBaseFabricName(splitMaterialData(materialVo.getBaseFabricName(),"","BASEFABRICNAME"));
		
		if(materialVo.getBoxType()!=null && !materialVo.getBoxType().equals(""))
			materialVo.setBoxType(splitMaterialData(materialVo.getBoxType(),"","BOXTYPE"));
		
		if(materialVo.getBuckleType()!=null && !materialVo.getBuckleType().equals(""))
			materialVo.setBuckleType(splitMaterialData(materialVo.getBuckleType(),"","BUCKLETYPE"));
		
		if(materialVo.getBulkTestRegime()!=null && !materialVo.getBulkTestRegime().equals(""))
			materialVo.setBulkTestRegime(splitMaterialData(materialVo.getBulkTestRegime(),"","BULKTESTREGIME"));
		
		
		if(materialVo.getChainType()!=null && !materialVo.getChainType().equals(""))
			materialVo.setChainType(splitMaterialData(materialVo.getChainType(),"","CHAINTYPE"));
		
		if(materialVo.getCottonType()!=null && !materialVo.getCottonType().equals(""))
			materialVo.setCottonType(splitMaterialData(materialVo.getCottonType(),"","COTTONTYPE"));
		
		if(materialVo.getDap()!=null && !materialVo.getDap().equals(""))
			materialVo.setDap(splitMaterialData(materialVo.getDap(),"","DAPBUCKLE"));
		
		if(materialVo.getFiberTypeAllSupp()!=null && !materialVo.getFiberTypeAllSupp().equals(""))
			materialVo.setFiberTypeAllSupp(splitMaterialData(materialVo.getFiberTypeAllSupp(),"","FIBERTYPE"));
		
		if(materialVo.getFinishedGoodsCOO()!=null && !materialVo.getFinishedGoodsCOO().equals(""))
			materialVo.setFinishedGoodsCOO(splitMaterialData(materialVo.getFinishedGoodsCOO(),"","FINISHEGOODSCOOLABEL"));
		
		if(materialVo.getPatentFinish()!=null && !materialVo.getPatentFinish().equals(""))
			materialVo.setPatentFinish(splitMaterialData(materialVo.getPatentFinish(),"","FINISH"));
		
		if(materialVo.getInkColor()!=null && !materialVo.getInkColor().equals(""))
			materialVo.setInkColor(splitMaterialData(materialVo.getInkColor(),"","INKCOLOR"));
		
		if(materialVo.getInsertType()!=null && !materialVo.getInsertType().equals(""))
			materialVo.setInsertType(splitMaterialData(materialVo.getInsertType(),"","PAPERINSERTSONLY"));
		
		if(materialVo.getInsertType2()!=null && !materialVo.getInsertType2().equals(""))
			materialVo.setInsertType2(splitMaterialData(materialVo.getInsertType2(),"","INSERT2TIMEMGMT"));
		
		if(materialVo.getKnitPileConstruction()!=null && !materialVo.getKnitPileConstruction().equals(""))
			materialVo.setKnitPileConstruction(splitMaterialData(materialVo.getKnitPileConstruction(),"","KNITPILECONSTRUCTION"));
		
		if(materialVo.getKnitType()!=null && !materialVo.getKnitType().equals(""))
			materialVo.setKnitType(splitMaterialData(materialVo.getKnitType(),"","KNITTYPE"));
		
		if(materialVo.getLabelSize()!=null && !materialVo.getLabelSize().equals(""))
			materialVo.setLabelSize(splitMaterialData(materialVo.getLabelSize(),"","LABELSIZE"));
		
		if(materialVo.getLabelType()!=null && !materialVo.getLabelType().equals(""))
			materialVo.setLabelType(splitMaterialData(materialVo.getLabelType(),"","LABELTYPE"));
		
		
		if(materialVo.getLiningWeightAllSupp()!=null && !materialVo.getLiningWeightAllSupp().equals(""))
			materialVo.setLiningWeightAllSupp(splitMaterialData(materialVo.getLiningWeightAllSupp(),"","LININGWEIGHTALLSUPP"));
		
		if(materialVo.getLogoType()!=null && !materialVo.getLogoType().equals(""))
			materialVo.setLogoType(splitMaterialData(materialVo.getLogoType(),"","LOGOTYPE"));
		
		if(materialVo.getOpeningSide()!=null && !materialVo.getOpeningSide().equals(""))
			materialVo.setOpeningSide(splitMaterialData(materialVo.getOpeningSide(),"","OPENINGSIDE"));
		
		if(materialVo.getProngMfgMethod()!=null && !materialVo.getProngMfgMethod().equals(""))
			materialVo.setProngMfgMethod(splitMaterialData(materialVo.getProngMfgMethod(),"","PRONGMFGMETHOD"));
		
		if(materialVo.getTapeContentAllSupp()!=null && !materialVo.getTapeContentAllSupp().equals(""))
			materialVo.setTapeContentAllSupp(splitMaterialData(materialVo.getTapeContentAllSupp(),"","TAPECONTENT"));
		
		if(materialVo.getTeethContentAllSupp()!=null && !materialVo.getTeethContentAllSupp().equals(""))
			materialVo.setTeethContentAllSupp(splitMaterialData(materialVo.getTeethContentAllSupp(),"","TEETHCONTENT"));
		
		if(materialVo.getWoolType()!=null && !materialVo.getWoolType().equals(""))
			materialVo.setWoolType(splitMaterialData(materialVo.getWoolType(),"","YARNTYPE"));
		
		if(materialVo.getWearablesLeatherType()!=null && !materialVo.getWearablesLeatherType().equals(""))
			materialVo.setWearablesLeatherType(splitMaterialData(materialVo.getWearablesLeatherType(),"","WEARABLESLEATHERTYPE"));
		
		if(materialVo.getTypeOfWeaveAllSupp()!=null && !materialVo.getTypeOfWeaveAllSupp().equals(""))
			materialVo.setTypeOfWeaveAllSupp(splitMaterialData(materialVo.getTypeOfWeaveAllSupp(),"","TYPEOFWEAVE"));
		
		if(materialVo.getSizeDesignation()!=null && !materialVo.getSizeDesignation().equals(""))
			materialVo.setSizeDesignation(splitMaterialData(materialVo.getSizeDesignation(),"","SIZEDESIGNATION"));
		
		if(materialVo.getSided()!=null && !materialVo.getSided().equals(""))
			materialVo.setSided(splitMaterialData(materialVo.getSided(),"","SIDED"));
		
		if(materialVo.getStitchingPurpose()!=null && !materialVo.getStitchingPurpose().equals(""))
			materialVo.setStitchingPurpose(splitMaterialData(materialVo.getStitchingPurpose(),"","SEWINGPURPOSE"));
		
		if(materialVo.getMaterialTestRegime()!=null && !materialVo.getMaterialTestRegime().equals(""))
			materialVo.setMaterialTestRegime(splitMaterialData(materialVo.getMaterialTestRegime(),"","SAMPLETESTREGIME"));
		
		if(materialVo.getRingType()!=null && !materialVo.getRingType().equals(""))
			materialVo.setRingType(splitMaterialData(materialVo.getRingType(),"","RINGTYPE"));
		
		if(materialVo.getRivetType()!=null && !materialVo.getRivetType().equals(""))
			materialVo.setRivetType(splitMaterialData(materialVo.getRivetType(),"","RIVETTYPE"));
		
		if(materialVo.getPackagingCategory()!=null && !materialVo.getPackagingCategory().equals(""))
			materialVo.setPackagingCategory(splitMaterialData(materialVo.getPackagingCategory(),"","PACKAGINGCATEGORY"));
		
		if(materialVo.getColoringMethodAllSupp()!=null && !materialVo.getColoringMethodAllSupp().equals(""))
			materialVo.setColoringMethodAllSupp(splitMaterialData(materialVo.getColoringMethodAllSupp(),"","COLORINGMETHOD"));

		if(materialVo.getLabelConstruction()!=null && !materialVo.getLabelConstruction().equals(""))
			materialVo.setLabelConstruction(splitMaterialData(materialVo.getLabelConstruction(),"","LABELCONSTRUCTION"));
		
		if(materialVo.getChannelUse()!=null && !materialVo.getChannelUse().equals(""))
			materialVo.setChannelUse(splitMaterialData(materialVo.getChannelUse(),"","CHANNELUSE"));

		
		if(materialVo.getTexture()!=null && !materialVo.getTexture().equals(""))
			materialVo.setTexture(splitMaterialData(materialVo.getTexture(),"","TEXTURE"));

		//true or false
		if(materialVo.getMaterialUsedByWearables()!=null && !materialVo.getSubType().equals("")){
			if(materialVo.getMaterialUsedByWearables().equalsIgnoreCase("true"))
	        materialVo.setMaterialUsedByWearables("Yes");
			else
				materialVo.setMaterialUsedByWearables("No");
	        }
		if(materialVo.getAutoCreateMultiColor()!=null && !materialVo.getAutoCreateMultiColor().equals("")){
			if(materialVo.getAutoCreateMultiColor().equalsIgnoreCase("true"))
	        materialVo.setAutoCreateMultiColor("Yes");
			else
				materialVo.setAutoCreateMultiColor("No");
	        }
		if(materialVo.getStdCostEstimateFlagAllSupp()!=null && !materialVo.getStdCostEstimateFlagAllSupp().equals("")){
			if(materialVo.getStdCostEstimateFlagAllSupp().equalsIgnoreCase("true"))
	        materialVo.setStdCostEstimateFlagAllSupp("Yes");
			else
				materialVo.setStdCostEstimateFlagAllSupp("No");
	        }
		
		if(materialVo.getLongLeadTime()!=null && !materialVo.getLongLeadTime().equals("")){
			if(materialVo.getLongLeadTime().equalsIgnoreCase("true"))
	        materialVo.setLongLeadTime("Yes");
			else
				materialVo.setLongLeadTime("No");
	        }
		if(materialVo.getResolveConflictFlag()!=null && !materialVo.getResolveConflictFlag().equals("")){
			if(materialVo.getResolveConflictFlag().equalsIgnoreCase("true"))
	        materialVo.setResolveConflictFlag("Yes");
			else
				materialVo.setResolveConflictFlag("No");
	        }
		if(materialVo.getLubricated()!=null && !materialVo.getLubricated().equals("")){
			if(materialVo.getLubricated().equalsIgnoreCase("true"))
	        materialVo.setLubricated("Yes");
			else
				materialVo.setLubricated("No");
	        }
		
		if(materialVo.getConflictFlag()!=null && !materialVo.getConflictFlag().equals("")){
			if(materialVo.getConflictFlag().equalsIgnoreCase("true"))
	        materialVo.setConflictFlag("Yes");
			else
				materialVo.setConflictFlag("No");
	        }
		
		if(materialVo.getSeparator()!=null && !materialVo.getSeparator().equals("")){
			if(materialVo.getSeparator().equalsIgnoreCase("true"))
	        materialVo.setSeparator("Yes");
			else
				materialVo.setSeparator("No");
	        }
		
		if(materialVo.getFsBullet()!=null && !materialVo.getFsBullet().equals("")){
			if(materialVo.getFsBullet().equalsIgnoreCase("true"))
	        materialVo.setFsBullet("Yes");
			else
				materialVo.setFsBullet("No");
	        }
		
		if(materialVo.getMaterialUsedByWearables()!=null && !materialVo.getMaterialUsedByWearables().equals("")){
			if(materialVo.getMaterialUsedByWearables().equalsIgnoreCase("true"))
	        materialVo.setMaterialUsedByWearables("Yes");
			else
				materialVo.setMaterialUsedByWearables("No");
	        }
		/*	if(materialVo.getStitchingPurpose()!=null && !materialVo.getStitchingPurpose().equals(""))
			materialVo.setStitchingPurpose(splitMaterialData(materialVo.getStitchingPurpose(),"",""));
		
		if(materialVo.getAutoCreateMultiColor()!=null && !materialVo.getAutoCreateMultiColor().equals(""))
			materialVo.setAutoCreateMultiColor(splitMaterialData(materialVo.getAutoCreateMultiColor(),"",""));
		
	
		if(materialVo.getMaterialUsedByWearables()!=null && !materialVo.getMaterialUsedByWearables().equals(""))
			materialVo.setMaterialUsedByWearables(splitMaterialData(materialVo.getMaterialUsedByWearables(),"",""));
		
		if(materialVo.getStdCostEstimateFlagAllSupp()!=null && !materialVo.getStdCostEstimateFlagAllSupp().equals(""))
			materialVo.setStdCostEstimateFlagAllSupp(splitMaterialData(materialVo.getStdCostEstimateFlagAllSupp(),"",""));
		
		if(materialVo.getLongLeadTime()!=null && !materialVo.getLongLeadTime().equals(""))
			materialVo.setLongLeadTime(splitMaterialData(materialVo.getLongLeadTime(),"",""));
		
		if(materialVo.getResolveConflictFlag()!=null && !materialVo.getResolveConflictFlag().equals(""))
			materialVo.setResolveConflictFlag(splitMaterialData(materialVo.getResolveConflictFlag(),"",""));
		
		if(materialVo.getConflictFlag()!=null && !materialVo.getConflictFlag().equals(""))
			materialVo.setConflictFlag(splitMaterialData(materialVo.getConflictFlag(),"",""));*/
		
        materialVo.setMaterialSupplier(materialDao.getMaterialSupplierList(materialVo.getIda3masterreference()));
        
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getMaterialList Ends");
		return materialVo;
		
	}
	public MaterialSupplierVo getMaterialSupplierVo(BigDecimal ida3MasterRef,String materialType) throws Exception{
		logger.info("getMaterialList Starts");
		
		MaterialSupplierVo materialSupplierVo = materialDao.getMaterialSupplierVo(ida3MasterRef,materialType);
		try{
		if(materialSupplierVo.getMetalContent()!=null && !materialSupplierVo.getMetalContent().equals(""))
		materialSupplierVo.setMetalContent(splitMaterialData(materialSupplierVo.getMetalContent(),"COMPOSITE","METALCONTENT"));
		if(materialSupplierVo.getMaterialcontentms()!=null && !materialSupplierVo.getMaterialcontentms().equals(""))
		materialSupplierVo.setMaterialcontentms(splitMaterialData(materialSupplierVo.getMaterialcontentms(),"COMPOSITE","MATERIALCONTENT"));
		if(materialSupplierVo.getTapeContent()!=null && !materialSupplierVo.getTapeContent().equals(""))
		materialSupplierVo.setTapeContent(splitMaterialData(materialSupplierVo.getTapeContent(),"COMPOSITE","TAPECONTENT"));
		if(materialSupplierVo.getTeethContent()!=null && !materialSupplierVo.getTeethContent().equals(""))
		materialSupplierVo.setTeethContent(splitMaterialData(materialSupplierVo.getTeethContent(),"COMPOSITE","TEETHCONTENT"));
		if(materialSupplierVo.getFabricContent()!=null && !materialSupplierVo.getFabricContent().equals(""))
	    materialSupplierVo.setFabricContent(splitMaterialData(materialSupplierVo.getFabricContent(),"COMPOSITE","FABRICCONTENT"));
		if(materialSupplierVo.getSupplierBasisUOM()!=null && !materialSupplierVo.getSupplierBasisUOM().equals(""))
		materialSupplierVo.setSupplierBasisUOM(splitMaterialData(materialSupplierVo.getSupplierBasisUOM(),"","UOM"));
		if(materialSupplierVo.getUnitOfMeasureSupp()!=null && !materialSupplierVo.getUnitOfMeasureSupp().equals(""))
		materialSupplierVo.setUnitOfMeasureSupp(splitMaterialData(materialSupplierVo.getUnitOfMeasureSupp(),"","UOM"));
		if(materialSupplierVo.getShippingTerms()!=null && !materialSupplierVo.getShippingTerms().equals(""))
		materialSupplierVo.setShippingTerms(splitMaterialData(materialSupplierVo.getShippingTerms().toLowerCase(),"","INCOTERMS"));
		if(materialSupplierVo.getStdCostCurrencySupplier()!=null && !materialSupplierVo.getStdCostCurrencySupplier().equals(""))
		materialSupplierVo.setStdCostCurrencySupplier(splitMaterialData(materialSupplierVo.getStdCostCurrencySupplier(),"COMPOSITE","CURRENCY"));
		if(materialSupplierVo.getStdCostFy()!=null && !materialSupplierVo.getStdCostFy().equals(""))
		materialSupplierVo.setStdCostFy(splitMaterialData(materialSupplierVo.getStdCostFy(),"","STDCOSTFY"));
		
		if(materialSupplierVo.getPlaceOfHarvest()!=null && !materialSupplierVo.getPlaceOfHarvest().equals(""))
			materialSupplierVo.setPlaceOfHarvest(splitMaterialData(materialSupplierVo.getPlaceOfHarvest(),"COMPOSITE","PLACEOFHARVEST"));
		
		
		if(materialSupplierVo.getSurfaceFinishms()!=null && !materialSupplierVo.getSurfaceFinishms().equals(""))
			materialSupplierVo.setSurfaceFinishms(materialDao.getMatAttributeData("SURFACEFINISH", materialSupplierVo.getSurfaceFinishms(),materialType));

		if(materialSupplierVo.getSurfaceFinishmsHardware()!=null && !materialSupplierVo.getSurfaceFinishmsHardware().equals(""))
			materialSupplierVo.setSurfaceFinishmsHardware(materialDao.getMatAttributeData("SURFACEFINISH",materialSupplierVo.getSurfaceFinishmsHardware(),materialType));
		
		if(materialSupplierVo.getSurfaceFinishnms()!=null && !materialSupplierVo.getSurfaceFinishnms().equals(""))
			materialSupplierVo.setSurfaceFinishnms(materialDao.getMatAttributeData("SURFACEFINISH",materialSupplierVo.getSurfaceFinishnms(),materialType));

		if(materialSupplierVo.getMaterialContent()!=null && !materialSupplierVo.getMaterialContent().equals(""))
			materialSupplierVo.setMaterialContent(splitMaterialData(materialSupplierVo.getMaterialContent(),"","MATERIALCONTENT"));

		//yes or no single values
		if(materialSupplierVo.getActiveMaterialSupplier()!=null && !materialSupplierVo.getActiveMaterialSupplier().equals(""))
			materialSupplierVo.setActiveMaterialSupplier(splitMaterialData(materialSupplierVo.getActiveMaterialSupplier(),"","ACTIVEMATERIALSUPPLIER"));

		if(materialSupplierVo.getArticleApproval()!=null && !materialSupplierVo.getArticleApproval().equals(""))
			materialSupplierVo.setArticleApproval(splitMaterialData(materialSupplierVo.getArticleApproval(),"","ARTICLEAPPROVAL"));
		
		if(materialSupplierVo.getExoticMaterial()!=null && !materialSupplierVo.getExoticMaterial().equals(""))
			materialSupplierVo.setExoticMaterial(splitMaterialData(materialSupplierVo.getExoticMaterial(),"","EXOTICMATERIAL"));
		
		if(materialSupplierVo.getPrintMethod()!=null && !materialSupplierVo.getPrintMethod().equals(""))
			materialSupplierVo.setPrintMethod(splitMaterialData(materialSupplierVo.getPrintMethod(),"","PRINTMETHOD"));
		
		if(materialSupplierVo.getAssociatedIntros()!=null && !materialSupplierVo.getAssociatedIntros().equals(""))
			materialSupplierVo.setAssociatedIntros(splitMaterialData(materialSupplierVo.getAssociatedIntros(),"","ASSOCIATEDINTRODATE"));
		
		if(materialSupplierVo.getAssociatedTeams()!=null && !materialSupplierVo.getAssociatedTeams().equals(""))
			materialSupplierVo.setAssociatedTeams(splitMaterialData(materialSupplierVo.getAssociatedTeams(),"","ASSOCIATEDTEAMS"));
		
		if(materialSupplierVo.getChannel()!=null && !materialSupplierVo.getChannel().equals(""))
			materialSupplierVo.setChannel(splitMaterialData(materialSupplierVo.getChannel(),"","CHANNEL"));
		
		if(materialSupplierVo.getCities()!=null && !materialSupplierVo.getCities().equals(""))
			materialSupplierVo.setCities(splitMaterialData(materialSupplierVo.getCities(),"","CITES"));
		

		if(materialSupplierVo.getFishAndWildlife()!=null && !materialSupplierVo.getFishAndWildlife().equals(""))
			materialSupplierVo.setFishAndWildlife(splitMaterialData(materialSupplierVo.getFishAndWildlife(),"","FISHANDWILDLIFE"));
		
		if(materialSupplierVo.getFfpStatus()!=null && !materialSupplierVo.getFfpStatus().equals(""))
			materialSupplierVo.setFfpStatus(splitMaterialData(materialSupplierVo.getFfpStatus(),"","FITFORPURPOSESTAUS"));
		
		if(materialSupplierVo.getHarvestMethod()!=null && !materialSupplierVo.getHarvestMethod().equals(""))
			materialSupplierVo.setHarvestMethod(splitMaterialData(materialSupplierVo.getHarvestMethod(),"","HARVESTMETHOD"));
		
		
		
		if(materialSupplierVo.getManufMethod()!=null && !materialSupplierVo.getManufMethod().equals(""))
			materialSupplierVo.setManufMethod(splitMaterialData(materialSupplierVo.getManufMethod(),"","MANUFACTURINGMETHOD"));
		
		if(materialSupplierVo.getProhibited()!=null && !materialSupplierVo.getProhibited().equals(""))
			materialSupplierVo.setProhibited(splitMaterialData(materialSupplierVo.getProhibited(),"","PROHIBITED"));
		
		if(materialSupplierVo.getUnitOfCurrency()!=null && !materialSupplierVo.getUnitOfCurrency().equals(""))
			materialSupplierVo.setUnitOfCurrency(splitMaterialData(materialSupplierVo.getUnitOfCurrency(),"","CURRENCY"));
		
		if(materialSupplierVo.getTotalContentms()!=null && !materialSupplierVo.getTotalContentms().equals(""))
			materialSupplierVo.setTotalContentms(splitMaterialData(materialSupplierVo.getTotalContentms(),"","FABRICCONTENT"));
		
		if(materialSupplierVo.getCoreContentms()!=null && !materialSupplierVo.getCoreContentms().equals(""))
			materialSupplierVo.setCoreContentms(splitMaterialData(materialSupplierVo.getCoreContentms(),"COMPOSITE","FABRICCONTENT"));
		
		if(materialSupplierVo.getContentWithBacking()!=null && !materialSupplierVo.getContentWithBacking().equals(""))
			materialSupplierVo.setContentWithBacking(splitMaterialData(materialSupplierVo.getContentWithBacking(),"","FABRICCONTENT"));
		
		
		
		if(materialSupplierVo.getExoticCommonName()!=null && !materialSupplierVo.getExoticCommonName().equals(""))
			materialSupplierVo.setExoticCommonName(splitMaterialData(materialSupplierVo.getExoticCommonName(),"","EXOTICCOMMONNAME"));
		
		if(materialSupplierVo.getUnrestricted()!=null && !materialSupplierVo.getUnrestricted().equals(""))
			materialSupplierVo.setUnrestricted(splitMaterialData(materialSupplierVo.getUnrestricted(),"","UNRESTRICTED"));
		
		if(materialSupplierVo.getPrintMethod()!=null && !materialSupplierVo.getPrintMethod().equals(""))
			materialSupplierVo.setPrintMethod(splitMaterialData(materialSupplierVo.getUnrestricted(),"","COLORINGMETHOD"));
		
		
		if(materialSupplierVo.getEstCuttableWidth()!=null && !materialSupplierVo.getEstCuttableWidth().equals("")){
			if(materialSupplierVo.getEstCuttableWidth().equalsIgnoreCase("true"))
			materialSupplierVo.setEstCuttableWidth("Yes");
			else
				materialSupplierVo.setEstCuttableWidth("No");
		}
		
		if(materialSupplierVo.getEstCuttableWidthFace()!=null && !materialSupplierVo.getEstCuttableWidthFace().equals("")){
			if(materialSupplierVo.getEstCuttableWidthFace().equalsIgnoreCase("true"))
			materialSupplierVo.setEstCuttableWidthFace("Yes");
			else
				materialSupplierVo.setEstCuttableWidthFace("No");
		}
		
		if(materialSupplierVo.getEstFullWidth()!=null && !materialSupplierVo.getEstFullWidth().equals("")){
			if(materialSupplierVo.getEstFullWidth().equalsIgnoreCase("true"))
			materialSupplierVo.setEstFullWidth("Yes");
			else
				materialSupplierVo.setEstFullWidth("No");
		}
		if(materialSupplierVo.getEstFullWidthFace()!=null && !materialSupplierVo.getEstFullWidthFace().equals("")){
			if(materialSupplierVo.getEstFullWidthFace().equalsIgnoreCase("true"))
			materialSupplierVo.setEstFullWidthFace("Yes");
			else
				materialSupplierVo.setEstFullWidthFace("No");
		}
		if(materialSupplierVo.getLongLeadTimeMS()!=null && !materialSupplierVo.getLongLeadTimeMS().equals("")){
			if(materialSupplierVo.getLongLeadTimeMS().equalsIgnoreCase("true"))
			materialSupplierVo.setLongLeadTimeMS("Yes");
			else
				materialSupplierVo.setLongLeadTimeMS("No");
		}
		if(materialSupplierVo.getStdCostEstimateFlag()!=null && !materialSupplierVo.getStdCostEstimateFlag().equals("")){
			if(materialSupplierVo.getStdCostEstimateFlag().equalsIgnoreCase("true"))
			materialSupplierVo.setStdCostEstimateFlag("Yes");
			else
				materialSupplierVo.setStdCostEstimateFlag("No");
		}
		if(materialSupplierVo.getBackedByMill()!=null && !materialSupplierVo.getBackedByMill().equals("")){
			if(materialSupplierVo.getBackedByMill().equalsIgnoreCase("true"))
			materialSupplierVo.setBackedByMill("Yes");
			else
				materialSupplierVo.setBackedByMill("No");
		}
		logger.info("getMaterialList Ends");
		}catch(Exception e){
			e.printStackTrace();
			}
		return materialSupplierVo;
	}

    public Map<String, Map<String, String>> getmaterialattribute(String attrtype, String attrname) throws Exception {
        Map<String, Map<String, String>> attributelist = materialDao.getmaterialAttributelist("", "");
        return attributelist;
    }
    
    public List<MaterialColorSearchResultVo> getMaterialColorSearch(String matPartNumber) throws Exception{
    	logger.info("getMaterialColorSearch Starts");
    	List<MaterialColorSearchResultVo> materialColorSearchResultList = materialDao.getMaterialColorSearch(matPartNumber);
    	logger.info("getMaterialColorSearch Ends");
    	return materialColorSearchResultList;
    	
    }
    
    public MaterialColorDetail getMaterialColorDetails(BigDecimal colorId) throws Exception {
    	logger.info("getMaterialColorDetails Starts");
    	MaterialColorDetail materialColorDetail = materialDao.getMaterialColorDetails(colorId);
    	logger.info("getMaterialColorDetails Ends");
    	return materialColorDetail;
    }
    
    public String splitMaterialData (String matData,String attType,String attName) throws Exception{
    	logger.info("splitMaterialData Starts");
    	String splitedData = "";
    	if(matData!= null && !matData.trim().equals("")){
    		String pattern = "(\\|\\~\\*\\~\\|)";
    		Pattern r = Pattern.compile(pattern);
    		Matcher m = r.matcher(matData);
    		String tmpMetData = m.replaceAll(",");

    	String[] splitMatData = tmpMetData.split(",");
    	for(String tmpData : splitMatData){
    		if(attType.equalsIgnoreCase("COMPOSITE")){

    			if(tmpData.contains("%")){
    			String[] tmpCompData = tmpData.split("%");
    			
    			tmpData = tmpCompData[0]+"% "+getMaterialAttrStore(tmpCompData[1],attName);
    			System.out.println("composite "+tmpData);
    			}else {
    				tmpData = " "+getMaterialAttrStore(tmpData,attName);
    			}
    		}else{
    			tmpData = getMaterialAttrStore(tmpData,attName);
    		}
    		if(splitedData.trim().length() >0 ){
    		splitedData = splitedData+","+tmpData;
    		}else{
    			splitedData = tmpData;
    		}
    	}
    	
    	logger.info("splitMaterialData Ends");
    	}
    	return splitedData;
    	  }
    
    public String getMaterialAttrStore(String valKey,String attName) throws Exception{
    	logger.info("splitMaterialData Starts");
    	 String matCompData = materialDao.getMatCompositeData(attName, valKey.trim());
    	logger.info("splitMaterialData Ends");
    	return matCompData;
    }
    
    public List<MaterialPricingSearchResult>  getMaterialPricingList (BigDecimal ida3MasterRef) throws Exception {
    	logger.info("getMaterialPricingList Starts");
    	List<MaterialPricingSearchResult>  matPricingSearchResult = materialDao.getMaterialPricingList(ida3MasterRef);
    	logger.info("getMaterialPricingList Ends");
		return matPricingSearchResult;
    	
    }
	@Override
	public List<MaterialReportVo> gererateMaterialReport(String cmnumber, String department,User user) {
		List<MaterialReportVo> materialReportVo = materialDao.getMaterialReportInfo(cmnumber,department);
		try {
			DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String today = df.format(new Date()).toString();
			System.out.println(user);
			System.out.println(user.getUserName());
		String fileName="WhereUsedReport_"+user.getUserName()+"_"+today+".xls";
		boolean status=generateReport(materialReportVo,fileName,user,cmnumber,department);
		if(status==true){
			System.out.println("Report sent to user");
		}
		else
		{
			System.out.println("Error in generating report at backend");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return materialReportVo;
	}
	private boolean generateReport(List<MaterialReportVo> materialReportVo, String fileName, User user, String cmnumber, String department) throws IOException {
		FileOutputStream fileOut = null;
		org.apache.poi.hssf.usermodel.HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		String filepath=PropertyReaderUtil.getValue("plmViewer.whereusedPath")+fileName;
		//String filepath=fileName;

	try{	
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet(fileName);
		HSSFRow rowhead = sheet.createRow((short) 0);
		HSSFFont font = workbook.createFont();
		
			/*
		 * HSSFCellStyle style = workbook.createCellStyle();
		 * font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		 * font.setColor(HSSFColor.AQUA.index);
		 * style.setFont(font);
		 * style.setFillBackgroundColor(HSSFColor.
		 * GREY_25_PERCENT.index); rowhead.setRowStyle(style);
		 */
		rowhead.createCell(0).setCellValue("CM Number");
		rowhead.createCell(1).setCellValue("Color");
		rowhead.createCell(2).setCellValue("Material (Desc)");
		rowhead.createCell(3).setCellValue("Supplier");
		rowhead.createCell(4).setCellValue("Style Number");
		rowhead.createCell(5).setCellValue("Product Life Cycle State");
		rowhead.createCell(6).setCellValue("SKU Name");
		rowhead.createCell(7).setCellValue("SKU Status");
		rowhead.createCell(8).setCellValue("SKU Intro");		
		rowhead.createCell(9).setCellValue("Department");
		rowhead.createCell(10).setCellValue("Product Intros");
		rowhead.createCell(11).setCellValue("SKU FS Intro");
		
		
		Iterator<MaterialReportVo> itr =materialReportVo.iterator();
		int count=1;
		while(itr.hasNext()){
			MaterialReportVo vo=itr.next();
			HSSFRow hssfRow = sheet.createRow(count++);
			HSSFFont hssfFont = workbook.createFont();
			
				/*
			 * HSSFCellStyle style = workbook.createCellStyle();
			 * font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			 * font.setColor(HSSFColor.AQUA.index);
			 * style.setFont(font);
			 * style.setFillBackgroundColor(HSSFColor.
			 * GREY_25_PERCENT.index); rowhead.setRowStyle(style);
			 */
			hssfRow.createCell(0).setCellValue(vo.getCmNumber());
			hssfRow.createCell(1).setCellValue(vo.getColor());
			hssfRow.createCell(2).setCellValue(vo.getMaterialDesc());
			hssfRow.createCell(3).setCellValue(vo.getSupplier());
			hssfRow.createCell(4).setCellValue(vo.getStyleNumber());
			hssfRow.createCell(5).setCellValue(vo.getProductLifecycleState());
			hssfRow.createCell(6).setCellValue(vo.getSkuName());
			hssfRow.createCell(7).setCellValue(vo.getSkuStatus());
			hssfRow.createCell(8).setCellValue(vo.getSkuIntro());		
			hssfRow.createCell(9).setCellValue(vo.getDepartment());
			hssfRow.createCell(10).setCellValue(vo.getProductIntros());
			hssfRow.createCell(11).setCellValue(vo.getSkuFsIntro());
		}
		
		if (workbook != null) {
			File file=new File(filepath);
			fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			System.out.println("Report with file name : " + filepath);
			System.out.println("Report File name: " + filepath);
			try {
				triggerMail(new File(filepath),user,cmnumber,department);
			} catch (Exception e) {
				System.out.println("FailedStylesReport.triggerMail Error in sending Mial " + e.getMessage());
			}
		
			System.out.println("The Report generated at path : " + filepath);
		}else{
			System.out.println("Report not generated");
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Failed to generate report: " + e.getMessage());
	}
	finally {
		if (fileOut != null)
			fileOut.close();
	}

		return true;
	}
	
	
	private static void triggerMail(File file, User user, String cmnumber, String department) throws Exception {
		logger.info("FailedStyleReport.triggerMail  Trigger mail maethod initiated.");
		String to = user.getEmail();
	
		String from =  PropertyReaderUtil.getValue("plmViewer.from");
		String host =  PropertyReaderUtil.getValue("plmViewer.host");
		
		String subject = "Where Used Report :"+file.getName();
		String[] recepients = to.split(",");
		InternetAddress[] address = new InternetAddress[recepients.length];
		int counter = 0;
		for (String recepient : recepients) {
			address[counter] = new InternetAddress(recepient.trim());
			counter++;
		}
		// Get the session object
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		// compose the message
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("PLMViewer - Where Used Report");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("PLMViewer : Generated Where used report for CM# "+ cmnumber + " and Department "+ department);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(file.getName());
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			logger.info("RunBatch.triggerMail Mail sent successfully");
		} catch (MessagingException mex) {
			logger.error("FailedStylesReport.triggerMail Error in sending Mial " + mex.getMessage());
		}

	}
}
