package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import com.sistearth.core.routes.PostsGetRoute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostsGetRouteTest {
    @Test
    public void testGet() throws Exception {
        Handler handler = new PostsGetRoute();
        assertEquals(new Answer(200, "Posts"), handler.process());
    }
}