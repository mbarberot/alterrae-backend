package com.sistearth.api.payloads;

import com.sistearth.api.beans.Error;
import com.sistearth.db.api.entity.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class LoginPayloadTest {
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
        assertThat(payload.getErrors(), hasItems(
                new Error("400", "username"),
                new Error("400", "password")
        ));
    }

    @Test
    public void testInvalidPayload_NullFields() throws Exception {
        LoginPayload payload = new LoginPayload(null, null);
        assertFalse(payload.isValid());
        assertEquals(2, payload.getErrors().size());
        assertThat(payload.getErrors(), hasItems(
                new Error("400", "username"),
                new Error("400", "password")
        ));
    }

    @Test
    public void testInvalidPayload_EmptyFields() throws Exception {
        LoginPayload payload = new LoginPayload("", "");
        assertFalse(payload.isValid());
        assertEquals(2, payload.getErrors().size());
        assertThat(payload.getErrors(), hasItems(
                new Error("400", "username"),
                new Error("400", "password")
        ));
    }

    @Test
    public void testInvalidPayload_MissingUsername() throws Exception {
        LoginPayload payload = new LoginPayload();
        payload.setPassword("jonsecret");

        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertThat(payload.getErrors(), hasItem(new Error("400", "username")));
    }

    @Test
    public void testInvalidPayload_NullUsername() throws Exception {
        LoginPayload payload = new LoginPayload(null, "jonsecret");
        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertThat(payload.getErrors(), hasItem(new Error("400", "username")));
    }

    @Test
    public void testInvalidPayload_EmptyUsername() throws Exception {
        LoginPayload payload = new LoginPayload("", "jonsecret");
        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertThat(payload.getErrors(), hasItem(new Error("400", "username")));
    }

    @Test
    public void testInvalidPayload_MissingPassword() throws Exception {
        LoginPayload payload = new LoginPayload();
        payload.setUsername("jon");

        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertThat(payload.getErrors(), hasItem(new Error("400", "password")));
    }

    @Test
    public void testInvalidPayload_NullPassword() throws Exception {
        LoginPayload payload = new LoginPayload("jon", null);
        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertThat(payload.getErrors(), hasItem(new Error("400", "password")));
    }

    @Test
    public void testInvalidPayload_EmptyPassword() throws Exception {
        LoginPayload payload = new LoginPayload("jon", "");
        assertFalse(payload.isValid());
        assertEquals(1, payload.getErrors().size());
        assertThat(payload.getErrors(), hasItem(new Error("400", "password")));
    }
}