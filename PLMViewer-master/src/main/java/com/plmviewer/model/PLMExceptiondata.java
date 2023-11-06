/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plmviewer.model;

/**
 *
 * @author JA306771
 */
public class PLMExceptiondata {
    private String exceptionmessage = null;
    private String statuscode = null;
    StackTraceElement stacktace[] = null;

    public String getExceptionmessage() {
        return exceptionmessage;
    }

    public void setExceptionmessage(String exceptionmessage) {
        this.exceptionmessage = exceptionmessage;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public StackTraceElement[] getStacktace() {
        return stacktace;
    }

    public void setStacktace(StackTraceElement[] stacktace) {
        this.stacktace = stacktace;
        
    }

   
    
}
