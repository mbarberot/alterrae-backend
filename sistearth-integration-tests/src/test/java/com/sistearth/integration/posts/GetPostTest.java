package com.sistearth.integration.posts;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetPostTest {
    @Test
    public void testGetPost() throws Exception {
        restApi()
                .when()
                .get("/api/posts/1")
                .then()
                .contentType("application/json")
                .body("data.id", equalTo("1"));
    }
}
