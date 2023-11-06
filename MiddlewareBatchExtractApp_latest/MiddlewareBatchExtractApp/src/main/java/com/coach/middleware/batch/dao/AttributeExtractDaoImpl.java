package com.coach.middleware.batch.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;


import org.apache.log4j.Logger;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.*;


import com.coach.middleware.RunBatch;
import com.coach.middleware.batch.dao.RowMapper.AttributeStoreMapper;
import com.coach.middleware.batch.dao.RowMapper.ColorMasterRowMapper;
import com.coach.middleware.batch.dao.RowMapper.DeltaExtractRowMapper;
import com.coach.middleware.batch.dao.RowMapper.FullPriceMOARowMapper;
import com.coach.middleware.batch.dao.RowMapper.LPSKUExtractRowMapper;
import com.coach.middleware.batch.dao.RowMapper.LPSKUFullPriceMOARowMapper;
import com.coach.middleware.batch.dao.RowMapper.SeasonGroupMasterRowMapper;
import com.coach.middleware.batch.dao.RowMapper.SizeScaleMappingRowMapper;
import com.coach.middleware.batch.dao.RowMapper.SizeScaleMasterRowMapper;
import com.coach.middleware.batch.dao.RowMapper.TotoExtractRowMapper;
import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.ColourCodingVo;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LPSKUFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LpskuExtractVO;
import com.coach.middleware.batch.dao.VO.SeasonGroupMasterVO;
import com.coach.middleware.batch.dao.VO.SizeScaleMappingVO;
import com.coach.middleware.batch.dao.VO.SizeScaleMasterVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;
import com.coach.middleware.batch.itemProcessor.CoachUtil;
import com.coach.middleware.batch.itemProcessor.LPSKUExtractItemProcessor;
import com.sun.org.apache.bcel.internal.generic.LLOAD;

public class AttributeExtractDaoImpl extends JdbcCursorItemReader<LpskuExtractVO> implements AttributeExtractDAO{
	private static final Logger logger = Logger
	.getLogger(AttributeExtractDaoImpl.class);
	
	List<AttributeStoreVO> attributeStoreVOList = new ArrayList<AttributeStoreVO>();
	List<ColourCodingVo> colourCodingVolist = new ArrayList<ColourCodingVo>();
	List<SizeScaleMasterVO> sizeScaleMasterVO = new ArrayList<SizeScaleMasterVO>();
	List<SeasonGroupMasterVO> seasonGroupMasterVO = new ArrayList<SeasonGroupMasterVO>();
	
	List<SizeScaleMappingVO> sizeScakevo = new ArrayList<SizeScaleMappingVO>();
	
	
	List<LPSKUFullPriceMOAVO> fullPriceMOAVO = new ArrayList<LPSKUFullPriceMOAVO>();
	List<String> deltaextractslot = new ArrayList<String>();
	

	public AttributeExtractDaoImpl() {
		super();
	}



	@Autowired
	
	DataSource dataSource;
	RowMapper<TotoExtractVO> lpskuExtractRowMapper;
	
	private JdbcTemplate jdbcTemplate;
	
