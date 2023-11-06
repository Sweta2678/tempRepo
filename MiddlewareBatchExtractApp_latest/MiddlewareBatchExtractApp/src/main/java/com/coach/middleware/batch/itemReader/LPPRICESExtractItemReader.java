package com.coach.middleware.batch.itemReader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;
import com.coach.middleware.batch.itemWriter.LPPRICESExtractItemWriter;

public class LPPRICESExtractItemReader implements ItemReader<LPPRICESExtractVO> {
	private List<LPPRICESExtractVO> LPPRICESExtractVOList = new ArrayList();
	private static ArrayList months = new ArrayList();
	private static ArrayList priceList = new ArrayList();
	@Autowired
	public LPPRICESExtractItemWriter LPPRICESExtractItemWriter;
	
	int index = 0;

	@Override
	public LPPRICESExtractVO read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {LPPRICESExtractVO LPPRICESExtractVO = null;
			System.out.println("Item reader *********");
			try {

				if (LPPRICESExtractVOList.size() == 0) {
					/*LPPRICESExtractVOList = LPPRICESExtractItemWriter
							.getLPPRICESExtractVOList();*/
				}

				if (LPPRICESExtractVOList.size() > 0
						&& LPPRICESExtractVOList.size() != index) {
					LPPRICESExtractVO = (LPPRICESExtractVO) LPPRICESExtractVOList.get(index);
				} else {
					return null;
				}

				index++;
			} catch (Exception e) {
				System.out.println("Excep in LppricesExtractItemReader -->" + e);
				return null;
			}

			return LPPRICESExtractVO;
			}


}
