package com.sistearth.view.request;

import com.sistearth.db.beans.Error;

import java.util.List;

public interface Payload<T> {
    boolean isValid();
    T getEntity();
    List<Error> getErrors();
}
