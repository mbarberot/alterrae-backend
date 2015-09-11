package com.sistearth.core.serializers;

import com.sistearth.core.models.Post;
import com.sistearth.core.models.User;
import com.sistearth.test.TestUtils;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Date;
import java.util.GregorianCalendar;

import static com.sistearth.test.TestUtils.createPost;
import static com.sistearth.test.TestUtils.createUser;
import static com.sistearth.test.TestUtils.serialize;

public class JSONApiPostBuilderTest {

    @Test
    public void testBuild() throws Exception {
        User user = createUser(1, "foo", "secret", "foo@bar.com");
        Post post = createPost(1, "Lorem Ipsum", "Dolor sit amet", new GregorianCalendar(2015,1,1,12,0,0).getTime(), user.getId());

        String expectedJson = "{ data: { " +
                "type: 'post', id: '1', " +
                "attributes: { " +
                "title: 'Lorem Ipsum', body: 'Dolor sit amet', created_at: '2015-01-01 12:00:00' " +
                "}}}";

        JSONAssert.assertEquals(
                expectedJson,
                serialize(new JSONApiPostBuilder(post, user).build()),
                JSONCompareMode.STRICT
        );
    }


}