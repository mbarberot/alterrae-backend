package com.alterrae.view.response.jsonapi;

import com.alterrae.db.api.entity.User;
import org.junit.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonApiUserViewTest {

    @Test
    public void testRender() throws Exception {
        assertEquals(
                "{\"data\":{\"attributes\":{\"email\":\"jon@snow.com\",\"username\":\"Jon\"},\"id\":\"57595f70fc13ae7c88001bec\",\"type\":\"users\"}}",
                new JsonApiUserView(new User("57595f70fc13ae7c88001bec", "Jon", "winterfell", "jon@snow.com")).render(),
                STRICT
        );
    }
}