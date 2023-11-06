package com.coach.middleware.batch.itemWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.coach.middleware.batch.dao.RowMapper.LPPRICESExtractRowMapper;
import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;
import com.coach.middleware.batch.itemProcessor.LPPRICESExtractItemProcessor;

public class LPPRICESExtractItemWriter implements ItemWriter<LPPRICESExtractVO> {
	public List<LPPRICESExtractVO> LPPRICESExtractVOList = new ArrayList<LPPRICESExtractVO>();
	public JdbcBatchItemWriter<LPPRICESExtractVO> LPPRICESExtractDBItemWriter = null;
	public static int rCount =0;
	private static final Logger logger = Logger.getLogger(LPPRICESExtractItemWriter.class);
	@Override
	public void write(List<? extends LPPRICESExtractVO> items) throws Exception {
		try {
			List<? extends LPPRICESExtractVO> lst = (List<? extends LPPRICESExtractVO>) items;
			List<? extends LPPRICESExtractVO> ls = (List<? extends LPPRICESExtractVO>) lst
					.get(0);
			logger.info("Write started :: " + new Date());
			LPPRICESExtractDBItemWriter.write(ls);
			System.out.println("Writer Completed.... " + ls.size() + " at "
					+ new Date());
			logger.info("Write completed :: "+ ls.size() + " at "+ new Date());
			rCount = rCount + ls.size();
			LPPRICESExtractItemProcessor.LPPRICESExtractVOList.clear();
			LPPRICESExtractRowMapper.LppriceVoList.clear();
			LPPRICESExtractRowMapper.styleList.clear();
			LPPRICESExtractItemProcessor.index = 0;
			LPPRICESExtractItemProcessor.fileOutProcessed.close();
			LPPRICESExtractItemProcessor.fileOutRead.close();
		} catch (Exception e) {
			logger.error("Exception in writing records to DB : "+e.getLocalizedMessage());
		}
	}

	public JdbcBatchItemWriter<LPPRICESExtractVO> getLPPRICESExtractDBItemWriter() {
		return LPPRICESExtractDBItemWriter;
	}

	public void setLPPRICESExtractDBItemWriter(
			JdbcBatchItemWriter<LPPRICESExtractVO> LPPRICESExtractDBItemWriter) {
		this.LPPRICESExtractDBItemWriter = LPPRICESExtractDBItemWriter;
	}
}
