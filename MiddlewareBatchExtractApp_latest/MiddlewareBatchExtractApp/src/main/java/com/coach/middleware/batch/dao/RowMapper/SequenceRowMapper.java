package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.LogTabVO;


public class SequenceRowMapper implements RowMapper<Integer>{
	@Override
	public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
		LogTabVO log = new LogTabVO();
		log.setSeq(rs.getString("JOB_EXECUTION_ID"));
		log.setStartDate(rs.getString("START_TIME"));
		return rs.getInt("JOB_EXECUTION_ID");
	}

}
