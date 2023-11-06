package com.tapestry.moic.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.tapestry.moic.response.interfaces.MoicResponse;

public class JobsSuccessResponse implements MoicResponse, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2306786338001266085L;

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
	 * @param status
	 * @param statusCode
	 * @param message
	 */
	public JobsSuccessResponse(boolean status, HttpStatus statusCode, String message) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JobsSuccessResponse [status=" + status + ", statusCode=" + statusCode + ", message=" + message + "]";
	}

	@Override
	public boolean getStatus() {
		return this.status;
	}
    
    

}
