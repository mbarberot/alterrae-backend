package com.sistearth.view.response.json;

import com.sistearth.db.api.entity.User;
import org.junit.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class LoginViewTest {
    @Test
    public void testRender() throws Exception {
        JsonLoginView view = new JsonLoginView();
        view.setUser(new User("57595f70fc13ae7c88001bed", "jon", "winterfell", "jon@snow.com"));
        view.setToken("my-token");

        assertEquals(
                "{ username: \"jon\", token: \"my-token\" }",
                view.render(),
                STRICT
        );
    }
}