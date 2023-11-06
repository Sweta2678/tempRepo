package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.ColourCodingVo;
import com.coach.middleware.batch.dao.VO.SizeScaleMappingVO;
import com.coach.middleware.batch.dao.VO.SizeScaleMasterVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public class SizeScaleMappingRowMapper implements RowMapper<SizeScaleMappingVO> {

	
	@Override
	public SizeScaleMappingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		SizeScaleMappingVO sizeScaleMasterVO = new SizeScaleMappingVO();
	    sizeScaleMasterVO.setSizename(rs.getString("sizelist"));
	    sizeScaleMasterVO.setSizeScaleID(rs.getString("sizescale"));
	    
	    return sizeScaleMasterVO;
	}

		
		
		
}



