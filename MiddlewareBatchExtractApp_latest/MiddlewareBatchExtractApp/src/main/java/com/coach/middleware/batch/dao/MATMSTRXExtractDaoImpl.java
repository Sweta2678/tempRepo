package com.coach.middleware.batch.dao;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.coach.middleware.batch.dao.RowMapper.FullPriceMOARowMapper;
import com.coach.middleware.batch.dao.RowMapper.LPPRICESExtractRowMapper;
import com.coach.middleware.batch.dao.RowMapper.LookupRowMapper;
import com.coach.middleware.batch.dao.RowMapper.MATMSTRXExtractRowMapper;
import com.coach.middleware.batch.dao.RowMapper.MaterialPriceRowMapper;
import com.coach.middleware.batch.dao.RowMapper.SequenceRowMapper;
import com.coach.middleware.batch.dao.RowMapper.VariantLookupRowMapper;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LogTabVO;
import com.coach.middleware.batch.dao.VO.LookupVo;
import com.coach.middleware.batch.dao.VO.MaterialPriceVo;
import com.coach.middleware.batch.dao.VO.VariantLookUpVo;
import com.coach.middleware.batch.itemProcessor.MATMSTRXExtractItemProcessor;
public class MATMSTRXExtractDaoImpl implements MATMSTRXExtractDao{
	DataSource dataSource;
	private static final Logger logger = Logger.getLogger(MATMSTRXExtractDaoImpl.class);
	List<LookupVo> lookupVo = new ArrayList<LookupVo>();
	List<VariantLookUpVo> VariantLookUpVo = new ArrayList<VariantLookUpVo>();
	List<MaterialPriceVo> MaterialPriceVoList = new ArrayList<MaterialPriceVo>();
	HashMap<String,LookupVo> lookupVoMap = new HashMap<String,LookupVo>();
	private JdbcTemplate jdbcTemplate;
	private int seq=0;
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public List<LookupVo> getAttributeFromLookups() {
		try{
			jdbcTemplate = new JdbcTemplate(dataSource); 
			logger.info("Getting display from AttributeStore : "+new Date());
			String sql = "select valuekey VALUEKEY,display DISPLAY,attributename ATTRIBUTENAME,materialtype MATERIALTYPE from MATERIALATTRIBUTESTORE where valuekey!= ' '";
			logger.info("Query to read AttributeStore :  "+sql);
			lookupVo = (List<LookupVo>) jdbcTemplate.query(sql, new Object[] {}, new LookupRowMapper());
			logger.info("Query to read AttributeStore completed : "+new Date() +" No of Rows : "+lookupVo.size());
			}catch(Exception e){
				logger.error("Error in AttributeStore read :" + e.getLocalizedMessage());
				return null;
			}
			return lookupVo;
	}
	@Override
	public List<VariantLookUpVo> getVariantFromLookups() {
		try{
			jdbcTemplate = new JdbcTemplate(dataSource); 
			logger.info("Getting Variant lookup query started : "+new Date());
			String sql = "select variantkey VARIANTKEY,variant VARIANT,materialtype MATERIALTYPE from VARIANTMASTER where VARIANTKEY!= ' '";
			logger.info("Query to read Variant :"+sql);
			VariantLookUpVo = (List<VariantLookUpVo>) jdbcTemplate.query(sql, new Object[] {}, new VariantLookupRowMapper());
			logger.info("Getting Variant query completed : "+new Date() +" No of Rows : "+lookupVo.size());
			}catch(Exception e){
				logger.error("Error in variant lookup " + e.getLocalizedMessage());
				return null;
			}
			return VariantLookUpVo;
	}
	public HashMap<String,MaterialPriceVo> getStandardCost(List<String> uniqueIDs) {
		try{
			jdbcTemplate = new JdbcTemplate(dataSource); 
			logger.info("Getting stdcost query started : "+new Date());
			String sql = "select uniqueID UNIQUEID,to_char(fromdate,'dd/mm/yyyy') FROMDATE,matsupplierid MATERIALSUPPID,matcolorid MATERIALCOLORID,price PRICE from materialpricing where todate is null  and matsupplierid in ("+StringUtils.collectionToCommaDelimitedString(uniqueIDs)+") and pricetype like 'Standard'";
			logger.info("Sql to get price from material pricing :"+sql);
			MaterialPriceVoList = (List<MaterialPriceVo>) jdbcTemplate.query(sql, new Object[] {}, new MaterialPriceRowMapper());
			logger.info("Getting material price query completed : "+new Date() +" generated MOA : "+MaterialPriceVoList.size());
			}catch(Exception e){
				logger.error("Error in material price " + e.getLocalizedMessage());
				return null;
			}
			return organizePriceForUniqueID();
	}

	@Override
	public String getAttributeLookups(String subType,String AttributeName,String materialType) {
		Iterator it = lookupVo.iterator();
		String returnStr=subType;
		while(it.hasNext()){
			LookupVo vo = (LookupVo) it.next();
			String key = vo.getVALUEKEY();
			String voAttributeName = vo.getATTRIBUTENAME();
			String value = vo.getDISPLAY();
			String voMaterialType = vo.getMATERIALTYPE();
			if(key.equalsIgnoreCase(subType) && voAttributeName.equalsIgnoreCase(AttributeName) && voMaterialType.equalsIgnoreCase(materialType) ){
				returnStr=value;
				return returnStr;
			}
		}
		return returnStr;
	}
	@Override
	public String getStatusLookups(String key,String attributeName) {
		Iterator it = lookupVo.iterator();
		String returnStr=key;
		while(it.hasNext()){
			LookupVo vo = (LookupVo) it.next();
			String vokey = vo.getVALUEKEY();
			String voAttributeName = vo.getATTRIBUTENAME();
			String value = vo.getDISPLAY();
			if(vokey.equalsIgnoreCase(key) && voAttributeName.equalsIgnoreCase(attributeName)){
				returnStr=value;
				return returnStr;
			}
		}
		return returnStr;
	}
	@Override
	public String getVariantLookups(String variantKey,String materialType) {
		Iterator it = VariantLookUpVo.iterator();
		String returnStr=variantKey;
		while(it.hasNext()){
			VariantLookUpVo vo = (VariantLookUpVo) it.next();
			String materialTypeVo = vo.getMateruialType();
			String value = vo.getValue();
			String voKey = vo.getKey();
			if(voKey.equalsIgnoreCase(variantKey) && materialTypeVo.equalsIgnoreCase(materialType)){
				//logger.info("variant Key :"+variantKey + " Value :"+value );
				returnStr=value;
				return returnStr;
			}
		}
		return returnStr;
	}

	private HashMap<String,MaterialPriceVo> organizePriceForUniqueID() {
		HashMap<String,MaterialPriceVo> uniqueIdPrice = new HashMap<String,MaterialPriceVo>();
		try {
			logger.info("Processing MaterialPricing started : " + new Date());
			List<String> uniqueIDList = MATMSTRXExtractRowMapper.materialUniqueIDList;
			for (String uniqueID : uniqueIDList) {
				for (MaterialPriceVo vo : MaterialPriceVoList) {
					if (uniqueID.equals(vo.getMatsupplierid().toString())) {
						uniqueIdPrice.put(uniqueID, vo);
					}
				}
			}
			MATMSTRXExtractRowMapper.materialUniqueIDList.clear();
			
		} catch (Exception e) {
			logger.error("Exception in parsing the MOA list in organizePriceForUniqueID :"+e.getLocalizedMessage());
		}
	return uniqueIdPrice;
	}
}



