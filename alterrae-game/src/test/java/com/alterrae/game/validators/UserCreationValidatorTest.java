package com.alterrae.game.validators;

import com.alterrae.api.beans.Error;
import com.alterrae.api.payloads.UserCreationPayload;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class UserCreationValidatorTest {
    @Test
    public void testValidPayload() throws Exception {
        UserCreationPayload payload = new UserCreationPayload("jon", "jonsecret", "jon@dot.com");
        UserCreationValidator validator = new UserCreationValidator(payload);

        assertTrue(validator.isValid());
        assertTrue(validator.getErrors().isEmpty());
    }

    @Test
    public void testInvalidPayload_Empty() throws Exception {
        UserCreationPayload payload = new UserCreationPayload();
        UserCreationValidator validator = new UserCreationValidator(payload);

        assertFalse(validator.isValid());
        assertEquals(3, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItems(
                new Error("400", "username"),
                new Error("400", "email"),
                new Error("400", "password")
        ));
    }

    @Test
    public void testInvalidPayload_BadEmail() throws Exception {
        UserCreationPayload payload = new UserCreationPayload("jon", "jonsecret", "jon@dot@plop.com");
        UserCreationValidator validator = new UserCreationValidator(payload);

        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "email-bad-syntax")));
    }

    @Test
    public void testInvalidPayload_BadPassword() throws Exception {
        UserCreationPayload payload = new UserCreationPayload("jon", "abc123*", "jon@doe.com");
        UserCreationValidator validator = new UserCreationValidator(payload);

        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "password-bad")));
    }
}