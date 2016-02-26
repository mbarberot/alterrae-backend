package com.sistearth.integration.posts;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class GetPostsTest {
    @Test
    public void testGetPosts() throws Exception {
        restApi()
                .when()
                .get("/api/posts")
                .then()
                .contentType("application/json")
                .body("data.id", hasItems("1","2"));
    }
}
