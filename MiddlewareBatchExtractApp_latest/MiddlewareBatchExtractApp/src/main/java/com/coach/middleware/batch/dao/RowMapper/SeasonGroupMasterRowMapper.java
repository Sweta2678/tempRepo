package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.ColourCodingVo;
import com.coach.middleware.batch.dao.VO.SeasonGroupMasterVO;
import com.coach.middleware.batch.dao.VO.SizeScaleMasterVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public class SeasonGroupMasterRowMapper implements RowMapper<SeasonGroupMasterVO> {

	
	@Override
	public SeasonGroupMasterVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		SeasonGroupMasterVO seasonGroupMasterVO = new SeasonGroupMasterVO();
		seasonGroupMasterVO.setSeasonGroupName(rs.getString("SEASONGROUPNAME"));
		seasonGroupMasterVO.setUniqueid(rs.getString("UNIQUEID"));
		
		
		
	        return seasonGroupMasterVO;
	}

		
		
		
}



