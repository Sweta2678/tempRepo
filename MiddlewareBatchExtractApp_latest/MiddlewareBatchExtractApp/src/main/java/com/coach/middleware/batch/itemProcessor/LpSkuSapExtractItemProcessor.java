/**
 * 
 */
/**
 * @author AB263159
 *
 */
package com.coach.middleware.batch.itemProcessor;

import java.util.*;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import com.coach.middleware.batch.dao.TotoExtractDAO;
import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.LpSkuSapVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;
import com.coach.middleware.batch.dto.TotoExtractDTO;

public class LpSkuSapExtractItemProcessor implements ItemProcessor<LpSkuSapVO, List<LpSkuSapVO>>{
	 
	
	private static final Logger logger = Logger.getLogger(LpSkuSapExtractItemProcessor.class);
	
	
	
	boolean fetchAttrStoreDet = true;
	int i=0;
	
	final List<LpSkuSapVO> lpSkuSapVOList = new ArrayList();
	final Set totoExtractVOSet = new HashSet();
	List<AttributeStoreVO> attributeStoreVOList= new ArrayList();
	private String styleNumToProcess = null;
	
	@Override
	public List<LpSkuSapVO> process(LpSkuSapVO lpSkuSapVO) throws Exception {
	
			try{
				styleNumToProcess = lpSkuSapVO.getStyle();
			if(isNullOrEmptyOrZero(lpSkuSapVO.getSeqNo()))
				lpSkuSapVO.setSeqNo("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getStyle()))
				lpSkuSapVO.setStyle("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getColor()))
				lpSkuSapVO.setColor("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getSkuSize()))
				lpSkuSapVO.setSkuSize("N/A");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getStyleDesc()))
				lpSkuSapVO.setStyleDesc("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getColorDesc()))
				lpSkuSapVO.setColorDesc("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getType()))
				lpSkuSapVO.setType("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getDropIrAlso()))
				lpSkuSapVO.setDropIrAlso("");	
			if(isNullOrEmptyOrZero(lpSkuSapVO.getSshipDate()))
				lpSkuSapVO.setSshipDate("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getProdHierar()))
				lpSkuSapVO.setProdHierar("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getDivi()))
				lpSkuSapVO.setDivi("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getDept()))
				lpSkuSapVO.setDept("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getSkuClass()))
				lpSkuSapVO.setSkuClass("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getSubClass()))
				lpSkuSapVO.setSubClass("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getTargetCost()))
				lpSkuSapVO.setTargetCost("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getFinalCost()))
				lpSkuSapVO.setFinalCost("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getIrOnly()))
				lpSkuSapVO.setIrOnly("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getUpcCode()))
				lpSkuSapVO.setUpcCode("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getVenRefNo()))
				lpSkuSapVO.setVenRefNo("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getExotic()))
				lpSkuSapVO.setExotic("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getSetupCreateDate()))
				lpSkuSapVO.setSetupCreateDate("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getRetPrice()))
				lpSkuSapVO.setRetPrice("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getWhPrice()))
				lpSkuSapVO.setWhPrice("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getJpp()))
				lpSkuSapVO.setJpp("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getJpy()))
				lpSkuSapVO.setJpy("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getJ2()))
				lpSkuSapVO.setJ2("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getJ3()))
				lpSkuSapVO.setJ3("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getHawaiiWhl()))
				lpSkuSapVO.setHawaiiWhl("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getKpp()))
				lpSkuSapVO.setKpp("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getHawaiiFp()))
				lpSkuSapVO.setHawaiiFp("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getHawaiiFs()))
				lpSkuSapVO.setHawaiiFs("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getCad()))
				lpSkuSapVO.setCad("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getIpp()))
				lpSkuSapVO.setIpp("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getSizeScale()))
				lpSkuSapVO.setSizeScale("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getFsStartShipDate()))
				lpSkuSapVO.setFsStartShipDate("");
			if(isNullOrEmptyOrZero(lpSkuSapVO.getFsTicketPrice()))
				lpSkuSapVO.setFsTicketPrice("");
				
			}catch(Exception e){
				logger.error("Style "+styleNumToProcess+" processing failed", e);
			}
			
			lpSkuSapVOList.add(lpSkuSapVO);
		
			return lpSkuSapVOList;
		
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
	
	private boolean isNullOrEmptyOrZero(String tmp) {

		if(tmp == null){
			//System.out.println("This is null");
			return true;
		}else if(tmp.trim().isEmpty()){
			//System.out.println("This is empty");
			return true;
		}else if(tmp.trim().equals("0")){
			//System.out.println("This is empty");
			return true;
		}else if(tmp.trim().equals("0.0")){
			//System.out.println("This is empty");
			return true;
		}
		
		return false;
	}
 
}