package com.sistearth.api.misc;

import com.sistearth.api.beans.Error;

import java.util.List;

public class Errors {
    private List<Error> errors;

    public Errors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
