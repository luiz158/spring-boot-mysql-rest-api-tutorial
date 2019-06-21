package com.controle.epi.model;

public class EventResponse {
    private boolean success;
    private String message;

    public EventResponse(boolean success) {
        this.success = success;
        this.message = "";
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    
    public void concatMessage(String epiName) {
        if(this.message.length() == 0)
            this.message = "Você não está utilizando: ";
        this.message += epiName + ", ";
    }
}
