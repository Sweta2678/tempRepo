package com.coach.middleware.batch.itemWriter;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.*;

 
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coach.middleware.batch.dao.VO.LpSkuSapVO;

 
public class LpSkuSapExtractItemWriter implements ItemWriter<LpSkuSapVO>  {
	
	private static final Logger logger = Logger.getLogger(LpSkuSapExtractItemWriter.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	FlatFileItemWriter<LpSkuSapVO>  LpSkuSapExtractFileItemWriter = null;
	
	int index = 0;
	
	
	@Override
	public void write(List<? extends LpSkuSapVO> items) throws Exception {
		//String INSERT_PRODUCT = "insert into DATAEXTRACTIONTOSAP(STYLE,COLOR,SKUSIZE,REQUESTTYPE,RECDFROMSAPFLAG,SAPCOMPLETEDATE) values (?,?,?,?,?,?)";
		String UPDATE_PRODUCT = "UPDATE DATAEXTRACTIONTOSAP SET WRITTENTOSAPFLAG=?,SENDTOSAPDATE=? WHERE  UNIQUEID =?";
		//System.currentTimeMillis()
		
		if(logger.isDebugEnabled()){
			logger.debug("inside LPSKUSAP Write process ");
		}
		
		
			List<? extends LpSkuSapVO> item = new ArrayList();
			item = (List<? extends LpSkuSapVO>) items.get(0);
			LpSkuSapVO lpSkuSapVO = item.get(index);
			
			
			try{	
			LpSkuSapExtractFileItemWriter.write(item);
			for(LpSkuSapVO iter:item){
				System.out.println(" the seq no is ==>"+iter.getSeqNo());
				/*if(265 == Integer.parseInt(iter.getSeqNo())){
					System.out.println(" throwing exception");
					throw new Exception("Check update process");
				}*/
				int updated = jdbcTemplate
						.update(UPDATE_PRODUCT, "Y",new Timestamp(System.currentTimeMillis()),iter.getSeqNo());
				
				if (updated == 1) {
					logger.info("UniqueId "+iter.getSeqNo()+" updated in DATAEXTRACTIONTOSAP table==>");
				}
			}
			
			}catch(Exception e){
				System.out.println("Exception ==>"+e.toString());
			}
		
			item.clear();
			Runtime.getRuntime().gc();
	
		logger.info("LPSKUSAP object size for writing"+items.size());
		
		if(logger.isDebugEnabled()){
			logger.debug("Exits LPSKUSAP Write process ");
		}
	}
	
	
	public FlatFileItemWriter<LpSkuSapVO> getLpSkuSapExtractFileItemWriter() {
		return LpSkuSapExtractFileItemWriter;
	}

	public void setLpSkuSapExtractFileItemWriter(
			FlatFileItemWriter<LpSkuSapVO> lpSkuSapExtractFileItemWriter) {
		LpSkuSapExtractFileItemWriter = lpSkuSapExtractFileItemWriter;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	
	
		 

	}