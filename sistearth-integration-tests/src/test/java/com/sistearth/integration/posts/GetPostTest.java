package com.sistearth.integration.posts;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetPostTest {
    @Test
    public void testGetPost() throws Exception {
        restApi()
                .when()
                .get("/api/posts/9723f94d-3c02-48ca-8dc1-ffb0913d9d23")
                .then()
                .contentType("application/json")
                .body("data.id", equalTo("9723f94d-3c02-48ca-8dc1-ffb0913d9d23"));
    }
}
