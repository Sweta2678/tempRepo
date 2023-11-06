package com.tapestry.moic.config;


import javax.persistence.EntityManagerFactory;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties("spring.datasource-inbound")
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactoryInbound",
        transactionManagerRef = "transactionManagerInbound",
		basePackages = "com.tapestry.moic.inbound.repository"
		)
public class DataSourceConfigInbound extends HikariConfigInbound{

	public final static String MODEL_PACKAGE = "com.tapestry.moic.inbound.entity";
	
    public DataSourceConfigInbound(HikariInboundProperties hikariReadProperties) {
    	super(hikariReadProperties);
    }

    @Bean
    public HikariDataSource dataSourceRead() {
        return new HikariDataSource(this);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryInbound(
            final HikariDataSource dataSourceRead) {

        return new LocalContainerEntityManagerFactoryBean() {{
            setDataSource(dataSourceRead);
            setPersistenceProviderClass(HibernatePersistenceProvider.class);
            setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
            setPackagesToScan(MODEL_PACKAGE);
            setJpaProperties(JPA_READ_PROPERTIES);
        }};
    }

    @Bean
    public PlatformTransactionManager transactionManagerInbound(EntityManagerFactory entityManagerFactoryInbound) {
        return new JpaTransactionManager(entityManagerFactoryInbound);
    }

	
}
