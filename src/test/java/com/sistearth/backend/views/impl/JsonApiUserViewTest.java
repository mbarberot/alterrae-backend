package com.sistearth.backend.views.impl;

import org.junit.Test;

import static com.sistearth.backend.utils.TestUtils.createUser;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonApiUserViewTest {

    @Test
    public void testRender() throws Exception {
        JsonApiUserView view = new JsonApiUserView();
        view.setUser(createUser(1, "Jon", "winterfell", "jon@snow.com"));
        assertEquals(
                "{ \"data\" : { \"attributes\" : { \"email\" : \"jon@snow.com\", \"username\" : \"Jon\" }, \"id\" : \"1\", \"type\" : \"users\" } }",
                view.render(),
                STRICT
        );
    }
}