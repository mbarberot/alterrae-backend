package com.alterrae.view.response.jsonapi;

import com.alterrae.api.beans.Error;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonApiErrorViewTest {

    @Test
    public void testRenderSingle_WithStatus() throws Exception {
        assertEquals(
                "{\"errors\":[{\"title\":null,\"status\":\"400\"}]}",
                new JsonApiErrorView("400").render(),
                STRICT
        );
    }

    @Test
    public void testRenderSingle_WithStatusAndTitle() throws Exception {
        assertEquals(
                "{\"errors\":[{\"title\":\"Not found\",\"status\":\"400\"}]}",
                new JsonApiErrorView("400", "Not found").render(),
                STRICT
        );
    }

    @Test
    public void testRenderSingle_WithError() throws Exception {
        assertEquals(
                "{\"errors\":[{\"title\":\"Not found\",\"status\":\"400\"}]}",
                new JsonApiErrorView(newArrayList(new Error("400", "Not found"))).render(),
                STRICT
        );
    }
}