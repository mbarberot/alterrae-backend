package com.sistearth.backend.views;

public interface View<T> {
    String renderBean(T bean) throws ViewException;
}
