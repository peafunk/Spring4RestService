package com.peafunk.spring4rest.service;

import java.io.File;

@FunctionalInterface
public interface FileReader {

	String fileToString(File file);
	
}
