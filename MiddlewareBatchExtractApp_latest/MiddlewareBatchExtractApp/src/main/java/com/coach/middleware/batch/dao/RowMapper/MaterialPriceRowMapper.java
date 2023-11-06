package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.MaterialPriceVo;
import com.coach.middleware.util.PMDBUtil;

public class MaterialPriceRowMapper implements RowMapper<MaterialPriceVo>{

	@Override
	public MaterialPriceVo mapRow(ResultSet rs, int arg1) throws SQLException {
		MaterialPriceVo materialpriceVo = new MaterialPriceVo();
		materialpriceVo.setMatsupplierid(PMDBUtil.checkForNull(rs.getString("MATERIALSUPPID")));
		materialpriceVo.setMatcolorid(PMDBUtil.checkForNull(rs.getString("MATERIALCOLORID")));
		materialpriceVo.setUniqueID(PMDBUtil.checkForNull(rs.getString("UNIQUEID")));
		materialpriceVo.setPrice(PMDBUtil.checkForNull(rs.getString("PRICE")));
		materialpriceVo.setFromDate(PMDBUtil.checkForNull(rs.getString("FROMDATE")));
		return materialpriceVo;
	}

}
