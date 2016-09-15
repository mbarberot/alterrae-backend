package com.alterrae.api.misc;

import com.alterrae.api.beans.Error;

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
