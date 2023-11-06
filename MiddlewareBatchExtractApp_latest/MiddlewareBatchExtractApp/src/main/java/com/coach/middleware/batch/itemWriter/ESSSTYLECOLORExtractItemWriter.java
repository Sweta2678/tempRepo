package com.coach.middleware.batch.itemWriter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.coach.middleware.batch.dao.VO.ESSSTYLECOLORExtractVO;
import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;


public class ESSSTYLECOLORExtractItemWriter  implements ItemWriter<ESSSTYLECOLORExtractVO>{
	public int count =0;
	private static final Logger logger = Logger.getLogger(ESSSTYLECOLORExtractItemWriter.class);
	public JdbcBatchItemWriter<ESSSTYLECOLORExtractVO> ESSSTYLECOLORExtractDBItemWriter = null;
	public JdbcBatchItemWriter<ESSSTYLECOLORExtractVO> getESSSTYLECOLORExtractDBItemWriter() {
		return ESSSTYLECOLORExtractDBItemWriter;
	}
	public void setESSSTYLECOLORExtractDBItemWriter(
			JdbcBatchItemWriter<ESSSTYLECOLORExtractVO> ESSSTYLECOLORExtractDBItemWriter) {
		this.ESSSTYLECOLORExtractDBItemWriter = ESSSTYLECOLORExtractDBItemWriter;
	}
	@Override
	public void write(List<? extends ESSSTYLECOLORExtractVO> items)
			throws Exception {
		//System.out.println("Writing..."+items.size());
		count=count+items.size();
		ESSSTYLECOLORExtractDBItemWriter.write(items);
		logger.info("Writing completed....."+count);
		//System.out.println("Writing completed....."+count);
	}

}
