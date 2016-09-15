package com.alterrae.db.api.manager;

import lombok.Getter;

@Getter
public class ModelException extends Exception {
    private String code;

    public ModelException(String message) {
        super(message);
    }

    public ModelException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ModelException(String message, String code) {
        super(message);
        this.code = code;
    }
}
