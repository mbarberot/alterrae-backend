package com.sistearth.backend.views.impl;

import org.junit.Test;

import static com.sistearth.backend.utils.TestUtils.createUser;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class LoginViewTest {
    @Test
    public void testRender() throws Exception {
        LoginView view = new LoginView();
        view.setUser(createUser(0, "jon", "winterfell", "jon@snow.com"));
        view.setToken("my-token");

        assertEquals(
                "{ username: \"jon\", token: \"my-token\" }",
                view.render(),
                STRICT
        );
    }
}