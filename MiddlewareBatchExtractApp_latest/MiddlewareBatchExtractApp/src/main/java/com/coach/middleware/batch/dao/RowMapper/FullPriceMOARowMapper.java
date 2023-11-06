package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;


import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;

public class FullPriceMOARowMapper implements RowMapper<FullPriceMOAVO> {

	
	@Override
	public FullPriceMOAVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		//System.out.println("*** FullPriceMOARowMapper ***");
		SimpleDateFormat dateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
		FullPriceMOAVO fullPriceMOAVO = new FullPriceMOAVO();
	     
		fullPriceMOAVO.setOWNER(rs.getString("OWNER"));
		fullPriceMOAVO.setOWNERUNIQUEID(rs.getString("OWNERUNIQUEID"));
		try {
			if(rs.getString("STARTDATE")!=null)
			fullPriceMOAVO.setSTARTDATE(dateFormat.parse(rs.getString("STARTDATE")));
			if(rs.getString("ENDDATE")!=null)
			fullPriceMOAVO.setENDDATE(dateFormat.parse(rs.getString("ENDDATE")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DecimalFormat df2 = new DecimalFormat( "#########.0" );
		fullPriceMOAVO.setTARGETCOST(rs.getDouble("TARGETCOST"));
		fullPriceMOAVO.setWHOLESALEPRICE(rs.getDouble("WHOLESALEPRICE"));
		fullPriceMOAVO.setRETAILPRICE(rs.getDouble("RETAILPRICE"));
	    return fullPriceMOAVO;
	}

		
		
		
}



