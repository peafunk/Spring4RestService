package com.peafunk.spring4rest.service;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Spring4RestHealthCheckerImpl implements Spring4RestHealthChecker {

    private final Logger logger = LoggerFactory.getLogger(Spring4RestHealthCheckerImpl.class);
    
    JdbcTemplate jdbcTemplate;
    @Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
    
	@Override
	public String doServiceHealthCheck() throws Exception {

		StringBuilder healthCheckResponse = new StringBuilder();
		healthCheckResponse.append("Spring4RestService Healthchecks\n");
		healthCheckResponse.append("-----------------------------------\n\n");
				
		//Check DB connection
		long startTime = System.currentTimeMillis();
		int result = jdbcTemplate.queryForObject("select 1 from dual", Integer.class);
		long endTime = System.currentTimeMillis();

		if(result==1){
			healthCheckResponse.append("DB connection test : PASS\n");
		}else{
			healthCheckResponse.append("DB connection test : FAIL\n");
		}
		healthCheckResponse.append("DB connection test took " + (endTime - startTime) + " milliseconds");
		
		return healthCheckResponse.toString();
		
	}    

	
}
