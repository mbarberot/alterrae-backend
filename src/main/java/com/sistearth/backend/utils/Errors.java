package com.sistearth.backend.utils;

public class Errors {
    public class Login {
        public static final String BAD_CREDENTIALS = "sistearth-error-login-bad-credentials";
    }

    public class User {
        public static final String NOT_VALID = "sistearth-error-user-not-valid";
        public static final String ALREADY_EXISTS = "sistearth-error-user-already-exists";
    }
}
