package com.peafunk.spring4rest.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.peafunk.spring4rest")
@PropertySource("classpath:Spring4RestService.properties")
@Import(DatabaseConfig.class)
public class Spring4RestServiceConfig {
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


	// Set the root folder of the spring4rest fragments
	@Value("${spring4rest.service.rootfolder}")
	String path;
	
	@Bean
	public File getRootFolder(){		
		File rootFolder = new File(path);
		return rootFolder;
	}
	
}
