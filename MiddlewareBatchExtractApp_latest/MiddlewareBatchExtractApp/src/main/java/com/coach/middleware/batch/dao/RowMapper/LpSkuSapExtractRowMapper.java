package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.LpSkuSapVO;


public class LpSkuSapExtractRowMapper implements RowMapper<LpSkuSapVO> {

		
	    public LpSkuSapVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	 
	    	LpSkuSapVO lpSkuSapVO = new LpSkuSapVO();
	     
	    	lpSkuSapVO.setSeqNo(rs.getString("seqNo"));
	    			lpSkuSapVO.setStyle(rs.getString("style"));
	    			lpSkuSapVO.setColor(rs.getString("color"));
	    			lpSkuSapVO.setSkuSize(rs.getString("skuSize"));
	    			lpSkuSapVO.setStyleDesc(rs.getString("styleDesc"));
	    			lpSkuSapVO.setColorDesc(rs.getString("colorDesc"));
	    			lpSkuSapVO.setType(rs.getString("type"));
	    			lpSkuSapVO.setDropIrAlso(rs.getString("dropIrAlso"));
	    			lpSkuSapVO.setSshipDate(rs.getString("sshipDate"));
	    			lpSkuSapVO.setProdHierar(rs.getString("prodHierar"));
	    			lpSkuSapVO.setDivi(rs.getString("divi"));
	    			lpSkuSapVO.setDept(rs.getString("dept"));
	    			lpSkuSapVO.setSkuClass(rs.getString("skuClass"));
	    			lpSkuSapVO.setSubClass(rs.getString("subClass"));
	    			lpSkuSapVO.setTargetCost(rs.getString("targetCost"));
	    			lpSkuSapVO.setFinalCost(rs.getString("finalCost"));
	    			lpSkuSapVO.setIrOnly(rs.getString("irOnly"));
	    			lpSkuSapVO.setUpcCode(rs.getString("upcCode"));
	    			lpSkuSapVO.setVenRefNo(rs.getString("venRefNo"));
	    			lpSkuSapVO.setExotic(rs.getString("exotic"));
	    			lpSkuSapVO.setSetupCreateDate(rs.getString("setupCreateDate"));
	    			lpSkuSapVO.setRetPrice(rs.getString("retPrice"));
	    			lpSkuSapVO.setWhPrice(rs.getString("whPrice"));
	    			lpSkuSapVO.setJpp(rs.getString("jpp"));
	    			lpSkuSapVO.setJpy(rs.getString("jpy"));
	    			lpSkuSapVO.setJ2(rs.getString("j2"));
	    			lpSkuSapVO.setJ3(rs.getString("j3"));
	    			lpSkuSapVO.setHawaiiWhl(rs.getString("hawaiiWhl"));
	    			lpSkuSapVO.setKpp(rs.getString("kpp"));
	    			lpSkuSapVO.setHawaiiFp(rs.getString("hawaiiFp"));
	    			lpSkuSapVO.setHawaiiFs(rs.getString("hawaiiFs"));
	    			lpSkuSapVO.setCad(rs.getString("cad"));
	    			lpSkuSapVO.setIpp(rs.getString("ipp"));
	    			lpSkuSapVO.setSizeScale(rs.getString("sizeScale"));
	    			lpSkuSapVO.setFsStartShipDate(rs.getString("fsStartShipDate"));
	    			lpSkuSapVO.setFsTicketPrice(rs.getString("fsTicketPrice"));

	        return lpSkuSapVO;
	    } 
	
}
