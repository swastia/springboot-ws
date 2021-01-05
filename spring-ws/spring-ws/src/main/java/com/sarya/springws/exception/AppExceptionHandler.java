package com.sarya.springws.exception;

import com.sarya.springws.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest wr){

        String customErrMsg = e.getLocalizedMessage();
        if(customErrMsg == null) customErrMsg = e.toString();
        ErrorMessage errMsg = new ErrorMessage(new Date(), customErrMsg);
        return new ResponseEntity<>(errMsg, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
    }
}
