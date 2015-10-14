package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.models.beans.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginPayloadTest {
    @Test
    public void testGetUser() throws Exception {
        User user = new User();
        user.setUsername("jon");
        user.setPassword("winterfell");

        LoginPayload payload = new LoginPayload();
        payload.setUsername("jon");
        payload.setPassword("winterfell");

        assertEquals(user, payload.getEntity());
    }

    @Test
    public void testIsValid() throws Exception {
        LoginPayload payload = new LoginPayload();
        payload.setUsername("jon");
        payload.setPassword("winterfell");

        assertTrue(payload.isValid());
    }

    @Test
    public void notValidWhenMissingUsername() throws Exception {
        LoginPayload payload = new LoginPayload();
        payload.setPassword("winterfell");

        assertFalse(payload.isValid());
    }

    @Test
    public void notValidWhenMissingPassword() throws Exception {
        LoginPayload payload = new LoginPayload();
        payload.setUsername("jon");

        assertFalse(payload.isValid());
    }

}
