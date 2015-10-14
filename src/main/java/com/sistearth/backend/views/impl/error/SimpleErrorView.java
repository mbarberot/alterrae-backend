package com.sistearth.backend.views.impl.error;

import com.sistearth.backend.utils.Error;

public class SimpleErrorView extends ErrorView {
    public SimpleErrorView(String status) {
        this.setErrors(new Error(status));
    }

    public SimpleErrorView(String status, String title) {
        this.setErrors(new Error(status, title));
    }
}
