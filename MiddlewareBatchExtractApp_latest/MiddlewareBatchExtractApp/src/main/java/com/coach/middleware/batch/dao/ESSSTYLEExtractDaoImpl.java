package com.coach.middleware.batch.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ESSSTYLEExtractDaoImpl implements ESSSTYLEExtractDao{
	private static final Logger logger = Logger.getLogger(ESSSTYLEExtractDaoImpl.class);
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
	public void deleteESSSTYLETable() {
		try{
			jdbcTemplate = new JdbcTemplate(dataSource); 
	 	   	String sql = "truncate table ESSSTYLE";
	 	    jdbcTemplate.execute(sql);		
	 	   System.out.println("Deleted older contents from ESSSTYLE");
	 	   logger.info("Deleted older contents from ESSSTYLE");
		}catch(Exception e){
			logger.error("Exception in truncation ESSSTYLE table : "+e.getLocalizedMessage());
		}
			
	}
}
