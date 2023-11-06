package com.tapestry.moic.response.interfaces;

import org.springframework.http.HttpStatus;

/**
 * The Interface MoicResponse.
 *
 * @version 0.0.1
 */
public interface MoicResponse {

	/**
	 * Method getStatus.
	 * 
	 * @return status boolean
	 */
	boolean getStatus();

	/**
	 * Method setStatus.
	 * 
	 * @param status boolean
	 */
	void setStatus(boolean status);

	/**
	 * Method getStatusCode.
	 * 
	 * @return {@link HttpStatus}
	 */
	HttpStatus getStatusCode();

	/**
	 * Method setStatusCode.
	 * 
	 * @param statusCode {@link HttpStatus}
	 */
	void setStatusCode(HttpStatus statusCode);

	/**
	 * Method getMessage.
	 * 
	 * @return message string
	 */
	String getMessage();

	/**
	 * Method setMessage.
	 * 
	 * @param message string
	 */
	void setMessage(String message);

}
