package com.coach.middleware.batch.itemProcessor;


import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import com.coach.middleware.batch.dao.VO.SapMatLpVO;
import com.coach.middleware.batch.dto.SapMatLpDTO;

public class SapMatLpItemProcessor implements ItemProcessor<SapMatLpDTO,List<SapMatLpVO>> {
	
	private static final Logger logger = Logger
			.getLogger(SapMatLpItemProcessor.class);

	List<SapMatLpVO> sapMatLpList = new ArrayList();
	@Override
	public List<SapMatLpVO> process(SapMatLpDTO sapMatLpDTO) throws Exception {
		// TODO Auto-generated method stub
		
		SapMatLpVO sapMatLpVO = new SapMatLpVO();
		//removeSpecialCharactersInInNumbers( sapMatLpDTO);
		if(!isNullOrEmpty(sapMatLpDTO.getUpc()))
			sapMatLpVO.setUpc(sapMatLpDTO.getUpc());
		else
			sapMatLpVO.setUpc("");
		
		if (!isNullOrEmpty(sapMatLpDTO.getMatDesc())) {
			String matDesc = sapMatLpDTO.getMatDesc();
			if (matDesc.contains("'")) {
				matDesc.replace('\'', '\"');
			}
			sapMatLpVO.setMatDesc(matDesc);
		}
		else
			sapMatLpVO.setMatDesc("");
		
		if(!isNullOrEmpty(sapMatLpDTO.getMatNum()))
		{
			// MatNum & C0lor
			String tmpStr = sapMatLpDTO.getMatNum();
			if(tmpStr.length() < 11){
				//logger.debug("getSkuColumn before==>"+tmpStr.length());
				for(int i=tmpStr.length();i<11;i++){
					tmpStr = tmpStr+" ";
				}
				//logger.debug("getSkuColumn after==>"+tmpStr.length());
			}
			sapMatLpVO.setMatNum(tmpStr);
			
			
			
		}else{
			sapMatLpVO.setMatNum(null);
		}
		
		if(!isNullOrEmpty(sapMatLpVO.getMatNum())){
			
			String tmpStr = sapMatLpVO.getMatNum();
			sapMatLpVO.setMatNum(sapMatLpVO.getMatNum().substring(0, 11).trim());
		
		
		sapMatLpVO.setColor(tmpStr.substring(6, 11).trim());
		
		// Quality
		String quality = null;
		if(tmpStr.trim().startsWith("F") || tmpStr.trim().startsWith("IR")){
			quality = "2";
		}else{
			quality = "1";
		}
		sapMatLpVO.setQuality(quality);
		
		// Style
		String style = tmpStr.substring(0, 6).trim();
		if(style.startsWith("F")){
			
			style= style.substring(1);
		
		}else if(style.startsWith("IR")){
			style= style.substring(2);
		}
		
		sapMatLpVO.setStyle(style);
		}	
		else
			sapMatLpVO.setMatNum("");
		
		if(!isNullOrEmpty(sapMatLpDTO.getStdCost()))
			sapMatLpVO.setStdCost(sapMatLpDTO.getStdCost());
		else
			sapMatLpVO.setStdCost("");
		
		if(!isNullOrEmpty(sapMatLpDTO.getRetailPrice()))
			sapMatLpVO.setRetailPrice(sapMatLpDTO.getRetailPrice());
		else
			sapMatLpVO.setRetailPrice("");
		
		if(!isNullOrEmpty(sapMatLpDTO.getWhPrice()))
			sapMatLpVO.setWhPrice(sapMatLpDTO.getWhPrice());
		else
			sapMatLpVO.setWhPrice("");
		
		if(!isNullOrEmpty(sapMatLpDTO.getFsTicketPrice()))
			sapMatLpVO.setFsTicketPrice(sapMatLpDTO.getFsTicketPrice());
		else
			sapMatLpVO.setFsTicketPrice("");
		
		logger.debug("Inside sapmatlp process==>");
		logger.debug("getUpc==>"+sapMatLpVO.getUpc());
		logger.debug("getMatDesc==>"+sapMatLpVO.getMatDesc());
		logger.debug("getMatNum==>"+sapMatLpVO.getMatNum());
		logger.debug("getStdCost==>"+sapMatLpVO.getStdCost());
		logger.debug("getRetailPrice==>"+sapMatLpVO.getRetailPrice());
		logger.debug("getWhPrice==>"+sapMatLpVO.getWhPrice());
		logger.debug("getFsTicketPrice==>"+sapMatLpVO.getFsTicketPrice());
		logger.debug("getQuality==>"+sapMatLpVO.getQuality());
		logger.debug("getStyle==>"+sapMatLpVO.getStyle());
		logger.debug("getColor==>"+sapMatLpVO.getColor());
		logger.debug("Inside sapmatlp process==>");
		
		sapMatLpList.add(sapMatLpVO);
		
		return sapMatLpList;
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
	
	public void removeSpecialCharactersInString(SapMatLpDTO sapMatLpDTO) {

		// removes all special characters.
		if (!isNullOrEmpty(sapMatLpDTO.getMatDesc())) {
			sapMatLpDTO.setMatDesc(sapMatLpDTO.getMatDesc().replaceAll("[^a-zA-Z0-9\\s\\.]", ""));

		}

	}
	
	public void removeSpecialCharactersInInNumbers(SapMatLpDTO sapMatLpDTO) {

		/*
		 * removes all characters and special characters permits only numbers
		 * and . Example String ="'hello 23.45& test"-->23.45
		 */

		if (!isNullOrEmpty(sapMatLpDTO.getMatDesc())) {
			sapMatLpDTO.setMatDesc(sapMatLpDTO.getMatDesc().replaceAll("[^a-zA-Z0-9\\s\\.]", ""));

		}
		if (!isNullOrEmpty(sapMatLpDTO.getRetailPrice())) {
			sapMatLpDTO.setRetailPrice(sapMatLpDTO.getRetailPrice().replaceAll("[^0-9\\.]", ""));
		}
		if (!isNullOrEmpty(sapMatLpDTO.getStdCost())) {
			sapMatLpDTO.setStdCost(sapMatLpDTO.getStdCost().replaceAll("[^0-9\\.]", ""));
		}
		if (!isNullOrEmpty(sapMatLpDTO.getWhPrice())) {
			sapMatLpDTO.setWhPrice(sapMatLpDTO.getWhPrice().replaceAll("[^0-9\\.]", ""));
		}
		if (!isNullOrEmpty(sapMatLpDTO.getFsTicketPrice())) {
			sapMatLpDTO.setFsTicketPrice(sapMatLpDTO.getFsTicketPrice().replaceAll("[^0-9\\.]", ""));
		}

	}

}
