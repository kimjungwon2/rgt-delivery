package com.example.delivery.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiErrorResponse bindExHandler(BindException e) {
        log.error("[BindException]",e);

        return new ApiErrorResponse("BAD REQUEST",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiErrorResponse illegalExHandler(IllegalArgumentException e){
        log.error("[IllegalArgumentException] ex",e);
        return new ApiErrorResponse("BAD REQUEST",e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiErrorResponse httpRequestExHandler(HttpRequestMethodNotSupportedException e) {
        log.error("[HttpRequestMethodNotSupportedException] ex",e);

        return new ApiErrorResponse("BAD REQUEST",e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ApiErrorResponse allExHandler(Exception e){
        log.error("[INTERNAL_SERVER_ERROR] ex",e);
        return new ApiErrorResponse("Server Error","Error");
    }

}
