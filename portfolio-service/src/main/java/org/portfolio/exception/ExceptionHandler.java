package org.portfolio.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ExceptionHandler {
	
	/**
	 * Method to return the failure message.
	 * 
	 * @param e
	 * @return
	 */
	public static Map<String, Object> gotFailure(Exception e) {
		Map<String, Object> failure = new HashMap<String, Object>();
		failure.put("status", "failure");
		failure.put("reason", e.getMessage());
		return failure;
	}
	
	public static HttpStatus getStatus(Exception e) {
		if(e.getMessage().equals("Data Not Found")) {
			return HttpStatus.NOT_FOUND;
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
