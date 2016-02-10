package com.sistearth.view.response.jsonapi;

import com.sistearth.db.beans.User;
import org.junit.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonApiUserViewTest {

    @Test
    public void testRender() throws Exception {
        assertEquals(
                "{\"data\":{\"attributes\":{\"email\":\"jon@snow.com\",\"username\":\"Jon\"},\"id\":\"1\",\"type\":\"users\"}}",
                new JsonApiUserView(new User(1, "Jon", "winterfell", "jon@snow.com")).render(),
                STRICT
        );
    }
}