package com.example.bookstore.exception;



import java.util.Date;

public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.timestamp = new Date();
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}