package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import com.sistearth.core.routes.IndexGetRoute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IndexGetRouteTest {
    @Test
    public void testGet() throws Exception {
        Handler handler = new IndexGetRoute();
        assertEquals(new Answer(200, "Hello world !"), handler.process());
    }
}