package com.sistearth.backend.controllers.payloads;

import com.sistearth.backend.utils.Error;

import java.util.List;

public interface Payload<T> {
    boolean isValid();
    T getEntity();
    List<Error> getErrors();
}
