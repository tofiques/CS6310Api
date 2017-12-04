package com.dublabs.Domain;

/**
 * Created by tofiques on 12/4/17.
 */
public class ResponseMessage < T>{
    public ResponseMessage(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public T response;
    public String  error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
