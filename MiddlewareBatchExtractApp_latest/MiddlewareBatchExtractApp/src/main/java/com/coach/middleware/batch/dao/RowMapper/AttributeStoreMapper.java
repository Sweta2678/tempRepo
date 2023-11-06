package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public class AttributeStoreMapper implements RowMapper<AttributeStoreVO> {

	@Override
	public AttributeStoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AttributeStoreVO attributeStoreVO = new AttributeStoreVO();
	     
		attributeStoreVO.setDisplay2(rs.getString("display2"));
		attributeStoreVO.setDisplay(rs.getString("display"));
		attributeStoreVO.setDisplay3(rs.getString("display3"));
		attributeStoreVO.setDisplay4(rs.getString("display4"));
		attributeStoreVO.setAttributeName(rs.getString("attributename"));
		attributeStoreVO.setValueKey(rs.getString("valuekey"));
		
	        return attributeStoreVO;
	}

		
		
		
}



