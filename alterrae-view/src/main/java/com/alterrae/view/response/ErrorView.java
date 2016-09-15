package com.alterrae.view.response;

import com.alterrae.api.beans.Error;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


public abstract class ErrorView implements View {
    protected List<Error> errors;

    public ErrorView() {
    }

    public ErrorView(String status) {
        setErrors(new Error(status));
    }

    public ErrorView(String status, String title) {
        setErrors(new Error(status, title));
    }

    public ErrorView(List<Error> errors) {
        this.setErrors(errors);
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void setErrors(Error... errors) {
        this.setErrors(newArrayList(errors));
    }


}
