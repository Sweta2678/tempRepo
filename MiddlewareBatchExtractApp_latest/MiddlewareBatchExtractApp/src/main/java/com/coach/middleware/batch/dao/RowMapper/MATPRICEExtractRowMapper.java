package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.MATPRICEExtractVO;
import com.coach.middleware.util.PMDBUtil;


public class MATPRICEExtractRowMapper implements RowMapper<MATPRICEExtractVO>{

	@Override
	public MATPRICEExtractVO mapRow(ResultSet rs, int arg1)
			throws SQLException {
		Timestamp createDate = rs.getTimestamp("CREATEDATE");
		Timestamp updateDate = rs.getTimestamp("UPDATEDATE");
		MATPRICEExtractVO matPrice = new MATPRICEExtractVO();
		matPrice.setCMNUMBER(PMDBUtil.checkForNull(rs.getString("CMNUMBER")));
		matPrice.setVENDORNUMBER(PMDBUtil.checkForNull(rs.getString("VENDORNUMBER")));
		matPrice.setPRICETYPE(PMDBUtil.checkForNull(rs.getString("PRICETYPE")));
		matPrice.setTODATE(PMDBUtil.checkForNull(rs.getString("TODATE")));
		matPrice.setPRICE(PMDBUtil.checkForNull(rs.getString("PRICE")));
		matPrice.setCURRENCY(PMDBUtil.checkForNull(rs.getString("CURRENCY")));
		matPrice.setFROMDATE(PMDBUtil.checkForNull(rs.getString("FROMDATE")));
		matPrice.setUNIQUEID(PMDBUtil.checkForNull(rs.getString("UNIQUEID")));
		matPrice.setCREATEDATE(createDate);
		matPrice.setUPDATEDATE(updateDate);
		matPrice.setCOLORCODE(PMDBUtil.checkColorCodeNull(rs.getString("COLORCODE")));
		if(createDate!=null && updateDate!=null){
		if(createDate.equals(updateDate))
		matPrice.setUSERACTION("ADD");
		else
		matPrice.setUSERACTION("UPD");
		}
		else
		matPrice.setUSERACTION("ADD");
		return matPrice;
	}

}
