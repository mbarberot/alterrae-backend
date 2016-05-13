package com.sistearth.view.request.payloads;

import com.sistearth.api.beans.User;
import com.sistearth.api.payloads.LoginPayload;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginPayloadTest extends PayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        LoginPayload payload = new LoginPayload("jon", "jonsecret");
        assertTrue(payload.isValid());
        assertEquals(
                User.builder().username("jon").password("jonsecret").build(),
                payload.getEntity()
        );
        assertEquals("jon", payload.getUsername());
        assertEquals("jonsecret", payload.getPassword());
    }

    @Test
    public void testInvalidPayload_NoFields() throws Exception {
        LoginPayload payload = new LoginPayload();
        assertFalse(payload.isValid());
        assertEquals(2, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "username"));
        assertTrue(hasError(payload.getErrors(), "password"));
    }

    @Test
    public void testInvalidPayload_NullFields() throws Exception {
        LoginPayload payload = new LoginPayload(null, null);
        assertFalse(payload.isValid());
        assertEquals(2, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "username"));
        assertTrue(hasError(payload.getErrors(), "password"));
    }

    @Test
    public void testInvalidPayload_EmptyFields() throws Exception {
        LoginPayload payload = new LoginPayload("", "");
        assertFalse(payload.isValid());
        assertEquals(2, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "username"));
        assertTrue(hasError(payload.getErrors(), "password"));
    }

    @Test
    public void testInvalidPayload_MissingUsername() throws Exception {
        LoginPayload payload = new LoginPayload();
        payload.setPassword("jonsecret");

        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "username"));
    }
    @Test
    public void testInvalidPayload_NullUsername() throws Exception {
        LoginPayload payload = new LoginPayload(null, "jonsecret");
        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "username"));
    }

    @Test
    public void testInvalidPayload_EmptyUsername() throws Exception {
        LoginPayload payload = new LoginPayload("", "jonsecret");
        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "username"));
    }

    @Test
    public void testInvalidPayload_MissingPassword() throws Exception {
        LoginPayload payload = new LoginPayload();
        payload.setUsername("jon");

        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "password"));
    }

    @Test
    public void testInvalidPayload_NullPassword() throws Exception {
        LoginPayload payload = new LoginPayload("jon", null);
        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "password"));
    }

    @Test
    public void testInvalidPayload_EmptyPassword() throws Exception {
        LoginPayload payload = new LoginPayload("jon", "");
        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "password"));
    }
}