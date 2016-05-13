package com.sistearth.game.validators;

import com.sistearth.api.beans.Error;
import com.sistearth.api.payloads.UserCreationPayload;
import org.junit.Test;

import java.util.List;

import static com.sistearth.test.utils.PayloadTestHelper.hasError;
import static org.junit.Assert.*;

public class UserCreationValidatorTest {
    @Test
    public void testValidPayload() throws Exception {
        UserCreationPayload payload = new UserCreationPayload("jon", "jonsecret", "jon@dot.com");
        UserCreationValidator validator = new UserCreationValidator(payload);

        assertTrue(validator.isValid());
        assertEquals(0, validator.getErrors().size());
    }

    @Test
    public void testInvalidPayload() throws Exception {
        UserCreationPayload payload = new UserCreationPayload();
        UserCreationValidator validator = new UserCreationValidator(payload);

        assertFalse(validator.isValid());
        List<Error> errors = validator.getErrors();
        assertEquals(3, errors.size());
        assertTrue(hasError(errors, "username"));
        assertTrue(hasError(errors, "email"));
        assertTrue(hasError(errors, "password"));
    }
}