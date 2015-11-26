package com.peafunk.spring4rest.dao

import static org.junit.Assert.*

import org.springframework.beans.factory.annotation.Autowired

import com.peafunk.spring4rest.AbstractTest
import com.peafunk.spring4rest.model.Spring4RestServiceRequest

/**
 * Tests content service DAO layer
 * @author pellington
 */
class Spring4RestServiceDAOTest extends AbstractTest {
	
	@Autowired
	private Spring4RestServiceDAOImpl daoImpl
	
	def setup() {}

	def"Get IDs from Ad Test"(){
		
		given:
			
		when:
			Spring4RestServiceRequest request = daoImpl.getBucketIds(adOverride)
			
		then:
			assertNotNull(request)				
			assertEquals(request.bucketId,10056)
			
		where:
			adOverride = 'test123'		
			
	}
	
	
	def"Get IDs from Ad Test with ampersand"(){
		
		given:
			
		when:
			Spring4RestServiceRequest request = daoImpl.getBucketIds(adOverride)
			
		then:
			assertNotNull(request)			
			assertEquals(request.bucketId,10465)
			
		where:
			adOverride = 'johnson&johnson'
			
	}
	
}
