package com.coach.middleware.batch.itemProcessor;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;
import com.coach.middleware.batch.dto.RpFtpAbcDTO;



public class RpFtpAbcItemProcessor implements ItemProcessor<RpFtpAbcDTO,List<RpFtpAbcVO>> {
	
	private String styleNumToProcess = null;
	private static final Logger logger = Logger
			.getLogger(RpFtpAbcItemProcessor.class);
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.skippedRecordsPath']}")
	private String skippedFilePath;
	//@Value("#{nightlyOutboundProps['com.coach.middleware.prodmast.skippedRecordsFileName']}")
	private String skippedFile;
	public static PrintWriter recordsSkipped = null;
	boolean fetchAttrStoreDet = true;
	List<RpFtpAbcVO> rpFtpAbcList = new ArrayList();
	@Override
	public List<RpFtpAbcVO> process(RpFtpAbcDTO rpFtpAbcDTO) throws Exception {
		if(fetchAttrStoreDet){
			String file = skippedFilePath+skippedFile;
		recordsSkipped = new PrintWriter(new BufferedWriter(new FileWriter(
				file, true)));
		fetchAttrStoreDet=false;
		}
		// TODO Auto-generated method stub
		//logger.debug("Inside rftpabc process==>"+rpFtpAbcDTO.getSkuColumn());
		RpFtpAbcVO rpFtpAbcVO = new RpFtpAbcVO();
		styleNumToProcess = rpFtpAbcDTO.getStyleNumber();
		try{
		if(!isNullOrEmpty(rpFtpAbcDTO.getAbcCode()))
			rpFtpAbcVO.setAbcCode(rpFtpAbcDTO.getAbcCode());
		else
			rpFtpAbcVO.setAbcCode("");
		
		if(!isNullOrEmpty(rpFtpAbcDTO.getMpg()))
			rpFtpAbcVO.setMpg(rpFtpAbcDTO.getMpg());
		else
			rpFtpAbcVO.setMpg("");
		
		if(!isNullOrEmpty(rpFtpAbcDTO.getSkuColumn()))
		{
			String tmpStr = rpFtpAbcDTO.getSkuColumn();
			if(tmpStr.length() < 11){
				//logger.debug("getSkuColumn before==>"+tmpStr.length());
				for(int i=tmpStr.length();i<11;i++){
					tmpStr = tmpStr+" ";
				}
				//logger.debug("getSkuColumn after==>"+tmpStr.length());
			}
			rpFtpAbcVO.setSkuColumn(tmpStr);
		}else{
			rpFtpAbcVO.setSkuColumn(null);
		}
		
		if(!isNullOrEmpty(rpFtpAbcVO.getSkuColumn()))
			rpFtpAbcVO.setStyleNumber(rpFtpAbcVO.getSkuColumn().substring(0, 6).trim());
		else
			rpFtpAbcVO.setStyleNumber("");
		
		if(!isNullOrEmpty(rpFtpAbcVO.getSkuColumn()))
			rpFtpAbcVO.setSkuName(rpFtpAbcVO.getSkuColumn().substring(6,11).trim());
		else
			rpFtpAbcVO.setSkuName("");
		
		if(!rpFtpAbcVO.getStyleNumber().equalsIgnoreCase("000000") && !rpFtpAbcVO.getStyleNumber().equalsIgnoreCase("000")){
			String sku = rpFtpAbcVO.getSkuColumn().trim();
			if(sku.length()>16 || rpFtpAbcVO.getAbcCode().trim().length()>10  || rpFtpAbcVO.getMpg().trim().length()>10 ||  rpFtpAbcVO.getStyleNumber().trim().length()>20 || rpFtpAbcVO.getSkuName().trim().length()>20){
				recordsSkipped.println(sku +" "+rpFtpAbcVO.getAbcCode() +" "+rpFtpAbcVO.getMpg()+" "+rpFtpAbcVO.getStyleNumber()+" "+rpFtpAbcVO.getSkuName());
			}
			else{
			rpFtpAbcList.add(rpFtpAbcVO);
			}
		}else{
			//logger.debug("getStyleNumber is not valid , skipped==>"+rpFtpAbcVO.getStyleNumber());
		}
		}catch(Exception e){
			logger.error("Style "+styleNumToProcess+" processing failed", e);
		}
		return rpFtpAbcList;
	}
	
	private boolean isNullOrEmpty(String tmp) {

		if(tmp == null){
			//System.out.println("This is null");
			return true;
		}else if(tmp.trim().isEmpty()){
			//System.out.println("This is empty");
			return true;
		}
		
		return false;
	}
	public String getSkippedFilePath() {
		return skippedFilePath;
	}
	public void setSkippedFilePath(String skippedFilePath) {
		this.skippedFilePath = skippedFilePath;
	}
	public String getSkippedFile() {
		return skippedFile;
	}
	public void setSkippedFile(String skippedFile) {
		this.skippedFile = skippedFile;
	}

}
