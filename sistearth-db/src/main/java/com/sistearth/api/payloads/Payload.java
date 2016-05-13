package com.sistearth.api.payloads;

import com.sistearth.api.beans.Error;

import java.util.List;

public interface Payload<T> {
    boolean isValid();
    T getEntity();
    List<Error> getErrors();
}
