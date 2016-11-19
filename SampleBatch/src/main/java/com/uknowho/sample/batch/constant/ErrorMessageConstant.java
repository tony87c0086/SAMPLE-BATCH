package com.uknowho.sample.batch.constant;

/**
 * This ErrorMessageConstant class defines error message constants.
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class ErrorMessageConstant {

	public ErrorMessageConstant() {
		
	}
	
	public static final String OBJECT_NOT_FOUND = 
			"Object not found.";

	// DB error message constants
	
	public static final String RECORD_NOT_FOUND = " Record(s) not found from database.";
	public static final String CONSTRAINT_FAILURE = "Record already exists in database."; 

	public static final String RECORD_SAVE_FAILURE = 
			"Error while processing saving record into database, "
			+ "id is not required for saving new record, "
			+ "while id is required for updating record.";
	public static final String RECORD_UPDATE_FAILURE = 
			"Error while processing updating record in database, "
			+ "id is required for updating record.";
	public static final String RECORD_MERGE_FAILURE = 
			"Error while processing merging record into database.";
	
	// Object mapping error message constants
	public static final String ENTITY_NOT_FOUND = "Entity is not found.";
	public static final String COPY_ENTITY_TO_MODEL_EXCEPTION = 
			"Error while copying entity to model.";
	
	public static final String MODEL_NOT_FOUND = "Model is not found.";
	public static final String COPY_ENTITY_FROM_MODEL_EXCEPTION = 
			"Error while copying entity from model.";
	
	public static final String CREATE_ENTITY_EXCEPTION = "Error while creating new entity.";
	
	public static final String MODEL_MAPPING_EXCEPTION = "Error while mapping object(s).";
	
	// Business logic error message constants
	public static final String TEMPLATE_NOT_FOUND = 
			"Template is not found from database for further processing request.";
	public static final String TEMPLATE_MODEL_NOT_FOUND = 
			"Template model is not found for further processing request.";
	
	public static final String NOTIFICATION_EMAIL_NOT_FOUND = 
			"Notification email is not found from database for further processing request.";
	public static final String NOTIFICATION_EMAIL_MODEL_NOT_FOUND = 
			"Notification email model is not found for further processing request.";

	// Normal exception error message constants 
	public static final String UNKNOWN_EXCEPTION = "Unknown error while processing request.";
	
}
