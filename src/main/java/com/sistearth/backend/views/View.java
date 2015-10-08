package com.sistearth.backend.views;

public interface View<T> {
    String render(T bean) throws ViewException;
}
