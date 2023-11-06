package com.coach.middleware.batch.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ESSSTYLECOLORExtractDaoImpl implements ESSSTYLECOLORExtractDao{
	private static final Logger logger = Logger.getLogger(ESSSTYLECOLORExtractDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void deleteESSSTYLECOLORTable() {
		try{
			jdbcTemplate = new JdbcTemplate(dataSource); 
	 	   	String sql = "truncate table ESSSTYLECOLOR";
	 	    jdbcTemplate.execute(sql);		
	 	   System.out.println("Deleted older contents from ESSSTYLECOLOR");
	 	   logger.info("Deleted older contents from ESSSTYLECOLOR");
		}catch(Exception e){
			logger.error("Exception in truncation ESSSTYLECOLOR table : "+e.getLocalizedMessage());
		}
			
	}

}
