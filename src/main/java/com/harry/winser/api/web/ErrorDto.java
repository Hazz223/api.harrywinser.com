package com.harry.winser.api.web;

public class ErrorDto {

    private Integer statusCode;
    private String errorMessage;

    public ErrorDto(Integer statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
