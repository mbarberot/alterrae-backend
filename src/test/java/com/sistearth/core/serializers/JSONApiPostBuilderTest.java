package com.sistearth.core.serializers;

import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.views.legacy.JSONApiPostBuilder;
import com.sistearth.backend.views.legacy.JSONApiUserBuilder;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.GregorianCalendar;

import static com.sistearth.test.TestUtils.*;

public class JSONApiPostBuilderTest {

    @Test
    public void testBuild() throws Exception {
        User user = createUser(1, "foo", "secret", "foo@bar.com");
        Post post = createPost(1, "Lorem Ipsum", "Dolor sit amet", new GregorianCalendar(2015, 0, 1, 12, 0, 0).getTime(), user.getId());

        String expectedJson = "{ " +
                "data: [{ " +
                "   type: 'posts', id: '1', " +
                "   attributes: { " +
                "       title: 'Lorem Ipsum', body: 'Dolor sit amet', created_at: '2015-01-01 12:00:00' " +
                "   }, relationships: { " +
                "       author: { data: { id: '1', type: 'users' } }" +
                "   }" +
                "}], " +
                "included: [ " +
                serialize(new JSONApiUserBuilder().buildData(user).build()) +
                "]" +
                "}";

        JSONAssert.assertEquals(
                expectedJson,
                serialize(new JSONApiPostBuilder().build(post, user)),
                JSONCompareMode.STRICT
        );
    }


}