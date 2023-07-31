package com.example.delivery.exception.advice;

import lombok.Getter;

@Getter
public class ApiErrorResponse {

    private String code;
    private String message;

    public ApiErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
