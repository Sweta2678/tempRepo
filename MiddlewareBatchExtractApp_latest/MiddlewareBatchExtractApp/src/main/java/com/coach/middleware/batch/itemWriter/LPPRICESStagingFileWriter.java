package com.coach.middleware.batch.itemWriter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;
import com.coach.middleware.batch.dao.VO.LPPRICESStagingExtractVo;
import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;

public class LPPRICESStagingFileWriter implements
		ItemWriter<LPPRICESStagingExtractVo> {
	private static final Logger logger = Logger
			.getLogger(LPPRICESStagingFileWriter.class);
	public static int index = 0;
	FlatFileItemWriter<LPPRICESStagingExtractVo> LPPRICESExtractFileItemWriter = null;

	@Override
	@SuppressWarnings("unchecked")
	public void write(List<? extends LPPRICESStagingExtractVo> items)
			throws Exception {
		try {
			List<LPPRICESStagingExtractVo> lst = new ArrayList<LPPRICESStagingExtractVo>(
					items);
			if (index == 0) {
				logger.info(" ** Write to file process ** " + lst.size());
				LPPRICESStagingExtractVo vo = new LPPRICESStagingExtractVo();
				vo.setESSSKU("Product");
				vo.setCENTURY("Year");
				vo.setPERIOD("Time");
				vo.setRETAILPRICE("RtlPrice");
				vo.setWHPRICE("WHPrice");
				vo.setJPY("RtlYen");
				lst.add(0, vo);
				index = 1;
			}
			LPPRICESExtractFileItemWriter.write(lst);
		} catch (Exception e) {
			logger.error("Exception ==>" + e);
		}
	}

	public FlatFileItemWriter<LPPRICESStagingExtractVo> getLPPRICESExtractFileItemWriter() {
		return LPPRICESExtractFileItemWriter;
	}

	public void setLPPRICESExtractFileItemWriter(
			FlatFileItemWriter<LPPRICESStagingExtractVo> lPPRICESExtractFileItemWriter) {
		this.LPPRICESExtractFileItemWriter = lPPRICESExtractFileItemWriter;
	}

}
