package com.peafunk.spring4rest.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peafunk.spring4rest.dao.Spring4RestServiceDAOImpl;
import com.peafunk.spring4rest.exception.Spring4RestServiceDataException;
import com.peafunk.spring4rest.exception.Spring4RestServiceFolderNotFoundException;
import com.peafunk.spring4rest.model.Spring4RestServiceRequest;
import com.peafunk.spring4rest.model.Spring4RestServiceResponse;
import com.peafunk.spring4rest.retrieval.file.BucketFolderNotFoundException;
import com.peafunk.spring4rest.retrieval.file.ContentFragmentRetriever;
import com.peafunk.spring4rest.retrieval.file.RenderingFolderNotFoundException;

@Service
public class Spring4RestServiceImpl implements Spring4RestService {

    private final Logger logger = LoggerFactory.getLogger(Spring4RestServiceImpl.class);    
	@Autowired
	Spring4RestServiceDAOImpl Spring4RestServiceDAOImpl;
	@Autowired
	File rootFolder;

	/**
	 * Looks up the bucketID and renderingID for a passed ad_override value
	 * @return
	 */
	@Override
	public Spring4RestServiceRequest getIdsForAdOverride(String adOverrideValue) throws Spring4RestServiceDataException{		
		Spring4RestServiceRequest bgRequest = Spring4RestServiceDAOImpl.getBucketIds(adOverrideValue);		
		return bgRequest;
	}
	
	
	/**
	 * Gets bucket fragments based on bucket ID and rendering type ID
	 * @param bucketId
	 * @return String fragment
	 */	
	@Override
	public Spring4RestServiceResponse getFragmentsByBucketIdAndRenderingId(int bucketId, int renderingId) throws Spring4RestServiceFolderNotFoundException {
		
		Spring4RestServiceResponse response = new Spring4RestServiceResponse();
		response.setBucketId(bucketId);
		response.setRenderingId(renderingId);
		
		try {			
			//Determine number of files in directory
			int fileCount = ContentFragmentRetriever.getFragmentCountByBucketIdAndRenderingId(rootFolder, bucketId, renderingId);
			if(fileCount==0){
				throw new Spring4RestServiceFolderNotFoundException(bucketId, null);				
			}else{
				response.setFileFragments(getFileFragments(fileCount, rootFolder, bucketId, renderingId));
			}
			
		} catch (BucketFolderNotFoundException e) {
			logger.error("Exception looking up bucket folder : " + e.getMessage());
			throw new Spring4RestServiceFolderNotFoundException(e.getMessage(), "Exception looking up bucket folder", e);
			
		} catch (RenderingFolderNotFoundException e) {
			logger.error("Exception looking up rendering folder : " + e.getMessage());
			throw new Spring4RestServiceFolderNotFoundException(e.getMessage(), "Exception looking up rendering folder", e);
		}		
		
		return response;
	}	

	
	@Override
	public Spring4RestServiceResponse getFragmentsByBucketNameAndRenderingId(String bucketName, int renderingId) throws Spring4RestServiceFolderNotFoundException {
		// TODO Implement this
		return null;
	}


	@Override
	public Spring4RestServiceResponse getFragmentsByBucketIdAndRenderingName(int bucketId, int renderingName) throws Spring4RestServiceFolderNotFoundException {
		// TODO Implement this
		return null;
	}
	
	
	/**
	 * Gets files in directory based on file count
	 * @param fileCount, rootFolder, bucketId, renderingId
	 * @return
	 * @throws BucketFolderNotFoundException RenderingFolderNotFoundException
	 */
	private Map<Integer,String> getFileFragments(int fileCount, File rootFolder, int bucketId, int renderingId) throws BucketFolderNotFoundException, RenderingFolderNotFoundException{
		
		if(fileCount == 1){
			File file = ContentFragmentRetriever.getFragmentByBucketIdAndRenderingId(rootFolder, bucketId, renderingId);
			return readFileFragment(file);
			
		}else{
			File[] files = ContentFragmentRetriever.getFragmentsByBucketIdAndRenderingId(rootFolder, bucketId, renderingId);
			return readFileFragments(files);
		}
		
	}
	
	
	/**
	 * Gets file fragment for single file bucket
	 * @return Map<Integer,String>
	 */
	private Map<Integer,String> readFileFragment(File file){
		Map<Integer,String> fragments = new HashMap<Integer,String>();
		fragments.put(Integer.parseInt(file.getName()), reader.fileToString(file));
		return fragments;		
	}
	
	
	/**
	 * Gets file fragments for multiple file bucket
	 * @return
	 */
	private Map<Integer,String> readFileFragments(File[] files){
		Map<Integer,String> fragments = new HashMap<Integer,String>();
		for(File file : files){
			fragments.put(Integer.parseInt(file.getName()), reader.fileToString(file));
		}
		return fragments;
	}
	
	
	/**
	 * Function to convert file contents to String
	 */
	FileReader reader = (File file) -> {

	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    //String lineSeparator = System.getProperty("line.separator");	
	    Scanner fileScanner = null;
		try {
		    fileScanner = new Scanner(file);	
	        while(fileScanner.hasNextLine()) {        
	            fileContents.append(fileScanner.nextLine());
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fileScanner.close();
	    }
        return fileContents.toString();
	};

	
}
