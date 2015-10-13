package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.models.beans.User;
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
    }

    @Test
    public void notValidWhenMissingUsername() throws Exception {

        UserCreationPayload payload = new UserCreationPayload();
        payload.setPassword("winterfell");
        payload.setEmail("jon@snow.com");

        assertFalse(payload.isValid());
    }

    @Test
    public void notValidWhenMissingEmail() throws Exception {

        UserCreationPayload payload = new UserCreationPayload();
        payload.setUsername("jon");
        payload.setPassword("winterfell");

        assertFalse(payload.isValid());
    }

    @Test
    public void notValidWhenMissingPassword() throws Exception {

        UserCreationPayload payload = new UserCreationPayload();
        payload.setUsername("jon");
        payload.setEmail("jon@snow.com");

        assertFalse(payload.isValid());
    }
}