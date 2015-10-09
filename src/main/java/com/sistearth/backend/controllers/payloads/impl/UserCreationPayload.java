package com.sistearth.backend.controllers.payloads.impl;

import static org.eclipse.jetty.util.StringUtil.isNotBlank;

public class UserCreationPayload extends UserPayload {
    @Override
    public boolean isValid() {
        return isNotBlank(username)
                && isNotBlank(email)
                && isNotBlank(password);
    }
}
