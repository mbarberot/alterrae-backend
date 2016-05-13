package com.sistearth.api.business;

import com.sistearth.api.beans.Error;
import com.sistearth.api.misc.Errors;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class BusinessPromise<T> {

    private BusinessResponse<T> successResponse;
    private ErrorBusinessResponse errorResponse;
    protected T entity;
    protected List<Error> errors;

    protected BusinessPromise() {
        entity = null;
        errors = newArrayList();
    }

    public BusinessPromise onSuccess(BusinessResponse<T> successResponse) {
        this.successResponse = successResponse;
        return this;
    }

    public BusinessPromise onFailure(ErrorBusinessResponse errorResponse) {
        this.errorResponse = errorResponse;
        return this;
    }

    public Object resolve() {
        doIt();

        if (isSuccessful()) {
            return successResponse.handle(entity);
        } else {
            return errorResponse.handle(new Errors(errors));
        }
    }

    private boolean isSuccessful() {
        return errors.isEmpty();
    }

    protected abstract void doIt();

}
