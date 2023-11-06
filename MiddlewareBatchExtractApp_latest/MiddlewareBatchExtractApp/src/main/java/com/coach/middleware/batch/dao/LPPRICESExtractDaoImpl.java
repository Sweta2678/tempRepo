package com.coach.middleware.batch.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.coach.middleware.batch.dao.RowMapper.CjiPriceMOARowMapper;
import com.coach.middleware.batch.dao.RowMapper.FullPriceMOARowMapper;
import com.coach.middleware.batch.dao.RowMapper.LPPRICESExtractRowMapper;
import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.CjiPriceMOAVO;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;
import com.coach.middleware.batch.itemProcessor.LPPRICESExtractItemProcessor;

public class LPPRICESExtractDaoImpl implements LPPRICESExtractDao {
	private static final Logger logger = Logger.getLogger(LPPRICESExtractDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;
	List<FullPriceMOAVO> lpPricesMOAVO = new ArrayList<FullPriceMOAVO>();
	List<CjiPriceMOAVO> lpPricesCjiMOAVO = new ArrayList<CjiPriceMOAVO>();
	List<LPPRICESExtractVO> StyleList = new ArrayList<LPPRICESExtractVO>();
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<FullPriceMOAVO> getFullPriceMOAList(List<String> ownerIds) {
		try{
		jdbcTemplate = new JdbcTemplate(dataSource); 
		logger.info("Getting PriceMOA query started : "+new Date());
		logger.info("No of unique Styles passed to get MOA:"+ownerIds.size() );
		String sql = "select MERCHANDISINGFULLPRICETABLE.STARTDATE as STARTDATE,MERCHANDISINGFULLPRICETABLE.ENDDATE as ENDDATE,MERCHANDISINGFULLPRICETABLE.OWNER as OWNER,MERCHANDISINGFULLPRICETABLE.OWNERUNIQUEID as OWNERUNIQUEID,MERCHANDISINGFULLPRICETABLE.TARGETCOST as TARGETCOST,MERCHANDISINGFULLPRICETABLE.WHOLESALEPRICE as WHOLESALEPRICE,MERCHANDISINGFULLPRICETABLE.RETAILPRICE as RETAILPRICE from MERCHANDISINGFULLPRICETABLE MERCHANDISINGFULLPRICETABLE where MERCHANDISINGFULLPRICETABLE.OWNERUNIQUEID in ("+StringUtils.collectionToCommaDelimitedString(ownerIds)+") order by OWNERUNIQUEID,STARTDATE asc";
		logger.info("sql for LPPRICES MOA:"+sql);
		lpPricesMOAVO = (List<FullPriceMOAVO>) jdbcTemplate.query(sql, new Object[] {}, new FullPriceMOARowMapper());
		logger.info("Getting PriceMOA query completed : "+new Date() +" generated MOA : "+lpPricesMOAVO.size());
		}catch(Exception e){
			logger.error("Error in getting the MOA records from MERCHANDISINGFULLPRICETABLE table : "+e.getLocalizedMessage());
			return null;
		}
		return lpPricesMOAVO;
	}
	@Override
	public List<CjiPriceMOAVO> getCjiPriceMOAList(List<String> ownerIds) {
		try{
		jdbcTemplate = new JdbcTemplate(dataSource); 
		logger.info("Getting CJI PriceMOA query started : "+new Date());
		logger.info("No of unique Styles passed to get CJI MOA:"+ownerIds.size() );
		String sql = "select MERCHANDISINGCJIPRICETABLE.STARTDATE as CJISTARTDATE,MERCHANDISINGCJIPRICETABLE.ENDDATE as CJIENDDATE,MERCHANDISINGCJIPRICETABLE.OWNER as OWNER,MERCHANDISINGCJIPRICETABLE.OWNERUNIQUEID as OWNERUNIQUEID ,MERCHANDISINGCJIPRICETABLE.CJIJPY as CJIPRICE from MERCHANDISINGCJIPRICETABLE MERCHANDISINGCJIPRICETABLE where MERCHANDISINGCJIPRICETABLE.OWNERUNIQUEID in  ("+StringUtils.collectionToCommaDelimitedString(ownerIds)+") order by OWNERUNIQUEID,STARTDATE asc";
		logger.info("sql for LPPRICES CJI MOA:"+sql);
		lpPricesCjiMOAVO = (List<CjiPriceMOAVO>) jdbcTemplate.query(sql, new Object[] {}, new CjiPriceMOARowMapper());
		logger.info("Getting CJI PriceMOA query completed : "+new Date() +" generated CJI MOA : "+lpPricesCjiMOAVO.size());
		}catch(Exception e){
			logger.error("Error in getting the MOA records from MERCHANDISINGFULLPRICETABLE table : "+e.getLocalizedMessage());
			return null;
		}
		return lpPricesCjiMOAVO;
	}	@Override
	public void deleteLPPRICESTable() {
		try{
			jdbcTemplate = new JdbcTemplate(dataSource); 
	 	   	String sql = "truncate table LPPRICES";
	 	    jdbcTemplate.execute(sql);		
	 	   logger.info("Deleted older contents from LPPRICES");
		}catch(Exception e){
			logger.error("Exception in truncation LPPRICES table : "+e.getLocalizedMessage());
		}
	}

	
}
