package com.sistearth.view.request.payloads;

import com.sistearth.db.beans.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserUpdatePayloadTest extends PayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload("1", "jon", "newsecret", "jon@new.com", "jonsecret");

        assertTrue(payload.isValid());
        assertEquals(0, payload.getErrors().size());
        assertEquals(
                User.builder().username("jon").password("newsecret").email("jon@new.com").build(),
                payload.getEntity()
        );
    }

    @Test
    public void testInvalidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload();
        assertFalse(payload.isValid());
        assertEquals(2, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "actualPassword"));
        assertTrue(hasError(payload.getErrors(), "emailOrPassword"));
    }
}