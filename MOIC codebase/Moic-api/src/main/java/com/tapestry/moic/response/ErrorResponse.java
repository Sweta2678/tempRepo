package com.tapestry.moic.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.tapestry.moic.response.interfaces.MoicResponse;



/**
 * The Class ErrorResponse.
 *
 * @version 0.0.1
 */
public class ErrorResponse implements MoicResponse, Serializable {

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
     * Field serialVersionUID long.
     */
    private static final long serialVersionUID = 7353761154155400844L;

    /**
     * Parameterized Constructor
     * 
     * @param status     boolean
     * @param statusCode {@link HttpStatus}
     * @param message    string
     */
    public ErrorResponse(boolean status, HttpStatus statusCode, String message) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
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
     * Method toString.
     * 
     * @return string
     * 
     * @see java.lang.Object#toString()
     **/
    @Override
    public String toString() {
    	return String.format("ErrorResponse [status=%s, statusCode=%s, message=%s, result=%s]", this.status,
		this.statusCode, this.message);
    }

}
