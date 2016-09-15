package com.alterrae.api.business;

import com.alterrae.api.misc.Errors;

public interface ErrorBusinessResponse extends BusinessResponse<Errors> {
    @Override
    Object handle(Errors value);
}
