package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.ColourCodingVo;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LPSKUFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public class LPSKUFullPriceMOARowMapper implements RowMapper<LPSKUFullPriceMOAVO> {

	
	@Override
	public LPSKUFullPriceMOAVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		LPSKUFullPriceMOAVO fullPriceMOAVO = new LPSKUFullPriceMOAVO();
	     
		fullPriceMOAVO.setOWNER(rs.getString("OWNER"));
		fullPriceMOAVO.setOWNERUNIQUEID(rs.getString("OWNERUNIQUEID"));
		
		DecimalFormat df2 = new DecimalFormat("#########.##");
		
		
		fullPriceMOAVO.setTARGETCOST(""+df2.format(rs.getDouble("TARGETCOST")));
		if(!fullPriceMOAVO.getTARGETCOST().contains(".")){
			fullPriceMOAVO.setTARGETCOST(fullPriceMOAVO.getTARGETCOST()+".0");
		}
//		else{
//			
//	    	StringTokenizer tokenizer = new StringTokenizer(fullPriceMOAVO.getTARGETCOST()+".00",".");
//	    	String val1 = tokenizer.nextToken();
//	    	String val2 = tokenizer.nextToken();
//	    	val2 =val2 + "00";
//	    	val1 = val1 + "."+val2.substring(0,2);
//	    	fullPriceMOAVO.setTARGETCOST(val1);
//		}
		
		fullPriceMOAVO.setTOTALCOST(""+df2.format(rs.getDouble("TOTALCOST")));
		if(!fullPriceMOAVO.getTOTALCOST().contains(".")){
			fullPriceMOAVO.setTOTALCOST(fullPriceMOAVO.getTOTALCOST()+".0");
		}
//		else{
//			StringTokenizer tokenizer = new StringTokenizer(fullPriceMOAVO.getTOTALCOST()+".00",".");
//	    	String val1 = tokenizer.nextToken();
//	    	String val2 = tokenizer.nextToken();
//	    	val2 =val2 + "00";
//	    	val1 = val1 + "."+val2.substring(0,2);
//	    	fullPriceMOAVO.setTOTALCOST(val1);
//		}
		
		fullPriceMOAVO.setWHOLESALEPRICE(""+df2.format(rs.getDouble("WHOLESALEPRICE")));
		if(!fullPriceMOAVO.getWHOLESALEPRICE().contains(".")){
			fullPriceMOAVO.setWHOLESALEPRICE(fullPriceMOAVO.getWHOLESALEPRICE()+".0");
		}
//		else{
//			StringTokenizer tokenizer = new StringTokenizer(fullPriceMOAVO.getWHOLESALEPRICE()+".00",".");
//	    	String val1 = tokenizer.nextToken();
//	    	String val2 = tokenizer.nextToken();
//	    	val2 =val2 + "00";
//	    	val1 = val1 + "."+val2.substring(0,2);
//	    	fullPriceMOAVO.setWHOLESALEPRICE(val1);
//		}
		
		fullPriceMOAVO.setRETAILPRICE(""+df2.format(rs.getDouble("RETAILPRICE")));
		if(!fullPriceMOAVO.getRETAILPRICE().contains(".")){
			fullPriceMOAVO.setRETAILPRICE(fullPriceMOAVO.getRETAILPRICE()+".0");
		}
//		else{
//			StringTokenizer tokenizer = new StringTokenizer(fullPriceMOAVO.getRETAILPRICE()+".00",".");
//	    	String val1 = tokenizer.nextToken();
//	    	String val2 = tokenizer.nextToken();
//	    	val2 =val2 + "00";
//	    	val1 = val1 + "."+val2.substring(0,2);
//	    	fullPriceMOAVO.setRETAILPRICE(val1);
//		}
		
	    return fullPriceMOAVO;
	}

		
		
		
}



