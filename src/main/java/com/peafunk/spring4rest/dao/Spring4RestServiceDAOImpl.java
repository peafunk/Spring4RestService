package com.peafunk.spring4rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.peafunk.spring4rest.exception.Spring4RestServiceDataException;
import com.peafunk.spring4rest.model.Spring4RestServiceRequest;

@Repository
public class Spring4RestServiceDAOImpl implements Spring4RestServiceDAO {

	JdbcTemplate jdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(Spring4RestServiceDAOImpl.class);
    
    private final String GET_IDS_FROM_AD_OVERRIDE_SQL = "select BUCKET_ID, RENDERING_ID from BUCKET_RENDERING where TEMPLATE_ID = 48 AND OVERRIDE_TAG = ?";
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Looks up the bucketId and renderingId of an adOverride value in the BUCKET.BUCKET_RENDERING table
	 * Query with templateId 48, the ON-THE-GO template
	 * @return 
	 */
	@Override
	public Spring4RestServiceRequest getBucketIds(String adOverrideValue) throws Spring4RestServiceDataException {
		
		Spring4RestServiceRequest bgRequest = null;
		try{
			bgRequest = this.jdbcTemplate.queryForObject(
					GET_IDS_FROM_AD_OVERRIDE_SQL,
			        new Object[]{adOverrideValue},
			        new RowMapper<Spring4RestServiceRequest>() {
						@Override
						public Spring4RestServiceRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
			            	Spring4RestServiceRequest request = new Spring4RestServiceRequest();
			            	request.setBucketId(rs.getInt("BUCKET_ID"));
			            	request.setRenderingId(rs.getInt("RENDERING_ID"));
			                return request;
						}
			        });
		}catch(Exception e){
			logger.error("Exception looking up bucket/rendering ID's for ad. : " + e.getMessage());
			throw new Spring4RestServiceDataException(e.getMessage(), "Exception looking up ID's", e);
		}
		
		return bgRequest;
	}

}
