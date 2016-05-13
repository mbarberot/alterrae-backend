package com.sistearth.api.business;

public interface BusinessResponse<T> {
    Object handle(T value);
}
