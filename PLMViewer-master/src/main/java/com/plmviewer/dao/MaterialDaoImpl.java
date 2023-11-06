/**
 *
 */
package com.plmviewer.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plmviewer.model.FlexBOMLink;
import com.plmviewer.model.MaterialColorDetail;
import com.plmviewer.model.MaterialColorSearchResultVo;
import com.plmviewer.model.MaterialPricingSearchResult;
import com.plmviewer.model.MaterialReportVo;
import com.plmviewer.model.MaterialResultVo;
import com.plmviewer.model.MaterialSearchVo;
import com.plmviewer.model.MaterialSupplierVo;
import com.plmviewer.model.MaterialVo;
import com.plmviewer.service.MaterialServiceImpl;
import com.plmviewer.util.FlexObject;
import com.plmviewer.util.FormatHelper;
import com.plmviewer.util.MaterialConstant;
import com.plmviewer.util.MaterialSupplier;
import com.plmviewer.util.MaterialSupplierConstant;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author uthanasekarapandian
 *
 */
@Repository
public class MaterialDaoImpl implements MaterialDao {
	
	public HashMap VALID_LINK_MERGE_MAP = new HashMap();
	private String colorId,colorName;

	@Autowired
	SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory
			.getLogger(MaterialDaoImpl.class);

