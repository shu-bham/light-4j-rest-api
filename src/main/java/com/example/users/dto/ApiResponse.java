package com.example.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }


}
