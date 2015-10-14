package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.utils.Error;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCreationPayloadTest {

    @Test
    public void testGetUser() throws Exception {
        User user = new User();
        user.setUsername("jon");
        user.setPassword("winterfell");
        user.setEmail("jon@snow.com");

        UserCreationPayload payload = new UserCreationPayload();
        payload.setUsername("jon");
        payload.setPassword("winterfell");
        payload.setEmail("jon@snow.com");

        assertEquals(user, payload.getEntity());
    }

    @Test
    public void testIsValid() throws Exception {
        UserCreationPayload payload = new UserCreationPayload();
        payload.setUsername("jon");
        payload.setPassword("winterfell");
        payload.setEmail("jon@snow.com");

        assertTrue(payload.isValid());
        assertEquals(0, payload.getErrors().size());
    }

    @Test
    public void notValidWhenMissingUsername() throws Exception {
        UserCreationPayload payload = new UserCreationPayload();
        payload.setPassword("winterfell");
        payload.setEmail("jon@snow.com");

        assertFalse(payload.isValid());
        assertEquals(new Error("400", "username"), payload.getErrors().get(0));
    }

    @Test
    public void notValidWhenMissingEmail() throws Exception {
        UserCreationPayload payload = new UserCreationPayload();
        payload.setUsername("jon");
        payload.setPassword("winterfell");

        assertFalse(payload.isValid());
        assertEquals(new Error("400", "email"), payload.getErrors().get(0));
    }

    @Test
    public void notValidWhenMissingPassword() throws Exception {
        UserCreationPayload payload = new UserCreationPayload();
        payload.setUsername("jon");
        payload.setEmail("jon@snow.com");

        assertFalse(payload.isValid());
        assertEquals(new Error("400", "password"), payload.getErrors().get(0));
    }
}