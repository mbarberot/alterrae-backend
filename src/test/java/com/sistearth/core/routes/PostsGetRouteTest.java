package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostsGetRouteTest {
    @Test
    public void testGet() throws Exception {
        Handler handler = new PostsGetRoute();
        assertEquals(new Answer(200, "{\n" +
                "  \"posts\" : [ {\n" +
                "    \"id\" : 1,\n" +
                "    \"title\" : \"Lorem ipsum\",\n" +
                "    \"body\" : \"Dolor sit amet\"\n" +
                "  } ]\n" +
                "}"),
                handler.process());
    }
}