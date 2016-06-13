package com.sistearth.api.payloads;

import com.sistearth.api.beans.Error;

import java.util.List;

@Deprecated
public interface OldPayload<T> {
    boolean isValid();
    T getEntity();
    List<Error> getErrors();
}
