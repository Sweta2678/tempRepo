package com.tapestry.moic.config;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class HikariConfigInternal extends HikariConfig {

    protected final HikariInternalProperties hikariWriteProperties;

    protected final String PERSISTENCE_UNIT_NAME = "write";

    protected final Properties JPA_WRITE_PROPERTIES = new Properties() {{
        put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        put("hibernate.hbm2ddl.auto", "update");
        put("hibernate.ddl-auto", "update");
        put("show-sql", "true");
    }};

    protected HikariConfigInternal(HikariInternalProperties hikariWriteProperties) {
        this.hikariWriteProperties = hikariWriteProperties;
        setPoolName(this.hikariWriteProperties.getPoolName());
        setMinimumIdle(this.hikariWriteProperties.getMinimumIdle());
        setMaximumPoolSize(this.hikariWriteProperties.getMaximumPoolSize());
        setIdleTimeout(this.hikariWriteProperties.getIdleTimeout());
        setSchema(this.hikariWriteProperties.getSchema());
    }
}
