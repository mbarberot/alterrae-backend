package com.sistearth.core.serializers;

import com.sistearth.core.models.User;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.sistearth.test.TestUtils.createUser;
import static com.sistearth.test.TestUtils.serialize;

public class JSONApiUserBuilderTest {

    @Test
    public void testBuild() throws Exception {
        User user = createUser(0, "foo", "secret", "foo@bar.com");
        String expectedJson = "{ data : [{ " +
                "type: 'users', id: '0', " +
                "attributes: { username: 'foo', email: 'foo@bar.com' } " +
                "}]}";
        JSONAssert.assertEquals(expectedJson, serialize(new JSONApiUserBuilder().build(user)), JSONCompareMode.STRICT);
    }
}