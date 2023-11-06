package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.LookupVo;
import com.coach.middleware.util.PMDBUtil;


public class LookupRowMapper implements RowMapper<LookupVo> {

	@Override
	public LookupVo mapRow(ResultSet rs, int arg1) throws SQLException {
		
		LookupVo lookupVo = new LookupVo();
		lookupVo.setVALUEKEY(PMDBUtil.checkForNull(rs.getString("VALUEKEY")));
		lookupVo.setATTRIBUTENAME(PMDBUtil.checkForNull(rs.getString("ATTRIBUTENAME")));
		lookupVo.setDISPLAY(PMDBUtil.checkForNull(rs.getString("DISPLAY")));
		lookupVo.setMATERIALTYPE(PMDBUtil.checkForNull(rs.getString("MATERIALTYPE")));
		return lookupVo;
	}

}
