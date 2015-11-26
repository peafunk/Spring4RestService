package com.peafunk.spring4rest.model;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Spring4RestServiceResponse {

	private int bucketId;
	private int renderingId;
	private Map<Integer,String> fileFragments;
	//private Map<String,String> metaDataProperties;
	
	public int getBucketId() {
		return bucketId;
	}
	public void setBucketId(int bucketId) {
		this.bucketId = bucketId;
	}
	public int getRenderingId() {
		return renderingId;
	}
	public void setRenderingId(int renderingId) {
		this.renderingId = renderingId;
	}
	public Map<Integer, String> getFileFragments() {
		return fileFragments;
	}
	public void setFileFragments(Map<Integer, String> fileFragments) {
		this.fileFragments = fileFragments;
	}
	
	
}
