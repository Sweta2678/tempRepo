/**
 * 
 */
/**
 * @author AB263159
 *
 */
package com.coach.middleware.batch.itemProcessor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.smartcardio.ATR;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.middleware.RunBatch;
import com.coach.middleware.util.CoachUtil;
import com.coach.middleware.util.PMDBUtil;
import com.coach.middleware.batch.dao.AttributeExtractDAO;
import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.ColourCodingVo;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LPSKUFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LpskuExtractVO;
import com.coach.middleware.batch.dao.VO.SeasonGroupMasterVO;
import com.coach.middleware.batch.dao.VO.SizeScaleMappingVO;
import com.coach.middleware.batch.dao.VO.SizeScaleMasterVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;
import com.coach.middleware.batch.dto.TotoExtractDTO;
import com.coach.middleware.extractloader.LpskuExtract;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;

public class LPSKUExtractItemProcessor implements ItemProcessor<LpskuExtractVO, List<LpskuExtractVO>> {

	private static final Logger logger = Logger.getLogger(LPSKUExtractItemProcessor.class);

	@Autowired
	private AttributeExtractDAO attributeExtractDAO;

	@Autowired
	private DataSource dataSource;

	TotoExtractDTO totoExtractDTO = new TotoExtractDTO();
	List<AttributeStoreVO> attributeStoreVOList = null;
	List<ColourCodingVo> colormastervolist = null;

	List<LPSKUFullPriceMOAVO> fullpriceMOAVO = null;
	List<SizeScaleMasterVO> sizescaleMasterVO = null;
	List<SizeScaleMappingVO> sizescaleMappingVO = null;
	List<SeasonGroupMasterVO> seasonGroupMasterVO = null;

	List<String> deltaextract = null;

	boolean fetchAttrStoreDet = true;
	int i = 0;
	boolean rowOneAdded = false;
	AttributeStoreVO attributemapper = null;
	static int ii = 0;

	// List<LpskuExtractVO> lpskuExtractVOList = null;

	// final List<TotoExtractVO> totoExtractVOList = new ArrayList();
	public static List<LpskuExtractVO> lpskuExtractVOList = new ArrayList<LpskuExtractVO>();

