package com.coach.middleware.batch.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.PRODMASTFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;

public class RpFtpAbcRowMapper implements RowMapper<RpFtpAbcVO> {

	@Override
	public RpFtpAbcVO mapRow(ResultSet rs, int arg1)
			throws SQLException {
		RpFtpAbcVO rpFtpAbcVO = new RpFtpAbcVO();
	    
		rpFtpAbcVO.setAbcCode(rs.getString("ABCCODE"));
		rpFtpAbcVO.setMpg(rs.getString("MPG"));
		rpFtpAbcVO.setStyleNumber(rs.getString("STYLE"));
		rpFtpAbcVO.setSkuName(rs.getString("SKUNAME"));
	    return rpFtpAbcVO;
	}

}
