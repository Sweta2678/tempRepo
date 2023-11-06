package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.ColourCodingVo;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public class ColorMasterRowMapper implements RowMapper<ColourCodingVo> {

	
	@Override
	public ColourCodingVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ColourCodingVo colomasterrvo = new ColourCodingVo();
	     
		colomasterrvo.setClolorname(rs.getString("shortcolorname"));
		colomasterrvo.setClolortype(rs.getString("colortype"));
		colomasterrvo.setUniqueid(rs.getString("uniqueid"));
		
		
		
	        return colomasterrvo;
	}

		
		
		
}



