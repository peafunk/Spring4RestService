package com.peafunk.spring4rest.dao;

import java.sql.SQLException;

import com.peafunk.spring4rest.exception.Spring4RestServiceDataException;
import com.peafunk.spring4rest.model.Spring4RestServiceRequest;

public interface Spring4RestServiceDAO {

	public Spring4RestServiceRequest getBucketIds(String adOverrideValue) throws Spring4RestServiceDataException;
}
