package com.peafunk.spring4rest.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("unit-test")
public class TestDatabaseConfig extends DatabaseConfig {

	/**
	 * Overriding dataSource for testing
	 * @return
	 * @throws NamingException
	 */
	@Bean
	public DataSource dataSource() throws NamingException {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("<driver>");
		dataSource.setUrl("<host>");
		dataSource.setUsername("<username>");
		dataSource.setPassword("<password>");
		return dataSource;
	}
    
}