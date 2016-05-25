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
                .body("data.id", hasItems("c55bc5a6-114e-48e0-a4c6-eb1dad55a979", "9723f94d-3c02-48ca-8dc1-ffb0913d9d23"));
    }
}
