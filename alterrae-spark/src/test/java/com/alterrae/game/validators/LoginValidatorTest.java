package com.alterrae.game.validators;

import com.alterrae.api.beans.Error;
import com.alterrae.api.payloads.LoginPayload;
import com.alterrae.api.validators.Validator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class LoginValidatorTest {
    @Test
    public void testValidPayload() throws Exception {
        Validator validator = new LoginValidator(new LoginPayload("jon", "jonsecret"));
        assertTrue(validator.isValid());
        assertTrue(validator.getErrors().isEmpty());
    }

    @Test
    public void testInvalidPayload_NoFields() throws Exception {
        Validator validator = new LoginValidator(new LoginPayload());
        assertFalse(validator.isValid());
        assertEquals(2, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItems(
                new Error("400", "username"),
                new Error("400", "password")
        ));
    }

    @Test
    public void testInvalidPayload_NullFields() throws Exception {
        Validator validator = new LoginValidator(new LoginPayload(null, null));
        assertFalse(validator.isValid());
        assertEquals(2, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItems(
                new Error("400", "username"),
                new Error("400", "password")
        ));
    }

    @Test
    public void testInvalidPayload_EmptyFields() throws Exception {
        Validator validator = new LoginValidator(new LoginPayload("", ""));
        assertFalse(validator.isValid());
        assertEquals(2, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItems(
                new Error("400", "username"),
                new Error("400", "password")
        ));
    }

    @Test
    public void testInvalidPayload_MissingUsername() throws Exception {
        Validator validator = new LoginValidator(LoginPayload.builder().password("jonsecret").build());
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "username")));
    }

    @Test
    public void testInvalidPayload_NullUsername() throws Exception {
        Validator validator = new LoginValidator(new LoginPayload(null, "jonsecret"));
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "username")));
    }

    @Test
    public void testInvalidPayload_EmptyUsername() throws Exception {
        Validator validator = new LoginValidator(new LoginPayload("", "jonsecret"));
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "username")));
    }

    @Test
    public void testInvalidPayload_MissingPassword() throws Exception {
        Validator validator = new LoginValidator(LoginPayload.builder().username("jon").build());
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "password")));
    }

    @Test
    public void testInvalidPayload_NullPassword() throws Exception {
        Validator validator = new LoginValidator(new LoginPayload("jon", null));
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "password")));
    }

    @Test
    public void testInvalidPayload_EmptyPassword() throws Exception {
        Validator validator = new LoginValidator(new LoginPayload("jon", ""));
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertThat(validator.getErrors(), hasItem(new Error("400", "password")));
    }
}