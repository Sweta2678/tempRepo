package com.coach.middleware.batch.dao;


import java.util.*;

import javax.sql.DataSource;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.coach.middleware.batch.dao.RowMapper.AttributeStoreMapper;
import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public class TotoExtractDaoImpl implements TotoExtractDAO{
	
	private static final Logger logger = Logger
			.getLogger(AttributeExtractDaoImpl.class);

	@Autowired
	DataSource dataSource;
	
	
	private JdbcTemplate jdbcTemplate;
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	List<AttributeStoreVO> attributeStoreVOList = new ArrayList();
	
	
	@Override
	public List<AttributeStoreVO> getTotoExtractAttrKeys(TotoExtractVO totoExtractVO){
		
		
		logger.debug("inside TotoExtractDaoImpl  getTotoExtractAttrKeys()-->");	
		
		jdbcTemplate = new JdbcTemplate(dataSource); 
		
		jdbcTemplate.setFetchSize(1000);
		
		
		String sql = "select ats.display,ats.display3,ats.display4,ats.attributename,ats.display2,ats.valuekey from ATTRIBUTESTORE ats" +
				" where Upper(ats.objectname)='PRODUCT' and UPPER(ats.objectlevel)='PRODUCT' and UPPER(ats.attributename)='DEPARTMENT'" +
				" or UPPER(ats.attributename)='STYLECLASS' or UPPER(ats.attributename)='SUBCLASS' or UPPER(ats.attributename)='TOTOCOUNTRYOFORIGIN'" +
				" or UPPER(ats.attributename)='SIZES' or UPPER(ats.attributename)='GENDER' or UPPER(ats.attributename)='SILHOUETTE'" +
				" or UPPER(ats.attributename)='COLLECTION' or UPPER(ats.attributename)='JEWELRYDIMENSIONS' or UPPER(ats.attributename)='KEYRINGDIMENSIONS'" +
				" or UPPER(ats.attributename)='ORGANIZATIONALPANEL' or UPPER(ats.attributename)='TOTOINTERIORPOCKET' or UPPER(ats.attributename)='CLOSURE'" +
				" or UPPER(ats.attributename)='CREDITCARDPOCKET' or UPPER(ats.attributename)='TOTOBILLFOLDPOCKET' or UPPER(ats.attributename)='IDWINDOW'" +
				" or UPPER(ats.attributename)='COIN' or UPPER(ats.attributename)='LEATHERMOISTURIZER' or UPPER(ats.attributename)='FABRICCLEANER'" +
				" or UPPER(ats.attributename)='DAMPCLOTH' or UPPER(ats.attributename)='TOTOTYPEOFSOLE'";

		
		attributeStoreVOList = (List<AttributeStoreVO>) jdbcTemplate.query(sql, new Object[] {}, new AttributeStoreMapper());
		
		return attributeStoreVOList;
	}


}
