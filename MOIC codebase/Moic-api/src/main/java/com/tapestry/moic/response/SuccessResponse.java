package com.tapestry.moic.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.tapestry.moic.response.interfaces.MoicResponse;



/**
 * The Class SuccessResponse.
 * 
 * @version 0.0.1
 */
public class SuccessResponse implements MoicResponse, Serializable {

    /**
     * Field status boolean.
     */
    private boolean status;

    /**
     * Field statusCode HttpStatus.
     */
    private HttpStatus statusCode;

    /**
     * Field message String.
     */
    private String message;

    /**
     * Field result Object.
     */
    private Object result;

    /**
     * Field serialVersionUID. (value is 5633725919087346901L) Long
     */
    private static final long serialVersionUID = 5633725919087346901L;

    /**
     * Parameterized Constructor
     * 
     * @param status     boolean
     * @param statusCode {@link HttpStatus}
     * @param message    string
     * @param result     object
     */
    public SuccessResponse(boolean status, HttpStatus statusCode, String message, Object result) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.result = result;
    }

    /**
     * Method getStatus.
     *  
     * @return status boolean
     **/
    @Override
    public boolean getStatus() {
    	return this.status;
    }

    /**
     * Method setStatus.
     * 
     * @param status boolean
     **/
    @Override
    public void setStatus(boolean status) {
    	this.status = status;
    }

    /**
     * Method getStatusCode.
     * 
     * @return statusCode {@link HttpStatus}
     **/
    @Override
    public HttpStatus getStatusCode() {
    	return this.statusCode;
    }

    /**
     * Method setStatusCode.
     * 
     * @param statusCode {@link HttpStatus}
     **/
    @Override
    public void setStatusCode(HttpStatus statusCode) {
    	this.statusCode = statusCode;
    }

    /**
     * Method getMessage.
     * 
     * @return message string
     **/
    @Override
    public String getMessage() {
    	return this.message;
    }

    /**
     * Method setMessage.
     * 
     * @param message string
     **/
    @Override
    public void setMessage(String message) {
    	this.message = message;
    }

    /**
     * Method getResult.
     * 
     * @return result object
     */
    public Object getResult() {
    	return this.result;
    }

    /**
     * Method setResult.
     * 
     * @param result object
     */
    public void setResult(Object result) {
    	this.result = result;
    }

    /**
     * Method toString.
     * 
     * @return string
     * 
     * @see java.lang.Object#toString()
     **/
    @Override
    public String toString() {
	return String.format("SuccessResponse [status=%s, statusCode=%s, message=%s, result=%s]", this.status,
		this.statusCode, this.message, this.result);
    }

}
