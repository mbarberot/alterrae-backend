package com.alterrae.game.validators;

import com.alterrae.api.payloads.UserUpdatePayload;
import com.alterrae.api.beans.Error;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class UserUpdateValidatorTest {

    @Test
    public void testValidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload("jon", "newsecret", "jon@new.com", "jonsecret");
        UserUpdateValidator validator = new UserUpdateValidator(payload);

        assertTrue(validator.isValid());
        assertTrue(validator.getErrors().isEmpty());
    }

    @Test
    public void testInvalidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload();
        UserUpdateValidator validator = new UserUpdateValidator(payload);

        assertFalse(validator.isValid());
        assertEquals(2, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItems(
                new Error("400", "actualPassword"),
                new Error("400", "emailOrPassword")
        ));

    }

    @Test
    public void testBadEmailSyntax() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload("jon", "newsecret", "jon@new@plop.com", "jonsecret");
        UserUpdateValidator validator = new UserUpdateValidator(payload);

        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "email-bad-syntax")));
    }

    @Test
    public void testPasswordTooShort() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload("jon", "2short*", "jon@new.com", "jonsecret");
        UserUpdateValidator validator = new UserUpdateValidator(payload);

        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "password-bad")));
    }
}