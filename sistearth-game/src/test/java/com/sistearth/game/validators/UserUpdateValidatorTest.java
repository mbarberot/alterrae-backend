package com.sistearth.game.validators;

import com.sistearth.api.beans.User;
import com.sistearth.api.payloads.UserUpdatePayload;
import org.junit.Test;

import static com.sistearth.test.utils.PayloadTestHelper.hasError;
import static org.junit.Assert.*;

public class UserUpdateValidatorTest {
    @Test
    public void testValidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload("1", "jon", "newsecret", "jon@new.com", "jonsecret");
        UserUpdateValidator validator = new UserUpdateValidator(payload);

        assertTrue(validator.isValid());
        assertEquals(0, validator.getErrors().size());
    }

    @Test
    public void testInvalidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload();
        UserUpdateValidator validator = new UserUpdateValidator(payload);

        assertFalse(validator.isValid());
        assertEquals(2, validator.getErrors().size());
        assertTrue(hasError(validator.getErrors(), "actualPassword"));
        assertTrue(hasError(validator.getErrors(), "emailOrPassword"));
    }

}