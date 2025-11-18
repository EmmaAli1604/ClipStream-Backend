package com.clipstream.clipstream_api.dto;

public class CortometrajeResponse {
    private boolean success;
    private String message;
    private Object data;

    public CortometrajeResponse() {}

    public CortometrajeResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CortometrajeResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}