package com.sistearth.view.request;

public class PayloadException extends Exception {
    public PayloadException(String message) {
        super(message);
    }

    public PayloadException(String message, Throwable cause) {
        super(message, cause);
    }
}
