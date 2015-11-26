package com.peafunk.spring4rest.controller

import com.peafunk.spring4rest.AbstractTest

import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller
import javax.xml.transform.stream.StreamSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.http.MediaType

import spock.lang.Ignore;
import static org.junit.Assert.*

/**
 * Tests content service endpoints
 * @author pellington
 */
class Spring4RestServiceControllerTest extends AbstractTest {
	
	@Autowired
	private WebApplicationContext wac
	private MockMvc mockMvc
	private MockHttpSession mockSession
	
	def setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
		mockSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
	}
	
	
	/**
	 * Tests if controller can be reached
	 * @return
	 */
	def"Base controller Test"(){
		
		given:
			
		when:
			ResultActions result=mockMvc.perform(MockMvcRequestBuilders.get("/v1/health"))			
			
			MockHttpServletResponse mockResponse = result.andReturn().getResponse()
			def res = mockResponse.content.toString()
			println(res)
			
		then:
			assertTrue(res.startsWith('Spring4RestService Healthchecks'))			
			
	}
	
	
	/**
	 * Tests calling main endpoint
	 * @return
	 */
	def"Get Fragment for bucketID and renderingID"(){
		
		given:
			
		when:
			ResultActions result=mockMvc.perform(MockMvcRequestBuilders.get("/v1/getFragment")
				.param("bucketId",bucketId)
				.param("renderingId",renderingId))			
			
			MockHttpServletResponse mockResponse = result.andReturn().getResponse()
			def res = mockResponse.content.toString()
			println(res)
			
		then:
			assertNotNull(res)
			
		where:
			bucketId = '10056'
			renderingId = '16141'
	}
	
	
	/**
	 * Tests getting ID's from addoverride
	 * @return
	 */
	def"Get ID's from addoverride"(){
		
		given:
			
		when:
			ResultActions result=mockMvc.perform(MockMvcRequestBuilders.get("/v1/getFragmentForAd")
				.param("adOverride","test123"))			
			
			MockHttpServletResponse mockResponse = result.andReturn().getResponse()
			def res = mockResponse.content.toString()
			println(res)
			
		then:
			assertNotNull(res)
	}
}
