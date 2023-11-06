package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.springframework.jdbc.core.RowMapper;
import com.coach.middleware.batch.dao.VO.CjiPriceMOAVO;

public class CjiPriceMOARowMapper implements RowMapper<CjiPriceMOAVO> {

			
			@Override
			public CjiPriceMOAVO mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
						CjiPriceMOAVO cjiPriceMOAVO = new CjiPriceMOAVO();
						cjiPriceMOAVO.setCJIPRICE(rs.getDouble("CJIPRICE"));
						cjiPriceMOAVO.setOWNER(rs.getString("OWNER"));
						cjiPriceMOAVO.setOWNERUNIQUEID(rs.getString("OWNERUNIQUEID"));
							try {
								cjiPriceMOAVO.setCJISTARTDATE(dateFormat.parse(rs.getString("CJISTARTDATE")));
								if(rs.getString("CJIENDDATE")!=null)
									cjiPriceMOAVO.setCJIENDDATE(dateFormat.parse(rs.getString("CJIENDDATE")));
							} catch (java.text.ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    return cjiPriceMOAVO;
			}
			
	}

