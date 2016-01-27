package com.sistearth.view.request.payloads;

import com.sistearth.db.beans.Error;

import java.util.List;
import java.util.Objects;

public class PayloadTest {
    protected boolean hasError(List<Error> errors, String errorTitle) {
        return errors.stream()
                .anyMatch(error -> Objects.equals(error.getTitle(), errorTitle));
    }
}
