package com.peafunk.spring4rest.service;

import com.peafunk.spring4rest.exception.Spring4RestServiceDataException;
import com.peafunk.spring4rest.exception.Spring4RestServiceFolderNotFoundException;
import com.peafunk.spring4rest.model.Spring4RestServiceRequest;
import com.peafunk.spring4rest.model.Spring4RestServiceResponse;

public interface Spring4RestService {

	public Spring4RestServiceRequest getIdsForAdOverride(String adOverrideValue) throws Spring4RestServiceDataException;
	
	public Spring4RestServiceResponse getFragmentsByBucketIdAndRenderingId(int bucketId, int renderingId) throws Spring4RestServiceFolderNotFoundException;

	public Spring4RestServiceResponse getFragmentsByBucketNameAndRenderingId(String bucketName, int renderingId) throws Spring4RestServiceFolderNotFoundException;

	public Spring4RestServiceResponse getFragmentsByBucketIdAndRenderingName(int bucketId, int renderingName) throws Spring4RestServiceFolderNotFoundException;
	
}
