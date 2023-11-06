package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;
import com.coach.middleware.batch.itemWriter.LPPRICESExtractItemWriter;

public class LPPRICESExtractRowMapper implements RowMapper<LPPRICESExtractVO> {
	private static final Logger logger = Logger.getLogger(LPPRICESExtractRowMapper.class);
	public static List<String> styleList = new ArrayList<String>();
	public static List<LPPRICESExtractVO> LppriceVoList = new ArrayList<LPPRICESExtractVO>();
	public LPPRICESExtractVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		LPPRICESExtractVO LPPRICESExtractVO = new LPPRICESExtractVO();
		Date fromDate = null;
		Date endDate = null;
	
		try {
			String styleUniqueId=rs.getString("STYLE_UNIQUE");
			LPPRICESExtractVO.setStyle(rs.getString("STYLE"));
			LPPRICESExtractVO.setSku(rs.getString("SKU"));
			LPPRICESExtractVO.setOwnerUniqueID(styleUniqueId);
			LPPRICESExtractVO.setStdCost(rs.getFloat("STDCOST"));
			if(!styleList.contains(styleUniqueId))
			styleList.add(styleUniqueId);
			LppriceVoList.add(LPPRICESExtractVO);
		} catch (Exception e) {logger.error("Error in LPPRICESRowMapper : "+e.getLocalizedMessage());// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LPPRICESExtractVO;
	}
	public static List<String> getStyleList() {
		return styleList;
	}
	public static void setStyleList(List<String> styleList) {
		LPPRICESExtractRowMapper.styleList = styleList;
	}



}