	@Transactional
	@SuppressWarnings("unchecked")
	public List<MaterialResultVo> getMaterialList(
			MaterialSearchVo materialSearchVo) throws Exception {
		logger.info("getMaterialList Starts");
		List<MaterialResultVo> materialResultVoList = null;
		try {
			String searchSql = "select att43 \"materialSlot\", att1 \"materialName\",att10 \"materialPartNumber\",att11 \"materialShortName\","
					+ " ida3a11 \"materialTypeId\","
					+ "(select display from MATERIALATTRIBUTESTORE where valuekey = att17 and attributename = 'MATERIALSUBTYPE'  and  MaterialType = "
					+ MaterialConstant.materialtypecase
					+ " and ROWNUM = 1) \"subType\","
					+ "( "
					+ MaterialConstant.materialtypecase
					+ " ) \"materialType\","// select display from
											// MATERIALATTRIBUTESTORE where
											// valuekey = att21 and
											// attributename = 'MATERIALTYPE'
					+ "(select display from MATERIALATTRIBUTESTORE where valuekey = att39 and attributename = 'BRAND' ) \"brand\","
					+ "(select display from MATERIALATTRIBUTESTORE where valuekey = statestate and attributename = 'LIFECYCLESTATE' ) \"lifeCycleState\","
					+ "(select display from MATERIALATTRIBUTESTORE where valuekey = att41 and attributename = 'MCG' ) \"materialCapacityGroup\","
					+ "(select display from MATERIALATTRIBUTESTORE where valuekey = att20 and attributename = 'EXOTICCOMMONNAME' ) \"commonName\","
					+ "(select display from MATERIALATTRIBUTESTORE where valuekey = att38 and attributename = 'ACTIVEMATERIAL' )  \"activeMaterial\","
					+ "(select display from MATERIALATTRIBUTESTORE where valuekey = att47 and attributename = 'CMPARENTGROUP' ) \"cmParentGroup\""
					+ "  from lcsmaterial where   "
					+ materialWhereClause(materialSearchVo)
					+ " and latestiterationinfo =1  ";

			Query query = sessionFactory.getCurrentSession().createSQLQuery(
					searchSql);
			materialResultVoList = query.setResultTransformer(
					Transformers.aliasToBean(MaterialResultVo.class)).list();
		} catch (Exception e) {
			e.printStackTrace();
                        throw e;
		}
		logger.info("getMaterialList Ends");
		return materialResultVoList;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public MaterialVo getMaterialVo(String searchkey, String searchtype,
			String materialType) throws Exception {
		logger.info("getMaterialVoList Starts");
		String sql = "";
		String column = "";
		if (searchtype.equalsIgnoreCase("partnumber")) {
			column = "att10";
		} else {
			column = "att43";
		}
		if (materialType.equalsIgnoreCase(MaterialConstant.EdgeStainType)) {
			sql = MaterialConstant.edgeStainSql + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.EnamelType)) {
			sql = MaterialConstant.enamel + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.FillerType)) {
			sql = MaterialConstant.filler + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.HardwareType)) {
			sql = MaterialConstant.hardware + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.LeatherType)) {
			sql = MaterialConstant.leather + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.MiscType)) {
			sql = MaterialConstant.misc + " and " + column + " = '" + searchkey
					+ "'";
		} else if (materialType
				.equalsIgnoreCase(MaterialConstant.PackagingType)) {
			sql = MaterialConstant.packaging + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.TextileType)) {
			sql = MaterialConstant.textile + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.ThreadType)) {
			sql = MaterialConstant.thread + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType
				.equalsIgnoreCase(MaterialConstant.WovenTrimType)) {
			sql = MaterialConstant.wovernTrim + " and " + column + " = '"
					+ searchkey + "'";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.ZipperType)) {
			sql = MaterialConstant.zipper + " and " + column + " = '"
					+ searchkey + "'";
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);

		MaterialVo materialVo = null;
		try {
			List<MaterialVo> resultwithAliastoBean = query
					.setResultTransformer(
							Transformers.aliasToBean(MaterialVo.class)).list();
			if (resultwithAliastoBean != null
					& resultwithAliastoBean.size() > 0) {
				materialVo = (MaterialVo) resultwithAliastoBean.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
                        throw e;
		}
		logger.info("getMaterialVoList Ends");
		return materialVo;

	}

	private String materialWhereClause(MaterialSearchVo materialSearchVo) throws Exception{
		logger.info("materialWhereClause Starts");
		String whereClause = "";
		try {
			if (materialSearchVo.getMaterialName() != null
					&& !materialSearchVo.getMaterialName().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					if (materialSearchVo.getMaterialName().contains("*")) {
						whereClause = whereClause
								+ " and UPPER(att1) like '"
								+ replaceSplChar(materialSearchVo
										.getMaterialName()).toUpperCase() + "'";
					} else {
						whereClause = whereClause + " and UPPER(att1) = '"
								+ materialSearchVo.getMaterialName().toUpperCase() + "'";
					}
				} else if (materialSearchVo.getMaterialName().contains("*")) {
					whereClause = whereClause
							+ " UPPER(att1) like '"
							+ replaceSplChar(materialSearchVo.getMaterialName()).toUpperCase()
							+ "'";
				} else {
					whereClause = whereClause + " UPPER(att1) = '"
							+ materialSearchVo.getMaterialName().toUpperCase() + "'";
				}
			}
			if (materialSearchVo.getMaterialshotname() != null
					&& !materialSearchVo.getMaterialshotname().trim()
							.equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					if (materialSearchVo.getMaterialshotname().contains("*")) {
						whereClause = whereClause
								+ " and att11 like '"
								+ replaceSplChar(materialSearchVo
										.getMaterialshotname()) + "'";
					} else {
						whereClause = whereClause + " and att11 = '"
								+ materialSearchVo.getMaterialshotname() + "'";
					}
				} else if (materialSearchVo.getMaterialshotname().contains("*")) {
					whereClause = whereClause
							+ " att11 like '"
							+ replaceSplChar(materialSearchVo
									.getMaterialshotname()) + "'";
				} else {
					whereClause = whereClause + " att11 = '"
							+ materialSearchVo.getMaterialshotname() + "'";
				}
			}

			if (materialSearchVo.getPartNumber() != null
					&& !materialSearchVo.getPartNumber().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					if (materialSearchVo.getPartNumber().contains("*")) {
						whereClause = whereClause
								+ " and att10 like '"
								+ replaceSplChar(materialSearchVo
										.getPartNumber()) + "'";
					} else {
						whereClause = whereClause + " and att10 = '"
								+ materialSearchVo.getPartNumber() + "'";
					}
				} else if (materialSearchVo.getPartNumber().contains("*")) {
					whereClause = whereClause + " att10 like '"
							+ replaceSplChar(materialSearchVo.getPartNumber())
							+ "'";
				} else {
					whereClause = whereClause + " att10 = '"
							+ materialSearchVo.getPartNumber() + "'";
				}
			}

			if (materialSearchVo.getMaterialslot() != null
					&& !materialSearchVo.getMaterialslot().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					if (materialSearchVo.getMaterialslot().contains("*")) {
						whereClause = whereClause
								+ " and att43 like '"
								+ replaceSplChar(materialSearchVo
										.getMaterialslot()) + "'";
					} else {
						whereClause = whereClause + " and att43 = '"
								+ materialSearchVo.getPartNumber() + "'";
					}
				} else if (materialSearchVo.getMaterialslot().contains("*")) {
					whereClause = whereClause
							+ " att43 like '"
							+ replaceSplChar(materialSearchVo.getMaterialslot())
							+ "'";
				} else {
					whereClause = whereClause + " att43 = '"
							+ materialSearchVo.getMaterialslot() + "'";

				}
			}

			if (materialSearchVo.getBrand() != null
					&& !materialSearchVo.getBrand().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					whereClause = whereClause + " and att39  = '"
							+ materialSearchVo.getBrand() + "'";
				} else {
					whereClause = whereClause + " att39  = '"
							+ materialSearchVo.getBrand() + "'";
				}
			}
			if (materialSearchVo.getActivematerial() != null
					&& !materialSearchVo.getActivematerial().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					whereClause = whereClause + " and att38  = '"
							+ materialSearchVo.getActivematerial() + "'";
				} else {
					whereClause = whereClause + " att38  = '"
							+ materialSearchVo.getActivematerial() + "'";
				}
			}
			if (materialSearchVo.getMaterialType() != null
					&& !materialSearchVo.getMaterialType().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					whereClause = whereClause + " and ida3a11  = '"
							+ materialSearchVo.getMaterialType() + "'";
				} else {
					whereClause = whereClause + " ida3a11  = '"
							+ materialSearchVo.getMaterialType() + "'";
				}
			}
			if (materialSearchVo.getMaterialsubtype() != null
					&& !materialSearchVo.getMaterialsubtype().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					whereClause = whereClause + " and att17  = '"
							+ materialSearchVo.getMaterialsubtype() + "'";
				} else {
					whereClause = whereClause + " att17  = '"
							+ materialSearchVo.getMaterialsubtype() + "'";
				}
			}
			if (materialSearchVo.getExoticcomonname() != null
					&& !materialSearchVo.getExoticcomonname().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					whereClause = whereClause + " and att20  = '"
							+ materialSearchVo.getExoticcomonname() + "'";
				} else {
					whereClause = whereClause + " att20  = '"
							+ materialSearchVo.getExoticcomonname() + "'";
				}
			}
			if (materialSearchVo.getCmparentgroup() != null
					&& !materialSearchVo.getCmparentgroup().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					whereClause = whereClause + " and att47   = '"
							+ materialSearchVo.getCmparentgroup() + "'";
				} else {
					whereClause = whereClause + " att47   = '"
							+ materialSearchVo.getCmparentgroup() + "'";
				}
			}
			if (materialSearchVo.getLifecyclestate() != null
					&& !materialSearchVo.getLifecyclestate().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					whereClause = whereClause + " and statestate   = '"
							+ materialSearchVo.getLifecyclestate() + "'";
				} else {
					whereClause = whereClause + " statestate   = '"
							+ materialSearchVo.getLifecyclestate() + "'";
				}
			}
			if (materialSearchVo.getCapacitytype() != null
					&& !materialSearchVo.getCapacitytype().trim().equals("")) {
				if (whereClause != null && !whereClause.trim().equals("")) {
					whereClause = whereClause + " and att41   = '"
							+ materialSearchVo.getCapacitytype() + "'";
				} else {
					whereClause = whereClause + " att41   = '"
							+ materialSearchVo.getCapacitytype() + "'";
				}
			}
			logger.info("materialWhereClause Ends");
		} catch (Exception e) {
			e.printStackTrace();
                        throw e;
		}
		return whereClause;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Map<String, String>> getmaterialAttributelist (
			String attributelevel, String attributename) throws Exception{

		logger.info("getAttributelist Starts");

		Query query = sessionFactory
				.openSession()
				.createQuery(
						" SELECT attrStore.valueKey,attrStore.attributeName,attrStore.display  FROM MaterialAttributeStore  attrStore WHERE attrStore.attributeName in ('MATERIALTYPE','MATERIALSUBTYPE','MCG','EXOTICCOMMONNAME','ACTIVEMATERIAL','BRAND','CMPARENTGROUP','LIFECYCLESTATE') order by attrStore.display ");
		Map<String, Map<String, String>> attributemap = new HashMap<String, Map<String, String>>();
		try {
			// System.out.println("attribute Lst search ");
			List attributeList = query.list();

			logger.info("*************************" + attributeList.size());

			Map<String, String> submap = null;
			for (int i = 0; i < attributeList.size(); i++) {
				Object[] vo = (Object[]) attributeList.get(i);
				String key = "" + vo[1];
				submap = attributemap.get(key);
				if (submap == null) {
					submap = new TreeMap<String, String>();
					attributemap.put(key, submap);
				}
				submap.put("" + vo[0], "" + vo[2]);
			}
			Map<String, String> typesubmap = new HashMap<String, String>();
			submap = attributemap.get("MATERIALTYPE");
			Iterator<String> itr = submap.keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next().toString();
				typesubmap.put(getmaterialcode(key), key);
			}
			attributemap.put("MATERIALTYPE", typesubmap);
		} catch (Exception e) {
			e.printStackTrace();
                        throw e;
		}
		return attributemap;

	}

	private String replaceSplChar(String tmpValue) {
		logger.info("replaceSplChar Starts");
		tmpValue = tmpValue.replaceAll("\\*", "%");
		logger.info("replaceSplChar Ends");
		return tmpValue;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Map<BigDecimal, String> getMaterialSupplierList (
			BigDecimal ida3MasterRef) throws Exception{

		logger.info("getMaterialSupplierList Starts");
		Map<BigDecimal, String> materialSupplierMap = new HashMap<BigDecimal, String>();
		try {
			String searchSql = MaterialSupplier.materialSupplier
					+ "  and mat.ida3masterreference =  " + ida3MasterRef + "";
			Query query = sessionFactory.getCurrentSession().createSQLQuery(
					searchSql);
			List<Object[]> materialSupplierList = query.list();

			logger.info("materialSupplierList size() "
					+ materialSupplierList.size());

			if (materialSupplierList != null && materialSupplierList.size() > 0) {

				java.util.Iterator<Object[]> srcIter = materialSupplierList
						.iterator();
				while (srcIter.hasNext()) {
					Object[] srcObject = (Object[]) srcIter.next();
					BigDecimal matSupplierKey = (BigDecimal) srcObject[0];
					String matSupplierValue = (String) srcObject[1];
					materialSupplierMap.put(matSupplierKey, matSupplierValue);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
                        throw e;
		}
		logger.info("getMaterialSupplierList Ends");

		return materialSupplierMap;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public MaterialSupplierVo getMaterialSupplierVo (
			BigDecimal ida3MasterReference, String materialType) throws Exception{

		logger.info("getMaterialSupplierVo Starts");
		String sql = "";
		MaterialSupplierVo materialSupplierVo = null;
		try{
		if (materialType.equalsIgnoreCase(MaterialConstant.EdgeStainType)) {
			sql = MaterialSupplierConstant.materialSupplierEdgeStain
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.EnamelType)) {
			sql = MaterialSupplierConstant.materialSupplierEnamel
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.FillerType)) {
			sql = MaterialSupplierConstant.materialSupplierFiller
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.HardwareType)) {
			sql = MaterialSupplierConstant.materialSupplierHardware
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.LeatherType)) {
			sql = MaterialSupplierConstant.materialSupplierLeather
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.MiscType)) {
			sql = MaterialSupplierConstant.materialSupplierMisc
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType
				.equalsIgnoreCase(MaterialConstant.PackagingType)) {
			sql = MaterialSupplierConstant.materialSupplierPackaging
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.TextileType)) {
			sql = MaterialSupplierConstant.materialSupplierTextile
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.ThreadType)) {
			sql = MaterialSupplierConstant.materialSupplierThread
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType
				.equalsIgnoreCase(MaterialConstant.WovenTrimType)) {
			sql = MaterialSupplierConstant.materialSupplierWovenTrim
					+ "  ida3masterreference = " + ida3MasterReference + "";
		} else if (materialType.equalsIgnoreCase(MaterialConstant.ZipperType)) {
			sql = MaterialSupplierConstant.materialSupplierZipper
					+ "  ida3masterreference = " + ida3MasterReference + "";
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);

		
		List<MaterialSupplierVo> resultwithAliastoBean = query
				.setResultTransformer(
						Transformers.aliasToBean(MaterialSupplierVo.class))
				.list();
		if (resultwithAliastoBean != null & resultwithAliastoBean.size() > 0) {
			materialSupplierVo = (MaterialSupplierVo) resultwithAliastoBean
					.get(0);
		}
		}catch(Exception e){
			e.printStackTrace();
                        throw e;
		}
		logger.info("getMaterialSupplierVo Ends");
		return materialSupplierVo;
	}

	@Transactional
	public List<MaterialColorSearchResultVo> getMaterialColorSearch (
			String matPartNumber) throws Exception{
		logger.info("getMaterialColorSearch Starts");
		List<MaterialColorSearchResultVo> materialColorResult=null;
		try{
		String matColorSearchSql = MaterialConstant.materialColor
				+ " and mc.att26 ='" + matPartNumber + "'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				matColorSearchSql);
		materialColorResult = query
				.setResultTransformer(
						Transformers
								.aliasToBean(MaterialColorSearchResultVo.class))
				.list();
		for(MaterialColorSearchResultVo vo : materialColorResult){
			String status =vo.getMaterialColorStatus();
			if(status!=null && !status.equals("")){
				vo.setMaterialColorStatus(getMaterialColorAttribute(status, "MSCSTATUS"));
			}
			
		}
		}catch(Exception e){
			e.printStackTrace();
                        throw e;
		}
		return materialColorResult;
	}

	@Transactional
	public MaterialColorDetail getMaterialColorDetails(BigDecimal colorId) throws Exception {
		MaterialServiceImpl materialServiceImpl=new MaterialServiceImpl();
		logger.info("getMaterialColorSearch Starts");
		MaterialColorDetail materialColorDetail = null;
		try{
		String matColorDetailSql = MaterialConstant.materialColorDetail
				+ "  ida2a2  =  " + colorId + "";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				matColorDetailSql);
		
		List<MaterialColorDetail> resultwithAliastoBean = query
				.setResultTransformer(
						Transformers.aliasToBean(MaterialColorDetail.class))
				.list();
		if (resultwithAliastoBean != null & resultwithAliastoBean.size() > 0) {
			materialColorDetail = (MaterialColorDetail) resultwithAliastoBean
					.get(0);
		}
		
		if(materialColorDetail.getStatus()!=null && !materialColorDetail.getStatus().equals("") ){
			materialColorDetail.setStatus(getMaterialColorAttribute(materialColorDetail.getStatus(),"MSCSTATUS"));
		}
		if(materialColorDetail.getUnitOfMeasure()!=null && !materialColorDetail.getUnitOfMeasure().equals("") ){
			materialColorDetail.setUnitOfMeasure(getMaterialColorAttribute(materialColorDetail.getUnitOfMeasure(),"UOM"));
		}
		if(materialColorDetail.getMcBrand()!=null && !materialColorDetail.getMcBrand().equals("") ){
			materialColorDetail.setMcBrand(getMaterialColorAttribute(materialColorDetail.getMcBrand(),"BRAND"));
		}
		if(materialColorDetail.getStandardCostCurrency()!=null && !materialColorDetail.getStandardCostCurrency().equals("") ){
			materialColorDetail.setStandardCostCurrency(getMaterialColorAttribute(materialColorDetail.getStandardCostCurrency(),"CURRENCY"));
		}
		if(materialColorDetail.getStdCostEffectivity()!=null && !materialColorDetail.getStdCostEffectivity().equals("") ){
			materialColorDetail.setStdCostEffectivity(getMaterialColorAttribute(materialColorDetail.getStdCostEffectivity(),"STDCOSTFY"));
		}
		if(materialColorDetail.getArticleApproval()!=null && !materialColorDetail.getArticleApproval().equals("") ){
			materialColorDetail.setArticleApproval(getMaterialColorAttribute(materialColorDetail.getArticleApproval(),"ARTICLEAPPROVAL"));
		}
		if(materialColorDetail.getQaApproval()!=null && !materialColorDetail.getQaApproval().equals("") ){
			materialColorDetail.setQaApproval(getMaterialColorAttribute(materialColorDetail.getQaApproval(),"QASTATUS"));
		}
		if(materialColorDetail.getShippingTerms()!=null && !materialColorDetail.getShippingTerms().equals("") ){
			materialColorDetail.setShippingTerms(getMaterialColorAttribute(materialColorDetail.getShippingTerms(),"INCOTERMS"));
		}
		if(materialColorDetail.getStdCostFy()!=null && !materialColorDetail.getStdCostFy().equals("") ){
			materialColorDetail.setStdCostFy(getMaterialColorAttribute(materialColorDetail.getStdCostFy(),"STDCOSTFY"));
		}
		if(materialColorDetail.getFinalColorApproval()!=null && !materialColorDetail.getFinalColorApproval().equals("") ){
			materialColorDetail.setFinalColorApproval(getMaterialColorAttribute(materialColorDetail.getFinalColorApproval(),"ARTICLEAPPROVAL"));
		}
		if(materialColorDetail.getChannel()!=null && !materialColorDetail.getChannel().equals("") ){
			materialColorDetail.setChannel(getMaterialColorAttribute(materialColorDetail.getChannel(),"CHANNEL"));
		}
		  if (materialColorDetail.getStdCostEstimateFlag()!=null  && !materialColorDetail.getStdCostEstimateFlag().trim().equals("") ) {
	             String value = materialColorDetail.getStdCostEstimateFlag();
	             if(value.equalsIgnoreCase("true"))
	            	 materialColorDetail.setStdCostEstimateFlag("Yes");
	             else
	            	 materialColorDetail.setStdCostEstimateFlag("No");
	             
	         }
		  if (materialColorDetail.getArticleApprovalBasis()!=null  && !materialColorDetail.getArticleApprovalBasis().trim().equals("") ) {
	             String value = materialColorDetail.getArticleApprovalBasis();
	             if(value.equalsIgnoreCase("true"))
	            	 materialColorDetail.setArticleApprovalBasis("Yes");
	             else
	            	 materialColorDetail.setArticleApprovalBasis("No");
	             
	         }
		  if (materialColorDetail.getFeedMulti()!=null  && !materialColorDetail.getFeedMulti().trim().equals("") ) {
	             String value = materialColorDetail.getFeedMulti();
	             if(value.equalsIgnoreCase("true"))
	            	 materialColorDetail.setFeedMulti("Yes");
	             else
	            	 materialColorDetail.setFeedMulti("No");
	             
	         }
		}catch(Exception e){
			e.printStackTrace();
                        throw e;
		}
		return materialColorDetail;
	}

	private String getMaterialColorAttribute(String key, String attName) throws Exception{

		logger.info("getMaterialColorAttribute Starts");
		String matCompData = "";
		try{
		String sql = "SELECT attrStore.display  FROM MaterialAttributeStore  attrStore WHERE "
				+ " attrStore.attributeName =    '"
				+ attName.toUpperCase()
				+ "' AND valueKey = '" + key + "' ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<String> matCompList = query.list();
		
		if (matCompList != null && matCompList.size() > 0) {
			matCompData = (String) matCompList.get(0);
		}
		logger.info("getMaterialColorAttribute Ends");
		}catch(Exception e){
			e.printStackTrace();
                        throw e;
		}
		return matCompData;
	
	}

	@Transactional
	public String getMatCompositeData(String attName, String valKey) throws Exception{
		logger.info("getMatCompositeData Starts");
		String matCompData = "";
		try{
		String sql = "SELECT attrStore.display  FROM MaterialAttributeStore  attrStore WHERE "
				+ " attrStore.attributeName =    '"
				+ attName.toUpperCase()
				+ "' AND trim(lower(valueKey)) = '" + valKey.toLowerCase() + "' ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<String> matCompList = query.list();
		
		if (matCompList != null && matCompList.size() > 0) {
			matCompData = (String) matCompList.get(0);
		}
		logger.info("getMatCompositeData Ends");
		}catch(Exception e){
			e.printStackTrace();
                        throw e;
		}
		return matCompData;
	}

	@Transactional
	public String getMatAttributeData(String attName, String valKey,
			String matType) throws Exception{
		String matCompData ="";
		matType = matType.toLowerCase();
		logger.info("getMatAttributeData Starts");
		try{
		String sql = "SELECT attrStore.display  FROM MaterialAttributeStore  attrStore WHERE "
				+ " attrStore.attributeName =    '"
				+ attName.toUpperCase()
				+ "' AND valueKey = '"
				+ valKey
				+ "' AND lower(MaterialType) ='"
				+ matType + "'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<String> matCompList = query.list();
		if(matCompList.size()>0)
		matCompData = (String) matCompList.get(0);
		logger.info("getMatAttributeData Ends");
		}catch(Exception e){
			e.printStackTrace();
                        throw e;
		}
		return matCompData;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<MaterialPricingSearchResult> getMaterialPricingList(
			BigDecimal ida3MasterRef) throws Exception{
            
		List<MaterialPricingSearchResult> materialPricingResult =null;
		logger.info("getMaterialPricingList Starts");
		try{
		String searchSql = MaterialConstant.MaterialPricing + "  mpe.ida3a5 = "
				+ ida3MasterRef + " ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				searchSql);
		materialPricingResult= query
				.setResultTransformer(
						Transformers
								.aliasToBean(MaterialPricingSearchResult.class))
				.list();

		logger.info("getMaterialPricingList size() "
				+ materialPricingResult.size());

		logger.info("getMaterialPricingList Ends");
		}catch(Exception e){
			e.printStackTrace();
                        throw e;
		}
		return materialPricingResult;
	}

	private String getmaterialcode(String key) {

		if (key.equalsIgnoreCase(MaterialConstant.EdgeStainType)) {
			return MaterialConstant.EdgeStainType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.EnamelType)) {
			return MaterialConstant.EnamelType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.FillerType)) {
			return MaterialConstant.FillerType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.HardwareType)) {
			return MaterialConstant.HardwareType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.LeatherType)) {
			return MaterialConstant.LeatherType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.MiscType)) {
			return MaterialConstant.MiscType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.PackagingType)) {
			return MaterialConstant.PackagingType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.TextileType)) {
			return MaterialConstant.TextileType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.ThreadType)) {
			return MaterialConstant.ThreadType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.WovenTrimType)) {
			return MaterialConstant.WovenTrimType_id;
		} else if (key.equalsIgnoreCase(MaterialConstant.ZipperType)) {
			return MaterialConstant.ZipperType_id;
		}
		return key;
	}

	@Override
	@Transactional
	public List<MaterialReportVo> getMaterialReportInfo(String cmnumber, String department) {
		List<MaterialReportVo> result= new ArrayList<>();
	String sql=
			
			"SELECT DISTINCT LCSMaterial.att10 \"cmNumber\",  LCSColor.att1 \"color\", LCSMaterial.att1 \"materialDesc\", LCSSupplier.att1 \"supplier\","
			+ " LCSProduct.att44 \"styleNumber\", LCSProduct.att42 \"slotNumber\", LCSProduct.att1 \"styleName\", LCSProduct.STATESTATE \"productLifecycleState\", "
			+ "LCSSKU.att1  \"skuName\",  LCSSKU.att7 \"skuStatus\" , LCSSKU.att27 \"skuIntro\",  LCSProduct.att27 \"productIntros\" ,  LCSProduct.att24 \"skuFsIntro\",  LCSProduct.att49 \"department\","
			+"LCSSKU.num12 \"lcsSkuNum12\",FlexBOMLink.att1 \"colorDesc\",LCSSKU.att6 \"lcsSkuAtt6\", LCSSKU.num47 \"lcsSkuNum47\", LCSSKU.att24 \"lcsskuatt24\", LCSSKU.att5 \"lcsskuatt5\", LCSProduct.num47 \"lcsproductnum47\",  LCSProduct.att45 \"lcsproductatt45\", LCSProduct.att51 \"lcsproductatt51\", FlexBOMPart.IDA2A2 \"flexbompartida2a2\", LCSProduct.BRANCHIDITERATIONINFO \"prodbranchiditerationinfo\", LCSMaterial.BRANCHIDITERATIONINFO \"matbranchiditerationinfo\", FlexBOMLink.ida3e5 \"flexbomlinkida3e5\",  LCSSKU.ida3masterreference \"lcsskuida3masterreference\", FlexBOMLink.att1 \"flexbomlinkatt1\", FlexBOMLink.dimensionid \"flexbomlinkdimensionid\", FlexBOMLink.dimensionname \"flexbomlinkdimensionname\", FlexBOMLink.ida2a2 \"flexbomlinkida2a2\", FlexBOMLink.updatestampa2 \"flexbomlinkupdatestampa2\""
			+ " FROM FlexBOMLink,  FlexBOMPart, PRODAREV LCSProduct, LCSSupplier, LCSMaterial, LCSColor, FlexType, SKUAREV LCSSKU, FLEXSPECTOCOMPONENTLINK, FlexSpecification WHERE  FlexBOMPart.ida3a12 = LCSProduct.ida3masterreference AND FlexBOMPart.ida3masterreference = flexspectocomponentlink.ida3b4 AND  FlexBOMPart.flextypeidpath = FlexType.typeidpath AND FlexSpecification.ida3masterreference = flexspectocomponentlink.ida3a4 AND  FlexBOMLink.ida3a5 = FlexBOMPart.ida3masterreference AND FlexBOMLink.ida3c5 = LCSSupplier.ida3masterreference AND FlexBOMLink.ida3b5 = LCSMaterial.ida3masterreference  AND LCSProduct.ida3masterreference = LCSSKU.ida3a12 AND FlexBOMLink.ida3d5 = LCSColor.ida2a2(+) AND LCSProduct.LATESTITERATIONINFO = '1' AND  UPPER(FlexBOMPart.BOMTYPE) = 'MAIN' AND FlexBOMPart.LATESTITERATIONINFO = '1' AND UPPER(FlexBOMPart.VERSIONIDA2VERSIONINFO) = 'A' AND LCSSKU.LATESTITERATIONINFO = '1' "
			+ " AND UPPER(LCSProduct.statecheckoutinfo) <> 'WRK' AND UPPER(LCSSKU.statecheckoutinfo) <> 'WRK' AND UPPER(FlexBOMPart.statecheckoutinfo) <> 'WRK' AND  LCSSupplier.LATESTITERATIONINFO = '1' AND LCSMaterial.LATESTITERATIONINFO = '1' "
			+ "AND flexbomlink.WIP = '0' AND flexbomlink.dropped = '0' AND flexbomlink.outdate IS NULL  AND UPPER(FlexSpecification.statecheckoutinfo) <> 'WRK' AND FlexSpecification.LATESTITERATIONINFO = '1' "
			+ "AND ( UPPER(LCSMATERIAL.att10) = '"+cmnumber+"' ) AND  "
			//+ "LCSProduct.att44='14660' AND "
			+ "FlexBOMLink.ida3d5 <> '28296506' AND FlexType.IDA2A2 = '6119' AND ( UPPER(LCSProduct.att49) IN( '"+department.replaceAll(",", "','")+"') )    ORDER BY LCSProduct.att44, LCSSKU.att1, LCSMaterial.att10 ";
try {
	SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
	 query.addScalar("cmNumber",StringType.INSTANCE);
	 query.addScalar("color",StringType.INSTANCE);
	 query.addScalar("materialDesc",StringType.INSTANCE);
	 query.addScalar("supplier",StringType.INSTANCE);
	 query.addScalar("styleNumber",StringType.INSTANCE);
	 query.addScalar("slotNumber",StringType.INSTANCE);
	 query.addScalar("colorDesc",StringType.INSTANCE);

	 query.addScalar("styleName",StringType.INSTANCE);
	 query.addScalar("productLifecycleState",StringType.INSTANCE);
	 query.addScalar("skuName",StringType.INSTANCE);
	 query.addScalar("skuStatus",StringType.INSTANCE);
	 query.addScalar("skuIntro",StringType.INSTANCE);
	 query.addScalar("department",StringType.INSTANCE);
	 query.addScalar("productIntros",StringType.INSTANCE);
	 query.addScalar("skuFsIntro",StringType.INSTANCE);
	 //query.addScalar("productFSIntro",StringType.INSTANCE);
	 
	 query.addScalar("lcsSkuNum12",StringType.INSTANCE);
	 query.addScalar("lcsSkuAtt6",StringType.INSTANCE);
	 query.addScalar("lcsSkuNum47",StringType.INSTANCE);
	 query.addScalar("lcsskuatt24",StringType.INSTANCE);
	 query.addScalar("lcsskuatt5",StringType.INSTANCE);
	 query.addScalar("lcsproductnum47",StringType.INSTANCE);
	 query.addScalar("lcsproductatt45",StringType.INSTANCE);
	 query.addScalar("lcsproductatt51",StringType.INSTANCE);
	 query.addScalar("flexbompartida2a2",StringType.INSTANCE);
	 query.addScalar("prodbranchiditerationinfo",StringType.INSTANCE);
	 query.addScalar("matbranchiditerationinfo",StringType.INSTANCE);
	 query.addScalar("flexbomlinkida3e5",StringType.INSTANCE);
	 query.addScalar("lcsskuida3masterreference",StringType.INSTANCE);
	 query.addScalar("flexbomlinkida2a2",StringType.INSTANCE);
	 query.addScalar("flexbomlinkatt1",StringType.INSTANCE);
	 query.addScalar("flexbomlinkdimensionid",StringType.INSTANCE);
	 query.addScalar("flexbomlinkdimensionname",StringType.INSTANCE);
	 query.addScalar("flexbomlinkupdatestampa2",StringType.INSTANCE);
	 result= (List<MaterialReportVo>) query.setResultTransformer(Transformers.aliasToBean(MaterialReportVo.class)).list();
	 System.out.println("Before Clean up"+result.size());

		result=removeIncorrectRecords(result);
		result=removeDuplicateBranchIds(result);
		result=mergeLinks(result);
		
		
		
		//perform post processing
		if(result != null) {
			Iterator bomDataIter = result.iterator();
			MaterialReportVo materialReportVo;
			String attKey = null;
			String attValue = null;
			String teamId = null;
			
			while(bomDataIter.hasNext()){
				materialReportVo = (MaterialReportVo) bomDataIter.next();
				
				if(materialReportVo.getDepartment()!=null && (!materialReportVo.getDepartment().equals(""))){	
					String value = getAttributeValue("PRODUCT", "DEPARTMENT", materialReportVo.getDepartment());
					materialReportVo.setDepartment(value);
				}
				
				if(materialReportVo.getSkuFsIntro()!=null && (!materialReportVo.getSkuFsIntro().equals(""))){	
					String skuFsIntro = getAttributeValue("PRODUCT", "FSINTRODATE", materialReportVo.getSkuFsIntro());
					materialReportVo.setSkuFsIntro(skuFsIntro);
				}
				
				if(materialReportVo.getSkuIntro()!=null && (!materialReportVo.getSkuIntro().equals(""))){
					String skuIntro = getAttributeValue("PRODUCT", "INTRODATE", materialReportVo.getSkuIntro());
					materialReportVo.setSkuIntro(skuIntro);
				}
				
				if(materialReportVo.getProductIntros()!=null && (!materialReportVo.getProductIntros().equals(""))){
					String productIntros = getAttributeValue("PRODUCT", "INTRODATE", materialReportVo.getProductIntros());
					materialReportVo.setProductIntros(productIntros);
				}
									
				//check for freetyped color					
//				if(!FormatHelper.hasContent(materialReportVo.getColor())) {
//					materialReportVo.setColor(materialReportVo.getColorDesc());				
//				}
//				

//				if(FormatHelper.hasContent(materialReportVo.getProductLifecycleState())) {
//					materialReportVo.setProductLifecycleState(productLifecycleState);
//					
//					put("LCSProduct.STATESTATE", 
//						(String)this.PRODUCT_LIFECYCLE_VALUE_MAP.get(fob.getString("LCSProduct.STATESTATE")));					
//				} else {
//					fob.put("LCSProduct.STATESTATE", "");
//				}
//				
//				if(FormatHelper.hasContent(fob.getString("LCSSKU." + ATTCOL_BOM_STATUS))) {
//					fob.put("LCSSKU." + ATTCOL_BOM_STATUS, 
//						(String)this.BOM_STATUS_VALUE_MAP.get(fob.getString("LCSSKU." + ATTCOL_BOM_STATUS)));					
//				} else {
//					fob.put("LCSSKU." + ATTCOL_BOM_STATUS, "");
//				}
//				
//				if(FormatHelper.hasContent(fob.getString("LCSSKU." + ATTCOL_SKU_STATUS))) {
//					fob.put("LCSSKU." + ATTCOL_SKU_STATUS, 
//						(String)this.SKU_STATUS_VALUE_MAP.get(fob.getString("LCSSKU." + ATTCOL_SKU_STATUS)));					
//				} else {
//					fob.put("LCSSKU." + ATTCOL_SKU_STATUS, "");
//				}
//				
//				if(FormatHelper.hasContent(fob.getString("LCSSKU." + ATTCOL_SKU_FSINTRO))) {
//					fob.put("LCSSKU." + ATTCOL_SKU_FSINTRO, 
//						(String)this.FS_INTRO_VALUE_MAP.get(fob.getString("LCSSKU." + ATTCOL_SKU_FSINTRO)));					
//				} else {
//					if(FormatHelper.hasContent(fob.getString("LCSProduct." + ATTCOL_SKU_FSINTRO))) {
//						fob.put("LCSSKU." + ATTCOL_SKU_FSINTRO, 
//						(String)this.FS_INTRO_VALUE_MAP.get(fob.getString("LCSProduct." + ATTCOL_SKU_FSINTRO)));
//					} else {
//						fob.put("LCSSKU." + ATTCOL_SKU_FSINTRO, "");
//					}
//				}
//				
//				if(FormatHelper.hasContent(fob.getString("LCSSKU." + ATTCOL_SKU_INTRO))) {
//					fob.put("LCSSKU." + ATTCOL_SKU_INTRO, 
//						(String)this.INTRO_VALUE_MAP.get(fob.getString("LCSSKU." + ATTCOL_SKU_INTRO)));					
//				} else {
//					if(FormatHelper.hasContent(fob.getString("LCSProduct." + ATTCOL_SKU_INTRO))) {
//						fob.put("LCSSKU." + ATTCOL_SKU_INTRO, 
//						(String)this.INTRO_VALUE_MAP.get(fob.getString("LCSProduct." + ATTCOL_SKU_INTRO)));
//					} else {
//						fob.put("LCSSKU." + ATTCOL_SKU_INTRO, "");
//					}
//				}
//				
//				if(FormatHelper.hasContent(fob.getString("LCSProduct." + ATTCOL_DEPT))) {
//					fob.put("LCSProduct." + ATTCOL_DEPT, 
//						(String)this.DEPT_VALUE_MAP.get(fob.getString("LCSProduct." + ATTCOL_DEPT)));					
//				} else {
//					fob.put("LCSProduct." + ATTCOL_DEPT, "");
//				}
//				
//				if(FormatHelper.hasContent(fob.getString("LCSProduct." + ATTCOL_SUB_COL))) {
//					fob.put("LCSProduct." + ATTCOL_SUB_COL, 
//						(String)this.SUB_COL_VALUE_MAP.get(fob.getString("LCSProduct." + ATTCOL_SUB_COL)));					
//				} else {
//					fob.put("LCSProduct." + ATTCOL_SUB_COL, "");
//				}
//						
//				if(FormatHelper.hasContent(fob.getString("LCSProduct." + ATTCOL_SUB_CLASS))) {
//					fob.put("LCSProduct." + ATTCOL_SUB_CLASS, 
//						(String)this.SUB_CLASS_VALUE_MAP.get(fob.getString("LCSProduct." + ATTCOL_SUB_CLASS)));					
//				} else {
//					fob.put("LCSProduct." + ATTCOL_SUB_CLASS, "");
//				}

				//get sku group name
//				if(FormatHelper.hasContent(fob.getString("LCSSKU." + ATTCOL_SKU_GROUP))) {
//					skuGroup = (LCSRevisableEntity) LCSQuery.findObjectById("VR:com.lcs.wc.foundation.LCSRevisableEntity:" +
//                                                           fob.getString("LCSSKU." + ATTCOL_SKU_GROUP));
//					if(skuGroup != null) 							
//						fob.put("LCSSKU." + ATTCOL_SKU_GROUP, (String) skuGroup.getValue("name"));
//				} else {
//					fob.put("LCSSKU." + ATTCOL_SKU_GROUP, "");
//				}
//				
//				//get team name
//				teamId = null;
//				if(FormatHelper.hasContent(fob.getString("LCSSKU." + ATTCOL_TEAM))) {
//					teamId = fob.getString("LCSSKU." + ATTCOL_TEAM);
//				} else if(FormatHelper.hasContent(fob.getString("LCSProduct." + ATTCOL_TEAM))) {
//					teamId = fob.getString("LCSProduct." + ATTCOL_TEAM);
//				}
//				if(FormatHelper.hasContent(teamId)) {
//					teamObj = (LCSLifecycleManaged)LCSQuery.findObjectById("OR:com.lcs.wc.foundation.LCSLifecycleManaged:" + teamId);						
//					if(teamObj != null) 							
//						fob.put("LCSSKU." + ATTCOL_TEAM, (String)teamObj.getName());
//				} else {
//					fob.put("LCSSKU." + ATTCOL_TEAM, "");
//				}
		}				
		}
					
		// remove duplicate records
		result = removeDuplicateRecords(result);	
		
		
		} catch (Exception e) {
			System.out.println("Error in generating material Report");

			e.printStackTrace();
		}
		System.out.println("After Clean up"+result.size());
		return result;
		}
	
	
	private List<MaterialReportVo> removeDuplicateRecords(List<MaterialReportVo> bomData) throws Exception {		
		List<MaterialReportVo> finalData = new ArrayList<MaterialReportVo>();		
		HashMap uniqueDataMap = new HashMap();
		try {			
			if(bomData != null) {
				boolean uniqueAcrossBOM = true;
				Iterator bomDataIter = bomData.iterator();
				MaterialReportVo materialReportVo;				
				String uniqueKey = null;
				while(bomDataIter.hasNext()){
					materialReportVo = (MaterialReportVo) bomDataIter.next();
					uniqueKey = materialReportVo.getCmNumber() + "_" + 
							materialReportVo.getColor() + "_" + 
							materialReportVo.getSupplier() + "_" + 
							materialReportVo.getSlotNumber() + "_" + 
							materialReportVo.getSkuName();
					if(!uniqueAcrossBOM) {
						uniqueKey = uniqueKey + "_" + materialReportVo.getFlexbomlinkida2a2();
					}					
					if(!uniqueDataMap.containsKey(uniqueKey)) {
						uniqueDataMap.put(uniqueKey, materialReportVo);
						finalData.add(materialReportVo);
					} 
				}								
			}
			 System.out.println("removeDuplicateRecords Size : " + finalData.size());		
		} catch(Exception exp) {
			System.out.println("Error## removeDuplicateRecords() - " + exp.getMessage());
			exp.printStackTrace();		
		}
		//if(WHERE_USED_DEBUG) System.out.println("finalData - " + finalData);
		return finalData;
	}
	
	private List<MaterialReportVo> removeIncorrectRecords(List<MaterialReportVo> bomData) throws Exception {
		List<MaterialReportVo> cleanedData = new ArrayList<MaterialReportVo>();		
		try {			
			if(bomData != null) {
				Iterator bomDataIter = bomData.iterator();
				MaterialReportVo materialReportVo;
				String linkSKUMasterId = null;
				String skuMasterId = null;

				while(bomDataIter.hasNext()){
					materialReportVo = (MaterialReportVo) bomDataIter.next();
					linkSKUMasterId = materialReportVo.getFlexbomlinkida3e5();	
					skuMasterId = materialReportVo.getLcsskuida3masterreference();	
					if(!FormatHelper.hasContent(linkSKUMasterId) ||
						(FormatHelper.hasContent(linkSKUMasterId) && linkSKUMasterId.equals(skuMasterId))) {
						cleanedData.add(materialReportVo);
					}
				}								
			}			
		} catch(Exception exp) {
			System.out.println("Error## removeIncorrectRecords() - " + exp.getMessage());
			exp.printStackTrace();		
		}
		 System.out.println("removeIncorrectRecords cleanedData - " + cleanedData.size());
		return cleanedData;
	} 
	
	
	private  List<MaterialReportVo> removeDuplicateBranchIds( List<MaterialReportVo> bomData) throws Exception {		
		 List<MaterialReportVo> finalData = new ArrayList<MaterialReportVo>();	
		HashMap dimensionIdMap = new HashMap();
		try {			
			if(bomData != null) {				
				Iterator bomDataIter = bomData.iterator();
				MaterialReportVo materialReportVo;				
				String dimensionId = null;
				String modifiedTimeStamp = null;
				String oldModifiedTimeStamp = null;
				while(bomDataIter.hasNext()){
					materialReportVo = (MaterialReportVo) bomDataIter.next();
					dimensionId = materialReportVo.getFlexbomlinkdimensionid();
					modifiedTimeStamp = materialReportVo.getFlexbomlinkupdatestampa2();
					if(!dimensionIdMap.containsKey(dimensionId)) {
						dimensionIdMap.put(dimensionId, modifiedTimeStamp);						
					} else {
						oldModifiedTimeStamp = (String) dimensionIdMap.get(dimensionId);
						if(isDateAfter(modifiedTimeStamp, oldModifiedTimeStamp)) {
							dimensionIdMap.put(dimensionId, modifiedTimeStamp);	
						}
					}
				}

				//filter duplicate brand id results
				bomDataIter = bomData.iterator();
				while(bomDataIter.hasNext()){
					materialReportVo = (MaterialReportVo) bomDataIter.next();
					dimensionId = materialReportVo.getFlexbomlinkdimensionid();
					modifiedTimeStamp = materialReportVo.getFlexbomlinkupdatestampa2();					
					if(dimensionIdMap.containsKey(dimensionId)) {
						if(modifiedTimeStamp.equals((String) dimensionIdMap.get(dimensionId))) {
							finalData.add(materialReportVo);
						}
					}
				}
			}
			//if(WHERE_USED_DEBUG) System.out.println("BOM Search Result Size : " + finalData.size());		
		} catch(Exception exp) {
			System.out.println("Error## removeDuplicateBranchIds() - " + exp.getMessage());
			exp.printStackTrace();		
		}
		 System.out.println("removeDuplicateBranchIds finalData - " + finalData.size());
		return finalData;
	}

	private  boolean isDateAfter(String modifiedTimeStamp, String oldModifiedTimeStamp) {
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
       
		try {
			Date	modifiedTimeStamp1 = sdf.parse(modifiedTimeStamp);	
			Date	 oldModifiedTimeStamp1 = sdf.parse(oldModifiedTimeStamp);
			 if(modifiedTimeStamp1.compareTo(oldModifiedTimeStamp1) >= 0)	{	
	             return true;
	          }
		} catch (ParseException e) {		
			e.printStackTrace();
		}

		return false;
	}

	@Transactional
	public 	List<MaterialReportVo> mergeLinks(List<MaterialReportVo> bomData) throws Exception {
		List<MaterialReportVo> cleanedData = new ArrayList<MaterialReportVo>();	
		try {			
						
			if(bomData != null) {
				Iterator bomDataIter = bomData.iterator();
				MaterialReportVo materialReportVo;
				String linkId = null;
				String dimensionId = null;
				String linkSKUMasterId = null;
				String skuMasterId = null;
				String dimensionname = null;
				FlexBOMLink flexBOMLink = null;
				Collection allLinksForBranch = null;
				Iterator allLinksIterator = null;
				FlexBOMLink childLink = null;
				String childId = null;
				int skuIndex = 0;
				String childSkuMasterID = null;
				String linkMatName = null;				
				String linkColName = null;
				String placeholderMaterialId = null;
				String materialId = null;
												
				while(bomDataIter.hasNext()){
					materialReportVo = (MaterialReportVo) bomDataIter.next();
					linkId = materialReportVo.getFlexbomlinkida2a2();	
					dimensionId = materialReportVo.getFlexbomlinkdimensionid();
					dimensionname = materialReportVo.getFlexbomlinkdimensionname();	
					linkSKUMasterId = materialReportVo.getFlexbomlinkida3e5();	
					skuMasterId = materialReportVo.getLcsskuida3masterreference();	
					
					if(!FormatHelper.hasContent(dimensionname)) {
					
							if(VALID_LINK_MERGE_MAP.containsKey(dimensionId)) {
								allLinksForBranch = (Collection) VALID_LINK_MERGE_MAP.get(dimensionId);
							} else {
								flexBOMLink = (FlexBOMLink) findObjectById(linkId);
								allLinksForBranch = getAllLinksForBranch(flexBOMLink);
							}
							if(allLinksForBranch == null || (allLinksForBranch != null && allLinksForBranch.size() <= 0)) {
								VALID_LINK_MERGE_MAP.put(dimensionId, allLinksForBranch);
								cleanedData.add(materialReportVo);
							} else 
							{
								VALID_LINK_MERGE_MAP.put(dimensionId, allLinksForBranch);
								allLinksIterator = allLinksForBranch.iterator();
								int i=0;	
								boolean skipFlag = false;
								boolean duplicateBranchId = false;
								boolean hasSKUOverride = false;
								while(allLinksIterator.hasNext()) {
									childLink = (FlexBOMLink) allLinksIterator.next();
									childId = childLink.getIda2a2();
									if(!linkId.equals(childId)) {
										skuIndex = childLink.getDimensionId().indexOf("SKU:");
										if (skuIndex >= 0) {
											childSkuMasterID = childLink.getDimensionId().substring(skuIndex+4);
											if(childSkuMasterID.indexOf(skuMasterId) >= 0) {
												hasSKUOverride = true;
												//check if sku override exists in result
												if(childSkuOverrideExists(dimensionId, childSkuMasterID, bomData)) {
													//do nothing. this style level record would be ignored
												} else {													
													placeholderMaterialId = "5222";
													if(childLink.getIda3a5() != null) {
														materialId = childLink.getIda3b5();
													}													
													
													if(( childLink.getIda3b5() == null || ( childLink.getIda3b5() != null &&
															placeholderMaterialId.equals(materialId))) &&
														(childLink.getIda3c5() == null || (childLink.getIda3c5() != null &&
															(childLink.getIda3c5().equals("5201"))  )
													) ){
															linkColName = (String)childLink.getAtt1();
															if(FormatHelper.hasContent(linkColName) && 
																!linkColName.equals(materialReportVo.getColor())) {
																//System.out.println("overriding color name with free text - "+linkColName);
																materialReportVo.setColor(linkColName);
															}															
															cleanedData.add(materialReportVo);
												
													}
												}												
											} else {
												continue;
											}
										} else {
											duplicateBranchId = true;
										}
									} else {
										skipFlag = true;										
									}
									i++;
								}

								if(skipFlag && i==1) {
									//SKIP_LINK_MERGE_MAP.put(dimensionId, "SKIP");
									cleanedData.add(materialReportVo);
								} else if(duplicateBranchId && !hasSKUOverride) {
									cleanedData.add(materialReportVo);
								}
							} 
						//}
					} else {
						//logic appended to make sure only non-orphan overrides are added to collection
						FlexBOMLink flexBOMLink1 =findObjectById(linkId);
						FlexBOMLink bomLink = findTopLevelBranch(flexBOMLink1);
						if(bomLink!= null)
							cleanedData.add(materialReportVo);
					}
				}				
		System.out.println("mergeLinks Size : " + cleanedData.size());		
		} 
		}catch(Exception exp) {
			System.out.println("Error## mergeLinks() - " + exp.getMessage());
			exp.printStackTrace();		
		}
		//if(WHERE_USED_DEBUG) System.out.println("cleanedData - " + cleanedData);
		return cleanedData;
	}
	
	
	@Transactional
	  private FlexBOMLink findObjectById(String ida2a2) {
		  String sql= "select dimensionId \"dimensionId\",ida2a2 \"ida2a2\",ida3a5 \"ida3a5\",ida3b5 \"ida3b5\",ida3c5 \"ida3c5\",parentrev  \"parentrev\","
		  		+ "branchid \"branchid\",outdate \"outdate\",dropped \"dropped\", att1 \"att1\" from FLEXBOMLINK where ida2a2='"+ida2a2+"'";		
		  SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.addScalar("dimensionId",StringType.INSTANCE);		
			query.addScalar("ida2a2",StringType.INSTANCE);
			query.addScalar("ida3a5",StringType.INSTANCE);
			query.addScalar("parentrev",StringType.INSTANCE);
			query.addScalar("branchid",StringType.INSTANCE);
			query.addScalar("outdate",StringType.INSTANCE);
			query.addScalar("dropped",StringType.INSTANCE);
			query.addScalar("ida3b5",StringType.INSTANCE);
			query.addScalar("ida3c5",StringType.INSTANCE);
			query.addScalar("att1",StringType.INSTANCE);
		List<FlexBOMLink> bomLinkList = query.setResultTransformer(Transformers.aliasToBean(FlexBOMLink.class)).list();
		if(bomLinkList!=null)
			return bomLinkList.get(0);
		
		return null;
	}
	  
	@Transactional
		public  Collection<FlexBOMLink> getAllLinksForBranch(FlexBOMLink flexbomlink)
		        throws Exception
		    {
		
				String sql= "select dimensionId \"dimensionId\",ida2a2 \"ida2a2\",ida3a5 \"ida3a5\",ida3b5 \"ida3b5\",ida3c5 \"ida3c5\",parentrev  \"parentrev\","
		  		+ "branchid \"branchid\",outdate \"outdate\",dropped \"dropped\", att1 \"att1\" from FLEXBOMLINK where IDA3A5='"+flexbomlink.getIda3a5() +"' and  PARENTREV='"+flexbomlink.getParentrev()+"' and "
						+ "BRANCHID='"+flexbomlink.getBranchid()+"' and OUTDATE IS NULL	and DROPPED=0 order by  DIMENSIONNAME desc"
						;
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				query.addScalar("dimensionId",StringType.INSTANCE);		
				query.addScalar("ida2a2",StringType.INSTANCE);
				query.addScalar("ida3a5",StringType.INSTANCE);
				query.addScalar("parentrev",StringType.INSTANCE);
				query.addScalar("branchid",StringType.INSTANCE);
				query.addScalar("outdate",StringType.INSTANCE);
				query.addScalar("dropped",StringType.INSTANCE);
				query.addScalar("ida3b5",StringType.INSTANCE);
				query.addScalar("ida3c5",StringType.INSTANCE);
				query.addScalar("att1",StringType.INSTANCE);
				List<FlexBOMLink> list = query.setResultTransformer(Transformers.aliasToBean(FlexBOMLink.class)).list();
				return list;
		  }
		
	  
	@Transactional
		public  FlexBOMLink findTopLevelBranch(FlexBOMLink flexbomlink)
        throws Exception
		    {
				String sql= "select IDA2A2 \"ida2a2\" from FLEXBOMLINK where IDA3A5='"+flexbomlink.getIda3a5() +"'and  PARENTREV='"+flexbomlink.getParentrev()+"' and "
						+ "BRANCHID='"+flexbomlink.getBranchid()+"' and OUTDATE IS NULL	and DROPPED=0  and DIMENSIONNAME IS NULL "
						;
				
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				query.addScalar("ida2a2",StringType.INSTANCE);

				List<FlexBOMLink> bomLinkList = query.setResultTransformer(Transformers.aliasToBean(FlexBOMLink.class)).list();
				if(bomLinkList!=null && bomLinkList.size()>0)
					return bomLinkList.get(0);
				
				return null;	

    }


	
	private boolean childSkuOverrideExists(String dimensionId, String childSkuMasterID, List<MaterialReportVo> bomData) throws Exception {
		if(bomData != null) {
			Iterator bomDataIter = bomData.iterator();
			MaterialReportVo fob;				
			String dimId = null;
			String compareString = dimensionId.trim() + "-SKU:" + childSkuMasterID.trim();
			while(bomDataIter.hasNext()){
				fob = (MaterialReportVo) bomDataIter.next();				
				dimId = fob.getFlexbomlinkdimensionid();
				if((dimId.trim()).equals(compareString)) {
					return true;
				}
			}	
		}
		return false;		
	}
	
	
	  @SuppressWarnings("unchecked")
	    @Transactional
	    public String getAttributeValue(String objectName, String attributeName, String valueKey) throws Exception {

	        try {
	            logger.info("getAttributeValue Starts");
	            if (valueKey != null && (attributeName.equalsIgnoreCase("FSINTRODATE") )) {
	                Query queryintro = sessionFactory.openSession().createSQLQuery("select sg.att1 as Display from seasongroup sg where sg.latestiterationinfo = 1 and sg.ida3a10 = 14636 and sg.ida3masterreference = '" + valueKey + "'");
	                List intro = queryintro.list();
	                if (intro != null && intro.size() > 0) {
	                    return intro.get(0).toString();
	                }

	            } else if (valueKey != null && (attributeName.equalsIgnoreCase("INTRODATE") )) {
	                Query querydelete = sessionFactory.openSession().createSQLQuery("select sg.att1 as Display from seasongroup sg where sg.latestiterationinfo =1 and sg.ida3a10=14671 and sg.ida3masterreference = '" + valueKey + "'");
	                List deletedate = querydelete.list();
	                if (deletedate != null && deletedate.size() > 0) {
	                    return deletedate.get(0).toString();
	                }
	            } else {

	                logger.info("Object Name ::: " + objectName + " :: Attribut Name " + attributeName + " :: " + valueKey);
	                if (objectName != null && attributeName != null && valueKey != null) {
	                    Query query = sessionFactory
	                            .getCurrentSession()
	                            .createQuery(
	                                    " SELECT attrStore.display  FROM AttributeStoreVo  attrStore WHERE   "
	                                    + "  attrStore.objectName='" + objectName + "'  AND attrStore.attributeName = '" + attributeName.trim() + "' "
	                                    + " AND attrStore.valueKey ='" + valueKey.trim() + "'");

	                    List<String> attributeList = query.list();
	                    logger.info("Attribute Size :::" + attributeList.size());
	                    if (attributeList != null && attributeList.size() > 0) {

	                        java.util.Iterator<String> attrIter = attributeList.iterator();
	                        while (attrIter.hasNext()) {
	                            valueKey = attrIter.next();
	                        }
	                    }
	                }
	            }
	            logger.info("getAttributeValue Ends");
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	        return valueKey;

	    }

	


}
