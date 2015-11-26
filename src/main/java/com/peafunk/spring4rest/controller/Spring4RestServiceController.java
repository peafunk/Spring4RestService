package com.peafunk.spring4rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peafunk.spring4rest.exception.Spring4RestServiceDataException;
import com.peafunk.spring4rest.exception.Spring4RestServiceFolderNotFoundException;
import com.peafunk.spring4rest.model.Spring4RestServiceRequest;
import com.peafunk.spring4rest.model.Spring4RestServiceResponse;
import com.peafunk.spring4rest.service.spring4restHealthCheckerImpl;
import com.peafunk.spring4rest.service.Spring4RestServiceImpl;


@RestController
@RequestMapping("/v1")
public class Spring4RestServiceController {
	
	@Autowired
	Spring4RestServiceImpl Spring4RestServiceImpl;
	@Autowired
	Spring4RestHealthCheckerImpl spring4restHealthCheckerImpl;
	
	/**
	 * Health check
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public void up(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String healthCheckResults = spring4restHealthCheckerImpl.doServiceHealthCheck();
		response.getOutputStream().write(healthCheckResults.getBytes());
	}
	
	
	/**
	 * Endpoint takes bucketID and renderingID and returns fragment
	 * @param bucketId
	 * @param renderingId
	 * @return String fragment
	 * @throws Spring4RestServiceFolderNotFoundException
	 * @throws Spring4RestServiceDataException
	 */
	@RequestMapping(value="/getFragment", method=RequestMethod.GET)
	public Spring4RestServiceResponse getBucket(@RequestParam("bucketId") String bucketId, @RequestParam("renderingId") String renderingId) throws Spring4RestServiceFolderNotFoundException, Spring4RestServiceDataException{

		try {
			Spring4RestServiceResponse response = Spring4RestServiceImpl.getFragmentsByBucketIdAndRenderingId(Integer.parseInt(bucketId), Integer.parseInt(renderingId));
			return response;
		} catch (NumberFormatException e) {
			throw new Spring4RestServiceDataException("Exception getting fragments. ", e.getMessage(), e);
		} 
		
	}
	
	
	/**
	 * Endpoint that takes ad_override value and returns fragment
	 * @param adOverride
	 * @return String fragment
	 * @throws Spring4RestServiceDataException
	 * @throws Spring4RestServiceFolderNotFoundException
	 */
	@RequestMapping(value="/getFragmentForAd", method=RequestMethod.GET)
	public Spring4RestServiceResponse getIds(@RequestParam("adOverride") String adOverride) throws Spring4RestServiceDataException, Spring4RestServiceFolderNotFoundException{
		
		Spring4RestServiceRequest bgRequest = new Spring4RestServiceRequest();
		bgRequest.setAdOverrideValue(adOverride);		
		bgRequest = Spring4RestServiceImpl.getIdsForAdOverride(adOverride);
		
		Spring4RestServiceResponse response = Spring4RestServiceImpl.getFragmentsByBucketIdAndRenderingId(bgRequest.getBucketId(), bgRequest.getRenderingId());
		
		return response;
	};
	
	
}
