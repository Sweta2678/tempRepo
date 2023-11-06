package com.coach.middleware.batch.itemWriter;

import java.util.List;

import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.coach.middleware.batch.dao.VO.LpskuExtractVO;

public class writerthread extends Thread{
	JdbcBatchItemWriter<LpskuExtractVO> LPSKUExtractDBItemWriter = null;
	List<? extends LpskuExtractVO> ls = null;
	writerthread(JdbcBatchItemWriter<LpskuExtractVO> LPSKUExtractDBItemWriter,List<? extends LpskuExtractVO> ls){
		this.LPSKUExtractDBItemWriter = LPSKUExtractDBItemWriter;
		this.ls = ls;
		
	}
	public void run(){
		try {
			System.out.println("Thread Started :: "+ls.size());
			LPSKUExtractDBItemWriter.write(ls);
			System.out.println("Thread Ended :: ");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error Count  :: "+ls.size());
		}
		ls.clear();
		LPSKUExtractItemWriter.threadcount--;
		Runtime.getRuntime().gc();
	}

}
