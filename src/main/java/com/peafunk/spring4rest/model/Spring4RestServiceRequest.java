package com.peafunk.spring4rest.model;

import org.springframework.stereotype.Component;

@Component
public class Spring4RestServiceRequest {

	private int bucketId;
	private int renderingId;
	private String adOverrideValue;
	
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
	public String getAdOverrideValue() {
		return adOverrideValue;
	}
	public void setAdOverrideValue(String adOverrideValue) {
		this.adOverrideValue = adOverrideValue;
	}
	
	
}
