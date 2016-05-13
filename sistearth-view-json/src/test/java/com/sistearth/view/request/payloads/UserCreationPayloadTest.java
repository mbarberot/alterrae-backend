package com.sistearth.view.request.payloads;

import com.sistearth.api.beans.User;
import com.sistearth.api.payloads.UserCreationPayload;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCreationPayloadTest extends PayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        UserCreationPayload payload = new UserCreationPayload("jon", "jonsecret", "jon@dot.com");

        assertTrue(payload.isValid());
        assertEquals(0, payload.getErrors().size());
        assertEquals(
                User.builder().username("jon").password("jonsecret").email("jon@dot.com").build(),
                payload.getEntity()
        );
    }

    @Test
    public void testInvalidPayload() throws Exception {
        UserCreationPayload payload = new UserCreationPayload();
        assertFalse(payload.isValid());
        assertEquals(3, payload.getErrors().size());
        assertTrue(hasError(payload.getErrors(), "username"));
        assertTrue(hasError(payload.getErrors(), "email"));
        assertTrue(hasError(payload.getErrors(), "password"));
    }
}