package com.coach.middleware.batch.itemProcessor;


import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import com.coach.middleware.batch.dao.VO.SapMcompLpVO;
import com.coach.middleware.batch.dto.SapMcompLpDTO;

public class SapMcompLpItemProcessor implements ItemProcessor<SapMcompLpDTO,List<SapMcompLpVO>> {
	
	private static final Logger logger = Logger
			.getLogger(SapMcompLpItemProcessor.class);

	List<SapMcompLpVO> sapMcompLpList = new ArrayList();
	@Override
	public List<SapMcompLpVO> process(SapMcompLpDTO sapMcompLpDTO) throws Exception {
		// TODO Auto-generated method stub
		
		SapMcompLpVO sapMcompLpVO = new SapMcompLpVO();
		
		if(!isNullOrEmpty(sapMcompLpDTO.getSeqId()))
			sapMcompLpVO.setSeqId(sapMcompLpDTO.getSeqId());
		else
			sapMcompLpVO.setSeqId("");
		
		if(!isNullOrEmpty(sapMcompLpDTO.getStyle())){
			sapMcompLpVO.setStyle(sapMcompLpDTO.getStyle());
			/*String matDesc = sapMatLpDTO.getMatDesc();
			if(matDesc.contains("'")){
				matDesc.replace('\'', '\"');
			}
			sapMatLpVO.setMatDesc(matDesc);*/
		}	
		else
			sapMcompLpVO.setStyle("");
		
		if(!isNullOrEmpty(sapMcompLpDTO.getColor()))
		{
			// MatNum & C0lor
			String tmpStr = sapMcompLpDTO.getColor();
			if(tmpStr.contains("/")){
				
				tmpStr = tmpStr.replace("/", "");
				}
				
			
		sapMcompLpVO.setColor(tmpStr);
			
		}else{
			sapMcompLpVO.setColor("");
		}
		
		if(!isNullOrEmpty(sapMcompLpDTO.getSkuSize())){
			
			String tmpStr = sapMcompLpDTO.getSkuSize();
			if(tmpStr.contains("'")){
				tmpStr = tmpStr.replace('\'', '\"');
			}
			sapMcompLpVO.setSkuSize(tmpStr);
		
		}else{
			sapMcompLpVO.setSkuSize("");
		}
		
		
		if(!isNullOrEmpty(sapMcompLpDTO.getRequestType())){
			String tmpStr = sapMcompLpDTO.getSkuSize();
			if(tmpStr.contains(" ")){
				tmpStr = tmpStr.replace(" ", "");
			}
			sapMcompLpVO.setRequestType(tmpStr);
		}
		else
			sapMcompLpVO.setRequestType("");
		
		if(!isNullOrEmpty(sapMcompLpDTO.getSapCompeletDate()))
			sapMcompLpVO.setSapCompeletDate(sapMcompLpDTO.getSapCompeletDate());
		else
			sapMcompLpVO.setSapCompeletDate("");
		
		if(!isNullOrEmpty(sapMcompLpDTO.getStatus()))
			sapMcompLpVO.setStatus(sapMcompLpDTO.getStatus());
		else
			sapMcompLpVO.setStatus("");
		
		if(!isNullOrEmpty(sapMcompLpDTO.getStatusCode()))
			sapMcompLpVO.setStatusCode(sapMcompLpDTO.getStatusCode());
		else
			sapMcompLpVO.setStatusCode("");
		
		
		/*logger.debug("Inside sapmcomlp process==>");
		logger.debug("getSeqId==>"+sapMcompLpVO.getSeqId());
		logger.debug("getStyle==>"+sapMcompLpVO.getStyle());
		logger.debug("getColor==>"+sapMcompLpVO.getColor());
		logger.debug("getSkuSize==>"+sapMcompLpVO.getSkuSize());
		logger.debug("getRequestType==>"+sapMcompLpVO.getRequestType());
		logger.debug("getSapCompeletDate==>"+sapMcompLpVO.getSapCompeletDate());
		logger.debug("Inside sapmatlp process==>");*/
		
		sapMcompLpList.add(sapMcompLpVO);
		
		return sapMcompLpList;
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

}
