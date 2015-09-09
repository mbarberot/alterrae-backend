package com.sistearth.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.core.models.User;
import com.sistearth.core.serializers.JsonSerializer;

public class TestUtils {
    public static User createUser(int id, String username, String password, String email) {
        User user = new User();
        user.setId(0);
        user.setUsername("foo");
        user.setPassword("secret");
        user.setEmail("foo@bar.com");
        return user;
    }

    public static String serialize(Object object) throws Exception {
        return new JsonSerializer().render(object);
    }
}
