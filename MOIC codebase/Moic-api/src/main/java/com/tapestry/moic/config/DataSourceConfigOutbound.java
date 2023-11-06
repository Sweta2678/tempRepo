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
@ConfigurationProperties("spring.datasource-outbound")
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactoryOutbound",
        transactionManagerRef = "transactionManagerOutbound",
		basePackages = "com.tapestry.moic.outbound.repository"
		)
public class DataSourceConfigOutbound extends HikariConfigOutbound{

	public final static String MODEL_PACKAGE = "com.tapestry.moic.outbound.entity";
	
    public DataSourceConfigOutbound(HikariOutboundProperties hikariReadProperties) {
    	super(hikariReadProperties);
    }

    @Bean
    public HikariDataSource dataSourceReadWrite() {
        return new HikariDataSource(this);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryOutbound(
            final HikariDataSource dataSourceReadWrite) {

        return new LocalContainerEntityManagerFactoryBean() {{
            setDataSource(dataSourceReadWrite);
            setPersistenceProviderClass(HibernatePersistenceProvider.class);
            setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
            setPackagesToScan(MODEL_PACKAGE);
            setJpaProperties(JPA_READ_PROPERTIES);
        }};
    }

    @Bean
    public PlatformTransactionManager transactionManagerOutbound(EntityManagerFactory entityManagerFactoryOutbound) {
        return new JpaTransactionManager(entityManagerFactoryOutbound);
    }

	
}
