package com.ueniweb.swiftwaresolutions.core.bootstrap.data;

import com.ueniweb.swiftwaresolutions.core.exception.*;
import com.ueniweb.swiftwaresolutions.infrastructure.exceptions.AbstractApplicationPlatformException;
import com.ueniweb.swiftwaresolutions.infrastructure.exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorHandler {

    public ResponseEntity<ErrorInfo> handleException(AbstractApplicationPlatformException ex) {
        final ErrorInfo errorInfo = new ErrorInfo();
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        if(ex instanceof AuthenticationException) {
            errorInfo.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        if(ex instanceof InvalidMailException){
            errorInfo.setStatusCode(HttpStatus.FORBIDDEN.value());
        }
        if(ex instanceof TimeLimitException){
            errorInfo.setStatusCode(HttpStatus.GATEWAY_TIMEOUT.value());
        }
        if(ex instanceof DuplicateRecordException){
            errorInfo.setStatusCode(HttpStatus.CONFLICT.value());
        }
        if(ex instanceof NoRecordFoundException){
            errorInfo.setStatusCode(HttpStatus.FORBIDDEN.value());
        }
        if(ex instanceof PlatformApiDataValidationException){
            errorInfo.setStatusCode(HttpStatus.NO_CONTENT.value());
            errorInfo.setFieldErrors(((PlatformApiDataValidationException) ex).getErrors());
        }
        errorInfo.setErrorCode(ex.getGlobalisationMessageCode());
        errorInfo.setMessage(ex.getDefaultUserMessage());
        errorInfo.setFields(ex.getDefaultUserMessageArgs());
        return new ResponseEntity<>(errorInfo, status);
    }
}
