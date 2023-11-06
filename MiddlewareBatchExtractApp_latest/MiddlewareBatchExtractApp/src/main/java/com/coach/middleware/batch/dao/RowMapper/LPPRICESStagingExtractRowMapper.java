package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.LPPRICESStagingExtractVo;
import com.coach.middleware.util.PMDBUtil;

public class LPPRICESStagingExtractRowMapper implements RowMapper<LPPRICESStagingExtractVo>{

	@Override
	public LPPRICESStagingExtractVo mapRow(ResultSet rs, int arg1)
			throws SQLException {
		LPPRICESStagingExtractVo vo = new LPPRICESStagingExtractVo();
		vo.setESSSKU(PMDBUtil.checkForNull(rs.getString("ESSSKU")));
		vo.setCENTURY(PMDBUtil.checkForNull(rs.getString("CENTURY")));
		vo.setPERIOD(PMDBUtil.checkForNull(rs.getString("PERIOD")));
		vo.setRETAILPRICE(PMDBUtil.checkForNull(rs.getString("RETAILPRICE")));
		vo.setWHPRICE(PMDBUtil.checkForNull(rs.getString("WHPRICE")));
		vo.setJPY(PMDBUtil.checkForNull(rs.getString("JPY")));
		return vo;
	}

}
