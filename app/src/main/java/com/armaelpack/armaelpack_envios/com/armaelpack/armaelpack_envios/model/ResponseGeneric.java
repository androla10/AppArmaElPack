package com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model;

/**
 * Created by ADRIAN on 25/11/2017.
 */

public class ResponseGeneric {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
