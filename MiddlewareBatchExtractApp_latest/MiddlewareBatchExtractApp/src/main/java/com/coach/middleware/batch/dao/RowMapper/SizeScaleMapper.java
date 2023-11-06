package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.SizeScaleVO;

public class SizeScaleMapper implements RowMapper<SizeScaleVO> {

	@Override
	public SizeScaleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SizeScaleVO sizeScaleVO = new SizeScaleVO();
		
		sizeScaleVO.setSizelist(rs.getString("sizelist"));
		sizeScaleVO.setSizescale(rs.getString("sizescale"));
		sizeScaleVO.setSizescalemsg(rs.getString("sizescalemsg"));
		return sizeScaleVO;
	}

}
