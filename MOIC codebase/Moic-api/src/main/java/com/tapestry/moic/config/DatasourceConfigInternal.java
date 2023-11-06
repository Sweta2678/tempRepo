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
@ConfigurationProperties("spring.datasource-internal")
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryInternal",
        transactionManagerRef = "transactionManagerInternal",
        basePackages = {"com.tapestry.moic.repository"}
)
public class DatasourceConfigInternal extends HikariConfigInternal {
	
	public final static String MODEL_PACKAGE = "com.tapestry.moic.entity";

    public DatasourceConfigInternal(HikariInternalProperties hikariWriteProperties) {
        super(hikariWriteProperties);
    }

    @Bean
    public HikariDataSource dataSourceWrite() {
        return new HikariDataSource(this);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryInternal(
            final HikariDataSource dataSourceWrite) {

        return new LocalContainerEntityManagerFactoryBean() {{
            setDataSource(dataSourceWrite);
            setPersistenceProviderClass(HibernatePersistenceProvider.class);
            setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
            setPackagesToScan(MODEL_PACKAGE);
            setJpaProperties(JPA_WRITE_PROPERTIES);
        }};
    }

    @Bean
    public PlatformTransactionManager transactionManagerInternal(EntityManagerFactory entityManagerFactoryInternal) {
        return new JpaTransactionManager(entityManagerFactoryInternal);
    }
}