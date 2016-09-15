package com.alterrae.api.business;

public interface BusinessResponse<T> {
    Object handle(T value);
}
