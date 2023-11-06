package com.tapestry.moic.config;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class HikariConfigInbound extends HikariConfig {

    protected final HikariInboundProperties hikariReadProperties;

    protected final String PERSISTENCE_UNIT_NAME = "read";

    protected final Properties JPA_READ_PROPERTIES = new Properties() {{
        put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        put("hibernate.hbm2ddl.auto", "update");
        put("hibernate.ddl-auto", "update");
        put("show-sql", "true");
    }};

    protected HikariConfigInbound(HikariInboundProperties hikariReadProperties) {
        this.hikariReadProperties = hikariReadProperties;
        setPoolName(this.hikariReadProperties.getPoolName());
        setMinimumIdle(this.hikariReadProperties.getMinimumIdle());
        setMaximumPoolSize(this.hikariReadProperties.getMaximumPoolSize());
        setIdleTimeout(this.hikariReadProperties.getIdleTimeout());
        setSchema(this.hikariReadProperties.getSchema());
    }
}
