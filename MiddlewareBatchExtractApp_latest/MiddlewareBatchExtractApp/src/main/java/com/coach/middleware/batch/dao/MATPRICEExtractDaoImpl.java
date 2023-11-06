package com.coach.middleware.batch.dao;

import javax.sql.DataSource;

public class MATPRICEExtractDaoImpl implements MATPRICEExtractDao{
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
