package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.PRODMASTFullPriceMOAVO;

public class PRODMASTFullPriceRowMapper implements RowMapper<PRODMASTFullPriceMOAVO> {

	@Override
	public PRODMASTFullPriceMOAVO mapRow(ResultSet rs, int arg1)
			throws SQLException {
		PRODMASTFullPriceMOAVO fullPriceMOAVO = new PRODMASTFullPriceMOAVO();
	     
		fullPriceMOAVO.setOwnerUniqueId(rs.getString("owneruniqueId"));
		fullPriceMOAVO.setTargetCost(rs.getString("TargetCost"));
		fullPriceMOAVO.setRetailPrice(rs.getString("RetailPrice"));
		fullPriceMOAVO.setWhPrice(rs.getString("WHPrice"));
		fullPriceMOAVO.setTotalCost(rs.getString("TotalCost"));
		
	    return fullPriceMOAVO;
	}

}
