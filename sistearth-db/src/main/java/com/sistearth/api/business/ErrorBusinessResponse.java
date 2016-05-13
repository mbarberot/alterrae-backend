package com.sistearth.api.business;

import com.sistearth.api.misc.Errors;

public interface ErrorBusinessResponse extends BusinessResponse<Errors> {
    @Override
    Object handle(Errors value);
}
