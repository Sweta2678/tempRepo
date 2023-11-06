package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.coach.middleware.batch.dao.VO.VariantLookUpVo;
import com.coach.middleware.util.PMDBUtil;

public class VariantLookupRowMapper implements RowMapper<VariantLookUpVo>{

	@Override
	public VariantLookUpVo mapRow(ResultSet rs, int arg1) throws SQLException {
		VariantLookUpVo lookupVo = new VariantLookUpVo();
		lookupVo.setKey(PMDBUtil.checkForNull(rs.getString("VARIANTKEY")));
		lookupVo.setValue(PMDBUtil.checkForNull(rs.getString("VARIANT")));
		lookupVo.setMaterialType(PMDBUtil.checkForNull(rs.getString("MATERIALTYPE")));
		return lookupVo;

	}

}
