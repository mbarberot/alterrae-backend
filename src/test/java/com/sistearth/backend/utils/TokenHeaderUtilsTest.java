package com.sistearth.backend.utils;

import org.junit.Test;

import static com.sistearth.backend.utils.TokenHeaderUtils.extractToken;
import static org.junit.Assert.assertEquals;

public class TokenHeaderUtilsTest {

    @Test
    public void testExtractToken() throws Exception {
        assertEquals("xxXx", extractToken("Bearer xxXx"));
    }
}