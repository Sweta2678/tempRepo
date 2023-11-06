package com.coach.middleware.batch.itemWriter;
import java.util.*;

 
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;
 
public class TotoExtractItemWriter implements ItemWriter<TotoExtractVO>  {
	
	private static final Logger logger = Logger.getLogger(TotoExtractItemWriter.class);
	
	FlatFileItemWriter<TotoExtractVO>  TotoExtractFileItemWriter = null;
	
	@Override
	public void write(List<? extends TotoExtractVO> items) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("inside TotoExtract Write process ");
		}
		
		try{
			List<? extends TotoExtractVO> item = new ArrayList();
			item = (List<? extends TotoExtractVO>) items.get(0);
			TotoExtractFileItemWriter.write(item);
			item.clear();
			Runtime.getRuntime().gc();
		}catch(Exception e){
			System.out.println("Exception ==>"+e);
		}
		
		logger.info("TotoExtract object size for writing"+items.size());
		
		if(logger.isDebugEnabled()){
			logger.debug("Exits TotoExtract Write process ");
		}
	}
	
	public FlatFileItemWriter<TotoExtractVO> getTotoExtractFileItemWriter() {
		return TotoExtractFileItemWriter;
	}
	public void setTotoExtractFileItemWriter(
			FlatFileItemWriter<TotoExtractVO> totoExtractFileItemWriter) {
		this.TotoExtractFileItemWriter = totoExtractFileItemWriter;
	}
	
	
	
		 

	}