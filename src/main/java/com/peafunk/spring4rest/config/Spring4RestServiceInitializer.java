package com.peafunk.spring4rest.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Spring4RestServiceInitializer extends AbstractAnnotationConfigDispatcherServletInitializer   {
	
	   @Override
	    protected Class<?>[] getRootConfigClasses() {
	        return new Class[] { Spring4RestServiceConfig.class };
	    }
	  
	    @Override
	    protected Class<?>[] getServletConfigClasses() {
	        return null;
	    }
	  
	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/Spring4RestService/*" };
	    }
	 
	    /*@Override
		protected Filter[] getServletFilters() {
			return new Filter[]{
					new CorsFilter()
					};
		}*/

}
