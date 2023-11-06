package com.plmviewer.controller;

import com.plmviewer.model.PLMExceptiondata;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    PLMExceptiondata handleCustomException(Exception ex) {
        PLMExceptiondata exceptionmodel = new PLMExceptiondata();
        exceptionmodel.setExceptionmessage(ex.getMessage());
        exceptionmodel.setStacktace(ex.getStackTrace());
        return exceptionmodel;
    }
    
    
}
