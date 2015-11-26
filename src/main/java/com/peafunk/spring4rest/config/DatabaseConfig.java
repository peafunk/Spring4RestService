package com.peafunk.spring4rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

	@Bean
    public DataSource dataSource() throws NamingException {
	    JndiTemplate jndiTemplate = new JndiTemplate();
	    DataSource dataSource = (DataSource) jndiTemplate.lookup("<JNDIName>");
        return dataSource;
    }
    
}