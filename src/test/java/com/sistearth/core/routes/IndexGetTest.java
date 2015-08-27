package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IndexGetTest {
    @Test
    public void testGet() throws Exception {
        Handler handler = new IndexGet();
        assertEquals(new Answer(200, "Hello world !"), handler.process(null)); // Todo replace null
    }
}