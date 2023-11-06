package com.coach.middleware.batch.itemWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.coach.middleware.batch.dao.VO.SapMcompLpVO;

public class SapMcompLpItemWriter implements ItemWriter<SapMcompLpVO>{
	
private static final Logger logger = Logger.getLogger(SapMcompLpItemWriter.class);
	
	
	//String INSERT_PRODUCT = "insert into DATAEXTRACTIONTOSAP(STYLE,COLOR,SKUSIZE,REQUESTTYPE,RECDFROMSAPFLAG,SAPCOMPLETEDATE) values (?,?,?,?,?,?)";
	String UPDATE_PRODUCT = "UPDATE DATAEXTRACTIONTOSAP SET RECDFROMSAPFLAG=?,SAPCOMLETEDATE=to_timestamp(?,'MM/dd/yyyy hh:mi:ss am'),STATUSCODE=?,STATUS=? WHERE  UNIQUEID =?";
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	
	private String seqId="";
	private String style="";
	private String skuSize="";
	private String requestType="";
	private String color="";
	private String recdFromSapflag="Y";
	private String sapCompeletDate=""; 
	private String status;
	private String statusCode;
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends SapMcompLpVO> items) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("inside SAPCOMLP Write process ");
		}
		
		
			List<? extends SapMcompLpVO> itemList = new ArrayList();
			itemList = (List<? extends SapMcompLpVO>) items.get(0);
				
			//rpFtpAbcDBItemWriter.write(item);
			
			/*logger.info("RPFTPABC object size for writing"+items.size());
			logger.info("itemList size "+itemList.size());*/
			for (SapMcompLpVO item : itemList) {
				
				
				
				try{
				
					/*
					 *	private String seqId="";
	private String style="";
	private String colorDesc="";
	private String skuSize="";
	private String requestType="";
	private String color="";
	private String recdFromSapflag="Y";
	private String sapCompeletDate=""; 
					 * */
					seqId = item.getSeqId() != null ? item.getSeqId().trim() : item.getSeqId();
					style = item.getStyle() != null ? item.getStyle().trim() : item.getStyle();
					color = item.getColor() != null ? item.getColor().trim() : item.getColor();
					skuSize = item.getSkuSize() != null ? item.getSkuSize().trim() : item.getSkuSize();
					requestType = item.getRequestType() != null ? item.getRequestType().trim() : item.getRequestType();
					recdFromSapflag = "Y";
					sapCompeletDate = item.getSapCompeletDate() != null ? item.getSapCompeletDate().trim() : item.getSapCompeletDate();
					status = item.getStatus()!=null ? item.getStatus().trim() : item.getStatus();
					statusCode = item.getStatusCode()!=null ? item.getStatusCode().trim() : item.getStatusCode();
					SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a"); 
					Date sapCompeletDateObj = null;
					try {
						sapCompeletDateObj = dt.parse(sapCompeletDate);
					} catch (ParseException e) {
						System.out.println("Exception ==>"+e);
						e.printStackTrace();
					} 
					
					
	
					int updated = jdbcTemplate
						.update(UPDATE_PRODUCT, recdFromSapflag,sapCompeletDate,statusCode,status,seqId);
				
				if (updated == 1) {
					logger.info("UniqueId "+item.getSeqId()+" updated in DATAEXTRACTIONTOSAP table==>");
				}
				
				
				}catch(Exception e){
					logger.error(e.toString()+": UniqueId "+seqId+" insert/update failed");
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
