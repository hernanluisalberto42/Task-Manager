package com.example.demo.config;

import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomHandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomNotFoundException.class})
    public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex,message(ex, HttpStatus.BAD_REQUEST),new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

    private ApiError message(RuntimeException ex, HttpStatus badRequest) {
        return ApiError
                .builder()
                .status(badRequest.value())
                .message(ExceptionUtils.getRootCauseMessage(ex))
                .throwable(ex.getCause())
                .build();
    }


}
