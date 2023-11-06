package com.coach.middleware.batch.itemWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;


import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;

public class RpFtpAbcItemWriter implements ItemWriter<RpFtpAbcVO>{
	
private static final Logger logger = Logger.getLogger(RpFtpAbcItemWriter.class);
	
	
	private JdbcBatchItemWriter<RpFtpAbcVO>  rpFtpAbcDBItemWriter = null;

	String INSERT_PRODUCT = "insert into RPFTPABC(SKU,ABCCODE,MPG,STYLE,SKUNAME) values ";
	String UPDATE_PRODUCT = "UPDATE RPFTPABC SET ABCCODE=?, MPG=?, SKU = ? WHERE  SKUNAME =? and STYLE=? ";
	String DELETE_PRODUCT = "DELETE FROM RPFTPABC WHERE  ";
	String insertval[] = null;
	public static int count=0;
	List<? extends RpFtpAbcVO> itemList = new ArrayList();
	String sku = null;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends RpFtpAbcVO> items) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("*************Inside RPFTPABC Write process : "+new Date());
		}
		try{
			itemList = (List<? extends RpFtpAbcVO>) items.get(0);
			String deletequery = "";
			for (RpFtpAbcVO item : itemList) {
				if(item.getMpg().length()>10){
					item.setMpg(item.getMpg().substring(0, 10));
				}
				sku = item.getSkuColumn().trim();
				if(sku.length()>16){
					sku = sku.substring(0,16);
					item.setSkuColumn(sku);
				}
				if(item.getAbcCode().trim().length()>10){
					item.setAbcCode(item.getAbcCode().substring(0,10));
				}
				if(item.getMpg().trim().length()>10){
					item.setMpg(item.getMpg().substring(0,10));
				}
				if(item.getStyleNumber().trim().length()>20){
					item.setStyleNumber(item.getStyleNumber().substring(0,20));
				}
				if(item.getSkuName().trim().length()>20){
					item.setSkuName(item.getSkuName().substring(0,20));
				}
				
				deletequery =  deletequery + " (SKUNAME = '"+item.getSkuName()+"' and STYLE= '"+item.getStyleNumber()+"' ) or ";
			}
			
			deletequery = DELETE_PRODUCT + deletequery.substring(0,deletequery.length()-3);
			
			String del[] = new String[1];
			del[0] = deletequery;
			logger.info("Delete started on RPFTPABC table "+deletequery);
			jdbcTemplate.batchUpdate(del);
			rpFtpAbcDBItemWriter.write(itemList);
			count = count+ itemList.size();
			logger.info("Insert completed on RPFTPABC table : "+count);
			itemList.clear();
			Runtime.getRuntime().gc();
		if(logger.isDebugEnabled()){
			logger.debug("**************Exits RPFTPABC Write process "+new Date());
		}
		}catch(Exception e){
			logger.error(e.toString()+": Sku "+sku+" insert/update failed "+e.getLocalizedMessage());
			logger.error(" Insert Failed :  "+e.getMessage());
			itemList.clear();
			Runtime.getRuntime().gc();
		}
	}


	public JdbcBatchItemWriter<RpFtpAbcVO> getRpFtpAbcDBItemWriter() {
		return rpFtpAbcDBItemWriter;
	}

	public void setRpFtpAbcDBItemWriter(
			JdbcBatchItemWriter<RpFtpAbcVO> rpFtpAbcDBItemWriter) {
		this.rpFtpAbcDBItemWriter = rpFtpAbcDBItemWriter;
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	

	

}
