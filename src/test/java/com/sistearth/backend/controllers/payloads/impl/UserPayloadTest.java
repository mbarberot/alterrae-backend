package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.models.beans.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserPayloadTest {

    @Test
    public void testGetUser() throws Exception {
        assertEquals(
                new User(),
                new UserPayload().getUser()
        );
    }

    @Test
    public void testIsValid() throws Exception {
        UserPayload validPayload = new UserPayload();
        UserPayload invalidPayload = new UserPayload();
        assertTrue(validPayload.isValid());
        assertFalse(invalidPayload.isValid());
    }
}