package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.ColourCodingVo;
import com.coach.middleware.batch.dao.VO.SizeScaleMasterVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public class SizeScaleMasterRowMapper implements RowMapper<SizeScaleMasterVO> {

	
	@Override
	public SizeScaleMasterVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		SizeScaleMasterVO sizeScaleMasterVO = new SizeScaleMasterVO();
	     
	    sizeScaleMasterVO.setSizename(rs.getString("SIZENAME"));
		sizeScaleMasterVO.setStyleUniqueid(rs.getString("STYLEUNIQUEID"));
		sizeScaleMasterVO.setSkuUniqueid(rs.getString("SKUUNIQUEID"));
		sizeScaleMasterVO.setVendorReff(rs.getString("VENDORREFNUMBER"));
	        return sizeScaleMasterVO;
	}

		
		
		
}



