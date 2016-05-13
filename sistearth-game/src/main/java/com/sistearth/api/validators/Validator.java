package com.sistearth.api.validators;

import com.sistearth.api.beans.Error;

import java.util.List;

public interface Validator {

    boolean isValid();

    List<Error> getErrors();
}
