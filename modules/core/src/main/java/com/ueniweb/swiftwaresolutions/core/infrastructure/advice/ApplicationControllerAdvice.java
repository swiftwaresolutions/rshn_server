package com.ueniweb.swiftwaresolutions.core.infrastructure.advice;


import com.ueniweb.swiftwaresolutions.core.bootstrap.data.ErrorHandler;
import com.ueniweb.swiftwaresolutions.core.bootstrap.data.ErrorInfo;
import com.ueniweb.swiftwaresolutions.infrastructure.exceptions.AbstractApplicationPlatformException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractApplicationPlatformException.class)
    public ResponseEntity<ErrorInfo> applicationExceptionHandler(AbstractApplicationPlatformException ex) {
        ErrorHandler handler = new ErrorHandler();
        return handler.handleException(ex);
    }
}