	String totoExtractSql = null;
	
	
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		//this.dataSource = dataSource;
    }
    
	public DataSource getDataSource() {
		return dataSource;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
	    String SQL= this.getSql();
	    super.setSql(SQL);
	
	}
	
	@Override
	public List<AttributeStoreVO> getTotoExtractAttrKeys(LpskuExtractVO totoExtractVO){
		logger.info("inside LpSKUExtractDaoImpl  getTotoExtractAttrKeys()-->");	
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setFetchSize(RunBatch.fetchsize);
		String sql = "select OBJECTNAME,OBJECTLEVEL,ATTRIBUTENAME,TYPE,VALUEKEY,DISPLAY,DISPLAY2,DISPLAY3,DISPLAY4 from ATTRIBUTESTORE";
		attributeStoreVOList = (List<AttributeStoreVO>) jdbcTemplate.query(sql, new Object[] {}, new AttributeStoreMapper());
		logger.info("Fetched Attribute Store details :: "+attributeStoreVOList.size());	
		return attributeStoreVOList;
	}

	@Override
	public List<ColourCodingVo> getColourCoding(LpskuExtractVO lpskuvo) {
		logger.info("inside LpSKUExtractDaoImpl  getColourCoding()-->");	
		jdbcTemplate = new JdbcTemplate(dataSource); 
		jdbcTemplate.setFetchSize(RunBatch.fetchsize);
		String sql = "select shortcolorname,colortype,uniqueid from COLORMASTER";
		colourCodingVolist = (List<ColourCodingVo>) jdbcTemplate.query(sql, new Object[] {}, new ColorMasterRowMapper());
		logger.info("Fetched colourCodingVolist details :: "+colourCodingVolist.size());	
		
		return colourCodingVolist;
		
	
	}

	@Override
	public List<LPSKUFullPriceMOAVO> getFullPriceMOAList(LpskuExtractVO lpskuvo) {
		logger.info("inside LpSKUExtractDaoImpl  getFullPriceMOAList()-->");	
		jdbcTemplate = new JdbcTemplate(dataSource); 
		jdbcTemplate.setFetchSize(RunBatch.fetchsize);
		String sql = "	SELECT WHOLESALEPRICE,RETAILPRICE,TARGETCOST,TOTALCOST, OWNERUNIQUEID,OWNER FROM MERCHANDISINGFULLPRICETABLE where (startdate <= sysdate and enddate >= sysdate) or (startdate <= sysdate and enddate is null)";		
		fullPriceMOAVO = (List<LPSKUFullPriceMOAVO>) jdbcTemplate.query(sql, new Object[] {}, new LPSKUFullPriceMOARowMapper());
		logger.info("Fetched fullPriceMOAVO details :: "+fullPriceMOAVO.size());	
		return fullPriceMOAVO;
		
	}

	@Override
	public List<SizeScaleMasterVO> getsizescaleMasterList(LpskuExtractVO lpskuvo) {
		logger.info("inside LpSKUExtractDaoImpl  getsizescaleMasterList()-->");	
 		jdbcTemplate = new JdbcTemplate(dataSource); 
 		jdbcTemplate.setFetchSize(RunBatch.fetchsize);
		//String sql = "	SELECT SIZENAME,SKUUNIQUEID,ROWNUMBER FROM SIZESCALETABLE";
 		String sql = "	SELECT SIZENAME,STYLEUNIQUEID,SKUUNIQUEID,VENDORREFNUMBER FROM sizescalemoa";
		
		
		
		sizeScaleMasterVO = (List<SizeScaleMasterVO>) jdbcTemplate.query(sql, new Object[] {}, new SizeScaleMasterRowMapper());
		logger.info("Fetched sizeScaleMasterVO details :: "+sizeScaleMasterVO.size());	
		return sizeScaleMasterVO;
	}

	@Override
	public List<SeasonGroupMasterVO> getSeasonGroupList(LpskuExtractVO lpskuvo) {
		logger.info("inside LpSKUExtractDaoImpl  getSeasonGroupList()-->");	
 		jdbcTemplate = new JdbcTemplate(dataSource); 
 		jdbcTemplate.setFetchSize(RunBatch.fetchsize);
 	  	String sql = "SELECT SEASONGROUPNAME,UNIQUEID FROM SEASONGROUPMASTER";
 	 	seasonGroupMasterVO = (List<SeasonGroupMasterVO>) jdbcTemplate.query(sql, new Object[] {}, new SeasonGroupMasterRowMapper());
 	 	logger.info("Fetched getSeasonGroupList details :: "+seasonGroupMasterVO.size());	
		return seasonGroupMasterVO;
	}
	
	@Override
	public List<SizeScaleMappingVO> getSkizeScaleMapping(LpskuExtractVO lpskuvo) {
		logger.info("inside LpSKUExtractDaoImpl  getSkizeScaleMapping()-->");	
 		jdbcTemplate = new JdbcTemplate(dataSource); 
 		jdbcTemplate.setFetchSize(RunBatch.fetchsize);
 		String sql = "select uniqueid as sizescale,sizelist as sizelist from sizescalemaster";
 	  	sizeScakevo = (List<SizeScaleMappingVO>) jdbcTemplate.query(sql, new Object[] {}, new SizeScaleMappingRowMapper());
 	  	logger.info("Fetched getSkizeScaleMapping details :: "+sizeScakevo.size());	
		return sizeScakevo;
	}
	
	@Override
	public List<String> getDeltaExtractSlots(LpskuExtractVO lpskuvo) {
		logger.info("inside LpSKUExtractDaoImpl  deltaextractslot()-->");	
 		jdbcTemplate = new JdbcTemplate(dataSource); 
 	   	String sql = "SELECT slot  FROM delta_extract";
 	   deltaextractslot = (List<String>) jdbcTemplate.query(sql, new Object[] {},new DeltaExtractRowMapper());
 	  logger.info("Fetched deltaextractslot details :: "+deltaextractslot.size());	
		return deltaextractslot;
	}

	@Override
	public void deleteLPSKUExtractStaging() {
		logger.info("inside LpSKUExtractDaoImpl  deleteLPSKUExtractStaging()-->");	
 		jdbcTemplate = new JdbcTemplate(dataSource); 
 	   	String sql = "truncate table FLEXLPSKUEXTRACT";
 	    jdbcTemplate.update(sql);
		
	}

	
	
	@Override
	public void deleteLPSKUExtractStagingbySlot(List<String> lst)
	{
		logger.info("inside LpSKUExtractDaoImpl  deleteLPSKUExtractStaging()-->");
		StringBuffer buf = new StringBuffer();
		jdbcTemplate = new JdbcTemplate(dataSource); 
		jdbcTemplate.setFetchSize(2000);
		jdbcTemplate = new JdbcTemplate(dataSource); 
		int i =0;
		for(String val : lst){
			
			i++;
			val = com.coach.middleware.util.CoachUtil.padd(10, val);
			buf.append("\'").append(val).append("\',");
			if(i>500){
				String condition = buf.toString().trim();
				condition = condition.substring(0, condition.lastIndexOf(","));
				String sql =  "delete from  FLEXLPSKUEXTRACT where slot in ("+condition+")";
		 		logger.info(sql);
		 	    jdbcTemplate.update(sql);
		 	    buf = new StringBuffer();
		 	    i = 0;
			}
			
		}
		String condition = buf.toString().trim();
		
		condition = condition.substring(0, condition.lastIndexOf(","));
		
 		
		
 	   	String sql = "delete from  FLEXLPSKUEXTRACT where slot in ("+condition+")";
 		//String sql = "delete from  FLEXLPSKUEXTRACT where slot in (SELECT slot  FROM delta_extract)";
 		logger.info(sql);
 	    jdbcTemplate.update(sql);
 	   logger.info("Delta Mode query:: Execution Completed "+sql);
		
	}

	


	

	

}
