package com.alterrae.game.validators;

import com.alterrae.api.beans.Error;
import com.alterrae.api.payloads.UserDeletePayload;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class UserDeleteValidatorTest {
    @Test
    public void valid() throws Exception {
        UserDeleteValidator validator = new UserDeleteValidator(new UserDeletePayload("somepassword"));
        assertTrue(validator.isValid());
        assertTrue(validator.getErrors().isEmpty());
    }

    @Test
    public void invalid() throws Exception {
        UserDeleteValidator validator = new UserDeleteValidator(new UserDeletePayload());
        assertFalse(validator.isValid());
        assertThat(validator.getErrors(), hasItem(new Error("400", "missing-actual-password")));
    }
}