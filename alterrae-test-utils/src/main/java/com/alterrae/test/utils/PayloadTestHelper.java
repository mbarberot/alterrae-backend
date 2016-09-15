package com.alterrae.test.utils;

import com.alterrae.api.beans.Error;

import java.util.List;
import java.util.Objects;

public class PayloadTestHelper {
    public static boolean hasError(List<Error> errors, String errorTitle) {
        return errors.stream()
                .anyMatch(error -> Objects.equals(error.getTitle(), errorTitle));
    }
}
