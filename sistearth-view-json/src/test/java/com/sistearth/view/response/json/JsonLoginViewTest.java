package com.sistearth.view.response.json;

import com.sistearth.api.beans.User;
import org.junit.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonLoginViewTest {
    @Test
    public void testRender() throws Exception {
        assertEquals(
                "{\"username\":\"Jon\",\"token\":\"jon-s-token\"}",
                new JsonLoginView(new User(1, "Jon", "jonsecret", "jon@dot.com"), "jon-s-token").render(),
                STRICT
        );
    }
}