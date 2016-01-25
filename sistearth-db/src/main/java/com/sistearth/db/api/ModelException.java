package com.sistearth.db.api;

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
