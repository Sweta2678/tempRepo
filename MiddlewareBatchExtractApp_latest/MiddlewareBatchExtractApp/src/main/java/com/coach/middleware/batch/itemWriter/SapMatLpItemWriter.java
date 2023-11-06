package com.coach.middleware.batch.itemWriter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coach.middleware.batch.dao.VO.SapMatLpVO;
import com.coach.middleware.extractloader.SAPMATLPExtract;

public class SapMatLpItemWriter implements ItemWriter<SapMatLpVO>{
	
private static final Logger logger = Logger.getLogger(SapMatLpItemWriter.class);
	
	JdbcBatchItemWriter<SapMatLpVO>  sapMatLpDBItemWriter = null;

	String INSERT_PRODUCT = "insert into SAPMATERIALINFO(MATNUM,MATDESC,UPC,STDCOST,RETPRICE,WHPRICE,FSTICKETPRICE,QUALITY,STYLE,COLOR) values (?,?,?,?,?,?,?,?,?,?)";
	String UPDATE_PRODUCT = "UPDATE SAPMATERIALINFO SET MATDESC=?,UPC=?,STDCOST=?,RETPRICE=?,WHPRICE=?,FSTICKETPRICE=?,QUALITY=?,STYLE=?,COLOR=? WHERE  MATNUM =?";
	
	/*String INSERT_PRODUCT = "insert into PMDB_RPFTPABC(STYLECOLOR,ABC,MPG,STYLE,COLOR) values (?,?,?,?,?)";
	String UPDATE_PRODUCT = "UPDATE PMDB_RPFTPABC SET ABC=?, MPG=?, STYLECOLOR=? WHERE COLOR =? and STYLE = ?";
*/
	public SAPMATLPExtract extract = new SAPMATLPExtract();
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	
	private String matNum = "";
	private String matDesc = "";
	private String upc = "";
	private String stdCost = "";
	private String retailPrice = "";
	private String whPrice = "";
	private String fsTicketPrice = "";
	private String quality = "";
	private String style = "";
	private String color = "";
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends SapMatLpVO> items) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("inside SAPMATLP Write process ");
		}
		
		
			List<? extends SapMatLpVO> itemList = new ArrayList();
			itemList = (List<? extends SapMatLpVO>) items.get(0);
				
			//rpFtpAbcDBItemWriter.write(item);
			
			/*logger.info("RPFTPABC object size for writing"+items.size());
			logger.info("itemList size "+itemList.size());*/
			for (SapMatLpVO item : itemList) {
				
				
				
				try{
				
					/*
					 * upc = "";
	private String stdCost = "";
	private String retailPrice = "";
	private String whPrice = "";
	private String fsTicketPrice = ""
	private String quality = "";
	private String style = "";
	private String color = "";;
					 * */
					matNum = item.getMatNum() != null ? item.getMatNum().trim() : item.getMatNum();
					matDesc = item.getMatDesc() != null ? item.getMatDesc().trim() : item.getMatDesc();
					upc = item.getUpc() != null ? item.getUpc().trim() : item.getUpc();
					stdCost = item.getStdCost() != null ? item.getStdCost().trim() : item.getStdCost();
					retailPrice = item.getRetailPrice() != null ? item.getRetailPrice().trim() : item.getRetailPrice();
					whPrice = item.getWhPrice() != null ? item.getWhPrice().trim() : item.getWhPrice();
					fsTicketPrice = item.getFsTicketPrice() != null ? item.getFsTicketPrice().trim() : item.getFsTicketPrice();
					quality = item.getQuality() != null ? item.getQuality().trim() : item.getQuality();
					style = item.getStyle() != null ? item.getStyle().trim() : item.getStyle();
					color = item.getColor() != null ? item.getColor().trim() : item.getColor();
					
					
				int updated = jdbcTemplate
						.update(UPDATE_PRODUCT, matDesc, upc,
								stdCost,retailPrice,whPrice,fsTicketPrice,quality,style,color,matNum);
				
				if (updated == 1) {
					logger.info("Style "+item.getMatNum()+" updated in SAPMATERIALINFO table==>");
				}
				
				/*logger.info("item  getSkuColumn"+item.getSkuColumn());
				logger.info("item  getSkuName"+item.getSkuName());*/
				
				
				if (updated == 0) {
					jdbcTemplate.update(INSERT_PRODUCT, matNum,matDesc, upc,
							stdCost,retailPrice,whPrice,fsTicketPrice,quality,style,color);
					
					logger.info("Style "+item.getMatNum()+" inserted to RPFTPABC table==>");
				}
				}catch(Exception e){
					logger.error(e.toString()+": Style "+matNum+" insert/update failed");
					//throw new Exception("Sku "+sku+" insert/update failed");
					SAPMATLPExtract.successFlag=false;
				}
				
				
			}
			itemList.clear();
			Runtime.getRuntime().gc();
		
		
		
		
		if(logger.isDebugEnabled()){
			logger.debug("Exits RPFTPABC Write process ");
		}
	}




	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	

	

}
