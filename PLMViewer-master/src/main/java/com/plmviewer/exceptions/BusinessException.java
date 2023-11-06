/**
 * 
 */
package com.plmviewer.exceptions;

/**
 * @author uthanasekarapandian
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private String exceptionMsg = null;
	 
    public BusinessException() {
        super();
    }
 
    public BusinessException(String message) {
        super(message);
        this.exceptionMsg = message;
    }
 
    public BusinessException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return exceptionMsg;
    }
 
    public String getExceptionMsg(){
        return this.exceptionMsg;
     }
     
     public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
     }
}

	

