package com.alterrae.integration.posts;

import org.junit.Test;

import static com.alterrae.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class GetPostsTest {
    @Test
    public void testGetPosts() throws Exception {
        restApi()
                .when()
                .get("/api/posts")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("data.id", hasItems("57581184c35c1c060094d794", "57581171c35c1c060094d790"));
    }
}
