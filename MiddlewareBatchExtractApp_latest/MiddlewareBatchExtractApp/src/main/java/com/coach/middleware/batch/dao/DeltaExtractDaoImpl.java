package com.coach.middleware.batch.dao;


import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.sql.DataSource;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class DeltaExtractDaoImpl implements DeltaExtractDAO{
	
	private static final Logger logger = Logger
			.getLogger(DeltaExtractDaoImpl.class);

	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Connection connection = null;
	public static int insert = 0;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	//private Timestamp deltaDate = null;
	
	String INSERT_DELTA = "INSERT INTO DELTA_EXTRACT" +
			" select DISTINCT stylenumber, slot from productmaster where uniqueid in (" + 
			"select object_key from dbinput_events where UPPER(object_name)='LCSPRODUCT' and to_timestamp(object_trandate) > to_timestamp(?, 'YYYY/MM/DD HH:MI:SS AM') " +
			"UNION " +
			"select to_char(sku.styleuniqueid) from skumaster sku, dbinput_events db where db.object_key = sku.uniqueid " +
			"and Upper(db.object_name)='LCSSKU' and to_timestamp(object_trandate) > to_timestamp(?, 'YYYY/MM/DD HH:MI:SS AM'))";
			
	
	String TRUNC_DELTA = "truncate table DELTA_EXTRACT";
	
	String DELETE_DELTA = "Delete from DELTA_EXTRACT where slot is null";
	
	String  deltaDateQuery = "select decode(to_char(max(start_time),'YYYY/MM/DD HH:MI:SS AM'),null,to_char('8-MAR-15','YYYY/MM/DD HH:MI:SS AM'),to_char(max(start_time),'YYYY/MM/DD HH:MI:SS AM')) DeltaDate " +
			 "from BATCH_JOB_EXECUTION where job_instance_id in (select distinct job_instance_id " +
			 "from batch_job_instance where job_name='DeltaExtractJob') and status='COMPLETED' ";
	
	@Override
	public void populateDeltaExtract() {

		logger.debug("Inside populateDeltaExtract");
		Date deltaDateObj = null;
		String deltaDateStr = null;
		
		
		try {
			String deltaDate = jdbcTemplate.queryForObject(deltaDateQuery,
					new Object[] {}, String.class);
			
				logger.debug("deltaDate before format "+ deltaDate + " ");
				logger.debug("DELTA_EXTRACT will be populated with records Since "
						+ deltaDate + " ");
				
				//SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); 
				/*SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
				SimpleDateFormat dtNew = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
				
				deltaDateObj = dt.parse(deltaDate);
				deltaDateStr = dtNew.format(deltaDateObj);*/
				insert = (int) jdbcTemplate.update(INSERT_DELTA, deltaDate,deltaDate);
			
		} catch (Exception e) {
			logger.debug("records not inserted to DELTA_EXTRACT table "+e);
			e.printStackTrace();
		}

		if (insert > 0) {
			logger.debug(" " + insert
					+ " record(s) inserted to DELTA_EXTRACT table");
		}

		

	}	

	@Override
	public void truncateDeltaExtract() {

		try {
			jdbcTemplate.execute(TRUNC_DELTA);
			logger.debug("DELTA_EXTRACT table truncated");
		} catch (Exception e) {
			logger.debug("DELTA_EXTRAcT table truncate - Failed");
			e.printStackTrace();
		}

	}
	
	
	public void deleteDeltaExtract(){
		
		int delete = 0;

		try {
			delete = jdbcTemplate.update(DELETE_DELTA);

		} catch (Exception e) {
			logger.debug("No records deleted from Delta_Extract table");
			e.printStackTrace();
		}

		if (delete > 0) {
			logger.debug(" " + delete
					+ " records deleted from Delta_Extract table with slot is null");
		}
		
	}
	
			
		
	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}



}
