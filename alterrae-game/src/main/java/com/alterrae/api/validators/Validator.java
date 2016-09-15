package com.alterrae.api.validators;

import com.alterrae.api.beans.Error;

import java.util.List;

public interface Validator {

    boolean isValid();

    List<Error> getErrors();
}