	@Override
	public List<LpskuExtractVO> process(LpskuExtractVO lpskuExtractVO) throws Exception {

		try {

			// System.out.println("Processor started..........");
			LpskuExtractVO lpskuVO = new LpskuExtractVO();
			if (fetchAttrStoreDet) {
				// getDeltaExtract(lpskuExtractVO);
				getAttributeStoreValues(lpskuVO);
				getColormasterlist(lpskuVO);
				getSizeScalemasterlist(lpskuVO);
				getFullPriceMOAlist(lpskuVO);
				getSeasonGroupMasterlist(lpskuVO);
				getDeltaExtract(lpskuExtractVO);
				getSizeScalemapping(lpskuExtractVO);
				fetchAttrStoreDet = false;

			}

			// System.out.println("item processor");
			lpskuExtractVO = getvoobject(lpskuExtractVO);
			updateprice(lpskuExtractVO);
			updateDates(lpskuExtractVO);
			List<LpskuExtractVO> ls = updatezsku(lpskuExtractVO);
			List<LpskuExtractVO> list = null;
			if (lpskuExtractVO.getLicense() != null && lpskuExtractVO.getLicense().equalsIgnoreCase("yes")){
				list = updateLicenseVendorReffNumber(ls);
				lpskuExtractVOList.addAll(list);
			}
			else {
				lpskuExtractVO.setVendorRef("");
				lpskuExtractVOList.addAll(ls);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("returning data item processor");
		return lpskuExtractVOList;

	}

	private List<LpskuExtractVO> updateLicenseVendorReffNumber(List<LpskuExtractVO> lpskuExtractVOList) {
			List<LpskuExtractVO> lpskuList = new ArrayList();
			try {
			for (LpskuExtractVO lpskuExtractVO : lpskuExtractVOList) {
				String size = lpskuExtractVO.getSizescale();
				String styleUniqueID = lpskuExtractVO.getProdUniqueid();
				String skuUniqueID = lpskuExtractVO.getSkuUniqueid();
				String vendorRef = "";
				//System.out.println(" sizescale " + lpskuExtractVO.getSizescale() + " " + lpskuExtractVO.getSizeScaleID());
					for (SizeScaleMasterVO sizeVo : sizescaleMasterVO) {
						if (sizeVo.getStyleUniqueid() != null && sizeVo.getSkuUniqueid() != null) {
							if (sizeVo.getStyleUniqueid().equals(styleUniqueID)
									&& sizeVo.getSkuUniqueid().equals(skuUniqueID)
									&& sizeVo.getSizename().equals(size.trim())) {
								//System.out.println(" sizescaleMasterVO " + sizeVo.getSizename() + " " + size);
								vendorRef = sizeVo.getVendorReff();
								if (vendorRef != null) {
									lpskuExtractVO.setVendorRef(vendorRef);
									//System.out.println("Vendor : " + vendorRef);
								}
							}
						}
					}
				lpskuList.add(lpskuExtractVO);
			}
		} catch (Exception e) {
			logger.error("updateLicenseVendorReffNumber error in getting the Sizescale information");
		}
		return lpskuList;
	}

	private void getDeltaExtract(LpskuExtractVO lpskuVO) {
		if (LpskuExtract.runmode.equals("full")) {
			logger.info("Full mode");

			attributeExtractDAO.deleteLPSKUExtractStaging();
		} else {
			logger.info("Delta mode");
			attributeExtractDAO.deleteLPSKUExtractStagingbySlot(attributeExtractDAO.getDeltaExtractSlots(lpskuVO));
		}
	}

	private void updateprice(LpskuExtractVO lpskuExtractVO) {
		LPSKUFullPriceMOAVO Skuvo = null, productvo = null;

		try {

			for (LPSKUFullPriceMOAVO pricevo : fullpriceMOAVO) {
				if (lpskuExtractVO.getSkuUniqueid() != null
						&& pricevo.getOWNERUNIQUEID().trim().equalsIgnoreCase(lpskuExtractVO.getSkuUniqueid().trim())) {
					Skuvo = pricevo;
					break;
				}
				if (pricevo.getOWNERUNIQUEID().trim().equalsIgnoreCase(lpskuExtractVO.getProdUniqueid().trim())) {
					productvo = pricevo;
					break;
				}
			}

			/******************* Assigning Retail Price *****************/
			if (Skuvo != null && Skuvo.getRETAILPRICE() != null && !Skuvo.getRETAILPRICE().equals("")) {
				lpskuExtractVO.setRetailPrice(padd(11, Skuvo.getRETAILPRICE()));
			} else
				if (productvo != null && productvo.getRETAILPRICE() != null && !productvo.getRETAILPRICE().equals("")) {
				lpskuExtractVO.setRetailPrice(padd(11, productvo.getRETAILPRICE()));
			}
			if (lpskuExtractVO.getRetailPrice() == null || !CoachUtil.hasContent(lpskuExtractVO.getRetailPrice())) {
				lpskuExtractVO.setRetailPrice(padd(11, "0.0"));
			}
			/*********************************************************/

			/******************* Assigning WHOLESALEPRICE *****************/
			if (Skuvo != null && Skuvo.getWHOLESALEPRICE() != null && !Skuvo.getWHOLESALEPRICE().equals("")) {
				lpskuExtractVO.setWhoePrice(padd(11, Skuvo.getWHOLESALEPRICE()));
			} else if (productvo != null && productvo.getWHOLESALEPRICE() != null
					&& !productvo.getWHOLESALEPRICE().equals("")) {
				lpskuExtractVO.setWhoePrice(padd(11, productvo.getWHOLESALEPRICE()));
			}
			if (lpskuExtractVO.getWhoePrice() == null || !CoachUtil.hasContent(lpskuExtractVO.getRetailPrice())) {
				lpskuExtractVO.setWhoePrice(padd(11, "0.0"));
			}
			/*********************************************************/

			// if(Skuvo !=null && Skuvo.getTARGETCOST()!=null &&
			// !Skuvo.getTARGETCOST().equals("")) {
			// lpskuExtractVO.setOriginalTargetcost(padd(11,Skuvo.getTARGETCOST()));
			// } else if(productvo !=null && productvo.getTARGETCOST()!=null &&
			// !productvo.getTARGETCOST().equals("")) {
			// lpskuExtractVO.setOriginalTargetcost(padd(11,productvo.getTARGETCOST()));
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

		lpskuExtractVO.setOriginalTargetcost(padd(11, lpskuExtractVO.getOriginalTargetcost()));

	}

	private void updateDates(LpskuExtractVO lpskuExtractVO) throws Exception {

		/*************** Intro Dates ***************/
		try {

			String sku = lpskuExtractVO.getsKUIntroDate();
			String pm = lpskuExtractVO.getStyleIntroDate();
			String pmdate = null, skudate = null;
			for (SeasonGroupMasterVO seasonvo : seasonGroupMasterVO) {
				if (seasonvo.getUniqueid() != null && seasonvo.getUniqueid().trim().equals(pm)) {
					pmdate = seasonvo.getSeasonGroupName();
				}
				if (seasonvo.getUniqueid() != null && seasonvo.getUniqueid().trim().equals(sku)) {
					skudate = seasonvo.getSeasonGroupName();
				}
			}

			if (skudate == null) {
				lpskuExtractVO.setsKUIntroDate(padd(10, dateFormater(pmdate)));
			} else {
				lpskuExtractVO.setsKUIntroDate(padd(10, dateFormater(skudate)));
			}

			// if(pmdate == null){
			// lpskuExtractVO.setStyleIntroDate(padd(10,dateFormater(skudate)));
			// }else{
			// lpskuExtractVO.setStyleIntroDate(padd(10,dateFormater(pmdate)));
			// }

			lpskuExtractVO.setStyleIntroDate(padd(10, dateFormater(pmdate)));

			/************** FSIntro Dates ***************/

			sku = lpskuExtractVO.getsKUFSIntroDate();
			pm = lpskuExtractVO.getStyleFSIntroDate();
			pmdate = null;
			skudate = null;
			for (SeasonGroupMasterVO seasonvo : seasonGroupMasterVO) {
				if (seasonvo.getUniqueid() != null && seasonvo.getUniqueid().trim().equals(pm)) {
					pmdate = seasonvo.getSeasonGroupName();
				}
				if (seasonvo.getUniqueid() != null && seasonvo.getUniqueid().trim().equals(sku)) {
					skudate = seasonvo.getSeasonGroupName();
				}
			}

			if (skudate == null) {
				lpskuExtractVO.setsKUFSIntroDate(padd(10, dateFormater(pmdate)));
			} else {
				lpskuExtractVO.setsKUFSIntroDate(padd(10, dateFormater(skudate)));
			}

			// if(pmdate == null){
			// lpskuExtractVO.setStyleFSIntroDate(padd(10,dateFormater(skudate)));
			// }else{
			// lpskuExtractVO.setStyleFSIntroDate(padd(10,dateFormater(pmdate)));
			// }

			lpskuExtractVO.setStyleFSIntroDate(padd(10, dateFormater(pmdate)));

			/************** Delete Dates ***************/

			sku = lpskuExtractVO.getsKUDeleteDate();
			pm = lpskuExtractVO.getStyleDeleteDate();
			pmdate = null;
			skudate = null;
			for (SeasonGroupMasterVO seasonvo : seasonGroupMasterVO) {
				if (seasonvo.getUniqueid() != null && seasonvo.getUniqueid().trim().equals(pm)) {
					pmdate = seasonvo.getSeasonGroupName();
				}
				if (seasonvo.getUniqueid() != null && seasonvo.getUniqueid().trim().equals(sku)) {
					skudate = seasonvo.getSeasonGroupName();
				}
			}

			if (skudate == null) {
				lpskuExtractVO.setsKUDeleteDate(padd(10, dateFormater(pmdate)));
			} else {
				lpskuExtractVO.setsKUDeleteDate(padd(10, dateFormater(skudate)));
			}
			lpskuExtractVO.setStyleDeleteDate(padd(10, dateFormater(pmdate)));

			/************** FS Delete ***************/

			sku = lpskuExtractVO.getsKUFSDeleteDate();
			pm = lpskuExtractVO.getStyleFSDeleteDate();

			pmdate = null;
			skudate = null;
			for (SeasonGroupMasterVO seasonvo : seasonGroupMasterVO) {
				if (seasonvo.getUniqueid() != null && seasonvo.getUniqueid().trim().equals(pm)) {
					pmdate = seasonvo.getSeasonGroupName();
				}
				if (seasonvo.getUniqueid() != null && seasonvo.getUniqueid().trim().equals(sku)) {
					skudate = seasonvo.getSeasonGroupName();
				}
			}

			if (skudate == null) {
				lpskuExtractVO.setsKUFSDeleteDate(padd(10, dateFormater(pmdate)));
			} else {
				lpskuExtractVO.setsKUFSDeleteDate(padd(10, dateFormater(skudate)));
			}
			lpskuExtractVO.setStyleFSDeleteDate(padd(10, dateFormater(pmdate)));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String padd(int tolength, String value) {
		StringBuffer sb = new StringBuffer();
		try {

			if (value == null) {
				value = "";
			}
			sb.append(value);
			for (int i = value.length(); i < tolength; i++) {
				sb.append(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value.length() > tolength) {
			return sb.toString().substring(0, tolength);
		}
		return sb.toString();

	}

	static int val = 0;

	private List<LpskuExtractVO> updatezsku(LpskuExtractVO lpskuExtractVO) throws CloneNotSupportedException {

		List<LpskuExtractVO> VOList = new ArrayList<LpskuExtractVO>();
		if (CoachUtil.hasContent(lpskuExtractVO.getStyleNumber())) {
			lpskuExtractVO.setzSKU(padd(6, lpskuExtractVO.getStyleNumber()) + padd(5, lpskuExtractVO.getSkuname()));
		} else {
			lpskuExtractVO.setzSKU(padd(11, lpskuExtractVO.getSkuname()));
		}

		if (lpskuExtractVO.getSkuUniqueid() == null) {

			if (lpskuExtractVO.getSkuname() == null || lpskuExtractVO.getSkuname().trim().equals("")) {
				lpskuExtractVO.setzSKU(padd(18, padd(6, "") + padd(5, "")));
				lpskuExtractVO.setSizescale(padd(10, "null"));
			} else {
				lpskuExtractVO.setzSKU(
						padd(18, padd(6, lpskuExtractVO.getStyleNumber()) + padd(5, lpskuExtractVO.getSkuname())));
				lpskuExtractVO.setSizescale(padd(10, "null"));
			}
			VOList.add(lpskuExtractVO);
			return VOList;
		}
		if (lpskuExtractVO.getSkuUniqueid().equals("") || !CoachUtil.hasContent(lpskuExtractVO.getSkuUniqueid())) {
			if (lpskuExtractVO.getSkuname() == null || lpskuExtractVO.getSkuname().trim().equals("")) {
				lpskuExtractVO.setzSKU(padd(18, padd(6, "") + padd(5, "")));
				lpskuExtractVO.setSizescale(padd(10, "null"));
			} else {
				lpskuExtractVO.setzSKU(
						padd(18, padd(6, lpskuExtractVO.getStyleNumber()) + padd(5, lpskuExtractVO.getSkuname())));
				lpskuExtractVO.setSizescale(padd(10, "null"));
			}
			VOList.add(lpskuExtractVO);
			return VOList;
		}

		if (CoachUtil.hasContent(lpskuExtractVO.getSkuUniqueid())) {
			VOList = getsizedetails(lpskuExtractVO);
		} else {
			lpskuExtractVO.setSizescale(padd(10, "null"));
		}

		// for(SizeScaleMasterVO sizevo :sizescaleMasterVO){
		// if(sizevo.getSizeUniqueid().trim().equalsIgnoreCase(lpskuExtractVO.getSkuUniqueid().trim())){
		//
		// LpskuExtractVO lpsvo = (LpskuExtractVO) lpskuExtractVO.clone();
		// lpsvo.setSizescale(attributemapper("SIZES",sizevo.getSizename()).getDisplay());
		// lpsvo.setzSKU(padd(18,padd(6,lpskuExtractVO.getStyleNumber())+padd(5,lpskuExtractVO.getSkuname())+lpsvo.getSizescale()));
		// VOList.add(lpsvo);
		//
		// }
		// }

		if (VOList.size() == 0) {
			if (CoachUtil.hasContent(lpskuExtractVO.getStyleNumber()))
				lpskuExtractVO.setzSKU(
						padd(18, padd(6, lpskuExtractVO.getStyleNumber()) + padd(5, lpskuExtractVO.getSkuname())));
			else
				lpskuExtractVO.setzSKU(padd(18, lpskuExtractVO.getSkuname()));

			if (lpskuExtractVO.getSizescale() == null || !CoachUtil.hasContent(lpskuExtractVO.getSizescale())) {
				lpskuExtractVO.setSizescale(padd(10, "null"));
			}
			VOList.add(lpskuExtractVO);

		}
		// System.out.println("added Skue = "+VOList.size());
		// System.out.println("total sku = "+val);
		return VOList;
	}

	private List<LpskuExtractVO> getsizedetails(LpskuExtractVO lpskuExtractVO) throws CloneNotSupportedException {
		List<LpskuExtractVO> VOList = new ArrayList<LpskuExtractVO>();
		try {
			if (lpskuExtractVO.getSizeScaleID() == null) {
				return VOList;
			}
			StringTokenizer token = null;
			for (SizeScaleMappingVO mappingvo : sizescaleMappingVO) {
				if (mappingvo.getSizeScaleID().equals(lpskuExtractVO.getSizeScaleID())) {
					token = new StringTokenizer(mappingvo.getSizename(), "|~*~|");
					while (token.hasMoreTokens()) {
						String val = token.nextToken();
						if (val != null) {
							LpskuExtractVO lpsvo = (LpskuExtractVO) lpskuExtractVO.clone();
							lpsvo.setSizescale(padd(10, val.trim()));
							if (CoachUtil.hasContent(lpsvo.getStyleNumber())) {
								lpsvo.setzSKU(padd(18, padd(6, lpskuExtractVO.getStyleNumber())
										+ padd(5, lpskuExtractVO.getSkuname()) + lpsvo.getSizescale().trim()));
							} else {
								lpsvo.setzSKU(
										padd(18, padd(11, lpskuExtractVO.getSkuname()) + lpsvo.getSizescale().trim()));
							}
							if (lpsvo.getSizescale() == null || lpsvo.getSizescale().trim().equals("")) {
								lpsvo.setSizescale(padd(10, "null"));
							}
							VOList.add(lpsvo);
						}
					}
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return VOList;

	}

	private void getFullPriceMOAlist(LpskuExtractVO lpskuVO) {
		if (fullpriceMOAVO == null) {
			fullpriceMOAVO = attributeExtractDAO.getFullPriceMOAList(lpskuVO);
			// System.out.println("fullpricemoaItemProcessor ::
			// "+fullpricemoaItemProcessor.getFullPriceMOAVOlist().size());
			// fullpriceMOAVO =
			// fullpricemoaItemProcessor.getFullPriceMOAVOlist();
		}
	}

	private void getSizeScalemasterlist(LpskuExtractVO lpskuVO) {
		if (sizescaleMasterVO == null) {
			sizescaleMasterVO = attributeExtractDAO.getsizescaleMasterList(lpskuVO);
			// System.out.println("sizeScaleMasterItemProcessor ::
			// "+sizeScaleMasterItemProcessor.getSizeScaleVOlist().size());
			// sizescaleMasterVO =
			// sizeScaleMasterItemProcessor.getSizeScaleVOlist();
		}
	}

	private void getSizeScalemapping(LpskuExtractVO lpskuVO) {
		if (sizescaleMappingVO == null) {
			sizescaleMappingVO = attributeExtractDAO.getSkizeScaleMapping(lpskuVO);
			// System.out.println("sizeScaleMasterItemProcessor ::
			// "+sizeScaleMasterItemProcessor.getSizeScaleVOlist().size());
			// sizescaleMasterVO =
			// sizeScaleMasterItemProcessor.getSizeScaleVOlist();
		}
	}

	private void getSeasonGroupMasterlist(LpskuExtractVO lpskuVO) {
		try {
			if (seasonGroupMasterVO == null) {
				seasonGroupMasterVO = attributeExtractDAO.getSeasonGroupList(lpskuVO);
				// System.out.println("seasongroupItemProcessor ::
				// "+seasongroupItemProcessor.getSeasonGroupMasterVOlist().size());
				// seasonGroupMasterVO =
				// seasongroupItemProcessor.seasonGroupMasterVOlist;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public LpskuExtractVO getvoobject(LpskuExtractVO lpskuExtractVO) {
		try {
			StringBuffer sb = new StringBuffer();

			// slot Number
			lpskuExtractVO.setSlotNumber(padd(10, lpskuExtractVO.getSlotNumber()));

			// StyleNumber
			lpskuExtractVO.setStyleNumber(padd(6, lpskuExtractVO.getStyleNumber()));

			// SKUMASTERID
			lpskuExtractVO.setSkumasterid(padd(2, lpskuExtractVO.getSkumasterid()));

			// ZSKU
			// Update in ZSKU Methode

			// SKU Group Code
			lpskuExtractVO.setsKUGroupCode(padd(2, lpskuExtractVO.getsKUGroupCode()));

			// SKU Group Desc
			// lpskuExtractVO.setsKUGroupDesc("#"+lpskuExtractVO.getsKUGroupDesc());
			lpskuExtractVO.setsKUGroupDesc(padd(40, lpskuExtractVO.getsKUGroupDesc()));

			// Abbreviated Style SKU Full Name
			if (lpskuExtractVO.getAbbreviatedStyleSKUFullName() == null
					|| lpskuExtractVO.getAbbreviatedStyleSKUFullName().trim().equalsIgnoreCase("null")) {
				lpskuExtractVO.setAbbreviatedStyleSKUFullName("");
			}
			sb = sb.append(lpskuExtractVO.getAbbreviatedStyleSKUFullName()).append(";")
					.append(getColourName(lpskuExtractVO.getShortcolorcode()));
			lpskuExtractVO.setAbbreviatedStyleSKUFullName(padd(35, sb.toString()));

			// lpskuExtractVO.setAbbreviatedStyleSKUFullName(sb.toString());
			// DepartmentCode
			// attributemapper =
			// attributemapper("Department",lpskuExtractVO.getDepartmentCode());
			// if(attributemapper!=null){
			// lpskuExtractVO.setDepartmentCode(attributemapper.getDiplay4());
			// }else{
			// lpskuExtractVO.setDepartmentCode("");
			// }
			lpskuExtractVO.setDepartmentCode(padd(3, lpskuExtractVO.getDepartmentCode()));

			// Class Code
			// attributemapper =
			// attributemapper("StyleClass",lpskuExtractVO.getClassCode());
			// if(attributemapper!=null){
			// lpskuExtractVO.setClassCode(attributemapper.getDiplay4());
			// }else{
			// lpskuExtractVO.setClassCode("");
			// }
			lpskuExtractVO.setClassCode(padd(3, lpskuExtractVO.getClassCode()));

			// Sub Class Code
			// attributemapper =
			// attributemapper("Subclass",lpskuExtractVO.getSubClassCode());
			// if(attributemapper!=null){
			// lpskuExtractVO.setSubClassCode(attributemapper.getDiplay4());
			// }else{
			// lpskuExtractVO.setSubClassCode("");
			// }
			lpskuExtractVO.setSubClassCode(padd(3, lpskuExtractVO.getSubClassCode()));

			// Collection code
			// attributemapper =
			// attributemapper("collection",lpskuExtractVO.getCollectioncode());
			// if(attributemapper!=null){
			// lpskuExtractVO.setCollectioncode(padd(4,attributemapper.getDiplay4());
			// }else{
			// lpskuExtractVO.setCollectioncode(padd(4,""));
			// }
			lpskuExtractVO.setCollectioncode(padd(3, lpskuExtractVO.getCollectioncode()));

			// Collection Description
			attributemapper = attributemapper("composition", lpskuExtractVO.getCollectioncode());
			if (attributemapper != null) {
				lpskuExtractVO.setCollectionDescription(padd(40, attributemapper.getDisplay2()));
			} else {
				lpskuExtractVO.setCollectionDescription(padd(40, ""));
			}

			// Sub Collection Code
			lpskuExtractVO.setSubCollectionCode(padd(3, lpskuExtractVO.getSubCollectionCode()));

			// all date Details updated in Update detail function
			// Style Intro Date
			// Style FS Intro Date
			// SKU Intro Date
			// SKU FS Intro Date

			// Material Type Code
			// String code = lpskuExtractVO.getSkumaterial();
			// if(code == null || code.trim().equals("")){
			String code = lpskuExtractVO.getMaterialTypeCode();
			// }
			attributemapper = attributemapper("material", code);
			if (attributemapper != null) {
				lpskuExtractVO.setMaterialTypeCode(padd(3, attributemapper.getDisplay3()));
			} else {
				lpskuExtractVO.setMaterialTypeCode(padd(3, ""));
			}

			// Material Type Value
			attributemapper = attributemapper("material", code);
			if (attributemapper != null) {
				lpskuExtractVO.setMaterialTypeValue(padd(40, attributemapper.getDisplay4()));
			} else {
				lpskuExtractVO.setMaterialTypeValue(padd(40, ""));
			}

			// Merch SKU status
			attributemapper = attributemapper("status", lpskuExtractVO.getMerchSKUstatus());
			if (attributemapper != null) {
				lpskuExtractVO.setMerchSKUstatus(padd(30, attributemapper.getDisplay()));
			} else {
				lpskuExtractVO.setMerchSKUstatus(padd(30, ""));
			}

			// BOM status
			lpskuExtractVO.setbOMstatus(padd(15, CoachUtil.toInitCap(lpskuExtractVO.getbOMstatus())));
			// attributemapper =
			// attributemapper("bomstatus",lpskuExtractVO.getbOMstatus());
			// if(attributemapper!=null){
			// lpskuExtractVO.setbOMstatus(padd(15,
			// attributemapper.getDisplay()));
			// }else{
			// lpskuExtractVO.setbOMstatus(padd(15,""));
			// }

			// pricedetals updated in updateprice methode
			// RETPRICE
			// WH Price
			// Original Target cost

			// Material Type Code 2
			lpskuExtractVO.setMaterialTypeCoded2(padd(3, code));

			// Material Type Desc
			attributemapper = attributemapper("material", code);
			if (attributemapper != null) {
				lpskuExtractVO.setMaterialTypeDesc(padd(40, attributemapper.getDisplay2()));
			} else {
				lpskuExtractVO.setMaterialTypeDesc(padd(40, ""));
			}

			// Signature Type Code

			String signcode = lpskuExtractVO.getSkuSignatureTypeCode();
			if (signcode == null || signcode.trim().equals("")) {
				signcode = lpskuExtractVO.getPmSignatureTypeCode();
			}
			lpskuExtractVO.setSignatureTypeCode(signcode);
			lpskuExtractVO.setSignatureTypeCode(padd(3, signcode));

			// Signature Type Desc
			attributemapper = attributemapper("LOGOTYPE", signcode);
			if (attributemapper != null) {
				lpskuExtractVO.setSignatureTypeDesc(padd(40, attributemapper.getDisplay2()));
			} else {
				lpskuExtractVO.setSignatureTypeDesc(padd(40, ""));

			}

			// Long Lead Time Material
			lpskuExtractVO.setLongLeadTimeMaterial(padd(3, lpskuExtractVO.getLongLeadTimeMaterial()));

			// One time Buy
			lpskuExtractVO.setOnetimeBuy(padd(3, lpskuExtractVO.getOnetimeBuy()));

			// Gender Code
			lpskuExtractVO.setGenderCode(padd(3, lpskuExtractVO.getGenderCode()));

			// Team gt from sku if ot ther get from product

			attributemapper = attributemapper("TEAM", lpskuExtractVO.getTeam());
			if (attributemapper != null) {
				lpskuExtractVO.setTeam(padd(20, attributemapper.getDisplay()));
			} else {
				lpskuExtractVO.setTeam(padd(20, ""));
			}

			// Designer
			lpskuExtractVO.setDesigner(padd(40, ""));// Set as Empty As of Now

			// Engineer
			lpskuExtractVO.setEngineer(padd(40, ""));// Set as Empty As of Now

			// Prod Development
			lpskuExtractVO.setProdDevelopment(padd(40, ""));// Set as Empty As
															// of Now

			// Production Mgr
			lpskuExtractVO.setProductionMgr(padd(40, ""));// Set as Empty As of
															// Now

			// Silhouette Code
			lpskuExtractVO.setSilhouetteCode(padd(3, lpskuExtractVO.getSilhouetteCode()));// Set
																							// as
																							// Empty
																							// As
																							// of
																							// Now

			// Silhouette Desc
			attributemapper = attributemapper("Silhouette", lpskuExtractVO.getSilhouetteCode());
			if (attributemapper != null) {
				lpskuExtractVO.setSilhouetteDesc(padd(40, attributemapper.getDisplay2()));
			} else {
				lpskuExtractVO.setSilhouetteDesc(padd(40, ""));
			}

			// Renegade
			// if(lpskuExtractVO.getRenegade()!=null &&
			// lpskuExtractVO.getRenegade().equalsIgnoreCase("true")){
			// lpskuExtractVO.setRenegade(padd(1,"Y"));
			// }else{
			// lpskuExtractVO.setRenegade(padd(1,"N"));
			// }
			lpskuExtractVO.setRenegade(padd(1, " "));

			// Early Costing
			lpskuExtractVO.setEarlyCosting(padd(1, "N"));

			// Dual Development
			// lpskuExtractVO.setDualDevelopment(padd(1,lpskuExtractVO.getDualDevelopment()));
			lpskuExtractVO.setDualDevelopment(padd(1, " "));

			// HandbagSize
			attributemapper = attributemapper("HANDBAGSIZE", lpskuExtractVO.getHandbagSize());
			if (attributemapper != null) {
				lpskuExtractVO.setHandbagSize(padd(15, attributemapper.getDisplay()));
			} else {
				lpskuExtractVO.setHandbagSize(padd(15, ""));
			}

			// Factory Store Type Product
			String pmtype = lpskuExtractVO.getFactoryStoreTypeProduct();
			attributemapper = attributemapper("factoryStoreTypeProduct", pmtype);
			if (attributemapper != null) {
				lpskuExtractVO.setFactoryStoreTypeProduct(padd(45, attributemapper.getDisplay()));
			} else {
				lpskuExtractVO.setFactoryStoreTypeProduct(padd(45, ""));
			}

			// SKU Factory Store Type
			String skutype = lpskuExtractVO.getSkuFactoryStoreType();
			attributemapper = attributemapper("factoryStoreTypeProduct", skutype);
			if (attributemapper != null) {
				lpskuExtractVO.setSkuFactoryStoreType(padd(45, attributemapper.getDisplay()));
			} else {
				lpskuExtractVO.setSkuFactoryStoreType(padd(45, ""));
			}

			// Product Name
			lpskuExtractVO.setProductName(padd(40, lpskuExtractVO.getProductName()));

			// Plan Exclusion
			lpskuExtractVO.setPlanExclusion(padd(30, lpskuExtractVO.getPlanExclusion()));

			// Key Style Costing
			lpskuExtractVO.setKeyStyleCosting(padd(15, lpskuExtractVO.getKeyStyleCosting()));

			// First Edit
			lpskuExtractVO.setFirstEdit(padd(15, "No"));

			// Second Edit
			lpskuExtractVO.setSecondEdit(padd(15, "No"));

			// GCC
			lpskuExtractVO.setGcc(padd(1, lpskuExtractVO.getGcc()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lpskuExtractVO;

	}

	private Object getattributeName(String ObjectName, String ObjectLevel, String AttributeName, String KeyValue) {
		return null;
	}

	private Object getColourName(String shortcolorcode) {
		if (shortcolorcode == null || shortcolorcode.trim().equals("")) {
			return "";
		}
		for (ColourCodingVo color : colormastervolist) {
			if (color.getUniqueid().equals(shortcolorcode)) {
				return color.getClolorname();
			}
		}
		return shortcolorcode;
	}

	private void getAttributeStoreValues(LpskuExtractVO totoExtractVO) {
		try {
			if (attributeStoreVOList == null) {
				attributeStoreVOList = attributeExtractDAO.getTotoExtractAttrKeys(totoExtractVO);
				// System.out.println("attributestoreItemProcessor ::
				// "+attributestoreItemProcessor.getAttributeStoreVOlist().size());
				// attributeStoreVOList =
				// attributestoreItemProcessor.getAttributeStoreVOlist();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getColormasterlist(LpskuExtractVO totoExtractVO) {
		try {
			if (colormastervolist == null) {
				colormastervolist = attributeExtractDAO.getColourCoding(totoExtractVO);
				// System.out.println("colormasterItemProcessor ::
				// "+colormasterItemProcessor.getColourCodingVolist().size());

				// colormastervolist =
				// colormasterItemProcessor.getColourCodingVolist();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AttributeExtractDAO getAttributeExtractDAO() {
		return this.attributeExtractDAO;
	}

	public void setAttributeExtractDAO(AttributeExtractDAO attributeExtractDAO) {
		this.attributeExtractDAO = attributeExtractDAO;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public AttributeStoreVO attributemapper(String name, String value) {
		StringBuffer sb = new StringBuffer();
		if (value != null) {
			for (AttributeStoreVO attributeStoreVO : attributeStoreVOList) {

				if (attributeStoreVO.getAttributeName().trim().equalsIgnoreCase(name.trim())) {

					if (attributeStoreVO.getValueKey() != null
							&& attributeStoreVO.getValueKey().trim().equalsIgnoreCase(value.trim())) {
						return attributeStoreVO;
					}
				}
			}
		}

		return null;
	}
	// public void setLpskuExtractVOList(List<LpskuExtractVO>
	// lpskuExtractVOList) {
	// this.lpskuExtractVOList = lpskuExtractVOList;
	// }
	// public List<LpskuExtractVO> getLpskuExtractVOList() {
	// return lpskuExtractVOList;
	// }

	public String dateFormater(String introDate) {
		String date = "";
		try {
			String splitValues[] = null;
			if (introDate == null || introDate.trim().equals("")) {
				return "";
			}
			splitValues = introDate.split(" ");
			splitValues = splitValues[0].split("/");
			if (splitValues[0].length() < 2) {
				splitValues[0] = "0" + splitValues[0];
			}
			if (splitValues[1].length() < 2) {
				splitValues[1] = "0" + splitValues[1];
			}
			date = splitValues[0] + "/" + splitValues[1] + "/" + splitValues[2];

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

}