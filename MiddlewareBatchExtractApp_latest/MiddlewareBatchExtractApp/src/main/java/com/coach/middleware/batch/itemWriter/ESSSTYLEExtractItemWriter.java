package com.coach.middleware.batch.itemWriter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.coach.middleware.batch.dao.VO.ESSSTYLEExtractVO;
import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;


public class ESSSTYLEExtractItemWriter  implements ItemWriter<ESSSTYLEExtractVO>{
	public int count =0;
	public JdbcBatchItemWriter<ESSSTYLEExtractVO> ESSSTYLEExtractDBItemWriter = null;
	private static final Logger logger = Logger.getLogger(ESSSTYLEExtractItemWriter.class);
	public JdbcBatchItemWriter<ESSSTYLEExtractVO> getESSSTYLEExtractDBItemWriter() {
		return ESSSTYLEExtractDBItemWriter;
	}
	public void setESSSTYLEExtractDBItemWriter(
			JdbcBatchItemWriter<ESSSTYLEExtractVO> ESSSTYLEExtractDBItemWriter) {
		this.ESSSTYLEExtractDBItemWriter = ESSSTYLEExtractDBItemWriter;
	}
	@Override
	public void write(List<? extends ESSSTYLEExtractVO> items)
			throws Exception {
		//System.out.println("Writing..."+items.size());
		count=count+items.size();
		ESSSTYLEExtractDBItemWriter.write(items);
		logger.info("Writing completed....."+count);
		//System.out.println("Writing completed....."+items.size());
	}

}
