package com.sistearth.backend.controllers.payloads;

public interface Payload<T> {
    boolean isValid();
    T getEntity();
}
