package com.sistearth.view.request.payloads;

import com.sistearth.db.api.entity.User;
import com.sistearth.api.payloads.UserUpdatePayload;
import org.junit.Test;

import static com.sistearth.test.utils.PayloadTestHelper.hasError;
import static org.junit.Assert.*;

public class UserUpdatePayloadTest extends PayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload("jon", "newsecret", "jon@new.com", "jonsecret");
        assertEquals(
                User.builder().username("jon").password("newsecret").email("jon@new.com").build(),
                payload.getEntity()
        );
    }

    @Test
    public void testInvalidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload();
        assertEquals(new User(), payload.getEntity());
    }
}