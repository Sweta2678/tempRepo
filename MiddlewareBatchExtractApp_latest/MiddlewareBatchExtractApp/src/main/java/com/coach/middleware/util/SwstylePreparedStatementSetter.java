package com.coach.middleware.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

public class SwstylePreparedStatementSetter implements PreparedStatementSetter {

    private String swStyles;

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
    	System.out.println("*************setting sw values*********************************"+ this.swStyles);
        ps.setString(1, this.swStyles);
    }

    public void setId(String swStyles) {   	
    	System.out.println("*************setId********************************* "+ this.swStyles);
        this.swStyles = swStyles;
    }

}