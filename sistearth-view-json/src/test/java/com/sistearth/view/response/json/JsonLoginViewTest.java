package com.sistearth.view.response.json;

import com.sistearth.db.api.entity.User;
import org.junit.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonLoginViewTest {
    @Test
    public void testRender() throws Exception {
        assertEquals(
                "{\"username\":\"Jon\",\"token\":\"jon-s-token\"}",
                new JsonLoginView(new User("57595f70fc13ae7c88001bec", "Jon", "jonsecret", "jon@dot.com"), "jon-s-token").render(),
                STRICT
        );
    }
}