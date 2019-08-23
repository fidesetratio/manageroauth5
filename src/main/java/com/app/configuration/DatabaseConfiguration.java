package com.app.configuration;


import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
basePackages = {"com.app.repo"})
@Order(99)
public class DatabaseConfiguration {
	@Autowired
	private Environment env;
	
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource oauthDataSource() {
     	return DataSourceBuilder.create().build();
    }
	
	@Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ds1EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(oauthDataSource());
 
        // Scan Entities in Package:
        em.setPackagesToScan(new String[] { "com.app.model" });
        em.setPersistenceUnitName("PERSITENCE_UNIT_NAME_1"); // Important !!
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<String,Object>();
        // JPA & Hibernate
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect.1"));
        properties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql.1"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto.1"));
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }
	
	 public PlatformTransactionManager ds1TransactionManager() {
		    JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(ds1EntityManager().getObject());
	        return transactionManager;
	    }
	  

	  @Bean(name="jdbctokenstore") 
	  public TokenStore tokenStore() { 
		  TokenStore tokenStore = new  JdbcTokenStore(oauthDataSource());
		  return tokenStore; 
	  }
	
}
