package com.peafunk.spring4rest.service

import static org.junit.Assert.*

import org.springframework.beans.factory.annotation.Autowired

import com.peafunk.spring4rest.AbstractTest
import com.peafunk.spring4rest.model.Spring4RestServiceRequest
import com.peafunk.spring4rest.model.Spring4RestServiceResponse

/**
 * Tests service layer
 * @author pellington
 */
class Spring4RestServiceTest extends AbstractTest {
	
	@Autowired
	private Spring4RestServiceImpl serviceImpl
	
	def setup() {}

	def"Get Fragments base test"(){
		
		given:
			
		when:
			Spring4RestServiceResponse response = serviceImpl.getFragmentsByBucketIdAndRenderingId(bucketId, renderingId)
			
		then:
			assertNotNull(response)				
			assertEquals(response.getFileFragments().size(),7)
			
		where:
			bucketId = 1113
			renderingId = 5716	
			
	}
	
	def"Get ID's for adoverride test"(){
		
		given:
			
		when:
			Spring4RestServiceRequest request = serviceImpl.getIdsForAdOverride(adOverride)
			
		then:
			assertNotNull(request)
			assertEquals(request.getBucketId(),1113)
			
		where:
			adOverride = 'multitest'
			
	}
}
