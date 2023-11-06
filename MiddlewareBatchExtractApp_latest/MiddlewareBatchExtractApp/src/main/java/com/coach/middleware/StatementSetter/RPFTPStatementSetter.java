package com.coach.middleware.StatementSetter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;



import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;

public class RPFTPStatementSetter implements ItemPreparedStatementSetter<RpFtpAbcVO> {
	@Override
	public void setValues(RpFtpAbcVO item,PreparedStatement ps) throws SQLException {
		
		try{
			
		String sku = item.getSkuColumn().trim();
		//System.out.println("Statement setter..."+sku.length());
		if(sku.length()>16){
			sku = sku.substring(0,16).trim();
			item.setSkuColumn(sku);
		}
		ps.setString(1, sku);
		ps.setString(2, item.getAbcCode());//want to add Style Name
		ps.setString(3, item.getMpg());
		ps.setString(4, item.getStyleNumber());
		ps.setString(5, item.getSkuName());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
}