package com.coach.middleware.batch.dao.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;
import com.coach.middleware.batch.dao.VO.ProductRenameVo;

public class ProductRenameExtractRowMapper implements RowMapper<ProductRenameVo>{

	
    public ProductRenameVo mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ProductRenameVo productRenameVO = new ProductRenameVo();
    	productRenameVO.setAction(rs.getString("action"));
    	productRenameVO.setColor(rs.getString("color"));
    	productRenameVO.setStyle(rs.getString("style"));
    	productRenameVO.setOldsku(rs.getString("oldsku"));
    	productRenameVO.setNewsku(rs.getString("newsku"));
    	productRenameVO.setRenamedby(rs.getString("renamedby"));
    	if(rs.getDate("renameddate")!= null) {
    	productRenameVO.setRenameddate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a").format(rs.getDate("renameddate")));
    	}  else {
    		productRenameVO.setRenameddate(rs.getString("renameddate"));
    	}
        return productRenameVO;
    } 

}
